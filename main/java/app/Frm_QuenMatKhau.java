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
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.ActionEvent;
import java.awt.Cursor;
import javax.swing.DropMode;
import javax.swing.JPasswordField;

public class Frm_QuenMatKhau extends JFrame implements ActionListener, MouseListener{
//	private JPanel pnBGR;
	private JTextField textField;
	private JPasswordField passwordField;
 
	public Frm_QuenMatKhau() {
		getContentPane().setBackground(SystemColor.controlHighlight);
		setTitle("Quên Mật Khẩu");
		setSize(800, 500);

		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(true);
		setLocationRelativeTo(null);
		setBgr();
	}
	
	protected void setBgr() {
		getContentPane().setLayout(null);
		JPanel pnBGR = new JPanel();
		pnBGR.setBounds(0, 0, 786, 463);
		getContentPane().add(pnBGR);
		
		
	    // Nạp ảnh từ tệp
        ImageIcon originalIcon = new ImageIcon(Frm_QuenMatKhau.class.getResource("/imgs/bg_chot1.png"));
        Image image = originalIcon.getImage();
        // resize lại ảnh
        Image resizedImage = image.getScaledInstance(800, 500, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon = new ImageIcon(resizedImage);
		pnBGR.setLayout(null);
		
		JButton btnXoaTrang = new JButton("");
		btnXoaTrang.setIcon(new ImageIcon(Frm_QuenMatKhau.class.getResource("/imgs/icon_lammoi.png")));
		btnXoaTrang.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnXoaTrang.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		JButton btnSend = new JButton("");
		btnSend.setIcon(new ImageIcon(Frm_QuenMatKhau.class.getResource("/imgs/icon_send-message.png")));
		btnSend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		passwordField = new JPasswordField();
		passwordField.setFont(new Font("Tahoma", Font.PLAIN, 24));
		passwordField.setBounds(305, 236, 395, 48);
		pnBGR.add(passwordField);
		
		JLabel lbNLMK = new JLabel("Nhập Lại Mật Khẩu:");
		lbNLMK.setForeground(Color.WHITE);
		lbNLMK.setFont(new Font("Tahoma", Font.BOLD, 22));
		lbNLMK.setBounds(56, 236, 247, 48);
		pnBGR.add(lbNLMK);
		
		textField = new JTextField();
		textField.setColumns(20);
//		textField.setHorizontalAlignment(SwingConstants.LEFT);
		textField.setFont(new Font("Tahoma", Font.PLAIN, 28));
		textField.setBackground(new Color(255, 255, 255));// khi click vào sẽ set new colo về màu trắng lại, nên làm như thế sẽ đẹp hơn
		
		textField.setBounds(305, 136, 395, 48);
		pnBGR.add(textField);
		
		
		JLabel lbMkm = new JLabel("Mật Khẩu Mới:");
		lbMkm.setForeground(new Color(255, 255, 255));
		lbMkm.setFont(new Font("Tahoma", Font.BOLD, 22));
		lbMkm.setBounds(56, 138, 180, 48);
		pnBGR.add(lbMkm);
		btnSend.setHideActionText(true);
		btnSend.setForeground(new Color(117, 154, 169));
		btnSend.setFont(new Font("Tahoma", Font.BOLD, 22));
		btnSend.setFocusPainted(false);
		btnSend.setBackground(SystemColor.control);
		btnSend.setBounds(484, 355, 143, 54);
		pnBGR.add(btnSend);
		btnXoaTrang.setHideActionText(true);
		btnXoaTrang.setForeground(new Color(117, 154, 169));
		btnXoaTrang.setFont(new Font("Tahoma", Font.BOLD, 22));
		btnXoaTrang.setFocusPainted(false);
		btnXoaTrang.setBackground(SystemColor.control);
		btnXoaTrang.setBounds(148, 355, 143, 54);
		pnBGR.add(btnXoaTrang);
		
		JLabel lbNamePro = new JLabel("Karaoke ETC");
		pnBGR.add(lbNamePro);
		lbNamePro.setForeground(Color.CYAN);
		lbNamePro.setHorizontalAlignment(SwingConstants.CENTER);
		lbNamePro.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 50));
		lbNamePro.setDoubleBuffered(true);
		lbNamePro.setBounds(187, 10, 440, 80);
		//add ảnh
		JLabel lbBGR = new JLabel(resizedIcon);
		lbBGR.setBackground(Color.WHITE);
		lbBGR.setHorizontalAlignment(SwingConstants.LEADING);
		lbBGR.setFont(new Font("Tahoma", Font.BOLD, 24));
		lbBGR.setBounds(0, 0, 800, 500);
		pnBGR.add(lbBGR);
		
		textField.addMouseListener(this);
	}
	

	
	public static void main(String[] args) {
		new Frm_QuenMatKhau().setVisible(true);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		textField.setBackground(new Color(255,255,255));
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
