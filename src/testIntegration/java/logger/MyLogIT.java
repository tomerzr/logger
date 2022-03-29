package logger;

import org.junit.Test;

import java.io.*;
import java.sql.Timestamp;

public class MyLogIT {

    @Test
    public void oneLineDefault() throws IOException, InterruptedException {
        System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName() + new Timestamp(System.currentTimeMillis()));
        new File(System.getProperty("user.dir")+ File.separator+"myLogFile.txt").delete();
        execCommand("java -cp \"target"+File.separator+"*\"  logger.MyLog aaa");
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
        execCommand("java -cp \""+System.getProperty("user.dir")+ File.separator+"target"+File.separator+"*\"  logger.MyLog INFO aaa");
        execCommand("java -cp \""+System.getProperty("user.dir")+ File.separator+"target"+File.separator+"*\"  logger.MyLog INFO bbb");
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
        execCommand("java -cp \""+System.getProperty("user.dir")+ File.separator+"target"+File.separator+"logger-1.0-SNAPSHOT.jar\"  logger.MyLog e aaa");
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
        execCommand("java -cp \"target"+File.separator+"*\"  logger.MyLog E aaa");
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
        execCommand("java -cp \"target"+File.separator+"*\"  logger.MyLog abc aaa");
        try (BufferedReader br = new BufferedReader(new FileReader(System.getProperty("user.dir")+ File.separator+"myLogFile.txt"))) {
            assert br.readLine().contains("abc aaa");
            assert br.readLine() == null;
        }
        System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName() + new Timestamp(System.currentTimeMillis()));
    }

    private void execCommand(String command) throws IOException, InterruptedException {
        System.out.println("execCommand"+command);
        Process process = Runtime.getRuntime().exec(command);
        process.waitFor();
        BufferedReader stdInput = new BufferedReader(new InputStreamReader(process.getInputStream()));
        BufferedReader stdError = new BufferedReader(new InputStreamReader(process.getErrorStream()));
        String s = null;
        System.out.println("stdInput");
        while ((s = stdInput.readLine()) != null) {
            System.out.println(s);
        }
        System.out.println("stdError");
        while ((s = stdError.readLine()) != null) {
            System.out.println(s);
        }
    }
}
