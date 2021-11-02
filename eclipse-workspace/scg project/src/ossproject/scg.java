package ossproject;
import javax.swing.*;
import java.awt.font.*;
import java.awt.*;
import java.awt.event.*;
//9월 30일 시작
//자바를 알지 못해 난잡한 코드들로 처리하느라 정리가 불가능한 상황에 왔음
// 그래서 독학과 GUI 수업을 통해 First란 창을 새로 띄워서 10월 말 다시 코드를 작성했음
// 일단 시간이 너무 늦은 관계로 밈에 관련된 간단한 선택형 게임을 만들 예정
// 첫번쨰는 간단한 틀과 화면 전환
// 두번쨰는 퀴즈들의 내용들과 처리
// 세번쨰로는 음악과 사운드 추가
// 마지막으로 기말 전까지 세세한것을 다듬을 것
public class scg extends JFrame implements ActionListener{
Container c = getContentPane();
Font Bold = new Font("궁서체",Font.BOLD,60);
Color red = new Color(20,20,20);
Font Plane = new Font("굴림체",Font.PLAIN,30);
JButton btok = new JButton();
JButton btno = new JButton();
JPanel FirstPage = new JPanel();
GridLayout warngird = new GridLayout(2,2,10,10);
GridLayout btngird = new GridLayout(2,2,10,10);
FlowLayout flow = new FlowLayout(10, 400, 10);
JPanel btnpnl = new JPanel();
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
		warnheadlbl.setText("경고");
		warnheadlbl.setFont(Bold);
		warnheadlbl.setForeground(Color.RED);
		warnheadlbl.setHorizontalAlignment(JLabel.CENTER);
		//warnsublbl.setText("<html>이것은 ???인 내용을 포함하고 있습니다.<br/>특정 성향에 따라 불쾌할수 있으므로 주의 바립니다.</html>");
		warnsublbl.setText("<html>최근에 시작해서 아무것도 없습니다.<br/>코드 보고 저급하다고 해도 저는 모릅니다.</html>");
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
	//10월 28일 시작
	class First extends JFrame{
		//상단창 프레임 제거 (예정)
		Frame frame = new Frame();
		JPanel fpnl[];
		JLabel flbl[];
		JButton fbtn[];
		JTextField nametxt;
		//JTextField name = new JTextField(5);
		ImageIcon loading = new ImageIcon("C:/Users/leesn/git/scg/eclipse-workspace/scg project/Images/loading2.gif");
		
		First(){
			super("시작");
			
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			Container c = getContentPane();
			//이 코드는 의미 없는것(패널에서 색을 받아 처리하면 되기 때문)
			//c.setBackground(red);
			
			setComp();
			addComp();
			addEvent();
			

			setSize(1280,720);
			setVisible(true);
		}
		void setComp() {
			nametxt = new JTextField(7);
			fpnl = new JPanel[3];
			flbl = new JLabel[3];
			fbtn = new JButton[2];
			for(int i = 0; i < 3; i++) {
				fpnl[i] = new JPanel();
				flbl[i] = new JLabel();
				if(i <2) {
					fbtn[i] = new JButton();
				}
				
			}
			//라벨설정(0은 이름 칸 추가,1에는 로고 추가)
			flbl[0].setText("이름");
			flbl[1].setIcon(loading);
			flbl[1].setHorizontalAlignment(WIDTH/2); 
			//버튼 설정
			fbtn[0].setText("시작");
			fbtn[1].setText("종료");
			
		}
		void addComp() {
			//패널 배치
			add(fpnl[0], BorderLayout.NORTH);
			add(fpnl[1], BorderLayout.CENTER);
			add(fpnl[2], BorderLayout.SOUTH);
			//로고라벨을 패널0에 추가
			fpnl[0].add(flbl[1]);
			fpnl[0].setBackground(Color.RED);
			//이름 칸을 패널1에 추가
			fpnl[1].add(new Label("성함"));
			fpnl[1].add(nametxt);
			fpnl[1].setBackground(Color.RED);
			//버튼 추가
			fpnl[2].add(fbtn[0]);
			fpnl[2].add(fbtn[1]);
			
		}
		
		void addEvent() {
			//버튼 리스너 추가
			fbtn[0].addActionListener(new StartHandler());
			fbtn[1].addActionListener(new CantcancelHandler());
		}
		
		class StartHandler implements ActionListener{
			//시작버튼, 화면 전환 필요함
			public void actionPerformed(ActionEvent e) {
				if(e.getSource()==fbtn[0]) {
					for(int i = 0; i < 3; i++) {
						fpnl[i].setVisible(false);
					}
					
				}
			}
		}
		class CantcancelHandler implements ActionListener{
			//종료 안되는 종료 버튼 ( 패널티 부여 )
			public void actionPerformed(ActionEvent e) {
				if(e.getSource()==fbtn[1]) {
					JOptionPane.showMessageDialog(null, nametxt.getText()+"님 종료는 못하십니다.");
				}
			}
		}
	}
	
	
	
	
	
	public static void main(String[] args) {
		new scg();
		
	}



}
