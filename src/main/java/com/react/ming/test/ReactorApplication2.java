package com.react.ming.test;

import com.react.ming.part2.util.Log;
import com.react.ming.test.src.dto.MemberDto;
import com.react.ming.test.src.service.MemberService;
import java.time.LocalTime;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.cursor.Cursor;
import org.apache.ibatis.session.ExecutorType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import reactor.core.publisher.Flux;

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


            service.selectMember(2L);

            log.info("==== 작업 종료 ====");
        };
    }
}
