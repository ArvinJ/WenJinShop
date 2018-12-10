package com.yandz.steels;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.yandz.steels.dao")
public class SteelShopApplication {

	public static void main(String[] args) {
		SpringApplication.run(SteelShopApplication.class, args);
	}
}
