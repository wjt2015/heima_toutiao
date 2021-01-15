



create table if not exists article_home(
    id bigint not null auto_increment comment '自增主键id',
    create_time timestamp default current_timestamp comment '记录的创建时间',
    update_time timestamp default current_timestamp on update current_timestamp comment '记录的更新时间',
    create_user varchar(256) not null default '' comment '记录创作者名字',
    update_user varchar(256) not null default '' comment '记录更新者名字',
    province_id int not null default '0' comment '省份id',
    city_id int not null default '0' comment '市id',
    county_id int not null default '0' comment '县id',
    max_behot_time timestamp default current_timestamp comment '最大时间',
    min_behot_time timestamp default current_timestamp comment '最小时间',
    tag varchar(64) not null default '' comment '数据范围,例如频道id',
    primary key(id)
)default character set='utf8' comment='article home' engine=innodb;





