/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jsontojava;
import java.util.ArrayList;  
  
import java.util.Arrays;  
  
import java.util.HashSet;  
  
import java.util.List;  
  
import java.util.Set;  
/**
 *
 * @author Administrator
 */
public class Scene {
    public Integer index;
    public String pages;
    public String title;
    public String cover;
    public String page;
    public List<Item>  arrItem;
	
	private List<Item> items = new ArrayList<Item>();
	
	public Integer getIndex() {
		return index;
	}
	public void setIndex(Integer index) {
		this.index = index;
	}
	public String getPage() {
		return page;
	}
	public void setPage(String page) {
		this.page = page;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getCover() {
		return cover;
	}
	public void setCover(String cover) {
		this.cover = cover;
	}
	public List<Item> getItems() {
		return items;
	}
	public void addItem(Item item) {
		this.items.add(item);
	}
}

