package Gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import org.apache.log4j.BasicConfigurator;

import data.BenhNhan;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JSplitPane;
import javax.swing.JList;
import javax.swing.JOptionPane;

import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import java.io.StringReader;

import javax.swing.SwingConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class KhamBenh extends JFrame {

	/**
	 * 
	 */
	FileWriter fw = null; 
	BufferedWriter bw = null; 
	PrintWriter pw = null;
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextArea txtDiaChi;
	private JTextField txtMa;
	private JTextField txtCMND;
	private JTextField txtTen; 
	private JTextArea txtKhamBenh;
	static DefaultListModel benhNhanModel = new DefaultListModel();
	JList listBenhNhan = new JList(benhNhanModel);
	static ArrayList<BenhNhan> listBn = new ArrayList<BenhNhan>();

	/**
	 * Launch the application.
	 * @throws JMSException 
	 * @throws NamingException 
	 */
	public static void main(String[] args) throws NamingException, JMSException {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					KhamBenh frame = new KhamBenh();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		loadDataMQ();
	}

	/**
	 * Create the frame.
	 */
	public KhamBenh() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 550, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.X_AXIS));
		
		JPanel panel = new JPanel();
		contentPane.add(panel);
		panel.setLayout(null);
		
		JSplitPane splitPane = new JSplitPane();
		splitPane.setBounds(0, 0, 539, 353);
		panel.add(splitPane);
		
		JPanel pNhapKham = new JPanel();
		splitPane.setRightComponent(pNhapKham);
		pNhapKham.setLayout(null);
		
		txtMa = new JTextField();
		txtMa.setEnabled(false);
		txtMa.setBounds(118, 80, 233, 20);
		pNhapKham.add(txtMa);
		txtMa.setColumns(10);
		
		txtCMND = new JTextField();
		txtCMND.setEnabled(false);
		txtCMND.setColumns(10);
		txtCMND.setBounds(118, 111, 233, 20);
		pNhapKham.add(txtCMND);
		
		txtTen = new JTextField();
		txtTen.setEnabled(false);
		txtTen.setColumns(10);
		txtTen.setBounds(118, 142, 233, 20);
		pNhapKham.add(txtTen);
		
		txtKhamBenh = new JTextArea();
		txtKhamBenh.setBounds(20, 252, 331, 53);
		pNhapKham.add(txtKhamBenh);
		
		JButton btnLuu = new JButton("C\u1EADp nh\u1EADt th\u00F4ng tin kh\u00E1m b\u1EC7nh");
		btnLuu.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String ma = txtMa.getText();
				String cmnd = txtCMND.getText();
				String ten = txtTen.getText();
				String diachi = txtDiaChi.getText();
				String khambenh = txtKhamBenh.getText();
				
				try { 
					try {
						fw = new FileWriter("benhnhan.txt", true);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} 
					bw = new BufferedWriter(fw); 
					pw = new PrintWriter(bw); 
					pw.println("Mã số bệnh nhân: " + ma); 
					pw.println("Số CMND: " + cmnd); 
					pw.println("Họ và tên: " + ten);
					pw.println("Địa chỉ: " + diachi);
					pw.println("Thông tin khám bệnh: " + khambenh);
					pw.println("Ngày Khám: " + new Date()); 
					pw.flush(); 
				} finally { 
					try { 
						pw.close(); 
						bw.close(); 
						fw.close(); 
					} catch (Exception io) {
						// can't do anything } } }
					}
				}
				txtMa.setText("");txtCMND.setText("");txtTen.setText("");txtDiaChi.setText("");txtKhamBenh.setText("");
			      JOptionPane.showInputDialog(this, "Nội dung khám đã được cập nhật");
			}
		});
		btnLuu.setBounds(20, 316, 331, 23);
		pNhapKham.add(btnLuu);
		
		JLabel lblNewLabel = new JLabel("M\u00E3 s\u1ED1 b\u1EC7nh nh\u00E2n");
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setBounds(10, 83, 100, 14);
		pNhapKham.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("S\u1ED1 CMND");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1.setBounds(10, 114, 98, 14);
		pNhapKham.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("H\u1ECD v\u00E0 t\u00EAn");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_2.setBounds(10, 145, 98, 14);
		pNhapKham.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("\u0110\u1ECBa ch\u1EC9");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_3.setBounds(10, 176, 100, 14);
		pNhapKham.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Kh\u00E1m B\u1EC7nh");
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_4.setBounds(10, 11, 341, 58);
		pNhapKham.add(lblNewLabel_4);
		
		txtDiaChi = new JTextArea();
		txtDiaChi.setEnabled(false);
		txtDiaChi.setBounds(118, 173, 233, 47);
		pNhapKham.add(txtDiaChi);
		
		JLabel lblNewLabel_5 = new JLabel("Nh\u1EADp n\u1ED9i dung kh\u00E1m");
		lblNewLabel_5.setBounds(20, 231, 142, 14);
		pNhapKham.add(lblNewLabel_5);
		
		JPanel pList = new JPanel();
		splitPane.setLeftComponent(pList);
		pList.setLayout(new BorderLayout(0, 0));
		
		JButton btnGoiKham = new JButton("          Gọi khám          ");
		btnGoiKham.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!listBenhNhan.isSelectionEmpty()) {
					String benhNhanID = (String) listBenhNhan.getSelectedValue();
					for (BenhNhan bn : listBn){
				         if (bn.getBenhNhanID().contains(benhNhanID)){
				               txtMa.setText(bn.getBenhNhanID());
				               txtCMND.setText(bn.getSoCMND());
				               txtTen.setText(bn.getHoTen());
				               txtDiaChi.setText(bn.getDiaChi());
				               
				         }
				      }
				}
				int index = listBenhNhan.getSelectedIndex();
				benhNhanModel.removeElementAt(index);
			}
		});
		pList.add(btnGoiKham, BorderLayout.SOUTH);
		pList.add(listBenhNhan, BorderLayout.CENTER);
	}
	
	static void loadDataMQ() throws NamingException, JMSException {
		BasicConfigurator.configure();
		Properties settings=new Properties();
		settings.setProperty(Context.INITIAL_CONTEXT_FACTORY,"org.apache.activemq.jndi.ActiveMQInitialContextFactory");
		settings.setProperty(Context.PROVIDER_URL, "tcp://localhost:61616");
		Context ctx=new InitialContext(settings);
		Object obj=ctx.lookup("ConnectionFactory");
		ConnectionFactory factory=(ConnectionFactory)obj;
		Destination destination =(Destination) ctx.lookup("dynamicQueues/nhanbenh");
		Connection con=factory.createConnection("admin","admin");
		con.start();
		Session session=con.createSession(
		/*transaction*/false,
		/*ACK*/Session.CLIENT_ACKNOWLEDGE
		);
		MessageConsumer receiver = session.createConsumer(destination);
		receiver.setMessageListener(new MessageListener() {
		@Override
			public void onMessage(Message msg) {
				try {
					if(msg instanceof TextMessage){
						TextMessage tm=(TextMessage)msg;
						String txt=tm.getText();
						JAXBContext jaxbContext;
						jaxbContext = JAXBContext.newInstance(BenhNhan.class);              
					    Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
					    System.out.println(jaxbUnmarshaller);
					    BenhNhan bn = (BenhNhan) jaxbUnmarshaller.unmarshal(new StringReader(txt));
					    listBn.add(bn);
					    benhNhanModel.addElement(bn.getBenhNhanID());
					    
						msg.acknowledge();
					}else if(msg instanceof ObjectMessage){
						ObjectMessage om=(ObjectMessage)msg;
						System.out.println(om);
					}
				}catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}
}
