



create table if not exists article_home(
    id bigint not null auto_increment comment '自增主键id',
    create_time timestamp default current_timestamp comment '记录的创建时间',
    update_time timestamp default current_timestamp on update current_timestamp comment '记录的更新时间',
    create_user varchar(256) not null default '' comment '记录创作者名字',
    update_user varchar(256) not null default '' comment '记录更新者名字',
    province_id int not null default '0' comment '省份id',
    city_id int not null default '0' comment '市id',
    county_id int not null default '0' comment '县id',
    size int not null default '10' comment '每页的文章数',
    max_behot_time timestamp default current_timestamp comment '最大时间',
    min_behot_time timestamp default current_timestamp comment '最小时间',
    tag varchar(64) not null default '' comment '数据范围,例如频道id',
    primary key(id)
)default character set='utf8mb4' comment='article home' engine=innodb;


create table if not exists article(
    id bigint not null auto_increment comment '自增主键id',
    title varchar(128) not null default '' comment '文章标题',
    author_id bigint not null default '0' comment '作者uid',
    author_name varchar(16) not null default '' comment '作者名字',
    channel_id bigint not null default '0' comment '频道id',
    channel_name varchar(16) not null default '' comment '频道名字',
    layout tinyint default null comment '文章布局;0,无图;1,单图;2,多图',
    flag tinyint default null comment '文章标记;0,普通文章;1,热点文章;',
    images varchar(1024) default null comment '文章图片url,逗号分隔;',
    labels varchar(256) default null comment '文章标签,逗号分隔',
    likes bigint default 0 comment '点赞数量',
    collection bigint default 0 comment '收藏数量',
    comment_count bigint default 0 comment '评论数量',
    view_count bigint default 0 comment '阅读数量',
    province_id int not null default '0' comment '省份id',
    city_id int not null default '0' comment '市id',
    county_id int not null default '0' comment '县id',
    create_time timestamp default current_timestamp comment '创建时间',
    publish_time timestamp default current_timestamp comment '发布时间',
    sync_status tinyint default 0 comment '同步状态',
    origin tinyint default 0 comment '来源',

    primary key(id)
)default character set='utf8mb4' comment='文章信息表' engine=innodb;







