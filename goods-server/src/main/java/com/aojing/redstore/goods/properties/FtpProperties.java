package com.aojing.redstore.goods.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @author gexiao
 * @date 2018/12/4 17:32
 */
@Configuration
@ConfigurationProperties(prefix = "ftp")
@Getter
@Setter
@Component
@PropertySource("classpath:goods-common.yml")
public class FtpProperties {

   private String server_ip;
   private String user;
   private String pass;
   private String server_http_prefix;
}
