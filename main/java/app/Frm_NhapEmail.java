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

public class Frm_NhapEmail extends JFrame implements ActionListener {
	private JTextField txtEmail;
	private JPanel pnBGR;
	FixButton btnQL, btnXoaTrang, btnSend;

	public Frm_NhapEmail() {
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
		ImageIcon originalIcon = new ImageIcon(Frm_NhapEmail.class.getResource("/imgs/bg_chot1.png"));
		Image image = originalIcon.getImage();
		// resize lại ảnh
		Image resizedImage = image.getScaledInstance(800, 500, Image.SCALE_SMOOTH);
		ImageIcon resizedIcon = new ImageIcon(resizedImage);
		pnBGR.setLayout(null);

		btnXoaTrang = new FixButton("");
		btnXoaTrang.setIcon(new ImageIcon(Frm_NhapEmail.class.getResource("/imgs/icon_lammoi.png")));
		btnXoaTrang.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnXoaTrang.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtEmail.setText("");
			}
		});

		btnSend = new FixButton("");
		btnSend.setIcon(new ImageIcon(Frm_NhapEmail.class.getResource("/imgs/icon_send-message.png")));
		btnSend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});

		btnQL = new FixButton("");
		btnQL.setIcon(new ImageIcon(Frm_NhapEmail.class.getResource("/imgs/btn_quaylai.png")));
		btnQL.setHideActionText(true);
		btnQL.setForeground(new Color(117, 154, 169));
		btnQL.setFont(new Font("Tahoma", Font.BOLD, 22));
		btnQL.setFocusPainted(false);
		btnQL.setBackground(SystemColor.control);
		btnQL.setBounds(74, 387, 143, 54);
		pnBGR.add(btnQL);
		btnSend.setHideActionText(true);
		btnSend.setForeground(new Color(117, 154, 169));
		btnSend.setFont(new Font("Tahoma", Font.BOLD, 22));
		btnSend.setFocusPainted(false);
		btnSend.setBackground(SystemColor.control);
		btnSend.setBounds(589, 305, 143, 54);
		pnBGR.add(btnSend);
		btnXoaTrang.setHideActionText(true);
		btnXoaTrang.setForeground(new Color(117, 154, 169));
		btnXoaTrang.setFont(new Font("Tahoma", Font.BOLD, 22));
		btnXoaTrang.setFocusPainted(false);
		btnXoaTrang.setBackground(SystemColor.control);
		btnXoaTrang.setBounds(383, 387, 143, 54);
		pnBGR.add(btnXoaTrang);

		txtEmail = new JTextField();
		txtEmail.setFont(new Font("Tahoma", Font.PLAIN, 24));
		txtEmail.setBounds(61, 305, 482, 51);
		pnBGR.add(txtEmail);
		txtEmail.setColumns(10);

		JLabel lbEmail = new JLabel("Email:");
		lbEmail.setForeground(Color.WHITE);
		lbEmail.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lbEmail.setBounds(61, 245, 125, 58);
		pnBGR.add(lbEmail);

		JLabel lbTextAbout = new JLabel("Vui lòng nhập email để lấy mã xác thực...");
		lbTextAbout.setForeground(Color.WHITE);
		lbTextAbout.setFont(new Font("Tahoma", Font.ITALIC, 32));
		lbTextAbout.setBounds(61, 186, 656, 49);
		pnBGR.add(lbTextAbout);

		JLabel iconQuenPass = new JLabel("Quên mật Khẩu");
		iconQuenPass.setForeground(new Color(192, 192, 192));
		iconQuenPass.setFont(new Font("Tahoma", Font.BOLD, 38));
		iconQuenPass.setIcon(new ImageIcon(Frm_NhapEmail.class.getResource("/imgs/icon_lock.png")));
		iconQuenPass.setBounds(61, 100, 382, 64);
		pnBGR.add(iconQuenPass);

		JLabel lbNamePro = new JLabel("Karaoke ETC");
		pnBGR.add(lbNamePro);
		lbNamePro.setForeground(Color.CYAN);
		lbNamePro.setHorizontalAlignment(SwingConstants.CENTER);
		lbNamePro.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 50));
		lbNamePro.setDoubleBuffered(true);
		lbNamePro.setBounds(173, 0, 440, 80);
		// add ảnh
		JLabel lbBGR = new JLabel(resizedIcon);
		lbBGR.setBounds(0, 0, 800, 500);
		pnBGR.add(lbBGR);
		btnQL.addActionListener(this);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object o = e.getSource();
		if (o == btnQL)
			this.dispose();
	}
}
