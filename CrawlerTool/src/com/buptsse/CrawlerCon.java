package com.buptsse;

import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class CrawlerCon {
	
	public CrawlerCon(){
		
	}
	
	public CrawlerCon(String seedUrl, int page, String filePath,int type){
		ArrayList<String> list = new ArrayList<>();
		ArrayList<InfoList.A_U_Info> infoList=new ArrayList<>();
		for(int i=1; i<=page; ++i){
			if(type == 0){
				list.addAll(UrlList.parseList((new HttpClientTool( seedUrl + "?p=" + i)).getReString()));//北邮人
			}else{
				list.addAll(UrlList.parseList((new HttpClientTool( seedUrl + "?ajax&p=" + i)).getReString()));//水木
			}
		}
		
		for(String s:list){
			String postid = s;
			if(postid.contains("?p=")){
				postid = postid.substring(0, postid.indexOf("?p="));
			}
			postid = postid.replace("/", "#");
			if(type == 0){
				writeSummaryToFile(postid, InfoList.getInfoList((new HttpClientTool("http://bbs.byr.cn" + s)).getReString()), filePath);
			}else{
				writeSummaryToFile(postid, InfoList.getInfoList((new HttpClientTool("http://www.newsmth.net" + s)).getReString()), filePath);
			}
		}
	}
	
	
	/*<pre>把解析完毕的帖子写入文件中</pre>
	 * @param postid 帖子的ID
	 * @param record 帖子内容的结构体
	 * @param filePath 帖子保存的路径
	 * @throws Exception
	 *  
	 * */
	public static void writeSummaryToFile(String postid, ArrayList<InfoList.A_U_Info> record, String filePath) {
		BufferedWriter bw;
		File file;
		try {
			file=new File(filePath + postid  + ".txt");
			bw=new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file, true), "utf-8"));
			
			for( InfoList.A_U_Info re : record){
				//数据库的格式是
				//发帖人名字，昵称，性别，信息，头像，帖子标题，发布时间，回复的人，帖子内容，ip
				bw.write(re.a_u_name + "╤" + re.a_u_uid + "╤" + re.a_u_sex + 
						"╤" + re.a_u_info + "╤" +re.a_u_img + "╤" + 
						re.a_u_heading + "╤" + re.a_u_date + "╤" + 
						re.a_u_replyTo + "╤" + re.content + "╤" + re.a_u_ip);
				bw.newLine();
			}
			bw.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// CrawlerCon是控制台的java程序，一共有三个参数，第一个是爬虫的种子页面，第二个是设定爬虫要爬取的页数，第三个是设定的爬取下来的文本保存的路径
		new CrawlerCon("http://www.newsmth.net/nForum/board/CrossGate?ajax", 3, "E:\\lab\\crawlCon2\\",1);
	}

}
