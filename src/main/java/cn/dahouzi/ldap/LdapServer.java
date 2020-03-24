package cn.dahouzi.ldap;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

import javax.naming.AuthenticationException;
import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;
import javax.naming.ldap.InitialLdapContext;
import javax.naming.ldap.LdapContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import cn.dahouzi.common.SystemProperties;

@Controller
public class LdapServer {

	@Autowired
	private SystemProperties sysProperties;

	public Attributes atr;

	@SuppressWarnings("finally")
	public LdapContext getConnect(String username, String password, String URL) {
		LdapContext ctx = null;
		Hashtable<String, String> HashEnv = new Hashtable<String, String>();
		HashEnv.put(Context.SECURITY_AUTHENTICATION, "simple"); // LDAP访问安全级别(none,simple,strong)
		HashEnv.put(Context.SECURITY_PRINCIPAL, username); // AD的用户名
		HashEnv.put(Context.SECURITY_CREDENTIALS, password); // AD的密码
		HashEnv.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory"); // LDAP工厂类
		HashEnv.put("com.sun.jndi.ldap.connect.timeout", "5000");// 连接超时设置为3秒
		HashEnv.put(Context.PROVIDER_URL, URL);// 默认端口389123qweASD

		try {
			ctx = new InitialLdapContext(HashEnv, null);// new
														// InitialDirContext(HashEnv);
														// 初始化上下文
			System.out.println("管理员身份验证成功!");

		} catch (AuthenticationException e) {
			System.out.println("身份验证失败!");
			e.printStackTrace();
		} catch (javax.naming.CommunicationException e) {
			System.out.println("AD域连接失败!");
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println("身份验证未知异常!");
			e.printStackTrace();
		} finally {
			return ctx;
		}
	}

	public NamingEnumeration<SearchResult> getUser(LdapContext ctx, String searchUser) throws Exception {
		// 设置过滤条件
		String filter = "(&(objectClass=top)(objectClass=organizationalPerson)(displayname=" + searchUser + "))";
		// 限制要查询的字段内容
		String[] attrPersonArray = { "uid", "distinguishedName", "userPassword", "displayName", "cn", "sn", "mail",
				"description" };
		SearchControls searchControls = new SearchControls();
		searchControls.setSearchScope(SearchControls.SUBTREE_SCOPE);
		// 设置将被返回的Attribute
		searchControls.setReturningAttributes(attrPersonArray);
		// 三个参数分别为：
		// 上下文；
		// 要搜索的属性，如果为空或 null，则返回目标上下文中的所有对象；
		// 控制搜索的搜索控件，如果为 null，则使用默认的搜索控件
		NamingEnumeration<SearchResult> answer = ctx.search("OU=广州华欣电子科技有限公司,DC=touch,DC=cn", filter.toString(),
				searchControls);
		// 输出查到的数据
		if (null != ctx) {
			try {
				ctx.close();
				ctx = null;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return answer;

	}

	public void testSearch() {
		System.out.println("连接属性：URL" + sysProperties.getUrl() + sysProperties.getAdminAccount());
		System.out.println("管理员：" + sysProperties.getAdminAccount());
		System.out.println("密码：" + sysProperties.getUserPwd());
	}

	/**
	 * 部门查询
	 * @param deptName 部门名称
	 * @return map:如果map.size()不为0，则查询成功；否则为发生异常       null:部门不存在
	 */
	public Map<String, String> findDept(LdapContext ctx, String deptName) {
		Map<String, String> messageMap = new HashMap<>();
		SearchControls searchCtls = new SearchControls();
		searchCtls.setSearchScope(SearchControls.SUBTREE_SCOPE);
		searchCtls.setReturningAttributes(null);

		try {
			NamingEnumeration<SearchResult> answer = ctx.search("OU=广州华欣电子科技有限公司,DC=touch,DC=cn", "", searchCtls);
			while (answer.hasMoreElements()) {
				SearchResult sr = answer.next();
				Attributes attrs = sr.getAttributes();
				if (attrs != null) {
					for (NamingEnumeration<? extends Attribute> e = attrs.getAll(); e.hasMore();) {
						String user = e.next().toString().replace(" ", "");
						String[] buff = user.split(":");
						messageMap.put(buff[0], buff[1]);
						System.err.println(buff[0] + "*********" + buff[1]);
					}
				}
			}

			return messageMap;
		} catch (NamingException e) {
			Map<String, String> nullMap = new HashMap<>();
			return nullMap;
		}
	}

}
