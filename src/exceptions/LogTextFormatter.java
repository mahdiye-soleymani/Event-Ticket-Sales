package exceptions;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Formatter;
import java.util.logging.LogRecord;

public class LogTextFormatter extends Formatter {
    private DateFormat dateFormat;
    private Date date = new Date();
    private static final String STRING_NEWLINE = System.getProperty("line.separator", "\n");

    public LogTextFormatter() {
        dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS");
    }

    @Override
    public String format(LogRecord record) {
        String msg = this.formatMessage(record);

        this.date.setTime(record.getMillis());
        return this.dateFormat.format(this.date) + " " + "[" + Thread.currentThread().getName() + "]" +
                record.getSourceClassName() + " " + record.getSourceMethodName() +
                " " + "[" + record.getLevel() + "]" + " " + msg + STRING_NEWLINE;

    }
}