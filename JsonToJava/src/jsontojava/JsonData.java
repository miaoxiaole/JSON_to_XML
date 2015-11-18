/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jsontojava;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 *
 * @author Administrator
 */
public class JsonData {
   // static Integer size;
    Url urld = new Url();
    //Item item;
    void fromJson(Scene scene ,String path,int sceneNum,int unitNum){
        String JsonContext = new Util().ReadFile("D:\\download\\test.json");
        JSONObject jsonObject = JSONObject.fromObject(JsonContext);
        JSONArray dialogs = jsonObject.getJSONArray("dialogs");
        int size = dialogs.size();
        System.out.println("Size: " + size);
     for(int  i = 0; i < size; i++){
        Item item;
        item = new Item();
        String copy_path;
        copy_path=path+unitNum+"-"+sceneNum+"-"+(i+1)+".mp3";
        JSONObject items = dialogs.getJSONObject(i);
        String natives = items.getString("native");
        String cn =items.getString("cn");
        item.setDisplay(natives+"\n"+cn);
        item.setRecord(natives+"\n"+cn);
        String audio_code =items.getString("audio_code"); 
        urld.httpDownload(audio_code,copy_path);    //通过这种方式可以给一个scene里面的所有mp3文件不重复命名，但是我要的是把一本书都放在一个文件夹里面，怎么也要三级命名
        scene.addItem(item);
        System.out.println("natives[" + i + "]" + natives);
        System.out.println("cn[" + i + "]" + cn);
        System.out.println("audio_code[" + i + "]" + audio_code);
        System.out.println("display[" + i + "]" +item.getDisplay());
        System.out.println();
    }
        
    }
    
}
