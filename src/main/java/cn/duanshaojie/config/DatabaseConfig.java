package cn.duanshaojie.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

/**
 * <b>类名：</b>DatabaseConfig.java<br>
 * <p><b>标题：</b>Database配置</p>
 * <p><b>描述：</b>注入xml文件</p>
 * @author <font color='blue'>edison_dsj@163.com</font>
 * @date  2017-08-14 下午
 * 
 * 桃之夭夭,灼灼其华
 */
@Configuration
@ImportResource(locations = "classpath:spring-beans.xml")
public class DatabaseConfig {

}
