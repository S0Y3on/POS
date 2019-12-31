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
	public static Scanner scan;//���� �Է��ϴ� ��.

	//private static DB_administrator dbconnecter = new DB_administrator();
	private static DB_administrator dbconnecter = DB_administrator.getInstacne();

	private JPanel adPanel;
	URL imagePrecordButton = GUI_admin.class.getClassLoader().getResource("PrecordButton.png");
	private JButton PrecordButton = new JButton(new ImageIcon(imagePrecordButton)); //��ǰ���
	
	URL imagePlookupButton = GUI_admin.class.getClassLoader().getResource("PlookupButton.png");
	private JButton PlookupButton = new JButton(new ImageIcon(imagePlookupButton)); //��ǰ��ȸ
	
	URL imagePsearchButton = GUI_admin.class.getClassLoader().getResource("PsearchButton.png");
	private JButton PsearchButton = new JButton(new ImageIcon(imagePsearchButton)); //��ǰ�˻�
	
	URL imageaddPamountButton = GUI_admin.class.getClassLoader().getResource("addPamountButton.png");
	private JButton addPamountButton = new JButton(new ImageIcon(imageaddPamountButton)); //����߰�
	
	URL imageClookupButton = GUI_admin.class.getClassLoader().getResource("ClookupButton.png");
	private JButton ClookupButton = new JButton(new ImageIcon(imageClookupButton)); //����ȸ
	
	URL imageCsearchButton = GUI_admin.class.getClassLoader().getResource("CsearchButton.png");
	private JButton CsearchButton = new JButton(new ImageIcon(imageCsearchButton)); //���˻�
	
	URL imageBlookupButton = GUI_admin.class.getClassLoader().getResource("BlookupButton.png");
	private JButton BlookupButton = new JButton(new ImageIcon(imageBlookupButton)); //�����ȸ
	
	URL imageexitButton = GUI_admin.class.getClassLoader().getResource("exitButton.png");
	private JButton exitButton = new JButton(new ImageIcon(imageexitButton)); //����
	URL imagelogo = GUI_admin.class.getClassLoader().getResource("logo.png");
	private ImageIcon logo = new ImageIcon(imagelogo);
	private JLabel logoImage = new JLabel(logo);


	public GUI_admin() 
	{
		super("MarketPos");
		scan = new Scanner(System.in);//���� �Է��ϴ� ��ü ����
	}
	
	public void check() {
		
		JFrame F_login = new JFrame();
		F_login.setTitle("������ �α���");
		
		JPanel NewWindowMember = new JPanel();
		F_login.setLayout(new BorderLayout());
		NewWindowMember.setLayout(null);
		NewWindowMember.setBackground(new Color(26,44,91));
		F_login.setContentPane(NewWindowMember);
		
		URL imagepassword = GUI_admin.class.getClassLoader().getResource("password.png");
		ImageIcon password = new ImageIcon(imagepassword);
		JLabel passwordImage = new JLabel(password);
		
		URL imageokay = GUI_admin.class.getClassLoader().getResource("okay.png");
		JButton okayButton = new JButton(new ImageIcon(imageokay)); //����
		
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
				ImageIcon okay_clicked = new ImageIcon(imageokay_clicked); //��ǰ���� ������ �̹���
				okayButton.setIcon(okay_clicked);				
			}
			@Override
			public void mouseExited(java.awt.event.MouseEvent e) {
				URL imageokay = GUI_admin.class.getClassLoader().getResource("okay.png");
				ImageIcon undo = new ImageIcon(imageokay); // ������ �������� �̹��� 
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
					System.out.println("�н����尡 ���� �ʽ��ϴ�.");
					JOptionPane popup =new JOptionPane();
					popup.showMessageDialog(null, "��й�ȣ�� Ʋ�Ƚ��ϴ�!");
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
	

	private void AdminGo(JFrame F_ad) {//DBconnector class ����.
		System.out.println("������ ���α׷��� �����մϴ�.");
		AdminMain(F_ad); //�Ǹ��� �ʱ�ȭ��
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
		L.setFont(new Font("����",Font.PLAIN,25));
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

		logoImage.setBounds(20,15, 345, 50);     //�ΰ�

		PrecordButton.setBounds(85,150,200,200);    //��ǰ���
		PrecordButton.addMouseListener(new MouseListener() {
			@Override
			public void mouseEntered(java.awt.event.MouseEvent e) {
				URL imagePrecordButton_clicked = GUI_admin.class.getClassLoader().getResource("PrecordButton_clicked.png");
				ImageIcon PrecordButton_clicked = new ImageIcon(imagePrecordButton_clicked); //��ǰ���� ������ �̹���
				PrecordButton.setIcon(PrecordButton_clicked);				
			}
			@Override
			public void mouseExited(java.awt.event.MouseEvent e) {
				URL imagePrecordButton  = GUI_admin.class.getClassLoader().getResource("PrecordButton.png");
				ImageIcon undo = new ImageIcon(imagePrecordButton); // ������ �������� �̹��� 
				PrecordButton.setIcon(undo);

			}
			@Override
			public void mousePressed(java.awt.event.MouseEvent e) {
				get_product_add();     //��ǰ ����� ���ڸ� �Է¹޴� �Լ�
			}
			@Override
			public void mouseClicked(java.awt.event.MouseEvent e) {
			}	
			@Override
			public void mouseReleased(java.awt.event.MouseEvent e) {

			}	
		});
		PlookupButton.setBounds(290,150, 200, 200);   //��ǰ��ȸ
		PlookupButton.addMouseListener(new MouseListener() {
			@Override
			public void mouseEntered(java.awt.event.MouseEvent e) {
				URL imagePlookupButton_clicked = GUI_admin.class.getClassLoader().getResource("PlookupButton_clicked.png");
				ImageIcon PlookupButton_clicked = new ImageIcon(imagePlookupButton_clicked); //��ǰ���� ������ �̹���
				PlookupButton.setIcon(PlookupButton_clicked);				
			}
			@Override
			public void mouseExited(java.awt.event.MouseEvent e) {
				URL imagePlookupButton  = GUI_admin.class.getClassLoader().getResource("PlookupButton.png");
				
				ImageIcon undo = new ImageIcon(imagePlookupButton); // ������ �������� �̹��� 
				PlookupButton.setIcon(undo);

			}
			@Override
			public void mousePressed(java.awt.event.MouseEvent e) {
				JFrame F_pdisplay = new JFrame("��ǰ��ȸ");
				F_pdisplay.setLocationRelativeTo(null);
				F_pdisplay.setSize(500,500);
				F_pdisplay.setVisible(true);


				ArrayList<String> data = dbconnecter.pdisplay();    
				DefaultListModel<String> m = new DefaultListModel<>();
				for(int i = 0 ;i<data.size();i++) {
					m.addElement(data.get(i)); //����Ʈ ��ü ���
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


		PsearchButton.setBounds(495,150, 200, 200);   //��ǰ�˻�
		PsearchButton.addMouseListener(new MouseListener() {
			@Override
			public void mouseEntered(java.awt.event.MouseEvent e) {
				URL imagePsearchButton_clicked = GUI_admin.class.getClassLoader().getResource("PsearchButton_clicked.png");
				ImageIcon PsearchButton_clicked = new ImageIcon(imagePsearchButton_clicked); //��ǰ���� ������ �̹���
				PsearchButton.setIcon(PsearchButton_clicked);				
			}
			@Override
			public void mouseExited(java.awt.event.MouseEvent e) {
				URL imagePsearchButton  = GUI_admin.class.getClassLoader().getResource("PsearchButton.png");
				ImageIcon undo = new ImageIcon(imagePsearchButton); // ������ �������� �̹��� 
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
		addPamountButton.setBounds(695,150,200,200);    //����߰�
		addPamountButton.addMouseListener(new MouseListener() {
			@Override
			public void mouseEntered(java.awt.event.MouseEvent e) {
				
				URL imageaddPamountButton_clicked  = GUI_admin.class.getClassLoader().getResource("addPamountButton_clicked.png");
				ImageIcon addPamountButton_clicked = new ImageIcon(imageaddPamountButton_clicked); //��ǰ���� ������ �̹���
				addPamountButton.setIcon(addPamountButton_clicked);				
			}
			@Override
			public void mouseExited(java.awt.event.MouseEvent e) {
				URL imageaddPamountButton  = GUI_admin.class.getClassLoader().getResource("addPamountButton.png");
				ImageIcon undo = new ImageIcon(imageaddPamountButton); // ������ �������� �̹��� 
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
		ClookupButton.setBounds(85,350,200,200);    //����ȸ
		ClookupButton.addMouseListener(new MouseListener() {
			@Override
			public void mouseEntered(java.awt.event.MouseEvent e) {
				URL imageClookupButton_clicked  = GUI_admin.class.getClassLoader().getResource("ClookupButton_clicked.png");
				ImageIcon ClookupButton_clicked = new ImageIcon(imageClookupButton_clicked); //��ǰ���� ������ �̹���
				ClookupButton.setIcon(ClookupButton_clicked);				
			}
			@Override
			public void mouseExited(java.awt.event.MouseEvent e) {
				URL imageClookupButton = GUI_admin.class.getClassLoader().getResource("ClookupButton.png");
				ImageIcon undo = new ImageIcon(imageClookupButton); // ������ �������� �̹��� 
				ClookupButton.setIcon(undo);

			}
			@Override
			public void mousePressed(java.awt.event.MouseEvent e) {
				JFrame F_psearch = new JFrame("����ȸ");
				F_psearch.setLocationRelativeTo(null);
				F_psearch.setSize(500,500);
				F_psearch.setVisible(true);
				
				ArrayList<String> data = dbconnecter.cdisplay();    
				DefaultListModel<String> m = new DefaultListModel<>();
				for(int i = 0 ;i<data.size();i++) {
					m.addElement(data.get(i)); //����Ʈ ��ü ���
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


		CsearchButton.setBounds(290,350,200,200);   //���˻�
		CsearchButton.addMouseListener(new MouseListener() {
			@Override
			public void mouseEntered(java.awt.event.MouseEvent e) {
				URL imageCsearchButton_clicked = GUI_admin.class.getClassLoader().getResource("CsearchButton_clicked.png");
				ImageIcon CsearchButton_clicked = new ImageIcon(imageCsearchButton_clicked); //��ǰ���� ������ �̹���
				CsearchButton.setIcon(CsearchButton_clicked);				
			}
			@Override
			public void mouseExited(java.awt.event.MouseEvent e) {
				URL imageCsearchButton = GUI_admin.class.getClassLoader().getResource("CsearchButton.png");
				ImageIcon undo = new ImageIcon(imageCsearchButton); // ������ �������� �̹��� 
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
		
		BlookupButton.setBounds(495,350,200,200);   //�����ȸ
		BlookupButton.addMouseListener(new MouseListener() {
			@Override
			public void mouseEntered(java.awt.event.MouseEvent e) {
				URL imageBlookupButton_clicked = GUI_admin.class.getClassLoader().getResource("BlookupButton_clicked.png");
				ImageIcon BlookupButton_clicked = new ImageIcon(imageBlookupButton_clicked); //��ǰ���� ������ �̹���
				BlookupButton.setIcon(BlookupButton_clicked);				
			}
			@Override
			public void mouseExited(java.awt.event.MouseEvent e) {
				URL imageBlookupButton = GUI_admin.class.getClassLoader().getResource("BlookupButton.png");
				ImageIcon undo = new ImageIcon(imageBlookupButton); // ������ �������� �̹��� 
				BlookupButton.setIcon(undo);

			}
			@Override
			public void mousePressed(java.awt.event.MouseEvent e) {
				JFrame F_pdisplay = new JFrame("�����ȸ");
				F_pdisplay.setLocationRelativeTo(null);
				F_pdisplay.setSize(500,500);
				F_pdisplay.setVisible(true);
				ArrayList<String> data = dbconnecter.bdisplay(); 
				//
				System.out.println(data);

				DefaultListModel<String> m = new DefaultListModel<>();
				for(int i = 0 ;i<data.size();i++) {
					m.addElement(data.get(i)); //����Ʈ ��ü ���
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


		exitButton.setBounds(695,350, 200, 200);    //����
		exitButton.addMouseListener(new MouseListener() {
			@Override
			public void mouseEntered(java.awt.event.MouseEvent e) {
				URL imageexitButton_clicked = GUI_admin.class.getClassLoader().getResource("exitButton_clicked.png");
				ImageIcon exitButton_clicked = new ImageIcon(imageexitButton_clicked); //��ǰ���� ������ �̹���
				exitButton.setIcon(exitButton_clicked);				
			}
			@Override
			public void mouseExited(java.awt.event.MouseEvent e) {
				URL imageexitButton  = GUI_admin.class.getClassLoader().getResource("exitButton.png");
				ImageIcon undo = new ImageIcon(imageexitButton); // ������ �������� �̹��� 
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

	
	//��� �߰��ϴ� �޼�
	private void get_product_add() {

		JFrame F_padd = new JFrame("��ǰ���");
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
		JButton okayButton = new JButton(new ImageIcon(imageokay)); //Ȯ��
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

		paddPanel.add(barcodeImage); //���ڵ�
		paddPanel.add(pnameImage);	//��ǰ�̸�
		paddPanel.add(ptypeImage);	//��ǰ����
		paddPanel.add(pamountImage);	//��ǰ����
		paddPanel.add(expirationImage);	//�������
		paddPanel.add(priceImage);	//��ǰ����

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
				ImageIcon okay_clicked = new ImageIcon(imageokay_clicked); //��ǰ���� ������ �̹���
				okayButton.setIcon(okay_clicked);				
			}
			@Override
			public void mouseExited(java.awt.event.MouseEvent e) {
				URL imageokay = GUI_admin.class.getClassLoader().getResource("okay.png");
				ImageIcon undo = new ImageIcon(imageokay); // ������ �������� �̹��� 
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
					popup.showMessageDialog(null, "�׸��� ��� �Է��ϼ���");	
				}

				else {
					boolean flag = false;
					if( pnameField.getText().length() >6 ) { 
						JOptionPane popup = new JOptionPane();
						popup.showMessageDialog(null, "��ǰ�̸��� 6���� �̳��� �Է����ּ���");
						flag = true;
					}
					else if(  ptypeField.getText().length() >5 ) {  
						JOptionPane popup = new JOptionPane();
						popup.showMessageDialog(null, "��ǰ������ 5���� �̳��� �Է����ּ���");
						flag = true;
					}

					try {
						if(Integer.parseInt(priceField.getText()) > 1000000){
							JOptionPane popup = new JOptionPane();
							popup.showMessageDialog(null, "��ǰ�� ������ 100���� �̳��� �������ּ���");
							flag = true;
						}
					}catch (Exception ce){
						JOptionPane popup = new JOptionPane();
						popup.showMessageDialog(null, "��ǰ�� ������ 100���� �̳��� �������ּ���");
						flag = true;

					}
					try {
						if (Integer.parseInt(pamountField.getText()) > 1000 ) {
							JOptionPane popup = new JOptionPane();
							popup.showMessageDialog(null, "��ǰ�� ������ 1000�� �̳��� �������ּ���");
							flag = true;
						}
					}catch (Exception ce){
						JOptionPane popup = new JOptionPane();
						popup.showMessageDialog(null, "��ǰ�� ������ 1000�� �̳��� �������ּ���");
						flag = true;

					}
					if(!flag) {
						if(!dbconnecter.checkBarcode(barcodeField.getText())) {
							dbconnecter.addProduct(barcodeField.getText(), pnameField.getText(),ptypeField.getText(), pamountField.getText(), expirationField.getText(),Integer.parseInt(priceField.getText()));//�� ���� - ��ǰ���
							System.out.println("����� �Ϸ� �Ǿ����ϴ�.");	
							F_padd.setVisible(false);
						}
						else {
							JOptionPane popup = new JOptionPane();
							popup.showMessageDialog(null, "�̹� ��ϵ� ���ڵ尡 �ֽ��ϴ�.");	
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
		JFrame psearch = new JFrame("��ǰ�˻�");
		JPanel psearchPanel = new JPanel();


		setLayout(new BorderLayout());
		psearchPanel.setLayout(null);
		psearchPanel.setBackground(new Color(26,44,91));
		setContentPane(psearchPanel);
		URL imagebarcode = GUI_admin.class.getClassLoader().getResource("barcode.png");
		ImageIcon barcode = new ImageIcon(imagebarcode);
		JLabel barcodeImage = new JLabel(barcode);

		URL imageokay = GUI_admin.class.getClassLoader().getResource("okay.png");
		JButton okayButton = new JButton(new ImageIcon(imageokay)); //����
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
				ImageIcon okay_clicked = new ImageIcon(imageokay_clicked); //��ǰ���� ������ �̹���
				okayButton.setIcon(okay_clicked);				
			}
			@Override
			public void mouseExited(java.awt.event.MouseEvent e) {
				URL imageokay  = GUI_admin.class.getClassLoader().getResource("okay.png");
				ImageIcon undo = new ImageIcon(imageokay); // ������ �������� �̹��� 
				okayButton.setIcon(undo);

			}
			@Override
			public void mouseClicked(java.awt.event.MouseEvent e) {

			}
			@Override
			public void mousePressed(java.awt.event.MouseEvent e) {
				JFrame result = new JFrame("�˻����");		   
				String data = dbconnecter.psearch(barcodeField.getText());   
				if(!data.equals("no")) {
					System.out.println("��ǰ�� ã�ҽ��ϴ�");
					Label L = new Label(data);
					Font f1 = new Font("����", Font.ITALIC, 28);
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
		JFrame pupdate = new JFrame("����߰�");
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
		JButton okayButton = new JButton(new ImageIcon(imageokay)); //����
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
				ImageIcon okay_clicked = new ImageIcon(imageokay_clicked); //��ǰ���� ������ �̹���
				okayButton.setIcon(okay_clicked);				
			}
			@Override
			public void mouseExited(java.awt.event.MouseEvent e) {
				URL imageokay  = GUI_admin.class.getClassLoader().getResource("okay.png");
				ImageIcon undo = new ImageIcon(imageokay); // ������ �������� �̹��� 
				okayButton.setIcon(undo);

			}
			@Override
			public void mouseClicked(java.awt.event.MouseEvent e) {

			}
			@Override
			public void mousePressed(java.awt.event.MouseEvent e) {
				if(barcodeField.getText().equals("") || amountField.getText().equals("")) {
					JOptionPane popup = new JOptionPane();
					popup.showMessageDialog(null, "�׸��� ��� �Է��ϼ���");	
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
		JFrame csearch = new JFrame("���˻�");
		JPanel get_csearch = new JPanel();
		setLayout(new BorderLayout());
		get_csearch.setLayout(null);
		get_csearch.setBackground(new Color(26,44,91));
		setContentPane(get_csearch);
		URL imagephoneNumber = GUI_admin.class.getClassLoader().getResource("phoneNumber.png");
		ImageIcon name = new ImageIcon(imagephoneNumber);
		JLabel phoneImage = new JLabel(name);


		URL imageokay  = GUI_admin.class.getClassLoader().getResource("okay.png");
		JButton okayButton = new JButton(new ImageIcon(imageokay)); //����
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
				ImageIcon okay_clicked = new ImageIcon(imageokay_clicked); //��ǰ���� ������ �̹���
				okayButton.setIcon(okay_clicked);				
			}
			@Override
			public void mouseExited(java.awt.event.MouseEvent e) {
				URL imageokay  = GUI_admin.class.getClassLoader().getResource("okay.png");
				ImageIcon undo = new ImageIcon(imageokay); // ������ �������� �̹��� 
				okayButton.setIcon(undo);

			}
			@Override
			public void mouseClicked(java.awt.event.MouseEvent e) {

			}
			@Override
			public void mousePressed(java.awt.event.MouseEvent e) {
				JFrame result = new JFrame("�˻����");		   
				String data = dbconnecter.csearch(phoneField.getText());   
				if(!data.equals("no")) {
					System.out.println("ȸ���� ã�ҽ��ϴ�");
					Label L = new Label(data);
					result.add(L);
					Font f1 = new Font("����", Font.ITALIC, 28);
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


