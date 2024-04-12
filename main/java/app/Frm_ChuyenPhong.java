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
import java.time.LocalDate;
import java.time.LocalTime;
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

import dao.DanhSachChiTietHoaDon;
import dao.DanhSachDatPhong;
import dao.DanhSachHoaDon;
import dao.DanhSachPhong;
import dao.DanhSachThuePhong;
import entitys.ChiTietHoaDon;
import entitys.HoaDonPhong;
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

public class Frm_ChuyenPhong extends JFrame implements ActionListener {
	JPanel pnDSDichVu;
	Panel pnChuyenPhong;
	private JTable tableDSPhong;
	private DefaultTableModel model;
	private JTextField txtKhachHang, txtSDT;
	private JPanel pnTTPHT, pnTTKH;
	private JLabel lbSucChua, lbMaPhong, lbLoaiPhong, lbSDT, lbTenKH, lbThongTinPhongHienTai, lbTinhTrang, lbGiaPhong,
			lbDSDichVu, lbBGQLDV, lbTTKH, lbChuyenPhong;
	private JTextField txtMaPhong, txtTinhTrang, txtLoaiPhong, txtGiaPhong, txtSucChua;
	private JComboBox comboTTP, comboLP, comboGP;
	FixButton btnHuy, btnChuyen;
	private DecimalFormat df;
	HoaDonPhong hd;
	DanhSachPhong p;
	DanhSachHoaDon dsHD;
	DanhSachThuePhong tp;
	DanhSachDatPhong dsDP;
	DanhSachChiTietHoaDon dsCT;

	public Panel getFrmChuyenPhong() {
		return this.pnChuyenPhong;
	}

	public Frm_ChuyenPhong(HoaDonPhong hd) {
		setTitle("CHUYỂN PHÒNG");
		setSize(1000, 820);
		setResizable(true);
		setLocationRelativeTo(null);
		this.hd = hd;
		gui();
	}

	public void gui() {
		getContentPane().setLayout(null);

		pnChuyenPhong = new Panel();
		pnChuyenPhong.setBounds(0, 0, 1000, 820);
		getContentPane().add(pnChuyenPhong);
		pnChuyenPhong.setLayout(null);

		btnChuyen = new FixButton("Làm mới");
		btnChuyen.setBounds(517, 718, 140, 40);
		pnChuyenPhong.add(btnChuyen);
		btnChuyen.setIcon(new ImageIcon(Frm_ThanhToan.class.getResource("/imgs/btn_xacnhan.png")));
		btnChuyen.setText("Xác nhận");
		btnChuyen.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnChuyen.addActionListener(this);

		btnHuy = new FixButton("Hủy đặt phòng");
		btnHuy.setBounds(354, 718, 140, 40);
		pnChuyenPhong.add(btnHuy);
		btnHuy.setIcon(new ImageIcon(Frm_ThanhToan.class.getResource("/imgs/btn_huydv.png")));
		btnHuy.setText("Hủy");
		btnHuy.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnHuy.addActionListener(this);

		lbTTKH = new JLabel("Thông tin khách hàng");
		lbTTKH.setForeground(Color.WHITE);
		lbTTKH.setFont(new Font("Tahoma", Font.BOLD, 15));
		lbTTKH.setBounds(46, 76, 200, 25);
		pnChuyenPhong.add(lbTTKH);

		pnDSDichVu = new JPanel();
		pnDSDichVu.setBackground(Color.WHITE);
		pnDSDichVu.setBounds(32, 458, 926, 250);
		pnChuyenPhong.add(pnDSDichVu);
		pnDSDichVu.setLayout(null);

		lbDSDichVu = new JLabel("Danh sách phòng");
		lbDSDichVu.setFont(new Font("Tahoma", Font.BOLD, 15));
		lbDSDichVu.setBounds(10, 0, 150, 25);
		pnDSDichVu.add(lbDSDichVu);
		String col[] = { "Mã phòng", "Tình trạng", "Sức chứa", "Loại phòng", "Giá phòng" };
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
		scrollPane.setBounds(0, 25, 980, 220);
		scrollPane.getHorizontalScrollBar();
		pnDSDichVu.add(scrollPane);

		scrollPane.setViewportView(tableDSPhong);

		pnTTKH = new JPanel();
		pnTTKH.setBorder(new LineBorder(new Color(0, 0, 0), 3));
		pnTTKH.setLayout(null);
		pnTTKH.setBackground(new Color(207, 169, 0));
		pnTTKH.setBounds(34, 111, 926, 92);
		pnChuyenPhong.add(pnTTKH);

		lbSDT = new JLabel("Số điện thoại:");
		lbSDT.setForeground(Color.WHITE);
		lbSDT.setFont(new Font("Tahoma", Font.BOLD, 15));
		lbSDT.setBounds(513, 30, 150, 25);
		pnTTKH.add(lbSDT);

		lbTenKH = new JLabel("Tên khách hàng:");
		lbTenKH.setForeground(Color.WHITE);
		lbTenKH.setFont(new Font("Tahoma", Font.BOLD, 15));
		lbTenKH.setBounds(10, 30, 200, 25);
		pnTTKH.add(lbTenKH);

		txtSDT = new JTextField();
		txtSDT.setFont(new Font("Tahoma", Font.BOLD, 15));
		txtSDT.setBounds(643, 30, 250, 30);
		txtSDT.setEditable(false);
		pnTTKH.add(txtSDT);

		txtKhachHang = new JTextField();
		txtKhachHang.setFont(new Font("Tahoma", Font.BOLD, 15));
		txtKhachHang.setBounds(160, 30, 250, 30);
		pnTTKH.add(txtKhachHang);

		lbThongTinPhongHienTai = new JLabel("Thông tin phòng hiện tại");
		lbThongTinPhongHienTai.setForeground(Color.WHITE);
		lbThongTinPhongHienTai.setFont(new Font("Tahoma", Font.BOLD, 15));
		lbThongTinPhongHienTai.setBounds(46, 213, 200, 25);
		pnChuyenPhong.add(lbThongTinPhongHienTai);

		pnTTPHT = new JPanel();
		pnTTPHT.setBorder(new LineBorder(new Color(0, 0, 0), 3));
		pnTTPHT.setLayout(null);
		pnTTPHT.setBackground(new Color(189, 0, 88));
		pnTTPHT.setBounds(34, 240, 926, 208);
		pnChuyenPhong.add(pnTTPHT);

		lbSucChua = new JLabel("Sức chứa: ");
		lbSucChua.setForeground(Color.WHITE);
		lbSucChua.setFont(new Font("Tahoma", Font.BOLD, 15));
		lbSucChua.setBounds(513, 30, 150, 25);
		pnTTPHT.add(lbSucChua);

		lbMaPhong = new JLabel("Mã phòng: ");
		lbMaPhong.setForeground(Color.WHITE);
		lbMaPhong.setFont(new Font("Tahoma", Font.BOLD, 15));
		lbMaPhong.setBounds(10, 30, 200, 25);
		pnTTPHT.add(lbMaPhong);

		txtSucChua = new JTextField();
		txtSucChua.setFont(new Font("Tahoma", Font.BOLD, 15));
		txtSucChua.setBounds(643, 30, 250, 30);
		pnTTPHT.add(txtSucChua);

		txtMaPhong = new JTextField();
		txtMaPhong.setFont(new Font("Tahoma", Font.BOLD, 15));
		txtMaPhong.setBounds(160, 30, 250, 30);
		txtMaPhong.setEditable(false);
		pnTTPHT.add(txtMaPhong);

		lbTinhTrang = new JLabel("Tình trạng: ");
		lbTinhTrang.setForeground(Color.WHITE);
		lbTinhTrang.setFont(new Font("Tahoma", Font.BOLD, 15));
		lbTinhTrang.setBounds(10, 80, 200, 25);
		pnTTPHT.add(lbTinhTrang);

		txtTinhTrang = new JTextField();
		txtTinhTrang.setFont(new Font("Tahoma", Font.BOLD, 15));
		txtTinhTrang.setBounds(643, 80, 250, 30);
		pnTTPHT.add(txtTinhTrang);

		lbLoaiPhong = new JLabel("Loại phòng: ");
		lbLoaiPhong.setForeground(Color.WHITE);
		lbLoaiPhong.setFont(new Font("Tahoma", Font.BOLD, 15));
		lbLoaiPhong.setBounds(513, 80, 150, 25);
		pnTTPHT.add(lbLoaiPhong);

		txtLoaiPhong = new JTextField();
		txtLoaiPhong.setFont(new Font("Tahoma", Font.BOLD, 15));
		txtLoaiPhong.setBounds(160, 80, 250, 30);
		pnTTPHT.add(txtLoaiPhong);

		txtGiaPhong = new JTextField();
		txtGiaPhong.setFont(new Font("Tahoma", Font.BOLD, 15));
		txtGiaPhong.setBounds(160, 130, 250, 30);
		pnTTPHT.add(txtGiaPhong);

		lbGiaPhong = new JLabel("Giá phòng: ");
		lbGiaPhong.setForeground(Color.WHITE);
		lbGiaPhong.setFont(new Font("Tahoma", Font.BOLD, 15));
		lbGiaPhong.setBounds(10, 130, 200, 25);
		pnTTPHT.add(lbGiaPhong);

		lbChuyenPhong = new JLabel("CHUYỂN PHÒNG");
		lbChuyenPhong.setForeground(Color.WHITE);
		lbChuyenPhong.setFont(new Font("Tahoma", Font.BOLD, 25));
		lbChuyenPhong.setBounds(429, 40, 300, 35);
		pnChuyenPhong.add(lbChuyenPhong);

		// add background ở cuối
		lbBGQLDV = new JLabel("");
		lbBGQLDV.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lbBGQLDV.setIcon(new ImageIcon(Frm_QuanLyDatPhong.class.getResource("/imgs/bg_trong.png")));
		lbBGQLDV.setBounds(0, 0, 1000, 820);
		pnChuyenPhong.add(lbBGQLDV);
		dsDP = new DanhSachDatPhong();
		txtGiaPhong.setEditable(false);
		txtKhachHang.setEditable(false);
		txtSucChua.setEditable(false);
		txtTinhTrang.setEditable(false);
		df = new DecimalFormat("###,### VNĐ");
		p = new DanhSachPhong();
		tp = new DanhSachThuePhong();
		dsCT = new DanhSachChiTietHoaDon();
		upTT();
		upTable();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object o = e.getSource();
		if (o == btnHuy) {

			dispose();
		}
		if (o == btnChuyen) {
			if (chuyenPhong(hd)) {
				JOptionPane.showMessageDialog(this, "Chuyển phòng thành công");
				dispose();
			}
		}
	}

	public void upTT() {
		int i = 0;
		txtKhachHang.setText(hd.getMaKhachHang().getHoTenKhachHang());
		txtSDT.setText(hd.getMaKhachHang().getSoDienThoai());
		txtMaPhong.setText(hd.getPhong().getMaPhong());
		Phong phong = p.getPhongTheoMa(hd.getPhong().getMaPhong());
		String gia = df.format(phong.getGiaPhong());
		String loai = String.valueOf(phong.getMaLoaiPhong().getTenLoaiPhong());
		String succhua = String.valueOf(phong.getSucChua());
		String tt = String.valueOf(phong.getMaTinhTrangPhong().getTenTinhTrangPhong());
		txtGiaPhong.setText(gia);
		txtTinhTrang.setText(loai);
		txtSucChua.setText(succhua);
		txtLoaiPhong.setText(tt);
	}

	public void upTable() {
		ArrayList<Phong> list = dsDP.getAllRoomByDate(LocalDate.now().toString());
		for (Phong p : list) {
			Object[] obj = new Object[5];
			obj[0] = p.getMaPhong().trim();
			obj[1] = p.getMaTinhTrangPhong().getTenTinhTrangPhong();
			obj[2] = p.getSucChua();
			obj[3] = p.getMaLoaiPhong().getTenLoaiPhong();
			obj[4] = df.format(p.getGiaPhong());
			if (p.getMaTinhTrangPhong().getMaTinhTrangPhong().equals("RENT")
					|| p.getMaTinhTrangPhong().getMaTinhTrangPhong().equals("BOOK")) {
				continue;
			}
			model.addRow(obj);
		}
	}

	public boolean chuyenPhong(HoaDonPhong hd) {
		int row = tableDSPhong.getSelectedRow();
		Phong phong = p.getPhongTheoMa(hd.getPhong().getMaPhong());
		String map = (String) tableDSPhong.getValueAt(row, 0);
		if (!tp.chuyenPhong(hd.getMaHoaDon(), map)) {
			ArrayList<ChiTietHoaDon> list = dsCT.getCTHDPTheoMa(hd.getMaHoaDon());
			ChiTietHoaDon ct = null;
			if (list.size()>0) {
				for (ChiTietHoaDon c : list) {
					if (c.getGioChuyen() != null)
						ct = new ChiTietHoaDon(hd, phong, LocalTime.now(), c.getGioChuyen());
				}
			} else 
				ct = new ChiTietHoaDon(hd, phong, LocalTime.now(), hd.getGioBatDauThue());

			dsCT.luuTTChuyenPhong(ct);
			tp.setTTPhongTheoMa(map, "RENT");
			tp.setTTPhongTheoMa(hd.getPhong().getMaPhong(), "EMPT");
			return true;
		}
		return false;
	}
}
