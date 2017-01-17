package Xrave;

import java.io.BufferedInputStream;

/**
 * Created by 2 on 16.01.2017.
 */
public interface Saver {
    boolean saveFile(BufferedInputStream data, String filename);
}
