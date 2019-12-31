package MarketPos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class DB_administrator extends DBconnector{ // 관리자 쿼리문 수행 부분
   
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
   
   
   //관리자가 맞지 확인하는 메소드
   public boolean checkPw(String inputPW) {
      try {

         String quary = "select * from administrator";             

         pstm = conn.prepareStatement(quary);
         rs = pstm.executeQuery();


         while (rs.next()) { //다음 방문할 값이 존재하는 동안 루프
            String pw = rs.getString("password"); // getInt(1)
            if(pw.equals(inputPW)) {
               System.out.println("관리자모드 로그인 성공 ");
               return true;
            }
         }
   
         pstm.close();

      } catch (SQLException se) {
         System.err.println("SQL 수행중 에러가 발생했습니다." + se.getMessage());
      } finally {

      }
      return false;
   }
   
   //물품을 추가하는 메소드 
   public void addProduct(String barcode, String pname, String ptype, String pamount, String expiration,int price) {
      try {         
         String sql = " insert into product values('"+barcode+"','"+pname+"','" +ptype+"','"+ pamount +"','"+expiration +"' ,"+price+ ")" ;    
         Statement stmt = conn.createStatement(); //커넥션에서 statement 객체 생성
           stmt.executeUpdate(sql);
           stmt.close();

      } catch (SQLException se) {
         System.err.println("SQL 수행중 에러가 발생했습니다." + se.getMessage());
      } finally {
      }      

//      System.out.println( barcode + "\t" +pname+ "\t" +ptype+ "\t" +pamount + "\t" +expiration + "\t" + price + "제품 추가 완료");
   }
   
   
    //상품 조회를 출력하는 메소드   
   public ArrayList<String> pdisplay() {
      System.out.println("상품 조회 출력");
      ArrayList<String> data = new ArrayList<String>(); 
      data.add("바코드     제품이름      제품종류    수량   유통기한       가격\r\n");
      try {
         String sql =  "select * from product";
         pstm = conn.prepareStatement(sql);
         rs = pstm.executeQuery();


         while (rs.next()) { //다음 방문할 값이 존재하는 동안 루프
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
         System.err.println("SQL 수행중 에러가 발생했습니다." + se.getMessage());
      } finally {

      }
      return data;

   }
   
   
    //상품을 검색하는 메소드  
      public String psearch(String key) {
         //System.out.println("상품 검색 출력");
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
            popup.showMessageDialog(null, "찾으시는 제품이 없습니다.");
         }   
         return line;
        
      }
      
      
      //고객 조회를 출력하는 메소드  
      public ArrayList cdisplay() {
         System.out.println("고객 조회 출력");
            ArrayList<String> data = new ArrayList<String>(); 
            data.add("이름      전화번호      회원등급   적립금   나이\r\n");
        // System.out.println("이름   전화번호   회원등급   적립금   나이");
         try {
            String sql = "select * from customer";
            pstm = conn.prepareStatement(sql);
            rs = pstm.executeQuery();
            while (rs.next()) { //다음 방문할 값이 존재하는 동안 루프

               String cname = rs.getString("cname");
               String phone = rs.getString("phone");
               String grade = rs.getString("grade");
               int saving = rs.getInt("saving");   
               int age = rs.getInt("age");               
               int len_cname = cname.length()-10;
               data.add(String.format("%"+len_cname+"s",cname) + String.format("%-15s",phone) + String.format("%-10s",grade)+String.format("%-9s",saving)+String.format("%s",age));
               
            }

         } catch (SQLException se) {
            System.err.println("SQL 수행중 에러가 발생했습니다." + se.getMessage());
         } finally {

         }
         return data;
      }
      
      
    //고객을 검색하는 메소드  
      public String csearch(String key) {
         //System.out.println("고객 검색 출력");
         String line ="no";
         try {
         
            String sql = "select * from customer where phone = '"+key+"'"; // excuteQuery 메소드를 이용하여 select 문을 실행
            pstm = conn.prepareStatement(sql);
            rs = pstm.executeQuery();
            rs.next();      
            //System.out.println("고객을 찾았습니다");
           // System.out.println("이름   전화번호   회원등급   적립금   나이");         
            String cname = rs.getString("cname");
            String phone = rs.getString("phone");
            String grade = rs.getString("grade");
            int saving = rs.getInt("saving");
            int age = rs.getInt("age");
            line =cname+"    "+phone+"     "+grade+"    "+saving+"     "+age;
            System.out.println(line);


         } catch (SQLException se) {
            JOptionPane popup =new JOptionPane();
            popup.showMessageDialog(null, "찾으시는 고객이 없습니다.");
         } finally {

         }
         return line;
      }

      
      //장부를 조회하는 메소드  
      public ArrayList bdisplay() {
         
         System.out.println("장부 출력");
         ArrayList<String> data = new ArrayList<String>(); 
         data.add("바코드   구매개수   제품이름   결제수단   가격\r\n");
         //System.out.println("바코드   구매개수   제품이름   결제수단   가격");
         try {
          

           String sql = "select * from books"; // excuteQuery 메소드를 이용하여 select 문을 실행
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
            System.err.println("SQL 수행중 에러가 발생했습니다." + se.getMessage());
         }  finally {

         }
         return data;
      }
      

    //제품개수를 추가하는 메소드 
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
                Statement stmt = conn.createStatement(); //커넥션에서 statement 객체 생성
                stmt.executeUpdate(sql);
                stmt.close();
               JOptionPane popup =new JOptionPane();
            popup.showMessageDialog(null, "재고가 조정되었습니다!");
            }
            else {
               JOptionPane popup =new JOptionPane();
               popup.showMessageDialog(null, "찾으시는 제품이 없습니다.");
            }
            
            

         } catch (SQLException se) {
            System.err.println("SQL 수행중 에러가 발생했습니다." + se.getMessage());
         } finally {

         }      

      }
      
      //바코드로 해당상품이 있는지 검색
      public boolean checkBarcode(String barcode) {
            try {
              
              
               String sql = "select * from product where barcode = '"+barcode+"'"; // item 으로 바코드 확인하고 
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
               System.err.println("SQL 수행중 에러가 발생했습니다." + se.getMessage());
            } finally {
            }
            return false;
         }

}