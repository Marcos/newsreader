create table entry(
id bigint primary key, 
date_entry nvarchar(4000), 
title_entry nvarchar(4000), 
url_entry nvarchar(4000),
date_insert datetime, 
date_published date,
link nvarchar(4000),
title nvarchar(4000),
source varchar(255), 
source_label varchar(255), 
status tinyint,
random_factor bigint);

alter table entry add clicks bigint default 0 with values;
alter table entry add short_link nvarchar(4000);
alter table entry add twitter_status tinyint;

insert into entry2 select id, date_entry, title_entry, url_entry, date_insert, date_published, link, title, source, source_label, status, random_factor, clicks, short_link from entry

create table tag(
	id varchar(255),
	label varchar(255),
	clicks bigint,
	primary key (id)
);

create table tag_entry( 
	entry_id bigint,
	tag_id varchar(255)
	primary key (entry_id, tag_id)
);

insert into tag (id,label,clicks) values ('comunidade', 'Comunidade', 0)
insert into tag (id,label,clicks) values ('educacao', 'Educação', 0)
insert into tag (id,label,clicks) values ('negocios', 'Negócios', 0)
insert into tag (id,label,clicks) values ('geral', 'Geral', 0)

insert into tag_entry
select entry_id, tag_id from (
select id entry_id, 'comunidade' tag_id from entry where source='cvj' or source='prefeitura' or source='defesa_civil'
union
select id entry_id, 'negocios' tag_id from entry where source='acij' or source='ajorpeme' or source='cdl_joinville'
union
select id entry_id, 'educacao' tag_id from entry where source='sociesc' or source='udesc' or source='univille'
union
select id entry_id, 'geral' tag_id from entry where source='portal_joinville' or source='nd_joinville'
) nt;

create table access(
id bigint identity primary key, 
referer	 nvarchar(4000), 
url_access nvarchar(4000), 
header nvarchar(4000), 
ip_source nvarchar(255),
date_access datetime);

alter table access add user_agent nvarchar(4000);

