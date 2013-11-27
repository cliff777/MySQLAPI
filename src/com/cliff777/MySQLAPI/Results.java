package com.cliff777.MySQLAPI;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.HashMap;

public class Results {
	ResultSetMetaData meta;
	ResultSet set;
	
	ArrayList<HashMap<String, Object>> raw = new ArrayList<HashMap<String, Object>>();
	
	public int rowCount() {
		return raw.size();
	}
	
	public void addRow(HashMap<String, Object> row) {
		this.raw.add(row);
	}
	
	public int getInteger(int row, String column) {
		return Integer.parseInt(String.valueOf(raw.get(row).get(column)));
	}
	
	public String getString(int row, String column) {
		return String.valueOf(raw.get(row).get(column));
	}
	
	public boolean hasRows() {
		return rowCount() != 0;
	}
	

}
