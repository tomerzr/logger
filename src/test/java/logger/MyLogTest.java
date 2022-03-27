package logger;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import utility.Convert;

import java.io.BufferedWriter;
import java.io.CharArrayWriter;
import java.io.FileWriter;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Map;

import static org.mockito.Matchers.*;
import static org.mockito.Mockito.times;
import static org.powermock.api.mockito.PowerMockito.doNothing;
import static org.powermock.api.mockito.PowerMockito.verifyPrivate;

@PrepareForTest(MyLog.class)
@RunWith(PowerMockRunner.class)
@PowerMockIgnore("jdk.internal.reflect.*")
public class MyLogTest {

    @Test
    public void testWriteToLogDefaultLevel() throws Exception {
        System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName() + new Timestamp(System.currentTimeMillis()));
        String[] arg = {"msg"};
        PowerMockito.spy(MyLog.class);
        doNothing().when(MyLog.class, "writeToLog", anyString(), anyString());
        MyLog.writeToLog(arg);
        verifyPrivate(MyLog.class, times(1)).invoke("writeToLog", anyString());
        verifyPrivate(MyLog.class, times(1)).invoke("writeToLog", anyString(), anyString());
//        PowerMockito.verifyStatic(Mockito.times(1));
//        MyLog.writeToLog(anyObject());
        System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName() + new Timestamp(System.currentTimeMillis()));
    }

    @Test
    public void testWriteToLogNewLevel() throws Exception {
        System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName() + new Timestamp(System.currentTimeMillis()));
        String[] arg = {"level", "msg"};
        PowerMockito.spy(MyLog.class);
        doNothing().when(MyLog.class, "writeToLog", anyString(), anyString());
        MyLog.writeToLog(arg);
        verifyPrivate(MyLog.class, times(1)).invoke("writeToLog", anyString(), anyString());
        verifyPrivate(MyLog.class, times(0)).invoke("writeToLog", anyString());
        System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName() + new Timestamp(System.currentTimeMillis()));
    }

    @Test
    public void testWriteToLogDefaultLevelPrint() throws Exception {
        System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName() + new Timestamp(System.currentTimeMillis()));
        String[] arg = {"msg"};
        FileWriter fileWriterMock = PowerMockito.mock(FileWriter.class);
        PowerMockito.whenNew(FileWriter.class).withArguments(anyString(), anyBoolean()).thenReturn(fileWriterMock);
        CharArrayWriter charArrayWriter = new CharArrayWriter();
        BufferedWriter bw = new BufferedWriter(charArrayWriter);
        PowerMockito.whenNew(BufferedWriter.class).withArguments(any(FileWriter.class)).thenReturn(bw);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        PowerMockito.field(MyLog.class, "dateFormat").set(MyLog.class, dateFormat);
        Map<String, String> levelAlias = Convert.stringToMap(":INFO,D:DEBUG,I:INFO,W:WARN,E:ERROR,F:FATAL", ",", ":");
        PowerMockito.field(MyLog.class, "levelAlias").set(MyLog.class, levelAlias);
        MyLog.writeToLog(arg);
        assert charArrayWriter.toString().contains("INFO msg");
        System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName() + new Timestamp(System.currentTimeMillis()));
    }

    @Test
    public void testWriteToLogAliasLevel() throws Exception {
        System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName() + new Timestamp(System.currentTimeMillis()));
        String[] arg = {"D", "msg"};
        FileWriter fileWriterMock = PowerMockito.mock(FileWriter.class);
        PowerMockito.whenNew(FileWriter.class).withArguments(anyString(), anyBoolean()).thenReturn(fileWriterMock);
        CharArrayWriter charArrayWriter = new CharArrayWriter();
        BufferedWriter bw = new BufferedWriter(charArrayWriter);
        PowerMockito.whenNew(BufferedWriter.class).withArguments(any(FileWriter.class)).thenReturn(bw);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        PowerMockito.field(MyLog.class, "dateFormat").set(MyLog.class, dateFormat);
        Map<String, String> levelAlias = Convert.stringToMap(":INFO,D:DEBUG,I:INFO,W:WARN,E:ERROR,F:FATAL", ",", ":");
        PowerMockito.field(MyLog.class, "levelAlias").set(MyLog.class, levelAlias);
        MyLog.writeToLog(arg);
        assert charArrayWriter.toString().contains("DEBUG msg");
        System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName() + new Timestamp(System.currentTimeMillis()));
    }

    @Test
    public void testWriteToLogNewLevelPrint() throws Exception {
        System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName() + new Timestamp(System.currentTimeMillis()));
        String[] arg = {"level", "msg"};
        FileWriter fileWriterMock = PowerMockito.mock(FileWriter.class);
        PowerMockito.whenNew(FileWriter.class).withArguments(anyString(), anyBoolean()).thenReturn(fileWriterMock);
        CharArrayWriter charArrayWriter = new CharArrayWriter();
        BufferedWriter bw = new BufferedWriter(charArrayWriter);
        PowerMockito.whenNew(BufferedWriter.class).withArguments(any(FileWriter.class)).thenReturn(bw);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        PowerMockito.field(MyLog.class, "dateFormat").set(MyLog.class, dateFormat);
        Map<String, String> levelAlias = Convert.stringToMap(":INFO,D:DEBUG,I:INFO,W:WARN,E:ERROR,F:FATAL", ",", ":");
        PowerMockito.field(MyLog.class, "levelAlias").set(MyLog.class, levelAlias);
        MyLog.writeToLog(arg);
        assert charArrayWriter.toString().contains("level msg");
        System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName() + new Timestamp(System.currentTimeMillis()));
    }
}
