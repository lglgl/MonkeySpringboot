
package cn.dahouzi.ldap;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.dahouzi.entity.LdapDao;

@Controller
@RequestMapping("/ldap")
public class LdapCtrl {

	@Autowired
	private LdapDao alldao;


	@ResponseBody
	@RequestMapping(value = "/findAllUser", method = RequestMethod.GET)
	public ArrayList<String> findAllUser() throws Exception {
		ArrayList<String> users=new ArrayList<>();
		alldao.findAll().forEach(p -> {
			users.add(p.getCommonName());
		});
		return users;
	}

	

}
