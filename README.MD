# service-rpc-kafka
rpc 远程调用kafka解耦，定时器自动重试失败的调用。

# 模块说明
```
service-rpc-kafka
├── service-api -- 系统共用api模块
├── service-consumer -- rpc服务消费模块(8062)
├── service-producter-- rpc服务生产模块(8061)
├── service-rpc-fail-dispose -- rpc失败服务处理模块(8061)
├── sql -- 数据库初始化脚本
```
# 使用技术说明
```
springboot
mysql
mybatis-plus
kafka
dubbo
zookeeper
druid
```
# 接口测试
http://localhost:8062/test

# 博客介绍
https://blog.csdn.net/u012954380/article/details/106519171

# 时序图
![Image text](https://github.com/Yanyf765/service-rpc-kafka/blob/master/img/uml.png)


