package app;

import java.awt.Image;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;

import dao.DanhSachTaiKhoan;
import entitys.NhanVien;

import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JEditorPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JComboBox;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.border.MatteBorder;

import java.awt.Component;
import java.awt.ComponentOrientation;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import java.awt.Dimension;

public class Frm_Profile extends JFrame implements ActionListener {
	private JLabel lbBGQLDP, lbBG;
	private JPanel pnBGR;
	private JPanel pnInfo;
	private NhanVien nv;

	Date currentDate;
	int ngay, thang, nam;
	FixButton btnDoiPass, btnQuayLai;
	private DanhSachTaiKhoan dsTK;

	public JPanel getFrm_Profile() {
		return this.pnInfo;
	}

	public Frm_Profile(NhanVien nv) {
		this.nv = nv;
		setTitle("THÔNG TIN CÁ NHÂN");
		setSize(970, 650);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(true);
		setLocationRelativeTo(null);
		gui();
	}

	public void gui() {

		getContentPane().setLayout(null);

		pnBGR = new JPanel();
		pnBGR.setBounds(0, 0, 970, 700);
		getContentPane().add(pnBGR);
		pnBGR.setLayout(null);

		ImageIcon originalIcon = new ImageIcon(Frm_DoiMatKhau.class.getResource("/imgs/bg_chot1.png"));
		Image image = originalIcon.getImage();
		// resize lại ảnh
		Image resizedImage = image.getScaledInstance(971, 633, Image.SCALE_SMOOTH);
		ImageIcon resizedIcon = new ImageIcon(resizedImage);
		pnBGR.setLayout(null);

		btnDoiPass = new FixButton("Đổi Mật Khẩu");
		btnDoiPass.setIcon(new ImageIcon(Frm_Profile.class.getResource("/imgs/btn_lammoi.png")));
		btnDoiPass.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnDoiPass.setBounds(533, 542, 205, 46);
		pnBGR.add(btnDoiPass);

		btnQuayLai = new FixButton("Xác Nhận");
		btnQuayLai.setIcon(new ImageIcon(Frm_Profile.class.getResource("/imgs/btn_quaylai.png")));
		btnQuayLai.setText("Quay Lại");
		btnQuayLai.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnQuayLai.setBounds(257, 542, 215, 46);
		pnBGR.add(btnQuayLai);

		pnInfo = new JPanel();
		pnInfo.setBackground(new Color(17, 85, 136));
		pnInfo.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(240, 248, 255)));
		pnInfo.setBounds(246, 258, 509, 192);
		pnBGR.add(pnInfo);
		pnInfo.setLayout(null);

		JLabel lbSDT = new JLabel("   Số Điện Thoại:");
		lbSDT.setIcon(new ImageIcon(Frm_Profile.class.getResource("/imgs/icon_sdt.png")));
		lbSDT.setBounds(10, 52, 191, 35);
		lbSDT.setForeground(Color.WHITE);
		lbSDT.setFont(new Font("Tahoma", Font.BOLD, 18));
		pnInfo.add(lbSDT);

		JLabel lbCCCD = new JLabel("   Số CCCD:");
		lbCCCD.setIcon(new ImageIcon(Frm_Profile.class.getResource("/imgs/icon_cmt.png")));
		lbCCCD.setBounds(10, 10, 181, 32);
		lbCCCD.setForeground(Color.WHITE);
		lbCCCD.setFont(new Font("Tahoma", Font.BOLD, 18));
		pnInfo.add(lbCCCD);

		JLabel lbNgaySinh = new JLabel("   Ngày Sinh:");
		lbNgaySinh.setIcon(new ImageIcon(Frm_Profile.class.getResource("/imgs/icon_ngaysinh.png")));
		lbNgaySinh.setBounds(10, 136, 191, 37);
		lbNgaySinh.setForeground(Color.WHITE);
		lbNgaySinh.setFont(new Font("Tahoma", Font.BOLD, 18));
		pnInfo.add(lbNgaySinh);

		JLabel lbGioiTinh = new JLabel("   Giới Tính:");
		lbGioiTinh.setIcon(new ImageIcon(Frm_Profile.class.getResource("/imgs/icon_gioitinh.png")));
		lbGioiTinh.setBounds(10, 94, 160, 32);
		lbGioiTinh.setForeground(Color.WHITE);
		lbGioiTinh.setFont(new Font("Tahoma", Font.BOLD, 18));
		pnInfo.add(lbGioiTinh);

		JLabel lblCccd = new JLabel(nv.getSoCCCD().toString());
		lblCccd.setForeground(Color.WHITE);
		lblCccd.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblCccd.setBounds(225, 9, 313, 37);
		pnInfo.add(lblCccd);

		JLabel lblSdt = new JLabel(nv.getSdt().toString());
		lblSdt.setForeground(Color.WHITE);
		lblSdt.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblSdt.setBounds(225, 54, 313, 32);
		pnInfo.add(lblSdt);

		JLabel lblGioiTinh = new JLabel(nv.isGioiTinh() ? "Nam" : "Nữ");
		lblGioiTinh.setForeground(Color.WHITE);
		lblGioiTinh.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblGioiTinh.setBounds(225, 94, 102, 32);
		pnInfo.add(lblGioiTinh);

		JLabel lblNgaySinh = new JLabel(nv.getNgaySinh().toString());
		lblNgaySinh.setForeground(Color.WHITE);
		lblNgaySinh.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNgaySinh.setBounds(225, 138, 313, 32);
		pnInfo.add(lblNgaySinh);

		JLabel lbIMG = new JLabel("");
		lbIMG.setIcon(new ImageIcon(Frm_Profile.class.getResource("/imgs/icon_ttcanhan.png")));
		lbIMG.setBounds(430, 43, 134, 140);
		pnBGR.add(lbIMG);

		ImageIcon originalIconRP = new ImageIcon(Frm_DoiMatKhau.class.getResource("/imgs/repair.png"));
		Image imagerp = originalIconRP.getImage();
		// resize lại ảnh
		Image resizedImageRp = imagerp.getScaledInstance(350, 633, Image.SCALE_SMOOTH);
		ImageIcon resizedIconRP = new ImageIcon(resizedImageRp);
		pnBGR.setLayout(null);

		JLabel lbHeader = new JLabel("THÔNG TIN CÁ NHÂN");
		lbHeader.setBounds(355, 10, 300, 29);
		pnBGR.add(lbHeader);
		lbHeader.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		lbHeader.setAlignmentX(Component.CENTER_ALIGNMENT);
		lbHeader.setHorizontalAlignment(SwingConstants.CENTER);
		lbHeader.setForeground(new Color(255, 255, 255));
		lbHeader.setFont(new Font("Tahoma", Font.BOLD, 24));

		JLabel lblTnN = new JLabel(nv.getHoTenNhanVien().toString());
		lblTnN.setBounds(425, 187, 313, 32);
		lblTnN.setForeground(Color.WHITE);
		lblTnN.setFont(new Font("Tahoma", Font.BOLD, 24));
		pnBGR.add(lblTnN);
		
		JLabel lblDiaChi = new JLabel(nv.getDiaChi().toString());
		lblDiaChi.setBounds(460, 216, 313, 32);
		pnBGR.add(lblDiaChi);
		lblDiaChi.setForeground(Color.WHITE);
		lblDiaChi.setFont(new Font("Tahoma", Font.BOLD, 20));
		pnBGR.add(lblDiaChi);
		// add ảnh
		JLabel lbBGR = new JLabel("");
		lbBGR.setIcon(new ImageIcon(Frm_Profile.class.getResource("/imgs/bg_profile.png")));
		lbBGR.setBounds(0, 0, 970, 700);
		pnBGR.add(lbBGR);
		
			

		dsTK = new DanhSachTaiKhoan();
		btnDoiPass.addActionListener(this);
		btnQuayLai.addActionListener(this);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if (o == btnQuayLai) {
			dispose();
			Frm_Chinh frm_Chinh = new Frm_Chinh(nv);
			frm_Chinh.setVisible(true);
		} else {
			
			this.dispose();
			doiPass();
		}
	}

	private void doiPass() {
		new Frm_DoiMatKhau(dsTK.getTaiKhoanTheoMa(nv.getMaNhanVien())).setVisible(true);
	}
}
