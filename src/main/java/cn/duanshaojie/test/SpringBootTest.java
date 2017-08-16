package cn.duanshaojie.test;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import redis.clients.jedis.Jedis;

@RestController
public class SpringBootTest extends Thread{
	
	@RequestMapping("/index/{key}/{value}")
	@ResponseBody
	public String index(@PathVariable String key,@PathVariable String value) {
		Jedis jedis = new Jedis("localhost");
		System.out.println("Connection to server sucessfully"); 
	      //check whether server is running or not 
	    System.out.println("Server is running: "+jedis.ping()); 
	    String name = "";
	    if(jedis.get(key)==null){
	    	System.out.println("取 test-duanshaojie 为空");
		    jedis.set(key, value);
		    for(int i = 0;i<5;i++){
		    	try {
					sleep(1000);
					System.out.println("正在查询...");
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		    }
	    	name = jedis.get(key);
	    }else{
	    	System.out.println("redis直接返回!");
	    	name = jedis.get(key);
	    }
	    
		return "hello boy:" +name;
	}
}
