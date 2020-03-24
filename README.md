# MonkeySpringboot(感动猴子)
搭建一台LDAP服务器，使其域名配置为touch.cn

##

## 1. 修改application.yml
![域服务器.png](http://blog.dahouzi.cn/blog/picture/域服务器.png?imageView/2/w/800)
```
ldap:
    adminAccount: Administrator@touch.cn
    base: OU=广州***科技有限公司,DC=touch,DC=cn
    domainName: '@touch.cn'
    referral: follow
    url: ldap://172.209.60.5:389
    userDn: cn=Administrator,cn=Users,dc=touch,dc=cn
    userPwd: mingwenmima
```

## 2. 修改Person的绑定的企业名称及域
```java
@Entry(base = "ou=广州**电子科技有限公司,dc=touch,dc=cn", objectClasses = "Person")//这一行
public final class Person {
	@Id
	private Name id;
	@DnAttribute(value = "uid", index = 1)
	private String uid;
	@Attribute(name = "cn")
	private String commonName;
	@Attribute(name = "sn")
	private String suerName;
	private String userPassword;
}

```


## 3. 打开http://127.0.0.1:8080/ldap/findAllUser
即可看到企业所有用户成员
