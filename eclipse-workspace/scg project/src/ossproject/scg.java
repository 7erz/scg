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
		JPanel pnl = new JPanel();
		JButton start = new JButton();
		JButton exit = new JButton();
		
		private ImageIcon loading = new ImageIcon("C:/Users/leesn/git/scg/eclipse-workspace/scg project/Images/loading.gif");
		
		First(){
			super("시작");
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			Container c = getContentPane();
			
			pnl.setBackground(Color.red);
			
			
			c.add(pnl);
			setSize(1280,720);
			setVisible(true);
			
		
		
		}
	}
	
	public static void main(String[] args) {
		new scg();
		
	}



}
