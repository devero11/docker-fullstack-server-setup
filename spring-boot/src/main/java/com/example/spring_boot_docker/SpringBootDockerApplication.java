
package com.example.spring_boot_docker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;



@SpringBootApplication
@RestController
public class SpringBootDockerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootDockerApplication.class, args);
    }

    @RequestMapping("/hello")
    public String hello(){
        System.out.println("Ping endpoint called!");
        return "hello";
    }
}

