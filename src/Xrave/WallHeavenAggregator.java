package Xrave;

import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.util.ArrayList;
import java.io.*;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by 2 on 07.01.2017.
 */
public class WallHeavenAggregator {
    Loader loader;
    Saver saver;
    WallHeavenAggregator(Loader ldr,Saver svr){
        loader=ldr;
        saver=svr;
    }
    void getImages(){
        return;
    }
    Pattern imagePattern=Pattern.compile("(class=\"preview\"[^0-9]*\\/wallpaper\\/)([0-9]+)");
    ArrayList parse(BufferedInputStream pageToParse, int imagesCount){
        ArrayList<String> listofindex=new ArrayList <String>();
        String content = null;
        Scanner sc=new Scanner(pageToParse);
        content =sc.useDelimiter("\\Z").next() ;
        Matcher m = imagePattern.matcher(content);
        while(m.find()&&imagesCount!=0){
            imagesCount--;
            listofindex.add(m.group(2));
        }
        sc.close();
        return listofindex;
    }
    ArrayList getImagesId(BufferedInputStream pageToParse, int imagesCount,String tags){
        int page=1;
        ArrayList<String> tmparraylist=null,fileId= new ArrayList<>(imagesCount);
        while(imagesCount>0){
            tmparraylist=parse(pageToParse,imagesCount);//"https://alpha.wallhaven.cc/search?q="+String.join("+",tags)+"&categories=111&page="+page,imagesCount);
            page++;
            imagesCount-=tmparraylist.size();
            if(tmparraylist==null){
                break;
            }
            fileId.addAll(tmparraylist);
        }
        return fileId;
    }

}
