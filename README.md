# learning_demo
学习的一些demo
#postgres 数据库dump
1. 连接服务器
ssh root@192.168.3.16
密码:1809
2. 切换目录
cd /tmp
3. 清理无关数据
rm -rf olap*
4. 切换用户
su postgres
5. 切换目录
cd /usr/pgsql-11/bin
6. 导出表数据
./pg_dump -d olap -t excel_config -O -x -c -f /tmp/olap_excel_config.sql
./pg_dump -d olap -t table_ruler -O -x -c -f /tmp/olap_table_ruler.sql
./pg_dump -d 数据库名 -t 表名 -O -x -c -f 导出路径
7. 压缩数据
zip olap.zip olap_excel_config.sql olap_table_ruler.sql
8. 拷贝到gitlab服务器
scp root@192.168.3.16:/tmp/olap.zip .

./pg_dump -d devdb -t a01_2018 -O -x -c -f /tmp/devdb_a01_2018.sql
./pg_dump -d devdb -t b01_2018 -O -x -c -f /tmp/devdb_b01_2018.sql
./pg_dump -d devdb -t a01_2018 -O -x -c -f /tmp/devdb_a01_2018.sql
./pg_dump -d devdb -t b01_2018 -O -x -c -f /tmp/devdb_b01_2018.sql
./pg_dump -d devdb -t b01_2018 -O -x -f /tmp/devdb_b01_2018.sql
./psql -d olap -f /tmp/olap.sql

-- 创建序列
CREATE SEQUENCE PxClassFileUpload_id
START WITH 1
INCREMENT BY 1
NO MINVALUE
NO MAXVALUE
CACHE 1;
-- 使用序列
alter table "PxClassFileUpload" alter column id set default nextval('PxClassFileUpload_id');

-- 查询死锁
SELECT * FROM pg_stat_activity WHERE datname='olap' and wait_event_type = 'Lock' order by pid;
SELECT pg_cancel_backend(3783);
-- 查询死锁
select oid,relname from pg_class where relname='mem_info';
select * from pg_locks where relation= '16439';
select pg_cancel_backend('3584');


-- uuid
-- 创建uuid函数
create extension "uuid-ossp" ;
select uuid_generate_v4();

-- 修改数据库密码
ALTER USER postgres WITH PASSWORD '20191809';

