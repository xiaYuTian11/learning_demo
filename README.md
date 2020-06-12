# learning_demo
学习的一些demo
#postgres 数据库dump
```shell script
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
```




# postgresql 相关操作

## 创建序列
```sql
CREATE SEQUENCE PxClassFileUpload_id
START WITH 1
INCREMENT BY 1
NO MINVALUE
NO MAXVALUE
CACHE 1;
```

## 使用序列
```sql
alter table "PxClassFileUpload" alter column id set default nextval('PxClassFileUpload_id');
```
## 重置序列
```sql
alter sequence pxhistorydata_id restart with 1;
```

## 查询死锁
```sql
SELECT * FROM pg_stat_activity WHERE datname='olap' and wait_event_type = 'Lock' order by pid;
SELECT pg_cancel_backend(3783);
```

## 查询死锁
```sql
select oid,relname from pg_class where relname='mem_info';
select * from pg_locks where relation= '16439';
select pg_cancel_backend('3584');
```


## 创建uuid函数
```sql
create extension "uuid-ossp" ;
select uuid_generate_v4();
```


## 修改数据库密码
```sql
ALTER USER postgres WITH PASSWORD '20191809';
```


# 服务器相关
## 解决服务器资源中文乱码
convmv   wget 解决服务器中文乱码
```shell script
yum -y install wget
wget http://www.j3e.de/linux/convmv/convmv-1.15.tar.gz
tar -zxvf convmv-1.15.tar.gz
cd convmv-1.15
make && make install
cd /home/gb1809/webapp/upload/impFile/Photos/
convmv  -f GBK -t UTF-8 --notest  *.jpg

```
