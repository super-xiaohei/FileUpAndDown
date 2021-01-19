package net.suncaper.fileprocess;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("net.suncaper.fileprocess.mapper")
public class FileprocessApplication {

    public static void main(String[] args) {
        SpringApplication.run(FileprocessApplication.class, args);
    }

}
