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
import java.text.DecimalFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
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

import dao.DanhSachHoaDon;
import dao.DanhSachKhachHang;
import dao.DanhSachPhong;
import dao.DanhSachThuePhong;
import entitys.HoaDonPhong;
import entitys.KhachHang;
import entitys.Phong;

import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

public class Frm_TTKH extends JFrame implements ActionListener {
	JPanel pnDSDichVu;
	Panel pnTTKH;
	private JTable tableDSPhong;
	private DefaultTableModel model;
	private JTextField txtKhachHang, txtSDT;
	private JPanel pnKH;
	private JLabel lbSDT, lbTenKH,
			lbDSDichVu, lbBGQLDV, lbTTKH;
	private JComboBox comboTTP, comboLP, comboGP;
	FixButton btnHuy, btnTinhTien;
	private DecimalFormat df;
	private DateTimeFormatter dt;
	String ma;
	HoaDonPhong hd;
	DanhSachPhong p;
	DanhSachHoaDon dsHD;
	DanhSachThuePhong tp;
	DanhSachKhachHang dsKH;

	public Panel getFrmTTKH() {
		return this.pnTTKH;
	}

	public Frm_TTKH(String ma) {
		setTitle("THÔNG TIN KHÁCH HÀNG");
		setSize(1000, 600);
		setResizable(true);
		setLocationRelativeTo(null);
		this.ma = ma;
		gui();
	}

	public void gui() {
		getContentPane().setLayout(null);

		pnTTKH = new Panel();
		pnTTKH.setBounds(0, 0, 1000, 558);
		getContentPane().add(pnTTKH);
		pnTTKH.setLayout(null);

		lbTTKH = new JLabel("Thông tin khách hàng");
		lbTTKH.setForeground(Color.WHITE);
		lbTTKH.setFont(new Font("Tahoma", Font.BOLD, 15));
		lbTTKH.setBounds(34, 37, 200, 25);
		pnTTKH.add(lbTTKH);

		pnDSDichVu = new JPanel();
		pnDSDichVu.setBackground(Color.WHITE);
		pnDSDichVu.setBounds(34, 206, 926, 250);
		pnTTKH.add(pnDSDichVu);
		pnDSDichVu.setLayout(null);

		lbDSDichVu = new JLabel("Danh sách hóa đơn");
		lbDSDichVu.setFont(new Font("Tahoma", Font.BOLD, 15));
		lbDSDichVu.setBounds(10, 0, 150, 25);
		pnDSDichVu.add(lbDSDichVu);
		String col[] = { "Mã hóa đơn", "Mã phòng", "Tên khách hàng", "SĐT", "Ngày", "Thời gian" };
		model = new DefaultTableModel(col, 0);
		tableDSPhong = new JTable(model);

		tableDSPhong.setBackground(Color.WHITE);

		// Set màu cho table
		// Set màu cho cột tiêu đề
		JTableHeader tbHeader = tableDSPhong.getTableHeader();
		tbHeader.setBackground(new java.awt.Color(0, 0, 0));
		tbHeader.setForeground(Color.WHITE);
		tbHeader.setFont(new Font("Tahoma", Font.BOLD, 14));

		// Set màu các dòng

		tableDSPhong.setBackground(Color.white);
		tableDSPhong.setFont(new Font("SansSerif", Font.PLAIN, 13));
		tableDSPhong.setSelectionBackground(new Color(158, 207, 0));
		tableDSPhong.setSelectionForeground(new Color(255, 255, 255));
		tableDSPhong.setRowHeight(30);

		JScrollPane scrollPane = new JScrollPane(tableDSPhong, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setBorder(new LineBorder(new Color(158, 207, 0), 1, true));
		scrollPane.setBackground(Color.BLACK);
		scrollPane.setBounds(0, 25, 926, 220);
		scrollPane.getHorizontalScrollBar();
		pnDSDichVu.add(scrollPane);

		scrollPane.setViewportView(tableDSPhong);

		pnKH = new JPanel();
		pnKH.setBorder(new LineBorder(new Color(0, 0, 0), 3));
		pnKH.setLayout(null);
		pnKH.setBackground(new Color(207, 169, 0));
		pnKH.setBounds(34, 83, 926, 92);
		pnTTKH.add(pnKH);

		lbSDT = new JLabel("Số điện thoại:");
		lbSDT.setForeground(Color.WHITE);
		lbSDT.setFont(new Font("Tahoma", Font.BOLD, 15));
		lbSDT.setBounds(513, 30, 150, 25);
		pnKH.add(lbSDT);

		lbTenKH = new JLabel("Tên khách hàng:");
		lbTenKH.setForeground(Color.WHITE);
		lbTenKH.setFont(new Font("Tahoma", Font.BOLD, 15));
		lbTenKH.setBounds(10, 30, 200, 25);
		pnKH.add(lbTenKH);

		txtSDT = new JTextField();
		txtSDT.setFont(new Font("Tahoma", Font.BOLD, 15));
		txtSDT.setBounds(643, 30, 250, 30);
		txtSDT.setEditable(false);
		pnKH.add(txtSDT);

		txtKhachHang = new JTextField();
		txtKhachHang.setFont(new Font("Tahoma", Font.BOLD, 15));
		txtKhachHang.setBounds(160, 30, 250, 30);
		pnKH.add(txtKhachHang);

		btnHuy = new FixButton("");
		btnHuy.setBounds(337, 479, 140, 40);
		pnTTKH.add(btnHuy);
		btnHuy.setIcon(new ImageIcon(Frm_ThanhToan.class.getResource("/imgs/btn_huydv.png")));
		btnHuy.setText("Quay lại");
		btnHuy.setFont(new Font("Tahoma", Font.BOLD, 15));

		btnTinhTien = new FixButton("");
		btnTinhTien.setBounds(507, 479, 171, 40);
		pnTTKH.add(btnTinhTien);
		btnTinhTien.setIcon(new ImageIcon(Frm_ThanhToan.class.getResource("/imgs/icon_tinhtien.png")));
		btnTinhTien.setText("Thanh toán");
		btnTinhTien.setFont(new Font("Tahoma", Font.BOLD, 15));

		// add background ở cuối
		lbBGQLDV = new JLabel("");
		lbBGQLDV.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lbBGQLDV.setIcon(new ImageIcon(Frm_QuanLyDatPhong.class.getResource("/imgs/bg_trong.png")));
		lbBGQLDV.setBounds(0, 0, 1000, 557);
		pnTTKH.add(lbBGQLDV);
		txtKhachHang.setEditable(false);
		btnHuy.addActionListener(this);
		btnTinhTien.addActionListener(this);
		df = new DecimalFormat("###,### VNĐ");
		dt = DateTimeFormatter.ofPattern("HH:mm");
		p = new DanhSachPhong();
		tp = new DanhSachThuePhong();
		dsKH = new DanhSachKhachHang();
		dsHD = new DanhSachHoaDon();
		upTable();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if(o == btnHuy)
			dispose();
		if(o == btnTinhTien) {
			if (ktraPhong()) {
				HoaDonPhong hd = getHDPDuocChon();
				LocalTime giotra = LocalTime.now();
				hd.setGioTraPhong(giotra);
				new Frm_ThanhToan(hd).setVisible(true);
			}
		}
		
	}
	public void upTable() {
		int i = 0;
		KhachHang kh = dsKH.getKhachHangTheoSDT(ma);
		String makh = kh.getMaKhachHang();
		txtKhachHang.setText(kh.getHoTenKhachHang());
		txtSDT.setText(ma);
		ArrayList<HoaDonPhong> list = dsHD.getDSHDTheoMaKH(makh);
		for (HoaDonPhong p : list) {
			if (p.getMaLoaiHoaDon().getMaLoaiHoaDon().equals("HDT")) {
				Object[] obj = new Object[6];
				obj[0] = p.getMaHoaDon().trim();
				obj[1] = p.getPhong().getMaPhong().trim();
				obj[2] = p.getMaKhachHang().getMaKhachHang().trim();
				obj[3] = p.getMaKhachHang().getSoDienThoai().trim();
				obj[4] = p.getNgayLapHoaDon();
				obj[5] = p.getGioBatDauThue().format(dt);
				model.addRow(obj);
			}
		}
	}
	public boolean ktraPhong() {
		int row = tableDSPhong.getSelectedRow();
		if (row < 0) {
			JOptionPane.showMessageDialog(this, "Chưa chọn phòng");
			return false;
		}
		if (tableDSPhong.getValueAt(row, 4).toString().equals("Phòng trống")) {
			JOptionPane.showMessageDialog(this, "Phòng chưa được thuê!!!\n Chọn phòng khác");
			return false;
		}
		return true;
	}
	public HoaDonPhong getHDPDuocChon() {
		int row = tableDSPhong.getSelectedRow();
		String ma = (String) tableDSPhong.getValueAt(row, 0);
		HoaDonPhong hd = tp.getHDTheoMa(ma);
		return hd;
	}
}
