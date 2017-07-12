package com.zmk.cms.common.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;


public class ServiceUtil {
public String getCurrentWeather(){
    //百度天气API  
    String baiduUrl = "";  
    StringBuffer strBuf;  
        //心知天气 url=https://api.seniverse.com/v3/weather/now.json?key=ebfyyscheyqlqhkz&location=beijing&language=zh-Hans&unit=c
 baiduUrl = "https://api.seniverse.com/v3/weather/now.json?key=ebfyyscheyqlqhkz&location=beijing&language=zh-Hans&unit=c";                    
    strBuf = new StringBuffer();  
          
    try{  
        URL url = new URL(baiduUrl);  
        URLConnection conn =  url.openConnection();  
        BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream(),"utf-8"));//转码。  
        String line = null;  
        while ((line = reader.readLine()) != null)  
            strBuf.append(line + " ");  
            reader.close();  
    }catch(MalformedURLException e) {  
        e.printStackTrace();   
    }catch(IOException e){  
        e.printStackTrace();   
    }     
    return strBuf.toString();  
}
//public static void main(String args[]){
//    ServiceUtil  s= new ServiceUtil();
//    s.getCurrentWeather();
//    
//}

}
