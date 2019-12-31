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
	BufferedWriter bw;//쓰기
	BufferedReader br;//읽기
	String urlbooks,urlcustomer,urlproduct,urladministrator;
//	private Connection con = null; 
//	private Statement stmt = null; 
//	private ResultSet rs = null;

	private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver"; // jdbc 드라이버 주소 
	private static final String DB_URL = "jdbc:mysql://203.247.166.201:3306/product?serverTimezone=UTC"; // DB 접속 주소 
	private static final String USERNAME = "POS"; // DB ID 
	private static final String PASSWORD = "qwe123"; // DB Password 


	protected static Connection getdb;

	
	protected  Connection getConnection()
	{
		Connection con = null;
		try { 

			Class.forName(JDBC_DRIVER); //Class 클래스의 forName()함수를 이용해서 해당 클래스를 메모리로 로드 하는 것입니다. //URL, ID, password를 입력하여 데이터베이스에 접속합니다. 
			con = DriverManager.getConnection(DB_URL,USERNAME,PASSWORD); // 접속결과를 출력합니다.
			if (con != null){System.out.println("성공");} 
			else{System.out.println("실패");} } 
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



