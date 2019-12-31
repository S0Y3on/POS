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
	
	
	public static boolean added_all = false; //���Ÿ� �Ϸ��Ͽ����� �Ǵ��ϱ�����
	public static boolean chkCustomer = false; // ȸ������ ������ ������ �ƴ��� �Ǵ��ϱ�����
	public static String phone = null; 
	public static int fee = 0;
	public static boolean check = false;  //������ �Ϸ��Ͽ����� �Ǵ��ϱ�����
	public static ArrayList<String> ShoppingBasket_Barcode = new ArrayList<String>(); //���ڵ� ���� ����Ʈ
	public static ArrayList<Integer> ShoppingBasket_Amount = new ArrayList<Integer>(); // ���ž� ���� ����Ʈ
	public static ArrayList<String> ShoppingBasket_Pname  = new ArrayList<String>(); // ��ǰ �� ���� ����Ʈ
	public static ArrayList<Integer> ShoppingBasket_Price =  new ArrayList<Integer>();  //��ǰ ����	
 

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
	
	//ȸ������ Ȯ���ϴ� �޼ҵ�
	public boolean checkCustomer(String phone) {
		try {

			String sql = "select * from customer where phone = '"+phone+"'"; // item ���� ���ڵ� Ȯ���ϰ� 
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
			System.err.println("SQL ������ ������ �߻��߽��ϴ�." + se.getMessage());
		} finally {
		}
		return false;
	}

	
	//ȸ���� ����ϴ� �޼ҵ� 
	public void addCustomer(String cname, String phone, char grade, int saving,int age) {
		try {
			String sql = " insert into Customer values('"+cname+"','"+phone+"','"+ grade +"', "+ saving +" , "+age +  ")" ;    
			Statement stmt = conn.createStatement(); //Ŀ�ؼǿ��� statement ��ü ����
			stmt.executeUpdate(sql);


			stmt.close();
		} catch (SQLException se) {
			System.err.println("SQL ������ ������ �߻��߽��ϴ�. �̹� �����ϴ� ȸ���Դϴ� �ٽ� �õ����ּ���" + se.getMessage());
		} finally {
		}      
		System.out.println(cname + "/" + phone  + "/" + grade   + "/"
				+ saving  + "/" + age + "/ ȸ�� �߰� �Ϸ�");   

	}
	

	//���Ű����� ��ǰ���� Ȯ���ϴ� �޼ҵ�  �Ǹ���
	public int checkProduct(String item, int count) {
		try {

			String sql = "select * from product where barcode = '"+item+"'"; // item ���� ���ڵ� Ȯ���ϰ�      
			pstm = conn.prepareStatement(sql);
			rs = pstm.executeQuery();
			while(rs.next()) {
				String pamount = rs.getString("pamount");
				String exp = rs.getString("expiration");
				int price = rs.getInt("price");

				exp = exp.substring(0, exp.lastIndexOf('-')+3 );

				if(Integer.parseInt(pamount)<count) {
					System.out.println("��� �����մϴ�.");
					JOptionPane popup =new JOptionPane();
					popup.showMessageDialog(null, "��� �����մϴ�.");
				}
				else if(!isValidExp(exp)) {
					System.out.println("��������� �������ϴ�.");
					JOptionPane popup =new JOptionPane();
					popup.showMessageDialog(null, "��������� �������ϴ�.");
				}
				else {
					return price;
				}  
			}

		} catch (SQLException se) {
			System.err.println("SQL ������ ������ �߻��߽��ϴ�." + se.getMessage());
		} finally {
		}
		return 0;
	}
	
	
	//������� �˻��ϴ� �޼ҵ�
	private boolean isValidExp(String exp) {
		Date today = new Date();
		SimpleDateFormat  format1 = new  SimpleDateFormat("yyyy-MM-dd");
		String present = format1.format(today);
		if(exp.compareTo(present)>=0 ) {
			return true;
		}

		return false;

	}

	//��ǰ�̸������͸� �ѱ��  �޼ҵ�  �Ǹ���
	public String getpname(String item) {    
		String data = "noname";

		try {
			String sql = "select pname from product where barcode = '"+item+"'"; // item ���� ���ڵ� Ȯ���ϰ�      
			pstm = conn.prepareStatement(sql);
			rs = pstm.executeQuery();
			rs.next();
			String pname = rs.getString("pname");       	              
			data = pname;	                   
		} catch (SQLException se) {
			System.err.println("SQL ������ ������ �߻��߽��ϴ�. (�̸� ������ ����)" + se.getMessage() );
		} finally {

		}
		return data;

	}

	// ������ ��ǰ���� ���� �޼ҵ�
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
					System.out.println("��� �����Ǿ����ϴ�");
				}
				else {
					System.err.println("���ڵ�� ��ġ�ϴ� ��ǰ�� �����ϴ�.");
				}

			} catch (SQLException se) {
				System.err.println("SQL ������ ������ �߻��߽��ϴ�." + se.getMessage());
			} finally {

			}      
		}

	}


	// ȸ���� ��� ��� - ������,ȸ����� ����
	public void membercalculation() {
		String grade = "N";
		int saving = 0;   
		try {

			String sql = "select grade,saving from customer where phone = '"+phone+"'"; // item ���� ���ڵ� Ȯ���ϰ� 
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
				System.out.println("�ý��� ����");
				break;
			}

			grade = renew_grade(saving);

			sql = "UPDATE CUSTOMER SET grade ='"+grade+"', saving = "+saving+" where phone = '"+phone+"'";
			Statement stmt = conn.createStatement();
			stmt.executeUpdate(sql);
			stmt.close();
			if(!prevGrade.equals(grade)) {

				System.out.println("����� ���� �Ǿ����ϴ� ���ŵ� ���:"+ grade);
				JOptionPane popup =new JOptionPane();
				popup.showMessageDialog(null, "����� ���� �Ǿ����ϴ� ���ŵ� ���:"+ grade);	
			}

			System.out.println("�������� �����Ǿ����ϴ� ����������:"+saving);
			JOptionPane popup =new JOptionPane();
			popup.showMessageDialog(null, "�������� �����Ǿ����ϴ� ����������:"+saving);	


		} catch (SQLException se) {
			System.err.println("SQL ������ ������ �߻��߽��ϴ�." + se.getMessage());
		} finally {
		}
	}
	
	// ȸ����� ��� �ϴ� �޼ҵ�
	private String renew_grade(int saving) {
		if(saving >= 1000.0)
			return "S";
		else if(saving>=500.0)
			return "A";
		else  return "B";

	}
	
	//��θ� �߰��ϴ� �޼ҵ�
	public void addBooks(String barcode, Integer amount, Integer price, String pname, String method) {
		try {

			String sql = " insert into books values('"+barcode+"',"+ amount +",'" + pname +"','"+ method +"', "+price +  ")" ;    
			Statement stmt = conn.createStatement();
			stmt.executeUpdate(sql);
			stmt.close();
		} catch (SQLException se) {
			System.err.println("SQL ������ ������ �߻��߽��ϴ�." + se.getMessage());
		}
		finally {

		}      

		System.out.println(barcode + "/" + amount  + "/" + pname   + "/"
				+ method  + "/" + price + "��/ ��� �߰� �Ϸ�");   
	}




}
