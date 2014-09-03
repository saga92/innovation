package com.buptsse;

import java.util.ArrayList;
import java.util.Date;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class InfoList {
	
	class A_U_Info{
		String a_u_name;
		String a_u_sex;
		String content;
		String a_u_img;
		String a_u_info;
		String a_u_uid;
		String a_u_replyTo;
		String a_u_heading;
		Date a_u_date;
		String a_u_ip;
	}//һ���򵥵��ڲ��࣬���ڴ洢��������Ҫ������
	
	public static ArrayList<A_U_Info> getInfoList(String html){
		Document doc;
		ArrayList<A_U_Info> au = new ArrayList<>();
		boolean tag = true;
		String sender = null;
		try {
			doc = Jsoup.parse(html); //����Jsoup�����html
			Elements divs = doc.select("table.article");//Jsoup���ѡ����
			
			for(int i=0; i < divs.size(); ++i){
				A_U_Info tempInfo = (new InfoList()).new A_U_Info(); //ʵ�������ڲ���Ķ���
				System.out.println(divs.get(i).select("td.a-content").text());//ѭ�������ӵ�body�������������̨�����ڹ۲�
				Message msg = new Message(divs.get(i).select("td.a-content").text());//��Message��������ӵ�body
				if(tag){//��¼������
	    			sender = msg.getMsgSender();
	    			tag = false;
	    			msg.setMsgReplyto(sender);
	    		}
	    		else{
	    			if(msg.getMsgReplyto()==""){
	    				msg.setMsgReplyto(sender); //����û�лظ���LZ������˶��ǻظ�LZ
	    			}
	    		}
				tempInfo.a_u_date = msg.getMsgDate();
				tempInfo.a_u_heading = msg.getMsgHeader();
				tempInfo.a_u_ip = msg.getMsgFromIP();
				tempInfo.a_u_replyTo = msg.getMsgReplyto();
				tempInfo.content = msg.getMsgDetail();
				
				tempInfo.a_u_name = PreProcessFM.newString(divs.get(i).select("span.a-u-name").text()); //Ԥ���������������
				tempInfo.a_u_sex = divs.get(i).select("span.a-u-sex").select("samp[title]").attr("title");
				
				tempInfo.a_u_img = divs.get(i).select("div.a-u-img").select("img[src]").attr("src");
				tempInfo.a_u_uid = PreProcessFM.newString(divs.get(i).select("div.a-u-uid").text());//Ԥ�����ǳ��������
				tempInfo.a_u_info = divs.get(i).select("dl.a-u-info").text();
				
				System.out.println(divs.get(i).select("span.a-u-name").text());
				System.out.println(divs.get(i).select("span.a-u-sex").select("samp[title]").attr("title"));
				System.out.println(divs.get(i).select("td.a-content").text());
				System.out.println("content parse : " +tempInfo.a_u_heading + tempInfo.a_u_date + tempInfo.a_u_replyTo + tempInfo.content + tempInfo.a_u_ip );
				System.out.println(divs.get(i).select("div.a-u-img").select("img[src]").attr("src"));
				System.out.println(divs.get(i).select("div.a-u-uid").text());
				System.out.println(divs.get(i).select("dl.a-u-info").text());
				
				au.add(tempInfo);				
			}
	
			return au;
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// ��̬���� getInfoList()�����ڽ�����ȡ����������ҳ�����ػظ��б�
		String html = (new HttpClientTool("http://bbs.byr.cn/article/Feeling/2279179")).getReString();
		getInfoList(html);
	}

}
