package ossproject;
import javax.swing.*;
import java.awt.font.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.*;
import java.util.Timer;
import java.awt.*;
import java.awt.event.*;
//9�� 30�� ����
//�ڹٸ� ���� ���� ������ �ڵ��� ó���ϴ��� ������ �Ұ����� ��Ȳ�� ����
// �׷��� ���а� GUI ������ ���� First�� â�� ���� ����� 10�� �� �ٽ� �ڵ带 �ۼ�����
// �ϴ� �ð��� �ʹ� ���� ����� �ҿ� ���õ� ������ ������ ������ ���� ����
// ù������ ������ Ʋ�� ȭ�� ��ȯ
// �ι����� ������� ������ ó��
// �������δ� ���ǰ� ���� �߰�
// ���������� �⸻ ������ �����Ѱ��� �ٵ��� �� (�� ���� �����̶� �ٵ��� �ʴ°͵� �����ϰ� ����)
public class scg extends JFrame implements ActionListener{
Container c = getContentPane();
Font Bold = new Font("�ü�ü",Font.BOLD,60);
Color red = new Color(20,20,20);
Font Plane = new Font("����ü",Font.PLAIN,30);
JButton btok = new JButton();
JButton btno = new JButton();
JPanel FirstPage = new JPanel();
GridLayout warngird = new GridLayout(2,2,10,10);
GridLayout btngird = new GridLayout(2,2,10,10);
FlowLayout flow = new FlowLayout(10, 400, 10);
JPanel btnpnl = new JPanel();



Music baton = new Music("Baton.mp3",false);



	public scg() {
		super("scg");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		c.setLayout(null);
		
		
		ImageIcon OK = new ImageIcon("C:/Users/leesn/git/scg/eclipse-workspace/scg project/Images/004-check.png");
		ImageIcon NO = new ImageIcon("C:/Users/leesn/git/scg/eclipse-workspace/scg project/Images/003-dislike.png");
		JPanel warnpnl = new JPanel();
		JLabel warnheadlbl = new JLabel();
		JLabel warnsublbl = new JLabel();
		
		
		c.setBackground(Color.black);
		btnpnl.setBackground(Color.black);
		warnpnl.setBackground(Color.black);
		warnheadlbl.setText("���");
		warnheadlbl.setFont(Bold);
		warnheadlbl.setForeground(Color.RED);
		warnheadlbl.setHorizontalAlignment(JLabel.CENTER);
		//warnsublbl.setText("<html>�̰��� ???�� ������ �����ϰ� �ֽ��ϴ�.<br/>Ư�� ���⿡ ���� �����Ҽ� �����Ƿ� ���� �ٸ��ϴ�.</html>");
		warnsublbl.setText("<html>�ֱٿ� �����ؼ� �ƹ��͵� �����ϴ�.<br/>�ڵ� ���� �����ϴٰ� �ص� ���� �𸨴ϴ�.</html>");
		warnsublbl.setForeground(Color.white);
		warnsublbl.setFont(Plane);
		warnsublbl.setHorizontalAlignment(JLabel.CENTER);
		
		btok = new JButton(OK);
		btno = new JButton(NO);
		btok.setPreferredSize(new Dimension(64,64));
		btno.setPreferredSize(new Dimension(64,64));
		btok.addActionListener(this);
		btno.addActionListener(this);
		
		warnpnl.setBounds(0, 0, 1280, 400);
		btnpnl.setBounds(0,401,1280,401);
		warnpnl.setLayout(warngird);
		btnpnl.setLayout(flow);
		warnpnl.add(warnheadlbl);
		warnpnl.add(warnsublbl);
		btnpnl.add(btok);
		btnpnl.add(btno);
		c.add(warnpnl);
		c.add(btnpnl);
		
		
		setSize(1280,720); 
		setVisible(true);
			
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btok) {
			new First();
			setVisible(false);
		}
		else if(e.getSource() == btno) {
			setVisible(false);
		}
	}
	
	
	
	
	//10�� 28�� ����
	public class First extends JFrame{
		//���â ������ ����
		Frame frame = new Frame();
		//���� �κ� �г�, ��, ��ư ����
		JPanel fpnl[];
		JLabel flbl[];
		JButton fbtn[];
		JTextField nametxt;
		//�ΰ��� �г�, ��, ��ư ����
		JPanel gpnl[];
		JLabel glbl[];
		JButton gbtn[];
		//���� �������� �����ϱ�
		Random rd = new Random();
		String[] quiz = new String[10];	//��������Ʈ �����ؼ� �ٽ� �ø���
		String[] c1 = new String[10];	//1�� ����
		String[] c2 = new String[10];	//2�� ����
		String[] c3 = new String[10];	//3�� ����(�Ҽ� ������ �߰�)
		int ran = rd.nextInt(quiz.length);
		int answer = 0;
		int life = 3;
		int score;
		int[] ans = new int[10]; //������ ����
		
		ImageIcon lobbyimg = new ImageIcon("C:/Users/leesn/git/scg/eclipse-workspace/scg project/Images/gamelogo.png");
		//LGPL���̼��� JLayer
		Music lobby = new Music("LOBBY.mp3", true);
		Music ingame = new Music("Ingame.mp3", true);
		
		First(){
			super("����");
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			Container c = getContentPane();
			//�� �ڵ�� �ǹ� ���°�(�гο��� ���� �޾� ó���ϸ� �Ǳ� ����)
			//c.setBackground(red);
			//��â ���ֱ�
			setUndecorated(true);
			//���� �κ� ����
			setComp();
			addComp();
			addEvent();

			//LGPL ���̼���
			lobby.start();
			
			//���� ����Ʈ
			//��������Ʈ
			quiz[0] = "�� ���� ��� �����ϱ��?";	//c2
			quiz[1] = "1989�� õ�ȹ����� ���� ���� �Ͼ����?"; //c2
			quiz[2] = "�Ѻ��� ��� ���� �����ǻ��ϱ��?"; //c1
			quiz[3] = "��ġ�� ��� ���� ������ �����ϱ��?"; //c1
			quiz[4] = "���� ���������� ���� ���� �Ͼ�� �ֳ���?"; //c2
			quiz[5] = "�ڳ�� ����� Ű���� �ұ��?"; //c3
			quiz[6] = "�����Ͽ� ������ ��ð� �Ͻʴϱ�?"; //c3
			quiz[7] = "�ڷγ��� ��� �������� �߿��߳���?"; //c3
			quiz[8] = "�ѱ�������?"; //c1
			quiz[9] = "���� �ְ��� �����?";//c2
			//������ 1��
			c1[0] = "�̱��� ��汹";
			c1[1] = "õ�ȹ� 6.4 ����";
			c1[2] = "��ȭ�ιΰ�ȭ��";	//����
			c1[3] = "��ȭ�ιΰ�ȭ��";	//����
			c1[4] = "�뵿����� ���뿹ȭ, ��� ����";
			c1[5] = "2��";
			c1[6] = "5�ð�";
			c1[7] = "���ѽ�";
			c1[8] = "�׹̿���"; //����
			c1[9] = "�̱�";
			//������ 2��
			c2[0] = "��ȭ�ιΰ�ȭ��";	//����
			c2[1] = "�ƹ� ���� �������ϴ�.";	//����
			c2[2] = "�h��";
			c2[3] = "�ѱ�";
			c2[4] = "�������� ��ȸ�� ������ �غ�";	//����
			c2[5] = "69,420��";
			c2[6] = "74�ð�";
			c2[7] = "����¡��";
			c2[8] = "ħ������";
			c2[9] = "��ȭ�ιΰ�ȭ��"; //����
			//������ 3��
			c3[0] = "�븸";
			c3[1] = "���� ���� �� ���?";
			c3[2] = "�Ѵ� �ƴѰ�?";
			c3[3] = "�Ϻ�";
			c3[4] = "�̽��� ���� ź��";
			c3[5] = "1��";	//����
			c3[6] = "3�ð�";	//����
			c3[7] = "�λ��"; //����
			c3[8] = "��ġ��";
			c3[9] = "��������";
			//���丮��Ʈ
			ans[0] = 2;
			ans[1] = 2;
			ans[2] = 1;
			ans[3] = 1;
			ans[4] = 2;
			ans[5] = 3;
			ans[6] = 3;
			ans[7] = 3;
			ans[8] = 1;
			ans[9] = 2;
			
			answer = ans[ran]; //��ġ Ȯ�� �ٶ�

			setSize(1280,720);
			setVisible(true);
			setLocationRelativeTo(null);
		}
		void setComp() {
			nametxt = new JTextField(7);
			fpnl = new JPanel[5];
			flbl = new JLabel[5];
			fbtn = new JButton[2];
			for(int i = 0; i < 5; i++) {
				fpnl[i] = new JPanel();
				flbl[i] = new JLabel();
				if(i <2) {
					fbtn[i] = new JButton();
					fbtn[i].setPreferredSize(new Dimension(300,200));
					fbtn[i].setFont(new Font("�������",Font.BOLD,16));
				}
				
			}
			//�󺧼���(0�� �̸� ĭ �߰�,1���� �ΰ� �߰�)
			flbl[1].setIcon(lobbyimg);
			//��ư ����
			fbtn[0].setText("����");
			fbtn[1].setText("����");

			
		}
		void addComp() {
			//�г� ��ġ
			add(fpnl[0], BorderLayout.CENTER);
			add(fpnl[2], BorderLayout.SOUTH);
			//�ΰ���� �г�0�� �߰�
			fpnl[0].add(flbl[1]);
			fpnl[0].setBackground(Color.RED);
			//�̸� ĭ�� �г�1�� �߰�
			fpnl[1].add(new Label("����"));
			fpnl[1].add(nametxt);
			fpnl[1].setBackground(Color.RED);
			//��ư �߰�
			fpnl[2].setBackground(Color.red);
			fpnl[2].add(fbtn[0]);
			fpnl[2].add(fbtn[1]);
			//�翷 ���� �߰�
			fpnl[3].add(flbl[2]);
			fpnl[4].add(flbl[3]);
			
		}
		
		void addEvent() {
			//��ư ������ �߰�
			fbtn[0].addActionListener(new StartHandler());
			fbtn[1].addActionListener(new CantcancelHandler());
		}
		
		class StartHandler implements ActionListener{
			//���۹�ư, ȭ�� ��ȯ �ʿ���
			public void actionPerformed(ActionEvent e) {
				if(e.getSource()==fbtn[0]) {
					lobby.stop();
					ingame.start();
					for(int i = 0; i < 5; i++) {
						fpnl[i].setVisible(false);
					}
					//�ΰ��� ����
					setGameComp();
					addGameComp();
					addGameEvent();
					
				}
			}
		}
		class CantcancelHandler implements ActionListener{
			//���� �ȵǴ� ���� ��ư ( �г�Ƽ �ο� )
			public void actionPerformed(ActionEvent e) {
				if(e.getSource()==fbtn[1]) {
					//��â(�г�Ƽâ)�� ���� ����� �߰��� ���
					new PenaltyImage();
					JOptionPane.showMessageDialog(null, nametxt.getText()+"���� ������ �ȹٷ� �����ÿ�!", "Warning!!", JOptionPane.ERROR_MESSAGE);
					
				}
			}
		}
		void setGameComp() {
			gpnl = new JPanel[3];
			glbl = new JLabel[3];
			gbtn = new JButton[3];
			for(int i = 0; i < 3; i++) {
				gpnl[i] = new JPanel();
				glbl[i] = new JLabel();
				gbtn[i] = new JButton();
				gbtn[i].setPreferredSize(new Dimension(300,200));
				gbtn[i].setFont(new Font("�������",Font.BOLD,16));
			}
			gpnl[2].setLayout(new FlowLayout());
			glbl[0].setText("����"+score+ "����: "+ quiz[ran]);
			glbl[0].setFont(new Font("�ü�ü",Font.BOLD, 40));
			glbl[1].setIcon(lobbyimg);
			//����� �ؽ�Ʈ�� ��ư������ �ٵ����� �ð������� ���� �־��
			gbtn[0].setText("1��: " + c1[ran]);
			gbtn[1].setText("2��: " + c2[ran]);
			gbtn[2].setText("3��: " + c3[ran]);
			
		}
		
		void addGameComp() {
			//0�� ����, 1�� ����, 2�� ������
			add(gpnl[0], BorderLayout.NORTH);
			add(gpnl[1], BorderLayout.CENTER);
			add(gpnl[2], BorderLayout.SOUTH);
			//add(gpnl[2], FlowLayout.CENTER);
			
			//���� ����
			gpnl[0].add(glbl[0]);
			gpnl[0].setBackground(Color.red);
			//���� ����
			gpnl[1].add(glbl[1]);
			gpnl[1].setBackground(Color.red);
			//��ư ����
			
			gpnl[2].add(gbtn[0],FlowLayout.LEFT);
			gpnl[2].add(gbtn[1],FlowLayout.CENTER);
			gpnl[2].add(gbtn[2],FlowLayout.RIGHT);
			gpnl[2].setBackground(Color.red);
		}
		//����� ���� ������ ó��
		void addGameEvent() {
			gbtn[0].addActionListener(new CorrectHandler());
			gbtn[1].addActionListener(new CorrectHandler());
			gbtn[2].addActionListener(new CorrectHandler());
		}
		//���� ������
		class CorrectHandler implements ActionListener{
			
			public void actionPerformed(ActionEvent e) {
				//ran=������ȣ
				//gbtn[0] gbtn[0] gbtn[0]
				//ans[ran]=2
				if(e.getSource()==gbtn[ans[ran]-1]){
					score = score + 1;
					new CorrectResult();
					ran = rd.nextInt(ans.length);
					glbl[0].setText("����"+score+ "����: "+ quiz[ran]);
					glbl[1].setIcon(lobbyimg);
					//����� �ؽ�Ʈ�� ��ư������ �ٵ����� �ð������� ���� �־��
					gbtn[0].setText("1��: " + c1[ran]);
					gbtn[1].setText("2��: " + c2[ran]);
					gbtn[2].setText("3��: " + c3[ran]);
				}
				else {
					life = life - 1;
					if(life == 2 || life == 1) {
						new WrongResult();
						ran = rd.nextInt(ans.length);
						glbl[0].setText("����"+score+ "����: "+ quiz[ran]);
						//����� �ؽ�Ʈ�� ��ư������ �ٵ����� �ð������� ���� �־��
						gbtn[0].setText("1��: " + c1[ran]);
						gbtn[1].setText("2��: " + c2[ran]);
						gbtn[2].setText("3��: " + c3[ran]);
						
					}
					if(life == 0) {
						setVisible(false);
						new GameOver(score);
						ingame.stop();
					}
				}

			}
		}
		
	}
	//����,�г�Ƽ�� ��� ������
	class PenaltyImage extends JFrame{
		JFrame penalty = new JFrame();
		Music BassBoost = new Music("BassBoost.mp3" ,false);
		public PenaltyImage() {
			//������ ���ϱ�
			Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
			penalty.setUndecorated(true);
			ImageIcon image = new ImageIcon("C:/Users/leesn/git/scg/eclipse-workspace/scg project/Images/redtxt.png");
			JLabel warnlbl = new JLabel(image);
			penalty.getContentPane().add(warnlbl);
			penalty.setSize(image.getIconWidth(), image.getIconHeight());
			//���� ���� â�� ���� �� ��ġ
			int x = (screenSize.width - penalty.getSize().width)/2;
			int y = (screenSize.height - penalty.getSize().height)/2;
			penalty.setLocation(x, y);
			penalty.setVisible(true);
			//��ư���� �г�Ƽâ ��ȣ�ۿ�
			penalty.addKeyListener(new ExitWarningListener());
			penalty.setLocationRelativeTo(null);
			BassBoost.start();
			
		}
		//escape Ű�� â �ݱ�
		class ExitWarningListener extends KeyAdapter {
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ESCAPE) {
					penalty.setVisible(false);
					BassBoost.stop();
				}
			}
		}
	}
	
	class CorrectResult extends JFrame{
		JFrame cResult = new JFrame();
		Music yay = new Music("Yay.mp3",false);
		public CorrectResult() {
			//������ ���ϱ�
			Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
			cResult.setUndecorated(true);
			ImageIcon image = new ImageIcon("C:/Users/leesn/git/scg/eclipse-workspace/scg project/Images/greentxt2.png");
			JLabel warnlbl = new JLabel(image);
			cResult.getContentPane().add(warnlbl);
			cResult.setSize(image.getIconWidth(), image.getIconHeight());
			//���� ���� â�� ���� �� ��ġ
			int x = (screenSize.width - cResult.getSize().width)/2;
			int y = (screenSize.height - cResult.getSize().height)/2;
			cResult.setLocation(x, y);
			cResult.setVisible(true);
			//��ư���� �г�Ƽâ ��ȣ�ۿ�
			cResult.addKeyListener(new ExitWarningListener());
			cResult.setLocationRelativeTo(null);
			yay.start();
			
		}
		//escape Ű�� â �ݱ�
		class ExitWarningListener extends KeyAdapter {
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ESCAPE) {
					cResult.setVisible(false);
					yay.stop();
				}
			}
		}
	}
	
	class WrongResult extends JFrame{
		JFrame wResult = new JFrame();
		Music chimp = new Music("Chimp.mp3",false);
		public WrongResult() {
			//������ ���ϱ�
			Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
			wResult.setUndecorated(true);
			ImageIcon image = new ImageIcon("C:/Users/leesn/git/scg/eclipse-workspace/scg project/Images/redtxt.png");
			JLabel warnlbl = new JLabel(image);
			wResult.getContentPane().add(warnlbl);
			wResult.setSize(image.getIconWidth(), image.getIconHeight());
			//���� ���� â�� ���� �� ��ġ
			int x = (screenSize.width - wResult.getSize().width)/2;
			int y = (screenSize.height - wResult.getSize().height)/2;
			wResult.setLocation(x, y);
			wResult.setVisible(true);
			//��ư���� �г�Ƽâ ��ȣ�ۿ�
			wResult.addKeyListener(new ExitWarningListener());
			wResult.setLocationRelativeTo(null);
			chimp.start();
		}
		//escape Ű�� â �ݱ�
		class ExitWarningListener extends KeyAdapter {
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ESCAPE) {
					wResult.setVisible(false);
					chimp.stop();
				}
			}
		}
	}
	class GameOver extends JFrame  {
		int score;
		ImageIcon gameover = new ImageIcon("C:/Users/leesn/git/scg/eclipse-workspace/scg project/Images/gameover1fix.png");
		Music gameovermusic = new Music("gameover.mp3",false);
		Music scream = new Music("AfricanAmericanScream.mp3",false);
		JPanel opnl[];
		JLabel olbl[];
		JButton obtn[];
		GameOver(int score) {
			super("GAME OVER");
			this.score = score;
			setUndecorated(true);
			
			setOverComp();
			addOverComp();
			addOverEvent();
			
			gameovermusic.start();
			scream.start();
			setSize(1280,720);
			setVisible(true);
			setLocationRelativeTo(null);
		}
		void setOverComp() {
			opnl = new JPanel[3];
			olbl = new JLabel[3];
			obtn = new JButton[3];
			
			for(int i = 0; i < 3; i++) {
				opnl[i] = new JPanel();
				obtn[i] = new JButton();
				obtn[i].setPreferredSize(new Dimension(300,200));
				obtn[i].setFont(new Font("�������",Font.BOLD,30));
				if(i<2) {
					olbl[i] = new JLabel();
				}
		}
			olbl[0].setIcon(gameover);
			
			olbl[1].setText("<html>����� ó���� ���Դϴ�.<br/>����� ����: "+ Integer.toString(score*15) + "</html>");
			olbl[1].setFont(new Font("�ü�ü",Font.BOLD,60));
			olbl[1].setForeground(Color.WHITE);
			

			obtn[0].setText("�����");
			obtn[1].setText("����");
			obtn[2].setText("���¼ҽ� ���̼���");
	}
		void addOverComp() {
			add(opnl[0], BorderLayout.NORTH);
			add(opnl[1], BorderLayout.CENTER);
			add(opnl[2], BorderLayout.SOUTH);
			
			//���� ����
			opnl[0].add(olbl[0]);
			opnl[0].setBackground(Color.BLACK);
			//���� ����
			opnl[1].add(olbl[1]);
			opnl[1].setBackground(Color.BLACK);
			//��ư ����
			
			opnl[2].add(obtn[0],FlowLayout.LEFT);
			opnl[2].add(obtn[1],FlowLayout.CENTER);
			opnl[2].add(obtn[2],FlowLayout.RIGHT);
			opnl[2].setBackground(Color.BLACK);
		}
		void addOverEvent() {
			obtn[0].addActionListener(new RestartListener());
			obtn[1].addActionListener(new ExitGameListener());
			obtn[2].addActionListener(new OpenSourceListener());
		}
		
		class RestartListener implements ActionListener{
			public void actionPerformed(ActionEvent e) {
				if(e.getSource()==obtn[0]) {
					setVisible(false);
					gameovermusic.stop();
					scream.stop();
					new First();
				}
			}
		}
		class ExitGameListener implements ActionListener{
			public void actionPerformed(ActionEvent e) {
				if(e.getSource()==obtn[1]) {
					gameovermusic.stop();
					scream.stop();
					System.exit(0);
				}
			}
		}
		class OpenSourceListener implements ActionListener{
			public void actionPerformed(ActionEvent e) {
				if(e.getSource()==obtn[2]) {
					new OSL();
				}
			}
		}
			
	}
	
	class OSL extends JFrame {
		JPanel oslpnl;
		JTable osltbl;
		JTextArea oslta;
		JScrollPane scrollPane;
		
		OSL(){
			super("Open Source License");
			
			setOSLComp();
			addOSLComp();
			
			setSize(1280,720);
			setVisible(true);
			setLocationRelativeTo(null);
		}
		
		void setOSLComp() {
			oslpnl = new JPanel();
			osltbl = new JTable();
			scrollPane = new JScrollPane(oslta);
			oslta = new JTextArea("GNU LESSER GENERAL PUBLIC LICENSE\r\n"
					+ "Version 3, 29 June 2007\r\n"
					+ "\r\n"
					+ "Copyright (C) 2007 Free Software Foundation, Inc. <http://fsf.org/>\r\n"
					+ "\r\n"
					+ "Everyone is permitted to copy and distribute verbatim copies of this license document, but changing it is not allowed.\r\n"
					+ "\r\n"
					+ "This version of the GNU Lesser General Public License incorporates the terms and conditions of version 3 of the GNU General Public License, supplemented by the additional permissions listed below.\r\n"
					+ "\r\n"
					+ "0. Additional Definitions.\r\n"
					+ "\r\n"
					+ "As used herein, ��this License�� refers to version 3 of the GNU Lesser General Public License, and the ��GNU GPL�� refers to version 3 of the GNU General Public License.\r\n"
					+ "\r\n"
					+ "��The Library�� refers to a covered work governed by this License, other than an Application or a Combined Work as defined below.\r\n"
					+ "\r\n"
					+ "An ��Application�� is any work that makes use of an interface provided by the Library, but which is not otherwise based on the Library. Defining a subclass of a class defined by the Library is deemed a mode of using an interface provided by the Library.\r\n"
					+ "\r\n"
					+ "A ��Combined Work�� is a work produced by combining or linking an Application with the Library. The particular version of the Library with which the Combined Work was made is also called the ��Linked Version��.\r\n"
					+ "\r\n"
					+ "The ��Minimal Corresponding Source�� for a Combined Work means the Corresponding Source for the Combined Work, excluding any source code for portions of the Combined Work that, considered in isolation, are based on the Application, and not on the Linked Version.\r\n"
					+ "\r\n"
					+ "The ��Corresponding Application Code�� for a Combined Work means the object code and/or source code for the Application, including any data and utility programs needed for reproducing the Combined Work from the Application, but excluding the System Libraries of the Combined Work.\r\n"
					+ "\r\n"
					+ "1. Exception to Section 3 of the GNU GPL.\r\n"
					+ "\r\n"
					+ "You may convey a covered work under sections 3 and 4 of this License without being bound by section 3 of the GNU GPL.\r\n"
					+ "\r\n"
					+ "2. Conveying Modified Versions.\r\n"
					+ "\r\n"
					+ "If you modify a copy of the Library, and, in your modifications, a facility refers to a function or data to be supplied by an Application that uses the facility (other than as an argument passed when the facility is invoked), then you may convey a copy of the modified version:\r\n"
					+ "\r\n"
					+ "a) under this License, provided that you make a good faith effort to ensure that, in the event an Application does not supply the function or data, the facility still operates, and performs whatever part of its purpose remains meaningful, or\r\n"
					+ "b) under the GNU GPL, with none of the additional permissions of this License applicable to that copy.\r\n"
					+ "3. Object Code Incorporating Material from Library Header Files.\r\n"
					+ "\r\n"
					+ "The object code form of an Application may incorporate material from a header file that is part of the Library. You may convey such object code under terms of your choice, provided that, if the incorporated material is not limited to numerical parameters, data structure layouts and accessors, or small macros, inline functions and templates (ten or fewer lines in length), you do both of the following:\r\n"
					+ "\r\n"
					+ "a) Give prominent notice with each copy of the object code that the Library is used in it and that the Library and its use are covered by this License.\r\n"
					+ "b) Accompany the object code with a copy of the GNU GPL and this license document.\r\n"
					+ "4. Combined Works.\r\n"
					+ "\r\n"
					+ "You may convey a Combined Work under terms of your choice that, taken together, effectively do not restrict modification of the portions of the Library contained in the Combined Work and reverse engineering for debugging such modifications, if you also do each of the following:\r\n"
					+ "\r\n"
					+ "a) Give prominent notice with each copy of the Combined Work that the Library is used in it and that the Library and its use are covered by this License.\r\n"
					+ "b) Accompany the Combined Work with a copy of the GNU GPL and this license document.\r\n"
					+ "c) For a Combined Work that displays copyright notices during execution, include the copyright notice for the Library among these notices, as well as a reference directing the user to the copies of the GNU GPL and this license document.\r\n"
					+ "d) Do one of the following:\r\n"
					+ "0) Convey the Minimal Corresponding Source under the terms of this License, and the Corresponding Application Code in a form suitable for, and under terms that permit, the user to recombine or relink the Application with a modified version of the Linked Version to produce a modified Combined Work, in the manner specified by section 6 of the GNU GPL for conveying Corresponding Source.\r\n"
					+ "1) Use a suitable shared library mechanism for linking with the Library. A suitable mechanism is one that (a) uses at run time a copy of the Library already present on the user's computer system, and (b) will operate properly with a modified version of the Library that is interface-compatible with the Linked Version.\r\n"
					+ "e) Provide Installation Information, but only if you would otherwise be required to provide such information under section 6 of the GNU GPL, and only to the extent that such information is necessary to install and execute a modified version of the Combined Work produced by recombining or relinking the Application with a modified version of the Linked Version. (If you use option 4d0, the Installation Information must accompany the Minimal Corresponding Source and Corresponding Application Code. If you use option 4d1, you must provide the Installation Information in the manner specified by section 6 of the GNU GPL for conveying Corresponding Source.)\r\n"
					+ "5. Combined Libraries.\r\n"
					+ "\r\n"
					+ "You may place library facilities that are a work based on the Library side by side in a single library together with other library facilities that are not Applications and are not covered by this License, and convey such a combined library under terms of your choice, if you do both of the following:\r\n"
					+ "\r\n"
					+ "a) Accompany the combined library with a copy of the same work based on the Library, uncombined with any other library facilities, conveyed under the terms of this License.\r\n"
					+ "b) Give prominent notice with the combined library that part of it is a work based on the Library, and explaining where to find the accompanying uncombined form of the same work.\r\n"
					+ "6. Revised Versions of the GNU Lesser General Public License.\r\n"
					+ "\r\n"
					+ "The Free Software Foundation may publish revised and/or new versions of the GNU Lesser General Public License from time to time. Such new versions will be similar in spirit to the present version, but may differ in detail to address new problems or concerns.\r\n"
					+ "\r\n"
					+ "Each version is given a distinguishing version number. If the Library as you received it specifies that a certain numbered version of the GNU Lesser General Public License ��or any later version�� applies to it, you have the option of following the terms and conditions either of that published version or of any later version published by the Free Software Foundation. If the Library as you received it does not specify a version number of the GNU Lesser General Public License, you may choose any version of the GNU Lesser General Public License ever published by the Free Software Foundation.\r\n"
					+ "\r\n"
					+ "If the Library as you received it specifies that a proxy can decide whether future versions of the GNU Lesser General Public License shall apply, that proxy's public statement of acceptance of any version is permanent authorization for you to choose that version for the Library.",10,50);
			 
		}
		void addOSLComp() {
			add(oslpnl, BorderLayout.CENTER);
			oslpnl.add(new JScrollPane(osltbl));
			osltbl.add(oslta);
			
		}
	}

	public static void main(String[] args) {
		new scg();
		
	}



}
