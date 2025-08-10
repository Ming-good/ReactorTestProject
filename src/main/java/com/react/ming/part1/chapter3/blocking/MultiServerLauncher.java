package com.react.ming.part1.chapter3.blocking;

import com.react.ming.part1.chapter3.blocking.branchOffice.SpringMvcBranchOfficeApplication;
import com.react.ming.part1.chapter3.blocking.headOffice.SpringMvcHeadOfficeApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

public class MultiServerLauncher {

    public static void main(String[] args) throws InterruptedException {
        ConfigurableApplicationContext branch = new SpringApplicationBuilder(SpringMvcBranchOfficeApplication.class)
                .properties(
                        "spring.application.name=branch-office",
                        "server.port=7070",
                        "spring.main.web-application-type=servlet",
                        "management.server.port=9081",
                        "logging.file.name=branch.log"
                ).run(args);

        Thread.sleep(2000);

        ConfigurableApplicationContext head = new SpringApplicationBuilder(SpringMvcHeadOfficeApplication.class).properties(
                "spring.application.name=head-office",
                "server.port=8080",
                // MVC면 servlet, WebFlux면 reactive 로 맞추기
                "spring.main.web-application-type=servlet",
                // 관리 포트도 분리하면 편함(선택)
                "management.server.port=9080",
                "logging.file.name=head.log"
        ).run(args);

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            try {
                head.close();
                branch.close();
            } catch (Exception e) {
            }
        }));
    }
}
