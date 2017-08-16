package cn.duanshaojie.dao;

import org.springframework.data.repository.CrudRepository;

import cn.duanshaojie.entity.JUserEntity;

public interface JUserDao extends CrudRepository<JUserEntity, Long> {
	public JUserEntity findByUsername(String username);
	public JUserEntity findByUsernameAndPassword(String username,String password);
}
