package MarketPos;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.border.Border;


public class GUI_admin extends JFrame{
	public static Scanner scan;//글을 입력하는 것.

	//private static DB_administrator dbconnecter = new DB_administrator();
	private static DB_administrator dbconnecter = DB_administrator.getInstacne();

	private JPanel adPanel;
	URL imagePrecordButton = GUI_admin.class.getClassLoader().getResource("PrecordButton.png");
	private JButton PrecordButton = new JButton(new ImageIcon(imagePrecordButton)); //상품등록
	
	URL imagePlookupButton = GUI_admin.class.getClassLoader().getResource("PlookupButton.png");
	private JButton PlookupButton = new JButton(new ImageIcon(imagePlookupButton)); //상품조회
	
	URL imagePsearchButton = GUI_admin.class.getClassLoader().getResource("PsearchButton.png");
	private JButton PsearchButton = new JButton(new ImageIcon(imagePsearchButton)); //상품검색
	
	URL imageaddPamountButton = GUI_admin.class.getClassLoader().getResource("addPamountButton.png");
	private JButton addPamountButton = new JButton(new ImageIcon(imageaddPamountButton)); //재고추가
	
	URL imageClookupButton = GUI_admin.class.getClassLoader().getResource("ClookupButton.png");
	private JButton ClookupButton = new JButton(new ImageIcon(imageClookupButton)); //고객조회
	
	URL imageCsearchButton = GUI_admin.class.getClassLoader().getResource("CsearchButton.png");
	private JButton CsearchButton = new JButton(new ImageIcon(imageCsearchButton)); //고객검색
	
	URL imageBlookupButton = GUI_admin.class.getClassLoader().getResource("BlookupButton.png");
	private JButton BlookupButton = new JButton(new ImageIcon(imageBlookupButton)); //장부조회
	
	URL imageexitButton = GUI_admin.class.getClassLoader().getResource("exitButton.png");
	private JButton exitButton = new JButton(new ImageIcon(imageexitButton)); //종료
	URL imagelogo = GUI_admin.class.getClassLoader().getResource("logo.png");
	private ImageIcon logo = new ImageIcon(imagelogo);
	private JLabel logoImage = new JLabel(logo);


	public GUI_admin() 
	{
		super("MarketPos");
		scan = new Scanner(System.in);//글을 입력하는 객체 생성
	}
	
	public void check() {
		
		JFrame F_login = new JFrame();
		F_login.setTitle("관리자 로그인");
		
		JPanel NewWindowMember = new JPanel();
		F_login.setLayout(new BorderLayout());
		NewWindowMember.setLayout(null);
		NewWindowMember.setBackground(new Color(26,44,91));
		F_login.setContentPane(NewWindowMember);
		
		URL imagepassword = GUI_admin.class.getClassLoader().getResource("password.png");
		ImageIcon password = new ImageIcon(imagepassword);
		JLabel passwordImage = new JLabel(password);
		
		URL imageokay = GUI_admin.class.getClassLoader().getResource("okay.png");
		JButton okayButton = new JButton(new ImageIcon(imageokay)); //종료
		
		setImage(okayButton);

		JPasswordField passwordField = new JPasswordField(); 
		setTextField(passwordField);      

		NewWindowMember.add(passwordImage);
		NewWindowMember.add(passwordField);

		NewWindowMember.add(okayButton);

		passwordImage.setBounds(30,72,150,46);
		passwordField.setBounds(205,72,250,50);

		okayButton.setBounds(180,170,138,51);
		okayButton.addMouseListener(new MouseListener() {
			@Override
			public void mouseEntered(java.awt.event.MouseEvent e) {
				URL imageokay_clicked = GUI_admin.class.getClassLoader().getResource("okay_clicked.png");
				ImageIcon okay_clicked = new ImageIcon(imageokay_clicked); //제품구매 눌려진 이미지
				okayButton.setIcon(okay_clicked);				
			}
			@Override
			public void mouseExited(java.awt.event.MouseEvent e) {
				URL imageokay = GUI_admin.class.getClassLoader().getResource("okay.png");
				ImageIcon undo = new ImageIcon(imageokay); // 누르고 나갔을때 이미지 
				okayButton.setIcon(undo);

			}
			@Override
			public void mouseClicked(java.awt.event.MouseEvent e) {
			}
			@Override
			public void mousePressed(java.awt.event.MouseEvent e) {
			
				String PW = passwordField.getText();
				if(dbconnecter.checkPw(PW)) {
					JFrame F_ad = new JFrame("administrator");
					AdminGo(F_ad);
					F_login.setVisible(false);
				}
				else {
					System.out.println("패스워드가 맞지 않습니다.");
					JOptionPane popup =new JOptionPane();
					popup.showMessageDialog(null, "비밀번호가 틀렸습니다!");
				}
			}
			@Override
			public void mouseReleased(java.awt.event.MouseEvent e) {

			}	
		});
		F_login.setSize(500,300);
		F_login.setResizable(true);
		F_login.setVisible(true);

	}
	

	private void AdminGo(JFrame F_ad) {//DBconnector class 연결.
		System.out.println("관리자 프로그램을 시작합니다.");
		AdminMain(F_ad); //판매자 초기화면
		F_ad.setPreferredSize(new Dimension(1000,750));
		F_ad.pack();
		F_ad.setVisible(true);
	}


	private void setTextField(JTextField L){
		Border lineBorder = BorderFactory.createLineBorder(Color.white, 4);
		Border emptyBorder = BorderFactory.createEmptyBorder(7, 7, 7, 7);
		L.setBorder(BorderFactory.createCompoundBorder(lineBorder,emptyBorder));
		L.setBackground(new Color(26,44,91));
		L.setForeground(Color.white);
		L.setFont(new Font("굴림",Font.PLAIN,25));
	}

	private void setImage(JButton bt) {
		bt.setBackground(Color.red);
		bt.setBorderPainted(false);
		bt.setFocusPainted(false);
		bt.setContentAreaFilled(false);
	}
	private void AdminMain(JFrame ad) 
	{
		adPanel = new JPanel();
		ad.setLayout(new BorderLayout());
		adPanel.setBackground(new Color(26,44,91));
		adPanel.setLayout(null);

		setImage(PrecordButton);
		setImage(PlookupButton);
		setImage(PsearchButton);
		setImage(addPamountButton);
		setImage(ClookupButton);
		setImage(CsearchButton);
		setImage(BlookupButton);
		setImage(exitButton);

		logoImage.setBounds(20,15, 345, 50);     //로고

		PrecordButton.setBounds(85,150,200,200);    //상품등록
		PrecordButton.addMouseListener(new MouseListener() {
			@Override
			public void mouseEntered(java.awt.event.MouseEvent e) {
				URL imagePrecordButton_clicked = GUI_admin.class.getClassLoader().getResource("PrecordButton_clicked.png");
				ImageIcon PrecordButton_clicked = new ImageIcon(imagePrecordButton_clicked); //제품구매 눌려진 이미지
				PrecordButton.setIcon(PrecordButton_clicked);				
			}
			@Override
			public void mouseExited(java.awt.event.MouseEvent e) {
				URL imagePrecordButton  = GUI_admin.class.getClassLoader().getResource("PrecordButton.png");
				ImageIcon undo = new ImageIcon(imagePrecordButton); // 누르고 나갔을때 이미지 
				PrecordButton.setIcon(undo);

			}
			@Override
			public void mousePressed(java.awt.event.MouseEvent e) {
				get_product_add();     //상품 들록할 인자를 입력받는 함수
			}
			@Override
			public void mouseClicked(java.awt.event.MouseEvent e) {
			}	
			@Override
			public void mouseReleased(java.awt.event.MouseEvent e) {

			}	
		});
		PlookupButton.setBounds(290,150, 200, 200);   //상품조회
		PlookupButton.addMouseListener(new MouseListener() {
			@Override
			public void mouseEntered(java.awt.event.MouseEvent e) {
				URL imagePlookupButton_clicked = GUI_admin.class.getClassLoader().getResource("PlookupButton_clicked.png");
				ImageIcon PlookupButton_clicked = new ImageIcon(imagePlookupButton_clicked); //제품구매 눌려진 이미지
				PlookupButton.setIcon(PlookupButton_clicked);				
			}
			@Override
			public void mouseExited(java.awt.event.MouseEvent e) {
				URL imagePlookupButton  = GUI_admin.class.getClassLoader().getResource("PlookupButton.png");
				
				ImageIcon undo = new ImageIcon(imagePlookupButton); // 누르고 나갔을때 이미지 
				PlookupButton.setIcon(undo);

			}
			@Override
			public void mousePressed(java.awt.event.MouseEvent e) {
				JFrame F_pdisplay = new JFrame("상품조회");
				F_pdisplay.setLocationRelativeTo(null);
				F_pdisplay.setSize(500,500);
				F_pdisplay.setVisible(true);


				ArrayList<String> data = dbconnecter.pdisplay();    
				DefaultListModel<String> m = new DefaultListModel<>();
				for(int i = 0 ;i<data.size();i++) {
					m.addElement(data.get(i)); //리스트 전체 출력
				}

				JList list = new JList(m);
				list.setFont( new Font("monospaced", Font.PLAIN, 12));
				JScrollPane scroll = new JScrollPane(list);
				scroll.setPreferredSize(new Dimension(300, 300));
				F_pdisplay.add(scroll);
			}
			@Override
			public void mouseClicked(java.awt.event.MouseEvent e) {
			}	
			@Override
			public void mouseReleased(java.awt.event.MouseEvent e) {

			}	
		});


		PsearchButton.setBounds(495,150, 200, 200);   //상품검색
		PsearchButton.addMouseListener(new MouseListener() {
			@Override
			public void mouseEntered(java.awt.event.MouseEvent e) {
				URL imagePsearchButton_clicked = GUI_admin.class.getClassLoader().getResource("PsearchButton_clicked.png");
				ImageIcon PsearchButton_clicked = new ImageIcon(imagePsearchButton_clicked); //제품구매 눌려진 이미지
				PsearchButton.setIcon(PsearchButton_clicked);				
			}
			@Override
			public void mouseExited(java.awt.event.MouseEvent e) {
				URL imagePsearchButton  = GUI_admin.class.getClassLoader().getResource("PsearchButton.png");
				ImageIcon undo = new ImageIcon(imagePsearchButton); // 누르고 나갔을때 이미지 
				PsearchButton.setIcon(undo);

			}
			@Override
			public void mousePressed(java.awt.event.MouseEvent e) {
				get_psearch();
			}
			@Override
			public void mouseClicked(java.awt.event.MouseEvent e) {
			}	
			@Override
			public void mouseReleased(java.awt.event.MouseEvent e) {

			}	
		});
		addPamountButton.setBounds(695,150,200,200);    //재고추가
		addPamountButton.addMouseListener(new MouseListener() {
			@Override
			public void mouseEntered(java.awt.event.MouseEvent e) {
				
				URL imageaddPamountButton_clicked  = GUI_admin.class.getClassLoader().getResource("addPamountButton_clicked.png");
				ImageIcon addPamountButton_clicked = new ImageIcon(imageaddPamountButton_clicked); //제품구매 눌려진 이미지
				addPamountButton.setIcon(addPamountButton_clicked);				
			}
			@Override
			public void mouseExited(java.awt.event.MouseEvent e) {
				URL imageaddPamountButton  = GUI_admin.class.getClassLoader().getResource("addPamountButton.png");
				ImageIcon undo = new ImageIcon(imageaddPamountButton); // 누르고 나갔을때 이미지 
				addPamountButton.setIcon(undo);

			}
			@Override
			public void mousePressed(java.awt.event.MouseEvent e) {
				get_product_update();
			}
			@Override
			public void mouseClicked(java.awt.event.MouseEvent e) {
			}	
			@Override
			public void mouseReleased(java.awt.event.MouseEvent e) {

			}	
		});
		ClookupButton.setBounds(85,350,200,200);    //고객조회
		ClookupButton.addMouseListener(new MouseListener() {
			@Override
			public void mouseEntered(java.awt.event.MouseEvent e) {
				URL imageClookupButton_clicked  = GUI_admin.class.getClassLoader().getResource("ClookupButton_clicked.png");
				ImageIcon ClookupButton_clicked = new ImageIcon(imageClookupButton_clicked); //제품구매 눌려진 이미지
				ClookupButton.setIcon(ClookupButton_clicked);				
			}
			@Override
			public void mouseExited(java.awt.event.MouseEvent e) {
				URL imageClookupButton = GUI_admin.class.getClassLoader().getResource("ClookupButton.png");
				ImageIcon undo = new ImageIcon(imageClookupButton); // 누르고 나갔을때 이미지 
				ClookupButton.setIcon(undo);

			}
			@Override
			public void mousePressed(java.awt.event.MouseEvent e) {
				JFrame F_psearch = new JFrame("고객조회");
				F_psearch.setLocationRelativeTo(null);
				F_psearch.setSize(500,500);
				F_psearch.setVisible(true);
				
				ArrayList<String> data = dbconnecter.cdisplay();    
				DefaultListModel<String> m = new DefaultListModel<>();
				for(int i = 0 ;i<data.size();i++) {
					m.addElement(data.get(i)); //리스트 전체 출력
				}
				JList list = new JList(m);   
				list.setFont( new Font("monospaced", Font.PLAIN, 12));
				JScrollPane scroll = new JScrollPane(list);
				scroll.setPreferredSize(new Dimension(300, 300));
				F_psearch.add(scroll);
			}
			@Override
			public void mouseClicked(java.awt.event.MouseEvent e) {
			}	
			@Override
			public void mouseReleased(java.awt.event.MouseEvent e) {

			}	
		});


		CsearchButton.setBounds(290,350,200,200);   //고객검색
		CsearchButton.addMouseListener(new MouseListener() {
			@Override
			public void mouseEntered(java.awt.event.MouseEvent e) {
				URL imageCsearchButton_clicked = GUI_admin.class.getClassLoader().getResource("CsearchButton_clicked.png");
				ImageIcon CsearchButton_clicked = new ImageIcon(imageCsearchButton_clicked); //제품구매 눌려진 이미지
				CsearchButton.setIcon(CsearchButton_clicked);				
			}
			@Override
			public void mouseExited(java.awt.event.MouseEvent e) {
				URL imageCsearchButton = GUI_admin.class.getClassLoader().getResource("CsearchButton.png");
				ImageIcon undo = new ImageIcon(imageCsearchButton); // 누르고 나갔을때 이미지 
				CsearchButton.setIcon(undo);

			}
			@Override
			public void mousePressed(java.awt.event.MouseEvent e) {
				get_csearch();
			}
			@Override
			public void mouseClicked(java.awt.event.MouseEvent e) {
			}	
			@Override
			public void mouseReleased(java.awt.event.MouseEvent e) {

			}	
		});
		
		BlookupButton.setBounds(495,350,200,200);   //장부조회
		BlookupButton.addMouseListener(new MouseListener() {
			@Override
			public void mouseEntered(java.awt.event.MouseEvent e) {
				URL imageBlookupButton_clicked = GUI_admin.class.getClassLoader().getResource("BlookupButton_clicked.png");
				ImageIcon BlookupButton_clicked = new ImageIcon(imageBlookupButton_clicked); //제품구매 눌려진 이미지
				BlookupButton.setIcon(BlookupButton_clicked);				
			}
			@Override
			public void mouseExited(java.awt.event.MouseEvent e) {
				URL imageBlookupButton = GUI_admin.class.getClassLoader().getResource("BlookupButton.png");
				ImageIcon undo = new ImageIcon(imageBlookupButton); // 누르고 나갔을때 이미지 
				BlookupButton.setIcon(undo);

			}
			@Override
			public void mousePressed(java.awt.event.MouseEvent e) {
				JFrame F_pdisplay = new JFrame("장부조회");
				F_pdisplay.setLocationRelativeTo(null);
				F_pdisplay.setSize(500,500);
				F_pdisplay.setVisible(true);
				ArrayList<String> data = dbconnecter.bdisplay(); 
				//
				System.out.println(data);

				DefaultListModel<String> m = new DefaultListModel<>();
				for(int i = 0 ;i<data.size();i++) {
					m.addElement(data.get(i)); //리스트 전체 출력
				}
				JList list = new JList(m);
				list.setFont( new Font("monospaced", Font.PLAIN, 12));
				JScrollPane scroll = new JScrollPane(list);
				scroll.setPreferredSize(new Dimension(300, 300));
				F_pdisplay.add(scroll);
			}
			@Override
			public void mouseClicked(java.awt.event.MouseEvent e) {
			}	
			@Override
			public void mouseReleased(java.awt.event.MouseEvent e) {

			}	
		});


		exitButton.setBounds(695,350, 200, 200);    //종료
		exitButton.addMouseListener(new MouseListener() {
			@Override
			public void mouseEntered(java.awt.event.MouseEvent e) {
				URL imageexitButton_clicked = GUI_admin.class.getClassLoader().getResource("exitButton_clicked.png");
				ImageIcon exitButton_clicked = new ImageIcon(imageexitButton_clicked); //제품구매 눌려진 이미지
				exitButton.setIcon(exitButton_clicked);				
			}
			@Override
			public void mouseExited(java.awt.event.MouseEvent e) {
				URL imageexitButton  = GUI_admin.class.getClassLoader().getResource("exitButton.png");
				ImageIcon undo = new ImageIcon(imageexitButton); // 누르고 나갔을때 이미지 
				exitButton.setIcon(undo);

			}
			@Override
			public void mousePressed(java.awt.event.MouseEvent e) {
				ad.setVisible(false);
			}
			@Override
			public void mouseClicked(java.awt.event.MouseEvent e) {
			}	
			@Override
			public void mouseReleased(java.awt.event.MouseEvent e) {

			}	
		});
		adPanel.add(logoImage);
		adPanel.add(PrecordButton);
		adPanel.add(PlookupButton);
		adPanel.add(PsearchButton);
		adPanel.add(addPamountButton);
		adPanel.add(ClookupButton);
		adPanel.add(CsearchButton);
		adPanel.add(BlookupButton);
		adPanel.add(exitButton);
		ad.getContentPane().add(adPanel);
	}

	
	//재고를 추가하는 메소
	private void get_product_add() {

		JFrame F_padd = new JFrame("상품등록");
		JPanel paddPanel = new JPanel();
		setLayout(new BorderLayout());
		paddPanel.setLayout(null);
		paddPanel.setBackground(new Color(26,44,91));
		setContentPane(paddPanel);
		URL imagebarcode = GUI_admin.class.getClassLoader().getResource("barcode.png");
		ImageIcon barcode = new ImageIcon(imagebarcode);
		
		JLabel barcodeImage = new JLabel(barcode);
		
		URL imagepname = GUI_admin.class.getClassLoader().getResource("pname.png");
		ImageIcon pname = new ImageIcon(imagepname);
		JLabel pnameImage = new JLabel(pname);	    
		URL imageptype = GUI_admin.class.getClassLoader().getResource("ptype.png");
		ImageIcon ptype = new ImageIcon(imageptype);
		JLabel ptypeImage = new JLabel(ptype);
		URL imageamount = GUI_admin.class.getClassLoader().getResource("amount.png");
		ImageIcon pamount = new ImageIcon(imageamount);
		JLabel pamountImage = new JLabel(pamount);
		URL imageexpiration = GUI_admin.class.getClassLoader().getResource("expiration.png");
		ImageIcon expiration = new ImageIcon(imageexpiration);
		JLabel expirationImage = new JLabel(expiration);	   
		URL imageprice = GUI_admin.class.getClassLoader().getResource("price.png");
		ImageIcon price = new ImageIcon(imageprice);
		JLabel priceImage = new JLabel(price);

		URL imageokay = GUI_admin.class.getClassLoader().getResource("okay.png");
		JButton okayButton = new JButton(new ImageIcon(imageokay)); //확인
		setImage(okayButton);


		JTextField barcodeField = new JTextField(); 
		setTextField(barcodeField);     
		JTextField pnameField = new JTextField();
		setTextField(pnameField);     
		JTextField ptypeField = new JTextField();
		setTextField(ptypeField);             
		JTextField pamountField = new JTextField(); 
		setTextField(pamountField);     
		JTextField expirationField = new JTextField();
		setTextField(expirationField);     
		JTextField priceField = new JTextField();
		setTextField(priceField);    

		paddPanel.add(barcodeImage); //바코드
		paddPanel.add(pnameImage);	//제품이름
		paddPanel.add(ptypeImage);	//제품종류
		paddPanel.add(pamountImage);	//제품수량
		paddPanel.add(expirationImage);	//유통기한
		paddPanel.add(priceImage);	//제품가격

		paddPanel.add(barcodeField);
		paddPanel.add(pnameField);
		paddPanel.add(ptypeField);
		paddPanel.add(pamountField);
		paddPanel.add(expirationField);
		paddPanel.add(priceField);

		paddPanel.add(okayButton);

		barcodeImage.setBounds(55,72,118,46);
		pnameImage.setBounds(38,160,155,47);
		ptypeImage.setBounds(38,245,156,46);
		pamountImage.setBounds(70,335,80,46);
		expirationImage.setBounds(38,425,155,47);
		priceImage.setBounds(70,515,77,42);

		barcodeField.setBounds(205,70,250,50);
		pnameField.setBounds(205,160,250,50);
		ptypeField.setBounds(205,250,250,50);	        
		pamountField.setBounds(205,340,250,50);
		expirationField.setBounds(205,430,250,50);
		priceField.setBounds(205,520,250,50);


		okayButton.setBounds(180,620,138,51);
		okayButton.addMouseListener(new MouseListener() {


			@Override
			public void mouseEntered(java.awt.event.MouseEvent e) {
				
				URL imageokay_clicked= GUI_admin.class.getClassLoader().getResource("okay_clicked.png");
				ImageIcon okay_clicked = new ImageIcon(imageokay_clicked); //제품구매 눌려진 이미지
				okayButton.setIcon(okay_clicked);				
			}
			@Override
			public void mouseExited(java.awt.event.MouseEvent e) {
				URL imageokay = GUI_admin.class.getClassLoader().getResource("okay.png");
				ImageIcon undo = new ImageIcon(imageokay); // 누르고 나갔을때 이미지 
				okayButton.setIcon(undo);

			}
			@Override
			public void mouseClicked(java.awt.event.MouseEvent e) {

			}
			@Override
			public void mousePressed(java.awt.event.MouseEvent e) {


				if(barcodeField.getText().equals("") || pnameField.getText().equals("") || ptypeField.getText().equals("") ||
						pamountField.getText().equals("") || expirationField.getText().equals("") || priceField.getText().equals("")) {
					JOptionPane popup = new JOptionPane();
					popup.showMessageDialog(null, "항목을 모두 입력하세요");	
				}

				else {
					boolean flag = false;
					if( pnameField.getText().length() >6 ) { 
						JOptionPane popup = new JOptionPane();
						popup.showMessageDialog(null, "제품이름을 6글자 이내로 입력해주세요");
						flag = true;
					}
					else if(  ptypeField.getText().length() >5 ) {  
						JOptionPane popup = new JOptionPane();
						popup.showMessageDialog(null, "제품종류을 5글자 이내로 입력해주세요");
						flag = true;
					}

					try {
						if(Integer.parseInt(priceField.getText()) > 1000000){
							JOptionPane popup = new JOptionPane();
							popup.showMessageDialog(null, "상품의 가격을 100만원 이내로 조정해주세요");
							flag = true;
						}
					}catch (Exception ce){
						JOptionPane popup = new JOptionPane();
						popup.showMessageDialog(null, "상품의 가격을 100만원 이내로 조정해주세요");
						flag = true;

					}
					try {
						if (Integer.parseInt(pamountField.getText()) > 1000 ) {
							JOptionPane popup = new JOptionPane();
							popup.showMessageDialog(null, "상품의 개수를 1000개 이내로 조정해주세요");
							flag = true;
						}
					}catch (Exception ce){
						JOptionPane popup = new JOptionPane();
						popup.showMessageDialog(null, "상품의 개수를 1000개 이내로 조정해주세요");
						flag = true;

					}
					if(!flag) {
						if(!dbconnecter.checkBarcode(barcodeField.getText())) {
							dbconnecter.addProduct(barcodeField.getText(), pnameField.getText(),ptypeField.getText(), pamountField.getText(), expirationField.getText(),Integer.parseInt(priceField.getText()));//값 저장 - 상품등록
							System.out.println("등록이 완료 되었습니다.");	
							F_padd.setVisible(false);
						}
						else {
							JOptionPane popup = new JOptionPane();
							popup.showMessageDialog(null, "이미 등록된 바코드가 있습니다.");	
						}
					}

				}

			}


			@Override
			public void mouseReleased(MouseEvent fe) {
				// TODO Auto-generated method stub

			}
		});

		F_padd.add(paddPanel);
		F_padd.setSize(500,750);
		F_padd.setResizable(true);
		F_padd.setVisible(true);

	}



	private void get_psearch() {
		JFrame psearch = new JFrame("상품검색");
		JPanel psearchPanel = new JPanel();


		setLayout(new BorderLayout());
		psearchPanel.setLayout(null);
		psearchPanel.setBackground(new Color(26,44,91));
		setContentPane(psearchPanel);
		URL imagebarcode = GUI_admin.class.getClassLoader().getResource("barcode.png");
		ImageIcon barcode = new ImageIcon(imagebarcode);
		JLabel barcodeImage = new JLabel(barcode);

		URL imageokay = GUI_admin.class.getClassLoader().getResource("okay.png");
		JButton okayButton = new JButton(new ImageIcon(imageokay)); //종료
		setImage(okayButton);

		JTextField barcodeField = new JTextField(); 
		setTextField(barcodeField);      

		psearchPanel.add(barcodeImage);
		psearchPanel.add(barcodeField);

		psearchPanel.add(okayButton);

		barcodeImage.setBounds(55,72,118,46);
		barcodeField.setBounds(205,72,250,50);

		okayButton.setBounds(180,170,138,51);
		okayButton.addMouseListener(new MouseListener() {
			@Override
			public void mouseEntered(java.awt.event.MouseEvent e) {
				URL imageokay_clicked = GUI_admin.class.getClassLoader().getResource("okay_clicked.png");
				ImageIcon okay_clicked = new ImageIcon(imageokay_clicked); //제품구매 눌려진 이미지
				okayButton.setIcon(okay_clicked);				
			}
			@Override
			public void mouseExited(java.awt.event.MouseEvent e) {
				URL imageokay  = GUI_admin.class.getClassLoader().getResource("okay.png");
				ImageIcon undo = new ImageIcon(imageokay); // 누르고 나갔을때 이미지 
				okayButton.setIcon(undo);

			}
			@Override
			public void mouseClicked(java.awt.event.MouseEvent e) {

			}
			@Override
			public void mousePressed(java.awt.event.MouseEvent e) {
				JFrame result = new JFrame("검색결과");		   
				String data = dbconnecter.psearch(barcodeField.getText());   
				if(!data.equals("no")) {
					System.out.println("상품을 찾았습니다");
					Label L = new Label(data);
					Font f1 = new Font("바탕", Font.ITALIC, 28);
					L.setFont(f1);;
					result.add(L);
					result.setSize(700,200);
					result.setResizable(true);
					result.setVisible(true);

					psearch.setVisible(false);
				}
			}
			@Override
			public void mouseReleased(java.awt.event.MouseEvent e) {

			}	
		});

		psearch.add(psearchPanel);
		psearch.setSize(500,300);
		psearch.setResizable(true);
		psearch.setVisible(true);
	}



	private void get_product_update() {
		JFrame pupdate = new JFrame("재고추가");
		JPanel puadatePanel = new JPanel();
		setLayout(new BorderLayout());
		puadatePanel.setLayout(null);
		puadatePanel.setBackground(new Color(26,44,91));
		setContentPane(puadatePanel);
		URL imagebarcode  = GUI_admin.class.getClassLoader().getResource("barcode.png");
		ImageIcon barcode = new ImageIcon(imagebarcode);
		JLabel barcodeImage = new JLabel(barcode);

		URL imageamount  = GUI_admin.class.getClassLoader().getResource("amount.png");
		ImageIcon amount = new ImageIcon(imageamount);
		JLabel amountImage = new JLabel(amount);

		URL imageokay  = GUI_admin.class.getClassLoader().getResource("okay.png");
		JButton okayButton = new JButton(new ImageIcon(imageokay)); //종료
		setImage(okayButton);


		JTextField barcodeField = new JTextField(); 
		setTextField(barcodeField);    
		JTextField amountField = new JTextField(); 
		setTextField(amountField);     

		puadatePanel.add(barcodeImage);
		puadatePanel.add(amountImage);

		puadatePanel.add(barcodeField);
		puadatePanel.add(amountField);

		puadatePanel.add(okayButton);

		barcodeImage.setBounds(55,72,118,46);
		amountImage.setBounds(70,162,80,46);

		barcodeField.setBounds(205,72,250,50);
		amountField.setBounds(205,162,250,50);

		okayButton.setBounds(180,280,138,51);
		okayButton.addMouseListener(new MouseListener() {
			@Override
			public void mouseEntered(java.awt.event.MouseEvent e) {
				URL imageokay_clicked  = GUI_admin.class.getClassLoader().getResource("okay_clicked.png");
				ImageIcon okay_clicked = new ImageIcon(imageokay_clicked); //제품구매 눌려진 이미지
				okayButton.setIcon(okay_clicked);				
			}
			@Override
			public void mouseExited(java.awt.event.MouseEvent e) {
				URL imageokay  = GUI_admin.class.getClassLoader().getResource("okay.png");
				ImageIcon undo = new ImageIcon(imageokay); // 누르고 나갔을때 이미지 
				okayButton.setIcon(undo);

			}
			@Override
			public void mouseClicked(java.awt.event.MouseEvent e) {

			}
			@Override
			public void mousePressed(java.awt.event.MouseEvent e) {
				if(barcodeField.getText().equals("") || amountField.getText().equals("")) {
					JOptionPane popup = new JOptionPane();
					popup.showMessageDialog(null, "항목을 모두 입력하세요");	
				}
				else {				
					dbconnecter.update_pamount(barcodeField.getText(), Integer.parseInt(amountField.getText()));
					pupdate.setVisible(false);
				}
			}
			@Override
			public void mouseReleased(java.awt.event.MouseEvent e) {

			}	
		});
		pupdate.add(puadatePanel);
		pupdate.setSize(500,400);
		pupdate.setResizable(true);
		pupdate.setVisible(true);
	}




	private void get_csearch() {
		JFrame csearch = new JFrame("고객검색");
		JPanel get_csearch = new JPanel();
		setLayout(new BorderLayout());
		get_csearch.setLayout(null);
		get_csearch.setBackground(new Color(26,44,91));
		setContentPane(get_csearch);
		URL imagephoneNumber = GUI_admin.class.getClassLoader().getResource("phoneNumber.png");
		ImageIcon name = new ImageIcon(imagephoneNumber);
		JLabel phoneImage = new JLabel(name);


		URL imageokay  = GUI_admin.class.getClassLoader().getResource("okay.png");
		JButton okayButton = new JButton(new ImageIcon(imageokay)); //종료
		setImage(okayButton);


		JTextField phoneField = new JTextField(); 
		setTextField(phoneField);    


		get_csearch.add(phoneImage);


		get_csearch.add(phoneField);


		get_csearch.add(okayButton);

		phoneImage.setBounds(15,72,170,46);
		phoneField.setBounds(205,72,250,50);

		okayButton.setBounds(180,280,138,51);
		okayButton.addMouseListener(new MouseListener() {
			@Override
			public void mouseEntered(java.awt.event.MouseEvent e) {
				URL imageokay_clicked  = GUI_admin.class.getClassLoader().getResource("okay_clicked.png");
				ImageIcon okay_clicked = new ImageIcon(imageokay_clicked); //제품구매 눌려진 이미지
				okayButton.setIcon(okay_clicked);				
			}
			@Override
			public void mouseExited(java.awt.event.MouseEvent e) {
				URL imageokay  = GUI_admin.class.getClassLoader().getResource("okay.png");
				ImageIcon undo = new ImageIcon(imageokay); // 누르고 나갔을때 이미지 
				okayButton.setIcon(undo);

			}
			@Override
			public void mouseClicked(java.awt.event.MouseEvent e) {

			}
			@Override
			public void mousePressed(java.awt.event.MouseEvent e) {
				JFrame result = new JFrame("검색결과");		   
				String data = dbconnecter.csearch(phoneField.getText());   
				if(!data.equals("no")) {
					System.out.println("회원을 찾았습니다");
					Label L = new Label(data);
					result.add(L);
					Font f1 = new Font("바탕", Font.ITALIC, 28);
					L.setFont(f1);;
					result.add(L);
					result.setSize(550,200);
					result.setResizable(true);
					result.setVisible(true);

					csearch.setVisible(false);
				}
			}
			@Override
			public void mouseReleased(java.awt.event.MouseEvent e) {

			}	
		});	        
		csearch.add(get_csearch);
		csearch.setSize(500,400);
		csearch.setResizable(true);
		csearch.setVisible(true);
	}
}


