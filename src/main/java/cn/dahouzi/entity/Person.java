package cn.dahouzi.entity;

import javax.naming.Name;

import org.springframework.ldap.odm.annotations.Attribute;
import org.springframework.ldap.odm.annotations.DnAttribute;
import org.springframework.ldap.odm.annotations.Entry;
import org.springframework.ldap.odm.annotations.Id;

@Entry(base = "ou=广州华欣电子科技有限公司,dc=touch,dc=cn", objectClasses = "Person")
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

	public Name getId() {
		return id;
	}

	public void setId(Name id) {
		this.id = id;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getCommonName() {
		return commonName;
	}

	public void setCommonName(String commonName) {
		this.commonName = commonName;
	}

	public String getSuerName() {
		return suerName;
	}

	public void setSuerName(String suerName) {
		this.suerName = suerName;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

}