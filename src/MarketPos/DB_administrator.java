package MarketPos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class DB_administrator extends DBconnector{ // ������ ������ ���� �κ�
   
   Connection conn = super.getConnection();
   PreparedStatement pstm = null;
   ResultSet rs = null;
   
   private static DB_administrator db_admin = new DB_administrator();
   public static DB_administrator getInstacne() {
       if(db_admin == null) {
          db_admin = new DB_administrator();
         }
         
         return db_admin;
      
   }
   
   
   //�����ڰ� ���� Ȯ���ϴ� �޼ҵ�
   public boolean checkPw(String inputPW) {
      try {

         String quary = "select * from administrator";             

         pstm = conn.prepareStatement(quary);
         rs = pstm.executeQuery();


         while (rs.next()) { //���� �湮�� ���� �����ϴ� ���� ����
            String pw = rs.getString("password"); // getInt(1)
            if(pw.equals(inputPW)) {
               System.out.println("�����ڸ�� �α��� ���� ");
               return true;
            }
         }
   
         pstm.close();

      } catch (SQLException se) {
         System.err.println("SQL ������ ������ �߻��߽��ϴ�." + se.getMessage());
      } finally {

      }
      return false;
   }
   
   //��ǰ�� �߰��ϴ� �޼ҵ� 
   public void addProduct(String barcode, String pname, String ptype, String pamount, String expiration,int price) {
      try {         
         String sql = " insert into product values('"+barcode+"','"+pname+"','" +ptype+"','"+ pamount +"','"+expiration +"' ,"+price+ ")" ;    
         Statement stmt = conn.createStatement(); //Ŀ�ؼǿ��� statement ��ü ����
           stmt.executeUpdate(sql);
           stmt.close();

      } catch (SQLException se) {
         System.err.println("SQL ������ ������ �߻��߽��ϴ�." + se.getMessage());
      } finally {
      }      

//      System.out.println( barcode + "\t" +pname+ "\t" +ptype+ "\t" +pamount + "\t" +expiration + "\t" + price + "��ǰ �߰� �Ϸ�");
   }
   
   
    //��ǰ ��ȸ�� ����ϴ� �޼ҵ�   
   public ArrayList<String> pdisplay() {
      System.out.println("��ǰ ��ȸ ���");
      ArrayList<String> data = new ArrayList<String>(); 
      data.add("���ڵ�     ��ǰ�̸�      ��ǰ����    ����   �������       ����\r\n");
      try {
         String sql =  "select * from product";
         pstm = conn.prepareStatement(sql);
         rs = pstm.executeQuery();


         while (rs.next()) { //���� �湮�� ���� �����ϴ� ���� ����
            String barcode = rs.getString("barcode");
            String pname = rs.getString("pname");
            String ptype = rs.getString("ptype");
            String pamount = rs.getString("pamount");
            String expiration = rs.getString("expiration");
            int price = rs.getInt("price");   

            int len_pname = pname.length()-14;
            int len_ptype = ptype.length()-12;

         
            data.add(String.format("%-11s",barcode) + String.format("%"+len_pname+"s",pname) + String.format("%"+len_ptype+"s",ptype)+String.format("%-7s",pamount)+String.format("%-15s",expiration)+String.format("%s",price));
         }
      
      } catch (SQLException se) {
         System.err.println("SQL ������ ������ �߻��߽��ϴ�." + se.getMessage());
      } finally {

      }
      return data;

   }
   
   
    //��ǰ�� �˻��ϴ� �޼ҵ�  
      public String psearch(String key) {
         //System.out.println("��ǰ �˻� ���");
         String line ="no";
         try {
           String sql = "select * from product where barcode = '"+key+"'";
           pstm = conn.prepareStatement(sql);
          rs = pstm.executeQuery();
            rs.next();      

            String barcode = rs.getString("barcode");
            String pname = rs.getString("pname");
            String ptype = rs.getString("ptype");
            String pamount = rs.getString("pamount");
            String expiration = rs.getString("expiration");
            int price = rs.getInt("price");   
            line =barcode+"   "+pname+"    "+ptype+"    "+pamount+"    "+expiration+"    "+price;
            System.out.println(line);
      

         } catch (SQLException se) {
            JOptionPane popup =new JOptionPane();
            popup.showMessageDialog(null, "ã���ô� ��ǰ�� �����ϴ�.");
         }   
         return line;
        
      }
      
      
      //�� ��ȸ�� ����ϴ� �޼ҵ�  
      public ArrayList cdisplay() {
         System.out.println("�� ��ȸ ���");
            ArrayList<String> data = new ArrayList<String>(); 
            data.add("�̸�      ��ȭ��ȣ      ȸ�����   ������   ����\r\n");
        // System.out.println("�̸�   ��ȭ��ȣ   ȸ�����   ������   ����");
         try {
            String sql = "select * from customer";
            pstm = conn.prepareStatement(sql);
            rs = pstm.executeQuery();
            while (rs.next()) { //���� �湮�� ���� �����ϴ� ���� ����

               String cname = rs.getString("cname");
               String phone = rs.getString("phone");
               String grade = rs.getString("grade");
               int saving = rs.getInt("saving");   
               int age = rs.getInt("age");               
               int len_cname = cname.length()-10;
               data.add(String.format("%"+len_cname+"s",cname) + String.format("%-15s",phone) + String.format("%-10s",grade)+String.format("%-9s",saving)+String.format("%s",age));
               
            }

         } catch (SQLException se) {
            System.err.println("SQL ������ ������ �߻��߽��ϴ�." + se.getMessage());
         } finally {

         }
         return data;
      }
      
      
    //���� �˻��ϴ� �޼ҵ�  
      public String csearch(String key) {
         //System.out.println("�� �˻� ���");
         String line ="no";
         try {
         
            String sql = "select * from customer where phone = '"+key+"'"; // excuteQuery �޼ҵ带 �̿��Ͽ� select ���� ����
            pstm = conn.prepareStatement(sql);
            rs = pstm.executeQuery();
            rs.next();      
            //System.out.println("���� ã�ҽ��ϴ�");
           // System.out.println("�̸�   ��ȭ��ȣ   ȸ�����   ������   ����");         
            String cname = rs.getString("cname");
            String phone = rs.getString("phone");
            String grade = rs.getString("grade");
            int saving = rs.getInt("saving");
            int age = rs.getInt("age");
            line =cname+"    "+phone+"     "+grade+"    "+saving+"     "+age;
            System.out.println(line);


         } catch (SQLException se) {
            JOptionPane popup =new JOptionPane();
            popup.showMessageDialog(null, "ã���ô� ���� �����ϴ�.");
         } finally {

         }
         return line;
      }

      
      //��θ� ��ȸ�ϴ� �޼ҵ�  
      public ArrayList bdisplay() {
         
         System.out.println("��� ���");
         ArrayList<String> data = new ArrayList<String>(); 
         data.add("���ڵ�   ���Ű���   ��ǰ�̸�   ��������   ����\r\n");
         //System.out.println("���ڵ�   ���Ű���   ��ǰ�̸�   ��������   ����");
         try {
          

           String sql = "select * from books"; // excuteQuery �޼ҵ带 �̿��Ͽ� select ���� ����
           pstm = conn.prepareStatement(sql);
            rs = pstm.executeQuery();
           
            while(rs.next()) {                        
               String barcode = rs.getString("barcode");
               int amount = rs.getInt("amount");
               String pname = rs.getString("pname");
               String method = rs.getString("method");
               int price = rs.getInt("price");  
               int len_pname = pname.length() - 11;
               data.add(String.format("%-10s",barcode) + String.format("%-10s",amount) + String.format("%"+len_pname+"s",pname)+String.format("%-11s",method)+String.format("%s",price));

              

            }

         } catch (SQLException se) {
            System.err.println("SQL ������ ������ �߻��߽��ϴ�." + se.getMessage());
         }  finally {

         }
         return data;
      }
      

    //��ǰ������ �߰��ϴ� �޼ҵ� 
      public void update_pamount(String item,int count) {

         try {
           
            String sql = "SELECT barcode from PRODUCT  ";
            pstm = conn.prepareStatement(sql);
            rs = pstm.executeQuery();             
               boolean flag = false;
            while(rs.next()) {
               String tempBarcode = rs.getString("barcode");
               if(tempBarcode.equals(item)) {
                  flag = true;
                  break;
               }
            }
            if(flag == true) {
                sql = "UPDATE PRODUCT SET pamount = pamount +"+ count +" where barcode ='"+ item+"'"  ;
                Statement stmt = conn.createStatement(); //Ŀ�ؼǿ��� statement ��ü ����
                stmt.executeUpdate(sql);
                stmt.close();
               JOptionPane popup =new JOptionPane();
            popup.showMessageDialog(null, "��� �����Ǿ����ϴ�!");
            }
            else {
               JOptionPane popup =new JOptionPane();
               popup.showMessageDialog(null, "ã���ô� ��ǰ�� �����ϴ�.");
            }
            
            

         } catch (SQLException se) {
            System.err.println("SQL ������ ������ �߻��߽��ϴ�." + se.getMessage());
         } finally {

         }      

      }
      
      //���ڵ�� �ش��ǰ�� �ִ��� �˻�
      public boolean checkBarcode(String barcode) {
            try {
              
              
               String sql = "select * from product where barcode = '"+barcode+"'"; // item ���� ���ڵ� Ȯ���ϰ� 
               pstm = conn.prepareStatement(sql);
               rs = pstm.executeQuery();
               while(rs.next()) {         
                  String target = rs.getString("barcode");
                  if(target.equals(barcode)) {
                     return true;
                  }
               }
               rs.close();
               return false;
            } catch (SQLException se) {
               System.err.println("SQL ������ ������ �߻��߽��ϴ�." + se.getMessage());
            } finally {
            }
            return false;
         }

}