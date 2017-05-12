package com.example.administrator.couldscreen4;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

/**
 * Created by Administrator on 2017/5/7.
 */

public class GetJson {
    public static String readStream(InputStream is) throws Exception{
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len = 0;
        while ((len = is.read(buffer))!=-1){
            baos.write(buffer, 0, len);
        }
        is.close();
        baos.close();
        String content = new String(baos.toByteArray(), "gbk");
        return content;
    }
}
