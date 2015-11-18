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
public class Data {
   // List<Unit> arrUnit;
    private List<Unit> units = new ArrayList<Unit>();
    public List<Unit> getUnits() {
		return units;
	}
	public void addUnit(Unit unit) {
		this.units.add(unit);
	}
}
