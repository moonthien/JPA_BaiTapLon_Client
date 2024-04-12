package app;

import javax.imageio.ImageIO;
import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import java.awt.AlphaComposite;
import java.awt.BorderLayout;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.Color;
import java.awt.Component;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

//import com.jgoodies.forms.layout.FormLayout;
//import com.jgoodies.forms.layout.ColumnSpec;
//import com.jgoodies.forms.layout.RowSpec;
//import com.toedter.calendar.JDateChooser;

import entitys.ChucVu;
import entitys.NhanVien;

import javax.swing.JMenu;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Timer;
import java.awt.event.ActionEvent;
import javax.swing.border.EtchedBorder;
import java.awt.SystemColor;
import java.awt.Rectangle;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.ScrollPane;
import javax.swing.JScrollBar;
import java.awt.Panel;

public class Frm_Chinh extends JFrame implements MouseListener, ActionListener {
	private JTextField txtMaKH, txtTenKH, txtDiaChi, txtThanhPho, txtQuocGia, txtSDT, txtGioiTinh, txtTuoi, txtCMND,
			txtMess;
	private JLabel lbHeader, lbNgayHienTai, lbIconGio, lbIconLich;
	private DefaultTableModel model;
	private JButton btnTroVe, btnLuu, btnSua, btnThem, btnXoa, btnDangxuat;
	private JMenu mnTrangChu, mnQLP, mnQLTP, mnQLKH, mnQLDP, mnQLDV, mnTK, mnQLNV, mnTroGiup;
	private JMenuItem mnTKNV, mnTKKH, mnTKDV, mnTKHD;
	private JTextField txtNguynVnA;
	private JTable table;
	private GridLayout gbl_pnNorth;
	JMenuBar menuBar;
	JPanel PnChinh;
	JPanel pnCenter, pnHeader;
	private JLabel lbGioHienTai;
	private JLabel lbHoTenNV;
	private JLabel lbChucVu;
	private JLabel lbIconChucVu;
	public NhanVien nv;
	KeyStroke keyStrokeF1, keyStrokeF2, keyStrokeF3, keyStrokeF4, keyStrokeF5, keyStrokeF6, keyStrokeF7, keyStrokeF71,
			keyStrokeF72, keyStrokeF73, keyStrokeF74, keyStrokeF8, keyStrokeF9;

	public Frm_Chinh() throws HeadlessException {
		super();
	}

	public Frm_Chinh(NhanVien nv) {
		this.nv = nv;
		setTitle("TRANG CHỦ");
		setSize(1400, 800);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(true);
		setLocationRelativeTo(null);
		gui();
	}

	public void gui() {
		getContentPane().setLayout(null);
		PnChinh = new JPanel();
		PnChinh.setBounds(0, 0, 1400, 800);
		PnChinh.setEnabled(false);

		// Thêm JLabel với hình nền vào JFrame
		getContentPane().add(PnChinh);
		PnChinh.setLayout(null);

		// ADD PN HEADER
		pnHeader = new JPanel();
		pnHeader.setBounds(0, 0, 1400, 130);
		PnChinh.add(pnHeader);
		pnHeader.setLayout(null);

		btnDangxuat = new FixButton("Đăng xuất");
		btnDangxuat.setBounds(1250, 20, 110, 35);
		pnHeader.add(btnDangxuat);

		btnDangxuat.setIcon(new ImageIcon(Frm_Chinh.class.getResource("/imgs/icon_dangxuat.png")));
		btnDangxuat.setBorder(new LineBorder(new Color(255, 0, 0), 3, true));
		btnDangxuat.setHorizontalAlignment(SwingConstants.RIGHT);

		btnDangxuat.setBackground(new Color(224, 255, 255));
		btnDangxuat.setFont(new Font("Verdana", Font.BOLD, 14));

		btnDangxuat.setForeground(new Color(0, 0, 0));

		lbNgayHienTai = new JLabel("");
		lbNgayHienTai.setForeground(Color.WHITE);
		lbNgayHienTai.setBackground(new Color(240, 240, 240));
		lbNgayHienTai.setFont(new Font("Tahoma", Font.BOLD, 20));
		lbNgayHienTai.setBounds(210, 45, 150, 25);
		pnHeader.add(lbNgayHienTai);

		lbGioHienTai = new JLabel("16:55:00");
		lbGioHienTai.setForeground(Color.WHITE);
		lbGioHienTai.setFont(new Font("Tahoma", Font.BOLD, 25));
		lbGioHienTai.setBounds(210, 5, 150, 25);
		pnHeader.add(lbGioHienTai);

		lbHoTenNV = new JLabel(nv.getHoTenNhanVien());
		lbHoTenNV.setForeground(Color.WHITE);
		lbHoTenNV.setFont(new Font("Tahoma", Font.BOLD, 20));
		lbHoTenNV.setBounds(980, 10, 200, 25);
		pnHeader.add(lbHoTenNV);

//		lbChucVu = new JLabel(nv.getchucVu().getTenChucVu());
		lbChucVu = new JLabel();
		lbChucVu.setForeground(Color.WHITE);
		lbChucVu.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lbChucVu.setBounds(980, 35, 80, 25);
		pnHeader.add(lbChucVu);

		lbIconChucVu = new JLabel("");
		lbIconChucVu.setIcon(new ImageIcon(Frm_Chinh.class.getResource("/imgs/icon_chucvu.png")));
		lbIconChucVu.setForeground(Color.WHITE);
		lbIconChucVu.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lbIconChucVu.setBounds(940, 18, 50, 50);
		pnHeader.add(lbIconChucVu);

		// button add sự kiện

		// menu add sự kiện

		// Set màu khi chọn

		menuBar = new JMenuBar();
		menuBar.setBounds(0, 80, 1400, 40);
		pnHeader.add(menuBar);

		mnTrangChu = new JMenu("Trang chủ (F1)");
		mnTrangChu.setFont(new Font("Tahoma", Font.BOLD, 12));
		mnTrangChu.setIcon(new ImageIcon(Frm_Chinh.class.getResource("/imgs/icon_trangchu.png")));
		menuBar.add(mnTrangChu);

		mnQLP = new JMenu("Quản lý phòng (F2)");
		mnQLP.setFont(new Font("Tahoma", Font.BOLD, 11));
		mnQLP.setIcon(new ImageIcon(Frm_Chinh.class.getResource("/imgs/icon_quanlyphong.png")));
		menuBar.add(mnQLP);

		mnQLTP = new JMenu("Quản lý thuê phòng (F3)");
		mnQLTP.setFont(new Font("Tahoma", Font.BOLD, 11));
		mnQLTP.setIcon(new ImageIcon(Frm_Chinh.class.getResource("/imgs/buy-home.png")));
		menuBar.add(mnQLTP);

		mnQLDP = new JMenu("Quản lý đặt phòng (F4)");
		mnQLDP.setFont(new Font("Tahoma", Font.BOLD, 11));
		mnQLDP.setIcon(new ImageIcon(Frm_Chinh.class.getResource("/imgs/icon_quanlydatphong.png")));
		menuBar.add(mnQLDP);

		mnQLKH = new JMenu("Quản lý khách hàng (F5)");
		mnQLKH.setFont(new Font("Tahoma", Font.BOLD, 11));
		mnQLKH.setIcon(new ImageIcon(Frm_Chinh.class.getResource("/imgs/icon_quanlykhachhang.png")));
		menuBar.add(mnQLKH);

		mnQLDV = new JMenu("Quản lý dịch vụ (F6)");
		mnQLDV.setFont(new Font("Tahoma", Font.BOLD, 11));
		mnQLDV.setIcon(new ImageIcon(Frm_Chinh.class.getResource("/imgs/phucvu.png")));
		menuBar.add(mnQLDV);

		mnTK = new JMenu("Thống kê (F7)");
		mnTK.setFont(new Font("Tahoma", Font.BOLD, 11));
		mnTK.setIcon(new ImageIcon(Frm_Chinh.class.getResource("/imgs/thongke.png")));
		menuBar.add(mnTK);

		mnTKNV = new JMenuItem("Thống kê phòng");
		mnTKNV.setFont(new Font("Tahoma", Font.BOLD, 11));
		mnTK.add(mnTKNV);

		mnTKKH = new JMenuItem("Thống kê khách hàng");
		mnTKKH.setFont(new Font("Tahoma", Font.BOLD, 11));
		mnTK.add(mnTKKH);

		mnTKDV = new JMenuItem("Thống kê dịch vụ");
		mnTKDV.setFont(new Font("Tahoma", Font.BOLD, 11));
		mnTK.add(mnTKDV);

		mnTKHD = new JMenuItem("Thống kê hoá đơn");
		mnTKHD.setFont(new Font("Tahoma", Font.BOLD, 11));
		mnTK.add(mnTKHD);

		mnQLNV = new JMenu("Quản lý nhân viên (F8)");
		mnQLNV.setFont(new Font("Tahoma", Font.BOLD, 11));
		mnQLNV.setIcon(new ImageIcon(Frm_Chinh.class.getResource("/imgs/nhanvien.png")));
		menuBar.add(mnQLNV);

		mnTroGiup = new JMenu("Trợ giúp (F9)");
		mnTroGiup.setIcon(new ImageIcon(Frm_Chinh.class.getResource("/imgs/trogiup.png")));
		mnTroGiup.setFont(new Font("Tahoma", Font.BOLD, 11));
		menuBar.add(mnTroGiup);

		pnCenter = new JPanel();
		pnCenter.setBounds(0, 120, 1400, 670);
		PnChinh.add(pnCenter);

		lbIconGio = new JLabel("");
		lbIconGio.setIcon(new ImageIcon(Frm_Chinh.class.getResource("/imgs/icon_dongho.png")));
		lbIconGio.setForeground(Color.WHITE);
		lbIconGio.setFont(new Font("Tahoma", Font.BOLD, 25));
		lbIconGio.setBounds(170, 2, 30, 30);
		pnHeader.add(lbIconGio);

		lbIconLich = new JLabel("");
		lbIconLich.setIcon(new ImageIcon(Frm_Chinh.class.getResource("/imgs/calendar.png")));
		lbIconLich.setForeground(Color.WHITE);
		lbIconLich.setFont(new Font("Tahoma", Font.BOLD, 20));
		lbIconLich.setBackground(SystemColor.menu);
		lbIconLich.setBounds(170, 40, 30, 30);
		pnHeader.add(lbIconLich);

		// add background header
		lbHeader = new JLabel("");
		lbHeader.setBounds(0, 0, 1400, 130);
		lbHeader.setIcon(new ImageIcon(Frm_Chinh.class.getResource("/imgs/bg_header.png")));
		pnHeader.add(lbHeader);
		// add background center
		JLabel lbBG = new JLabel("");
		lbBG.setBounds(0, 10, 1400, 800);
		lbBG.setIcon(new ImageIcon(Frm_Chinh.class.getResource("/imgs/bg_trangchu.png")));
		pnCenter.add(lbBG);

		// ADD sự kiện
		btnDangxuat.addActionListener(this);
		btnDangxuat.addMouseListener(this);
		mnTrangChu.addMouseListener(this);
		mnQLDV.addMouseListener(this);
		mnQLDP.addMouseListener(this);
		mnQLKH.addMouseListener(this);
		mnQLNV.addMouseListener(this);
		mnQLP.addMouseListener(this);
		mnQLTP.addMouseListener(this);
		mnTroGiup.addMouseListener(this);
		lbChucVu.addMouseListener(this);

		mnTKDV.addActionListener(this);
		mnTKHD.addActionListener(this);
		mnTKKH.addActionListener(this);
		mnTKNV.addActionListener(this);

//		pnHeader.addMouseListener(this);
		lbHoTenNV.addMouseListener(this);

		// add và địng nghĩa các hot key cho ứng dụng

		// add và địng nghĩa các hot key cho ứng dụng

		keyStrokeF1 = KeyStroke.getKeyStroke("F1");
		keyStrokeF2 = KeyStroke.getKeyStroke("F2");
		keyStrokeF3 = KeyStroke.getKeyStroke("F3");
		keyStrokeF4 = KeyStroke.getKeyStroke("F4");
		keyStrokeF5 = KeyStroke.getKeyStroke("F5");
		keyStrokeF6 = KeyStroke.getKeyStroke("F6");
		keyStrokeF7 = KeyStroke.getKeyStroke("F7");
		keyStrokeF71 = KeyStroke.getKeyStroke("1");
		keyStrokeF72 = KeyStroke.getKeyStroke("2");
		keyStrokeF73 = KeyStroke.getKeyStroke("3");
		keyStrokeF74 = KeyStroke.getKeyStroke("4");
		keyStrokeF8 = KeyStroke.getKeyStroke("F8");
		keyStrokeF9 = KeyStroke.getKeyStroke("F9");

		// Ngày, giờ hiện tại
		ngayHienTai();
		gioHienTai();
		loadFrm_TrangChu();

		addHotKey();
	}

	public void loadFrmQuanLyKhachHang() throws SQLException {
		pnCenter.removeAll();
		Frm_KhachHang frKH = new Frm_KhachHang();
		pnCenter.add(frKH.getFrmQuanLyKhachHang());
	}

	public void loadFrmQuanLyNhanVien() {
		String maCV = "QL";
//		ChucVu cv = new ChucVu(maCV);
//		if (nv.getchucVu().getMaChucVu().equalsIgnoreCase(maCV)) {
			pnCenter.removeAll();
			Frm_NhanVien frNV = new Frm_NhanVien();
			pnCenter.add(frNV.getFrmQuanLyNhanVien());
//		} else {
//			JOptionPane.showMessageDialog(this, "!!! Bạn không có quyền truy cập chức năng này");
//		}
	}

	public void loadFrmQuanLyThuePhong() {
		pnCenter.removeAll();
		Frm_ThuePhong frTP = new Frm_ThuePhong(nv);
		pnCenter.add(frTP.getFrmQuanLyThuePhong());
	}

	public void loadFrmQuanLyDichVu() throws SQLException {
		pnCenter.removeAll();
		Frm_QuanLyDichVu frQLDV = new Frm_QuanLyDichVu();
		pnCenter.add(frQLDV.getFrmQuanLyDichVu());
	}

	public void loadFrmQuanLyPhong() {
		String maCV = "QL";
//		ChucVu cv = new ChucVu(maCV);
//		if (nv.getchucVu().getMaChucVu().equalsIgnoreCase(maCV)) {
			pnCenter.removeAll();
			Frm_QuanLyPhong frQLP = new Frm_QuanLyPhong(nv);
			pnCenter.add(frQLP.getFrmQuanLyPhong());
//		} else {
			JOptionPane.showMessageDialog(this, "!!! Bạn không có quyền truy cập chức năng này");
//		}
	}

	// load Frm Linh Kien
	public void loadFrmQuanLyDatPhong() {
		pnCenter.removeAll();
		Frm_QuanLyDatPhong frmqldp = new Frm_QuanLyDatPhong(nv);
		pnCenter.add(frmqldp.getFrmQuanLyDatPhong());

	}

	public void loadFrm_ThongKeDichVu() {
		String maCV = "QL";
//		ChucVu cv = new ChucVu(maCV);
//		if (nv.getchucVu().getMaChucVu().equalsIgnoreCase(maCV)) {
			pnCenter.removeAll();
			Frm_ThongKeDichVu frTKDV = new Frm_ThongKeDichVu();
			pnCenter.add(frTKDV.getFrmThongKeDichVu());
//		} else {
//			JOptionPane.showMessageDialog(this, "!!! Bạn không có quyền truy cập chức năng này");
//		}
	}

	public void loadFrm_ThongKeHoaDon() {
			pnCenter.removeAll();
			Frm_ThongKeHoaDon frTKHD = new Frm_ThongKeHoaDon();
			pnCenter.add(frTKHD.getFrmThongKeHoaDon());
	}

	public void loadFrm_ThongKeKhachHang() {
		pnCenter.removeAll();
		Frm_ThongKeKhachHang frTKKH = new Frm_ThongKeKhachHang();
		pnCenter.add(frTKKH.getFrmThongKeKhachHang());

	}

	public void loadFrm_ThongKePhong() {
		pnCenter.removeAll();
		Frm_ThongKePhong frTKNV = new Frm_ThongKePhong();
		pnCenter.add(frTKNV.getFrmPhong());

	}

	public void loadFrm_TrangChu() {
		pnCenter.removeAll();
		Frm_TrangChu frTC = new Frm_TrangChu();
		pnCenter.add(frTC.getFrmTrangChu());

	}

	public void loadFrm_Profile() {
		Frm_Profile frmProfile = new Frm_Profile(nv);
		frmProfile.setVisible(true);
		this.setVisible(false);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		Object o = e.getSource();
		if (o == lbHoTenNV) {
			loadFrm_Profile();
		}
		if (o == mnQLDP)
			loadFrmQuanLyDatPhong();
		if (o == mnQLKH)
			try {
				loadFrmQuanLyKhachHang();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		if (o == mnQLNV)
			loadFrmQuanLyNhanVien();
		if (o == mnQLTP)
			loadFrmQuanLyThuePhong();
		if (o == mnQLDV)
			try {
				loadFrmQuanLyDichVu();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		if (o == mnQLP)
			loadFrmQuanLyPhong();
		if (o == mnTrangChu)
			loadFrm_TrangChu();

		if (o == lbChucVu) {
			loadFrm_Profile();
		}

		if (o == mnTrangChu)
			loadFrm_TrangChu();
		if (o == mnTroGiup) {
			String webURL = "https://qhuy1504.github.io/KaraokeETC_HELP/";
			try {
				Desktop.getDesktop().browse(new URL(webURL).toURI());
			} catch (MalformedURLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (URISyntaxException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
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
		Object o = e.getSource();
		if (o == lbChucVu) {
			lbChucVu.setForeground(new Color(227, 204, 0));
			lbChucVu.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(227, 204, 0)));
		} else if (o == btnDangxuat) {
			// TODO Auto-generated method stub
			btnDangxuat.setBackground(Color.red);
		}

	}

	@Override
	public void mouseExited(MouseEvent e) {
		Object o = e.getSource();
		if (o == lbChucVu) {
			lbChucVu.setForeground(Color.WHITE);
			lbChucVu.setBorder(new MatteBorder(0, 0, 0, 0, (Color) new Color(227, 204, 0)));
		} else if (o == btnDangxuat) {
			// TODO Auto-generated method stub
			btnDangxuat.setBackground(Color.white);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object o = e.getSource();
		if (o == mnTKDV)
			loadFrm_ThongKeDichVu();
		else if (o == mnTKHD)
			loadFrm_ThongKeHoaDon();
		else if (o == mnTKKH)
			loadFrm_ThongKeKhachHang();
		else if (o == mnTKNV)

			loadFrm_ThongKePhong();
		else if (o == btnDangxuat) {
			dangXuat();
		}

	}

	// Lấy ngày hiện tại
	public void ngayHienTai() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDateTime now = LocalDateTime.now();
		lbNgayHienTai.setText(dtf.format(now));
	}

	// Lấy giờ hiện tại
	public void gioHienTai() {

		ActionListener act = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Date date = new Date();
				DateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
				String time = timeFormat.format(date);
				lbGioHienTai.setText(time);
			}
		};
		javax.swing.Timer timer = new javax.swing.Timer(1000, act);
		timer.setInitialDelay(0);
		timer.start();
	}

	public void dangXuat() {
		if (JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn đăng xuất không?", "Cảnh báo",
				JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
			Frm_DangNhap frame = new Frm_DangNhap();
			frame.setVisible(true);
			this.setVisible(false);
		}
	}

	public void addInfoStaff(NhanVien nv) {
		pnHeader = new JPanel();
		lbHoTenNV = new JLabel(nv.getHoTenNhanVien());
		lbHoTenNV.setForeground(Color.WHITE);
		lbHoTenNV.setFont(new Font("Tahoma", Font.BOLD, 20));
		lbHoTenNV.setBounds(980, 10, 200, 25);
		pnHeader.add(lbHoTenNV);
	}

	public void addHotKey() {
		// hot key f1
		mnTrangChu.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(keyStrokeF1, "clickButton");
		mnTrangChu.getActionMap().put("clickButton", new AbstractAction() {
//	        @Override
			public void actionPerformed(ActionEvent e) {
				mnTrangChu.doClick();
				loadFrm_TrangChu();
			}
		});
		// hot key f2
		mnQLP.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(keyStrokeF2, "clickButton");
		mnQLP.getActionMap().put("clickButton", new AbstractAction() {
//			        @Override
			public void actionPerformed(ActionEvent e) {
				mnQLP.doClick();
				loadFrmQuanLyPhong();
			}
		});
		// hot key f3
		mnQLTP.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(keyStrokeF3, "clickButton");
		mnQLTP.getActionMap().put("clickButton", new AbstractAction() {
//			        @Override
			public void actionPerformed(ActionEvent e) {
				mnQLTP.doClick();
				loadFrmQuanLyThuePhong();
			}
		});
		// hot key f4
		mnQLDP.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(keyStrokeF4, "clickButton");
		mnQLDP.getActionMap().put("clickButton", new AbstractAction() {
//			        @Override
			public void actionPerformed(ActionEvent e) {
				mnQLDP.doClick();
				loadFrmQuanLyDatPhong();
			}
		});
		// hot key f5
		mnQLKH.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(keyStrokeF5, "clickButton");
		mnQLKH.getActionMap().put("clickButton", new AbstractAction() {
//			        @Override
			public void actionPerformed(ActionEvent e) {
				mnQLKH.doClick();
				try {
					loadFrmQuanLyKhachHang();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		// hot key f6
		mnQLDV.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(keyStrokeF6, "clickButton");
		mnQLDV.getActionMap().put("clickButton", new AbstractAction() {
//			        @Override
			public void actionPerformed(ActionEvent e) {
				mnQLDV.doClick();
				try {
					loadFrmQuanLyDichVu();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		// hot key f7
		mnTK.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(keyStrokeF7, "clickButton");
		mnTK.getActionMap().put("clickButton", new AbstractAction() {
//			        @Override
			public void actionPerformed(ActionEvent e) {
				mnTK.doClick();
				callTKNV();
				callTKKH();
				callTKDV();
				callTKHD();
			}
		});
		// hot key f8
		mnQLNV.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(keyStrokeF8, "clickButton");
		mnQLNV.getActionMap().put("clickButton", new AbstractAction() {
//			        @Override
			public void actionPerformed(ActionEvent e) {
				mnQLNV.doClick();
				loadFrmQuanLyNhanVien();
			}
		});
		// hot key f9
		mnTroGiup.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(keyStrokeF9, "clickButton");
		mnTroGiup.getActionMap().put("clickButton", new AbstractAction() {
//					        @Override
			public void actionPerformed(ActionEvent e) {
				mnTroGiup.doClick();
				String webURL = "https://qhuy1504.github.io/KaraokeETC_HELP/";
				try {
					Desktop.getDesktop().browse(new URL(webURL).toURI());
				} catch (MalformedURLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (URISyntaxException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

	}

	public void callTKNV() {
		// hot key f71
		mnTKNV.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(keyStrokeF71, "clickButton");
		mnTKNV.getActionMap().put("clickButton", new AbstractAction() {
//	        @Override
			public void actionPerformed(ActionEvent e) {
				mnTKNV.doClick();
				loadFrm_ThongKePhong();
			}
		});
	}

	public void callTKKH() {
		// hot key f71
		mnTKKH.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(keyStrokeF72, "clickButton");
		mnTKKH.getActionMap().put("clickButton", new AbstractAction() {
//	        @Override
			public void actionPerformed(ActionEvent e) {
				mnTKKH.doClick();
				loadFrm_ThongKeKhachHang();
			}
		});
	}

	public void callTKDV() {
		// hot key f71
		mnTKDV.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(keyStrokeF73, "clickButton");
		mnTKDV.getActionMap().put("clickButton", new AbstractAction() {
//	        @Override
			public void actionPerformed(ActionEvent e) {
				mnTKDV.doClick();
				loadFrm_ThongKeDichVu();
			}
		});
	}

	public void callTKHD() {
		// hot key f71
		mnTKHD.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(keyStrokeF74, "clickButton");
		mnTKHD.getActionMap().put("clickButton", new AbstractAction() {
//	        @Override
			public void actionPerformed(ActionEvent e) {
				mnTKHD.doClick();
				loadFrm_ThongKeHoaDon();
			}
		});
	}

}