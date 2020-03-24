package cn.dahouzi.entity;

import javax.naming.Name;

import org.springframework.data.repository.CrudRepository;

public interface LdapDao extends CrudRepository<Person, Name> {

}
