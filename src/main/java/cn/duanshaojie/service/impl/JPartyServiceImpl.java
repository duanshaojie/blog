package cn.duanshaojie.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.duanshaojie.dao.JPartyDao;
import cn.duanshaojie.entity.JPartyEntity;
import cn.duanshaojie.service.JPartyService;

@Service
public class JPartyServiceImpl implements JPartyService {

	@Autowired
	private JPartyDao dao;
	@Override
	public JPartyEntity saveParty(JPartyEntity entity) {
		return dao.save(entity);
	}
	@Override
	public JPartyEntity findPartyById(long partyId) {
		return dao.findOne(partyId);
	}

}
