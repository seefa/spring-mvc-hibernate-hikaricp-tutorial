# CREATE YOUR OWN DATABASE and add this table

create table if not exists contact
(
	contact_id int auto_increment,
	name varchar(50) charset utf8 not null,
	family varchar(50) charset utf8 null,
	birthday date null,
	phone varchar(15) null,
	telephone varchar(20) null,
	email varchar(100) null,
	address varchar(100) null,
	constraint contact_contact_id_uindex
		unique (contact_id)
);

alter table contact
	add primary key (contact_id);

