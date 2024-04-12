package app;

import java.awt.SystemColor;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;

import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Cursor;
import javax.swing.DropMode;

public class Frm_XacThuc extends JFrame{
	private JTextField txtEmail;
	private JPanel pnBGR;
 
	public Frm_XacThuc() {
		getContentPane().setBackground(SystemColor.controlHighlight);
		setTitle("Đăng Nhập");
		setSize(800, 500);

		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(true);
		setLocationRelativeTo(null);
		setBgr();
	}
	
	protected void setBgr() {
		getContentPane().setLayout(null);
		 pnBGR = new JPanel();
		pnBGR.setBounds(0, 0, 786, 463);
		getContentPane().add(pnBGR);
		
		
	    // Nạp ảnh từ tệp
        ImageIcon originalIcon = new ImageIcon(Frm_XacThuc.class.getResource("/imgs/bg_chot1.png"));
        Image image = originalIcon.getImage();
        // resize lại ảnh
        Image resizedImage = image.getScaledInstance(800, 500, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon = new ImageIcon(resizedImage);
		pnBGR.setLayout(null);
		
		JButton btnXoaTrang = new JButton("");
		btnXoaTrang.setIcon(new ImageIcon(Frm_XacThuc.class.getResource("/imgs/icon_lammoi.png")));
		btnXoaTrang.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
		btnXoaTrang.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		JButton btnSend = new JButton("");
		btnSend.setIcon(new ImageIcon(Frm_XacThuc.class.getResource("/imgs/icon_send-message.png")));
		btnSend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		JLabel lblVuiLngKim = new JLabel("vui lòng kiểm tra và xác thực!");
		lblVuiLngKim.setToolTipText("");
		lblVuiLngKim.setForeground(Color.WHITE);
		lblVuiLngKim.setFont(new Font("Tahoma", Font.ITALIC, 32));
		lblVuiLngKim.setBounds(61, 186, 675, 49);
		pnBGR.add(lblVuiLngKim);
		btnSend.setHideActionText(true);
		btnSend.setForeground(new Color(117, 154, 169));
		btnSend.setFont(new Font("Tahoma", Font.BOLD, 22));
		btnSend.setFocusPainted(false);
		btnSend.setBackground(SystemColor.control);
		btnSend.setBounds(484, 383, 143, 54);
		pnBGR.add(btnSend);
		btnXoaTrang.setHideActionText(true);
		btnXoaTrang.setForeground(new Color(117, 154, 169));
		btnXoaTrang.setFont(new Font("Tahoma", Font.BOLD, 22));
		btnXoaTrang.setFocusPainted(false);
		btnXoaTrang.setBackground(SystemColor.control);
		btnXoaTrang.setBounds(148, 383, 143, 54);
		pnBGR.add(btnXoaTrang);
		
		txtEmail = new JTextField();
//		txtEmail.setDropMode(DropMode.ON_OR_INSERT_COLS);
		txtEmail.setFont(new Font("Tahoma", Font.PLAIN, 24));
		txtEmail.setBounds(61, 305, 232, 51);
		pnBGR.add(txtEmail);
		txtEmail.setColumns(10);
		
		JLabel lbOTP = new JLabel("OTP:");
		lbOTP.setForeground(Color.WHITE);
		lbOTP.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lbOTP.setBounds(61, 245, 125, 58);
		pnBGR.add(lbOTP);
		
		JLabel lbTextAbout = new JLabel("Mã xác thực đã được gửi đến mail của bạn,");
		lbTextAbout.setToolTipText("");
		lbTextAbout.setForeground(Color.WHITE);
		lbTextAbout.setFont(new Font("Tahoma", Font.ITALIC, 32));
		lbTextAbout.setBounds(61, 139, 675, 49);
		pnBGR.add(lbTextAbout);
		
		JLabel lbNamePro = new JLabel("Karaoke ETC");
		pnBGR.add(lbNamePro);
		lbNamePro.setForeground(Color.CYAN);
		lbNamePro.setHorizontalAlignment(SwingConstants.CENTER);
		lbNamePro.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 50));
		lbNamePro.setDoubleBuffered(true);
		lbNamePro.setBounds(173, 0, 440, 80);
		//add ảnh
		JLabel lbBGR = new JLabel(resizedIcon);
		lbBGR.setBounds(0, 0, 800, 500);
		pnBGR.add(lbBGR);
	}
	

	
	public static void main(String[] args) {
		new Frm_XacThuc().setVisible(true);
	}
}
