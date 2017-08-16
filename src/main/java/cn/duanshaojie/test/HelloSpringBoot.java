package cn.duanshaojie.test;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloSpringBoot {

	@RequestMapping("hello/{name}")
	@ResponseBody
	public String helloSpringBoot(@PathVariable String name){
		return name+",helloSpringBoot";
	}
}
