package com.buptsse;

import java.awt.BorderLayout;
import java.awt.Checkbox;
import java.awt.CheckboxGroup;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.*;

public class CrawlerGUI extends JFrame{
	private JTextField jtf1 = new JTextField();
	private JTextField jtf2 = new JTextField();
	private JTextField jtf3 = new JTextField();
	
	private JRadioButton jrb1 = new JRadioButton("��������̳");
	private JRadioButton jrb2 = new JRadioButton("ˮľ�廪");
	private JFileChooser jfc = new JFileChooser();
	File file = new File("");
    public CrawlerGUI(){      //ͼ�ν���
    	setLayout(new BorderLayout());  	
    	JPanel p1 = new JPanel(new GridLayout(1,2));
    	p1.setBorder(BorderFactory.createTitledBorder("��ѡ����ҳ���ͣ�"));
    	p1.add(jrb1);
    	p1.add(jrb2);
    	ButtonGroup btg = new ButtonGroup();
    	btg.add(jrb1);
    	btg.add(jrb2);
    	JLabel l1 = new JLabel("����ҳ���ַ��");
    	JLabel l2 = new JLabel("��ȡҳ����");
    	JLabel l3 = new JLabel("�ı�����·����");
    	//jfc.setDialogTitle("���ļ���λ��");
//    	int result = jfc.showOpenDialog(this);
//    	if(result == jfc.showSaveDialog(this)){
//    		file = jfc.getSelectedFile();
//    	}
    	JPanel p2 = new JPanel(new GridLayout(3,2));
    	p2.add(l1);
    	p2.add(jtf1);
    	p2.add(l2);
    	p2.add(jtf2);
    	
    	p2.add(l3);
    	p2.add(jtf3);
    	//p2.add(jbt1);
    	JButton jbt3 = new JButton("���");
    	JPanel p3 = new JPanel(new BorderLayout());
    	p3.add(jbt3,BorderLayout.EAST);
    	JPanel p4 = new JPanel();
    	JButton jbt1 = new JButton("��ʼ");
    	JButton jbt2 = new JButton("ȡ��");
    	p4.add(jbt1);
    	p4.add(jbt2);
    	JLabel head = new JLabel("�������������ز�����");
    	JPanel p5 = new JPanel(new BorderLayout());
    	p5.add(head,BorderLayout.NORTH);
    	p5.add(p1,BorderLayout.CENTER);
    	p5.add(p2,BorderLayout.SOUTH);
    	JPanel p6 = new JPanel(new BorderLayout());
    	p6.add(p3,BorderLayout.NORTH);
    	p6.add(p4,BorderLayout.CENTER);
    	add(p5,BorderLayout.NORTH);
    	add(p6,BorderLayout.CENTER);
    	jbt1.addActionListener(new CrawlListener());
    	jbt2.addActionListener(new CloseListener());
    	jbt3.addActionListener(new FileListener());
    	
    }
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
        CrawlerGUI frame = new CrawlerGUI();
        frame.setSize(400,250);
        frame.setLocationRelativeTo(null);
        frame.setTitle("Crawler");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
	}
	
	class CrawlListener implements ActionListener{    //��ʼ��ť�¼�����
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			String seedUrl = jtf1.getText();
			int page = Integer.parseInt(jtf2.getText());
			String filePath = jtf3.getText();
			if(filePath.contains("\\")&&!filePath.endsWith("\\")){
				filePath = filePath +"\\";
			}
			if(filePath.contains("/")&&!filePath.endsWith("/")){
				filePath = filePath + "/";
			}
			if(jrb1.isSelected()){
				CrawlerCon crawlerCon = new CrawlerCon(seedUrl,page,filePath,0);
				JOptionPane.showMessageDialog(null, "��ȡ�ɹ���");
			}
			else{
				CrawlerCon crawlerCon = new CrawlerCon(seedUrl,page,filePath,1);
				JOptionPane.showMessageDialog(null, "��ȡ�ɹ���");
			}
			
		}
	}
	class CloseListener implements ActionListener{     //ȡ����ť�¼�����
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			System.exit(0);
		}
	}
	class FileListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			JFileChooser fileChooser = new JFileChooser("D:\\");
	    	fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
	    	String filePath="";
	        int returnVal = fileChooser.showOpenDialog(fileChooser);
	        if(returnVal == JFileChooser.APPROVE_OPTION){       
	            filePath= fileChooser.getSelectedFile().getAbsolutePath();//���������ѡ����ļ��е�·��
	    	}
	        jtf3.setText(filePath);
		}
	}

}
