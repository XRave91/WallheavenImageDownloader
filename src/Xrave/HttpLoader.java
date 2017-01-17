package Xrave;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;

/**
 * Created by 2 on 07.01.2017.
 */
public class HttpLoader implements Loader {
    @Override
    public BufferedInputStream getData(String url) {
        return getContent(url);
    }

    static String userAgent="Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.95 Safari/537.11";
    BufferedInputStream getContent(String url){
        URLConnection connection = null;
        try {
            URL link=new URL(url);
            connection = link.openConnection();
            connection.setRequestProperty("User-Agent",userAgent);//записать в константу
            connection.connect();
        } catch (Exception e) {
            System.out.println("Соединение не удалось. Проверьте соединение, и правильность УРЛ адреса");
            return null;
        }
        BufferedInputStream bis = null;
        try {
            bis = new BufferedInputStream(connection.getInputStream());
        } catch (IOException e) {
            return null;
        }
        if (bis ==null){
            return null;
        }
        return bis;
        }

//    void download_images(String[] urls,String Folder){//логичнее это перенести в класс парсинга
//        for (int i=0;i<urls.length;i++) {
//            System.out.println("\n"+(i+1)+"/"+fileId.size()+" "+String.join("_",parameters.tags)+"_"+fileId.get(i));
//            try {
//                if(!downloadFile(new URL("https://wallpapers.wallhaven.cc/wallpapers/full/wallhaven-"+fileId.get(i)+
//                        ".jpg"),new File("c:\\wallpapes\\"+String.join("_",parameters.tags)+"_"+fileId.get(i)+".jpg"))){
//                    try {
//                        Files.deleteIfExists(new File("c:\\wallpapes\\"+String.join("_",parameters.tags)+"_"+
//                                fileId.get(i)+".jpg").toPath());
//                    } catch (IOException e3) {
//                        System.out.println("file not deleted");
//                        e3.printStackTrace();
//                    }
//                    try {
//                        downloadFile(new URL("https://wallpapers.wallhaven.cc/wallpapers/full/wallhaven-"+
//                                fileId.get(i)+".png"),new File("c:\\wallpapes\\"+String.join("_",parameters.tags)+"_"+fileId.get(i)+".png"));
//                    } catch (MalformedURLException e) {
//                        e.printStackTrace();
//                    }
//                    System.out.println(i+1+"/"+fileId.size()+" "+String.join("_",parameters.tags)+"_"+fileId.get(i)+".png downloaded");
//                    continue;
//                }
//            } catch (MalformedURLException e) {
//                e.printStackTrace();
//            }
//            System.out.println(i+1+"/"+fileId.size()+" "+String.join("_",parameters.tags)+"_"+fileId.get(i)+".jpg downloaded");
//        }
//    }
}
