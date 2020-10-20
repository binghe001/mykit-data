package io.mykit.data.parser.logger;


import io.mykit.data.parser.flush.FlushService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LogServiceImpl implements LogService {

    @Autowired
    private FlushService flushService;

    @Override
    public void log(LogType logType) {
        flushService.asyncWrite(logType.getType(), logType.getMessage());
    }

    @Override
    public void log(LogType logType, String msg) {
        flushService.asyncWrite(logType.getType(), null == msg ? logType.getMessage() : msg);
    }

    @Override
    public void log(LogType logType, String format, Object... args) {
        flushService.asyncWrite(logType.getType(), String.format(format, args));
    }
}