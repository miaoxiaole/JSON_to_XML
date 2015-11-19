/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jsontojava;

import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

/**
 *
 * @author Administrator
 */
public class Main {
     public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
       int i;
       Data data = new Data(); //创建Data对象让data可以收集units
       DataResults dr = new DataResults();
       for(i=12;i<17;i++){
          dr.setAttributes("D:\\Download\\分类对话\\"+(i+1)+"\\",data,i+1); 
       }
     dr.getAttributes(data);
     }
    
}
