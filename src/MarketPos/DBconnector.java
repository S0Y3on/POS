package MarketPos;


import java.io.BufferedReader;
import java.io.BufferedWriter;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JOptionPane;


public class DBconnector {

	String url;
	BufferedWriter bw;//����
	BufferedReader br;//�б�
	String urlbooks,urlcustomer,urlproduct,urladministrator;
//	private Connection con = null; 
//	private Statement stmt = null; 
//	private ResultSet rs = null;

	private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver"; // jdbc ����̹� �ּ� 
	private static final String DB_URL = "jdbc:mysql://203.247.166.201:3306/product?serverTimezone=UTC"; // DB ���� �ּ� 
	private static final String USERNAME = "POS"; // DB ID 
	private static final String PASSWORD = "qwe123"; // DB Password 


	protected static Connection getdb;

	
	protected  Connection getConnection()
	{
		Connection con = null;
		try { 

			Class.forName(JDBC_DRIVER); //Class Ŭ������ forName()�Լ��� �̿��ؼ� �ش� Ŭ������ �޸𸮷� �ε� �ϴ� ���Դϴ�. //URL, ID, password�� �Է��Ͽ� �����ͺ��̽��� �����մϴ�. 
			con = DriverManager.getConnection(DB_URL,USERNAME,PASSWORD); // ���Ӱ���� ����մϴ�.
			if (con != null){System.out.println("����");} 
			else{System.out.println("����");} } 
		catch (ClassNotFoundException e) { 
			System.out.println("Class Not Found Exection");
			e.printStackTrace(); } 
		catch (SQLException e) { 
			System.out.println("SQL Exception : " + e.getMessage());
			e.printStackTrace(); 
		} 
		return con; 
	}


}



