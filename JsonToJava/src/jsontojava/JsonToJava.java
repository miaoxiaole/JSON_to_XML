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
public class JsonToJava {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
    // TODO Auto-generated method stub
    String JsonContext = new Util().ReadFile("D:\\download\\apkinfo.json");
    JSONArray jsonArray = JSONArray.fromObject(JsonContext);
    int size = jsonArray.size();
    System.out.println("Size: " + size);
    for(int  i = 0; i < size; i++){
    JSONObject jsonObject = jsonArray.getJSONObject(i);
    System.out.println("[" + i + "]name=" + jsonObject.getString("name"));
    System.out.println("[" + i + "]package_name=" + jsonObject.get("package_name"));
    System.out.println("[" + i + "]check_version=" + jsonObject.get("check_version"));
    }
    }
    
}
