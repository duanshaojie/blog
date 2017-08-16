package cn.duanshaojie.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component("scheduledTest")
public class ScheduledTest {
	private static final Logger logger = LoggerFactory.getLogger(ScheduledTest.class);
	
//	@Scheduled(cron="0/1 * * * * ?")
//	public void executeTask2(){
//		Thread curren = Thread.currentThread();
//		System.out.println("定时任务:"+curren.getId());
//		logger.info("定时任务1:"+curren.getId()+",name"+curren.getName());
//	}
	
	public void executeTask(){
		Thread curren = Thread.currentThread();
		System.out.println("定时任务:"+curren.getId());
		logger.info("定时任务2:"+curren.getId()+",name"+curren.getName());
	}
}
