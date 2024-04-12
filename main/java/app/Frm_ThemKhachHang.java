package app;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.LineBorder;

import com.toedter.calendar.JDateChooser;

import dao.DanhSachKhachHang;
import dao.Dao_PhatSinhMa;
import entitys.KhachHang;
import entitys.LoaiKhachHang;

import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.border.TitledBorder;

public class Frm_ThemKhachHang extends JFrame implements ActionListener {
	Panel pnThemKhachHang;
	private JTextField txtKhachHang, txtSDT;
	private JPanel pnTTDDP;
	private JLabel lbGioiTinh, lbTenKH, lbBGQLDV, lbThemKH, lbSDT_1;
	private JTextField txtTinhTrang, txtLoaiPhong, txtGiaPhong;
	FixButton btnHuy, btnXacNhan, btnLamMoi;
	private JTextField txtCCCD;
	private JComboBox comboGT;
	DanhSachKhachHang dsKH;
	String sdt;
	KhachHang kh;

	public Frm_ThemKhachHang(String sdt) {
		this.sdt = sdt;
		setTitle("THÊM KHÁCH HÀNG");
		setSize(1000, 500);
		setResizable(true);
		setLocationRelativeTo(null);
		this.sdt = sdt;
		gui();
	}

	public void gui() {
		getContentPane().setLayout(null);

		pnThemKhachHang = new Panel();
		pnThemKhachHang.setBounds(0, 0, 1000, 500);
		getContentPane().add(pnThemKhachHang);
		pnThemKhachHang.setLayout(null);

		lbThemKH = new JLabel("THÊM KHÁCH HÀNG");
		lbThemKH.setForeground(Color.WHITE);
		lbThemKH.setFont(new Font("Tahoma", Font.BOLD, 25));
		lbThemKH.setBounds(372, 52, 256, 50);
		pnThemKhachHang.add(lbThemKH);

		pnTTDDP = new JPanel();
		pnTTDDP.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0), 8), "", TitledBorder.LEADING,
				TitledBorder.TOP, null, Color.BLACK));
		pnTTDDP.setLayout(null);
		pnTTDDP.setBackground(new Color(189, 0, 88));
		pnTTDDP.setBounds(37, 128, 926, 243);
		pnThemKhachHang.add(pnTTDDP);

		lbGioiTinh = new JLabel("Giới tính: ");
		lbGioiTinh.setForeground(Color.WHITE);
		lbGioiTinh.setFont(new Font("Tahoma", Font.BOLD, 15));
		lbGioiTinh.setBounds(519, 99, 87, 25);
		pnTTDDP.add(lbGioiTinh);

		lbTenKH = new JLabel("Tên khách hàng:");
		lbTenKH.setForeground(Color.WHITE);
		lbTenKH.setFont(new Font("Tahoma", Font.BOLD, 15));
		lbTenKH.setBounds(21, 47, 126, 25);
		pnTTDDP.add(lbTenKH);

		txtSDT = new JTextField(sdt);
		txtSDT.setFont(new Font("Tahoma", Font.BOLD, 15));
		txtSDT.setBounds(654, 47, 250, 30);
		txtSDT.setEditable(false);
		pnTTDDP.add(txtSDT);

		txtKhachHang = new JTextField();
		txtKhachHang.setFont(new Font("Tahoma", Font.BOLD, 15));
		txtKhachHang.setBounds(171, 47, 250, 30);
		pnTTDDP.add(txtKhachHang);

		JLabel lbCCCD = new JLabel("CCCD:");
		lbCCCD.setForeground(Color.WHITE);
		lbCCCD.setFont(new Font("Tahoma", Font.BOLD, 15));
		lbCCCD.setBounds(21, 99, 55, 25);
		pnTTDDP.add(lbCCCD);

		txtCCCD = new JTextField();
		txtCCCD.setFont(new Font("Tahoma", Font.BOLD, 15));
		txtCCCD.setBounds(171, 99, 250, 30);
		pnTTDDP.add(txtCCCD);

		lbSDT_1 = new JLabel("Số điện thoại:");
		lbSDT_1.setForeground(Color.WHITE);
		lbSDT_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lbSDT_1.setBounds(519, 47, 113, 25);
		pnTTDDP.add(lbSDT_1);

		comboGT = new JComboBox();
		comboGT.setModel(new DefaultComboBoxModel(new String[] { "Nam", "Nữ" }));
		comboGT.setSelectedIndex(0);
		comboGT.setFont(new Font("Tahoma", Font.BOLD, 15));
		comboGT.setBounds(654, 94, 250, 30);
		pnTTDDP.add(comboGT);

		btnHuy = new FixButton("Hủy đặt phòng");
		btnHuy.setBounds(145, 396, 140, 40);
		pnThemKhachHang.add(btnHuy);
		btnHuy.setIcon(new ImageIcon(Frm_ThanhToan.class.getResource("/imgs/btn_huydv.png")));
		btnHuy.setText("Hủy");
		btnHuy.setFont(new Font("Tahoma", Font.BOLD, 15));

		btnXacNhan = new FixButton("Làm mới");

		btnXacNhan.setBounds(715, 396, 140, 40);
		pnThemKhachHang.add(btnXacNhan);
		btnXacNhan.setIcon(new ImageIcon(Frm_ThanhToan.class.getResource("/imgs/btn_xacnhan.png")));
		btnXacNhan.setText("Xác nhận");
		btnXacNhan.setFont(new Font("Tahoma", Font.BOLD, 15));

		FixButton btnLamMoi = new FixButton("Hủy đặt phòng");
		btnLamMoi.setIcon(new ImageIcon(Frm_ThemKhachHang.class.getResource("/imgs/btn_lammoi.png")));
		btnLamMoi.setText("Làm mới");
		btnLamMoi.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnLamMoi.setBounds(430, 396, 140, 40);
		pnThemKhachHang.add(btnLamMoi);

		// add background ở cuối
		lbBGQLDV = new JLabel("");
		lbBGQLDV.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lbBGQLDV.setIcon(new ImageIcon(Frm_QuanLyDatPhong.class.getResource("/imgs/bg_trong.png")));
		lbBGQLDV.setBounds(0, 0, 1000, 820);
		pnThemKhachHang.add(lbBGQLDV);
		btnHuy.addActionListener(this);
		btnXacNhan.addActionListener(this);
		btnLamMoi.addActionListener(this);

		dsKH = new DanhSachKhachHang();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object o = e.getSource();
		if (o == btnHuy) {
			this.dispose();
		} else if (o == btnXacNhan) {
			if (luuThongTinKhachHang()) {
				JOptionPane.showMessageDialog(this, "Thêm thành công");
				this.dispose();
			} else {
				JOptionPane.showMessageDialog(this, "Không thể thêm khách hàng!");
			}
		} else {
			xoaTrang();
		}
	}

	public void xoaTrang() {
		txtKhachHang.setText("");
		txtCCCD.setText("");
		txtSDT.setText("");
		comboGT.setSelectedIndex(0);
	}

	public boolean ktraDuLieu() {
		String ten = txtKhachHang.getText();
		if (ten.equals("") || !ten.matches(
				"^[A-Z][ A-Za-za-zA-ZÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠàáâãèéêìíòóôõùúăđĩũơƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂẾưăạảấầẩẫậắằẳẵặẹẻẽềềểếỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪễệỉịọỏốồổỗộớờởỡợụủứừỬỮỰỲỴÝỶỸửữựỳỵỷỹ]*")) {
			JOptionPane.showMessageDialog(this, "Tên không được để trống và viết hoa chữ cái đầu");
			txtKhachHang.requestFocus();
			return false;
		}
		String cccd = txtCCCD.getText();
		DanhSachKhachHang dsKh = new DanhSachKhachHang();
		ArrayList<KhachHang> list = dsKH.getDSKhachHang();
		for (KhachHang kh : list) {
			if (cccd.equals(kh.getSoCCCD())) {
				JOptionPane.showMessageDialog(this, "Số CCCD đã tồn tại");
				txtCCCD.requestFocus();
				return false;
			}
		}
		if (cccd.equals("") || !cccd.matches("^([0-9]{12})$")) {
			JOptionPane.showMessageDialog(this, "Số CCCD không để trống và chỉ được 12 số");
			txtCCCD.requestFocus();
			return false;
		}
		return true;
	}

	private boolean luuThongTinKhachHang() {
		String tenKH = txtKhachHang.getText().trim();
		String sCCD = txtCCCD.getText().trim();
		String sdtKH = txtSDT.getText().trim();
		int gt = comboGT.getSelectedIndex();
		boolean gtt = (gt == 0) ? true : false;
		Dao_PhatSinhMa psm = new Dao_PhatSinhMa();
		String ma = psm.getMaKHCuoi();
		Object[] obj = new Object[7];
		LoaiKhachHang lkh = new LoaiKhachHang("NOR");
		kh = new KhachHang(ma, tenKH, sCCD, sdtKH, 0, gtt, lkh);
		if (ktraDuLieu() == true) {
			if (!dsKH.themKhachHang(kh)) {
				obj[0] = ma;
				obj[1] = tenKH;
				obj[2] = sCCD;
				obj[3] = sdtKH;
				obj[4] = gtt;
				obj[5] = lkh;
				obj[6] = 0;
				xoaTrang();
				return true;
			}
		}
		return false;
	}

}
