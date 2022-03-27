package logger;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Timestamp;

public class MyLogIT {

    @Test
    public void oneLineDefault() throws IOException, InterruptedException {
        System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName() + new Timestamp(System.currentTimeMillis()));
        new File("myLogFile.txt").delete();
        Process process = Runtime.getRuntime().exec("java -cp target\\*  logger.MyLog aaa");
        process.waitFor();
        try (BufferedReader br = new BufferedReader(new FileReader("myLogFile.txt"))) {
            assert br.readLine().contains("INFO aaa");
            assert br.readLine() == null;
        }
        System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName() + new Timestamp(System.currentTimeMillis()));
    }

    @Test
    public void twoLines() throws IOException, InterruptedException {
        System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName() + new Timestamp(System.currentTimeMillis()));
        new File("myLogFile.txt").delete();
        Process process = Runtime.getRuntime().exec("java -cp target\\*  logger.MyLog INFO aaa");
        process.waitFor();
        process = Runtime.getRuntime().exec("java -cp target\\*  logger.MyLog INFO bbb");
        process.waitFor();
        try (BufferedReader br = new BufferedReader(new FileReader("myLogFile.txt"))) {
            assert br.readLine().contains("INFO aaa");
            assert br.readLine().contains("INFO bbb");
            assert br.readLine() == null;
        }
        System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName() + new Timestamp(System.currentTimeMillis()));
    }

    @Test
    public void oneLineAlias_e() throws IOException, InterruptedException {
        System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName() + new Timestamp(System.currentTimeMillis()));
        new File("myLogFile.txt").delete();
        Process process = Runtime.getRuntime().exec("java -cp target\\*  logger.MyLog e aaa");
        process.waitFor();
        try (BufferedReader br = new BufferedReader(new FileReader("myLogFile.txt"))) {
            assert br.readLine().contains("ERROR aaa");
            assert br.readLine() == null;
        }
        System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName() + new Timestamp(System.currentTimeMillis()));
    }

    @Test
    public void oneLineAlias_E() throws IOException, InterruptedException {
        System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName() + new Timestamp(System.currentTimeMillis()));
        new File("myLogFile.txt").delete();
        Process process = Runtime.getRuntime().exec("java -cp target\\*  logger.MyLog E aaa");
        process.waitFor();
        try (BufferedReader br = new BufferedReader(new FileReader("myLogFile.txt"))) {
            assert br.readLine().contains("ERROR aaa");
            assert br.readLine() == null;
        }
        System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName() + new Timestamp(System.currentTimeMillis()));
    }

    @Test
    public void oneLineNewLevel() throws IOException, InterruptedException {
        System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName() + new Timestamp(System.currentTimeMillis()));
        new File("myLogFile.txt").delete();
        Process process = Runtime.getRuntime().exec("java -cp target\\*  logger.MyLog abc aaa");
        process.waitFor();
        try (BufferedReader br = new BufferedReader(new FileReader("myLogFile.txt"))) {
            assert br.readLine().contains("abc aaa");
            assert br.readLine() == null;
        }
        System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName() + new Timestamp(System.currentTimeMillis()));
    }
}
