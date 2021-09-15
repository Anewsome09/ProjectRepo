DROP SCHEMA IF EXISTS ims CASCADE;

CREATE SCHEMA ims AUTHORIZATION postgres;

CREATE TABLE ims.product(
	product_id serial not null primary KEY,
	title varchar(40),
	category varchar(20),
	man varchar(40),
	product_quantity int,
	min_limit int
);


CREATE TABLE ims.product_stock (
	stock_id serial not null,
	product_id int,
	transaction_date date NOT NULL,
	vendor varchar(40) NOT NULL,
	batch_code varchar(10),
	invoice_num varchar(10),
	quantity int not null,
	transaction_type varchar(3) NOT null,
	primary key (stock_id),
	constraint fk_product
		foreign key (product_id) 
			references ims.product(product_id)
);

INSERT INTO ims.product (title, category, man, min_limit, product_quantity) VALUES ('Maple Chest Drawer', 'furniture', 'Furn Builder Co.', 10, 30);
INSERT INTO ims.product (title, category, man, min_limit, product_quantity) VALUES ('TV', 'electronics', 'Samsung', 5, 24);
INSERT INTO ims.product (title, category, man, min_limit, product_quantity) VALUES ('Dresser', 'furniture', 'Furn Builder Co.', 10, 20);
INSERT INTO ims.product (title, category, man, min_limit, product_quantity) VALUES ('TV stand', 'furniture', 'Ikea', 4, 10);
INSERT INTO ims.product (title, category, man, min_limit, product_quantity) VALUES ('Switch', 'electronics', 'Nintendo', 20, 50);
INSERT INTO ims.product (title, category, man, min_limit, product_quantity) VALUES ('Microwave', 'kitchen', 'General Electric', 20, 50);

INSERT INTO ims.product_stock (product_id, transaction_date, vendor, batch_code, invoice_num, quantity, transaction_type) VALUES (1, '2021/08/25', 'Goodwill LLC', 'ABC122', 'JAN23002', 3, 'IN');
INSERT INTO ims.product_stock (product_id, transaction_date, vendor, batch_code, invoice_num, quantity, transaction_type) VALUES (2, '2021/05/30', 'Samsung', 'GKD134', 'BIG1932', 10, 'IN');
INSERT INTO ims.product_stock (product_id, transaction_date, vendor, batch_code, invoice_num, quantity, transaction_type) VALUES (3, '2021/05/30', 'Goodwill LLC', 'GFD114', 'AUG1932', 3, 'OUT');
INSERT INTO ims.product_stock (product_id, transaction_date, vendor, batch_code, invoice_num, quantity, transaction_type) VALUES (4, '2021/05/30', 'IKEA', 'IKD912', 'AFS3921', 5, 'OUT');
INSERT INTO ims.product_stock (product_id, transaction_date, vendor, batch_code, invoice_num, quantity, transaction_type) VALUES (5, '2021/05/30', 'NINTENDO', 'TKG814', 'ADDD9322', 10, 'OUT');
INSERT INTO ims.product_stock (product_id, transaction_date, vendor, batch_code, invoice_num, quantity, transaction_type) VALUES (6, '2021/06/05', 'Goodwill LLC', 'LAD814', 'GKS9322', 2, 'IN');

--function for updating the product_quantity with the quantity of the product_stock table
CREATE or replace function update_product_quantity_func()
returns trigger 
LANGUAGE plpgsql
AS $$
begin
UPDATE ims.product p
set product_quantity = case 
	when transaction_type = 'IN' then product_quantity + quantity
	when transaction_type = 'OUT' then product_quantity - quantity
	end
FROM 
    ims.product_stock s
WHERE 
    p.product_id = s.product_id
and s.stock_id = (select max(stock_id) from ims.product_stock );
 return null;
end;
$$;

--trigger made to run after a row is insertee onto product_stock to take that rows quantity
--and update product with the update_product_quantity_func
CREATE TRIGGER check_insert
    after INSERT ON ims.product_stock 
    for each row
    EXECUTE function update_product_quantity_func();