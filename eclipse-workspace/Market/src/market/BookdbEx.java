package market;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

class BookModel {
   String id;
   String title;
   String publisher;
   int price;
   BookModel(){};
   BookModel(String id, String title, String publisher, int price){
      this.id=id;
      this.title=title;
      this.publisher=publisher;
      this.price=price;
   }
}

class BookdbView extends JFrame{
JPanel[] pnl=new JPanel[2];
   JLabel[] label=new JLabel[4];
   JTextField[] tf=new JTextField[4];
   JButton[] button=new JButton[4];
   JTextArea ta=new JTextArea();
   private static final long serialVersionUID = 1L;
   JTable table;
   DefaultTableModel model;
   
   BookdbView() {
      String[] lbl_tf= {"ID","Ttile","Publisher","Price"};
      String[] lbl_button= {"추가","조회","수정","삭제"};
      Container c=getContentPane();
      pnl[0]=new JPanel();
      pnl[1]=new JPanel();
      for(int i=0;i<4;i++) {
         tf[i]=new JTextField(9);
         label[i]=new JLabel(lbl_tf[i]);
         button[i]=new JButton(lbl_button[i]);
      }
      String[] columnNames = {"ID", "책제목","출판사","가격"}; // JTable 설정
      model = new DefaultTableModel(columnNames,0) {
      private static final long serialVersionUID = 1L;
         public boolean isCellEditable(int row, int column) { 
            return false;
         }
      };
      table = new JTable(model);
      
      for(int i=0;i<4;i++) {
         pnl[0].add(label[i]);
         pnl[0].add(tf[i]);
         pnl[1].add(button[i]);
      }
      c.add(pnl[0],BorderLayout.NORTH);
      c.add(new JScrollPane(table),BorderLayout.CENTER);
      c.add(pnl[1],BorderLayout.SOUTH);
      setTitle("서적 관리");
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setSize(600,400);
      setVisible(true);
   }
} 

public class BookdbEx {
   BookdbView v=new BookdbView();
   ActionHandler handler=new ActionHandler();
   Connection con=null;
   Statement stmt=null;
   ResultSet rs=null;
   PreparedStatement ps = null;
   
   public BookdbEx() {
      v.button[0].addActionListener(handler);
      v.button[1].addActionListener(handler);
      v.button[2].addActionListener(handler);
      v.button[3].addActionListener(handler);
   }

   class ActionHandler implements ActionListener {
      public void actionPerformed(ActionEvent e) {
         makeConnection();
         if(e.getSource()==v.button[0]) {
            try {
                  stmt = con.createStatement();
                  int st = stmt.executeUpdate(
                          "INSERT INTO book values ('" + v.tf[0].getText() + "', '" + v.tf[1].getText() + "', '" + v.tf[2].getText() + "', '" + v.tf[3].getText() + "')");
              } catch (SQLException e1) {
                  e1.printStackTrace();
              }
         }
         
         else if(e.getSource()==v.button[1]) {
            String sql="SELECT * FROM book";
            String[] row=new String[4];
            v.model.setNumRows(0); // JTable 초기화
            try {
               System.out.println(sql+"\n");
               rs=stmt.executeQuery(sql);
               while(rs.next()) {
                  row[0]=rs.getString("id")+"\t";
                  row[1]=rs.getString("title")+"\t";
                  row[2]=rs.getString("publisher")+"\t";
                  row[3]=rs.getString("price")+"\n";
                  v.model.addRow(row); // 추가
               }
            } catch (SQLException e1) {
               e1.printStackTrace();
            }
         }
         else if(e.getSource()==v.button[2]) {
            try {
                  stmt = con.createStatement();
                  int st = stmt.executeUpdate("UPDATE book set id = '" + v.tf[0].getText() + "' where title = '" + v.tf[1].getText() +"';");
              } catch (SQLException e1) {
                  e1.printStackTrace();
              }
            
         }
         else if(e.getSource()==v.button[3]) {
            try {
                  stmt = con.createStatement();
                  int st = stmt.executeUpdate("DELETE from book where id = '" + v.tf[0].getText() + "';" );
              } catch (SQLException e1) {
                  e1.printStackTrace();
              }
         }
         disConnection();      
      }
   }

   public Connection makeConnection(){ //드라이브 연결
      String url="jdbc:mysql://localhost:3306/book_db?serverTimezone=Asia/Seoul";
      String id="root";
      String password="1234";
      try{
         Class.forName("com.mysql.cj.jdbc.Driver");
         System.out.println("드라이브 적재 성공");
         con=DriverManager.getConnection(url, id, password);
         stmt=con.createStatement();
         System.out.println("데이터베이스 연결 성공");
      }catch(ClassNotFoundException e){
         System.out.println("드라이버를 찾을 수 없습니다");
      }catch(SQLException e){
         System.out.println("연결에 실패하였습니다");
      }
      return con;
   }

   public void disConnection() {
      try{
         rs.close();
         stmt.close();
         con.close();
      }catch(SQLException e){System.out.println(e.getMessage());}
   }

   public static void main(String[] args) {
      new BookdbEx();
   }

}