package com.qzuyiban.pan;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@MapperScan(value="com.qzuyiban.pan.dao")
@SpringBootApplication 
@EnableCaching
@EnableTransactionManagement
public class QzuyibanApplication {

	public static void main(String[] args) {
		SpringApplication.run(QzuyibanApplication.class, args);
	}
}
