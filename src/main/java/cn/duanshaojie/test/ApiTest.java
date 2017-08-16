package cn.duanshaojie.test;

import org.junit.Test;

import cn.duanshaojie.enumerate.AcountStatusEnum;

public class ApiTest {
	@Test
	public void enumTest(){
		System.out.println(AcountStatusEnum.FROZEN.getIndex()+","+AcountStatusEnum.FROZEN.getName());
	}
}
