package cn.dahouzi.common;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 配置文件
 * 2020年3月24日10:20:49
 * @author dahouzi
 */
@Component
public class SystemProperties {


	@Value("${ldap.url}")
	private String url;
	@Value("${ldap.base}")
	private String bsae;
	@Value("${ldap.userDn}")
	private String userDn;
	@Value("${ldap.adminAccount}")
	private String adminAccount;
	@Value("${ldap.userPwd}")
	private String userPwd;
	@Value("${ldap.referral}")
	private String referral;
	@Value("${ldap.domainName}")
	private String domainName;


	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getBsae() {
		return bsae;
	}

	public void setBsae(String bsae) {
		this.bsae = bsae;
	}

	public String getUserDn() {
		return userDn;
	}

	public void setUserDn(String userDn) {
		this.userDn = userDn;
	}

	public String getAdminAccount() {
		return adminAccount;
	}

	public void setAdminAccount(String adminAccount) {
		this.adminAccount = adminAccount;
	}

	public String getUserPwd() {
		return userPwd;
	}

	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}

	public String getReferral() {
		return referral;
	}

	public void setReferral(String referral) {
		this.referral = referral;
	}

	public String getDomainName() {
		return domainName;
	}

	public void setDomainName(String domainName) {
		this.domainName = domainName;
	}



}
