package com.react.ming.test;

import com.react.ming.test.src.dto.MemberDto;
import com.react.ming.test.src.service.MemberService;
import java.time.LocalTime;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@Slf4j
@SpringBootApplication
public class ReactorApplication2 {

    public static void main(String[] args) {
        SpringApplication.run(ReactorApplication2.class, args);
    }

    @Autowired
    MemberService service;

    @Bean
    public CommandLineRunner run() {
        return (String... args) -> {
            log.info("# 요청 시작 시간: {}", LocalTime.now());
            MemberDto memberDto = new MemberDto();
            memberDto.setUsername("lee");
            memberDto.setAge(11);
            service.insertMember(memberDto);

            MemberDto memberDto1 = service.selectMember(2L);
            log.info(memberDto1.toString());

            log.info("==== 작업 종료 ====");
        };
    }
}
