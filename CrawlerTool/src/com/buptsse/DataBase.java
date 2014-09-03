package com.buptsse;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;

public class DataBase {    //���ļ����е������ļ�����һ�����ݿ�ı���

	public DataBase() {
		String driver = "com.mysql.jdbc.Driver";//database��driver
		String url = "jdbc:mysql://127.0.0.1:3306/movie";//���ݿ�ĵ�ַ
		String user = "root";// mysql��root�˻���
		String password = "rjxy";//mysql��password
		try {			
			Class.forName(driver);//�趨database��driver
			Connection conn = DriverManager.getConnection(url, user, password);  //�����ݿ⽨������
			if (!conn.isClosed()) {
				System.out.println("database connects successful...");
			}
			Statement statement = conn.createStatement();
			for (String s : getFromFile()) {      
				String[] tempRecord = s.split("�h");    //��txt�ļ��е������Ԩh�ָ�����������tempRecord
				ArrayList<String> record = new ArrayList<String>(Arrays.asList(tempRecord));//���ݿ�ı�����11�������һ�����ӵ�ID��txt�ļ�������һ�����֣���
				if (record.size() < 11){                                                    //txt�ļ��е�10�������10���ÿ��ַ�������
					for(int i = record.size();i<11;i++){
						record.add("");
					}
				}
				for (int i = 0; i < record.size(); ++i) {           //�����ַ����滻ΪNULL
					if (record.get(i).equals("")) {
						record.set(i, "NULL");
					} else {
						record.set(i, "\"" + record.get(i) + "\"");
					}
				}
				if(record.size() == 11){                 
					System.out.println("INSERT INTO movie VALUES (" + record.get(0)     //movie�Ǳ������ɸ�
							+ "," + record.get(1) + "," + record.get(2) + "," + record.get(3)
							+ "," + record.get(4) + "," + record.get(5) + "," + record.get(6)+","
									+ record.get(7) + "," + record.get(8)
											+ "," + record.get(9) + "," + record.get(10) + ")");
					statement.execute("INSERT INTO movie VALUES (" + record.get(0)     //SQL���д�����ݿ�ı���
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
		File file = new File("F:/CrawlerCon/");     //�ļ��еľ���·��
		File[] file2 = file.listFiles();       //Ϊ�ļ��е�ÿ��txt�ļ�����Fileʵ������������file2
		try {
			
			for (File f:file2){
			br = new BufferedReader(new InputStreamReader(new FileInputStream(f),"utf-8"));
			index1 = f.getAbsolutePath().lastIndexOf("#");    //���ļ����������ID��
			index2 = f.getAbsolutePath().indexOf(".txt");
			postID = f.getAbsolutePath().substring(index1+1, index2);
			while ((temp = br.readLine()) != null) {      //��ȡtxtÿһ�����ݣ�����������ID
				temp = postID + "�h" + temp;
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

