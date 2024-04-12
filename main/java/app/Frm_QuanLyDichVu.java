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
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
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
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import com.toedter.calendar.JDateChooser;

import connectDB.ConnectDB;
import dao.DanhSachDichVu;
import dao.DanhSachKhachHang;
import dao.Dao_PhatSinhMa;

import entitys.DichVu;
import entitys.KhachHang;
import entitys.LoaiDichVu;
import entitys.LoaiKhachHang;
import entitys.NhanVien;
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

public class Frm_QuanLyDichVu extends JFrame implements ActionListener, MouseListener {
	private JPanel pnDSDichVu, pnTTDV;
	private JLabel lbDSDichVu, lbBGQLDV, lbTTDV, lbLoaiDichVu, lbTenDV, lbSoLuongTon, lbDonGia, lbTB, lbIconSearch,
			lbIconSearchLTP;
	private JComboBox comboTDV, comboLDV;
	private JTextField txtDonGia, txtSoLuongTon;
	private Panel pnQLDV;
	private JTableHeader tbHeader;
	private FixButton btnLamMoi, btnThem, btnSua;
	private JTable tableDSDichVu;
	private JScrollPane scrollPane;
	private DefaultTableModel model;
	KeyStroke keyStrokeCTRL1, keyStrokeCTRL2, keyStrokeCTRL3;
	DanhSachDichVu dsDV;
	DecimalFormat df;
	DecimalFormat dfslt;

	public Panel getFrmQuanLyDichVu() {
		return this.pnQLDV;
	}

	public Frm_QuanLyDichVu() throws SQLException {
		setTitle("QUẢN LÝ DỊCH VỤ");
		setSize(1400, 670);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(true);
		setLocationRelativeTo(null);
		gui();
	}

	public void gui() throws SQLException {
		getContentPane().setLayout(null);

		pnQLDV = new Panel();
		pnQLDV.setBounds(0, 0, 1400, 670);
		getContentPane().add(pnQLDV);
		pnQLDV.setLayout(null);

		pnTTDV = new JPanel();
		pnTTDV.setBorder(new LineBorder(new Color(0, 0, 0), 3));
		pnTTDV.setBackground(new java.awt.Color(207, 169, 0));
		pnTTDV.setBounds(40, 45, 1280, 181);
		pnQLDV.add(pnTTDV);
		pnTTDV.setLayout(null);

		lbTTDV = new JLabel("Thông tin dịch vụ");
		lbTTDV.setFont(new Font("Tahoma", Font.BOLD, 17));
		lbTTDV.setForeground(new Color(255, 255, 255));
		lbTTDV.setBounds(30, 15, 250, 20);
		pnQLDV.add(lbTTDV);

		lbLoaiDichVu = new JLabel("Loại dịch vụ:");
		lbLoaiDichVu.setForeground(Color.WHITE);
		lbLoaiDichVu.setFont(new Font("Tahoma", Font.BOLD, 15));
		lbLoaiDichVu.setBounds(36, 29, 200, 25);
		pnTTDV.add(lbLoaiDichVu);

		lbTenDV = new JLabel("Tên dịch vụ:");
		lbTenDV.setForeground(Color.WHITE);
		lbTenDV.setFont(new Font("Tahoma", Font.BOLD, 15));
		lbTenDV.setBounds(36, 69, 200, 25);
		pnTTDV.add(lbTenDV);

		lbSoLuongTon = new JLabel("Số lượng tồn:");
		lbSoLuongTon.setForeground(Color.WHITE);
		lbSoLuongTon.setFont(new Font("Tahoma", Font.BOLD, 15));
		lbSoLuongTon.setBounds(687, 29, 200, 25);
		pnTTDV.add(lbSoLuongTon);

		lbDonGia = new JLabel("Đơn giá:");
		lbDonGia.setForeground(Color.WHITE);
		lbDonGia.setFont(new Font("Tahoma", Font.BOLD, 15));
		lbDonGia.setBounds(687, 69, 200, 25);
		pnTTDV.add(lbDonGia);

		comboLDV = new JComboBox();
		comboLDV.setModel(new DefaultComboBoxModel(new String[] { "Thực phẩm", "Nước uống" }));
		comboLDV.setSelectedIndex(0);
		comboLDV.setFont(new Font("Tahoma", Font.BOLD, 15));
		comboLDV.setBounds(226, 29, 300, 30);
		pnTTDV.add(comboLDV);

		comboTDV = new JComboBox();
		comboTDV.setFont(new Font("Tahoma", Font.BOLD, 15));
		comboTDV.setBounds(226, 69, 300, 30);
		comboTDV.setEditable(true);
		pnTTDV.add(comboTDV);

		txtSoLuongTon = new JTextField();
		txtSoLuongTon.setFont(new Font("Tahoma", Font.BOLD, 15));
		txtSoLuongTon.setBounds(877, 29, 300, 30);
		pnTTDV.add(txtSoLuongTon);

		txtDonGia = new JTextField();
		txtDonGia.setFont(new Font("Tahoma", Font.BOLD, 15));
		txtDonGia.setBounds(877, 69, 300, 30);
		txtDonGia.setFont(new Font("Tahoma", Font.BOLD, 15));
		pnTTDV.add(txtDonGia);

		lbTB = new JLabel();
		lbTB.setHorizontalAlignment(SwingConstants.LEFT);
		lbTB.setFont(new Font("Tahoma", Font.BOLD, 15));
		lbTB.setBounds(20, 128, 500, 36);
		lbTB.setForeground(Color.RED);
		pnTTDV.add(lbTB);
// các nút CRUD
		btnThem = new FixButton("Thêm (Ctrl 1)");
		btnThem.setIcon(new ImageIcon(Frm_QuanLyDichVu.class.getResource("/imgs/icon_btn_them.png")));
		btnThem.setBounds(550, 128, 190, 40);
		pnTTDV.add(btnThem);
		btnThem.setFont(new Font("Tahoma", Font.BOLD, 16));

		btnSua = new FixButton("Sửa (Ctrl 2)");
		btnSua.setIcon(new ImageIcon(Frm_QuanLyDichVu.class.getResource("/imgs/icon_btn_sua.png")));
		btnSua.setBounds(750, 128, 190, 40);
		pnTTDV.add(btnSua);
		btnSua.setFont(new Font("Tahoma", Font.BOLD, 16));

		btnLamMoi = new FixButton("Làm mới (Ctrl 3)");
		btnLamMoi.setIcon(new ImageIcon(Frm_QuanLyDichVu.class.getResource("/imgs/icon_btn_lammoi.png")));
		btnLamMoi.setBounds(950, 128, 200, 40);
		pnTTDV.add(btnLamMoi);
		btnLamMoi.setFont(new Font("Tahoma", Font.BOLD, 16));

		lbIconSearch = new JLabel("New label");
		lbIconSearch.setIcon(new ImageIcon(Frm_QuanLyDichVu.class.getResource("/imgs/icon_search.png")));
		lbIconSearch.setBounds(536, 69, 30, 30);
		pnTTDV.add(lbIconSearch);

		lbIconSearchLTP = new JLabel("");
		lbIconSearchLTP.setIcon(new ImageIcon(Frm_QuanLyDichVu.class.getResource("/imgs/icon_search.png")));
		lbIconSearchLTP.setBounds(536, 29, 30, 30);
		pnTTDV.add(lbIconSearchLTP);

		pnDSDichVu = new JPanel();
		pnDSDichVu.setBackground(Color.WHITE);
		pnDSDichVu.setBounds(0, 310, 1400, 320);
		pnQLDV.add(pnDSDichVu);
		pnDSDichVu.setLayout(null);

		lbDSDichVu = new JLabel("Danh sách dịch vụ");
		lbDSDichVu.setFont(new Font("Tahoma", Font.BOLD, 15));
		lbDSDichVu.setBounds(10, 0, 150, 25);
		pnDSDichVu.add(lbDSDichVu);
		// bảng table
		String col[] = { "Mã DV", "Tên Dịch Vụ", "Loại Dịch Vụ", "Số Lượng Tồn", "Giá Bán" };
		model = new DefaultTableModel(col, 0) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false; // Không cho phép chỉnh sửa ô
			}
		};

		tableDSDichVu = new JTable(model);
		tableDSDichVu.setBackground(Color.WHITE);

		// Set màu cho table
		// Set màu cho cột tiêu đề
		tbHeader = tableDSDichVu.getTableHeader();
		tbHeader.setBackground(new java.awt.Color(0, 0, 0));
		tbHeader.setForeground(Color.WHITE);
		tbHeader.setFont(new Font("Tahoma", Font.BOLD, 14));
		tbHeader.setPreferredSize(new Dimension(100, 30));
		// Set màu các dòng

		tableDSDichVu.setBackground(Color.white);
		tableDSDichVu.setFont(new Font("SansSerif", Font.PLAIN, 13));
		tableDSDichVu.setSelectionBackground(new Color(158, 207, 0));
		tableDSDichVu.setSelectionForeground(new Color(255, 255, 255));
		tableDSDichVu.setRowHeight(30);

		scrollPane = new JScrollPane(tableDSDichVu, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setBorder(new LineBorder(new Color(158, 207, 0), 1, true));
		scrollPane.setBackground(Color.BLACK);
		scrollPane.setBounds(0, 25, 1380, 290);
		scrollPane.getHorizontalScrollBar();
		pnDSDichVu.add(scrollPane);

		scrollPane.setViewportView(tableDSDichVu);

		// add background ở cuối
		lbBGQLDV = new JLabel("");
		lbBGQLDV.setIcon(new ImageIcon(Frm_QuanLyDatPhong.class.getResource("/imgs/bg_chot1.png")));
		lbBGQLDV.setBounds(0, 0, 1400, 670);
		pnQLDV.add(lbBGQLDV);

		btnLamMoi.addActionListener(this);
		btnThem.addActionListener(this);
		btnSua.addActionListener(this);
		comboLDV.addActionListener(this);
		tableDSDichVu.addMouseListener(this);
		lbIconSearch.addMouseListener(this);
		lbIconSearchLTP.addMouseListener(this);
		// kết nối data
		ConnectDB.getInstance().connect();
		// Danh sach Mat Hang
		dsDV = new DanhSachDichVu();
		upTable();
		phanLoaiCombobox();

		// add và định nghĩa các hot key cho ứng dụng
		keyStrokeCTRL1 = KeyStroke.getKeyStroke("ctrl 1");
		keyStrokeCTRL2 = KeyStroke.getKeyStroke("ctrl 2");
		keyStrokeCTRL3 = KeyStroke.getKeyStroke("ctrl 3");

		// Phím nóng
		addHotKey1();
		addHotKey2();
		addHotKey3();
	}

	public static void main(String[] args) throws SQLException {
		new Frm_QuanLyDichVu().setVisible(true);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object o = e.getSource();
		if (o.equals(btnThem)) {
			if (btnThem.getText().equalsIgnoreCase("Thêm (Ctrl 1)")) {
				btnThem.setText("Xác nhận");
				btnSua.setText("Hủy");
			} else if (btnThem.getText().equalsIgnoreCase("Xác nhận")) {
				if (themDV()) {
					btnSua.setText("Sửa (Ctrl 2)");
					btnThem.setText("Thêm (Ctrl 1)");
				}
			} else if (btnThem.getText().equals("Xác nhận ")) {
				if (suaDichVu()) {
					btnThem.setText("Thêm (Ctrl 1)");
					btnSua.setText("Sửa (Ctrl 2)");
				}
			}
		} else if (o.equals(btnSua)) {
			if (btnSua.getText().equals("Hủy")) {
				btnThem.setText("Thêm (Ctrl 1)");
				btnSua.setText("Sửa (Ctrl 2)");
			} else if (btnSua.getText().equals("Sửa (Ctrl 2)")) {
				btnThem.setText("Xác nhận ");
				btnSua.setText("Hủy");
			}
		} else if (o.equals(btnLamMoi)) {
			clearTable();
			xoaTrang();
			upTable();

		}
		if (o == comboLDV) {
			phanLoaiCombobox();
		}

	}

	public void xoaTrang() {
		txtSoLuongTon.setText("");
		txtDonGia.setText("");
		tableDSDichVu.clearSelection();
		lbTB.setText("");
	}

	// Up thông tin lên bảng
	public void upTable() {
		int i = 0;
		df = new DecimalFormat("###,### VNĐ");
		ArrayList<DichVu> list = dsDV.getDSDichVu();
		for (DichVu dv : list) {
			Object[] obj = new Object[7];
			obj[0] = dv.getMaDichVu().trim();
			obj[1] = dv.getTenDichVu().trim();
			obj[2] = dv.getloaiDichVu().getTenLoaiDichVu();
			obj[3] = dv.getSoLuongTon();
			obj[4] = df.format(dv.getDonGia());
			model.addRow(obj);
		}
		xoaTrang();
	}

	// thêm dịch vụ
	public boolean themDV() {
		df = new DecimalFormat("###,### VNĐ");
		Object[] obj = new Object[6];
		if (ktraDuLieu()) {
			Dao_PhatSinhMa matp1 = new Dao_PhatSinhMa();
			String mada = matp1.getMaDATuDong();
			String manu = matp1.getMaNUTuDong();
			String loai = (String) comboLDV.getSelectedItem();
			String tendv = (String) comboTDV.getSelectedItem();
			LoaiDichVu ldv;
			String ma;
			if (loai.equals("Thực phẩm")) {
				ma = mada;
				ldv = new LoaiDichVu("FOOD", "Thực phẩm");

			} else {
				ldv = new LoaiDichVu("WATER", "Nước uống");
				ma = manu;
			}
			int slt = Integer.parseInt(txtSoLuongTon.getText());
			double giaban = Double.parseDouble(txtDonGia.getText());

			DichVu dv = new DichVu(ma, tendv, ldv, slt, giaban);

			if (!dsDV.themDichVu(dv)) {
				JOptionPane.showMessageDialog(this, "Thêm thành công");
				obj[0] = ma;
				obj[1] = tendv;
				obj[2] = ldv.getTenLoaiDichVu();
				obj[3] = slt;
				obj[4] = df.format(giaban);
				model.addRow(obj);
				xoaTrang();
				return true;
			}
		}
		return false;
	}

	// update dịch vụ
	public boolean suaDichVu() {
		df = new DecimalFormat("###,### VNĐ");
		int row = tableDSDichVu.getSelectedRow();
		if (row == -1) {
			JOptionPane.showMessageDialog(this, "Chọn dịch vụ cần sửa");

		} else {

			Object[] obj = new Object[7];
			if (ktraDuLieuSua()) {
				String ma = (String) tableDSDichVu.getValueAt(row, 0);
				String loai = (String) comboLDV.getSelectedItem();
				String tendv = (String) comboTDV.getSelectedItem();
				LoaiDichVu ldv;
				if (loai.equals("Thực phẩm")) {
					ldv = new LoaiDichVu("FOOD", "Thực phẩm");
				} else {
					ldv = new LoaiDichVu("WATER", "Nước uống");
				}
				int slt = Integer.parseInt(txtSoLuongTon.getText());
				double giaban = Double.parseDouble(txtDonGia.getText());
				DichVu dv = new DichVu(ma, tendv, ldv, slt, giaban);
				obj[0] = ma;
				obj[1] = tendv;
				obj[2] = ldv.getTenLoaiDichVu();
				obj[3] = slt;
				obj[4] = df.format(giaban);
				if (!dsDV.suaDichVu(dv)) {
					JOptionPane.showMessageDialog(this, "Sửa thành công");
					tableDSDichVu.setValueAt(obj[1], row, 1);
					tableDSDichVu.setValueAt(obj[2], row, 2);
					tableDSDichVu.setValueAt(obj[3], row, 3);
					tableDSDichVu.setValueAt(obj[4], row, 4);
					xoaTrang();
					return true;

				}
			}
		}
		return false;
	}

	// Phân loại Dịch vụ
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

// Lọc dịch vụ theo loại dịch vụ
	public void locTheoLoaiDichVu() {
		clearTable();
		df = new DecimalFormat("###,### VNĐ");
		String loai = String.valueOf(comboLDV.getSelectedItem());
		String ma;
		if (loai.equals("Thực phẩm")) {
			ma = "FOOD";
		} else {
			ma = "WATER";
		}
		ArrayList<DichVu> list = dsDV.getDSDichVu();
		int i = 0;
		for (DichVu dv : list) {
			if (dv.getloaiDichVu().getMaLoaiDichVu().equals(ma)) {
				Object[] obj = new Object[6];
				obj[0] = dv.getMaDichVu().trim();
				obj[1] = dv.getTenDichVu();
				obj[2] = dv.getloaiDichVu().getTenLoaiDichVu();
				obj[3] = dv.getSoLuongTon();
				obj[4] = df.format(dv.getDonGia());
				model.addRow(obj);
			}
		}
	}

	// Lọc dịch vụ theo tên add kết quả lọc lên bảng
	public void locTheoTenDichVu() {
		clearTable();
		df = new DecimalFormat("###,### VNĐ");
		String tendv = (String)(comboTDV.getSelectedItem());
		ArrayList<DichVu> list = dsDV.getDSDichVu();
		int i = 0;
		for (DichVu dv : list) {
			if (dv.getTenDichVu().equalsIgnoreCase(tendv)) {
				Object[] obj = new Object[6];
				obj[0] = dv.getMaDichVu().trim();
				obj[1] = dv.getTenDichVu();
				obj[2] = dv.getloaiDichVu().getTenLoaiDichVu();
				obj[3] = dv.getSoLuongTon();
				obj[4] = df.format(dv.getDonGia());
				model.addRow(obj);
			}
		}
	}

	// Lọc dịch vụ theo tên dịch vụ
	public void kiemTraTenDV() {
		String tendv = comboTDV.getSelectedItem().toString();
		df = new DecimalFormat("#######");
		dfslt = new DecimalFormat("####");
		DichVu dv = dsDV.getDVTheoTen(tendv);
		if (tendv.equals("")) {
			JOptionPane.showMessageDialog(this, "Tên dịch vụ không được để trống");
			comboTDV.requestFocus();
		} else if (!tendv.matches(
				"^[A-ZĐđ][ A-ZĐđa-za-zĐđA-ZÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠàáâãèéêìíòóôõùúăđĩũơƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂẾưăạảấầẩẫậắằẳẵặẹẻẽềềểếỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪễệỉịọỏốồổỗộớờởỡợụủứừỬỮỰỲỴÝỶỸửữựỳỵỷỹ]*")) {
			JOptionPane.showMessageDialog(this, "Tên dịch vụ viết hoa chữ cái đầu !");
			comboTDV.requestFocus();
		} else if (dv != null) {
			String ldv = dv.getloaiDichVu().getTenLoaiDichVu();
			txtSoLuongTon.setText(dfslt.format(dv.getSoLuongTon()));
			txtDonGia.setText(df.format(dv.getDonGia()));
			locTheoTenDichVu();
		}else {
			JOptionPane.showMessageDialog(this, "Dịch vụ chưa có trong hệ thống \n Thêm dịch vụ mới!!!");
			comboTDV.requestFocus();
		}
	}

	// Xóa trắng bảng
	public void clearTable() {
		while (tableDSDichVu.getRowCount() > 0) {
			model.removeRow(0);
		}
	}

	// click table

	public void setTextTB() {
		int row = tableDSDichVu.getSelectedRow();
		comboLDV.setSelectedItem(tableDSDichVu.getValueAt(row, 2).toString());
		comboTDV.setSelectedItem(tableDSDichVu.getValueAt(row, 1).toString());
		txtSoLuongTon.setText(tableDSDichVu.getValueAt(row, 3).toString());
		try {
			txtDonGia.setText(df.parse(tableDSDichVu.getValueAt(row, 4).toString()) + "");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void showMessage(String message) {
		lbTB.setText(message);
	}

	// hot key Ctrl1
	public void addHotKey1() {

		btnThem.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(keyStrokeCTRL1, "clickButton");
		btnThem.getActionMap().put("clickButton", new AbstractAction() {
//							        @Override
			public void actionPerformed(ActionEvent e) {
				btnThem.doClick();
			}
		});
	}

	// hot key Crtl2
	public void addHotKey2() {
		btnSua.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(keyStrokeCTRL2, "clickButton");
		btnSua.getActionMap().put("clickButton", new AbstractAction() {
//							        @Override
			public void actionPerformed(ActionEvent e) {
				btnSua.doClick();
			}
		});
	}

	// hot key Crtl3
	public void addHotKey3() {
		btnLamMoi.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(keyStrokeCTRL3, "clickButton");
		btnLamMoi.getActionMap().put("clickButton", new AbstractAction() {
//							        @Override
			public void actionPerformed(ActionEvent e) {
				btnLamMoi.doClick();
			}
		});
	}

// Kiểm tra dữ liêu nhập vào
	public boolean ktraDuLieu() {

		try {
			int slt = Integer.parseInt(txtSoLuongTon.getText());
			if (slt <= 0) {
				showMessage("(*) Số lượng tồn phải lớn hơn 0");
				txtSoLuongTon.requestFocus();
				return false;
			}
		} catch (Exception e) {
			showMessage("(*) Số lượng tồn là số và không được để trống");
			txtSoLuongTon.requestFocus();
			return false;
		}

		try {
			float donGia = Float.parseFloat(txtDonGia.getText());
			if (donGia <= 0) {
				showMessage("(*) Đơn giá phải lớn hơn 0");
				txtDonGia.requestFocus();
				return false;
			}
		} catch (Exception e) {
			showMessage("(*) Đơn giá phải là số và không được để trống");
			txtDonGia.requestFocus();
			return false;
		}
		String tendv = comboTDV.getSelectedItem().toString();
		for (int i = 0; i < tableDSDichVu.getRowCount(); i++) {
			if (tendv.equalsIgnoreCase(tableDSDichVu.getValueAt(i, 1).toString())) {
				showMessage("(*) Tên dịch vụ đã tồn tại, vui lòng sửa lại số lượng tồn, đơn giá");
				comboTDV.requestFocus();
				return false;
			}
		}
		if (tendv.equals("") || !tendv.matches(
				"^[A-ZĐđ][ A-ZĐđa-za-zĐđA-ZÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠàáâãèéêìíòóôõùúăđĩũơƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂẾưăạảấầẩẫậắằẳẵặẹẻẽềềểếỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪễệỉịọỏốồổỗộớờởỡợụủứừỬỮỰỲỴÝỶỸửữựỳỵỷỹ]*")) {
			showMessage("(*) Tên dịch vụ không được để trống và viết hoa chữ cái đầu");
			comboTDV.requestFocus();
			return false;
		}

		return true;
	}

	// Kiểm tra dữ liêu sửa
	public boolean ktraDuLieuSua() {
		int row = tableDSDichVu.getSelectedRow();
		try {
			int slt = Integer.parseInt(txtSoLuongTon.getText());
			if (slt <= 0) {
				showMessage("(*) Số lượng tồn phải lớn hơn 0");
				txtSoLuongTon.requestFocus();
				return false;
			}
		} catch (Exception e) {
			showMessage("(*) Số lượng tồn là số và không được để trống");
			txtSoLuongTon.requestFocus();
			return false;
		}

		try {
			float donGia = Float.parseFloat(txtDonGia.getText());
			if (donGia <= 0) {
				showMessage("(*) Đơn giá phải lớn hơn 0");
				txtDonGia.requestFocus();
				return false;
			}
		} catch (Exception e) {
			showMessage("(*) Đơn giá phải là số và không được để trống");
			txtDonGia.requestFocus();
			return false;
		}
		String tendv = comboTDV.getSelectedItem().toString();
		for (int i = 0; i < tableDSDichVu.getRowCount(); i++) {
			if (tendv.equalsIgnoreCase(tableDSDichVu.getValueAt(i, 1).toString())
					&& !tendv.equals(tableDSDichVu.getValueAt(row, 1))) {
				JOptionPane.showMessageDialog(this, "Tên dịch vụ đã tồn tại !!!");
				return false;

			}
		}
		if (tendv.equals("") || !tendv.matches(
				"^[A-ZĐđ][ A-ZĐđa-za-zĐđA-ZÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠàáâãèéêìíòóôõùúăđĩũơƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂẾưăạảấầẩẫậắằẳẵặẹẻẽềềểếỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪễệỉịọỏốồổỗộớờởỡợụủứừỬỮỰỲỴÝỶỸửữựỳỵỷỹ]*")) {
			showMessage("(*) Tên dịch vụ không được để trống và viết hoa chữ cái đầu");
			comboTDV.requestFocus();
			return false;
		}

		return true;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		Object o = e.getSource();
		if (o == lbIconSearch) {
			kiemTraTenDV();
		} else if (o == lbIconSearchLTP) {
			locTheoLoaiDichVu();
		} else {
			setTextTB();
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
		if (o == lbIconSearch) {
			lbIconSearch.setBorder(new LineBorder(new Color(0, 0, 0), 3));
		} else if (o == lbIconSearchLTP) {
			lbIconSearchLTP.setBorder(new LineBorder(new Color(0, 0, 0), 3));
		}
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		Object o = e.getSource();
		if (o == lbIconSearch) {
			lbIconSearch.setBorder(new LineBorder(new Color(0, 0, 0), 0));
		} else if (o == lbIconSearchLTP) {
			lbIconSearchLTP.setBorder(new LineBorder(new Color(0, 0, 0), 0));
		}
	}
}
