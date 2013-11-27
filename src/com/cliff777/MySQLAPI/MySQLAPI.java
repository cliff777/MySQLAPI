package com.cliff777.MySQLAPI;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;


public class MySQLAPI {
	private Connection conn;

	public MySQLAPI(String ip, int port, String database, String user, String pass) {
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			String address = "jdbc:mysql://" + ip + ":" + port + "/" + database + "?user=" + user + "&password=" + pass;
			conn = DriverManager.getConnection(address);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	public Results read(String sql, Object...args) {
		try {

			PreparedStatement statement = conn.prepareStatement(sql);


			for(int i = 0; i < args.length; i++) {
				statement.setObject(i, args[i]);
			}

			ResultSet set = statement.executeQuery();

			Results results = new Results();

			while(set.next()) {
				HashMap<String, Object> row = new HashMap<String, Object>();

				for(int i = 1; i <= set.getMetaData().getColumnCount(); i++) {
					row.put(set.getMetaData().getColumnName(i), set.getObject(i));
				}

				results.addRow(row);

			}
			statement.close();
			set.close();

			return results;
		}catch(SQLException e) {
			e.printStackTrace();
			return new Results();
		}
	}

	public boolean write(String sql, Object...args) {
		try {
			PreparedStatement statement = conn.prepareStatement(sql);


			for(int i = 0; i < args.length; i++) {
				statement.setObject(i, args[i]);
			}

			statement.execute();

			return true;
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}
