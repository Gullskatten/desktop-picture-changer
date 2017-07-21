package runner;

import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.win32.W32APIOptions;

import java.io.File;

public class DesktopChanger {

    private static final String PICTURE_FOLDER = "C:\\picture_folder\\";
    private static final int CHANGE_PICTURE_RATE_MILLIS = 60000;
    public static final int LOOP_AMOUNT = 100;

    public interface User32 extends Library {
        User32 INSTANCE = (User32) Native.loadLibrary("user32",User32.class, W32APIOptions.DEFAULT_OPTIONS);
        boolean SystemParametersInfo (int one, int two, String s ,int three);
    }

    public static void main(String[] args) throws InterruptedException {
        int count = 0;

        File folder = new File(PICTURE_FOLDER);

        while(count < LOOP_AMOUNT) {
            for (final File fileEntry : folder.listFiles()) {
                if (!fileEntry.isDirectory()) {
                    User32.INSTANCE.SystemParametersInfo(0x0014, 0, fileEntry.getAbsolutePath() , 1);
                    Thread.sleep(CHANGE_PICTURE_RATE_MILLIS);
                }
            }
            count++;
        }
    }
}
