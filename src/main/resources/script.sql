create table entry(
id bigint identity primary key, 
date_entry nvarchar(4000), 
title_entry nvarchar(4000), 
url_entry nvarchar(4000),
date_insert date, 
date_published date,
link nvarchar(4000),
title nvarchar(4000),
source varchar(255), 
source_label varchar(255), 
status tinyint,
random_factor bigint)

create table tag( 
entry_id bigint,
id varchar(255),
label varchar(255),
clicks bigint,
primary key (entry_id, id)
)

insert into tag (entry_id, id, label, clicks)
select id, source, source_label, 0 from entry

insert into tag
select entry_id, id, label, 0 from (
select id entry_id, 'comunidade' id, 'Comunidade' label from entry where source='cvj' or source='prefeitura' or source='defesa_civil'
union
select id entry_id, 'negocios' id, 'Negócios' label from entry where source='acij' or source='ajorpeme' or source='cdl_joinville'
union
select id entry_id, 'educacao' id, 'Educação' label from entry where source='sociesc' or source='udesc' or source='univille'
) nt
