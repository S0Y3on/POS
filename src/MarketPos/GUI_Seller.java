package MarketPos;

import MarketPos.GUI_admin;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.border.Border;

//import com.sun.glass.events.MouseEvent;


public class GUI_Seller{


	private static DB_seller dbconnecter = DB_seller.getInstacne();
	
    //private static DB_seller dbconnecter = new DB_seller();


	URL imagesell = GUI_Seller.class.getClassLoader().getResource("sell.png");
	URL imagemember = GUI_Seller.class.getClassLoader().getResource("member.png");
	URL imageadmin = GUI_Seller.class.getClassLoader().getResource("admin.png");
	URL imageexit = GUI_Seller.class.getClassLoader().getResource("exit.png");

	private JButton sellButton = new JButton(new ImageIcon(imagesell)); //제품구매
	private JButton memberButton = new JButton(new ImageIcon(imagemember)); //회원추가
	private JButton adminButton = new JButton(new ImageIcon(imageadmin)); //관리자모드
	private JButton exitButton = new JButton(new ImageIcon(imageexit)); //종료

	
	URL imagelogo = GUI_Seller.class.getClassLoader().getResource("logo.png");

	private ImageIcon logo = new ImageIcon(imagelogo);
	private JLabel logoImage = new JLabel(logo);

	private JPanel sellPanel;
	private JPanel purPanel;

	

	URL imageorder = GUI_Seller.class.getClassLoader().getResource("order.png");
	URL imagebarcode = GUI_Seller.class.getClassLoader().getResource("barcode.png");
	URL imageamount = GUI_Seller.class.getClassLoader().getResource("amount.png");

	private ImageIcon order = new ImageIcon(imageorder);
	private JLabel orderImage = new JLabel(order);
	private ImageIcon barcode = new ImageIcon(imagebarcode);
	private JLabel barcodeImage = new JLabel(barcode);
	private ImageIcon num = new ImageIcon(imageamount);
	private JLabel numImage = new JLabel(num);
	
	//
	public GUI_Seller()
	{	

		JFrame F_sell = new JFrame("MarketPos");
		sellMain(F_sell); //판매자 초기화면
		F_sell.getContentPane().add(sellPanel);
		F_sell.setPreferredSize(new Dimension(1000,750));
		F_sell.pack();
		F_sell.setVisible(true);
		F_sell.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	private void setImage(JButton bt) {
		bt.setBackground(Color.red);
		bt.setBorderPainted(false);
		bt.setFocusPainted(false);
		bt.setContentAreaFilled(false);
	}
	private void sellMain(JFrame f1) 
	{
		sellPanel = new JPanel();
		f1.setLayout(new BorderLayout());
		sellPanel.setBackground(new Color(26,44,91));
		sellPanel.setLayout(null);
		setImage(sellButton); //판매
		setImage(memberButton); //회원추가 버튼
		setImage(adminButton); // 관리자 버튼
		setImage(exitButton); //종료 버튼 

		logoImage.setBounds(20,15, 345, 50); 

		sellButton.setBounds(250,150, 200, 200);		
		sellButton.addMouseListener(new MouseListener() {     //제품구매
			@Override
			public void mouseEntered(java.awt.event.MouseEvent e) {
				URL imagesell_clicked = GUI_Seller.class.getClassLoader().getResource("sell_clicked.png");
				ImageIcon sellButton_clicked = new ImageIcon(imagesell_clicked); //제품구매 눌려진 이미지
				sellButton.setIcon(sellButton_clicked);				
			}
			@Override
			public void mouseExited(java.awt.event.MouseEvent e) {
				URL imagesell = GUI_Seller.class.getClassLoader().getResource("sell.png");
				ImageIcon undo = new ImageIcon(imagesell); // 누르고 나갔을때 이미지 
				sellButton.setIcon(undo);
			}
			@Override
			public void mousePressed(java.awt.event.MouseEvent e) {
				JFrame F_pur = new JFrame("제품구매");
				purchase(F_pur);
				F_pur.setPreferredSize(new Dimension(1000,750));
				F_pur.pack();
				F_pur.setVisible(true);			
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub

			}
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}
		});

		memberButton.setBounds(500,150, 200, 200);
		memberButton.addMouseListener(new MouseListener() {       //회원추가
			@Override
			public void mouseEntered(java.awt.event.MouseEvent e) {
				URL imagemember_clicked = GUI_Seller.class.getClassLoader().getResource("member_clicked.png");
				ImageIcon member_clicked = new ImageIcon(imagemember_clicked); //회원추가 눌렀을떄 이미지
				memberButton.setIcon(member_clicked);				
			}
			@Override
			public void mouseExited(java.awt.event.MouseEvent e) {
				URL imagemember = GUI_Seller.class.getClassLoader().getResource("member.png");
				ImageIcon undo = new ImageIcon(imagemember); // 누르고 나갔을때 이미지 
				memberButton.setIcon(undo);

			}
			@Override
			public void mouseClicked(java.awt.event.MouseEvent e) {

			}
			@Override
			public void mousePressed(java.awt.event.MouseEvent e) {
				get_customer_add();

			}
			@Override
			public void mouseReleased(java.awt.event.MouseEvent e) {

			}	
		});


		adminButton.setBounds(250,400, 200, 200);
		adminButton.addMouseListener(new MouseListener() {      //관리자모드
			@Override
			public void mouseEntered(java.awt.event.MouseEvent e) {
				URL imageadmin_clicked = GUI_Seller.class.getClassLoader().getResource("admin_clicked.png");
				ImageIcon admin_clicked = new ImageIcon(imageadmin_clicked); //관리자 모드 눌려진 이미지
				adminButton.setIcon(admin_clicked);				
			}
			@Override
			public void mouseExited(java.awt.event.MouseEvent e) {
				URL imageadmin = GUI_Seller.class.getClassLoader().getResource("admin.png");
				ImageIcon undo = new ImageIcon(imageadmin); // 누르고 나갔을때 이미지 
				adminButton.setIcon(undo);

			}
			@Override
			public void mouseClicked(java.awt.event.MouseEvent e) {

			}
			@Override
			public void mousePressed(java.awt.event.MouseEvent e) {
				GUI_admin ad = new GUI_admin();
				ad.check();
			}
			@Override
			public void mouseReleased(java.awt.event.MouseEvent e) {

			}	
		});

		exitButton.setBounds(500,400, 200, 200);
		exitButton.addMouseListener(new MouseListener() {     //종료
			@Override
			public void mouseEntered(java.awt.event.MouseEvent e) {
				URL imageexit_clicked = GUI_Seller.class.getClassLoader().getResource("exit_clicked.png");
				ImageIcon exit_clicked = new ImageIcon(imageexit_clicked); //종료 눌려진 이미지
				exitButton.setIcon(exit_clicked);				
			}
			@Override
			public void mouseExited(java.awt.event.MouseEvent e) {
				URL imageexit = GUI_Seller.class.getClassLoader().getResource("exit.png");
				ImageIcon undo = new ImageIcon(imageexit); // 누르고 나갔을때 이미지 
				exitButton.setIcon(undo);

			}
			@Override
			public void mouseClicked(java.awt.event.MouseEvent e) {
			}
			@Override
			public void mousePressed(java.awt.event.MouseEvent e) {
				System.exit(1);
			}
			@Override
			public void mouseReleased(java.awt.event.MouseEvent e) {

			}	
		});

		sellPanel.add(logoImage);
		sellPanel.add(sellButton);
		sellPanel.add(memberButton);
		sellPanel.add(adminButton);
		sellPanel.add(exitButton);

	}

	// 텍스트필드 선설정 배경 글꼴 함수
	private void setTextField(JTextField L){
		Border lineBorder = BorderFactory.createLineBorder(Color.white, 4);
		Border emptyBorder = BorderFactory.createEmptyBorder(7, 7, 7, 7);
		L.setBorder(BorderFactory.createCompoundBorder(lineBorder,emptyBorder));
		L.setBackground(new Color(26,44,91));
		L.setForeground(Color.white);
		L.setFont(new Font("굴림",Font.PLAIN,25));
	}



	private void get_customer_add() {
		JFrame F_cadd = new JFrame();
		F_cadd.setTitle("회원추가");
		JPanel caddPanel = new JPanel();
		F_cadd.setLayout(new BorderLayout());
		caddPanel.setLayout(null);
		caddPanel.setBackground(new Color(26,44,91));
		F_cadd.setContentPane(caddPanel);
		
		URL imagename = GUI_Seller.class.getClassLoader().getResource("name.png");
		ImageIcon cname = new ImageIcon(imagename);
		JLabel cnameImage = new JLabel(cname);

		URL imageage = GUI_Seller.class.getClassLoader().getResource("age.png");
		ImageIcon age = new ImageIcon(imageage);
		JLabel ageImage = new JLabel(age);	        

		URL imagePhoneNumber = GUI_Seller.class.getClassLoader().getResource("phoneNumber.png");
		ImageIcon phone = new ImageIcon(imagePhoneNumber);
		JLabel phoneImage = new JLabel(phone);

		
		URL imageokay = GUI_Seller.class.getClassLoader().getResource("okay.png");
		JButton okayButton = new JButton(new ImageIcon(imageokay)); //종료
		setImage(okayButton);



		JTextField cnameField = new JTextField();  //이름 입력
		setTextField(cnameField);     
		JTextField ageField = new JTextField();  //나이
		setTextField(ageField);
		JTextField phoneField = new JTextField(); //전화번호
		setTextField(phoneField);     

		caddPanel.add(cnameImage);
		caddPanel.add(ageImage);
		caddPanel.add(phoneImage);
		caddPanel.add(cnameField);
		caddPanel.add(phoneField);
		caddPanel.add(ageField);
		caddPanel.add(okayButton);

		cnameImage.setBounds(55,72,85,46);
		ageImage.setBounds(55,160,81,47);
		phoneImage.setBounds(13,255,175,44);

		cnameField.setBounds(205,70,250,50);
		phoneField.setBounds(205,250,250,50);
		ageField.setBounds(205,160,250,50);
		okayButton.setBounds(170,360,138,51);
		okayButton.addMouseListener(new MouseListener() {
			@Override
			public void mouseEntered(java.awt.event.MouseEvent e) {
				
				URL imageokay_clicked = GUI_Seller.class.getClassLoader().getResource("okay_clicked.png");
				ImageIcon okayButton_clicked = new ImageIcon(imageokay_clicked); //눌려진 이미지
				okayButton.setIcon(okayButton_clicked);				
			}
			@Override
			public void mouseExited(java.awt.event.MouseEvent e) {
				URL imageokay  = GUI_Seller.class.getClassLoader().getResource("okay.png");
				ImageIcon undo = new ImageIcon(imageokay); // 누르고 나갔을때 이미지 
				okayButton.setIcon(undo);
			}
			@Override
			public void mousePressed(java.awt.event.MouseEvent e) { //회원등록

				if(cnameField.getText().equals("") || phoneField.getText().equals("") || ageField.getText().equals("")) {
					JOptionPane popup = new JOptionPane();
					popup.showMessageDialog(null, "항목을 모두 입력하세요");	
				}
				else {
					if(!dbconnecter.checkCustomer(phoneField.getText())) { //이미 회원으로 등록되어있지않은 경우
						dbconnecter.addCustomer(cnameField.getText(),phoneField.getText(),'B',0,Integer.parseInt(ageField.getText()));
						System.out.println("회원등록완료");
						JOptionPane popup =new JOptionPane();
						popup.showMessageDialog(null, "회원을 등록하였습니다.");
					}
					else {  //회원으로 등록되어있는 경우
						JOptionPane popup =new JOptionPane();
						popup.showMessageDialog(null, "이미 등록된 회원입니다.");
					}
					F_cadd.setVisible(false);
				}
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub

			}
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}
		});


		F_cadd.setSize(500,500);
		F_cadd.setResizable(true);
		F_cadd.setVisible(true);


	}

	private void purchase(JFrame F_pur) {
		URL imagebaadd  = GUI_Seller.class.getClassLoader().getResource("baadd.png");
		JButton baaddButton = new JButton(new ImageIcon(imagebaadd));
		URL imagebuy  = GUI_Seller.class.getClassLoader().getResource("buy.png");
		JButton buyButton = new JButton(new ImageIcon(imagebuy));
		URL imagecancel  = GUI_Seller.class.getClassLoader().getResource("cancel.png");
		JButton cancelButton = new JButton(new ImageIcon(imagecancel));
		
		URL imagecash  = GUI_Seller.class.getClassLoader().getResource("cash.png");
		JButton cashButton = new JButton(new ImageIcon(imagecash));
		
		URL imagecard  = GUI_Seller.class.getClassLoader().getResource("card.png");
		JButton cardButton = new JButton(new ImageIcon(imagecard));
		
		URL imagebafin = GUI_Seller.class.getClassLoader().getResource("bafin.png");
		JButton bafinButton = new JButton(new ImageIcon(imagebafin));
		
		URL imagesellexit = GUI_Seller.class.getClassLoader().getResource("sellexit.png");
		JButton sellexitButton = new JButton(new ImageIcon(imagesellexit));

		print_start();


		purPanel =  new JPanel();
		F_pur.setLayout(new BorderLayout());
		purPanel.setBackground(new Color(26,44,91));
		purPanel.setLayout(null);

		setImage(buyButton);
		setImage(cancelButton);
		setImage(cashButton);
		setImage(cardButton);

		setImage(baaddButton); //바코드 추가 부분
		setImage(bafinButton);  // 추가완료 부분
		setImage(sellexitButton);

		logoImage.setBounds(20,15, 345, 50);
		orderImage.setBounds(50, 125, 501, 500);
		//bacoImage.setBounds(600, 125, 315, 207);

		JTextField bacoField = new JTextField(); 
		setTextField(bacoField);
		JTextField amountField = new JTextField(); 
		setTextField(amountField);

		barcodeImage.setBounds(615, 125, 118, 46);
		numImage.setBounds(615, 180, 80, 46);
		bacoField.setBounds(772, 125, 125, 50);
		amountField.setBounds(772, 180, 125, 50);

		JTextArea addProduct = new JTextArea(); 
		JTextArea addPrice = new JTextArea();
		JTextArea addAmount = new JTextArea();
		JTextArea addallPrice = new JTextArea();
		JTextArea allPrice = new JTextArea();

		//구매 상품 추가 부분
		baaddButton.setBounds(615, 250, 130, 60);
		baaddButton.addMouseListener(new MouseListener() {
			@Override
			public void mouseEntered(java.awt.event.MouseEvent e) {
				URL imagebaadd_clicked = GUI_Seller.class.getClassLoader().getResource("baadd_clicked.png");
				
				ImageIcon baadd_clicked = new ImageIcon(imagebaadd_clicked); //제품구매 눌려진 이미지
				baaddButton.setIcon(baadd_clicked);				
			}
			@Override
			public void mouseExited(java.awt.event.MouseEvent e) {
				URL imagebaadd = GUI_Seller.class.getClassLoader().getResource("baadd.png");
				ImageIcon undo = new ImageIcon(imagebaadd); // 누르고 나갔을때 이미지 
				baaddButton.setIcon(undo);

			}
			@Override
			public void mouseClicked(java.awt.event.MouseEvent e) {
			}
			@Override
			public void mousePressed(java.awt.event.MouseEvent e) {
				if (DB_seller.added_all) {
					DB_seller.added_all = false;
				}
				int onePrice1 = 0;
				Color gray = new Color(0x777777);
				String tmpb = bacoField.getText();
				String tmpnum = amountField.getText();

				if(tmpb.equals("") ||tmpnum.equals("")) {

					JOptionPane popup = new JOptionPane();
					popup.showMessageDialog(null, "항목을 모두 입력하세요");	

				}
				else {
					String addedBarcode = bacoField.getText();
					int amount = Integer.parseInt(amountField.getText());
					int price = dbconnecter.checkProduct(addedBarcode, amount);
					String addedPname = dbconnecter.getpname(addedBarcode);

					if(price >=1) {
						DB_seller.ShoppingBasket_Barcode.add(addedBarcode); //장바구니에 추가
						DB_seller.ShoppingBasket_Amount.add(amount);
						onePrice1 = addBasket(bacoField, amountField, addProduct,  addPrice, addAmount, addallPrice);  
						DB_seller.ShoppingBasket_Price.add(price);
						DB_seller.ShoppingBasket_Pname.add(addedPname);  //상품이름 담아두는 곳
						System.out.println(addedBarcode+" : "+amount+"개");

					}
					else {
						System.out.println("구매할 수 없는 상품입니다.");
						JOptionPane popup =new JOptionPane();
						popup.showMessageDialog(null, "구매할 수 없는 상품입니다.");	
					}

					int tmp_onePrice2 =0;

					String onePrice2 = allPrice.getText();
					if(onePrice2.equals("")) 
						tmp_onePrice2 =0;
					else 
						tmp_onePrice2 = Integer.parseInt(onePrice2);

					int sumPrice = onePrice1 + tmp_onePrice2;
					allPrice.setText(Integer.toString(sumPrice));
					allPrice.setFont(new Font("굴림",Font.PLAIN,25));
					allPrice.setForeground(Color.white);
					allPrice.setBackground(gray);
					purPanel.add(allPrice);
					allPrice.setBounds(450, 587, 100, 30);
				}
			}
			@Override
			public void mouseReleased(java.awt.event.MouseEvent e) {

			}	
		});

		//구매 완료 버튼 부분 - 추가완료
		bafinButton.setBounds(770, 250, 130, 60);
		bafinButton.addMouseListener(new MouseListener() {
			@Override
			public void mouseEntered(java.awt.event.MouseEvent e) {
				URL imagebafin_clicked = GUI_Seller.class.getClassLoader().getResource("bafin_clicked.png");
				ImageIcon bafin_clicked = new ImageIcon(imagebafin_clicked); //눌려진 이미지
				bafinButton.setIcon(bafin_clicked);				
			}
			@Override
			public void mouseExited(java.awt.event.MouseEvent e) {
				URL imagebafin = GUI_Seller.class.getClassLoader().getResource("bafin.png");
				ImageIcon undo = new ImageIcon(imagebafin); // 누르고 나갔을때 이미지 
				bafinButton.setIcon(undo);
			}
			@Override
			public void mousePressed(java.awt.event.MouseEvent e) { 
				DB_seller.added_all = true;	

				dbconnecter.fee = calculation(DB_seller.ShoppingBasket_Price,DB_seller.ShoppingBasket_Amount);
				JOptionPane popup =new JOptionPane();
				popup.showMessageDialog(null, "모든 상품 담기완료 이제 결제가 가능합니다.");			
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub

			}
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}
		});



		//회원구매 액션부분
		buyButton.setBounds(620, 350, 126, 126);
		buyButton.addMouseListener(new MouseListener() {
			@Override
			public void mouseEntered(java.awt.event.MouseEvent e) {
				URL imagebuy_clicked = GUI_Seller.class.getClassLoader().getResource("buy_clicked.png");
				ImageIcon buy_clicked = new ImageIcon(imagebuy_clicked); //눌려진 이미지
				buyButton.setIcon(buy_clicked);				
			}
			@Override
			public void mouseExited(java.awt.event.MouseEvent e) {
				URL imagebuy = GUI_Seller.class.getClassLoader().getResource("buy.png");
				ImageIcon undo = new ImageIcon(imagebuy); // 누르고 나갔을때 이미지 
				buyButton.setIcon(undo);				
			}
			@Override
			public void mousePressed(java.awt.event.MouseEvent e) { 
				
				if(DB_seller.added_all) {
					JFrame F_member = new JFrame();
					get_memberbuy(F_member);  
				}
				else {
					JOptionPane popup =new JOptionPane();
					popup.showMessageDialog(null, "구매완료를 하지않았습니다.구매를 완료하였다면,추가완료 버튼을 눌러주세요.");					
				}
				
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub

			}
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}
		});

		//구매 취소버튼을 누른 경우
		cancelButton.setBounds(770, 350, 126, 126);
		cancelButton.addMouseListener(new MouseListener() {
			@Override
			public void mouseEntered(java.awt.event.MouseEvent e) {
				URL imagecancel_clicked = GUI_Seller.class.getClassLoader().getResource("cancel_clicked.png");
				ImageIcon cancel_clicked = new ImageIcon(imagecancel_clicked); //눌려진 이미지
				cancelButton.setIcon(cancel_clicked);				
			}
			@Override
			public void mouseExited(java.awt.event.MouseEvent e) {
				URL imagecancel = GUI_Seller.class.getClassLoader().getResource("cancel.png");
				ImageIcon undo = new ImageIcon(imagecancel); // 누르고 나갔을때 이미지 
				cancelButton.setIcon(undo);
			}
			@Override
			public void mousePressed(java.awt.event.MouseEvent e) { 
				reset_var();
				DB_seller.ShoppingBasket_Barcode.clear();//바코드 받을 리스트
				DB_seller.ShoppingBasket_Amount.clear(); // 구매양 받을 리스트
				DB_seller.ShoppingBasket_Pname.clear();  // 제품 명 받을 리스트
				DB_seller.ShoppingBasket_Price.clear();   //상품 가격	
				DB_seller.ShoppingBasket_Barcode = new ArrayList<String>();
				DB_seller.ShoppingBasket_Amount = new ArrayList<Integer>();
				DB_seller.ShoppingBasket_Pname = new ArrayList<String>();  // 제품 명 받을 리스트
				DB_seller.ShoppingBasket_Price = new ArrayList<Integer>();

			}
			@Override
			public void mouseClicked(MouseEvent e) {				
			}
			@Override
			public void mouseReleased(MouseEvent e) {					
				F_pur.setVisible(false);
				// TODO Auto-generated method stub

			}

		});

		//현금 결제 액션
		cashButton.setBounds(620, 500, 126, 126);
		cashButton.addMouseListener(new MouseListener() {
			@Override
			public void mouseEntered(java.awt.event.MouseEvent e) {
				URL imagecash_clicked = GUI_Seller.class.getClassLoader().getResource("cash_clicked.png");
				ImageIcon cash_clicked = new ImageIcon(imagecash_clicked); //눌려진 이미지
				
				cashButton.setIcon(cash_clicked);				
			}
			@Override
			public void mouseExited(java.awt.event.MouseEvent e) {
				URL imagecash = GUI_Seller.class.getClassLoader().getResource("cash.png");
				ImageIcon undo = new ImageIcon(imagecash); // 누르고 나갔을때 이미지 
				cashButton.setIcon(undo);
			}
			@Override
			public void mousePressed(java.awt.event.MouseEvent e) { 
				if(DB_seller.added_all) {
					JFrame F_cash = new JFrame("현금결제");
					get_cashcal(F_pur,F_cash,dbconnecter.fee);

				}
				else {
					JOptionPane popup =new JOptionPane();
					popup.showMessageDialog(null, "구매완료를 하지않았습니다.구매를 완료하였다면,추가완료 버튼을 눌러주세요.");					

				}
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub

			}
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}
		});

		//카드결제 액션
		cardButton.setBounds(770, 500, 126, 126);
		cardButton.addMouseListener(new MouseListener() {
			@Override
			public void mouseEntered(java.awt.event.MouseEvent e) {
				URL imagecard_clicked = GUI_Seller.class.getClassLoader().getResource("card_clicked.png");
				ImageIcon card_clicked = new ImageIcon(imagecard_clicked); //눌려진 이미지
				cardButton.setIcon(card_clicked);				
			}
			@Override
			public void mouseExited(java.awt.event.MouseEvent e) {
				URL imagecard  = GUI_Seller.class.getClassLoader().getResource("card.png");
				ImageIcon undo = new ImageIcon(imagecard); // 누르고 나갔을때 이미지 
				cardButton.setIcon(undo);
				
			}
			@Override
			public void mousePressed(java.awt.event.MouseEvent e) { 
				if(DB_seller.added_all) {

					JFrame F_card = new JFrame("카드결제");
					get_cardcal(F_pur, F_card,dbconnecter.fee);   
				}
				else {
					
					JOptionPane popup =new JOptionPane();
					popup.showMessageDialog(null, "구매완료를 하지않았습니다.구매를 완료하였다면,추가완료 버튼을 눌러주세요.");					

				}
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub

			}
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}
		});

		   //구매종료액션
	      sellexitButton.setBounds(885,10, 88, 86);
	      sellexitButton.addMouseListener(new MouseListener() {
	         @Override
	         public void mouseEntered(java.awt.event.MouseEvent e) {
	        	 URL imagesellexit_clicked  = GUI_Seller.class.getClassLoader().getResource("sellexit_clicked.png");
	            ImageIcon sellexit_clicked = new ImageIcon(imagesellexit_clicked); //제품구매 눌려진 이미지
	            sellexitButton.setIcon(sellexit_clicked);            
	         }
	         @Override
	         public void mouseExited(java.awt.event.MouseEvent e) {
				URL imagesellexit  = GUI_Seller.class.getClassLoader().getResource("sellexit.png");
	            ImageIcon undo = new ImageIcon(imagesellexit); // 누르고 나갔을때 이미지 
	            sellexitButton.setIcon(undo);
	         }
	         @Override
	         public void mouseClicked(java.awt.event.MouseEvent e) {

	         }
	         @Override
	         public void mousePressed(java.awt.event.MouseEvent e) {
	            reset_var();
	            DB_seller.ShoppingBasket_Barcode.clear();//바코드 받을 리스트
	            DB_seller.ShoppingBasket_Amount.clear(); // 구매양 받을 리스트
	            DB_seller.ShoppingBasket_Pname.clear();  // 제품 명 받을 리스트
	            DB_seller.ShoppingBasket_Price.clear();   //상품 가격   
	            DB_seller.ShoppingBasket_Barcode = new ArrayList<String>();
	            DB_seller.ShoppingBasket_Amount = new ArrayList<Integer>();
	            DB_seller.ShoppingBasket_Pname = new ArrayList<String>();  // 제품 명 받을 리스트
	            DB_seller.ShoppingBasket_Price = new ArrayList<Integer>();
	            F_pur.setVisible(false);
	         }
	         @Override
	         public void mouseReleased(java.awt.event.MouseEvent e) {

	         }   
	      });

		purPanel.add(logoImage);
		purPanel.add(orderImage);
		purPanel.add(barcodeImage);
		purPanel.add(numImage);
		purPanel.add(bacoField);
		purPanel.add(amountField);
		purPanel.add(baaddButton);
		purPanel.add(bafinButton);
		purPanel.add(buyButton);
		purPanel.add(cancelButton);
		purPanel.add(cashButton);
		purPanel.add(cardButton);
		purPanel.add(sellexitButton);
		F_pur.getContentPane().add(purPanel);


	}

	private int addBasket(JTextField bacoField, JTextField numField,JTextArea addProduct, JTextArea addPrice,JTextArea addAmount,JTextArea addallPrice) {

		String addedBarcode = bacoField.getText();
		int addedAmount = Integer.parseInt(numField.getText());
		int price = dbconnecter.checkProduct(addedBarcode, addedAmount);
		String addedPname = dbconnecter.getpname(addedBarcode);
		Color gray = new Color(0x777777);
		int allPrice = price * addedAmount;

		addProduct.append(addedPname + "\n");
		addProduct.setFont(new Font("굴림",Font.PLAIN,25));
		addProduct.setForeground(Color.white);
		addProduct.setBackground(gray);
		purPanel.add(addProduct);
		addProduct.setBounds(90, 220, 100, 350);


		addPrice.append(Integer.toString(price)+"\n");
		addPrice.setFont(new Font("굴림",Font.PLAIN,25));
		addPrice.setForeground(Color.white);
		addPrice.setBackground(gray);
		purPanel.add(addPrice);
		addPrice.setBounds(260, 220, 60, 350);

		addAmount.append(Integer.toString(addedAmount)+"\n");
		addAmount.setFont(new Font("굴림",Font.PLAIN,25));
		addAmount.setForeground(Color.white);
		addAmount.setBackground(gray);
		purPanel.add(addAmount);
		addAmount.setBounds(380, 220, 60, 350);

		addallPrice.append(Integer.toString(allPrice)+"\n");
		addallPrice.setFont(new Font("굴림",Font.PLAIN,25));
		addallPrice.setForeground(Color.white);
		addallPrice.setBackground(gray);
		purPanel.add(addallPrice);
		addallPrice.setBounds(460, 220, 80, 350);

		bacoField.setText(""); //추가하면 텍스트 clear
		numField.setText("");

		return allPrice;
	}



	// 회원구매창
	private void get_memberbuy(JFrame F_member) {
		F_member.setTitle("회원구매");
		JPanel memberPanel = new JPanel();
		F_member.setLayout(new BorderLayout());
		memberPanel.setLayout(null);
		memberPanel.setBackground(new Color(26,44,91));
		F_member.setContentPane(memberPanel);
		
		URL imagePhoneNumber = GUI_Seller.class.getClassLoader().getResource("phoneNumber.png");		
		ImageIcon PhoneNumber = new ImageIcon(imagePhoneNumber);
		JLabel PhoneNumberImage = new JLabel(PhoneNumber);

		URL imageokay = GUI_Seller.class.getClassLoader().getResource("okay.png");
		JButton okayButton = new JButton(new ImageIcon(imageokay)); //종료
		setImage(okayButton);

		JTextField phoneNumberField = new JTextField();
		setTextField(phoneNumberField);

		memberPanel.add(PhoneNumberImage);
		memberPanel.add(phoneNumberField);
		memberPanel.add(okayButton);

		PhoneNumberImage.setBounds(20,50,175,44);
		phoneNumberField.setBounds(215,50,250,50);

		okayButton.setBounds(180,125,138,51);
		okayButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				if(dbconnecter.checkCustomer(phoneNumberField.getText()))//회원이라면
				{
					System.out.println(phoneNumberField.getText());
					dbconnecter.chkCustomer = true; 
					dbconnecter.phone = phoneNumberField.getText();
					JOptionPane popup =new JOptionPane();
					popup.showMessageDialog(null, "회원 인증완료.");
				}
				else checkRegister();

				F_member.setVisible(false);
			}
		});

		F_member.setSize(500,250);
		F_member.setResizable(true);
		F_member.setVisible(true);

	}

	//등록된 회원이 아니라면 회원가입할껀지 물어보는 메소드
	private void checkRegister() {

		int result = JOptionPane.showConfirmDialog(null, "회원이 아닙니다. 회원추가를 하시겠습니까?","Confirm",JOptionPane.YES_NO_OPTION);
		if(result == JOptionPane.CANCEL_OPTION) {
			return;
		}
		else if(result == JOptionPane.YES_OPTION) {
			get_customer_add();
			JOptionPane popup =new JOptionPane();
			popup.showMessageDialog(null, "회원추가를 하셨으면 회원구매를 다시 눌러주세요.");
			//			dbconnecter.chkCustomer = true; 
		}
		else {
			return;
		}
	}



	//현금결제창
	private void get_cashcal(JFrame F_pur,JFrame F_cash,int pay) {
		F_cash.setTitle("현금결제");
		JPanel cashPanel = new JPanel();
		F_cash.setLayout(new BorderLayout());
		cashPanel.setLayout(null);
		cashPanel.setBackground(new Color(26,44,91));
		F_cash.setContentPane(cashPanel);
		URL imagegetCash = GUI_Seller.class.getClassLoader().getResource("getCash.png");
		ImageIcon InputCash = new ImageIcon(imagegetCash);
		JLabel InputCashImage = new JLabel(InputCash);
		
		URL imageokay = GUI_Seller.class.getClassLoader().getResource("okay.png");
		JButton okayButton = new JButton(new ImageIcon(imageokay)); //종료
		setImage(okayButton);

		JTextField InputCashField = new JTextField();
		setTextField(InputCashField);



		cashPanel.add(InputCashImage);
		cashPanel.add(InputCashField);
		cashPanel.add(okayButton);

		InputCashImage.setBounds(30,55,142,41);
		InputCashField.setBounds(215,50,250,50);

		//오키 버튼 액션
		okayButton.setBounds(180,125,138,51);
		okayButton.addMouseListener(new MouseListener() {
			@Override
			public void mouseEntered(java.awt.event.MouseEvent e) {
				
				URL imageokay_clicked = GUI_Seller.class.getClassLoader().getResource("okay_clicked.png");
				ImageIcon okayButton_clicked = new ImageIcon(imageokay_clicked); //눌려진 이미지
				okayButton.setIcon(okayButton_clicked);				
			}
			@Override
			public void mouseExited(java.awt.event.MouseEvent e) {
				URL imageokay = GUI_Seller.class.getClassLoader().getResource("okay.png");
				ImageIcon undo = new ImageIcon(imageokay); // 누르고 나갔을때 이미지 
				okayButton.setIcon(undo);
			}
			@Override
			public void mousePressed(java.awt.event.MouseEvent e) { 
				int InputCash = Integer.parseInt(InputCashField.getText()); // 받은 금액
				if(InputCash>=pay) { 
					
					JOptionPane popup =new JOptionPane();
					int change = InputCash - pay;
					popup.showMessageDialog(null,  "총금액:" + pay +"원\r\n" + "받은돈 :" + InputCash+"원\r\n" +"거스름돈 :" + change+"원\r\n" );
					DB_seller.added_all = false;
					F_cash.setVisible(false);
					F_pur.removeAll();
					F_pur.setVisible(false);



					if(dbconnecter.chkCustomer) dbconnecter.membercalculation();

					set_books_add(DB_seller.ShoppingBasket_Barcode,DB_seller.ShoppingBasket_Amount,DB_seller.ShoppingBasket_Price,DB_seller.ShoppingBasket_Pname,1);
					product_print(DB_seller.ShoppingBasket_Pname,DB_seller.ShoppingBasket_Amount,DB_seller.ShoppingBasket_Price,dbconnecter.fee,1);
					dbconnecter.renew_pamount(DB_seller.ShoppingBasket_Barcode, DB_seller.ShoppingBasket_Amount);

					reset_var();
					print_finish();
					DB_seller.ShoppingBasket_Barcode.clear();//바코드 받을 리스트
					DB_seller.ShoppingBasket_Amount.clear(); // 구매양 받을 리스트
					DB_seller.ShoppingBasket_Pname.clear();  // 제품 명 받을 리스트
					DB_seller.ShoppingBasket_Price.clear();   //상품 가격	
					DB_seller.ShoppingBasket_Barcode = new ArrayList<String>();
					DB_seller.ShoppingBasket_Amount = new ArrayList<Integer>();
					DB_seller.ShoppingBasket_Pname = new ArrayList<String>();  // 제품 명 받을 리스트
					DB_seller.ShoppingBasket_Price = new ArrayList<Integer>();
				}
				else { // 받은 금액이 적은 경우
					JOptionPane popup =new JOptionPane();
					popup.showMessageDialog(null, "결제금액이 부족합니다.");	
				}

			}
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub

			}
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}
		});

		F_cash.setSize(500,250);
		F_cash.setResizable(true);
		F_cash.setVisible(true);
	}


	//카드결제창
	private void get_cardcal(JFrame F_pur, JFrame F_card,int pay) {

		F_card.setTitle("카드결제");
		JPanel NewWindowMember = new JPanel();
		F_card.setLayout(new BorderLayout());
		NewWindowMember.setLayout(null);
		NewWindowMember.setBackground(new Color(26,44,91));
		F_card.setContentPane(NewWindowMember);
		URL imagecardNum = GUI_Seller.class.getClassLoader().getResource("cardNum.png");
		ImageIcon CardNum = new ImageIcon(imagecardNum);
		JLabel CardNumImage = new JLabel(CardNum);

		URL imageokay = GUI_Seller.class.getClassLoader().getResource("okay.png");
		JButton okayButton = new JButton(new ImageIcon(imageokay)); //종료
		setImage(okayButton);

		JTextField CardNumField = new JTextField();
		setTextField(CardNumField);

		NewWindowMember.add(CardNumImage);
		NewWindowMember.add(CardNumField);
		NewWindowMember.add(okayButton);

		CardNumImage.setBounds(30,55,142,37);
		CardNumField.setBounds(215,50,250,50);

		okayButton.setBounds(180,125,138,51);
		okayButton.addMouseListener(new MouseListener() {
			@Override
			public void mouseEntered(java.awt.event.MouseEvent e) {
				
				URL imageokay_clicked = GUI_Seller.class.getClassLoader().getResource("okay_clicked.png");
				ImageIcon okayButton_clicked = new ImageIcon(imageokay_clicked); //눌려진 이미지
				okayButton.setIcon(okayButton_clicked);				
			}
			@Override
			public void mouseExited(java.awt.event.MouseEvent e) {
				URL imageokay  = GUI_Seller.class.getClassLoader().getResource("okay.png");
				ImageIcon undo = new ImageIcon(imageokay); // 누르고 나갔을때 이미지 
				okayButton.setIcon(undo);
			}
			@Override
			public void mousePressed(java.awt.event.MouseEvent e) { 

				if(CardNumField.getText().equals("")) {
					JOptionPane popup =new JOptionPane();
					popup.showMessageDialog(null, "카드번호를 입력해주세요");	

				}
				else {
					JOptionPane popup =new JOptionPane();
					popup.showMessageDialog(null, "카드 결제완료 결제금액:"+pay);	
					DB_seller.added_all = false;
					F_pur.removeAll();
					F_pur.setVisible(false);
					F_card.setVisible(false);


					if(dbconnecter.chkCustomer) dbconnecter.membercalculation();

					set_books_add(DB_seller.ShoppingBasket_Barcode,DB_seller.ShoppingBasket_Amount,DB_seller.ShoppingBasket_Price,DB_seller.ShoppingBasket_Pname,0);
					product_print(DB_seller.ShoppingBasket_Pname,DB_seller.ShoppingBasket_Amount,DB_seller.ShoppingBasket_Price,dbconnecter.fee,0);
					dbconnecter.renew_pamount(DB_seller.ShoppingBasket_Barcode, DB_seller.ShoppingBasket_Amount);
					reset_var();
					print_finish();
					DB_seller.ShoppingBasket_Barcode.clear();//바코드 받을 리스트
					DB_seller.ShoppingBasket_Amount.clear(); // 구매양 받을 리스트
					DB_seller.ShoppingBasket_Pname.clear();  // 제품 명 받을 리스트
					DB_seller.ShoppingBasket_Price.clear();   //상품 가격	
					DB_seller.ShoppingBasket_Barcode = new ArrayList<String>();
					DB_seller.ShoppingBasket_Amount = new ArrayList<Integer>();
					DB_seller.ShoppingBasket_Pname = new ArrayList<String>();  // 제품 명 받을 리스트
					DB_seller.ShoppingBasket_Price = new ArrayList<Integer>();


				}



			}
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub

			}
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}
		});
		F_card.setSize(500,250);
		F_card.setResizable(true);
		F_card.setVisible(true);
	}

	//fee를 계산하는 메소드
	private int calculation(ArrayList<Integer> prod_count_price,ArrayList<Integer> count_list) {	//물품 번호,물품 개수를 이용해서 비용을 확인 
		int fee =0;
		for (int i = 0; i < count_list.size(); i++) {
			fee += prod_count_price.get(i)
					* count_list.get(i);
		}

		System.out.println(count_list.size() + "개/ 총 금액 계산완료 =" + fee);// 구입물품의 개수, 총 금액 출력
		return fee; //총금액 호출
	}


	//장부를 기록하는 메소드
	private void set_books_add(ArrayList<String> item_list,ArrayList<Integer> count_list,ArrayList<Integer> shoppingBasket_Price,ArrayList<String> pname_list,int cash) {
		for(int i = 0;i<item_list.size();i++) {
			if(cash != 0)
				dbconnecter.addBooks(item_list.get(i), count_list.get(i), shoppingBasket_Price.get(i), pname_list.get(i),"cash");//값 저장.
			else dbconnecter.addBooks(item_list.get(i), count_list.get(i), shoppingBasket_Price.get(i), pname_list.get(i),"card");
		}
		System.out.println("장부 기록이 완료되었습니다.");

	}


	//db 변수들을  reset하는 메소드
	private void reset_var() {
		dbconnecter.chkCustomer = false; 
		dbconnecter.fee=0;
		dbconnecter.phone = null;
	}


	//영수증을 작성하는 메소드
	private void product_print(ArrayList<String> pname_list, ArrayList<Integer> count_list,ArrayList<Integer> price_list, int fee, int cash) {
		BufferedWriter bw;//쓰기
		BufferedReader br;//읽기

		try {
			bw = new BufferedWriter(new FileWriter("receipt.txt", true));
			bw.write("=======================\r\n");
			bw.write("제품이름\t\t제품수\t가격\r\n");
			bw.write("------------------------------------------");
			bw.newLine();
			for(int i = 0;i<pname_list.size();i++) {
				String data = pname_list.get(i)+"\t\t"+ count_list.get(i)+"\t"+ (count_list.get(i)*price_list.get(i))+ "\n";
				bw.write(data.toString());
				bw.newLine();
			}
			String method;
			if(cash != 0) method="cash";
			else method="card";
			bw.write("------------------------------------------\r\n");
			bw.write("\n");
			bw.write("결제 방법\t\t"+ method);
			bw.newLine();
			bw.write("총    금액\t\t"+ fee);
			bw.write("\r\n=======================");
			bw.newLine();
			bw.flush();
			bw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//구매종료시 - 영수증 띄우기
	private void print_finish() {
		try {
			Desktop.getDesktop().edit(new File("receipt.txt"));
		} catch (IOException e) {
			System.out.println("영수증이 없습니다.");
			e.printStackTrace();
		}
		System.out.println("영수증을 출력하였습니다.");
	}

	//구매시시작시  - 영수증생성
	private void print_start() { 
		try {
			BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("receipt.txt"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}