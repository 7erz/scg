package ossproject;
import javax.swing.*;
import java.awt.font.*;
import java.io.File;
import java.util.Random;
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
	class First extends JFrame{
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
		String[] quiz = new String[10]; //��������Ʈ �����ؼ� �ٽ� �ø���
		String[] c1 = new String[10];	//1�� ����
		String[] c2 = new String[10];	//2�� ����
		String[] c3 = new String[10];	//3�� ����(�Ҽ� ������ �߰�)
		int ran = rd.nextInt(quiz.length);
		int answer = 0;
		int score = 0;
		int[] ans = new int[10]; //������ ����
		//JTextField name = new JTextField(5);
		ImageIcon lobbyimg = new ImageIcon("C:/Users/leesn/git/scg/eclipse-workspace/scg project/Images/logotest1.jpg");
		ImageIcon left = new ImageIcon("C:/Users/leesn/git/scg/eclipse-workspace/scg project/Images/loading.gif");
		ImageIcon right = new ImageIcon("C:/Users/leesn/git/scg/eclipse-workspace/scg project/Images/loading.gif");
		//LGPL���̼��� JLayer
		Music lobby = new Music("LOBBY.mp3", true);
		Music ingame = new Music("ingame.mp3", true);
		First(){
			super("����");
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			Container c = getContentPane();
			//�� �ڵ�� �ǹ� ���°�(�гο��� ���� �޾� ó���ϸ� �Ǳ� ����)
			//c.setBackground(red);
			setUndecorated(true);
			//���� �κ� ����
			setComp();
			addComp();
			addEvent();

			//LGPL ���̼���
			lobby.start();

			setSize(1280,720);
			setVisible(true);
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
				}
				
			}
			//�󺧼���(0�� �̸� ĭ �߰�,1���� �ΰ� �߰�)
			flbl[0].setText("�̸�");
			flbl[1].setIcon(lobbyimg);
			flbl[2].setIcon(left);
			flbl[3].setIcon(right);
			//��ư ����
			fbtn[0].setText("����");
			fbtn[1].setText("����");

			
		}
		void addComp() {
			//�г� ��ġ
			add(fpnl[0], BorderLayout.NORTH);
			add(fpnl[1], BorderLayout.CENTER);
			add(fpnl[2], BorderLayout.SOUTH);
			add(fpnl[3], BorderLayout.WEST);
			add(fpnl[4], BorderLayout.EAST);
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
			}
			glbl[0].setText("����: "+ quiz[ran]);
			glbl[1].setIcon(lobbyimg);
			//����� �ؽ�Ʈ�� ��ư������ �ٵ����� �ð������� ���� �־��
			gbtn[0].setText("1��: " + c1[ran]);
			gbtn[1].setText("2��: " + c2[ran]);
			gbtn[2].setText("3��: " + c3[ran]);
			answer = ans[ran]; //��ġ Ȯ�� �ٶ�
		}
		
		void addGameComp() {
			//0�� ����, 1�� ����, 2�� ������
			add(gpnl[0], BorderLayout.NORTH);
			add(gpnl[1], BorderLayout.CENTER);
			add(gpnl[2], BorderLayout.SOUTH);
			
			//���� ����
			gpnl[0].add(glbl[0]);
			gpnl[0].setBackground(Color.red);
			//���� ����
			gpnl[1].add(glbl[1]);
			gpnl[1].setBackground(Color.red);
			//��ư ����
			gpnl[2].add(gbtn[0]);
			gpnl[2].add(gbtn[1]);
			gpnl[2].add(gbtn[2]);
			gpnl[2].setBackground(Color.red);
		}
	}
	//����,�г�Ƽ�� ��� ������
	class PenaltyImage extends JFrame{
		JFrame penalty = new JFrame();
		public PenaltyImage() {
			//������ ���ϱ�
			Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
			penalty.setUndecorated(true);
			ImageIcon image = new ImageIcon("C:/Users/leesn/git/scg/eclipse-workspace/scg project/Images/test.jpg");
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
		}
		//escape Ű�� â �ݱ�
		class ExitWarningListener extends KeyAdapter {
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ESCAPE) {
					penalty.setVisible(false);
				}
			}
		}
	}
	
	
	public static void main(String[] args) {
		new scg();
		
	}



}
