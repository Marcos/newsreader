create table entry(
id bigint not null auto_increment, 
date_entry text, 
title_entry text, 
url_entry text,
date_insert date, 
date_published date,
link text,
title text,
source varchar(255), 
sourceLabel varchar(255), 
status tinyint,
random_factor bigint,
primary key(id));