package com.react.ming.part1.chapter3.nonBlocking;

import com.react.ming.part1.chapter3.nonBlocking.branchOffice.SpringReactiveBranchOfficeApplication;
import com.react.ming.part1.chapter3.nonBlocking.headOffice.SpringReactiveHeadOfficeApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

public class MultiServerLauncher {

    public static void main(String[] args) throws InterruptedException {
        ConfigurableApplicationContext branch = new SpringApplicationBuilder(SpringReactiveBranchOfficeApplication.class)
                .properties(
                        "spring.application.name=branch-office",
                        "server.port=7070",
                        "spring.main.web-application-type=reactive",
//                        "spring.main.web-application-type=servlet",
                        "management.server.port=9081",
                        "logging.file.name=branch.log"
                ).run(args);
        System.out.println("APPLICATION TYPE: " + branch.getClass().getName());
        Thread.sleep(2000);

        ConfigurableApplicationContext head = new SpringApplicationBuilder(SpringReactiveHeadOfficeApplication.class).properties(
                "spring.application.name=head-office",
                "server.port=8080",
                // MVC면 servlet, WebFlux면 reactive 로 맞추기
                "spring.main.web-application-type=reactive",
                // 관리 포트도 분리하면 편함(선택)
//                "management.server.port=9080",
                "logging.file.name=head.log"
        ).run(args);

        Thread.sleep(7000);

        head.close();
        branch.close();

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            try {
                head.close();
                branch.close();
            } catch (Exception e) {
            }
        }));
    }
}
