# MonkeySpringboot(感动猴子)
搭建一台LDAP服务器，使其域名配置为touch.cn

##

## 修改application.yml
![域服务器.png](http://blog.dahouzi.cn/blog/picture/域服务器.png?imageView/2/w/800)
ldap:
    adminAccount: Administrator@touch.cn
    base: OU=广州***科技有限公司,DC=touch,DC=cn
    domainName: '@touch.cn'
    referral: follow
    url: ldap://172.209.60.5:389
    userDn: cn=Administrator,cn=Users,dc=touch,dc=cn
    userPwd: mingwenmima