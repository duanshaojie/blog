package cn.duanshaojie.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.duanshaojie.dao.JUserDao;
import cn.duanshaojie.entity.JPartyEntity;
import cn.duanshaojie.entity.JUserEntity;
import cn.duanshaojie.service.JPartyService;
import cn.duanshaojie.service.JUserService;
import cn.duanshaojie.vo.UserVO;

@Service
public class JUserServiceImpl implements JUserService {

	@Autowired
	private JUserDao dao;
	@Autowired
	private JPartyService partyService;
	
	@Override
	public JUserEntity save(JUserEntity entity) {
		return dao.save(entity);
	}

	@Override
	public JUserEntity findByUsername(String username) {
		return dao.findByUsername(username);
	}

	@Override
	public JUserEntity findByUsernameAndPassword(String username, String password) {
		return dao.findByUsernameAndPassword(username, password);
	}

	@Override
	public UserVO getUserInfo(JUserEntity userUsernameEntity) {
		UserVO vo = new UserVO();
		long partyId = userUsernameEntity.getPartyId();
		JPartyEntity partyEntity = partyService.findPartyById(partyId);
		vo.setId(userUsernameEntity.getUserId());
		vo.setHead(partyEntity.getHead());
		vo.setMobile(userUsernameEntity.getMobile());
		vo.setNickname(partyEntity.getNickname());
		vo.setTitle(partyEntity.getTitle());
		vo.setUsername(userUsernameEntity.getUsername());
		return vo;
	}
}
