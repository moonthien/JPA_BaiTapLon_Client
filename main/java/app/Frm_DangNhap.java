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
import javax.swing.Timer;

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
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JToggleButton;
import java.awt.Rectangle;
import javax.swing.border.LineBorder;
import javax.swing.event.MenuListener;

//import connectDB.ConnectDB;
//import dao.DanhSachNhanVien;
//import dao.DanhSachTaiKhoan;
import entitys.NhanVien;
import entitys.TaiKhoan;

import java.awt.Cursor;
import java.awt.ComponentOrientation;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.BevelBorder;
import javax.swing.border.MatteBorder;


public class Frm_DangNhap extends JFrame implements MouseListener, ActionListener {
	private JButton btnDangNhap;
	private JPanel formDangNhap, pnTaiKhoan, pnMatKhau, panelHeader;
	private JTextField txtTaiKhoan;
	private JPasswordField pwdNv;
	private JLabel lbLogin, lbTenTaiKhoan_1, lbQuenMatKhau, lbIconUser, lbIconLock, lbMatKhau, lbBgTrai, lbBGR;
	private Frm_Chinh frmChinh;
	public TaiKhoan tk;
//	private DanhSachTaiKhoan dsTk;
//	private DanhSachNhanVien dsNV;

	public Frm_DangNhap() {
//		ConnectDB.getInstance().connect();
		frmChinh = new Frm_Chinh();
		getContentPane().setBackground(SystemColor.controlHighlight);
		setTitle("Đăng nhập");
		setSize(1200, 500);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(true);
		setLocationRelativeTo(null);
//		dsTk = new DanhSachTaiKhoan();
//		dsNV = new DanhSachNhanVien();
		showGui();
	}

	private void formDangNhap() {
		btnDangNhap();
		pnTaiKhoan = new JPanel();
		pnTaiKhoan.setBackground(new Color(255, 255, 255, 0));
		pnTaiKhoan.setLayout(null);
		pnTaiKhoan.setBounds(35, 78, 549, 51);
		formDangNhap.add(pnTaiKhoan);

		lbTenTaiKhoan_1 = new JLabel("Tài Khoản:");
		lbTenTaiKhoan_1.setForeground(new Color(255, 255, 255));
		lbTenTaiKhoan_1.setBounds(0, 7, 140, 36);
		pnTaiKhoan.add(lbTenTaiKhoan_1);
		lbTenTaiKhoan_1.setFont(new Font("Tahoma", Font.BOLD, 22));

		txtTaiKhoan = new JTextField();
		txtTaiKhoan.setText("NV001");
		txtTaiKhoan.setIgnoreRepaint(true);
		txtTaiKhoan.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		txtTaiKhoan.setBackground(Color.WHITE);
		txtTaiKhoan.setFont(new Font("Dialog", Font.PLAIN, 20));
		txtTaiKhoan.setBounds(152, 7, 373, 36);
		txtTaiKhoan.setBorder(new MatteBorder(0, 0, 5, 0, (Color) Color.WHITE));
		pnTaiKhoan.add(txtTaiKhoan);

		pnMatKhau = new JPanel();
		pnMatKhau.setBackground(new Color(255, 255, 255, 0));
		pnMatKhau.setLayout(null);
		pnMatKhau.setBounds(35, 151, 549, 51);
		formDangNhap.add(pnMatKhau);

		lbMatKhau = new JLabel("Mật Khẩu:");
		lbMatKhau.setForeground(new Color(255, 255, 255));
		lbMatKhau.setBackground(new Color(240, 240, 240));
		lbMatKhau.setBounds(2, 7, 140, 36);
		pnMatKhau.add(lbMatKhau);
		lbMatKhau.setFont(new Font("Tahoma", Font.BOLD, 22));

		pwdNv = new JPasswordField();
		pwdNv.setText("nv001");
		pwdNv.setBackground(Color.WHITE);
		pwdNv.setFont(new Font("Tahoma", Font.PLAIN, 18));
		pwdNv.setBounds(152, 7, 373, 36);
		pnMatKhau.add(pwdNv);

		lbLogin = new JLabel("LOGIN");
		lbLogin.setForeground(new Color(255, 255, 255));
		lbLogin.setFont(new Font("Tahoma", Font.BOLD, 30));
		lbLogin.setBounds(290, 21, 215, 45);
		formDangNhap.add(lbLogin);

		lbQuenMatKhau = new JLabel("Quên mật khẩu?");
		lbQuenMatKhau.setForeground(new Color(255, 255, 255));
		lbQuenMatKhau.setFont(new Font("Tahoma", Font.ITALIC, 18));
		lbQuenMatKhau.setBounds(428, 212, 139, 25);
		formDangNhap.add(lbQuenMatKhau);

		lbIconUser = new JLabel("");
		lbIconUser.setIcon(new ImageIcon(Frm_DangNhap.class.getResource("/imgs/icon_user.png")));
		lbIconUser.setBounds(570, 88, 32, 32);
		formDangNhap.add(lbIconUser);

		lbIconLock = new JLabel("");
		lbIconLock.setIcon(new ImageIcon(Frm_DangNhap.class.getResource("/imgs/icon_block.png")));
		lbIconLock.setBounds(570, 160, 32, 32);
		formDangNhap.add(lbIconLock);
		lbQuenMatKhau.addMouseListener(this);

	}

	private void btnDangNhap() {
		btnDangNhap = new FixButton2("Đăng nhập");
		btnDangNhap.setFocusPainted(false);
		btnDangNhap.setHideActionText(true);
		btnDangNhap.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnDangNhap.setFont(new Font("Tahoma", Font.BOLD, 22));
		btnDangNhap.setBounds(250, 257, 171, 51);
		btnDangNhap.setBackground(new Color(254, 255, 212));
		formDangNhap.add(btnDangNhap);
	}

	private void opacity() {
		panelHeader = new JPanel();
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
		formDangNhap.setForeground(new Color(255, 140, 0));
		formDangNhap.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(240, 248, 255)));
		formDangNhap.setBounds(505, 74, 620, 331);
		pnBGR.add(formDangNhap);
		formDangNhap.setBackground(new Color(17, 85, 136));
		formDangNhap.setLayout(null);

		ImageIcon originalIcon = new ImageIcon(Frm_DangNhap.class.getResource("/imgs/bg_chot1.png"));
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

		btnDangNhap.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object o = e.getSource();
		if (o.equals(btnDangNhap)) {
			dangNhap();
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		Object o = e.getSource();
		if (o == lbQuenMatKhau) {
			quenMatKhau();
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stu0385788328b

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		lbQuenMatKhau.setForeground(new Color(227, 204, 0));
		lbQuenMatKhau.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(227, 204, 0)));

	}

	@Override
	public void mouseExited(MouseEvent e) {
		lbQuenMatKhau.setForeground(Color.WHITE);
		lbQuenMatKhau.setBorder(new MatteBorder(0, 0, 0, 0, (Color) new Color(227, 204, 0)));
		btnDangNhap.setBackground(new Color(254, 255, 212));
		// TODO Auto-generated method stub
	}

	public void dangNhap() {
//		String maNV = txtTaiKhoan.getText().toString().trim();
//		String mk = pwdNv.getText().toString().trim();
//		boolean isTrue = regexDangNhap(maNV, mk);
//		if (isTrue) {
//			tk = dsTk.getTaiKhoanTheoMa(maNV);
//			if (tk == null) {
//				JOptionPane.showMessageDialog(this, "Tài khoản không đúng!\nVui lòng kiểm tra lại.");
//				txtTaiKhoan.requestFocus();
//			} else if (!tk.getMatKhau().equals(mk)) {
//				JOptionPane.showMessageDialog(this, "Mật khẩu không đúng!\nVui lòng kiểm tra lại.");
//				pwdNv.requestFocus();
//			} else {
//				NhanVien nv = dsNV.getNhanVienTheoMa(maNV);
//				frmChinh.addInfoStaff(nv);
				Frm_Chinh frm_Chinh = new Frm_Chinh();
				frm_Chinh.setVisible(true);
//				this.setVisible(false);
////			}
//		}
	}

	public void quenMatKhau() {
		String maNV = txtTaiKhoan.getText().toString().trim();
		String mk = pwdNv.getText().toString().trim();
//		tk = dsTk.getTaiKhoanTheoMa(maNV);
		Frm_GuiMail frmSendMail = new Frm_GuiMail(tk);
		frmSendMail.setVisible(true);
		this.setVisible(false);
	}

	// regex
	private boolean regexDangNhap(String id, String pass) {
		if (id.equalsIgnoreCase("")) {
			JOptionPane.showMessageDialog(this, "Vui lòng điền mã nhân viên để đăng nhập!");
			return false;
		} else if (!id.matches("^(NV[0-9]{3})$")) {
			JOptionPane.showMessageDialog(this, "Mã nhân viên sai định dạng!");
			txtTaiKhoan.requestFocus();
			return false;
		}
		if (pass.equalsIgnoreCase("")) {
			JOptionPane.showMessageDialog(this, "Vui lòng điền mật khẩu để đăng nhập!");
			return false;
		}
		return true;
	}

	public static void main(String[] args) {
		new Frm_DangNhap().setVisible(true);
	}
}