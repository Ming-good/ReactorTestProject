package com.react.ming.part2.chapter7;

import com.react.ming.part2.chapter6.server.SpringReactiveApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

public class ServerLauncher {

    public static void main(String[] args) {
        ConfigurableApplicationContext run = new SpringApplicationBuilder(SpringReactiveApplication.class)
                .properties(
                        "spring.application.name=branch-office",
                        "server.port=7070",
//                        "spring.main.web-application-type=reactive",
                        "spring.main.web-application-type=servlet",
                        "management.server.port=9081",
                        "logging.file.name=branch.log"
                ).run(args);

        Runtime.getRuntime().addShutdownHook(new Thread(
                () -> {
                    try {
                        run.close();
                    } catch (Exception e) {
                    }
                }
        ));

    }
}
