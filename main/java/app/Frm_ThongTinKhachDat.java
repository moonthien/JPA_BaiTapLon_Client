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

public class Frm_ThongTinKhachDat extends JFrame implements ActionListener, MouseListener {
	JPanel pnDSDichVu;
	Panel pnTTKHD;
	private JTable tableDSPhong;
	private DefaultTableModel model;
	private JTextField txtKhachHang, txtSDT;
	private JPanel pnKH;
	private JLabel lbSDT, lbTenKH, lbDSDichVu, lbBGQLDV, lbTTKH;
	private JComboBox comboTTP, comboLP, comboGP;
	FixButton btnQL, btnNhanPhong, btnHuyDatPhong;
	private DecimalFormat df;
	private DateTimeFormatter dt;
	String ma;
	HoaDonPhong hd;
	DanhSachPhong p;
	DanhSachHoaDon dsHD;
	DanhSachThuePhong tp;
	DanhSachKhachHang dsKH;
	DanhSachDatPhong dsDP = new DanhSachDatPhong();
	DanhSachThuePhong dsTP = new DanhSachThuePhong();
	private Date ngayHienTai;
	LocalDate ngayHT;

	public Panel getFrm_ThongTinKhachDat() {
		return this.pnTTKHD;
	}

	public Frm_ThongTinKhachDat(String ma) {
		setTitle("THÔNG TIN KHÁCH HÀNG ĐẶT Phòng");
		setSize(1000, 600);
		setResizable(true);
		setLocationRelativeTo(null);
		this.ma = ma;
		gui();
	}

	public void gui() {
		getContentPane().setLayout(null);

		pnTTKHD = new Panel();
		pnTTKHD.setBounds(0, 0, 1000, 558);
		getContentPane().add(pnTTKHD);
		pnTTKHD.setLayout(null);

		btnHuyDatPhong = new FixButton("Hủy đặt phòng");
		btnHuyDatPhong.setIcon(new ImageIcon(Frm_ThongTinKhachDat.class.getResource("/imgs/btn_huydv.png")));
		btnHuyDatPhong.setSelectedIcon(new ImageIcon(Frm_ThongTinKhachDat.class.getResource("/imgs/btn_quaylai.png")));
		btnHuyDatPhong.setText("Huỷ Phòng");
		btnHuyDatPhong.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnHuyDatPhong.setBounds(417, 484, 166, 51);
		pnTTKHD.add(btnHuyDatPhong);

		lbTTKH = new JLabel("Thông tin khách hàng");
		lbTTKH.setForeground(Color.WHITE);
		lbTTKH.setFont(new Font("Tahoma", Font.BOLD, 15));
		lbTTKH.setBounds(34, 37, 200, 25);
		pnTTKHD.add(lbTTKH);

		pnDSDichVu = new JPanel();
		pnDSDichVu.setBackground(Color.WHITE);
		pnDSDichVu.setBounds(34, 206, 926, 250);
		pnTTKHD.add(pnDSDichVu);
		pnDSDichVu.setLayout(null);

		lbDSDichVu = new JLabel("Danh sách hóa đơn");
		lbDSDichVu.setFont(new Font("Tahoma", Font.BOLD, 15));
		lbDSDichVu.setBounds(10, 0, 150, 25);
		pnDSDichVu.add(lbDSDichVu);

		String col[] = { "Mã hóa đơn", "Mã phòng", "Tên khách hàng", "SĐT", "Ngày đặt", "Giờ đặt" };
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
		pnTTKHD.add(pnKH);

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

		btnQL = new FixButton("Quay Lai");
		btnQL.setBounds(123, 484, 171, 51);
		pnTTKHD.add(btnQL);
		btnQL.setIcon(new ImageIcon(Frm_ThongTinKhachDat.class.getResource("/imgs/btn_quaylai.png")));
		btnQL.setText("Quay Lại");
		btnQL.setFont(new Font("Tahoma", Font.BOLD, 15));

		btnNhanPhong = new FixButton("Nhận Phòng");
		btnNhanPhong.setBounds(706, 484, 171, 51);
		pnTTKHD.add(btnNhanPhong);
		btnNhanPhong.setIcon(new ImageIcon(Frm_ThanhToan.class.getResource("/imgs/btn_xacnhan.png")));
		btnNhanPhong.setText("Nhận Phòng");
		btnNhanPhong.setFont(new Font("Tahoma", Font.BOLD, 15));

		// add background ở cuối
		lbBGQLDV = new JLabel("");
		lbBGQLDV.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lbBGQLDV.setIcon(new ImageIcon(Frm_QuanLyDatPhong.class.getResource("/imgs/bg_trong.png")));
		lbBGQLDV.setBounds(10, 10, 1000, 557);
		pnTTKHD.add(lbBGQLDV);

		LocalDate localDateTime = LocalDate.now();
		int ngay = localDateTime.getDayOfMonth();
		int thang = localDateTime.getMonthValue();
		int nam = localDateTime.getYear();
		ngayHienTai = new Date(nam - 1900, thang - 1, ngay);
		txtKhachHang.setEditable(false);
		btnQL.addActionListener(this);
		btnNhanPhong.addActionListener(this);
		btnHuyDatPhong.addActionListener(this);
		tableDSPhong.addMouseListener(this);
		dt = DateTimeFormatter.ofPattern("HH:mm");
		p = new DanhSachPhong();
		tp = new DanhSachThuePhong();
		dsKH = new DanhSachKhachHang();
		dsHD = new DanhSachHoaDon();
		upTable();
		getIndexRow();
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		Object o = e.getSource();
		if (o == btnQL) {
			this.dispose();

		} else if (o == btnNhanPhong) {
			try {

				boolean isNhanPhong = nhanPhong();
				upTable();
				
			} catch (Exception e2) {
				throw new Error("Chưa chọn phiếu đặt phòng!");
			}
		} else {
			try {

				boolean huyDatPhong = huyDatPhong();
			} catch (Exception e2) {
				throw new Error("Chưa chọn phiếu đặt phòng!");
			}
			upTable();
		}
	}

	// chốt cái bảng này, cái bảng này oke, sửa lại cho giống cái bên đặt phòng nữa
	// là rồi

	public void upTable() {
		KhachHang kh = dsKH.getKHTheoMa(ma);
		String makh = kh.getMaKhachHang();
		txtKhachHang.setText(kh.getHoTenKhachHang());
		txtSDT.setText(kh.getSoDienThoai());
		ArrayList<HoaDonPhong> list = dsHD.getDSHDDTheoMaKH(makh);
		model.setRowCount(0);
		for (HoaDonPhong hd : list) {
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

	/**
	 * Kiểm tra điều kiện thử để có thể nhận phòng được hay không, check giờ nhận.
	 * 
	 * @return true nếu nhận phòng thành công, hoá đơn đặt sẽ chuyển thành hoá đơn
	 *         thuê.
	 */
	public boolean nhanPhong() {
		DanhSachKhachHang dsKH = new DanhSachKhachHang();
		KhachHang kh = dsKH.getKHTheoMa(ma);
		int row = tableDSPhong.getSelectedRow();
		int gioDat = Integer.valueOf(tableDSPhong.getValueAt(row, 5).toString().split(":")[0]);
		int phutDat = Integer.valueOf(tableDSPhong.getValueAt(row, 5).toString().split(":")[1]);
		LocalDate ngay = LocalDate.parse(tableDSPhong.getValueAt(row, 4).toString());
		String maHoaDon = tableDSPhong.getValueAt(row, 0).toString();
		// lấy ngày hiện tại
		if (kiemTraDieuKienNhanPhong(ngay, gioDat, phutDat, kh)) {
			HoaDonPhong hd = dsDP.getHoaDonById(maHoaDon);
			Phong p = hd.getPhong();
			NhanVien nv = hd.getMaNhanVien();
			LoaiHoaDon lhd = new LoaiHoaDon("HDT");

			if (dsDP.huyDatPhong(maHoaDon)) {
				LocalTime currentTime = LocalTime.now();
				HoaDonPhong newHD = new HoaDonPhong(maHoaDon, p, nv, kh, lhd, ngayHT, currentTime);
				if (!dsTP.themHDThue(newHD) && !dsTP.setTTPhongTheoMa(p.getMaPhong(), "RENT")) {
					JOptionPane.showMessageDialog(this, "Nhận phòng thành công!");
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

	/**
	 * kiểm tra điều kiện huỷ có cho phép huỷ hay không
	 * 
	 * @return true nếu huỷ đặt phòng thành công và false nếu k huỷ được.
	 * 
	 */

	public boolean huyDatPhong() {
		KhachHang kh = dsKH.getKHTheoMa(ma);
		if (kh != null) {
			int row = tableDSPhong.getSelectedRow();
			String maHoaDon = tableDSPhong.getValueAt(row, 0).toString();
			if (maHoaDon != null) {
				int isDelete = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn huỷ", "Thông Báo",
						JOptionPane.YES_NO_OPTION);
				if (isDelete == JOptionPane.YES_OPTION) {
					dsDP.huyDatPhong(maHoaDon);
					JOptionPane.showMessageDialog(this, "Huỷ đặt phòng thành công!!");
					return true;
				}
			} else {
				JOptionPane.showMessageDialog(this, "Huỷ đặt phòng không thành công, vui lòng kiểm tra lại!");
			}
		}
		return false;

	}

	public void getIndexRow() {
		int isSelected1 = tableDSPhong.getSelectedRow();
		if (isSelected1 == -1) {
			btnNhanPhong.setEnabled(false);
			btnHuyDatPhong.setEnabled(false);
			tableDSPhong.clearSelection();
		} else {
			btnHuyDatPhong.setEnabled(true);
			btnNhanPhong.setEnabled(true);
		}

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		getIndexRow();
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

}
