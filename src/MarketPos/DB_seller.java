package MarketPos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JOptionPane;

public class DB_seller extends DBconnector  {
	
	
	public static boolean added_all = false; //구매를 완료하였는지 판단하기위해
	public static boolean chkCustomer = false; // 회원으로 결제할 것인지 아닌지 판단하기위해
	public static String phone = null; 
	public static int fee = 0;
	public static boolean check = false;  //결제를 완료하였는지 판단하기위해
	public static ArrayList<String> ShoppingBasket_Barcode = new ArrayList<String>(); //바코드 받을 리스트
	public static ArrayList<Integer> ShoppingBasket_Amount = new ArrayList<Integer>(); // 구매양 받을 리스트
	public static ArrayList<String> ShoppingBasket_Pname  = new ArrayList<String>(); // 제품 명 받을 리스트
	public static ArrayList<Integer> ShoppingBasket_Price =  new ArrayList<Integer>();  //상품 가격	
 

	Connection conn = super.getConnection();
	PreparedStatement pstm = null;
	ResultSet rs = null;
	private static DB_seller db_sell = new DB_seller();
	   
	   
    static DB_seller getInstacne() {
      if(db_sell == null) {
         db_sell = new DB_seller();
      }

      return db_sell;

   }
	
	//회원인지 확인하는 메소드
	public boolean checkCustomer(String phone) {
		try {

			String sql = "select * from customer where phone = '"+phone+"'"; // item 으로 바코드 확인하고 
			pstm = conn.prepareStatement(sql);
			rs = pstm.executeQuery();

			while(rs.next()) {         
				String target = rs.getString("phone");
				if(target.equals(phone)) {
					return true;
				}
			}
			return false;
		} catch (SQLException se) {
			System.err.println("SQL 수행중 에러가 발생했습니다." + se.getMessage());
		} finally {
		}
		return false;
	}

	
	//회원을 등록하는 메소드 
	public void addCustomer(String cname, String phone, char grade, int saving,int age) {
		try {
			String sql = " insert into Customer values('"+cname+"','"+phone+"','"+ grade +"', "+ saving +" , "+age +  ")" ;    
			Statement stmt = conn.createStatement(); //커넥션에서 statement 객체 생성
			stmt.executeUpdate(sql);


			stmt.close();
		} catch (SQLException se) {
			System.err.println("SQL 수행중 에러가 발생했습니다. 이미 존재하는 회원입니다 다시 시도해주세요" + se.getMessage());
		} finally {
		}      
		System.out.println(cname + "/" + phone  + "/" + grade   + "/"
				+ saving  + "/" + age + "/ 회원 추가 완료");   

	}
	

	//구매가능한 상품인지 확인하는 메소드  판매자
	public int checkProduct(String item, int count) {
		try {

			String sql = "select * from product where barcode = '"+item+"'"; // item 으로 바코드 확인하고      
			pstm = conn.prepareStatement(sql);
			rs = pstm.executeQuery();
			while(rs.next()) {
				String pamount = rs.getString("pamount");
				String exp = rs.getString("expiration");
				int price = rs.getInt("price");

				exp = exp.substring(0, exp.lastIndexOf('-')+3 );

				if(Integer.parseInt(pamount)<count) {
					System.out.println("재고가 부족합니다.");
					JOptionPane popup =new JOptionPane();
					popup.showMessageDialog(null, "재고가 부족합니다.");
				}
				else if(!isValidExp(exp)) {
					System.out.println("유통기한이 지났습니다.");
					JOptionPane popup =new JOptionPane();
					popup.showMessageDialog(null, "유통기한이 지났습니다.");
				}
				else {
					return price;
				}  
			}

		} catch (SQLException se) {
			System.err.println("SQL 수행중 에러가 발생했습니다." + se.getMessage());
		} finally {
		}
		return 0;
	}
	
	
	//유통기한 검사하는 메소드
	private boolean isValidExp(String exp) {
		Date today = new Date();
		SimpleDateFormat  format1 = new  SimpleDateFormat("yyyy-MM-dd");
		String present = format1.format(today);
		if(exp.compareTo(present)>=0 ) {
			return true;
		}

		return false;

	}

	//상품이름데이터를 넘기는  메소드  판매자
	public String getpname(String item) {    
		String data = "noname";

		try {
			String sql = "select pname from product where barcode = '"+item+"'"; // item 으로 바코드 확인하고      
			pstm = conn.prepareStatement(sql);
			rs = pstm.executeQuery();
			rs.next();
			String pname = rs.getString("pname");       	              
			data = pname;	                   
		} catch (SQLException se) {
			System.err.println("SQL 수행중 에러가 발생했습니다. (이름 데이터 오류)" + se.getMessage() );
		} finally {

		}
		return data;

	}

	// 구매후 제품개수 빼는 메소드
	public void renew_pamount(ArrayList<String> item_list,ArrayList<Integer> count_list) {
		for(int i = 0;i< item_list.size();i++) {
			String item = item_list.get(i);
			int count = count_list.get(i);
			try {

				String chk = "SELECT barcode from PRODUCT  ";
				pstm = conn.prepareStatement(chk);
				rs = pstm.executeQuery();

				//System.out.println(item+","+count);
				boolean flag = false;
				while(rs.next()) {
					String tempBarcode = rs.getString("barcode");
					if(tempBarcode.equals(item)) {
						flag = true;
						break;
					}
				}
				if(flag == true) {
					String sql = "UPDATE PRODUCT SET pamount = pamount -"+ count +" where barcode ='"+ item+"'"  ;
					Statement stmt = conn.createStatement();
					stmt.executeUpdate(sql);
					stmt.close();
					//					pstm = conn.prepareStatement(sql);
					//					rs = pstm.executeQuery();
					System.out.println("재고가 조정되었습니다");
				}
				else {
					System.err.println("바코드와 일치하는 제품이 없습니다.");
				}

			} catch (SQLException se) {
				System.err.println("SQL 수행중 에러가 발생했습니다." + se.getMessage());
			} finally {

			}      
		}

	}


	// 회원인 경우 계산 - 적립금,회원등급 결정
	public void membercalculation() {
		String grade = "N";
		int saving = 0;   
		try {

			String sql = "select grade,saving from customer where phone = '"+phone+"'"; // item 으로 바코드 확인하고 
			pstm = conn.prepareStatement(sql);
			rs = pstm.executeQuery();

			rs.next();

			grade = rs.getString("grade");
			String prevGrade = grade;
			saving = rs.getInt("saving");

			switch (grade) {
			case "S":
				saving += fee * 0.05;
				break;
			case "A":
				saving += fee * 0.02;
				break;
			case "B":
				saving += fee * 0.01;
				break;
			default:
				System.out.println("시스템 오류");
				break;
			}

			grade = renew_grade(saving);

			sql = "UPDATE CUSTOMER SET grade ='"+grade+"', saving = "+saving+" where phone = '"+phone+"'";
			Statement stmt = conn.createStatement();
			stmt.executeUpdate(sql);
			stmt.close();
			if(!prevGrade.equals(grade)) {

				System.out.println("등급이 갱신 되었습니다 갱신된 등급:"+ grade);
				JOptionPane popup =new JOptionPane();
				popup.showMessageDialog(null, "등급이 갱신 되었습니다 갱신된 등급:"+ grade);	
			}

			System.out.println("적립금이 조정되었습니다 보유적립금:"+saving);
			JOptionPane popup =new JOptionPane();
			popup.showMessageDialog(null, "적립금이 조정되었습니다 보유적립금:"+saving);	


		} catch (SQLException se) {
			System.err.println("SQL 수행중 에러가 발생했습니다." + se.getMessage());
		} finally {
		}
	}
	
	// 회원등급 계산 하는 메소드
	private String renew_grade(int saving) {
		if(saving >= 1000.0)
			return "S";
		else if(saving>=500.0)
			return "A";
		else  return "B";

	}
	
	//장부를 추가하는 메소드
	public void addBooks(String barcode, Integer amount, Integer price, String pname, String method) {
		try {

			String sql = " insert into books values('"+barcode+"',"+ amount +",'" + pname +"','"+ method +"', "+price +  ")" ;    
			Statement stmt = conn.createStatement();
			stmt.executeUpdate(sql);
			stmt.close();
		} catch (SQLException se) {
			System.err.println("SQL 수행중 에러가 발생했습니다." + se.getMessage());
		}
		finally {

		}      

		System.out.println(barcode + "/" + amount  + "/" + pname   + "/"
				+ method  + "/" + price + "원/ 장부 추가 완료");   
	}




}
