package cn.duanshaojie.service.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import cn.duanshaojie.service.EmailService;
import cn.duanshaojie.utils.SendMailUtils;
import cn.duanshaojie.vo.CacheMailVO;
import freemarker.template.Template;

@Service
public class EmailServiceImpl implements EmailService {

	@Autowired
    private FreeMarkerConfigurer freeMarkerConfigurer;
	@Autowired
	private SendMailUtils send;
	@Value(value = "${dev.url}")
	private String domain;

	private final static Logger logger = LoggerFactory.getLogger(EmailServiceImpl.class);
	
    private ConcurrentMap cacheManager = new ConcurrentHashMap();
	@Override
	public void sendRegisterCode(String address) {
        try {
        	//生成验证码
        	CacheMailVO vo = new CacheMailVO();
        	vo.setTimestamp(System.currentTimeMillis());
            Random rand = new Random();
            int n = 100000+rand.nextInt(899999);
            vo.setVerifyCode(String.valueOf(n));
            cacheManager.put(address,vo);
			Template template = freeMarkerConfigurer.getConfiguration().getTemplate("register.html");
			 // FreeMarker通过Map传递动态数据
			String url = domain+"/user/verify?address="+address+"&code="+n;
	        Map<String, String> map = new HashMap<String, String>();   
	        map.put("username", address); 
	        map.put("url", url); 
	        String htmlText = FreeMarkerTemplateUtils.processTemplateIntoString(template, map); 
	        send.sendMali("激活账号",address, htmlText, null, null);
		} catch (Exception e) {
			logger.error("EmailServiceImpl---register send Email error",e);
		}    
        
	}

	@Override
	public boolean verifyCode(String address, String verifyCode) {
		String cacheKey = address;
        CacheMailVO vo = (CacheMailVO)cacheManager.get(cacheKey);
        if(vo == null) {
            return Boolean.FALSE;

        } else{
            Long timeStamp =  System.currentTimeMillis()-vo.getTimestamp();
            if (timeStamp > 300000) {
                cacheManager.remove(cacheKey);
                return Boolean.FALSE;
            }else if(vo.getVerifyCode().equals(verifyCode)) {
                return Boolean.TRUE;
            }else{
                return Boolean.FALSE;
            }
        }
	}

}
