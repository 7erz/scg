package ossproject;
import javax.swing.*;
import java.awt.font.*;
import java.awt.*;
import java.awt.event.*;

public class scg extends JFrame{
Container c = getContentPane();
Font Bold = new Font("±Ã¼­Ã¼",Font.BOLD,30);
	public scg() {
		super("scg");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		JPanel warnpnl = new JPanel();
		JLabel warnheadlbl = new JLabel();
		JLabel warnsublbl = new JLabel();
		
		warnpnl.setBackground(Color.BLACK);
		warnheadlbl.setText("°æ°í");
		warnheadlbl.setFont(Bold);
		
		
		c.add(warnpnl);
		c.add(warnheadlbl);
		
		setSize(1280,720);
		setVisible(true);
	}
	//https://m.blog.naver.com/10hsb04/221607286384
	//https://dinae.tistory.com/27
	class First extends JFrame{
		First(){
		
		
		}
	}
	
	public static void main(String[] args) {
		new scg();

	}

}
