package ossproject;
import javax.swing.*;
import java.awt.font.*;
import java.awt.*;
import java.awt.event.*;

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
		warnsublbl.setText("<html>이것은 ???인 내용을 포함하고 있습니다.<br/>특정 성향에 따라 불쾌할수 있으므로 주의 바립니다.</html>");
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
	
	class First extends JFrame{
		JPanel backpnl;
		JPanel fpnl[];
		JLabel flbl[];
		JButton fbtn[];
		JTextField name;
		//JTextField name = new JTextField(5);
		ImageIcon loading = new ImageIcon("C:/Users/leesn/git/scg/eclipse-workspace/scg project/Images/loading2.gif");
		
		First(){
			super("시작");
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			Container c = getContentPane();
			
			backpnl = new JPanel();
			//backpnl.setBackground(Color.red);
			
			c.setBackground(red);
			
			setComp();
			addComp();
			addEvent();
			
			c.add(backpnl);
			setSize(1280,720);
			setVisible(true);
		}
		void setComp() {
			name = new JTextField(7);
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
			flbl[0].setText("이름");
			flbl[1].setIcon(loading);
			fbtn[0].setText("시작");
			fbtn[1].setText("종료");
			
		}
		void addComp() {
			add(fpnl[0], BorderLayout.NORTH);
			add(fpnl[1], BorderLayout.CENTER);
			add(fpnl[2], BorderLayout.SOUTH);
			
			fpnl[0].add(flbl[1]);
			fpnl[0].setBackground(Color.RED);
			
			fpnl[1].add(flbl[0]);
			fpnl[1].add(name);
			fpnl[1].setBackground(Color.RED);
			
			fpnl[2].add(fbtn[0]);
			fpnl[2].add(fbtn[1]);
			
		}
		
		void addEvent() {
			
			fbtn[0].addActionListener(new StartHandler());
			fbtn[1].addActionListener(new CantcancelHandler());
		}
		
		class StartHandler implements ActionListener{
			
			public void actionPerformed(ActionEvent e) {
				if(e.getSource()==fbtn[0]) {
					for(int i = 0; i < 3; i++) {
						fpnl[i].setVisible(false);
					}
					
				}
			}
		}
		class CantcancelHandler implements ActionListener{
			
			public void actionPerformed(ActionEvent e) {
				if(e.getSource()==fbtn[1]) {
					JOptionPane.showMessageDialog(null, "Bad 이벤트 추가 할것");
				}
			}
		}
	}
	
	
	
	
	
	public static void main(String[] args) {
		new scg();
		
	}



}
