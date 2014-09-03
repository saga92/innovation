//替换无效的标点符号为.；替换表情字符为具有情感倾向的词；
package com.buptsse;

import java.sql.Date;

public class PreProcessFM {
	private String laterString;
	PreProcessFM(String str){
        laterString = newString(str);
	}
	String getString(){
		return laterString;
	}
	public static String newString(String str){
        int index1,index2,i=0;
        String subString;       		
		char c='z';
		index1 = str.indexOf("[img");
		while(index1!=-1){
			index2 = str.indexOf("img]");
			subString = str.substring(index1,index2+4);
			str = toDelate(str, subString);
			index1 = str.indexOf("[img");
		}
		index1 = str.indexOf("iPhone客户端");
		while(index1!=-1){
			index2 = str.indexOf("发布", index1);
			subString = str.substring(index1,index2+2);
			str = toDelate(str, subString);
			index1 = str.indexOf("iPhone客户端");
		}
		index1 = str.indexOf("http");
		while(index1!=-1){
			index2 = str.indexOf(" ", index1);
			if(index2!=-1){
			subString = str.substring(index1,index2);
			str = toDelate(str, subString);
			index1 = str.indexOf("http");}
			else{
				break;
			}
		}
		index1=str.indexOf("[em");
		while(index1!=-1){
			while(c!=']'){
				i++;
				c = str.charAt(index1+i);
			}
			index2 = index1+i;
            if(index1!=-1&&index2!=-1){
            	subString = str.substring(index1,index2+1);
            	str = str.substring(0, index1)+toChange(subString)+str.substring(index2+1);
            }
			index1=str.indexOf("[em");
		}
		
		
		index1 = toPoint(str);
		while(index1!=-1){
			str = str.substring(0,index1)+"."+str.substring(index1+1);
			index1 = toPoint(str);
		}
		
		return str;
	}
	public static int toPoint(String str){
		int index;
		index = str.indexOf(',');
		if(index==-1)
			index = str.indexOf('\\');
		if(index==-1)
			index = str.indexOf('「');
		if(index==-1)
			index = str.indexOf('」');
		if(index==-1)
			index = str.indexOf('/');
		if(index==-1)
			index = str.indexOf('、');
		if(index==-1)
			index = str.indexOf('|');
		if(index==-1)
			index = str.indexOf('&');
		if(index==-1)
			index = str.indexOf('=');
		if(index==-1)
			index = str.indexOf('，');
		if(index==-1)
			index = str.indexOf('。');
		if(index==-1)
			index = str.indexOf('。');
		if(index==-1)
			index = str.indexOf('？');
		if(index==-1)
			index = str.indexOf('！');
		if(index==-1)
			index = str.indexOf('!');
		if(index==-1)
			index = str.indexOf('?');
		if(index==-1)
			index = str.indexOf('-');
		if(index==-1)
			index = str.indexOf('_');
		if(index==-1)
			index = str.indexOf('-');
		if(index==-1)
			index = str.indexOf('~');
		if(index==-1)
			index = str.indexOf('～');
		if(index==-1)
			index = str.indexOf('―');
		if(index==-1)
			index = str.indexOf('“');
		if(index==-1)
			index = str.indexOf('”');
		if(index==-1)
			index = str.indexOf('‘');
		if(index==-1)
			index = str.indexOf('’');
		if(index==-1)
			index = str.indexOf('"');
		if(index==-1)
			index = str.indexOf('《');
		if(index==-1)
			index = str.indexOf('》');
		if(index==-1)
			index = str.indexOf('【');
		if(index==-1)
			index = str.indexOf('】');
		if(index==-1)
			index = str.indexOf('[');
		if(index==-1)
			index = str.indexOf(']');
		if(index==-1)
			index = str.indexOf('<');
		if(index==-1)
			index = str.indexOf('>');
		if(index==-1)
			index = str.indexOf(':');
		if(index==-1)
			index = str.indexOf(';');
		if(index==-1)
			index = str.indexOf('：');
		if(index==-1)
			index = str.indexOf('；');
		if(index==-1)
			index = str.indexOf('（');
		if(index==-1)
			index = str.indexOf('）');
		if(index==-1)
			index = str.indexOf('(');
		if(index==-1)
			index = str.indexOf(')');
		return index;
	}
	public static String toChange(String str){
		int index1,index2;
		String sub;
		index1 = 3;
		index2 = str.length()-1;
		sub = str.substring(index1,index2);
		if(sub.equals("1")||sub.equals("2")||sub.equals("7")||sub.equals("8")||sub.equals("9")||sub.equals("10")||sub.equals("11")||sub.equals("13")||sub.equals("14")||sub.equals("19")||sub.equals("24")||sub.equals("42")
				||sub.equals("57")||sub.equals("56")||sub.equals("73")||sub.equals("70")||sub.equals("69")||sub.equals("67")||sub.equals("72")||sub.equals("71")||sub.equals("a38")||sub.equals("a34")||sub.equals("a16")||sub.equals("a7")
				||sub.equals("a1")||sub.equals("a2")||sub.equals("b23")||sub.equals("b19")||sub.equals("b15")||sub.equals("b11")||sub.equals("b9")||sub.equals("c55")||sub.equals("c58")||sub.equals("c57")||sub.equals("c47")||sub.equals("c46")
				||sub.equals("c42")||sub.equals("c40")||sub.equals("c39")||sub.equals("c36")||sub.equals("c33")||sub.equals("c33")||sub.equals("c31")||sub.equals("c29")||sub.equals("c25")||sub.equals("c24")||sub.equals("c23")
				||sub.equals("c22")||sub.equals("c21")||sub.equals("c15")||sub.equals("c14")||sub.equals("c12")||sub.equals("c10")||sub.equals("c6")||sub.equals("c4")||sub.equals("c2")||sub.equals("c0"))
			return "垃圾";
		else if(sub.equals("12")||sub.equals("18")||sub.equals("22")||sub.equals("21")||sub.equals("20")||sub.equals("68")||sub.equals("a40")||sub.equals("a39")||sub.equals("a36")||sub.equals("a33")||sub.equals("a32")
				||sub.equals("a31")||sub.equals("a29")||sub.equals("a28")||sub.equals("a25")||sub.equals("a21")||sub.equals("a18")||sub.equals("a20")||sub.equals("a12")||sub.equals("a15")||sub.equals("a11")||sub.equals("a9")||sub.equals("a4")||sub.equals("a3")||sub.equals("b24")
				||sub.equals("b22")||sub.equals("b21")||sub.equals("b20")||sub.equals("b17")||sub.equals("b12")||sub.equals("b5")||sub.equals("b0")||sub.equals("c51")||sub.equals("c50")||sub.equals("c49")||sub.equals("c41")||sub.equals("c35")||sub.equals("c34")||sub.equals("c32")||sub.equals("c27")||sub.equals("c17")
				||sub.equals("c8")||sub.equals("c7")||sub.equals("c5")||sub.equals("c3")||sub.equals("c1"))
			return "赞";
		else {
			return "";
		}
	}
	public static String toDelate(String str,String substr){
    	String str1;
    	while(str.indexOf(substr)!=-1){
    		str1 = str.substring(0, str.indexOf(substr));
    		str = str1+str.substring(str.indexOf(substr)+substr.length());
    	}
        if(str.startsWith(substr)){
        	str = str.substring(substr.length());
        }
        return str;
    }
	
}
