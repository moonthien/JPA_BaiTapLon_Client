package app;

import javax.swing.JFrame;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.Image;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.JPanel;
import java.awt.Panel;
import java.awt.Label;
import java.awt.Canvas;
import javax.swing.JSlider;
import javax.swing.JTextField;

import java.awt.SystemColor;
import java.awt.CardLayout;
import javax.swing.JDesktopPane;
import java.awt.FlowLayout;
import java.awt.TextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JToggleButton;
import java.awt.Rectangle;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import javax.swing.event.MenuListener;

import connectDB.ConnectDB;
import dao.DanhSachNhanVien;
import dao.DanhSachTaiKhoan;
import entitys.NhanVien;
import entitys.TaiKhoan;

import java.awt.Cursor;
import java.awt.ComponentOrientation;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

public class Frm_DoiMatKhau extends JFrame implements ActionListener {
	private JButton btnXacNhan;
	private JPanel formDangNhap, pnTaiKhoan;
	private JTextField txtTaiKhoan;
	private JPasswordField pwdNv;
	private JLabel lbPass, lbBGR, lbBgTrai;
	private Frm_Chinh frmChinh;
	public TaiKhoan tk;
	private DanhSachTaiKhoan dsTK;

	public JPanel get_FrmSendMail() {
		return this.formDangNhap;
	}

	public Frm_DoiMatKhau(TaiKhoan tk) {
		this.tk = tk;
		ConnectDB.getInstance().connect();
		frmChinh = new Frm_Chinh();
		getContentPane().setBackground(SystemColor.controlHighlight);
		setTitle("Thay đổi mật khẩu");
		setSize(1200, 500);

		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(true);
		setLocationRelativeTo(null);
		dsTK = new DanhSachTaiKhoan();
		showGui();
	}

	private void formDangNhap() {
		btnDangNhap();

		JPanel pnTaiKhoan = new JPanel();
		pnTaiKhoan.setBackground(new Color(255, 255, 255, 0));
		pnTaiKhoan.setLayout(null);
		pnTaiKhoan.setBounds(35, 78, 549, 51);
		formDangNhap.add(pnTaiKhoan);

		JLabel lbNewPass = new JLabel("Mật khẩu mới:");
		lbNewPass.setForeground(new Color(255, 255, 255));
		lbNewPass.setBounds(0, 7, 167, 36);
		pnTaiKhoan.add(lbNewPass);
		lbNewPass.setFont(new Font("Tahoma", Font.BOLD, 22));

		txtTaiKhoan = new JTextField();
		txtTaiKhoan.setIgnoreRepaint(true);
		txtTaiKhoan.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		txtTaiKhoan.setBackground(SystemColor.window);
		txtTaiKhoan.setFont(new Font("Dialog", Font.PLAIN, 20));
		txtTaiKhoan.setBounds(227, 8, 300, 36);
		pnTaiKhoan.add(txtTaiKhoan);

		JPanel pnMatKhau = new JPanel();
		pnMatKhau.setBackground(new Color(255, 255, 255, 0));
		pnMatKhau.setLayout(null);
		pnMatKhau.setBounds(35, 151, 549, 51);
		formDangNhap.add(pnMatKhau);

		JLabel lbNhapLai = new JLabel("Nhập lại mật khẩu:");
		lbNhapLai.setForeground(new Color(255, 255, 255));
		lbNhapLai.setBackground(new Color(240, 240, 240));
		lbNhapLai.setBounds(2, 7, 214, 36);
		pnMatKhau.add(lbNhapLai);
		lbNhapLai.setFont(new Font("Tahoma", Font.BOLD, 22));

		pwdNv = new JPasswordField();
		pwdNv.setBackground(SystemColor.window);
		pwdNv.setFont(new Font("Tahoma", Font.PLAIN, 18));
		pwdNv.setBounds(226, 7, 301, 36);
		pnMatKhau.add(pwdNv);

		lbPass = new JLabel("Thay đổi mật khẩu");
		lbPass.setBounds(210, 20, 300, 45);
		lbPass.setForeground(new Color(255, 255, 255));
		lbPass.setFont(new Font("Tahoma", Font.BOLD, 30));
		formDangNhap.add(lbPass);

		// add sự kiện nút xác nhận
		btnXacNhan.addActionListener(this);
	}

	private void btnDangNhap() {
		btnXacNhan = new FixButton("Đăng Nhập");
		btnXacNhan.setText("Xác Nhận");
		btnXacNhan.setFocusPainted(false);
		btnXacNhan.setHideActionText(true);
		btnXacNhan.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnXacNhan.setFont(new Font("Tahoma", Font.BOLD, 22));
		btnXacNhan.setBounds(224, 257, 171, 51);
		formDangNhap.add(btnXacNhan);
	}

	private void opacity() {
		JPanel panelHeader = new JPanel();
		panelHeader.setBackground(new Color(117, 154, 169, 100)); // 100 là alpha
		panelHeader.setBounds(0, 0, 800, 85);

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

		formDangNhap = new JPanel();
		formDangNhap.setBounds(505, 74, 620, 331);
		formDangNhap.setForeground(new Color(255, 140, 0));
		formDangNhap.setBackground(new Color(17, 85, 136));
		formDangNhap.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(240, 248, 255)));
		pnBGR.add(formDangNhap);
		formDangNhap.setLayout(null);

		ImageIcon originalIcon = new ImageIcon(Frm_DoiMatKhau.class.getResource("/imgs/bg_chot1.png"));
		Image image = originalIcon.getImage();
		// resize lại ảnh
		Image resizedImage = image.getScaledInstance(800, 500, Image.SCALE_SMOOTH);
		ImageIcon resizedIcon = new ImageIcon(resizedImage);
		pnBGR.setLayout(null);

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
		Date ngayHienTai = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String ngayHienTaiChuoi = sdf.format(ngayHienTai);
	}

	public void showGui() {
		getContentPane().setLayout(null);
		// bgr
		setBgr();
		// form
		formDangNhap();
	}

	private boolean comparePass() {
		String maNV = txtTaiKhoan.getText().toString().trim();
		String mk = pwdNv.getText().toString().trim();
		if (maNV.equals(mk) && !maNV.equals("")) {
			return true;
		}
		return false;
	}

	public boolean doiMatKhau() {
		if (comparePass()) {
			this.setVisible(false);
			dsTK.updatePassword(tk.getMaNhanVien(), txtTaiKhoan.getText().trim());
			return true;
		} else {
			JOptionPane.showMessageDialog(this, "Không được để trống và mật khẩu phải khớp !!!");
		}
		return false;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if (o == btnXacNhan) {
			Boolean fl = doiMatKhau();
			if (fl == true) {
				JOptionPane.showMessageDialog(this, "Thay đổi mật khẩu thành công !");
				Frm_DangNhap frmDangNhap = new Frm_DangNhap();
				frmDangNhap.setVisible(true);
			}
		}
	}
}