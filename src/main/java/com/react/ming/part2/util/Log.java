package com.react.ming.part2.util;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.LoggerFactory;

@Slf4j
public class Log {
    static {
        Logger rootLogger = (Logger) LoggerFactory.getLogger(org.slf4j.Logger.ROOT_LOGGER_NAME);
        rootLogger.setLevel(Level.INFO);
    }

    public static void info(String str) {
        log.info(str);
    }

    public static void info(String str, Object... str2) {
        log.info(str, str2);
    }
}
