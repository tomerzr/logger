package logger;

import org.junit.Test;

import java.io.*;
import java.sql.Timestamp;

public class MyLogIT {

    boolean isWindows = System.getProperty("os.name").toLowerCase().startsWith("windows");

    @Test
    public void oneLineDefault() throws IOException, InterruptedException {
        System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName() + new Timestamp(System.currentTimeMillis()));
        new File("logger" + File.separator + "myLogFile.txt").delete();
        execProcessBuilder("java -cp \"target" + File.separator + "logger-1.0-SNAPSHOT.jar\"  logger.MyLog aaa");
        try (BufferedReader br = new BufferedReader(new FileReader("logger" + File.separator + "myLogFile.txt"))) {
            assert br.readLine().contains("INFO aaa");
            assert br.readLine() == null;
        }
        System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName() + new Timestamp(System.currentTimeMillis()));
    }

    @Test
    public void twoLines() throws IOException, InterruptedException {
        System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName() + new Timestamp(System.currentTimeMillis()));
        new File("logger" + File.separator + "myLogFile.txt").delete();
        execProcessBuilder("java -cp \"target" + File.separator + "logger-1.0-SNAPSHOT.jar\"  logger.MyLog INFO aaa");
        execProcessBuilder("java -cp \"target" + File.separator + "logger-1.0-SNAPSHOT.jar\"  logger.MyLog INFO bbb");
        try (BufferedReader br = new BufferedReader(new FileReader("logger" + File.separator + "myLogFile.txt"))) {
            assert br.readLine().contains("INFO aaa");
            assert br.readLine().contains("INFO bbb");
            assert br.readLine() == null;
        }
        System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName() + new Timestamp(System.currentTimeMillis()));
    }

    @Test
    public void oneLineAlias_e() throws IOException, InterruptedException {
        System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName() + new Timestamp(System.currentTimeMillis()));
        new File("logger" + File.separator + "myLogFile.txt").delete();
        execProcessBuilder("java -cp \"target" + File.separator + "logger-1.0-SNAPSHOT.jar\"  logger.MyLog e aaa");
        try (BufferedReader br = new BufferedReader(new FileReader("logger" + File.separator + "myLogFile.txt"))) {
            assert br.readLine().contains("ERROR aaa");
            assert br.readLine() == null;
        }
        System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName() + new Timestamp(System.currentTimeMillis()));
    }

    @Test
    public void oneLineAlias_E() throws IOException, InterruptedException {
        System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName() + new Timestamp(System.currentTimeMillis()));
        new File("logger" + File.separator + "myLogFile.txt").delete();
        execProcessBuilder("java -cp \"target" + File.separator + "logger-1.0-SNAPSHOT.jar\"  logger.MyLog E aaa");
        try (BufferedReader br = new BufferedReader(new FileReader("logger" + File.separator + "myLogFile.txt"))) {
            assert br.readLine().contains("ERROR aaa");
            assert br.readLine() == null;
        }
        System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName() + new Timestamp(System.currentTimeMillis()));
    }

    @Test
    public void oneLineNewLevel() throws IOException, InterruptedException {
        System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName() + new Timestamp(System.currentTimeMillis()));
        new File("logger" + File.separator + "myLogFile.txt").delete();
        execProcessBuilder("java -cp \"target" + File.separator + "logger-1.0-SNAPSHOT.jar\"  logger.MyLog abc aaa");
        try (BufferedReader br = new BufferedReader(new FileReader("logger" + File.separator + "myLogFile.txt"))) {
            assert br.readLine().contains("abc aaa");
            assert br.readLine() == null;
        }
        System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName() + new Timestamp(System.currentTimeMillis()));
    }

    private void execProcessBuilder(String command) throws IOException, InterruptedException {
        System.out.println("execCommand" + command);
        ProcessBuilder builder = new ProcessBuilder();
        if (isWindows) {
            builder.command("cmd.exe", "/c", command);
        } else {
            builder.command("sh", "-c", command);
        }
        System.out.println("execCommand" + builder.command());
        Process process = builder.start();
        BufferedReader stdInput = new BufferedReader(new InputStreamReader(process.getInputStream()));
        BufferedReader stdError = new BufferedReader(new InputStreamReader(process.getErrorStream()));
        process.waitFor();
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
