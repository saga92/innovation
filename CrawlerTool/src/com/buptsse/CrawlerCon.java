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
				list.addAll(UrlList.parseList((new HttpClientTool( seedUrl + "?p=" + i)).getReString()));//������
			}else{
				list.addAll(UrlList.parseList((new HttpClientTool( seedUrl + "?ajax&p=" + i)).getReString()));//ˮľ
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
	
	
	/*<pre>�ѽ�����ϵ�����д���ļ���</pre>
	 * @param postid ���ӵ�ID
	 * @param record �������ݵĽṹ��
	 * @param filePath ���ӱ����·��
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
				//���ݿ�ĸ�ʽ��
				//���������֣��ǳƣ��Ա���Ϣ��ͷ�����ӱ��⣬����ʱ�䣬�ظ����ˣ��������ݣ�ip
				bw.write(re.a_u_name + "�h" + re.a_u_uid + "�h" + re.a_u_sex + 
						"�h" + re.a_u_info + "�h" +re.a_u_img + "�h" + 
						re.a_u_heading + "�h" + re.a_u_date + "�h" + 
						re.a_u_replyTo + "�h" + re.content + "�h" + re.a_u_ip);
				bw.newLine();
			}
			bw.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// CrawlerCon�ǿ���̨��java����һ����������������һ�������������ҳ�棬�ڶ������趨����Ҫ��ȡ��ҳ�������������趨����ȡ�������ı������·��
		new CrawlerCon("http://www.newsmth.net/nForum/board/CrossGate?ajax", 3, "E:\\lab\\crawlCon2\\",1);
	}

}
