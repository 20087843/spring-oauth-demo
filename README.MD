Spring Cloud Oauth
===

### 项目运行
1. 启动 oauth-server
2. 密码模式授权



### Grant Type 授权模式
![TokenGranter](resources/grant_type.jpg)
TokenGranter 五个实现类分别对应物种授权模式
 - ResourceOwnerPasswordTokenGranter : password密码模式
 - AuthorizationCodeTokenGranter : authorization_code授权码模式
 - ClientCredentialsTokenGranter : client_credentials客户端模式
 - ImplicitTokenGranter : implicit简化模式
 - RefreshTokenGranter : refresh_token 刷新token

### 参考资料
1. [Spring Cloud OAuth2 实现单点登录](https://www.jianshu.com/p/ea0a7d89f5f0)
2. [Spring Cloud OAuth2 搭建授权服务](https://www.cnblogs.com/fp2952/p/8973613.html)
3. [Spring Security Oauth2 之 架构源码解读](https://blog.csdn.net/wuzhiwei549/article/details/79808509)

### 参考项目
1. [基于Spring Security 5的OAuth2通用登录模块](https://gitee.com/dgut-sai/security-oauth2)