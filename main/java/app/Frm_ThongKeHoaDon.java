package app;

import java.awt.EventQueue;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import java.awt.FlowLayout;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Panel;
import java.sql.Date;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import com.mindfusion.scheduling.Cursor;
import com.toedter.calendar.JDateChooser;

import connectDB.ConnectDB;
import dao.DanhSachHoaDon;
import dao.DanhSachKhachHang;
import dao.DanhSachNhanVien;
import dao.DanhSachPhong;
import entitys.HoaDonPhong;
import entitys.KhachHang;
import entitys.NhanVien;
import entitys.Phong;
import jiconfont.icons.FontAwesome;
import jiconfont.swing.IconFontSwing;

import javax.swing.border.LineBorder;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.JScrollPane;
import java.awt.ScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.ActionEvent;
import javax.swing.border.EtchedBorder;

public class Frm_ThongKeHoaDon extends JFrame implements ActionListener, MouseListener {
	private LocalDate now;
	private int ngay;
	private int thang;
	private int nam;
	private Date dNow;
	private JDateChooser dateChooserThongKeNgayBatDau;
	private JDateChooser dateChooserThongKeNgayKetThuc;
	private JTable table;
	private Panel panel_tong;
	private DefaultTableModel model;
	private JLabel lbltg, lbltongtk1, lbltongtk2, lblthongke1, lblthongke2;
	private DecimalFormat df;
	private DecimalFormat dfs;
	private DecimalFormat dfh;
	private SimpleDateFormat sf;
	private JScrollPane scrollPane;
	private JPanel panel_tkhd, panel_ngay, panel_thongke1, panel_thongke2;
	private JLabel lbltkhd, lbltgtk, lblnbd, lblnkt, lbliconthongke1, lbliconthongke2, lblbackground;
	private JTableHeader tbHeader;
	private JButton btnThongKe, btnLamMoi;
	private DateTimeFormatter dt;
	private FixButton btnInHD;
	DanhSachHoaDon dsHD;
	Frm_ThanhToan frmTT;
	HoaDonPhong hd;
	DanhSachPhong p;

	public Panel getFrmThongKeHoaDon() {
		return this.panel_tong;
	}

	public static void main(String[] args) {
		new Frm_ThongKeHoaDon().setVisible(true);

	}

	/**
	 * Create the application.
	 */
	public Frm_ThongKeHoaDon() {
		setTitle("THỐNG KÊ HÓA ĐƠN");
		setSize(1400, 670);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(true);
		setLocationRelativeTo(null);

		gui();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void gui() {
		getContentPane().setLayout(null);
		panel_tong = new Panel();
		panel_tong.setBounds(0, 0, 1400, 670);
		getContentPane().add(panel_tong);
		panel_tong.setLayout(null);

		panel_tkhd = new JPanel();
		panel_tkhd.setBackground(new Color(0, 0, 0));
		panel_tkhd.setBounds(536, 10, 298, 41);
		panel_tong.add(panel_tkhd);
		panel_tkhd.setLayout(null);
		lbltg = new JLabel("");
		lbltg.setForeground(new Color(255, 255, 255));
		lbltg.setFont(new Font("Tahoma", Font.BOLD, 17));
		lbltg.setBounds(653, 61, 310, 41);
		panel_tong.add(lbltg);
		btnInHD = new FixButton("Xuất hóa đơn");
		btnInHD.setIcon(new ImageIcon(Frm_ThongKeHoaDon.class.getResource("/imgs/icon_xuahoadon.png")));
		btnInHD.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnInHD.setBounds(650, 585, 200, 40);
		panel_tong.add(btnInHD);

		lbltkhd = new JLabel("THỐNG KÊ HÓA ĐƠN");
		lbltkhd.setForeground(new Color(255, 255, 255));
		lbltkhd.setBounds(52, 10, 223, 20);
		panel_tkhd.add(lbltkhd);
		lbltkhd.setFont(new Font("Tahoma", Font.BOLD, 18));

		lbltgtk = new JLabel("Thời gian thống kê:");
		lbltgtk.setForeground(new Color(255, 255, 255));
		lbltgtk.setFont(new Font("Tahoma", Font.BOLD, 17));
		lbltgtk.setBounds(450, 69, 228, 25);
		panel_tong.add(lbltgtk);
		panel_ngay = new JPanel();
		panel_ngay.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0), 5), "", TitledBorder.LEADING,
				TitledBorder.TOP, null, Color.BLACK));
		panel_ngay.setBackground(new Color(207, 169, 0));
		panel_ngay.setBounds(10, 112, 427, 228);
		panel_tong.add(panel_ngay);
		panel_ngay.setLayout(null);

		lblnbd = new JLabel("Ngày bắt đầu:");
		lblnbd.setForeground(new Color(255, 255, 255));
		lblnbd.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblnbd.setBounds(10, 22, 128, 38);
		panel_ngay.add(lblnbd);
		now = LocalDate.now();
		ngay = now.getDayOfMonth();
		thang = now.getMonthValue() - 1;
		nam = now.getYear() - 1900;

		dNow = new Date(nam, thang, ngay);

		dateChooserThongKeNgayBatDau = new JDateChooser();
		dateChooserThongKeNgayBatDau.getCalendarButton()
				.setIcon(new ImageIcon(Frm_ThongKePhong.class.getResource("/imgs/calendar.png")));
		dateChooserThongKeNgayBatDau.getCalendarButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		dateChooserThongKeNgayBatDau.setDateFormatString("dd/MM/yyyy");

		dateChooserThongKeNgayBatDau.setFont(new Font("Tahoma", Font.PLAIN, 15));
		dateChooserThongKeNgayBatDau.getCalendarButton().setPreferredSize(new Dimension(40, 30));
		dateChooserThongKeNgayBatDau
				.setIcon(new ImageIcon(Frm_ThongKePhong.class.getResource("/imgs/calendar.png")));

		dateChooserThongKeNgayBatDau.setBounds(149, 22, 226, 38);
		dateChooserThongKeNgayBatDau.setDate(dNow);
		panel_ngay.add(dateChooserThongKeNgayBatDau);

		lblnkt = new JLabel("Ngày kết thúc:");
		lblnkt.setForeground(new Color(255, 255, 255));
		lblnkt.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblnkt.setBounds(10, 80, 128, 38);
		panel_ngay.add(lblnkt);
		dateChooserThongKeNgayKetThuc = new JDateChooser();
		dateChooserThongKeNgayKetThuc.setDateFormatString("dd/MM/yyyy");

		dateChooserThongKeNgayKetThuc.setFont(new Font("Tahoma", Font.PLAIN, 15));
		dateChooserThongKeNgayKetThuc.getCalendarButton().setPreferredSize(new Dimension(40, 30));
		dateChooserThongKeNgayKetThuc
				.setIcon(new ImageIcon(Frm_ThongKePhong.class.getResource("/imgs/calendar.png")));

		dateChooserThongKeNgayKetThuc.setBounds(149, 80, 226, 38);
		dateChooserThongKeNgayKetThuc.setDate(dNow);
		panel_ngay.add(dateChooserThongKeNgayKetThuc);

		btnThongKe = new FixButton("Thống kê");
		btnThongKe.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnThongKe.setBounds(84, 128, 253, 42);
		btnThongKe.setIcon(new ImageIcon(Frm_ThongKeHoaDon.class.getResource("/imgs/thongke.png")));
		panel_ngay.add(btnThongKe);

		btnLamMoi = new FixButton("Làm mới");
		btnLamMoi.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnLamMoi.setBounds(84, 176, 253, 42);
		btnLamMoi.setIcon(new ImageIcon(Frm_ThongKeHoaDon.class.getResource("/imgs/btn_lammoi.png")));
		panel_ngay.add(btnLamMoi);

		panel_thongke1 = new JPanel();
		panel_thongke1
				.setBorder(new TitledBorder(
						new TitledBorder(
								new TitledBorder(new LineBorder(new Color(0, 0, 0), 5), "", TitledBorder.LEADING,
										TitledBorder.TOP, null, new Color(0, 0, 0)),
								"", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)),
						"", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_thongke1.setBackground(new Color(189, 0, 88));
		panel_thongke1.setBounds(447, 112, 471, 228);
		panel_tong.add(panel_thongke1);
		panel_thongke1.setLayout(null);

		lbliconthongke1 = new JLabel("");
		lbliconthongke1.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lbliconthongke1.setForeground(new Color(255, 255, 255));
		lbliconthongke1.setBounds(205, 25, 64, 64);
		panel_thongke1.add(lbliconthongke1);
		lbliconthongke1.setIcon(new ImageIcon(Frm_ThongKeHoaDon.class.getResource("/imgs/icon_tkhd.png")));

		lblthongke1 = new JLabel("");
		lblthongke1.setForeground(new Color(255, 255, 255));
		lblthongke1.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblthongke1.setBounds(140, 115, 200, 35);
		panel_thongke1.add(lblthongke1);
		lbltongtk1 = new JLabel("");
		lbltongtk1.setForeground(new Color(255, 255, 255));
		lbltongtk1.setFont(new Font("Tahoma", Font.BOLD, 20));
		lbltongtk1.setBounds(220, 140, 70, 43);
		panel_thongke1.add(lbltongtk1);

		panel_thongke2 = new JPanel();
		panel_thongke2.setBorder(new LineBorder(new Color(0, 0, 0), 5));
		panel_thongke2.setBackground(new Color(222, 155, 0));
		panel_thongke2.setBounds(928, 112, 448, 228);
		panel_tong.add(panel_thongke2);
		panel_thongke2.setLayout(null);

		lbliconthongke2 = new JLabel("");
		lbliconthongke2.setBounds(200, 35, 64, 64);
		panel_thongke2.add(lbliconthongke2);
		lbliconthongke2.setIcon(new ImageIcon(Frm_ThongKeHoaDon.class.getResource("/imgs/icon_tkhd_1.png")));

		lblthongke2 = new JLabel("");
		lblthongke2.setForeground(new Color(255, 255, 255));
		lblthongke2.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblthongke2.setBounds(120, 115, 265, 30);
		panel_thongke2.add(lblthongke2);
		lbltongtk2 = new JLabel("");
		lbltongtk2.setForeground(new Color(255, 255, 255));
		lbltongtk2.setFont(new Font("Tahoma", Font.BOLD, 20));
		lbltongtk2.setBounds(150, 135, 250, 41);
		panel_thongke2.add(lbltongtk2);
		String col[] = { "Mã HD", "Ngày lập HD", "Giờ bắt đầu thuê", "Giờ trả phòng", "Tên khách hàng", "Tên NV",
				"Mã phòng", "Tổng tiền" };
		model = new DefaultTableModel(col, 0);
		table = new JTable(model);
		table.setFont(new Font("Tahoma", Font.PLAIN, 15));
		tbHeader = table.getTableHeader();
		tbHeader.setBackground(new java.awt.Color(0, 0, 0));
		tbHeader.setForeground(Color.WHITE);
		tbHeader.setFont(new Font("Tahoma", Font.BOLD, 15));
		table.setBackground(Color.white);
		table.setFont(new Font("SansSerif", Font.PLAIN, 14));
		table.setSelectionBackground(new Color(158, 207, 0));
		table.setSelectionForeground(new Color(255, 255, 255));
		table.setRowHeight(30);
		scrollPane = new JScrollPane(table);
		scrollPane.setBorder(new LineBorder(new Color(158, 207, 0), 1, true));
		scrollPane.setBackground(Color.BLACK);
		scrollPane.setBounds(10, 350, 1366, 228);
		scrollPane.getHorizontalScrollBar();
		scrollPane.setViewportView(table);
		panel_tong.add(scrollPane);
		lblbackground = new JLabel("");
		lblbackground.setBounds(0, 0, 1400, 700);
		panel_tong.add(lblbackground);
		lblbackground.setIcon(new ImageIcon(Frm_ThongKePhong.class.getResource("/imgs/bg_chot1.png")));
		// khai bao định dạng
		df = new DecimalFormat("###,### VNĐ");
		dfs = new DecimalFormat("### p");
		dfh = new DecimalFormat("### h");
		sf = new SimpleDateFormat("dd/MM/yyy");
		dt = DateTimeFormatter.ofPattern("HH:mm");
		// add su kien button
		btnThongKe.addActionListener(this);
		btnLamMoi.addActionListener(this);
		btnInHD.addActionListener(this);
		table.addMouseListener(this);
		// kết nối data
		ConnectDB.getInstance().connect();
		// Danh sach Khach Hang
		dsHD = new DanhSachHoaDon();
	}

	/**
	 * Đưa dữ liệu từ danh sách lên bảng
	 */
	public void upTable(ArrayList<HoaDonPhong> list) {
		int i = 0;
		for (HoaDonPhong hd : list) {
			Object[] obj = new Object[8];
			obj[0] = hd.getMaHoaDon().trim();
			obj[1] = hd.getNgayLapHoaDon();
			obj[2] = hd.getGioBatDauThue().format(dt);
			obj[3] = hd.getGioTraPhong().format(dt);
			obj[4] = hd.getMaKhachHang().getHoTenKhachHang();
			obj[5] = hd.getMaNhanVien().getHoTenNhanVien();
			obj[6] = hd.getPhong().getMaPhong();
			obj[7] = df.format(hd.getThanhTien());
			if (table.getRowCount() == 0)
				model.addRow(obj);
			else {
				for (i = 0; i < table.getRowCount(); i++) {
					if (obj[0].toString().equals(table.getValueAt(i, 0)))
						break;
				}
				if (i == table.getRowCount())
					model.addRow(obj);
			}
		}

	}

	/**
	 * Thống kê số hóa đơn theo ngày
	 */
	public void loadThongKeHoaDon() {

		java.util.Date utilngayBD = dateChooserThongKeNgayBatDau.getDate();
		java.util.Date utilngayKT = dateChooserThongKeNgayKetThuc.getDate();
		@SuppressWarnings("deprecation")
		Date ngayBatDau = new Date(utilngayBD.getYear(), utilngayBD.getMonth(), utilngayBD.getDate());
		@SuppressWarnings("deprecation")
		Date ngayKetThuc = new Date(utilngayKT.getYear(), utilngayKT.getMonth(), utilngayKT.getDate());
		if (ngayBatDau.before(ngayKetThuc) || ngayBatDau.equals(ngayKetThuc)) {
			ArrayList<HoaDonPhong> listHD = dsHD.getDSHDTheoNgay(ngayBatDau, ngayKetThuc);
			int tong = listHD.size();
			lblthongke1.setText("Tổng số hóa đơn:");
			lbltongtk1.setText(String.valueOf(tong));
			float tongtien = dsHD.tongTienHDTheoNgay(ngayBatDau, ngayKetThuc);
			lblthongke2.setText("Tổng tiền các hóa đơn:");
			lbltongtk2.setText(df.format(tongtien));
			upTable(listHD);
		} else
			JOptionPane.showMessageDialog(this, "Ngày bắt đầu phải trước hoặc bằng ngày kết thúc!");
	}

	/**
	 * đưa ngày được chọn thống kê lên thời gian thống kê
	 */
	public void loadThongKeSoGio() {
		java.util.Date utilngayBD = dateChooserThongKeNgayBatDau.getDate();
		java.util.Date utilngayKT = dateChooserThongKeNgayKetThuc.getDate();
		@SuppressWarnings("deprecation")
		Date ngayden = new Date(utilngayBD.getYear(), utilngayBD.getMonth(), utilngayBD.getDate());
		@SuppressWarnings("deprecation")
		Date ngayKT = new Date(utilngayKT.getYear(), utilngayKT.getMonth(), utilngayKT.getDate());
		lbltg.setText(sf.format(ngayden) + " - " + sf.format(ngayKT));

	}

	/**
	 * xóa tất cả dữ liệu trên frame đưa về mặc định
	 */
	public void resetAll() {
		dateChooserThongKeNgayBatDau.setDate(dNow);
		dateChooserThongKeNgayKetThuc.setDate(dNow);
		lbltongtk1.setText("");
		lbltongtk2.setText("");
		lblthongke1.setText("");
		lblthongke2.setText("");
		lbltg.setText("");
		clearTable();

	}

	/**
	 * xóa dữ liệu của table
	 */
	public void clearTable() {
		while (table.getRowCount() > 0) {
			model.removeRow(0);
		}
	}

	/**
	 * xóa dữ liệu của thống kê tổng số hóa đơn của nhân viên
	 */
	public void clearTK2() {
		lbltongtk2.setText("");
		lblthongke2.setText("");
	}

// Xuất hóa đơn PDF
	public void XuatHoaDonPDF() throws Exception {
		int i = table.getSelectedRow();
		if (i == -1) {
			JOptionPane.showMessageDialog(this, "Không có hóa đơn nào để xuất !");
		} else {
			String maHDIN = table.getValueAt(i, 0).toString();
			java.util.Date utilngayBD = dateChooserThongKeNgayBatDau.getDate();
			java.util.Date utilngayKT = dateChooserThongKeNgayKetThuc.getDate();
			@SuppressWarnings("deprecation")
			Date ngayBatDau = new Date(utilngayBD.getYear(), utilngayBD.getMonth(), utilngayBD.getDate());
			@SuppressWarnings("deprecation")
			Date ngayKetThuc = new Date(utilngayKT.getYear(), utilngayKT.getMonth(), utilngayKT.getDate());
			ArrayList<HoaDonPhong> listHD = dsHD.getDSHDTheoNgay(ngayBatDau, ngayKetThuc);
			for (HoaDonPhong hd : listHD) {
				for (i = 0; i < table.getRowCount(); i++) {
					if (maHDIN.equals((hd.getMaHoaDon()))) {
						frmTT = new Frm_ThanhToan(hd);
					}
				}
			}
			frmTT.xuatHoaDonFilePDF();
		}

	}

	/**
	 * sự kiện click chuột vào table
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
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

	/**
	 * Sự kiện nút thống kê và làm mới
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object o = e.getSource();
		if (o == btnThongKe) {
			clearTable();
			clearTK2();
			loadThongKeSoGio();
			loadThongKeHoaDon();
		} else if (o == btnLamMoi) {
			resetAll();
		} else if (o == btnInHD) {
			try {
				XuatHoaDonPDF();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		}
	}

}
