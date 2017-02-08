package Xrave;

import java.io.*;

/**
 * Created by 2 on 17.01.2017.
 */
public class FileSaver implements Saver{
    public boolean saveFile(BufferedInputStream data, String filename){
        if (data==null){
            return false;
        }
        File file = new File( filename);
        File folder = new File(file.getParent());
        if (!folder.exists()){
            folder.mkdirs();
        }
        BufferedOutputStream bos = null;
        try {
            bos = new BufferedOutputStream(new FileOutputStream(file));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        }
        int i;
        try {
            while ((i = data.read()) != -1) {
                bos.write(i);
            }
            bos.close();
        } catch (IOException e) {
            System.out.println("Cannot save file:"+ file.getName());
        }
        try {
            data.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }
}
