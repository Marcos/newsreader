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