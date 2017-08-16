package cn.duanshaojie.service;

import cn.duanshaojie.entity.JPartyEntity;

public interface JPartyService {
	public JPartyEntity saveParty(JPartyEntity entity);
	public JPartyEntity findPartyById(long partyId);
}
