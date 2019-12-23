create database seckill;
use seckill;
create table seckill(
    `seckill_id` bigint not null auto_increment comment '商品库存ID',
    `name` varchar(120) not null comment '商品名称',
    `start_time` timestamp not null default current_timestamp comment '秒杀开始时间',
    `end_time` timestamp not null default current_timestamp comment '秒杀结束时间',
    `create_time` timestamp not null  default current_timestamp comment '创建时间',
    primary key (seckill_id),
    key idx_start_time(start_time),
    key idx_end_time(end_time),
    key idx_create_time(create_time)
) engine = innoDB auto_increment = 1000 default charset = utf8 comment '秒杀库存表';

-- 插入初始化数据
insert into
  seckill(name,number,start_time,end_time)
values
  ('1000元秒杀iphone6',100,'2016-5-22 00:00:00','2016-5-23 00:00:00'),
  ('500元秒杀iPad2',200,'2016-5-22 00:00:00','2016-5-23 00:00:00'),
  ('300元秒杀小米4',300,'2016-5-22 00:00:00','2016-5-23 00:00:00'),
  ('200元秒杀红米note',400,'2016-5-22 00:00:00','2016-5-23 00:00:00');


select * from seckill;


create table success_killed(
    `seckill_id` bigint not null comment '秒杀商品ID',
    `user_phone` bigint not null comment '用户手机号',
    `state` tinyint not null default -1 comment '状态标示:-1无效 0成功 1已付款',
    `create_time` timestamp not null comment '创建时间',
    primary key (seckill_id,user_phone),
    key idx_create_time(create_time)
)engine = innoDB default charset = utf8 comment = '秒杀成功明细表';