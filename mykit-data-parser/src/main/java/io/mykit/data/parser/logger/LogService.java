package io.mykit.data.parser.logger;

public interface LogService {

    void log(LogType logType);

    void log(LogType logType, String msg);

    void log(LogType logType, String format, Object... args);
}