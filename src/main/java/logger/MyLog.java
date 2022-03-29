package logger;

import utility.Convert;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Map;

public class MyLog {

    private static final String space = " ";
    private static final String defaultLevelKey = "";
    private static String fileName;
    private static SimpleDateFormat dateFormat;
    private static Map<String, String> levelAlias;

    public static void main(String[] args) {
        System.out.println("main"+ System.getProperty("user.dir"));
        System.out.println("args"+Arrays.toString(args));
        setConfiguration();
        writeToLog(args);
    }

    private static void setConfiguration() {
       // System.out.println(System.getProperty("user.dir"));
       // fileName = System.getProperty("user.dir")+File.separator+"target" +File.separator+"myLogFile.txt";
//        fileName = System.getProperty("user.dir")+File.separator+"logger" +File.separator+"myLogFile.txt";
        fileName = "logger" +File.separator+"myLogFile.txt";
        System.out.println("setConfiguration"+fileName);

        File parent = new File(fileName).getParentFile();

        if (!parent.exists()){
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

    private static void writeToLog(String level, String msg)  {
        System.out.println("writeToLog");

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
//            System.out.println("writeToLog2");

//            try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
//
//                System.out.println("br"+ br.readLine());
//
//            }
//
//            File directoryPath = new File(System.getProperty("user.dir")+File.separator+"target" );
//            //List of all files and directories
//            String contents[] = directoryPath.list();
//            System.out.println("contents"+Arrays.toString(contents));

        } catch (IOException e) {
            System.out.println("IOException"+e);
            e.printStackTrace();
        }




    }

}
