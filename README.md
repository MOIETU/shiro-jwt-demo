# shiro-jwt-demo
使用shiro + jwt实现基于token的登陆认证模块

## 步骤

1、执行doc目录的sql文件

2、配置application.yml中数据库，redis

3、启动项目

## 介绍

##### 基于shiro + jwt + redis对实现的自定义token登陆认证模块，主要用于APP用户进行登录认证.

由于用户在使用APP的时候，涉及到用户持续使用，token失效时间续期的问题。

本demo中，token默认的失效时间为30天，其中当token使用超过5分钟后，那一次请求的response中会返回新的token(新的token从刷新那一刻重新开始按30天算)，前端获取到新的token替换掉旧的token。

并且旧的token将可以继续使用15秒(同时请求多个接口，最先结束的接口，将会使其它接口的token失效，因此需要设置一个缓冲时间)

#### 待优化

目前该demo，代码目录和maven模块划分有点凌乱，后续会进行调整。