/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jsontojava;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import org.xml.sax.SAXException;

/**
 *
 * @author Administrator
 */
public class DataResults {
     Unit unit;
      Scene scene;
      Item item;
      String p1;
      static String p2="D:\\workSpace\\Book4-2\\";     //路径的写法
      Map <String,String> map = new HashMap<String,String>();    //map的创建与使用；
      
          //这个k应该是一个传入参数，这样才能保证k值不同；
      public void setAttributes(String path,Data data,int num) throws ParserConfigurationException, SAXException, IOException{
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document document = db.parse(new File(path+"map.xml"));   //得到源XML文件
        // 根据标签名访问节点
        NodeList list = document.getElementsByTagName("item");
        System.out.println("list length: "+ list.getLength());
        //来匹配Unit的值
       // useMap(8);    //这个对应case
        
        
        p1=path;
        //dow.Download1(p1+"cover"+num+".png", p2+"cover"+num+".png");
        
        System.out.println(p1+"   "+p2);
       
        //设置unit的属性：
        unit = new Unit();
        unit.setIndex(num);   //num是用来改变每次unit 的index 值
        unit.setPage("2-5");   
        String mapKey = ""+num;
        System.out.println("吴月吴月吴月"+mapKey);
        unit.setTitle(map.get(mapKey));   //暂时没有cover，而且全设置成常量是想先验证看
        unit.setCover("cover"+num+".png");
        // 遍历每一个节点
        for (int i = 0; i < list.getLength(); ++i)
        {
            scene = new Scene();
            scene.setIndex(i+1);
            System.out.println("----------------------");
            final Node node = list.item(i);
            //存储name属性
            final String value00 = node.getAttributes().getNamedItem("name").getNodeValue(); 
            System.out.println(value00); 
            scene.setTitle(value00);
            //存储page属性
            final String value01 = node.getAttributes().getNamedItem("page").getNodeValue();  
            System.out.println(value01); 
            scene.setPage(value01);
            //存储url属性
            final String value02 = node.getAttributes().getNamedItem("url").getNodeValue();  //存储url属性
            System.out.println("url:   "); 
            //存储cover属性
            final String value03 = node.getAttributes().getNamedItem("cover").getNodeValue();  
            System.out.println(value03); 
            scene.setCover(value03);
            System.out.println(p1+value03+"   "+p2+value03);
            
           // dow.Download1(p1+value03, p2+value03);
            
            Document document1 = db.parse(new File(path+value02));   
            NodeList list1 = document1.getElementsByTagName("item");
   
            for (int j = 0; j < list1.getLength(); ++j)
          {
            item = new Item();
            item.setIndex(j+1);
            System.out.println("===========\n=========");
            final Node node1 = list1.item(j);
            //存储display属性
            final String value10 = node1.getAttributes().getNamedItem("display").getNodeValue();
            System.out.println(value10);
            item.setDisplay(value10);
          
            //存储record 属性
            final String value11 = node1.getAttributes().getNamedItem("record").getNodeValue();
            System.out.println(value11);
            item.setRecord(value11);
            //存储mp3属性，并将该MP3文件拷贝到盘里面
            final String value12 = node1.getAttributes().getNamedItem("mp3").getNodeValue();
            System.out.println(value12);
            item.setMp3(value12);
            //  dow.Download1(p1+value12, p2+value12);
            //将该item对象添加到scene的<item>类型的List中
            scene.addItem(item);      
          }
          unit.addScene(scene);
         
        }
         data.addUnit(unit);
}
     public void getAttributes(Data data1) throws ParserConfigurationException{
         //实例化解析器
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        //创建Document对象
        Document doc = builder.newDocument();

        //创建XML文件所需的各种对象并序列化
        Element book = doc.createElement("book");
        Element data = doc.createElement("data");
        for(Iterator i =  data1.getUnits().iterator(); i.hasNext();){
            Element unit = doc.createElement("unit");
            Unit unitValue =(Unit)i.next();
            for(Iterator j = unitValue.getScenes().iterator(); j.hasNext();){
                Element scene = doc.createElement("scene");   
                Scene sceneValue =(Scene)j.next();
                for(Iterator k = sceneValue.getItems().iterator(); k.hasNext();){
                     Item itemValue =(Item)k.next();
                     Element item = doc.createElement("item");
                     Element display = doc.createElement("display");
                     Element record = doc.createElement("record");
                     Element mp3 = doc.createElement("mp3");
                     Text displayText = doc.createTextNode(itemValue.getDisplay());
                     Text recordText = doc.createTextNode(itemValue.getRecord());
                     Text mp3Text = doc.createTextNode(itemValue.getMp3());
                     //先给item添加元素节点
                     item.appendChild(display);
                     item.appendChild(record);
                     item.appendChild(mp3);
                     //给各元素节点添加文本节点
                     display.appendChild(displayText);
                     record.appendChild(recordText);
                     mp3.appendChild(mp3Text);
                     //给item添加属性,本来是个integer类型的，要把它转换成字符串型的
                     item.setAttribute("index", itemValue.getIndex().toString());
                     //将每次循环创建的item元素节点加入到scene中
                     scene.appendChild(item);
                }
                //给scene添加属性；
                scene.setAttribute("index", sceneValue.getIndex().toString());
                scene.setAttribute("page",sceneValue.getPage());
                scene.setAttribute("title", sceneValue.getTitle());
                scene.setAttribute("cover", sceneValue.getCover());
                //将每次循环的scene加入到unit中去
                unit.appendChild(scene);
            }
            unit.setAttribute("index",unitValue.getIndex().toString());
            unit.setAttribute("page",unitValue.getPage());
            unit.setAttribute("title",unitValue.getTitle());
            unit.setAttribute("cover",unitValue.getCover());
            //将每次循环的unit加入到data元素节点中去
            data.appendChild(unit);
        }
       book.appendChild(data);
       doc.appendChild(book);
       doc2XmlFile(doc,"meta.xml");    //调用函数创建xml文件
       
       }
    public static boolean doc2XmlFile(Document document, String filename) {
            boolean flag = true;
            try {
            TransformerFactory tFactory = TransformerFactory.newInstance();
            Transformer transformer = tFactory.newTransformer();
            /** 编码 */
            // transformer.setOutputProperty(OutputKeys.ENCODING, "GB2312");
            DOMSource source = new DOMSource(document);
            StreamResult result = new StreamResult(new File(p2+filename));
            transformer.transform(source, result);
            } catch (Exception ex) {
            flag = false;
            ex.printStackTrace();
            }
            return flag;
            }
    
}
