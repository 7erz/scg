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

//9월 30일 시작
//자바를 알지 못해 난잡한 코드들로 처리하느라 정리가 불가능한 상황에 왔음
// 그래서 독학과 GUI 수업을 통해 First란 창을 새로 띄워서 10월 말 다시 코드를 작성했음
// 일단 시간이 너무 늦은 관계로 밈에 관련된 간단한 선택형 게임을 만들 예정
// 첫번쨰는 간단한 틀과 화면 전환
// 두번쨰는 퀴즈들의 내용들과 처리
// 세번쨰로는 음악과 사운드 추가
// 마지막으로 기말 전까지 세세한것을 다듬을 것 (밈 관련 게임이라 다듬지 않는것도 생각하고 있음)
public class scg extends JFrame implements ActionListener {
	Container c = getContentPane();
	Font Bold = new Font("궁서체", Font.BOLD, 60);
	Color red = new Color(20, 20, 20);
	Font Plane = new Font("굴림체", Font.PLAIN, 30);
	JButton btok = new JButton();
	JButton btno = new JButton();
	JPanel FirstPage = new JPanel();
	GridLayout warngird = new GridLayout(2, 2, 10, 10);
	GridLayout btngird = new GridLayout(2, 2, 10, 10);
	FlowLayout flow = new FlowLayout(10, 400, 10);
	JPanel btnpnl = new JPanel();

	Music baton = new Music("Baton.mp3", false);

	public scg() {
		super("scg");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		c.setLayout(null);
		
		ImageIcon OK = new ImageIcon(getClass().getClassLoader().getResource("Images/004-check.png"));
		ImageIcon NO = new ImageIcon(getClass().getClassLoader().getResource("Images/003-dislike.png"));
		//ImageIcon NO = new ImageIcon("Images/003-dislike.png");
		JPanel warnpnl = new JPanel();
		JLabel warnheadlbl = new JLabel();
		JLabel warnsublbl = new JLabel();

		c.setBackground(Color.black);
		btnpnl.setBackground(Color.black);
		warnpnl.setBackground(Color.black);
		warnheadlbl.setText("경고");
		warnheadlbl.setFont(Bold);
		warnheadlbl.setForeground(Color.RED);
		warnheadlbl.setHorizontalAlignment(JLabel.CENTER);
		warnsublbl.setText("<html>이 게임은 밈 갬성을 위해 일부로 열화되었읍니다.<br/>특정 성향에 따라 불쾌할수 있으므로 주의 바립니다.</html>");
		warnsublbl.setForeground(Color.white);
		warnsublbl.setFont(Plane);
		warnsublbl.setHorizontalAlignment(JLabel.CENTER);

		btok = new JButton(OK);
		btno = new JButton(NO);
		btok.setPreferredSize(new Dimension(64, 64));
		btno.setPreferredSize(new Dimension(64, 64));
		btok.addActionListener(this);
		btno.addActionListener(this);

		btok.setBorderPainted(false);
		btok.setFocusPainted(false);
		btok.setContentAreaFilled(false);

		btno.setBorderPainted(false);
		btno.setFocusPainted(false);
		btno.setContentAreaFilled(false);

		warnpnl.setBounds(0, 0, 1280, 400);
		btnpnl.setBounds(0, 401, 1280, 401);
		warnpnl.setLayout(warngird);
		btnpnl.setLayout(flow);
		warnpnl.add(warnheadlbl);
		warnpnl.add(warnsublbl);
		btnpnl.add(btok);
		btnpnl.add(btno);
		c.add(warnpnl);
		c.add(btnpnl);

		setSize(1280, 720);
		setVisible(true);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btok) {
			new First();
			setVisible(false);
		} else if (e.getSource() == btno) {
			setVisible(false);
		}
	}

	// 10월 28일 시작
	public class First extends JFrame {
		// 상단창 프레임 제거
		Frame frame = new Frame();
		// 게임 로비 패널, 라벨, 버튼 지정
		JPanel fpnl[];
		JLabel flbl[];
		JButton fbtn[];
		JTextField nametxt;
		// 인게임 패널, 라벨, 버튼 지정
		JPanel gpnl[];
		JLabel glbl[];
		JButton gbtn[];
		// 문제 랜덤으로 제공하기
		Random rd = new Random();
		// 여기서
		String[] quiz = new String[14]; // 문제리스트 참고해서 다시 올릴것
		String[] c1 = new String[14]; // 1번 문항
		String[] c2 = new String[14]; // 2번 문항
		String[] c3 = new String[14]; // 3번 문항(할수 있으면 추가)
		int[] ans = new int[14]; // 정답의 갯수
		// https://seoneu.tistory.com/10 사진 배열
		ImageIcon[] pic = { new ImageIcon(getClass().getClassLoader().getResource("Images/0.png")), // [0]
				new ImageIcon(getClass().getClassLoader().getResource("Images/1.jpg")), // [1]
				new ImageIcon(getClass().getClassLoader().getResource("Images/2.jpg")), // [2]
				new ImageIcon(getClass().getClassLoader().getResource("Images/3.jpg")), // [3]
				new ImageIcon(getClass().getClassLoader().getResource("Images/4.jpg")), // [4]
				new ImageIcon(getClass().getClassLoader().getResource("Images/5.jpg")), // [5]
				new ImageIcon(getClass().getClassLoader().getResource("Images/6.jpg")), // [6]
				new ImageIcon(getClass().getClassLoader().getResource("Images/7.jpg")), // [7]
				new ImageIcon(getClass().getClassLoader().getResource("Images/8.png")), // [8]
				new ImageIcon(getClass().getClassLoader().getResource("Images/9.jpg")), // [9]
				new ImageIcon(getClass().getClassLoader().getResource("Images/10.jpg")), // [10]
				new ImageIcon(getClass().getClassLoader().getResource("Images/11.jpg")), // [11]
				new ImageIcon(getClass().getClassLoader().getResource("Images/12.jpg")), // [12]
				new ImageIcon(getClass().getClassLoader().getResource("Images/13.jpg")),// [13]
		};
		// 여기까지 문제가 증가할때마다 수정 해야함

		int ran = rd.nextInt(quiz.length);
		int answer = 0;
		int life = 3;
		int score;

		ImageIcon lobbyimg = new ImageIcon(getClass().getClassLoader().getResource("Images/gamelogo.png"));
		// LGPL라이센스 JLayer
		Music lobby = new Music("LOBBY.mp3", true);
		Music ingame = new Music("Ingame.mp3", true);

		First() {
			super("시작");
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			Container c = getContentPane();
			// 이 코드는 의미 없는것(패널에서 색을 받아 처리하면 되기 때문)
			// c.setBackground(red);
			// 윗창 없애기
			setUndecorated(true);
			// 게임 로비 설정
			setComp();
			addComp();
			addEvent();

			// LGPL 라이센스
			lobby.start();

			// 문제 리스트
			// 문제리스트
			quiz[0] = "이 곳은 어느 나라일까요?"; // c2
			quiz[1] = "1989년 천안문에서 무슨 일이 일어났나요?"; // c2
			quiz[2] = "한복은 어느 나라 전통의상일까요?"; // c1
			quiz[3] = "김치는 어느 나라 고유의 음식일까요?"; // c1
			quiz[4] = "신장 위구르에선 무슨 일이 일어나고 있나요?"; // c2
			quiz[5] = "자녀는 몇명을 키워야 할까요?"; // c3
			quiz[6] = "일주일에 게임을 몇시간 하십니까?"; // c3
			quiz[7] = "코로나는 어느 지역에서 발원했나요?"; // c3
			quiz[8] = "한국전쟁은?"; // c1
			quiz[9] = "한글창제 세종대왕은 어느 나라 사람?";// c2
			quiz[10] = "영어는?"; // c1
			quiz[11] = "징기스칸은?"; // c3
			quiz[12] = "고구려의 역사는?"; // c1
			quiz[13] = "홍콩의 독립은?"; // c2
			// 선텍지 1번
			c1[0] = "미국의 우방국";
			c1[1] = "천안문 6.4 항쟁";
			c1[2] = "중화인민공화국"; // 정답
			c1[3] = "중화인민공화국"; // 정답
			c1[4] = "노동착취와 성노예화, 장기 적출";
			c1[5] = "10명";
			c1[6] = "5시간";
			c1[7] = "우한시";
			c1[8] = "항미원조"; // 정답
			c1[9] = "한민족";
			c1[10] = "중국어의 사투리"; // 정답
			c1[11] = "몽골인";
			c1[12] = "중국 한나라의 역사"; // 정답
			c1[13] = "독립해야 한다.";
			// 선택지 2번
			c2[0] = "중화인민공화국"; // 정답
			c2[1] = "아무 일이 없었습니다."; // 정답
			c2[2] = "한국";
			c2[3] = "한국";
			c2[4] = "교육시켜 사회에 내보낼 준비"; // 정답
			c2[5] = "69,420명";
			c2[6] = "74시간";
			c2[7] = "모르겠습니다?";
			c2[8] = "침략전쟁";
			c2[9] = "조선족"; // 정답
			c2[10] = "영국의 토속언어";
			c2[11] = "러시아인";
			c2[12] = "한국 삼국사 역사";
			c2[13] = "하나의 중국을 위해 편입"; // 정답
			// 선택지 3번
			c3[0] = "대만";
			c3[1] = "진압 당한 그 사건?";
			c3[2] = "둘다 아닌가?";
			c3[3] = "일본";
			c3[4] = "이슬람 신자 탄압";
			c3[5] = "3명"; // 정답
			c3[6] = "3시간"; // 정답
			c3[7] = "서울시"; // 정답
			c3[8] = "정치쇼";
			c3[9] = "아이누족";
			c3[10] = "기원을 알수 없다";
			c3[11] = "중국인"; // 정답
			c3[12] = "러시아 슬라브족 역사";
			c3[13] = "아직 영국땅 아닌가?";
			// 정답리스트
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
			ans[10] = 1;
			ans[11] = 3;
			ans[12] = 1;
			ans[13] = 2;

			answer = ans[ran]; // 위치 확인 바람

			setSize(1280, 720);
			setVisible(true);
			setLocationRelativeTo(null);
		}

		void setComp() {
			nametxt = new JTextField(7);
			fpnl = new JPanel[5];
			flbl = new JLabel[5];
			fbtn = new JButton[2];
			for (int i = 0; i < 5; i++) {
				fpnl[i] = new JPanel();
				flbl[i] = new JLabel();
				if (i < 2) {
					fbtn[i] = new JButton();
					fbtn[i].setPreferredSize(new Dimension(300, 180));
					fbtn[i].setFont(new Font("맑은고딕", Font.BOLD, 16));
				}

			}
			// 라벨설정(0은 이름 칸 추가,1에는 로고 추가)
			flbl[1].setIcon(lobbyimg);
			// 버튼 설정
			fbtn[0].setText("시작");
			fbtn[1].setText("종료");

		}

		void addComp() {
			// 패널 배치
			add(fpnl[0], BorderLayout.CENTER);
			add(fpnl[2], BorderLayout.SOUTH);
			// 로고라벨을 패널0에 추가
			fpnl[0].add(flbl[1]);
			fpnl[0].setBackground(Color.RED);
			// 이름 칸을 패널1에 추가
			fpnl[1].add(new Label("성함"));
			fpnl[1].add(nametxt);
			fpnl[1].setBackground(Color.RED);
			// 버튼 추가
			fpnl[2].setBackground(Color.red);
			fpnl[2].add(fbtn[0]);
			fpnl[2].add(fbtn[1]);
			// 양옆 사진 추가
			fpnl[3].add(flbl[2]);
			fpnl[4].add(flbl[3]);

		}

		void addEvent() {
			// 버튼 리스너 추가
			fbtn[0].addActionListener(new StartHandler());
			fbtn[1].addActionListener(new CantcancelHandler());
		}

		class StartHandler implements ActionListener {
			// 시작버튼, 화면 전환 필요함
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == fbtn[0]) {
					lobby.stop();
					ingame.start();
					for (int i = 0; i < 5; i++) {
						fpnl[i].setVisible(false);
					}
					// 인게임 설정
					setGameComp();
					addGameComp();
					addGameEvent();

				}
			}
		}

		class CantcancelHandler implements ActionListener {
			// 종료 안되는 종료 버튼 ( 패널티 부여 )
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == fbtn[1]) {
					// 새창(패널티창)을 띄우고 경고문을 추가로 띄움
					new PenaltyImage();
					JOptionPane.showMessageDialog(null, nametxt.getText() + "동지 교육을 똑바로 들으시오!", "Warning!!",
							JOptionPane.ERROR_MESSAGE);

				}
			}
		}

		void setGameComp() {
			gpnl = new JPanel[3];
			glbl = new JLabel[3];
			gbtn = new JButton[3];
			for (int i = 0; i < 3; i++) {
				gpnl[i] = new JPanel();
				glbl[i] = new JLabel();
				gbtn[i] = new JButton();
				gbtn[i].setPreferredSize(new Dimension(300, 180));
				gbtn[i].setFont(new Font("맑은고딕", Font.BOLD, 16));
			}
			gpnl[2].setLayout(new FlowLayout());
			glbl[0].setText("문제: " + quiz[ran]);
			glbl[0].setFont(new Font("궁서체", Font.BOLD, 40));
			glbl[1].setIcon(pic[ran]);
			// 현재는 텍스트형 버튼이지만 다듬을떄 시간있으면 사진 넣어볼것
			gbtn[0].setText("1번: " + c1[ran]);
			gbtn[1].setText("2번: " + c2[ran]);
			gbtn[2].setText("3번: " + c3[ran]);

		}

		void addGameComp() {
			// 0은 문제, 1은 사진, 2는 선택지
			add(gpnl[0], BorderLayout.NORTH);
			add(gpnl[1], BorderLayout.CENTER);
			add(gpnl[2], BorderLayout.SOUTH);
			// add(gpnl[2], FlowLayout.CENTER);

			// 문제 설정
			gpnl[0].add(glbl[0]);
			gpnl[0].setBackground(Color.red);
			// 사진 설정
			gpnl[1].add(glbl[1]);
			gpnl[1].setBackground(Color.red);
			// 버튼 설정

			gpnl[2].add(gbtn[0], FlowLayout.LEFT);
			gpnl[2].add(gbtn[1], FlowLayout.CENTER);
			gpnl[2].add(gbtn[2], FlowLayout.RIGHT);
			gpnl[2].setBackground(Color.red);
		}

		// 정답과 오답 리스터 처리
		void addGameEvent() {
			gbtn[0].addActionListener(new CorrectHandler());
			gbtn[1].addActionListener(new CorrectHandler());
			gbtn[2].addActionListener(new CorrectHandler());
		}

		// 정답 리스너
		class CorrectHandler implements ActionListener {

			public void actionPerformed(ActionEvent e) {
				// ran=문제번호
				// gbtn[0] gbtn[0] gbtn[0]
				// ans[ran]=2
				if (e.getSource() == gbtn[ans[ran] - 1]) {
					score = score + 1;
					new CorrectResult();
					ran = rd.nextInt(ans.length);
					glbl[0].setText("문제: " + quiz[ran]);
					glbl[1].setIcon(pic[ran]);
					// 현재는 텍스트형 버튼이지만 다듬을떄 시간있으면 사진 넣어볼것
					gbtn[0].setText("1번: " + c1[ran]);
					gbtn[1].setText("2번: " + c2[ran]);
					gbtn[2].setText("3번: " + c3[ran]);
				} else {
					life = life - 1;
					if (life == 2 || life == 1) {
						new WrongResult();
						ran = rd.nextInt(ans.length);
						glbl[0].setText("문제: " + quiz[ran]);
						glbl[1].setIcon(pic[ran]);
						// 현재는 텍스트형 버튼이지만 다듬을떄 시간있으면 사진 넣어볼것
						gbtn[0].setText("1번: " + c1[ran]);
						gbtn[1].setText("2번: " + c2[ran]);
						gbtn[2].setText("3번: " + c3[ran]);

					}
					if (life == 0) {
						setVisible(false);
						new GameOver(score);
						ingame.stop();
					}
				}

			}
		}

	}

	// 실점,패널티용 경고 프레임
	class PenaltyImage extends JFrame {
		JFrame penalty = new JFrame();
		Music BassBoost = new Music("BassBoost.mp3", false);

		public PenaltyImage() {
			// 사이즈 구하기
			Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
			penalty.setUndecorated(true);
			ImageIcon image = new ImageIcon(getClass().getClassLoader().getResource("Images/redtxt.png"));
			JLabel warnlbl = new JLabel(image);
			penalty.getContentPane().add(warnlbl);
			penalty.setSize(image.getIconWidth(), image.getIconHeight());
			// 가로 세로 창을 놓을 곳 배치
			int x = (screenSize.width - penalty.getSize().width) / 2;
			int y = (screenSize.height - penalty.getSize().height) / 2;
			penalty.setLocation(x, y);
			penalty.setVisible(true);
			// 버튼으로 패널티창 상호작용
			penalty.addKeyListener(new ExitWarningListener());
			penalty.setLocationRelativeTo(null);
			BassBoost.start();

		}

		// escape 키로 창 닫기
		class ExitWarningListener extends KeyAdapter {
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
					penalty.setVisible(false);
					BassBoost.stop();
				}
			}
		}
	}

	class CorrectResult extends JFrame {
		JFrame cResult = new JFrame();
		Music yay = new Music("Yay.mp3", false);

		public CorrectResult() {
			// 사이즈 구하기
			Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
			cResult.setUndecorated(true);
			ImageIcon image = new ImageIcon(getClass().getClassLoader().getResource("Images/greentxt2.png"));
			JLabel warnlbl = new JLabel(image);
			cResult.getContentPane().add(warnlbl);
			cResult.setSize(image.getIconWidth(), image.getIconHeight());
			// 가로 세로 창을 놓을 곳 배치
			int x = (screenSize.width - cResult.getSize().width) / 2;
			int y = (screenSize.height - cResult.getSize().height) / 2;
			cResult.setLocation(x, y);
			cResult.setVisible(true);
			// 버튼으로 패널티창 상호작용
			cResult.addKeyListener(new ExitWarningListener());
			cResult.setLocationRelativeTo(null);
			yay.start();

		}

		// escape 키로 창 닫기
		class ExitWarningListener extends KeyAdapter {
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
					cResult.setVisible(false);
					yay.stop();
				}
			}
		}
	}

	class WrongResult extends JFrame {
		JFrame wResult = new JFrame();
		Music chimp = new Music("Chimp.mp3", false);

		public WrongResult() {
			// 사이즈 구하기
			Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
			wResult.setUndecorated(true);
			ImageIcon image = new ImageIcon(getClass().getClassLoader().getResource("Images/redtxt.png"));
			JLabel warnlbl = new JLabel(image);
			wResult.getContentPane().add(warnlbl);
			wResult.setSize(image.getIconWidth(), image.getIconHeight());
			// 가로 세로 창을 놓을 곳 배치
			int x = (screenSize.width - wResult.getSize().width) / 2;
			int y = (screenSize.height - wResult.getSize().height) / 2;
			wResult.setLocation(x, y);
			wResult.setVisible(true);
			// 버튼으로 패널티창 상호작용
			wResult.addKeyListener(new ExitWarningListener());
			wResult.setLocationRelativeTo(null);
			chimp.start();
		}

		// escape 키로 창 닫기
		class ExitWarningListener extends KeyAdapter {
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
					wResult.setVisible(false);
					chimp.stop();
				}
			}
		}
	}

	class GameOver extends JFrame {
		int score;
		ImageIcon gameover = new ImageIcon(getClass().getClassLoader().getResource("Images/gameover1fix.png"));
		Music gameovermusic = new Music("gameover.mp3", false);
		Music scream = new Music("AfricanAmericanScream.mp3", false);
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
			setSize(1280, 720);
			setVisible(true);
			setLocationRelativeTo(null);
		}

		void setOverComp() {
			opnl = new JPanel[3];
			olbl = new JLabel[3];
			obtn = new JButton[3];

			for (int i = 0; i < 3; i++) {
				opnl[i] = new JPanel();
				obtn[i] = new JButton();
				obtn[i].setPreferredSize(new Dimension(300, 180));
				obtn[i].setFont(new Font("맑은고딕", Font.BOLD, 30));
				if (i < 2) {
					olbl[i] = new JLabel();
				}
			}
			olbl[0].setIcon(gameover);

			olbl[1].setText("<html>당신은 처형될 것입니다.<br/>당신의 점수: " + Integer.toString(score * 15) + "</html>");
			olbl[1].setFont(new Font("궁서체", Font.BOLD, 60));
			olbl[1].setForeground(Color.WHITE);

			obtn[0].setText("재시작");
			obtn[1].setText("종료");
			obtn[2].setText("사용된 라이선스");
		}

		void addOverComp() {
			add(opnl[0], BorderLayout.NORTH);
			add(opnl[1], BorderLayout.CENTER);
			add(opnl[2], BorderLayout.SOUTH);

			// 문제 설정
			opnl[0].add(olbl[0]);
			opnl[0].setBackground(Color.BLACK);
			// 사진 설정
			opnl[1].add(olbl[1]);
			opnl[1].setBackground(Color.BLACK);
			// 버튼 설정

			opnl[2].add(obtn[0], FlowLayout.LEFT);
			opnl[2].add(obtn[1], FlowLayout.CENTER);
			opnl[2].add(obtn[2], FlowLayout.RIGHT);
			opnl[2].setBackground(Color.BLACK);
		}

		void addOverEvent() {
			obtn[0].addActionListener(new RestartListener());
			obtn[1].addActionListener(new ExitGameListener());
			obtn[2].addActionListener(new OpenSourceListener());
		}

		class RestartListener implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == obtn[0]) {
					setVisible(false);
					gameovermusic.stop();
					scream.stop();
					new First();
				}
			}
		}

		class ExitGameListener implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == obtn[1]) {
					gameovermusic.stop();
					scream.stop();
					System.exit(0);
				}
			}
		}

		class OpenSourceListener implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == obtn[2]) {
					new OSL();
				}
			}
		}

	}

	class OSL extends JFrame {
		JTextArea oslta = new JTextArea("JLayer 1.0.1\r\n"
				+ "--------------------------------------------------------------------------\r\n" + "\r\n"
				+ "GNU LESSER GENERAL PUBLIC LICENSE\r\n" + "Version 3, 29 June 2007\r\n" + "\r\n"
				+ "Copyright (C) 2007 Free Software Foundation, Inc. <http://fsf.org/>\r\n" + "\r\n"
				+ "Everyone is permitted to copy and distribute verbatim copies of this license document, but changing it is not allowed.\r\n"
				+ "\r\n"
				+ "This version of the GNU Lesser General Public License incorporates the terms and conditions of version 3 of the GNU General Public License, supplemented by the additional permissions listed below.\r\n"
				+ "\r\n" + "0. Additional Definitions.\r\n" + "\r\n"
				+ "As used herein, “this License” refers to version 3 of the GNU Lesser General Public License, and the “GNU GPL” refers to version 3 of the GNU General Public License.\r\n"
				+ "\r\n"
				+ "“The Library” refers to a covered work governed by this License, other than an Application or a Combined Work as defined below.\r\n"
				+ "\r\n"
				+ "An “Application” is any work that makes use of an interface provided by the Library, but which is not otherwise based on the Library. Defining a subclass of a class defined by the Library is deemed a mode of using an interface provided by the Library.\r\n"
				+ "\r\n"
				+ "A “Combined Work” is a work produced by combining or linking an Application with the Library. The particular version of the Library with which the Combined Work was made is also called the “Linked Version”.\r\n"
				+ "\r\n"
				+ "The “Minimal Corresponding Source” for a Combined Work means the Corresponding Source for the Combined Work, excluding any source code for portions of the Combined Work that, considered in isolation, are based on the Application, and not on the Linked Version.\r\n"
				+ "\r\n"
				+ "The “Corresponding Application Code” for a Combined Work means the object code and/or source code for the Application, including any data and utility programs needed for reproducing the Combined Work from the Application, but excluding the System Libraries of the Combined Work.\r\n"
				+ "\r\n" + "1. Exception to Section 3 of the GNU GPL.\r\n" + "\r\n"
				+ "You may convey a covered work under sections 3 and 4 of this License without being bound by section 3 of the GNU GPL.\r\n"
				+ "\r\n" + "2. Conveying Modified Versions.\r\n" + "\r\n"
				+ "If you modify a copy of the Library, and, in your modifications, a facility refers to a function or data to be supplied by an Application that uses the facility (other than as an argument passed when the facility is invoked), then you may convey a copy of the modified version:\r\n"
				+ "\r\n"
				+ "a) under this License, provided that you make a good faith effort to ensure that, in the event an Application does not supply the function or data, the facility still operates, and performs whatever part of its purpose remains meaningful, or\r\n"
				+ "b) under the GNU GPL, with none of the additional permissions of this License applicable to that copy.\r\n"
				+ "3. Object Code Incorporating Material from Library Header Files.\r\n" + "\r\n"
				+ "The object code form of an Application may incorporate material from a header file that is part of the Library. You may convey such object code under terms of your choice, provided that, if the incorporated material is not limited to numerical parameters, data structure layouts and accessors, or small macros, inline functions and templates (ten or fewer lines in length), you do both of the following:\r\n"
				+ "\r\n"
				+ "a) Give prominent notice with each copy of the object code that the Library is used in it and that the Library and its use are covered by this License.\r\n"
				+ "b) Accompany the object code with a copy of the GNU GPL and this license document.\r\n"
				+ "4. Combined Works.\r\n" + "\r\n"
				+ "You may convey a Combined Work under terms of your choice that, taken together, effectively do not restrict modification of the portions of the Library contained in the Combined Work and reverse engineering for debugging such modifications, if you also do each of the following:\r\n"
				+ "\r\n"
				+ "a) Give prominent notice with each copy of the Combined Work that the Library is used in it and that the Library and its use are covered by this License.\r\n"
				+ "b) Accompany the Combined Work with a copy of the GNU GPL and this license document.\r\n"
				+ "c) For a Combined Work that displays copyright notices during execution, include the copyright notice for the Library among these notices, as well as a reference directing the user to the copies of the GNU GPL and this license document.\r\n"
				+ "d) Do one of the following:\r\n"
				+ "0) Convey the Minimal Corresponding Source under the terms of this License, and the Corresponding Application Code in a form suitable for, and under terms that permit, the user to recombine or relink the Application with a modified version of the Linked Version to produce a modified Combined Work, in the manner specified by section 6 of the GNU GPL for conveying Corresponding Source.\r\n"
				+ "1) Use a suitable shared library mechanism for linking with the Library. A suitable mechanism is one that (a) uses at run time a copy of the Library already present on the user's computer system, and (b) will operate properly with a modified version of the Library that is interface-compatible with the Linked Version.\r\n"
				+ "e) Provide Installation Information, but only if you would otherwise be required to provide such information under section 6 of the GNU GPL, and only to the extent that such information is necessary to install and execute a modified version of the Combined Work produced by recombining or relinking the Application with a modified version of the Linked Version. (If you use option 4d0, the Installation Information must accompany the Minimal Corresponding Source and Corresponding Application Code. If you use option 4d1, you must provide the Installation Information in the manner specified by section 6 of the GNU GPL for conveying Corresponding Source.)\r\n"
				+ "5. Combined Libraries.\r\n" + "\r\n"
				+ "You may place library facilities that are a work based on the Library side by side in a single library together with other library facilities that are not Applications and are not covered by this License, and convey such a combined library under terms of your choice, if you do both of the following:\r\n"
				+ "\r\n"
				+ "a) Accompany the combined library with a copy of the same work based on the Library, uncombined with any other library facilities, conveyed under the terms of this License.\r\n"
				+ "b) Give prominent notice with the combined library that part of it is a work based on the Library, and explaining where to find the accompanying uncombined form of the same work.\r\n"
				+ "6. Revised Versions of the GNU Lesser General Public License.\r\n" + "\r\n"
				+ "The Free Software Foundation may publish revised and/or new versions of the GNU Lesser General Public License from time to time. Such new versions will be similar in spirit to the present version, but may differ in detail to address new problems or concerns.\r\n"
				+ "\r\n"
				+ "Each version is given a distinguishing version number. If the Library as you received it specifies that a certain numbered version of the GNU Lesser General Public License “or any later version” applies to it, you have the option of following the terms and conditions either of that published version or of any later version published by the Free Software Foundation. If the Library as you received it does not specify a version number of the GNU Lesser General Public License, you may choose any version of the GNU Lesser General Public License ever published by the Free Software Foundation.\r\n"
				+ "\r\n"
				+ "If the Library as you received it specifies that a proxy can decide whether future versions of the GNU Lesser General Public License shall apply, that proxy's public statement of acceptance of any version is permanent authorization for you to choose that version for the Library.",
				30, 100);
		JScrollPane oslsp = new JScrollPane(oslta);
		JPanel oslpnl = new JPanel();

		OSL() {
			super("Open Source License");

			// setOSLComp();
			addOSLComp();

			setSize(1280, 720);
			setVisible(true);
			setLocationRelativeTo(null);
		}

//		void setOSLComp() {
//			
//		}
		void addOSLComp() {
			add(oslpnl, BorderLayout.CENTER);
			oslpnl.add(oslsp);

		}
	}

	public static void main(String[] args) {
		new scg();

	}

}
