/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jsontojava;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 *
 * @author Administrator
 */
public class TestJson {
     public static void main(String[] args) {
    // TODO Auto-generated method stub
     JsonData jd = new JsonData();
     Item item;
     Scene scene =new Scene();
     //for(int j=0;j<20;j++){    //一个j循环就是一个一个scene
        
        jd.fromJson(scene, "D:\\Download\\new\\",1,1); 
        //String display =item.getDisplay();
        //String mp3 = item.getMp3();
        //System.out.println("wuyue   " +display);
        //System.out.println("wuyue   " + mp3);
    // }
     
    }
}
