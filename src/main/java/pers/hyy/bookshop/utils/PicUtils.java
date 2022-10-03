package pers.hyy.bookshop.utils;

import org.apache.commons.io.FileUtils;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

public class PicUtils {
    private static final String PrePath="src/main/resources/static/pic/";
    private static final String SuffixName=".jpg";
    public static String setPic(InputStream in,String username) throws IOException{
//        String ID=UUID.randomUUID().toString().replace("-","");
        File file=new File(PrePath+username+SuffixName);
        if(file.exists()){
            file.delete();
        }
        FileUtils.copyInputStreamToFile(in,file);
        return  username;
    }
}
