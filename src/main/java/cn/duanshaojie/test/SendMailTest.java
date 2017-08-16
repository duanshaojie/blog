package cn.duanshaojie.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import cn.duanshaojie.utils.SendMailUtils;

@RestController
public class SendMailTest {
	@Autowired
	private SendMailUtils send;
	
	@RequestMapping("send/{content}")
	@ResponseBody
	public String send(@PathVariable String content){
		try {
			send.sendMali("测试","edison_dsj@163.com", content, "C:/Users/dev/Desktop/template.xlsx", "C:/Users/dev/Desktop/kaxing.png");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "发送失败";
		}
		return "发送成功";
	}
}
