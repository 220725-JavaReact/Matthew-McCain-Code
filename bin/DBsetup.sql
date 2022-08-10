create table categories (
	category varchar(255) primary key
);

create table Products (
	id serial primary key,
	name varchar(255),
	price decimal,
	category varchar(255) references categories (category),
	description text
);

create table states (
	state varchar(2) primary key
);

create table addresses (
	id bigserial primary key,
	building integer,
	street varchar(255),
	city varchar(255),
	state varchar(2) references states (state),
	zip smallint
);

create table customers (
	id serial primary key,
	name varchar(255),
	address bigint references addresses (id),
	email varchar(255) unique,
	phone integer
);

create table store_fronts (
	id serial primary key,
	name varchar(255),
	address bigint references addresses (id),
	phone integer
);

create table inventory (
	store_id integer  references store_fronts (id),
	product_id integer  references products (id),
	quantity integer,
	primary key(store_id, product_id)
);

create table orders (
	id serial primary key,
	store_id integer references store_fronts (id),
	customer_id integer references customers (id),
	total decimal
);

create table line_items (
	order_id integer  references orders (id),
	product_id integer  references products (id),
	quantity integer,
	primary key(order_id, product_id)
);
$