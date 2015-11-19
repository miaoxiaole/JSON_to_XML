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
     JsonData jd;
     String p1;
    static String p2="D:\\huyu\\Book4\\";     //路径的写法;是收集数据的路径
      
      public void setAttributes(String path,Data data,int num) throws ParserConfigurationException, SAXException, IOException{
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document document = db.parse(new File(path+"map.xml"));   //得到源XML文件
        // 根据标签名访问节点
        NodeList list = document.getElementsByTagName("scene");
        System.out.println("list length: "+ list.getLength());
        //来匹配Unit的值
       
        //设置unit的属性：
        unit = new Unit();
        unit.setIndex(num);   //num是用来改变每次unit 的index 值
        unit.setPage(null);   
        unit.setTitle("");      //暂时没有cover，而且全设置成常量是想先验证看
        unit.setCover("");
        Integer n =1;
        // 遍历每一个节点
        for (int i = 0; i < list.getLength(); ++i)
        {
            jd = new JsonData();
            scene = new Scene();
            scene.setIndex(i+1);
            System.out.println("----------------------");
            final Node node = list.item(i);
            //存储name属性
           //final String value00 = node.getAttributes().getNamedItem("name").getNodeValue(); 
            final String value =node.getTextContent();
            System.out.println(value); 
            scene.setTitle(value);    //就是常用词汇的
          
            scene.setCover("");
            jd.fromJson(scene,p2,path+value+"\\"+value+"_0.json",num,i+1);   //将每一个item添加到scene中
             //n =scene.getItems().size();
            Integer m =scene.getItems().size()+n;
            Integer t=m-1;
            scene.setPage(n.toString()+"-"+t.toString());
            n=m;
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
