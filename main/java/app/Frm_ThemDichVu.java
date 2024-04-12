package app;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Date;
import java.text.DecimalFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
//<<<<<<< HEAD
//import java.util.Iterator;
//import java.util.function.DoubleToLongFunction;
//=======
//>>>>>>> 0cb884e6cc745bcdf8c94e97aca6c848886290e8

import javax.swing.BorderFactory;
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
import javax.swing.border.TitledBorder;
import javax.swing.plaf.SpinnerUI;

//<<<<<<< HEAD
//import com.toedter.calendar.JDateChooser;

import connectDB.ConnectDB;
import dao.DanhSachChiTietHoaDon;
import dao.DanhSachDichVu;
import dao.DanhSachHoaDon;
import dao.DanhSachPhong;
import dao.Dao_PhatSinhMa;
//import dao.ThuePhong;
import entitys.ChiTietHoaDon;
import entitys.DichVu;
import entitys.HoaDonPhong;
import entitys.LoaiDichVu;
import entitys.LoaiPhong;
import entitys.Phong;
import entitys.PhuThu;
import entitys.TinhTrangPhong;
//=======
import com.toedter.calendar.JDateChooser;

import connectDB.ConnectDB;
import dao.DanhSachDichVu;
import dao.DanhSachHoaDon;
import dao.DanhSachThuePhong;
import entitys.ChiTietHoaDon;
import entitys.DichVu;
import entitys.HoaDonPhong;
//>>>>>>> 0cb884e6cc745bcdf8c94e97aca6c848886290e8

import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

//import org.apache.poi.hssf.record.cf.BorderFormatting;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.MatteBorder;
import javax.swing.border.EtchedBorder;

public class Frm_ThemDichVu extends JFrame implements ActionListener, MouseListener {

	JPanel pnDSDichVu, pnDV;
	JLabel lbDSDichVu, lbBGQLDV, lbDV, lbLoaiDichVu, lbTenDV, lbSoLuong, lbGiamSL, lbTangSL, lblTenPhong,
			lblTenKH, lblTime;
	JComboBox comboTDV, comboLDV;
	JTextField txtSoLuongTon;
	Panel pnTDV;
	FixButton btnHuyDV, btnXacNhan, btnLamMoi, btnThemDV, btnSuaDV;
	FixButton2 btnQuayLai;
	private JTable tableDSDichVu;
	private DefaultTableModel model;
	private DateTimeFormatter dt;
	private JLabel lbTongTien;
	int STT = 0;
	private JLabel lbPhong;
	private JLabel lbKH;
	private JLabel lbGioVao;
	HoaDonPhong hd;
	DanhSachDichVu dsDV;
	DanhSachHoaDon dsHD;
	DanhSachThuePhong tp;
	private DecimalFormat df;
	private JLabel lbTongTien_1;

	public Panel getFrmThemDichVu(HoaDonPhong hd) {
		return this.pnTDV;
	}

	public Frm_ThemDichVu(HoaDonPhong hd) {
		setTitle("THÊM DỊCH VỤ");
		setSize(1400, 670);
		setResizable(true);
		setLocationRelativeTo(null);
		this.hd = hd;
		gui();
	}

	public void gui() {
		getContentPane().setLayout(null);

		pnTDV = new Panel();
		pnTDV.setBounds(0, 0, 1400, 670);
		getContentPane().add(pnTDV);
		pnTDV.setLayout(null);

		pnDV = new JPanel();
		pnDV.setBorder(new LineBorder(new Color(255, 255, 255), 3));
		pnDV.setBackground(new java.awt.Color(17, 85, 136));
		pnDV.setBounds(20, 90, 481, 424);
		pnTDV.add(pnDV);
		pnDV.setLayout(null);

		lbDV = new JLabel("Dịch vụ");
		lbDV.setFont(new Font("Tahoma", Font.BOLD, 17));
		lbDV.setForeground(new Color(255, 255, 255));
		lbDV.setBounds(421, 60, 98, 20);
		pnTDV.add(lbDV);

		lbLoaiDichVu = new JLabel("Loại dịch vụ:");
		lbLoaiDichVu.setForeground(Color.WHITE);
		lbLoaiDichVu.setFont(new Font("Tahoma", Font.BOLD, 15));
		lbLoaiDichVu.setBounds(14, 45, 200, 25);
		pnDV.add(lbLoaiDichVu);

		lbTenDV = new JLabel("Tên dịch vụ:");
		lbTenDV.setForeground(Color.WHITE);
		lbTenDV.setFont(new Font("Tahoma", Font.BOLD, 15));
		lbTenDV.setBounds(14, 85, 200, 25);
		pnDV.add(lbTenDV);

		lbSoLuong = new JLabel("Số lượng:");
		lbSoLuong.setForeground(Color.WHITE);
		lbSoLuong.setFont(new Font("Tahoma", Font.BOLD, 15));
		lbSoLuong.setBounds(14, 125, 200, 25);
		pnDV.add(lbSoLuong);

		comboLDV = new JComboBox();
		comboLDV.setModel(new DefaultComboBoxModel(new String[] { "Thực phẩm", "Nước uống" }));
		comboLDV.setSelectedIndex(0);
		comboLDV.setFont(new Font("Tahoma", Font.BOLD, 15));
		comboLDV.setBounds(129, 45, 300, 30);
		pnDV.add(comboLDV);

		comboTDV = new JComboBox();
		comboTDV.setFont(new Font("Tahoma", Font.BOLD, 15));
		comboTDV.setBounds(129, 85, 300, 30);
		comboTDV.setEditable(true);
		pnDV.add(comboTDV);

		txtSoLuongTon = new JTextField("");
		txtSoLuongTon.setBounds(200, 125, 80, 30);
		txtSoLuongTon.setFont(new Font("Tahoma", Font.BOLD, 15));
		pnDV.add(txtSoLuongTon);
		btnThemDV = new FixButton("Làm mới");
		btnThemDV.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnThemDV.setIcon(new ImageIcon(Frm_ThemDichVu.class.getResource("/imgs/cart.png")));
		btnThemDV.setText("Thêm dịch vụ");
		btnThemDV.setBounds(129, 206, 300, 35);
		pnDV.add(btnThemDV);
		btnThemDV.setFont(new Font("Tahoma", Font.BOLD, 15));

		btnLamMoi = new FixButton("Hủy đặt phòng");
		btnLamMoi.setIcon(new ImageIcon(Frm_ThemDichVu.class.getResource("/imgs/btn_lammoi.png")));
		btnLamMoi.setText("Làm mới");
		btnLamMoi.setBounds(129, 296, 300, 35);
		pnDV.add(btnLamMoi);
		btnLamMoi.setFont(new Font("Tahoma", Font.BOLD, 15));

		lbGiamSL = new JLabel("");
		lbGiamSL.setIcon(new ImageIcon(Frm_ThemDichVu.class.getResource("/imgs/btn_giam.png")));
		lbGiamSL.setFont(new Font("Tahoma", Font.BOLD, 15));
		lbGiamSL.setBounds(158, 125, 30, 27);
		pnDV.add(lbGiamSL);

		lbTangSL = new JLabel("");
		lbTangSL.setIcon(new ImageIcon(Frm_ThemDichVu.class.getResource("/imgs/btn_them.png")));
		lbTangSL.setForeground(Color.WHITE);
		lbTangSL.setFont(new Font("Tahoma", Font.BOLD, 15));
		lbTangSL.setBounds(286, 125, 30, 27);
		pnDV.add(lbTangSL);

		lbTongTien = new JLabel("Đơn giá:");
		lbTongTien.setBackground(Color.WHITE);
		lbTongTien.setForeground(Color.WHITE);
		lbTongTien.setFont(new Font("Tahoma", Font.BOLD, 15));
		lbTongTien.setBounds(14, 165, 111, 30);
		pnDV.add(lbTongTien);

		btnSuaDV = new FixButton("Sửa dịch vụ");
		btnSuaDV.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnSuaDV.setIcon(new ImageIcon(Frm_QuanLyDichVu.class.getResource("/imgs/icon_btn_sua.png")));
		btnSuaDV.setForeground(new Color(255, 255, 255));
		btnSuaDV.setBounds(129, 251, 300, 35);
		pnDV.add(btnSuaDV);
		
		lbTongTien_1 = new JLabel("Đơn giá:");
		lbTongTien_1.setForeground(Color.WHITE);
		lbTongTien_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lbTongTien_1.setBackground(Color.WHITE);
		lbTongTien_1.setBounds(129, 165, 300, 30);
		pnDV.add(lbTongTien_1);

		pnDSDichVu = new JPanel();
		pnDSDichVu.setBackground(Color.WHITE);
		pnDSDichVu.setBounds(562, 95, 780, 350);
		pnTDV.add(pnDSDichVu);
		pnDSDichVu.setLayout(null);

		btnQuayLai = new FixButton2("Quay lại");
		btnQuayLai.setIcon(new ImageIcon(Frm_ThemDichVu.class.getResource("/imgs/btn_quaylai.png")));
		btnQuayLai.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnQuayLai.setBounds(20, 20, 140, 40);
		pnTDV.add(btnQuayLai);

		lbDSDichVu = new JLabel("Danh sách dịch vụ");
		lbDSDichVu.setFont(new Font("Tahoma", Font.BOLD, 15));
		lbDSDichVu.setBounds(10, 0, 150, 25);
		pnDSDichVu.add(lbDSDichVu);

		String col[] = { "STT", "Mã dịch vụ", "Loại dịch vụ", "Tên dịch vụ", "Số lượng", "Đơn giá", "Thành tiền" };
		model = new DefaultTableModel(col, 0);
		tableDSDichVu = new JTable(model);
		tableDSDichVu.setBackground(Color.WHITE);

		// Set màu cho table
		// Set màu cho cột tiêu đề
		JTableHeader tbHeader = tableDSDichVu.getTableHeader();
		tbHeader.setBackground(new java.awt.Color(0, 0, 0));
		tbHeader.setForeground(Color.WHITE);
		tbHeader.setFont(new Font("Tahoma", Font.BOLD, 14));
		// Set màu các dòng

		tableDSDichVu.setBackground(Color.white);
		tableDSDichVu.setFont(new Font("SansSerif", Font.PLAIN, 13));
		tableDSDichVu.setSelectionBackground(new Color(158, 207, 0));
		tableDSDichVu.setSelectionForeground(new Color(255, 255, 255));
		tableDSDichVu.setRowHeight(30);

		JScrollPane scrollPane = new JScrollPane(tableDSDichVu, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setBorder(new LineBorder(new Color(158, 207, 0), 1, true));
		scrollPane.setBackground(Color.BLACK);
		scrollPane.setBounds(0, 25, 780, 320);
		scrollPane.getHorizontalScrollBar();
		pnDSDichVu.add(scrollPane);

		scrollPane.setViewportView(tableDSDichVu);

		btnHuyDV = new FixButton("");
		btnHuyDV.setIcon(new ImageIcon(Frm_ThemDichVu.class.getResource("/imgs/btn_huydv.png")));
		btnHuyDV.setText("Hủy dịch vụ");
		btnHuyDV.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnHuyDV.setBounds(700, 470, 200, 40);
		pnTDV.add(btnHuyDV);
		btnXacNhan = new FixButton("");
		btnXacNhan.setIcon(new ImageIcon(Frm_ThemDichVu.class.getResource("/imgs/btn_xacnhan.png")));
		btnXacNhan.setText("Xác nhận");
		btnXacNhan.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnXacNhan.setBounds(990, 469, 200, 40);
		pnTDV.add(btnXacNhan);

		JPanel pnTTPhong = new JPanel();
		pnTTPhong.setBackground(new java.awt.Color(255, 255, 255, 80));
		pnTTPhong.setBounds(562, 0, 780, 75);
		pnTTPhong.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),
				"Th\u00F4ng tin ph\u00F2ng", TitledBorder.CENTER, TitledBorder.TOP, null, Color.WHITE));

		pnTDV.add(pnTTPhong);
		pnTTPhong.setLayout(null);

		lbPhong = new JLabel("Mã Phòng:");
		lbPhong.setForeground(Color.WHITE);
		lbPhong.setFont(new Font("Tahoma", Font.BOLD, 15));
		lbPhong.setBounds(10, 10, 100, 20);
		pnTTPhong.add(lbPhong);

		lbKH = new JLabel("Tên khách hàng:");
		lbKH.setForeground(Color.WHITE);
		lbKH.setFont(new Font("Tahoma", Font.BOLD, 15));
		lbKH.setBounds(10, 52, 132, 20);
		pnTTPhong.add(lbKH);

		lbGioVao = new JLabel("Giờ vào:");
		lbGioVao.setForeground(Color.WHITE);
		lbGioVao.setFont(new Font("Tahoma", Font.BOLD, 15));
		lbGioVao.setBounds(591, 52, 77, 20);
		pnTTPhong.add(lbGioVao);

		lblTenPhong = new JLabel("");
		lblTenPhong.setForeground(new Color(0, 0, 0));
		lblTenPhong.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblTenPhong.setBounds(100, 10, 119, 20);
		pnTTPhong.add(lblTenPhong);

		lblTenKH = new JLabel("");
		lblTenKH.setForeground(new Color(0, 0, 0));
		lblTenKH.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblTenKH.setBounds(152, 52, 229, 23);
		pnTTPhong.add(lblTenKH);

		lblTime = new JLabel("");
		lblTime.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblTime.setBounds(666, 54, 104, 17);
		pnTTPhong.add(lblTime);

		lbTangSL.addMouseListener(this);
		lbGiamSL.addMouseListener(this);

		// add background ở cuối
		lbBGQLDV = new JLabel("");
		lbBGQLDV.setIcon(new ImageIcon(Frm_QuanLyDatPhong.class.getResource("/imgs/bg_chot1.png")));
		lbBGQLDV.setBounds(0, 0, 1400, 670);
		pnTDV.add(lbBGQLDV);
		btnThemDV.addActionListener(this);
		btnHuyDV.addActionListener(this);
		btnLamMoi.addActionListener(this);
		btnQuayLai.addActionListener(this);
		btnXacNhan.addActionListener(this);
		comboLDV.addActionListener(this);
		comboTDV.addActionListener(this);
		tableDSDichVu.addMouseListener(this);
		btnSuaDV.addActionListener(this);
		dt = DateTimeFormatter.ofPattern("HH:mm");
		df = new DecimalFormat("###,### VNĐ");
		// kết nối data
		ConnectDB.getInstance().connect();
		dsDV = new DanhSachDichVu();
		dsHD = new DanhSachHoaDon();
		tp = new DanhSachThuePhong();
		upTT();
		phanLoaiCombobox();
		
		
		
	

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		Object o = e.getSource();
		int sl;
		if (txtSoLuongTon.getText().equals("")) {
			sl = 0;
		} else
			sl = Integer.parseInt(txtSoLuongTon.getText().trim());
		if (o == lbTangSL) {
			sl += 1;
			txtSoLuongTon.setText(String.valueOf(sl));
		}
		if (o == lbGiamSL) {
			sl -= 1;
			txtSoLuongTon.setText(String.valueOf(sl));
		}
		if (o == tableDSDichVu) {
			clickTB();
		}
	}

	public boolean ktraSL(String ma) {
		int slt = 0;
		try {
			slt = Integer.parseInt(txtSoLuongTon.getText());
			if (slt <= 0) {
				JOptionPane.showMessageDialog(this, "Số lượng phải lớn hơn 0");
				txtSoLuongTon.requestFocus();
				return false;
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "Số lượng không được để trống và không chứa kí tự");
			txtSoLuongTon.requestFocus();
			return false;
		}
		if (slt > dsDV.getSLTonTheoMaDV(ma)) {
			JOptionPane.showMessageDialog(this, "Số lượng phải nhỏ hơn hoặc bằng số lượng tồn");
			return false;
		}
		return true;
	}

	public boolean ktraSLSua(String ma) {
		int slt = 0;
		int row = tableDSDichVu.getSelectedRow();
		int slg = Integer.parseInt(tableDSDichVu.getValueAt(row, 4).toString());
		try {
			slt = Integer.parseInt(txtSoLuongTon.getText());
			if (slt <= 0) {
				JOptionPane.showMessageDialog(this, "Số lượng phải lớn hơn 0");
				txtSoLuongTon.requestFocus();
				return false;
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "Số lượng không được để trống và không chứa kí tự");
			txtSoLuongTon.requestFocus();
			return false;
		}
		if (slt > dsDV.getSLTonTheoMaDV(ma) + slg) {
			JOptionPane.showMessageDialog(this, "Số lượng phải nhỏ hơn hoặc bằng số lượng tồn");
			return false;
		}
		return true;
	}

	public void lamMoi() {
		txtSoLuongTon.setText("");
		tableDSDichVu.clearSelection();
		
		comboLDV.setSelectedIndex(0);
		comboTDV.setSelectedIndex(0);
		lbTongTien_1.setText("");
	}

	public void phanLoaiCombobox() {
		String loai = String.valueOf(comboLDV.getSelectedItem());
		ArrayList<DichVu> list = null;
		comboTDV.removeAllItems();
		if (loai.equals("Thực phẩm")) {
			comboTDV.removeAllItems();
			list = dsDV.getDSDichVuTheoLoai("FOOD");
		} else {
			comboTDV.removeAllItems();
			list = dsDV.getDSDichVuTheoLoai("WATER");
		}
		for (DichVu s : list) {
			comboTDV.addItem(s.getTenDichVu());
		}
		
	}

	public void upTT() {
		lblTenPhong.setText(hd.getPhong().getMaPhong());
		lblTenKH.setText(hd.getMaKhachHang().getHoTenKhachHang());
		lblTime.setText(dt.format(hd.getGioBatDauThue()));
		ArrayList<ChiTietHoaDon> list = tp.getCTHDDVTheoMa(hd.getMaHoaDon());
		int i = 1;
		float tongtiendv = 0;
		for (ChiTietHoaDon p : list) {
			if (p.getDichVu() != null) {
				DichVu dv = dsDV.getDVTheoMa(p.getDichVu().getMaDichVu());
				Object[] obj = new Object[7];
				obj[0] = i++;
				obj[1] = p.getDichVu().getMaDichVu();
				obj[2] = dv.getloaiDichVu().getTenLoaiDichVu();
				obj[3] = dv.getTenDichVu();
				obj[4] = dsDV.getSLTheoMaDV(p.getDichVu().getMaDichVu());
				obj[5] = df.format(dsDV.getDGTheoMaDV(p.getDichVu().getMaDichVu()));
				float tong = dsDV.getSLTheoMaDV(p.getDichVu().getMaDichVu())
						* dsDV.getDGTheoMaDV(p.getDichVu().getMaDichVu());
				tongtiendv += tong;
				obj[6] = df.format(tong);
				model.addRow(obj);
			}
		}
	}

	public void clickTB() {
		int row = tableDSDichVu.getSelectedRow();
		comboLDV.setSelectedItem(tableDSDichVu.getValueAt(row, 2).toString());
		comboTDV.setSelectedItem(tableDSDichVu.getValueAt(row, 3).toString());
		txtSoLuongTon.setText(tableDSDichVu.getValueAt(row, 4).toString());
		lbTongTien_1.setText(tableDSDichVu.getValueAt(row, 5).toString());
	}

	public void suaDV() {
		int row = tableDSDichVu.getSelectedRow();
		String ldv = (String) comboLDV.getSelectedItem();
		String tdv = (String) comboTDV.getSelectedItem();
		String madv = dsDV.getMaDVTheoTen(tdv);
		if (ktraSLSua(madv)) {
			int sl = Integer.parseInt(txtSoLuongTon.getText());
			int slt = dsDV.getSLTonTheoMaDV(madv);
			int slg = Integer.parseInt(tableDSDichVu.getValueAt(row, 4).toString());
			dsDV.updateSLTon(madv, slt + slg - sl);
			if (!tp.suaDVTheoMa(hd.getMaHoaDon(), madv, sl)) {
				tableDSDichVu.setValueAt(sl, row, 4);
				tableDSDichVu.setValueAt(df.format(sl * dsDV.getDGTheoMaDV(madv)), row, 6);
				JOptionPane.showMessageDialog(this, "Sửa thành công");
			}
		}
	}

	public void themDV() {
		int row = tableDSDichVu.getRowCount();
		String ldv = (String) comboLDV.getSelectedItem();
		String tdv = (String) comboTDV.getSelectedItem();
		String madv = dsDV.getMaDVTheoTen(tdv);
		boolean bl = true;
		for (int i = 0; i < row; i++) {
			if (tdv.equals(tableDSDichVu.getValueAt(i, 3))) {
				JOptionPane.showMessageDialog(this, "Dịch vụ đã được thêm!!!\nKhông thêm mới");
				bl = false;
				break;
			}
		}
		if (bl == true) {
			if (ktraSL(madv)) {
				int sl = Integer.parseInt(txtSoLuongTon.getText());
				int slt = dsDV.getSLTonTheoMaDV(madv);
				dsDV.updateSLTon(madv, slt - sl);
				if (!tp.themDVTheoMa(hd.getMaHoaDon(), madv, sl)) {
					JOptionPane.showMessageDialog(this, "Thêm thành công");
					Object[] obj = new Object[7];
					obj[0] = row + 1;
					obj[1] = madv;
					obj[2] = ldv;
					obj[3] = tdv;
					obj[4] = sl;
					obj[5] = df.format(dsDV.getDGTheoMaDV(madv));
					float tong = sl * dsDV.getDGTheoMaDV(madv);
					obj[6] = df.format(tong);
					model.addRow(obj);
				}
			}
		}
	}

	public void huyDV() {
		int row = tableDSDichVu.getSelectedRow();
		String madv = dsDV.getMaDVTheoTen(tableDSDichVu.getValueAt(row, 3).toString());
		int slt = dsDV.getSLTonTheoMaDV(madv);
		int slg = Integer.parseInt(tableDSDichVu.getValueAt(row, 4).toString());
		dsDV.updateSLTon(madv, slt + slg);
		if (JOptionPane.showConfirmDialog(this, "Bạn có chắn chắn muốn xóa dịch vụ không?", "Thông báo",
				JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
			if (!tp.deleteDV(madv)) {
				model.removeRow(row);
			}
		}
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
//		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override

	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if (o == btnQuayLai) {
			dispose();
		}
		if (o == btnLamMoi) {
			lamMoi();
		}
		if (o == comboLDV) {
			phanLoaiCombobox();
		}if (o==comboTDV) {
			String tdv = (String) comboTDV.getSelectedItem();
			String madv = dsDV.getMaDVTheoTen(tdv);
			lbTongTien_1.setText(df.format(dsDV.getDGTheoMaDV(madv)));
		}

		if (o == btnThemDV) {
			String tdv = (String) comboTDV.getSelectedItem();

		}
		if (o == btnSuaDV) {
			int row = tableDSDichVu.getSelectedRow();
			if (row < 0) {
				JOptionPane.showMessageDialog(this, "Chọn dịch vụ cần sửa");
			} else
				suaDV();
		}
		if (o == btnThemDV) {
			themDV();
		}
		if (o == btnXacNhan) {
			dispose();
		}
		if (o == btnHuyDV) {
			int row = tableDSDichVu.getSelectedRow();
			if (row < 0) {
				JOptionPane.showMessageDialog(this, "Chọn dịch vụ cần hủy");
			} else
				huyDV();
		}
	}

}
