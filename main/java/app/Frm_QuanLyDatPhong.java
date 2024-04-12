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
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import javax.swing.AbstractAction;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.KeyStroke;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.LineBorder;

import com.toedter.calendar.JDateChooser;

import connectDB.ConnectDB;
import dao.DanhSachDatPhong;
import dao.DanhSachHoaDon;
import dao.DanhSachKhachHang;
import dao.DanhSachNhanVien;
import dao.DanhSachPhong;
import dao.DanhSachThuePhong;
import dao.DanhSachTinhTrang;
import dao.Dao_PhatSinhMa;
import entitys.DichVu;
import entitys.HoaDonPhong;
import entitys.KhachHang;
import entitys.LoaiDichVu;
import entitys.LoaiHoaDon;
import entitys.NhanVien;
import entitys.Phong;
import entitys.TinhTrangPhong;

import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.SystemColor;
import java.awt.Component;
import javax.swing.SwingConstants;
import java.text.SimpleDateFormat;

public class Frm_QuanLyDatPhong extends JFrame implements ActionListener, MouseListener, PropertyChangeListener {
	private JScrollPane scrDSPDD;
	private JScrollPane scrollPane;
	private JPanel pnLoaiPhong, pnDSP, pnTTDDP, pnDSPDD;
	private JLabel lbLoaiPhongTK, lbTinhTrang, lbDSPhong, lbBGQLDP, lbTTDDP, lbSDT, lbTenKH, lbLoaiKH, lbNgayDat,
			lbThoiGianDat, lbIconSearch, lbDSPDD;
	private JComboBox comboLKH, cbGio, cbPhut;
	private JTextField txtSDT, txtKhachHang;
	private FixButton btnLamMoi, btnHuyDatPhong, btnDatPhong, btnNhanPhong;
	private FixButton2 btnTatCa, btnPhongThuong, btnPhongVip;
	private JDateChooser ngayDatPhong;
	private JRadioButton rDangDat, radioTrong;
	private Date ngayHienTai;
	private Panel pnQLDP;
	private int ngay, thang, nam;
	private JTable tableDSPhong, tableDSPhong1;
	private DefaultTableModel model, model1;
	private ButtonGroup bg;
	public DanhSachDatPhong dsDP;
	public DanhSachKhachHang dsKH;

	Date ngayDat;
	String dateString;
	boolean flag = false;

	DanhSachThuePhong dsTP;
	DanhSachHoaDon dsHD;
	LocalDate localDate;
	LocalDate ngayHT;
	LocalTime localTime;
	NhanVien nv;
	private DecimalFormat df;
	KeyStroke keyStrokeCTRL1, keyStrokeCTRL2, keyStrokeCTRL3, keyStrokeCTRL4;

	public Panel getFrmQuanLyDatPhong() {
		return this.pnQLDP;
	}

	public Frm_QuanLyDatPhong(NhanVien nv) {
		this.nv = nv;
		setTitle("QUẢN LÝ ĐẶT PHÒNG");
		setSize(1400, 670);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(true);
		setLocationRelativeTo(null);
		gui();
	}

	public void gui() {
		getContentPane().setLayout(null);

		pnQLDP = new Panel();
		pnQLDP.setBounds(0, 0, 1400, 670);
		getContentPane().add(pnQLDP);
		pnQLDP.setLayout(null);

		pnDSPDD = new JPanel();
		pnDSPDD.setLayout(null);
		pnDSPDD.setBackground(Color.WHITE);
		pnDSPDD.setBounds(32, 358, 982, 264);
		pnQLDP.add(pnDSPDD);

		lbDSPDD = new JLabel("Danh phòng đã đặt");
		lbDSPDD.setFont(new Font("Tahoma", Font.BOLD, 15));
		lbDSPDD.setBounds(10, 0, 145, 25);
		pnDSPDD.add(lbDSPDD);

		pnTTDDP = new JPanel();
		pnTTDDP.setBorder(new LineBorder(new Color(0, 0, 0), 3));
		pnTTDDP.setBackground(new java.awt.Color(207, 169, 0));
		pnTTDDP.setBounds(32, 10, 650, 320);
		pnQLDP.add(pnTTDDP);
		pnTTDDP.setLayout(null);

		lbTTDDP = new JLabel("Thông tin đơn đặt phòng");
		lbTTDDP.setFont(new Font("Tahoma", Font.BOLD, 17));
		lbTTDDP.setForeground(new Color(255, 255, 255));
		lbTTDDP.setBounds(10, 10, 250, 20);
		pnTTDDP.add(lbTTDDP);

		lbSDT = new JLabel("Số điện thoại:");
		lbSDT.setForeground(Color.WHITE);
		lbSDT.setFont(new Font("Tahoma", Font.BOLD, 15));
		lbSDT.setBounds(80, 41, 200, 38);
		pnTTDDP.add(lbSDT);

		lbTenKH = new JLabel("Tên khách hàng:");
		lbTenKH.setForeground(Color.WHITE);
		lbTenKH.setFont(new Font("Tahoma", Font.BOLD, 15));
		lbTenKH.setBounds(80, 76, 200, 38);
		pnTTDDP.add(lbTenKH);

		lbLoaiKH = new JLabel("Loại khách hàng:");
		lbLoaiKH.setForeground(Color.WHITE);
		lbLoaiKH.setFont(new Font("Tahoma", Font.BOLD, 15));
		lbLoaiKH.setBounds(80, 116, 200, 38);
		pnTTDDP.add(lbLoaiKH);

		lbNgayDat = new JLabel("Ngày đặt:");
		lbNgayDat.setForeground(Color.WHITE);
		lbNgayDat.setFont(new Font("Tahoma", Font.BOLD, 15));
		lbNgayDat.setBounds(80, 155, 95, 38);
		pnTTDDP.add(lbNgayDat);

		lbThoiGianDat = new JLabel("Thời gian:");
		lbThoiGianDat.setForeground(Color.WHITE);
		lbThoiGianDat.setFont(new Font("Tahoma", Font.BOLD, 15));
		lbThoiGianDat.setBounds(80, 203, 90, 25);
		pnTTDDP.add(lbThoiGianDat);

		txtSDT = new JTextField();
		txtSDT.setFont(new Font("Tahoma", Font.BOLD, 15));
		txtSDT.setBounds(230, 50, 300, 25);
		pnTTDDP.add(txtSDT);

		txtKhachHang = new JTextField();
		txtKhachHang.setFont(new Font("Tahoma", Font.BOLD, 15));
		txtKhachHang.setBounds(230, 85, 300, 25);
		txtKhachHang.disable();
		pnTTDDP.add(txtKhachHang);

		comboLKH = new JComboBox();
		comboLKH.setEnabled(false);
		comboLKH.setModel(new DefaultComboBoxModel(new String[] { "Khách hàng VIP", "Khách hàng thường" }));
		comboLKH.setSelectedIndex(0);
		comboLKH.setFont(new Font("Tahoma", Font.BOLD, 15));
		comboLKH.setBounds(230, 120, 300, 30);
		pnTTDDP.add(comboLKH);

		lbIconSearch = new JLabel();
		lbIconSearch.setIcon(new ImageIcon(Frm_QuanLyDatPhong.class.getResource("/imgs/icon_search.png")));
		lbIconSearch.setBounds(540, 47, 32, 30);
		pnTTDDP.add(lbIconSearch);

		ngayDatPhong = new JDateChooser();
		ngayDatPhong.getCalendarButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		ngayDatPhong.setDateFormatString("dd/MM/yyyy");

		ngayDatPhong.setFont(new Font("Tahoma", Font.BOLD, 15));
		ngayDatPhong.getCalendarButton().setPreferredSize(new Dimension(40, 30));
		ngayDatPhong.setIcon(new ImageIcon(Frm_QuanLyDatPhong.class.getResource("/imgs/calendar.png")));

		ngayDatPhong.setBounds(230, 155, 300, 30);

		LocalDateTime localDateTime = LocalDateTime.now();
		ngay = localDateTime.getDayOfMonth();
		thang = localDateTime.getMonthValue();
		nam = localDateTime.getYear();
		ngayHienTai = new Date(nam - 1900, thang - 1, ngay);

		ngayDatPhong.setDate(ngayHienTai);
		pnTTDDP.add(ngayDatPhong);

		cbGio = new JComboBox();
		cbGio.setFont(new Font("Tahoma", Font.BOLD, 18));
		cbGio.setModel(new DefaultComboBoxModel(new String[] { "09", "10", "11", "12", "13", "14", "15", "16", "17",
				"18", "19", "20", "21", "22", "23" }));
		cbGio.setBounds(230, 199, 50, 30);
		pnTTDDP.add(cbGio);

		cbPhut = new JComboBox();
		cbPhut.setModel(new DefaultComboBoxModel(new String[] { "00", "15", "30", "45" }));
		cbPhut.setFont(new Font("Tahoma", Font.BOLD, 18));
		cbPhut.setBounds(290, 199, 50, 30);
		pnTTDDP.add(cbPhut);

		pnLoaiPhong = new JPanel();
		pnLoaiPhong.setBorder(new LineBorder(new Color(0, 0, 0), 3));
		pnLoaiPhong.setBackground(new java.awt.Color(189, 0, 88));
		pnLoaiPhong.setBounds(700, 10, 650, 80);
		pnQLDP.add(pnLoaiPhong);
		pnLoaiPhong.setLayout(null);

		lbLoaiPhongTK = new JLabel("Loại phòng:");
		lbLoaiPhongTK.setForeground(Color.WHITE);
		lbLoaiPhongTK.setFont(new Font("Tahoma", Font.BOLD, 15));
		lbLoaiPhongTK.setBounds(46, 11, 100, 25);
		pnLoaiPhong.add(lbLoaiPhongTK);

		pnQLDP.add(pnLoaiPhong);

		btnTatCa = new FixButton2("Tất cả");
		btnTatCa.setBounds(165, 10, 100, 25);
		pnLoaiPhong.add(btnTatCa);

		btnPhongVip = new FixButton2("Phòng VIP");
		btnPhongVip.setBounds(295, 10, 120, 25);
		pnLoaiPhong.add(btnPhongVip);

		btnPhongThuong = new FixButton2("Phòng thường");
		btnPhongThuong.setBounds(440, 10, 150, 25);
		pnLoaiPhong.add(btnPhongThuong);

		lbTinhTrang = new JLabel("Tình trạng:");
		lbTinhTrang.setForeground(Color.WHITE);
		lbTinhTrang.setFont(new Font("Tahoma", Font.BOLD, 15));

		lbTinhTrang.setBounds(46, 50, 100, 25);

		pnLoaiPhong.add(lbTinhTrang);

		rDangDat = new JRadioButton("Đang đặt");
		rDangDat.setOpaque(false);
		// bỏ viền
		rDangDat.setFocusPainted(false);
		rDangDat.setFont(new Font("Tahoma", Font.BOLD, 15));
		rDangDat.setBounds(164, 50, 111, 21);
		pnLoaiPhong.add(rDangDat);

		radioTrong = new JRadioButton("Trống");
		radioTrong.setContentAreaFilled(false);
		radioTrong.setFont(new Font("Tahoma", Font.BOLD, 15));
		radioTrong.setBounds(295, 50, 103, 21);
		radioTrong.setFocusPainted(false);
		pnLoaiPhong.add(radioTrong);

		bg = new ButtonGroup();
		bg.add(radioTrong);
		bg.add(rDangDat);

		pnDSP = new JPanel();
		pnDSP.setBackground(Color.WHITE);
		pnDSP.setBounds(700, 100, 650, 230);
		pnQLDP.add(pnDSP);
		pnDSP.setLayout(null);

		lbDSPhong = new JLabel("Danh sách phòng");
		lbDSPhong.setFont(new Font("Tahoma", Font.BOLD, 15));
		lbDSPhong.setBounds(10, 0, 150, 25);
		pnDSP.add(lbDSPhong);

		String col[] = { "Mã phòng", "Loại phòng", "Sức chứa", "Giá phòng", "Tình trạng" };
		model1 = new DefaultTableModel(col, 0) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false; // Không cho phép chỉnh sửa ô
			}
		};

		tableDSPhong = new JTable(model1);
		tableDSPhong.setBackground(Color.WHITE);
		// Set màu cho table
		// Set màu cho cột tiêu đề
		JTableHeader tbHeader = tableDSPhong.getTableHeader();
		tbHeader.setBackground(new java.awt.Color(0, 0, 0));
		tbHeader.setForeground(Color.WHITE);
		tbHeader.setFont(new Font("Tahoma", Font.BOLD, 14));
		// Set màu các dòng

//		tableDSPhong.setShowHorizontalLines(true);
//		tableDSPhong.setShowGrid(true);
		tableDSPhong.setBackground(Color.white);
		tableDSPhong.setFont(new Font("SansSerif", Font.PLAIN, 13));
		tableDSPhong.setSelectionBackground(new Color(158, 207, 0));
		tableDSPhong.setSelectionForeground(new Color(255, 255, 255));
		tableDSPhong.setRowHeight(30);

		scrollPane = new JScrollPane(tableDSPhong, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setBorder(new LineBorder(new Color(158, 207, 0), 1, true));
		scrollPane.setBackground(Color.BLACK);
		scrollPane.setBounds(0, 25, 650, 200);
		scrollPane.getHorizontalScrollBar();
		pnDSP.add(scrollPane);

		scrollPane.setViewportView(tableDSPhong);

		String col1[] = { "Mã hóa đơn", "Mã phòng", "Tên khách hàng", "SĐT", "Ngày", "Thời gian", "Tên nhân viên" };
		model = new DefaultTableModel(col1, 0) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false; // Không cho phép chỉnh sửa ô
			}
		};
		tableDSPhong1 = new JTable(model);
		tableDSPhong1.setBackground(Color.WHITE);

		// Set màu cho table
		// Set màu cho cột tiêu đề
		JTableHeader tbHeader1 = tableDSPhong1.getTableHeader();
		tbHeader1.setBackground(new java.awt.Color(0, 0, 0));
		tbHeader1.setForeground(Color.WHITE);
		tbHeader1.setFont(new Font("Tahoma", Font.BOLD, 14));
		// Set màu các dòng

		tableDSPhong1.setBackground(Color.white);
		tableDSPhong1.setFont(new Font("SansSerif", Font.PLAIN, 13));
		tableDSPhong1.setSelectionBackground(new Color(158, 207, 0));
		tableDSPhong1.setSelectionForeground(new Color(255, 255, 255));
		tableDSPhong1.setRowHeight(30);

		scrDSPDD = new JScrollPane((Component) null, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrDSPDD.setBorder(new LineBorder(new Color(158, 207, 0), 1, true));
		scrDSPDD.setBackground(Color.BLACK);
		scrDSPDD.setBounds(0, 25, 982, 229);
		pnDSPDD.add(scrDSPDD);
		scrDSPDD.setViewportView(tableDSPhong1);

		btnLamMoi = new FixButton("Làm mới");
		btnLamMoi.setText("Làm mới (Ctrl 4)");
		btnLamMoi.setIcon(new ImageIcon(Frm_QuanLyDatPhong.class.getResource("/imgs/btn_lammoi.png")));
		btnLamMoi.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnLamMoi.setBounds(1116, 571, 230, 51);
		pnQLDP.add(btnLamMoi);

		btnHuyDatPhong = new FixButton("Hủy đặt phòng");
		btnHuyDatPhong.setText("Hủy đặt phòng (Ctrl 3)");
		btnHuyDatPhong.setIcon(new ImageIcon(Frm_QuanLyDatPhong.class.getResource("/imgs/btn_huydv.png")));
		btnHuyDatPhong.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnHuyDatPhong.setBounds(1116, 499, 230, 51);
		pnQLDP.add(btnHuyDatPhong);

		btnDatPhong = new FixButton("Đặt phòng");
		btnDatPhong.setText("Đặt phòng (Ctrl 2)");
		btnDatPhong.setMouseHoverEnable(true);
		btnDatPhong.setIcon(new ImageIcon(Frm_QuanLyDatPhong.class.getResource("/imgs/btn_xacnhan.png")));
		btnDatPhong.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnDatPhong.setBounds(1116, 430, 230, 51);
		pnQLDP.add(btnDatPhong);

		btnNhanPhong = new FixButton("Nhận phòng");
		btnNhanPhong.setText("Nhận phòng (Ctrl 1)");
		btnNhanPhong.setIcon(new ImageIcon(Frm_QuanLyDatPhong.class.getResource("/imgs/btn_nhanphong.png")));
		btnNhanPhong.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNhanPhong.setBounds(1116, 358, 230, 51);
		pnQLDP.add(btnNhanPhong);

		// add background ở cuối
		lbBGQLDP = new JLabel("");
		lbBGQLDP.setIcon(new ImageIcon(Frm_QuanLyDatPhong.class.getResource("/imgs/bg_chot1.png")));
		lbBGQLDP.setBounds(0, 0, 1400, 670);
		pnQLDP.add(lbBGQLDP);

		rDangDat.addActionListener(this);
		radioTrong.addActionListener(this);

		btnDatPhong.addActionListener(this);
		btnHuyDatPhong.addActionListener(this);
		btnLamMoi.addActionListener(this);
		btnNhanPhong.addActionListener(this);
		btnPhongThuong.addActionListener(this);
		btnPhongVip.addActionListener(this);
		btnTatCa.addActionListener(this);

		btnDatPhong.addMouseListener(this);
		btnHuyDatPhong.addMouseListener(this);
		btnLamMoi.addMouseListener(this);
		btnNhanPhong.addMouseListener(this);
		btnPhongThuong.addMouseListener(this);
		btnPhongVip.addMouseListener(this);
		btnTatCa.addMouseListener(this);
		lbIconSearch.addMouseListener(this);
		tableDSPhong.addMouseListener(this);
		tableDSPhong1.addMouseListener(this);
		ngayDatPhong.addPropertyChangeListener(this);
		// connect db
		ConnectDB.getInstance().connect();
		dsDP = new DanhSachDatPhong();
		dsKH = new DanhSachKhachHang();
		dsTP = new DanhSachThuePhong();
		dsHD = new DanhSachHoaDon();
		ngayDat = ngayDatPhong.getDate();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dateString = dateFormat.format(ngayDat);
		clearTable();
		upTable1(dsDP.getAllRoomByDate(dateString));
		upTable2(dsDP.getAllRoomStatusByDate());
		getIndexRow();

		// add và định nghĩa các hot key cho ứng dụng
		keyStrokeCTRL1 = KeyStroke.getKeyStroke("ctrl 1");
		keyStrokeCTRL2 = KeyStroke.getKeyStroke("ctrl 2");
		keyStrokeCTRL3 = KeyStroke.getKeyStroke("ctrl 3");
		keyStrokeCTRL4 = KeyStroke.getKeyStroke("ctrl 4");

		// Phím nóng
		addHotKey1();
		addHotKey2();
		addHotKey3();
		addHotKey4();
		xoaTrang();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if (o == btnDatPhong) {
			boolean isBooked = datPhong();
			if (isBooked) {
				clearTable();
				upTable1(dsDP.getAllRoomByDate(dateString));
				upTable2(dsDP.getAllRoomStatusByDate());
			}
		} else if (o == btnNhanPhong) {
			boolean isNhanPhong = nhanPhong();
			if (isNhanPhong) {
				clearTable();
				upTable1(dsDP.getAllRoomByDate(dateString));
				upTable2(dsDP.getAllRoomStatusByDate());
			}
		} else if (o == btnLamMoi) {
			xoaTrang();

		} else if (o == btnPhongThuong) {
			btnPhongThuong.setBackground(getBackground());
			ArrayList<Phong> listN = dsDP.getAllRoomByType(dateString, "NOR");
			upTable1(listN);
			// r dang dat
		} else if (o == rDangDat) {
			ArrayList<Phong> list = dsDP.getAllRoomByDate(dateString);
			upTableDangDat(list);
			//
		} else if (o == radioTrong) {
			ArrayList<Phong> list = dsDP.getAllRoomByDate(dateString);
			upTableTrong(list);
			//
		} else if (o == btnTatCa) {
			ArrayList<Phong> list = dsDP.getAllRoomByDate(dateString);
			upTable1(list);
		} else if (o == btnPhongVip) {
			ArrayList<Phong> listT = dsDP.getAllRoomByType(dateString, "VIP");
			upTable1(listT);
		} else {
			boolean huyDatPhong = huyDatPhong();
			if (huyDatPhong) {
				clearTable();
				upTable1(dsDP.getAllRoomByDate(dateString));
				upTable2(dsDP.getAllRoomStatusByDate());
			}
		}
	}

	public void getIndexRow() {
		int isSelected1 = tableDSPhong.getSelectedRow();
		int isSelected2 = tableDSPhong1.getSelectedRow();

		if (isSelected1 == -1 && isSelected2 == -1) {
			btnDatPhong.setEnabled(false);
			btnNhanPhong.setEnabled(false);
			btnHuyDatPhong.setEnabled(false);
			tableDSPhong.clearSelection();
			tableDSPhong1.clearSelection();
		}

		if (isSelected1 != -1) {
			btnDatPhong.setEnabled(true);
			btnNhanPhong.setEnabled(false);
			btnHuyDatPhong.setEnabled(false);
			tableDSPhong1.clearSelection();
		}

		if (isSelected2 != -1) {
			btnDatPhong.setEnabled(false);
			btnNhanPhong.setEnabled(true);
			btnHuyDatPhong.setEnabled(true);
			tableDSPhong.clearSelection();
		}
	}

	public void upTableDangDat(ArrayList<Phong> arr) {
		df = new DecimalFormat("###,### VNĐ");
		model1.setRowCount(0);
		for (Phong p : arr) {
			if (p.getMaTinhTrangPhong().getMaTinhTrangPhong().equalsIgnoreCase("BOOK")) {
				Object[] obj = new Object[5];
				obj[0] = p.getMaPhong().trim();
				obj[1] = p.getMaLoaiPhong().getTenLoaiPhong();
				obj[2] = p.getSucChua();
				obj[3] = df.format(p.getGiaPhong());
				obj[4] = p.getMaTinhTrangPhong().getTenTinhTrangPhong();
				model1.addRow(obj);
			}
			continue;
		}
	}

	public void upTableTrong(ArrayList<Phong> arr) {
		df = new DecimalFormat("###,### VNĐ");
		model1.setRowCount(0);
		for (Phong p : arr) {
			if (p.getMaTinhTrangPhong().getMaTinhTrangPhong().equalsIgnoreCase("EMPT")) {
				Object[] obj = new Object[5];
				obj[0] = p.getMaPhong().trim();
				obj[1] = p.getMaLoaiPhong().getTenLoaiPhong();
				obj[2] = p.getSucChua();
				obj[3] = df.format(p.getGiaPhong());
				obj[4] = p.getMaTinhTrangPhong().getTenTinhTrangPhong();
				model1.addRow(obj);
			}
			continue;
		}
	}

	public void upTable1(ArrayList<Phong> arr) {
		df = new DecimalFormat("###,### VNĐ");
		model1.setRowCount(0);
		for (Phong p : arr) {
			Object[] obj = new Object[5];
			obj[0] = p.getMaPhong().trim();
			obj[1] = p.getMaLoaiPhong().getTenLoaiPhong();
			obj[2] = p.getSucChua();
			obj[3] = df.format(p.getGiaPhong());
			obj[4] = p.getMaTinhTrangPhong().getTenTinhTrangPhong();
			model1.addRow(obj);
		}
	}

	public void upTable2(ArrayList<HoaDonPhong> arr) {
		model.setRowCount(0);
		for (HoaDonPhong hd : arr) {
			Object[] obj = new Object[7];
			obj[0] = hd.getMaHoaDon().trim();
			obj[1] = hd.getPhong().getMaPhong().trim();
			obj[2] = hd.getMaKhachHang().getHoTenKhachHang().trim();
			obj[3] = hd.getMaKhachHang().getSoDienThoai();
			obj[4] = hd.getNgayDat().toString();
			obj[5] = hd.getGioDat().toString();
			obj[6] = hd.getMaNhanVien().getHoTenNhanVien().trim();
			model.addRow(obj);
		}
	}

	public KhachHang ktraKHDAT() {
		String sdt = txtSDT.getText();
		KhachHang kh = dsKH.getKhachHangTheoSDT(sdt);
		if (sdt.equals("")) {
			JOptionPane.showMessageDialog(this, "Số điện thoại không được để trống");
			txtSDT.requestFocus();
		} else if (!sdt.matches("\\d{10}")) {
			JOptionPane.showMessageDialog(this, "Số điện thoại không quá 10 số và ko có kí tự");
			txtSDT.setText("");
			txtSDT.requestFocus();
		} else if (kh != null) {
			txtKhachHang.setText(kh.getHoTenKhachHang());
			if (kh.getLoaiKhachHang().getMaLoaiKhachHang().equalsIgnoreCase("VIP"))
				comboLKH.setSelectedIndex(0);
			else
				comboLKH.setSelectedIndex(1);
		} else {
			JOptionPane.showMessageDialog(this, "Khách hàng chưa có trong hệ thống \nthêm khách hàng mới!!!");
			Frm_ThemKhachHang frm_ThemKH = new Frm_ThemKhachHang(sdt);
			frm_ThemKH.setVisible(true);
		}
		return kh;
	}

	public void xoaTrang() {
		txtKhachHang.setText("");
		txtSDT.setText("");
		comboLKH.setSelectedIndex(0);
		cbGio.setSelectedIndex(0);
		cbPhut.setSelectedIndex(0);
		rDangDat.setSelected(false);
		radioTrong.setSelected(false);
		bg.clearSelection();
		ArrayList<Phong> list = dsDP.getAllRoomByDate(dateString);
		clearTable();
		upTable1(list);
		upTable2(dsDP.getAllRoomStatusByDate());
		try {
			huyDatPhongQuaHan();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	private boolean datPhong() {
		String sdt = txtSDT.getText().trim();
		String gio = (String) cbGio.getSelectedItem();
		String phut = (String) cbPhut.getSelectedItem();
		String tgian = gio + ":" + phut + ":" + "00".trim();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
		localTime = LocalTime.parse(tgian, formatter);
		ngayHT = ngayHienTai.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

		int row = tableDSPhong.getSelectedRow();

		KhachHang kh = ktraKHDAT();
		if (kh != null) {
			ngayDat = ngayDatPhong.getDate();
			Instant instant = ngayDat.toInstant();
			localDate = instant.atZone(ZoneId.systemDefault()).toLocalDate();
			// Có thể lấy mã để tìm phòng theo mã. Sau đó set loại hoá đơn,
			// sinh mã hoá đơn tự động, lấy nhân viên và khách hàng.
			String ma = tableDSPhong.getValueAt(row, 0).toString();
			DanhSachPhong dsP = new DanhSachPhong();

			// này là phòng trong db cho nên nó luôn trả về EMPT, tại vì trong db set cứng
			// nó là EMPT
			Phong p = dsP.getPhongTheoMa(ma);

			// call hoá đơn phòng theo mã
			// HoaDonPhong hd = dsDP.getHoaDonById(ma);

			// làm sao làm, phải lấy được cái tình trạng ở trong cái lấy phòng theo ngày chứ
			// k phải chọt xuống db
			// lấy cái tên tình trạng ở cột cuối đem đi so sánh.
			String tinhTrang = tableDSPhong.getValueAt(row, 4).toString();

			LoaiHoaDon lhd = new LoaiHoaDon("HDD");
			HoaDonPhong hdp = new HoaDonPhong(new Dao_PhatSinhMa().getMaHDCuoi(), p, nv, kh, lhd, ngayHT, localDate,
					localTime);

			if (checkDieuKienDatPhong(tinhTrang, kh)) {
				dsDP.themHoaDonDat(hdp);
				dsTP.setTTPhongTheoMa(ma, "BOOK");
				JOptionPane.showMessageDialog(this, "Đặt phòng thành công!");
				btnDatPhong.setEnabled(false);
			}
			return true;
		} else {
			JOptionPane.showMessageDialog(this, "Không đặt được phòng, vui lòng kiểm tra lại");
		}

		return false;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		getIndexRow();
		Object o = e.getSource();
		if (o == lbIconSearch) {
			try {
				KhachHang isKhachHang = ktraKHDAT();
				if (dsHD.getDSHDDTheoMaKH(isKhachHang.getMaKhachHang()).size() > 0) {
					new Frm_ThongTinKhachDat(isKhachHang.getMaKhachHang()).setVisible(true);
				}
			} catch (Exception e2) {
				System.out.println("Chưa kiểm tra khách hàng");
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
		Object o = e.getSource();
		if (o == btnTatCa) {
			btnTatCa.setBackground(new Color(90, 125, 144));
		}
		if (o == btnPhongVip) {
			btnPhongVip.setBackground(new Color(90, 125, 144));
		}
		if (o == btnPhongThuong) {
			btnPhongThuong.setBackground(new Color(90, 125, 144));
		}
		if (o == lbIconSearch) {
			lbIconSearch.setBorder(new LineBorder(new Color(0, 0, 0), 3));
		}
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		Object o = e.getSource();
		if (o == btnTatCa) {
			btnTatCa.setBackground(new Color(255, 255, 255));
		}
		if (o == btnPhongVip) {
			btnPhongVip.setBackground(new Color(255, 255, 255));
		}
		if (o == btnPhongThuong) {
			btnPhongThuong.setBackground(new Color(255, 255, 255));
		}
		if (o == lbIconSearch) {
			lbIconSearch.setBorder(new LineBorder(new Color(0, 0, 0), 0));
		}

	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		Object o = evt.getSource();
		if (o == ngayDatPhong) {
			ngayDat = ngayDatPhong.getDate();
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			dateString = dateFormat.format(ngayDat);
			upTable1(dsDP.getAllRoomByDate(dateString));
//			getIndexRow();
			ArrayList<Phong> list = dsDP.getAllRoomByDate(dateString);
			upTable1(list);
		}
	}

	/**
	 * Kiểm tra khách hàng mới nhập vào đã có trong database hay chưa.
	 */

	public void clearTableDSP() {
		while (tableDSPhong.getRowCount() > 0) {
			model1.removeRow(0);
		}
	}

	public void clearTableDSPD() {
		while (tableDSPhong1.getRowCount() > 0) {
			model.removeRow(0);
		}
	}

	public void clearTable() {
		clearTableDSP();
		clearTableDSPD();
	}

	/**
	 * lấy tình trạng phòng đang trống call lên danh sách hoá đơn, nếu có hoá đơn
	 * vào ngày hoom đó thì không cho đặt dsDP.getAllRoomByDate(dateString)
	 * 
	 * @param p
	 * @return
	 */

	public boolean checkDieuKienDatPhong(String p, KhachHang kh) {
		if (kh == null)
			return false;
		// lấy thời gian hiện tại
		LocalTime currentTime = LocalTime.now();
		// lấy ngày hiện tại
		LocalDate ngay = ngayDat.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		// chia ra 2 trường hợp, nếu ngày đặt là ngày hôm nay thì sao và ngày đặt là
		// ngày sau ngày hiện tại thì sao??
		// TH1: Ngày đặt là ngày hiện tại.
		if ((ngay.compareTo(ngayHT) == 0)) { // ngày đặt là ngày hiện tại
			if (((localTime.compareTo(currentTime) >= 0))) { // so sánh giờ đặt với giờ hiện tại
				if (p.equalsIgnoreCase("Phòng đã đặt") || p.equalsIgnoreCase("Phòng đang thuê")) { // check tình trạng
																									// phòng
					JOptionPane.showMessageDialog(this, "Hiện tại phòng này không thể đặt");
					return false;
				}
			} else {
				JOptionPane.showMessageDialog(this, "Giờ đặt phải sau giờ hiện tại!!");
				return false;
			}
		} else if ((ngay.compareTo(ngayHT) > 0)) {
			if (p.equalsIgnoreCase("Phòng đã đặt")) {// check tình trạng phòng
				JOptionPane.showMessageDialog(this, "Phòng hiện tại đã có người đặt, vui lòng chọn một phòng khác!!");
				return false;
			}

		} else {
			JOptionPane.showMessageDialog(this, "Ngày Đặt phải là ngày hôm nay hoặc sau ngày hiện tại!");
			return false;
		}
		return true;
	}

	/**
	 * Kiểm tra điều kiện thử để có thể nhận phòng được hay không, check giờ nhận.
	 * 
	 * @return true nếu nhận phòng thành công, hoá đơn đặt sẽ chuyển thành hoá đơn
	 *         thuê.
	 */
	public boolean nhanPhong() {
		KhachHang kh = ktraKHDAT();
		int row = tableDSPhong1.getSelectedRow();
		int gioDat = Integer.valueOf(tableDSPhong1.getValueAt(row, 5).toString().split(":")[0]);
		int phutDat = Integer.valueOf(tableDSPhong1.getValueAt(row, 5).toString().split(":")[1]);
		LocalDate ngay = LocalDate.parse(tableDSPhong1.getValueAt(row, 4).toString());
		String maHoaDon = tableDSPhong1.getValueAt(row, 0).toString();
		// lấy ngày hiện tại
		if (kiemTraDieuKienNhanPhong(ngay, gioDat, phutDat, kh)) {
			HoaDonPhong hd = dsDP.getHoaDonById(maHoaDon);
			Phong p = hd.getPhong();
			NhanVien nv = hd.getMaNhanVien();
//			KhachHang kh = hd.getMaKhachHang();
			LoaiHoaDon lhd = new LoaiHoaDon("HDT");

			if (dsDP.huyDatPhong(maHoaDon)) {
				LocalTime currentTime = LocalTime.now();
				HoaDonPhong newHD = new HoaDonPhong(maHoaDon, p, nv, kh, lhd, ngayHT, currentTime);
				if (!dsTP.themHDThue(newHD) && !dsTP.setTTPhongTheoMa(p.getMaPhong(), "RENT")) {
					JOptionPane.showMessageDialog(this, "Nhận phòng thành công!");
					btnNhanPhong.setEnabled(false);
					btnHuyDatPhong.setEnabled(false);
					return true;
				}

			}
			return true;
		}

		return false;
	}

	/**
	 * ngày đặt và ngày hiện tại phải bằng nhau. giờ nhận phải truóc giờ hiện tại 5p
	 * 
	 * @param gioDat  int
	 * @param phutDat int
	 * @return true nếu đủ điều kiện nhận phòng.
	 */
	public boolean kiemTraDieuKienNhanPhong(LocalDate ngay, int gioDat, int phutDat, KhachHang kh) {
		if (kh == null)
			return false;
		LocalTime currentTime = LocalTime.now();
		int gio = currentTime.getHour();
		int phut = currentTime.getMinute();
		ngayHT = ngayHienTai.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

		if (ngayHT.compareTo(ngay) == 0) {

			if (Math.abs(gio - gioDat) == 0) {
				if (Math.abs(phutDat - phut) <= 10)
					return true;
				else {
					JOptionPane.showMessageDialog(this, "Chỉ nhận phòng trong khoảng 10p trước giờ đặt phòng!!");
					return false;
				}
			} else if (Math.abs(gio - gioDat) == 1) {
				if (phutDat == 00 && phut >= 50) {
					return true;
				} else if ((phutDat == 15 || phutDat == 30 || phutDat == 45) && phutDat - phut <= 10
						&& phutDat >= phut) {
					return true;
				} else {
					JOptionPane.showMessageDialog(this, "Chỉ nhận phòng trong khoảng 10p trước giờ đặt phòng!!");
					return false;
				}
			} else {
				JOptionPane.showMessageDialog(this,
						"Không thể nhận phòng,\nVui lòng nhận phòng trước giờ đặt tối đa 10p!!");
				return false;
			}
		} else {
			JOptionPane.showMessageDialog(this, "Không thể nhận phòng,\nVui lòng đến đúng ngày đặt phòng để nhận!!");
			return false;
		}
	}

	/*
	 * 
	 * kiểm tra nhập sdt có đúng không
	 */

	public boolean kiemTraDuLieuNhapVao(String dt) {
		if (dt.equals("")) {
			JOptionPane.showMessageDialog(this, "Số điện thoại không được để trống!");
			txtSDT.requestFocus();
			return false;
		}
		if (!dt.matches("^(0[0-9]{9})$")) {
			JOptionPane.showMessageDialog(this, "Số điện thoại sai định dạng, vui lòng nhập đúng!!");
			txtSDT.requestFocus();
			return false;
		}
		return true;
	}

	/**
	 * kiểm tra điều kiện huỷ có cho phép huỷ hay không
	 * 
	 * @return true nếu huỷ đặt phòng thành công và false nếu k huỷ được.
	 * 
	 */

	public boolean huyDatPhong() {
		KhachHang kh = ktraKHDAT();
		if (kh != null) {
			int row = tableDSPhong1.getSelectedRow();
			String maHoaDon = tableDSPhong1.getValueAt(row, 0).toString();
			if (maHoaDon != null) {
				int isDelete = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn huỷ", "Thông Báo",
						JOptionPane.YES_NO_OPTION);
				if (isDelete == JOptionPane.YES_OPTION) {
					dsDP.huyDatPhong(maHoaDon);
					JOptionPane.showMessageDialog(this, "Huỷ đặt phòng thành công!!");
					btnNhanPhong.setEnabled(false);
					btnHuyDatPhong.setEnabled(false);
					return true;
				} else {
					return false;
				}
			} else {
				JOptionPane.showMessageDialog(this, "Huỷ đặt phòng không thành công, vui lòng kiểm tra lại!");
			}
		}
		return false;

	}

	/**
	 * Lọc phòng theo tình trạng
	 */

	// kiểm tra điều kiện huỷ đặt phòng.
	/*
	 * Nếu có phòng được đặt nhưng khong nhận sẽ tự động huỷ kiểm tra giờ hiện tại
	 * và giờ đặt phòng, nếu quá hạn thì sẽ huỷ phòng
	 */
	public void huyDatPhongQuaHan() {
		for (HoaDonPhong hd : dsDP.getAllRoomStatusByDate()) {
			String maHoaDon = hd.getMaHoaDon().trim();
			String ngay = hd.getNgayDat().toString();
			SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
			Date ngayDat = null;
			try {
				ngayDat = fmt.parse(ngay);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			int gioDat = Integer.parseInt(hd.getGioDat().toString().split(":")[0]);
			int phutDat = Integer.parseInt(hd.getGioDat().toString().split(":")[1]);

			LocalTime currentTime = LocalTime.now();
			int gio = currentTime.getHour();

			int phut = currentTime.getMinute();

			// quá giờ đặt 30p thì sẽ tự động huỷ đặt phòng
			if ((ngayHienTai.compareTo(ngayDat) > 0)
					|| (ngayHienTai.compareTo(ngayDat) == 0 && localTime.now().isAfter(hd.getGioDat().plusMinutes(29)))) {
				dsDP.huyDatPhong(maHoaDon);
				dsTP.setTTPhongTheoMa(hd.getPhong().getMaPhong(), "EMPT");
				DanhSachDatPhong dsdp = new DanhSachDatPhong();
				upTable2(dsdp.getAllRoomStatusByDate());
			}
		}
	}

	// hot key Ctrl1
	public void addHotKey1() {

		btnNhanPhong.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(keyStrokeCTRL1, "clickButton");
		btnNhanPhong.getActionMap().put("clickButton", new AbstractAction() {
//								        @Override
			public void actionPerformed(ActionEvent e) {
				btnNhanPhong.doClick();
			}
		});
	}

	// hot key Crtl2
	public void addHotKey2() {
		btnDatPhong.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(keyStrokeCTRL2, "clickButton");
		btnDatPhong.getActionMap().put("clickButton", new AbstractAction() {
//								        @Override
			public void actionPerformed(ActionEvent e) {
				btnDatPhong.doClick();
			}
		});
	}

	// hot key Crtl3
	public void addHotKey3() {
		btnHuyDatPhong.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(keyStrokeCTRL3, "clickButton");
		btnHuyDatPhong.getActionMap().put("clickButton", new AbstractAction() {
//								        @Override
			public void actionPerformed(ActionEvent e) {
				btnHuyDatPhong.doClick();
			}
		});
	}

	// hot key Crtl4
	public void addHotKey4() {
		btnLamMoi.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(keyStrokeCTRL4, "clickButton");
		btnLamMoi.getActionMap().put("clickButton", new AbstractAction() {
//								        @Override
			public void actionPerformed(ActionEvent e) {
				btnLamMoi.doClick();
			}
		});
	}

}
