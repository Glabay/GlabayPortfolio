package xyz.glabaystudios.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

/**
 * @author Glabay
 * @project GlabayPortfolio
 * @social Discord: Glabay | Website: www.GlabayStudios.xyz
 * @since 2023-11-24
 */
@Configuration
public class Logger {

    @Value("${application.debug}")
    private boolean debugMode;

    private static Logger logger;

    private String loggingLevel = "INFO";

    public static Logger getLogger() {
        if (Objects.isNull(logger)) logger = new Logger();
        return logger;
    }

    public static Logger getLogger(String loggingLevel) {
        if (Objects.isNull(logger)) logger = new Logger();
        logger.loggingLevel = loggingLevel.toUpperCase();
        return logger;
    }

    public void printMessage(String message) {
        if (!debugMode) return;
        printWithTimestamp(message);
    }

    private void printWithTimestamp(String message) {
        var dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        var now = LocalDateTime.now();
        var printout = "[%s] [ %s ] %s%n".formatted(dtf.format(now), loggingLevel, message);
        System.out.printf(printout);
    }
}
