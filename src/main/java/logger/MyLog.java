package logger;

import utility.Convert;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Map;

public class MyLog {

    private static final String space = " ";
    private static final String defaultLevelKey = "";
    private static String fileName;
    private static SimpleDateFormat dateFormat;
    private static Map<String, String> levelAlias;

    public static void main(String[] args) {
        setConfiguration();
        writeToLog(args);
    }

    private static void setConfiguration() {
        fileName = "logger" + File.separator + "myLogFile.txt";
        File parent = new File(fileName).getParentFile();
        if (!parent.exists()) {
            parent.mkdirs();
        }
        String dateFormatPattern = "yyyy-MM-dd HH:mm:ss.SSS";
        dateFormat = new SimpleDateFormat(dateFormatPattern);
        String mapLevelSting = ":INFO,D:DEBUG,I:INFO,W:WARN,E:ERROR,F:FATAL";
        levelAlias = Convert.stringToMap(mapLevelSting, ",", ":");
        String defaultLevel = "INFO";
        levelAlias.put(defaultLevelKey, defaultLevel);
    }

    public static void writeToLog(String[] args) {
        if (args.length == 1) {
            writeToLog(args[0]);
        } else if (args.length > 1) {
            writeToLog(args[0], args[1]);
        }
    }

    private static void writeToLog(String msg) {
        writeToLog(defaultLevelKey, msg);
    }

    private static void writeToLog(String level, String msg) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true));
            writer.write(dateFormat.format(System.currentTimeMillis()));
            writer.write(space);
            writer.write(levelAlias.getOrDefault(level.toUpperCase(), level));
            writer.write(space);
            writer.write(msg);
            writer.write(System.lineSeparator());
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
