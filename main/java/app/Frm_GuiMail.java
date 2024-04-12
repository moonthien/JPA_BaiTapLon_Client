package app;

import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Image;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import connectDB.ConnectDB;
import dao.DanhSachNhanVien;
import dao.DanhSachTaiKhoan;
import entitys.NhanVien;
import entitys.TaiKhoan;
import mail.Config;

import javax.swing.UIManager;
import javax.swing.border.MatteBorder;

public class Frm_GuiMail extends JFrame implements ActionListener {
	private JButton btnSend;
	private JPanel pnSendMail;
	private JTextField txtMail;
	private JLabel lbBGR, lbBgTrai;
	Date ngayHienTai;
	ImageIcon originalIcon, resizedIcon;
	JLabel lbQuenMatKhau;
	Image image, resizedImage;
	public TaiKhoan tk;

	public JPanel get_FrmSendMail() {
		return this.pnSendMail;
	}

	public Frm_GuiMail(TaiKhoan tk) {
		this.tk = tk;
		getContentPane().setBackground(SystemColor.controlHighlight);
		setTitle("Gửi Mail");
		setSize(1200, 500);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(true);
		setLocationRelativeTo(null);
		showGui();
	}

	private void formSendMail() {
		btnSend();

		JLabel lbMatKhau_1 = new JLabel("");
		lbMatKhau_1.setIcon(new ImageIcon(Frm_GuiMail.class.getResource("/imgs/icon_lock.png")));
		lbMatKhau_1.setForeground(Color.WHITE);
		lbMatKhau_1.setFont(new Font("Tahoma", Font.BOLD, 22));
		lbMatKhau_1.setBackground(UIManager.getColor("Button.background"));
		lbMatKhau_1.setBounds(38, 70, 64, 72);
		pnSendMail.add(lbMatKhau_1);

		lbQuenMatKhau = new JLabel("Quên mật khẩu?");
		lbQuenMatKhau.setForeground(SystemColor.window);
		lbQuenMatKhau.setFont(new Font("Tahoma", Font.BOLD, 22));
		lbQuenMatKhau.setBackground(UIManager.getColor("Button.background"));
		lbQuenMatKhau.setBounds(112, 70, 196, 72);
		pnSendMail.add(lbQuenMatKhau);

		JLabel lbTextNoti = new JLabel("Vui lòng nhập email để lấy mã xác thực OTP!");
		lbTextNoti.setForeground(new Color(255, 255, 255));
		lbTextNoti.setFont(new Font("Tahoma", Font.BOLD, 22));
		lbTextNoti.setBackground(UIManager.getColor("Button.background"));
		lbTextNoti.setBounds(48, 162, 522, 36);
		pnSendMail.add(lbTextNoti);

		JLabel lbEmail = new JLabel("Email:");
		lbEmail.setBounds(48, 208, 90, 36);
		pnSendMail.add(lbEmail);
		lbEmail.setForeground(new Color(255, 255, 255));
		lbEmail.setBackground(new Color(240, 240, 240));
		lbEmail.setFont(new Font("Tahoma", Font.BOLD, 22));

		txtMail = new JTextField();
		txtMail.setBounds(48, 239, 316, 47);
		pnSendMail.add(txtMail);
		txtMail.setIgnoreRepaint(true);
		txtMail.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		txtMail.setBackground(SystemColor.window);
		txtMail.setFont(new Font("Dialog", Font.PLAIN, 20));

	}

	private void opacity() {
		JPanel panelHeader = new JPanel();
		panelHeader.setBackground(new Color(117, 154, 169, 100)); // 100 là alpha
		panelHeader.setBounds(0, 0, 800, 85);

	}

	private void btnSend() {
		JLabel lbSendMail = new JLabel("Bảo mật tài khoản");
		lbSendMail.setBackground(new Color(192, 192, 192));
		lbSendMail.setForeground(new Color(255, 255, 255));
		lbSendMail.setFont(new Font("Tahoma", Font.BOLD, 30));
		lbSendMail.setBounds(210, 20, 300, 45);
		pnSendMail.add(lbSendMail);
		btnSend = new FixButton("Đăng Nhập");
		btnSend.setText("Gửi");
		btnSend.setFocusPainted(false);
		btnSend.setHideActionText(true);
		btnSend.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnSend.setFont(new Font("Tahoma", Font.BOLD, 22));
		btnSend.setBounds(422, 242, 148, 46);
		pnSendMail.add(btnSend);
	}

	// set background
	private void setBgr() {
		lbNgayHienTai();
		opacity();
		getContentPane().setLayout(null);
		JPanel pnBGR = new JPanel();
		pnBGR.setBounds(0, 0, 1200, 500);
		getContentPane().add(pnBGR);
		pnBGR.setLayout(null);

		FixButton2 btnQuayLai = new FixButton2("");
		btnQuayLai.setIcon(new ImageIcon(Frm_GuiMail.class.getResource("/imgs/btn_quaylai.png")));
		btnQuayLai.setHideActionText(true);
		btnQuayLai.setFont(new Font("Tahoma", Font.BOLD, 22));
		btnQuayLai.setFocusPainted(false);
		btnQuayLai.setBounds(0, 0, 91, 40);
		pnBGR.add(btnQuayLai);
		
		pnSendMail = new JPanel();
		pnSendMail.setBounds(505, 74, 620, 331);
		pnSendMail.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(240, 248, 255)));
		pnBGR.add(pnSendMail);
		pnSendMail.setBackground(new Color(17, 85, 136));
		pnSendMail.setLayout(null);

		originalIcon = new ImageIcon(Frm_DangNhap.class.getResource("/imgs/bg_chot1.png"));
		Image image = originalIcon.getImage();
		// resize lại ảnh
		resizedImage = image.getScaledInstance(800, 500, Image.SCALE_SMOOTH);
		resizedIcon = new ImageIcon(resizedImage);
		pnBGR.setLayout(null);

		btnQuayLai.addActionListener(this);

		// add ảnh
		lbBGR = new JLabel();
		lbBGR.setIcon(new ImageIcon(Frm_DangNhap.class.getResource("/imgs/bg_login_phai1.png")));
		lbBGR.setBackground(new Color(0, 100, 230));
		lbBGR.setBounds(430, 0, 760, 500);
		pnBGR.add(lbBGR);

		lbBgTrai = new JLabel("");
		lbBgTrai.setIcon(new ImageIcon(Frm_DangNhap.class.getResource("/imgs/bg_login_trai1.png")));
		lbBgTrai.setBounds(0, 0, 440, 500);
		pnBGR.add(lbBgTrai);
	}

	// Hiển thị ngày hiện tại
	public void lbNgayHienTai() {
		ngayHienTai = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String ngayHienTaiChuoi = sdf.format(ngayHienTai);
	}

	public void showGui() {
		getContentPane().setLayout(null);
		// bgr
		setBgr();
		formSendMail();
		btnSend.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object o = e.getSource();
		if (o == btnSend) {
			sendMail();
		} else {
			Frm_DangNhap fmDangNhap = new Frm_DangNhap();
			fmDangNhap.setVisible(true);
			this.setVisible(false);
		}
	}

	public void sendMail() {
		if (ktraDuLieu()) {
			String mail = txtMail.getText().trim();
			Config sendMail = new Config(mail);
			sendMail.main(null);// send mail
			String rd = sendMail.getRd();
			JOptionPane.showMessageDialog(this, "Đang gửi mail xác thực, vui lòng đợi trong giây lát ^^!");
			xoaTrang();
			Frm_NhapOTP frmOtp = new Frm_NhapOTP(tk, rd);
			frmOtp.setVisible(true);
			this.setVisible(false);
		}
	}

	// kiểm tra regex
	public boolean ktraDuLieu() {
		String email = txtMail.getText();
		if (email.equals("") || !email.matches("[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}")) {
			JOptionPane.showMessageDialog(this, "Email không được để trống và phải đúng định dạng,\nVD: xxx@gmail.com");
			txtMail.requestFocus();
			return false;
		}
		return true;
	}

	public void xoaTrang() {
		txtMail.setText("");
	}
}
