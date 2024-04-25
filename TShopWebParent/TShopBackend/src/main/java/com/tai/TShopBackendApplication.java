package com.tai;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan({"com.tai.common.entity", "com.tai.admin.user"})
public class TShopBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(TShopBackendApplication.class, args);
    }

}
