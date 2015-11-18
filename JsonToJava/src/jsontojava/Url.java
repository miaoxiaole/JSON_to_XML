/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jsontojava;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 *
 * @author Administrator
 */
public class Url {
   public void httpDownload(String httpUrl,String saveFile){  
       // 下载网络文件  
       int bytesum = 0;  
       int byteread = 0;  
  
       URL url = null;  
    try {  
        url = new URL(httpUrl);  
    } catch (MalformedURLException e1) {  
        // TODO Auto-generated catch block  
        e1.printStackTrace();  
    }  
  
       try {  
           URLConnection conn = url.openConnection();  
           InputStream inStream = conn.getInputStream();  
           FileOutputStream fs = new FileOutputStream(saveFile);  
  
           byte[] buffer = new byte[1204];  
           while ((byteread = inStream.read(buffer)) != -1) {  
               bytesum += byteread;  
               System.out.println(bytesum);  
               fs.write(buffer, 0, byteread);  
           }  
       } catch (FileNotFoundException e) {  
           e.printStackTrace();  
         
       } catch (IOException e) {  
           e.printStackTrace();  
         
       }  
   }  
}
