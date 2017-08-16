package cn.duanshaojie.service;

import cn.duanshaojie.entity.JUserEntity;
import cn.duanshaojie.vo.UserVO;

public interface JUserService {
	public JUserEntity save(JUserEntity entity);
	public JUserEntity findByUsername(String username);
	public JUserEntity findByUsernameAndPassword(String username,String password);
	public UserVO getUserInfo(JUserEntity userUsernameEntity);
}
