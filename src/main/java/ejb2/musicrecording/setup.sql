create table music_recordings (
	id int primary key,
	artist varchar(20),
	title  varchar(40),
	publisher int,	// fkey to publishers
	double price,
	int stockcount
);

create table music_publishers (
	id int primary key,
	name varchar(40),
	city varchar(40),
	phone varchar(20)
);
