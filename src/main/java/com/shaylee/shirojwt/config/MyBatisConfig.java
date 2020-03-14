package com.shaylee.shirojwt.config;

import org.springframework.context.annotation.Configuration;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * Title: Mybatis配置
 * Project: shaylee-common
 *
 * @author Adrian
 * @date 2020-03-09
 */
@Configuration
@MapperScan(value = "com.shaylee.**.dao")
public class MyBatisConfig {

}
