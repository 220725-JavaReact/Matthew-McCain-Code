package services.util;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class Logger {

    private static Logger logger = new Logger();

    private Logger() {}

    public static Logger getLogger() {
        return logger;
    }
    private void writeToFile(String log)
    {
        String filePath = "./logs/" + LocalDate.now() + ".log";
        try (FileWriter writer = new FileWriter(filePath, true)){
            writer.append(log + "\n");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    public void log(LogLevel level, String message) {
        Log log = new Log(level, LocalDateTime.now(), message);
        writeToFile(log.toString());

    }
    public void log(LogLevel level, Exception ex) {
        Log log = new Log(level, LocalDateTime.now(), ex.toString());
        writeToFile(log.toString());

    }
    private class Log{
        LogLevel level;
        LocalDateTime timeStamp;
        String message;


        private Log(LogLevel level, LocalDateTime timeStamp, String message)
        {
            this.level = level;
            this.message = message;
            this.timeStamp = timeStamp;
        }


        @Override
        public String toString() {
            return level + "\t" + timeStamp + "\t" + message;
        }


    }

    public enum LogLevel{
        info, debug, verbose, warning, error, fatal
    }
}
