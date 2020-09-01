package com.uz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;

@SpringBootApplication
public class ZadachaApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZadachaApplication.class, args);

        File dir = new File("C:\\Users\\Aslom\\proekt\\zadacha\\uploadFile");
        dir.mkdirs();

        System.out.println(dir);
    }

}
