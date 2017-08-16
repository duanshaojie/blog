package cn.duanshaojie.service;

public interface EmailService {
	public boolean verifyCode(String address, String verifyCode);
	void sendRegisterCode(String address);
}
