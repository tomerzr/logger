package logger;

import org.junit.Test;

import java.io.*;
import java.sql.Timestamp;

public class MyLogIT {

    @Test
    public void oneLineDefault() throws IOException, InterruptedException {
        System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName() + new Timestamp(System.currentTimeMillis()));
        new File(System.getProperty("user.dir")+ File.separator+"myLogFile.txt").delete();
        Process process = Runtime.getRuntime().exec("java -cp \"target"+File.separator+"*\"  logger.MyLog aaa");
        process.waitFor();
        try (BufferedReader br = new BufferedReader(new FileReader(System.getProperty("user.dir")+ File.separator+"myLogFile.txt"))) {
            assert br.readLine().contains("INFO aaa");
            assert br.readLine() == null;
        }
        System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName() + new Timestamp(System.currentTimeMillis()));
    }

    @Test
    public void twoLines() throws IOException, InterruptedException {
        System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName() + new Timestamp(System.currentTimeMillis()));
        System.out.println(System.getProperty("user.dir")+ File.separator+"myLogFile.txt");
        new File(System.getProperty("user.dir")+ File.separator+"myLogFile.txt").delete();
        System.out.println("java -cp "+System.getProperty("user.dir")+ File.separator+"\"target"+File.separator+"*\"  logger.MyLog INFO aaa");

        Process process = Runtime.getRuntime().exec("java -cp "+System.getProperty("user.dir")+ File.separator+"\"target"+File.separator+"*\"  logger.MyLog INFO aaa");
        process.waitFor();
        BufferedReader stdInput = new BufferedReader(new InputStreamReader(process.getInputStream()));
        BufferedReader stdError = new BufferedReader(new InputStreamReader(process.getErrorStream()));
        System.out.println("Here is the standard output of the command:\n");
        String s = null;
        while ((s = stdInput.readLine()) != null) {
            System.out.println(s);
        }
        while ((s = stdError.readLine()) != null) {
            System.out.println(s);
        }
        process = Runtime.getRuntime().exec("java -cp \"target"+File.separator+"*\"  logger.MyLog INFO bbb");
        process.waitFor();
        try (BufferedReader br = new BufferedReader(new FileReader(System.getProperty("user.dir")+ File.separator+"myLogFile.txt"))) {
            assert br.readLine().contains("INFO aaa");
            assert br.readLine().contains("INFO bbb");
            assert br.readLine() == null;
        }
        System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName() + new Timestamp(System.currentTimeMillis()));
    }

    @Test
    public void oneLineAlias_e() throws IOException, InterruptedException {
        System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName() + new Timestamp(System.currentTimeMillis()));
        new File(System.getProperty("user.dir")+ File.separator+"myLogFile.txt").delete();
        Process process = Runtime.getRuntime().exec("java -cp \"target"+File.separator+"*\"  logger.MyLog e aaa");
        process.waitFor();
        try (BufferedReader br = new BufferedReader(new FileReader(System.getProperty("user.dir")+ File.separator+"myLogFile.txt"))) {
            assert br.readLine().contains("ERROR aaa");
            assert br.readLine() == null;
        }
        System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName() + new Timestamp(System.currentTimeMillis()));
    }

    @Test
    public void oneLineAlias_E() throws IOException, InterruptedException {
        System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName() + new Timestamp(System.currentTimeMillis()));
        new File(System.getProperty("user.dir")+ File.separator+"myLogFile.txt").delete();
        Process process = Runtime.getRuntime().exec("java -cp \"target"+File.separator+"*\"  logger.MyLog E aaa");
        process.waitFor();
        try (BufferedReader br = new BufferedReader(new FileReader(System.getProperty("user.dir")+ File.separator+"myLogFile.txt"))) {
            assert br.readLine().contains("ERROR aaa");
            assert br.readLine() == null;
        }
        System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName() + new Timestamp(System.currentTimeMillis()));
    }

    @Test
    public void oneLineNewLevel() throws IOException, InterruptedException {
        System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName() + new Timestamp(System.currentTimeMillis()));
        new File(System.getProperty("user.dir")+ File.separator+"myLogFile.txt").delete();
        Process process = Runtime.getRuntime().exec("java -cp \"target"+File.separator+"*\"  logger.MyLog abc aaa");
        process.waitFor();
        try (BufferedReader br = new BufferedReader(new FileReader(System.getProperty("user.dir")+ File.separator+"myLogFile.txt"))) {
            assert br.readLine().contains("abc aaa");
            assert br.readLine() == null;
        }
        System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName() + new Timestamp(System.currentTimeMillis()));
    }
}
