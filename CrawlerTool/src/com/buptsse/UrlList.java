package com.buptsse;

import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class UrlList {
	
	public static ArrayList<String> parseList(String html){
		ArrayList<String> list = new ArrayList<>();
		Document doc;
		Elements els;
		try {
			doc = Jsoup.parse(html); //采用Jsoup库解析html
			els = doc.select("td.title_9"); //Jsoup里的选择器
			els = els.select("a[href]");
			for(int i=0;i<els.size();++i){
				String href = els.get(i).attr("href");
				System.out.println(href);
				list.add(href);
			}
			expandList(list);
			
			for(int i=0;i<list.size();++i){
				
				System.out.println(list.get(i));
				
			}
			
			return list;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}
	
	//添加省略的列表部分
	public static void expandList(ArrayList<String> list){
		for(int i=0;; ++i){
			if(i+1 == list.size()) break;
			System.out.println("i = "+ i +" size = " +list.size());
			String singleList = list.get(i);
			if(singleList.contains("?p=") && list.get(i+1).contains("?p=")){
				while( (Integer.parseInt(singleList.substring(singleList.indexOf("?p=")+3))+1) != Integer.parseInt(list.get(i+1).substring(list.get(i+1).indexOf("?p=")+3))){
					int pageNum = Integer.parseInt(list.get(i+1).substring(list.get(i+1).indexOf("?p=")+3))-1;
					list.add(i+1, list.get(i+1).replace(list.get(i+1).substring(list.get(i+1).indexOf("?p=")), "?p="+pageNum));
				}
			}
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// parseList() 是用来解析帖子列表页面，返回帖子地址列表
		String html = (new HttpClientTool("http://bbs.byr.cn/board/DigiLife?p=3")).getReString();
		parseList(html);
	}

}
