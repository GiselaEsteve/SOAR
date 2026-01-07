import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Logger {

    private static final DateTimeFormatter FMT = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

    public void info(String message) {
        System.out.println(format("INFO", message));
    }

    public void warn(String message) {
        System.out.println(format("WARN", message));
    }

    public void error(String message) {
        System.err.println(format("ERROR", message));
    }

    private String format(String level, String message) {
        return "[" + LocalDateTime.now().format(FMT) + "][" + level + "] " + message;
    }
}
