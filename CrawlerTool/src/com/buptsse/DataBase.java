package com.buptsse;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;

public class DataBase {    //将文件夹中的所有文件导入一个数据库的表中

	public DataBase() {
		String driver = "com.mysql.jdbc.Driver";//database的driver
		String url = "jdbc:mysql://127.0.0.1:3306/movie";//数据库的地址
		String user = "root";// mysql的root账户名
		String password = "rjxy";//mysql的password
		try {			
			Class.forName(driver);//设定database的driver
			Connection conn = DriverManager.getConnection(url, user, password);  //与数据库建立连接
			if (!conn.isClosed()) {
				System.out.println("database connects successful...");
			}
			Statement statement = conn.createStatement();
			for (String s : getFromFile()) {      
				String[] tempRecord = s.split("╤");    //将txt文件中的数据以╤分隔开存入数组tempRecord
				ArrayList<String> record = new ArrayList<String>(Arrays.asList(tempRecord));//数据库的表中有11项，包括第一项帖子的ID（txt文件名中那一串数字）和
				if (record.size() < 11){                                                    //txt文件中的10项，若不够10项用空字符串补齐
					for(int i = record.size();i<11;i++){
						record.add("");
					}
				}
				for (int i = 0; i < record.size(); ++i) {           //将空字符串替换为NULL
					if (record.get(i).equals("")) {
						record.set(i, "NULL");
					} else {
						record.set(i, "\"" + record.get(i) + "\"");
					}
				}
				if(record.size() == 11){                 
					System.out.println("INSERT INTO movie VALUES (" + record.get(0)     //movie是表名，可改
							+ "," + record.get(1) + "," + record.get(2) + "," + record.get(3)
							+ "," + record.get(4) + "," + record.get(5) + "," + record.get(6)+","
									+ record.get(7) + "," + record.get(8)
											+ "," + record.get(9) + "," + record.get(10) + ")");
					statement.execute("INSERT INTO movie VALUES (" + record.get(0)     //SQL语句写入数据库的表中
							+ "," + record.get(1) + "," + record.get(2) + "," + record.get(3)
							+ "," + record.get(4) + "," + record.get(5) + "," + record.get(6)+","
									+ record.get(7) + "," + record.get(8)
											+ "," + record.get(9) + "," + record.get(10) + ")");
				}else{
					
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static ArrayList<String> getFromFile() {   //
		ArrayList<String> list = new ArrayList<String>();
		BufferedReader br;
		String temp,postID;
		int index1,index2;
		File file = new File("F:/CrawlerCon/");     //文件夹的绝对路径
		File[] file2 = file.listFiles();       //为文件夹的每个txt文件创建File实例，存入数组file2
		try {
			
			for (File f:file2){
			br = new BufferedReader(new InputStreamReader(new FileInputStream(f),"utf-8"));
			index1 = f.getAbsolutePath().lastIndexOf("#");    //从文件名获得帖子ID号
			index2 = f.getAbsolutePath().indexOf(".txt");
			postID = f.getAbsolutePath().substring(index1+1, index2);
			while ((temp = br.readLine()) != null) {      //读取txt每一行数据，并加入帖子ID
				temp = postID + "╤" + temp;
				list.add(temp);
			}
			br.close();
			}			
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new DataBase();

	}

}

