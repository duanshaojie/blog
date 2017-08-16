package cn.duanshaojie.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;

/**
 * <b>类名：</b>QuartzConfig.java<br>
 * <p><b>标题：</b>定时任务配置文件</p>
 * <p><b>描述：</b>注入</p>
 * @author <font color='blue'>edison_dsj@163.com</font>
 * @date  2017-08-14 下午
 * 定时任务
 * 桃之夭夭,灼灼其华
 */

@Configuration
@ImportResource(locations = { "classpath:job/schedul.xml" })
public class QuartzConfig {

}
