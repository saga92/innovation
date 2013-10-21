package com.buptsse;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Message {
    private String msgSender;
    private String msgHeader;
    private Date msgDate;
    private String msgDetail;
    private String msgReplyto;
    private String msgFromIP;
    private String msgID;
    
    public Message(String string){
    	int index1=0,index2,index0;
    	index2=string.indexOf("(");// get msgSender;
        if(index2!=-1&&string.startsWith("发信人:"))
            msgSender = string.substring(5, index2).trim()	;
        else {
			msgSender = "";
		}
        index0 = index1; 
        index1=string.indexOf("标  题:",index0);//get msgHeader;
        if(index1==-1)
        	index1 = string.indexOf("标??题:",index0);
    	index2=string.indexOf("发信站:",index1);
    	if(string.startsWith("--")){
    		msgHeader = string.substring(2, index2-1).trim();
    	}
    	else if(index1!=-1&&index2!=-1)
            msgHeader = string.substring(index1+5, index2-1).trim();
        else {
			msgHeader = "";
		}
        index0 = index2;
        index1=string.indexOf("(",index0);//get msgDate;
    	index2=string.indexOf(")",index1);
        String str = null,year,month,day,time;
		if(index1!=-1&&index2!=-1)
            str = string.substring(index1+1, index2);
        else {
			msgDate = null;
        }
		str = toDelate(str, "&nbsp;");		
		String[] date2 = new String[]{"","","","",""};
		int j=0;		
		for(int i=0;i<5;i++){
			date2[i]="";
				while(j<str.length()&&str.charAt(j)!=' '&&str.charAt(j)!=' '&&str.charAt(j)!='?'){
				    date2[i]=date2[i]+str.charAt(j);
				    j++;
				}
				while(j<str.length()&&(str.charAt(j)==' '||str.charAt(j)==' '||str.charAt(j)=='?')){
				    j++;}
			
		}
		month = toNum(date2[1]);
		day = date2[2];
		time = date2[3];
		year = date2[4];
		DateFormat formet = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String s = year+"-"+month+"-"+day+" "+time;
		msgDate = null;
		try{
			msgDate = formet.parse(s);
		}catch (ParseException e) {   
	           e.printStackTrace(); }
		index0 = index2;
		index1=index2+1;//get msgDetail,msgReplyto;
    	index2=string.indexOf("--",index1);
    	if(index1!=-1&&index2!=-1){
        str = string.substring(index1+2, index2);
        str = toDelate(str, "&nbsp;");
        int index11 = str.indexOf("【");
        int index22 = index2 = str.indexOf("提到: 】",index11);
        if(index11!=-1&&index22!=-1){
        	index1 = index11;
        	String replyFormer = str.substring(0,index1);
        	index2 = index22+5;     	
        	String replyTo = str.substring(index1,index2);
        	index1 = replyTo.indexOf("在");
        	int index3 = replyTo.indexOf("的大作中提到");
        	msgReplyto = replyTo.substring(index1+1,index3).trim();
        	if(msgReplyto.indexOf("(")!=-1){
        		msgReplyto = msgReplyto.substring(0,msgReplyto.indexOf("("));
        	}
        	if(!replyFormer.trim().isEmpty()){
        		msgDetail = replyFormer.trim();
        	}
        	else{
        		str = str.substring(index2);
            	while(str.indexOf(": ")!=-1){
            	    str = str.substring( str.indexOf(": ")+2);}
            	if(str.indexOf(" ")!=-1){
            		msgDetail = str.substring(str.indexOf(" ")+1).trim();
            	}
            	else{
            		msgDetail = "";
            	}
        	}
        	
        }
        else {
			msgReplyto = "";
			msgDetail = str.trim();
		}
    	}
    	else if(index1!=-1&&index2==-1){
    		msgDetail = string.substring(index1);
    		msgReplyto = "";
    	}
    	else{
    		msgReplyto = "";
    		msgDetail = "";		
    	}
    	index0 = string.indexOf("※");  //get msgFromIP
    	if(index0!=-1){
            string = string.substring(string.indexOf("※"));
            index1 = string.indexOf("[FROM:");
            index2 = string.indexOf("]");
            if(index1!=-1&&index2!=-1){
                msgFromIP = string.substring(index1+7,index2).trim();}
            else {
            	msgFromIP = "";
			}
    	}
        else{
        	msgFromIP = "";
        }
    } 
    
    public String toDelate(String str,String substr){   //删除字符串中的某个子串
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
    public String toNum(String s){    //将月份转换成数字型
    	switch(s){
    	case "Jan":
    		return "1";
    	case "Feb":
    		return "2";
    	case "Mar":
    		return "3";
    	case "Apr":
    		return "4";
    	case "May":
    		return "5";
    	case "Jun":
    		return "6";
    	case "Jul":
    		return "7";
    	case "Aug":
    		return "8";
    	case "Sep":
    		return "9";
    	case "Oct":
    		return "10";
    	case "Nov":
    		return "11";
    	case "Dec":
    		return "12";
    	}
		return "";
    }

    public String getMsgSender(){
    	return msgSender;
    }
    public String getMsgHeader(){
    	PreProcessFM msg = new PreProcessFM(msgHeader);
    	msgHeader = msg.getString();
    	return msgHeader;
    }
    public Date getMsgDate(){
    	return msgDate;
    }
    public String getMsgDetail(){
    	PreProcessFM msg = new PreProcessFM(msgDetail);
    	msgDetail = msg.getString();
    	return msgDetail;
    }
    public String getMsgReplyto(){
    	return msgReplyto;
    }
    public String getMsgFromIP(){
    	return msgFromIP;
    }
    public void setMsgReplyto(String s){
    	msgReplyto = s;
    }
    public String getMsgID(){
    	return msgID;
    }
    public void setMsgID(String id){
    	msgID = id;
    }
    public void setMsgReplyTo(String id){
    	msgReplyto = id;
    }

public static void main(String[] args){
	// 有个Unparseable的异常，请解决一下
	Message iMessage = new Message("发信人: ygs (要灌水|阿桂，\"人科，圡博属|等你回来。), 信区: CrossGate 标??题: 蓝一·亲友团·非主流职业如何发挥自己最大的效果？ 发信站: 水木社区 (Sun Oct??6 06:03:46 2013), 站内 ?? 蓝1嘛。。弓手的天下，大家都懂的。传教又是最好的奶妈，除了这两个职业以外，别的职业去蓝1都多少有点尴尬。 本文为你解惑，在熟悉的亲友团甚至五开蓝1，其他职业怎么混。 ?? 法师 是的你没看错，我说的就是法师——为什么？ 因为蓝1法师去略亏，超魔一回合180 200魔的，比别人一个队伍消耗都大，输出固然高，但是——也浪费不少。 ?? 推荐策略： 1 和另外一个超10法师双练 2 见下文。 ?? 格斗 好吧，格斗是后娘养的，10级蛋蛋才5-7个还没覆盖全部8个怪。 况且，格斗吧，大家现在练级都快，保不齐去蓝一的时候只有7 8蛋，惨点的也就才6蛋，怎么破？ ?? 推荐策略：见下文。 ?? 重装 满攻的重装空魔回力未尝不可，但是血敏重装呢？回力就有点。。力不从心了，况且，回力本身就不是重装的最佳选择——重装的出路在何方？只能砍狗去么？ 推荐策略：魔格重联合练级！ ?? 魔格重配合法： ?? 法师带自己强力魔法的纯水晶，保证强力能够秒被克的老鼠 实战证明，强6+纯水晶可以秒一种老鼠 强8X+XY强袭可以秒两种老鼠 强8X+YZ强袭可以秒一种，不如纯水晶。 但是强袭本身也不便宜吧， 地风强袭现在不贵，2w左右，土豪可以选择，也可以用快坏的强袭，取决于你的强力魔法是什么。 ?? ?? 重装，空魔普攻单秒老鼠，自己的宠崩击自己打的这只。 无攻重装：没啥说的，带点加攻的装备吧。计算了一下，90级纯血敏剑 裸体70攻，8A剑/10A剑大概260左右（8A便宜，10A坏了还可以强化） 8紫一套128攻，随便带点首饰（专属+鸟毛50攻）能到500攻，配合2级左右乾坤也可以单秒怪物。 500攻，甩回力基本挠痒痒。但是如果普攻属性相克的老鼠。。 ?? ?? 格斗，蛋蛋甩在法师强力点的另外一侧。 ?? 这样可以实现：格斗出手的时候，一般只有5-6怪了，这样格斗的蛋蛋可以覆盖几乎所有的怪物。aoe档次提升。 ?? 实战： 我去蓝1的组合是，8乱，8蛋，8强火，剑士，传教，基本一回合。人物都不带紫装，弓带9B弓，弓格都是满攻加点，剑士洗点纯血敏，带10A剑用乾坤（起码没魔了还能吃锅子补一下） ?? 这样做的优点： 1 省魔， 就算格斗不穿减魔衣服，我一回合的消耗有80+32+40+18=170，和野队4弓乱8的消耗基本相当 2 没圣盾情况下基本一回合，圣盾小于等于两个也比较稳。三个及以上的——我相信没几个队伍敢说稳吧，即便宠物不nono ?? 缺点： 1 需要信任，毕竟尝鲜不是简单事情。 2 需要配合，法师首先定强力，其次剑士定单秒点，然后格斗定蛋的方向，三个职业需要互相参考 ?? 传统回力职业： 护士，饲养等 半血空魔丢回力未尝不可，但是也可以试试看学重装单秒，一般这些丢回力的职业还是有点攻击的，凑到600攻并不难。 另，蓝1补血护士绝对比传教差非常多，带护士补血请慎重。 ?? 其他职业： 医生，驯兽： 小刀没啥意义吧 大概。。还不如学重装单秒。。 ?? 咒术，巫师： 如果敏高的，有超补4级左右的，去蓝1当奶只是消耗料理的问题 强力也勉强够，是更多的料理的问题。。。 -- 1.玩C比的是信心 我就是能farm神装拯救世界 2.玩C比的是理性 我再能farm我也不能1打5 3.玩C比的是队友 他就抢你线，他团站就要后撤，那无解 4.玩C比的是忍耐 看到队友被揍的鸟样而你没神装，那继续farm 5.玩C比的是主见 你第一个杀的人会成为团队的整个风向标，不杀脆皮杀MT，能不废么？ 6.玩C比的是大局观 什么时候放弃，什么时候撤兵，什么时候打bos 什么时候推塔 ?? ?? ※ 来源:·水木社区 newsmth.net·[FROM: 59.66.202.*]");
	System.out.println(iMessage.getMsgSender());
	System.out.println(iMessage.getMsgHeader());
	System.out.println(iMessage.getMsgDate());
	System.out.println(iMessage.getMsgDetail());
	System.out.println(iMessage.getMsgFromIP());
	System.out.println(iMessage.getMsgReplyto());

	
	
}
}