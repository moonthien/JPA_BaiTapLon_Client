package app;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.Panel;

import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import client.Client_KhachHangDao;
//import connectDB.*;
//import dao.DanhSachKhachHang;
//import dao.Dao_PhatSinhMa;
import entitys.KhachHang;
import entitys.LoaiKhachHang;
import entitys.Phong;

import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.awt.event.ActionEvent;

public class Frm_KhachHang extends JFrame implements ActionListener, MouseListener {
	private JLabel lbTTKH, lblHoTen, lblSDT, lblCCCD, lblGioiTinh, lblLoaiKhachHgang, lblDTL, lbDSPhong, lbBG, lbTB,
			lbIconSearch;
	private JTextField txtTenKH, txtLoaiKH, txtSDT, txtCCCD, txtDTL;
	private DefaultTableModel model;
	private JTableHeader tbHeader;
	private JTable table;
	private JScrollPane scrollPane;
	private FixButton btnLamMoi, btnSua, btnThem;
	private JComboBox comboGT, comboLKH;
	KeyStroke keyStrokeCTRL1, keyStrokeCTRL2, keyStrokeCTRL3;
//	DanhSachKhachHang dsKh;
	Panel pnQLKH;
	JPanel panel, pnDSP;
	private Client_KhachHangDao dsClientKH;

//	public Panel getFrmQuanLyKhachHang() {
//		return this.pnQLKH;
//	}

	public Frm_KhachHang() throws SQLException, UnknownHostException, IOException, ClassNotFoundException {
		// getContentPane().setBackground(Color.CYAN);
		setTitle("QUẢN LÝ KHÁCH HÀNG");
		setSize(1400, 670);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		setLocationRelativeTo(null);
		gui();
	}

	public void gui() throws SQLException, UnknownHostException, IOException, ClassNotFoundException {
		getContentPane().setLayout(null);
		pnQLKH = new Panel();
		pnQLKH.setBounds(0, 0, 1400, 670);
		getContentPane().add(pnQLKH);
		pnQLKH.setLayout(null);
		//

		panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0), 3));
		panel.setBackground(new Color(207, 169, 0));
		panel.setBounds(102, 51, 1200, 271);
		pnQLKH.add(panel);
		panel.setLayout(null);

		lbTTKH = new JLabel("Thông tin khách hàng");
		lbTTKH.setBounds(102, 21, 250, 20);
		pnQLKH.add(lbTTKH);
		lbTTKH.setFont(new Font("Tahoma", Font.BOLD, 17));
		lbTTKH.setForeground(new Color(255, 255, 255));

		lblHoTen = new JLabel("Họ và tên: ");
		lblHoTen.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblHoTen.setForeground(new Color(255, 255, 255));
		lblHoTen.setBounds(79, 21, 91, 27);
		panel.add(lblHoTen);

		lblSDT = new JLabel("Số điện thoại:");
		lblSDT.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblSDT.setForeground(new Color(255, 255, 255));
		lblSDT.setBounds(679, 20, 123, 28);
		panel.add(lblSDT);

		lblCCCD = new JLabel("CCCD:");
		lblCCCD.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblCCCD.setForeground(new Color(255, 255, 255));
		lblCCCD.setBounds(79, 81, 72, 36);
		panel.add(lblCCCD);

		lblGioiTinh = new JLabel("Giới tính:");
		lblGioiTinh.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblGioiTinh.setForeground(new Color(255, 255, 255));
		lblGioiTinh.setBounds(679, 81, 95, 36);
		panel.add(lblGioiTinh);

		lblLoaiKhachHgang = new JLabel("Loại KH:");
		lblLoaiKhachHgang.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblLoaiKhachHgang.setForeground(new Color(255, 255, 255));
		lblLoaiKhachHgang.setBounds(79, 143, 81, 36);
		panel.add(lblLoaiKhachHgang);

		lblDTL = new JLabel("Điểm tích luỹ:");
		lblDTL.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblDTL.setForeground(new Color(255, 255, 255));
		lblDTL.setBounds(679, 141, 123, 41);
		panel.add(lblDTL);

		txtTenKH = new JTextField();

		txtTenKH.setFont(new Font("Tahoma", Font.BOLD, 15));
		txtTenKH.setBounds(180, 19, 300, 30);
		panel.add(txtTenKH);
		txtTenKH.setColumns(10);

		txtSDT = new JTextField();
		txtSDT.setFont(new Font("Tahoma", Font.BOLD, 15));
		txtSDT.setBounds(812, 19, 300, 30);
		txtSDT.setFont(new Font("Tahoma", Font.BOLD, 17));
		panel.add(txtSDT);
		txtSDT.setColumns(10);

		txtCCCD = new JTextField();
		txtCCCD.setFont(new Font("Tahoma", Font.BOLD, 15));
		txtCCCD.setColumns(10);
		txtCCCD.setBounds(180, 83, 300, 30);
		txtCCCD.setFont(new Font("Tahoma", Font.BOLD, 17));
		panel.add(txtCCCD);

		txtLoaiKH = new JTextField();
		txtLoaiKH.setColumns(10);
		txtLoaiKH.setFont(new Font("Tahoma", Font.BOLD, 17));
		txtLoaiKH.setBounds(180, 146, 300, 30);
		txtLoaiKH.setEditable(false);
		panel.add(txtLoaiKH);

		txtDTL = new JTextField();
		txtDTL.setFont(new Font("Tahoma", Font.BOLD, 15));
		txtDTL.setColumns(10);
		txtDTL.setBounds(812, 145, 300, 31);
		txtDTL.setFont(new Font("Tahoma", Font.BOLD, 17));
		txtDTL.setEditable(false);
		panel.add(txtDTL);

		lbTB = new JLabel();
		lbTB.setHorizontalAlignment(SwingConstants.LEFT);
		lbTB.setFont(new Font("Tahoma", Font.BOLD, 15));
		lbTB.setBounds(380, 180, 700, 36);
		lbTB.setForeground(Color.RED);
		panel.add(lbTB);

		btnThem = new FixButton("Thêm (Ctrl 1)");
		btnThem.setIcon(new ImageIcon(Frm_QuanLyDichVu.class.getResource("/imgs/icon_btn_them.png")));
		btnThem.setBounds(300, 223, 200, 36);
		panel.add(btnThem);
		btnThem.setFont(new Font("Tahoma", Font.BOLD, 15));

		btnSua = new FixButton("Sửa (Ctrl 2)");
		btnSua.setIcon(new ImageIcon(Frm_QuanLyDichVu.class.getResource("/imgs/icon_btn_sua.png")));
		btnSua.setBounds(525, 223, 200, 36);
		panel.add(btnSua);
		btnSua.setFont(new Font("Tahoma", Font.BOLD, 15));

		btnLamMoi = new FixButton("Làm mới (Ctrl 3)");
		btnLamMoi.setIcon(new ImageIcon(Frm_QuanLyDichVu.class.getResource("/imgs/icon_btn_lammoi.png")));
		btnLamMoi.setBounds(750, 223, 200, 36);
		panel.add(btnLamMoi);
		btnLamMoi.setFont(new Font("Tahoma", Font.BOLD, 15));

		comboGT = new JComboBox();
		comboGT.setModel(new DefaultComboBoxModel(new String[] { "Nam", "Nữ" }));
		comboGT.setSelectedIndex(0);
		comboGT.setFont(new Font("Tahoma", Font.BOLD, 15));
		comboGT.setBounds(812, 82, 300, 31);
		panel.add(comboGT);

		lbIconSearch = new JLabel("New label");
		lbIconSearch.setIcon(new ImageIcon(Frm_KhachHang.class.getResource("/imgs/icon_search.png")));
		lbIconSearch.setBounds(1125, 20, 30, 30);
		panel.add(lbIconSearch);

		// Danh sách khách hàng table
		pnDSP = new JPanel();
		pnDSP.setBounds(100, 350, 1200, 270);
		pnDSP.setLayout(null);
		lbDSPhong = new JLabel("Danh sách khách hàng");
		lbDSPhong.setFont(new Font("Tahoma", Font.BOLD, 15));
		lbDSPhong.setBounds(10, 0, 200, 25);
		pnDSP.add(lbDSPhong);

		String col[] = { "Mã KH", "Họ tên", "Loại KH", "Giới tính", "SĐT", "CCCD", "Điểm tích luỹ" };
		model = new DefaultTableModel(col, 0) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false; // Không cho phép chỉnh sửa ô
			}
		};
		table = new JTable(model);

		// Set màu cho table
		// Set màu cho cột tiêu đề
		tbHeader = table.getTableHeader();

		tbHeader.setBackground(new java.awt.Color(0, 0, 0));
		tbHeader.setForeground(Color.WHITE);
		tbHeader.setFont(new Font("Tahoma", Font.BOLD, 14));
		// Set màu các dòng
		table.setShowHorizontalLines(true);
		table.setShowGrid(true);
		table.setBackground(Color.white);
		table.setFont(new Font("SansSerif", Font.PLAIN, 13));
		table.setSelectionBackground(new Color(158, 207, 155));

		table.setRowHeight(30);
		scrollPane = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setBorder(new LineBorder(new Color(158, 207, 0), 1, true));
		scrollPane.setBackground(Color.BLACK);
		scrollPane.setBounds(0, 20, 1200, 250);
		scrollPane.getHorizontalScrollBar();
		pnDSP.add(scrollPane);
		scrollPane.setViewportView(table);
		pnQLKH.add(pnDSP);
// backgroud	

		lbBG = new JLabel();
		lbBG.setBounds(0, 0, 1400, 700);
		lbBG.setIcon(new ImageIcon(Frm_KhachHang.class.getResource("/imgs/bg_chot1.png")));
		pnQLKH.add(lbBG);

		btnLamMoi.addActionListener(this);
		btnThem.addActionListener(this);
		btnSua.addActionListener(this);
		table.addMouseListener(this);
		lbIconSearch.addMouseListener(this);

		Socket socket = new Socket("DESKTOP-R9M9IMC", 3481);

		dsClientKH = new Client_KhachHangDao();


		try {
			upTable();
		} catch (ClassNotFoundException | IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}


		// add và định nghĩa các hot key cho ứng dụng
		keyStrokeCTRL1 = KeyStroke.getKeyStroke("ctrl 1");
		keyStrokeCTRL2 = KeyStroke.getKeyStroke("ctrl 2");
		keyStrokeCTRL3 = KeyStroke.getKeyStroke("ctrl 3");

		// Phím nóng
		addHotKey1();
		addHotKey2();
		addHotKey3();

	}

	public static void main(String[] args)
			throws SQLException, UnknownHostException, IOException, ClassNotFoundException {
		new Frm_KhachHang().setVisible(true);
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

				try {
					if (themKH()) {
						btnSua.setText("Sửa (Ctrl 2)");
						btnThem.setText("Thêm (Ctrl 1)");
						System.out.println("Thêm thành công");
					}
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			} else if (btnThem.getText().equals("Xác nhận ")) {
				try {
					if (suaKH()) {
						btnThem.setText("Thêm (Ctrl 1)");
						btnSua.setText("Sửa (Ctrl 2)");
					}
				} catch (HeadlessException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
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
		//	clearTable();
			xoaTrang();
			try {
				upTable();
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

	public void xoaTrang() {
		txtTenKH.setText("");
		txtSDT.setText("");
		txtCCCD.setText("");
		txtLoaiKH.setText("");
		txtDTL.setText("");
		lbTB.setText("");
		table.clearSelection();
		comboGT.setSelectedIndex(0);
	}

	public void upTable() throws ClassNotFoundException, IOException {
		int i = 1;
		List<KhachHang> list = dsClientKH.getDSKH();
//		LoaiKhachHang loaiKhachHang = dsClientKH.getLoaiKhachHang(2);
		List<LoaiKhachHang> listLoaiKH = dsClientKH.getDSLKH();
		
		for (KhachHang kh : list) {
			
			Object[] obj = new Object[7];
			obj[0] = kh.getMaKhachHang();
			obj[1] = kh.getHoTenKhachHang();
			obj[2] = listLoaiKH.get(kh.getLoaiKhachHang().getMaLoaiKhachHang()-1).getTenLoaiKhachHang();
			obj[3] = kh.isGioiTinh() ? "Nam" : "Nữ";
			obj[4] = kh.getSoDienThoai().trim();
			obj[5] = kh.getSoCCCD().trim();
			obj[6] = kh.getDiemTichLuy();
			model.addRow(obj);
		}
		xoaTrang();
	}

//	 theem khach hang
	public boolean themKH() throws ClassNotFoundException, IOException {
		Object[] obj = new Object[7];
		if (ktraDuLieu()) {
			String ten = txtTenKH.getText();
			String dt = txtSDT.getText();
			String cccd = txtCCCD.getText();
			String gt = (String) comboGT.getSelectedItem();
			boolean gioitinh;
			if (gt.equals("Nam")) {
				gioitinh = true;
			} else
				gioitinh = false;
//			LoaiKhachHang lkh = new LoaiKhachHang("NOR", "Khách hàng thường");
			LoaiKhachHang loaiKhachHang = dsClientKH.getLoaiKhachHang(2);

			KhachHang kh = new KhachHang(ten, cccd, dt, 1, gioitinh, loaiKhachHang);
			dsClientKH.themKhachHang(kh);

			List<KhachHang> list = dsClientKH.getDSKH();
			KhachHang kh1 = list.get(list.size()-1);
			JOptionPane.showMessageDialog(this, "Thêm thành công");
			obj[0] = kh1.getMaKhachHang();
			obj[1] = kh.getHoTenKhachHang();
			obj[2] = kh.getLoaiKhachHang().getTenLoaiKhachHang();
			obj[3] = gt;
			obj[4] = dt;
			obj[5] = cccd;
			obj[6] = kh.getDiemTichLuy();
			model.addRow(obj);
			xoaTrang();
//				return true;
//			}
		}
		return false;
	}

	// update khach hang
	public boolean suaKH() throws HeadlessException, ClassNotFoundException, IOException {
		int row = table.getSelectedRow();
		if (row == -1) {
			JOptionPane.showMessageDialog(this, "Chọn khách hàng cần sửa");
		} else {
			Object[] obj = new Object[7];
			if (ktraDuLieuSua()) {
				int ma = (int) table.getValueAt(row, 0);
				String ten = txtTenKH.getText().toString();
				String dt = txtSDT.getText().toString();
				String cccd = txtCCCD.getText().toString();
				String gt = (String) comboGT.getSelectedItem();
				boolean gioitinh;
				if (gt.equals("Nam")) {
					gioitinh = true;
				} else
					gioitinh = false;
//				LoaiKhachHang lkh = new LoaiKhachHang("NOR", "Khách hàng thường");
				LoaiKhachHang loaiKhachHang = dsClientKH.getLoaiKhachHang(1);
				obj[0] = ma;
				obj[1] = ten;
				obj[2] = loaiKhachHang.getTenLoaiKhachHang();
				obj[3] = gt;
				obj[4] = dt;
				obj[5] = cccd;
				obj[6] = 0;
				
				KhachHang kh = new KhachHang(ma,ten, cccd, dt, 0, gioitinh, loaiKhachHang);
				if (dsClientKH.updateKhachHang(kh)) {
				JOptionPane.showMessageDialog(this, "Sửa thành công");
				table.setValueAt(obj[1], row, 1);
				table.setValueAt(obj[3], row, 3);
				table.setValueAt(obj[4], row, 4);
				table.setValueAt(obj[5], row, 5);
				xoaTrang();
				return true;
			}
			}
		}
		return false;

	}
	// click table

	public void setTextTB() {
		int row = table.getSelectedRow();
		txtTenKH.setText(table.getValueAt(row, 1).toString());
		txtCCCD.setText(table.getValueAt(row, 5).toString());
		txtLoaiKH.setText(table.getValueAt(row, 2).toString());
		int i = 1;
		if (table.getValueAt(row, 3).toString().equals("Nam"))
			i = 0;
		comboGT.setSelectedIndex(i);
		txtSDT.setText(table.getValueAt(row, 4).toString());
		txtDTL.setText(table.getValueAt(row, 6).toString());
	}

	public void showMessage(String message) {
		lbTB.setText(message);
	}

	public void ktraKH() throws ClassNotFoundException, IOException {
		String sdt = txtSDT.getText();
		KhachHang kh = dsClientKH.getKhachHangTheoSDT(sdt);
		if (sdt.equals("")) {
			
			JOptionPane.showMessageDialog(this, "Số điện thoại không được để trống");
			txtSDT.requestFocus();
		} else if (!sdt.matches("^(0[0-9]{9})$")) {
			JOptionPane.showMessageDialog(this, "Số điện thoại không quá 10 số và bắt đầu bằng số 0");
			txtSDT.requestFocus();
		} else if (kh != null) {
			LoaiKhachHang loaiKhachHang = dsClientKH.getLoaiKhachHang(kh.getLoaiKhachHang().getMaLoaiKhachHang());
			txtTenKH.setText(kh.getHoTenKhachHang());
			txtCCCD.setText(kh.getSoCCCD());
			txtLoaiKH.setText(loaiKhachHang.getTenLoaiKhachHang());
			txtDTL.setText(Integer.toString(kh.getDiemTichLuy()));
			Boolean gt = kh.isGioiTinh();
			int i = 2;
			if (gt == true) {
				i = 0;
			} else {
				i = 1;
			}
			comboGT.setSelectedIndex(i);
			locKHTheoSDT();
		} else {
			JOptionPane.showMessageDialog(this, "Khách hàng chưa có trong hệ thống \n Thêm khách hàng mới!!!");
			txtTenKH.requestFocus();
		}

	}

	public void clearTable() {
		while (table.getRowCount() > 0) {
			model.removeRow(0);
		}
	}

//	// Lọc khách hàng theo SĐT
	public void locKHTheoSDT() throws ClassNotFoundException, IOException {
		clearTable();
		String sdt = txtSDT.getText();
		KhachHang kh = dsClientKH.getKhachHangTheoSDT(sdt);
		LoaiKhachHang loaiKhachHang = dsClientKH.getLoaiKhachHang(kh.getLoaiKhachHang().getMaLoaiKhachHang());
		Object[] obj = new Object[7];
		obj[0] = kh.getMaKhachHang();
		obj[1] = kh.getHoTenKhachHang();
		obj[2] = loaiKhachHang.getTenLoaiKhachHang();
		obj[3] = kh.isGioiTinh() ? "Nam" : "Nữ";
		obj[4] = kh.getSoDienThoai().trim();
		obj[5] = kh.getSoCCCD().trim();
		obj[6] = kh.getDiemTichLuy();
		model.addRow(obj);

	}

	// hot key Ctrl1
	public void addHotKey1() {

		btnThem.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(keyStrokeCTRL1, "clickButton");
		btnThem.getActionMap().put("clickButton", new AbstractAction() {
		@Override
			public void actionPerformed(ActionEvent e) {
				btnThem.doClick();
			}
		});
	}
//
//	// hot key Crtl2
	public void addHotKey2() {
		btnSua.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(keyStrokeCTRL2, "clickButton");
		btnSua.getActionMap().put("clickButton", new AbstractAction() {
						        @Override
			public void actionPerformed(ActionEvent e) {
				btnSua.doClick();
			}
		});
	}
//
//	// hot key Crtl3
	public void addHotKey3() {
		btnLamMoi.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(keyStrokeCTRL3, "clickButton");
		btnLamMoi.getActionMap().put("clickButton", new AbstractAction() {
					        @Override
			public void actionPerformed(ActionEvent e) {
				btnLamMoi.doClick();
			}
		});
	}

	// kiểm tra regex
	public boolean ktraDuLieu() {
		String ten = txtTenKH.getText();

		if (ten.equals("") || !ten.matches(
				"^[A-ZĐđ][ A-ZĐđa-za-zĐđA-ZÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠàáâãèéêìíòóôõùúăđĩũơƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂẾưăạảấầẩẫậắằẳẵặẹẻẽềềểếỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪễệỉịọỏốồổỗộớờởỡợụủứừỬỮỰỲỴÝỶỸửữựỳỵỷỹ]*")) {
			showMessage("(*) Tên không được để trống và viết hoa chữ cái đầu");

			txtTenKH.requestFocus();
			return false;
		}
		String dt = txtSDT.getText();
		for (int i = 0; i < table.getRowCount(); i++) {
			if (dt.equals(table.getValueAt(i, 4).toString())) {
				showMessage("(*)Số điện thoại đã tồn tại");
				txtSDT.requestFocus();
				return false;
			}
		}
		if (dt.equals("") || !dt.matches("^(0[0-9]{9})$")) {
			showMessage("(*)Số điện thoại không để trống và chỉ được 10 số, bắt đầu bằng số 0");
			txtSDT.requestFocus();
			return false;
		}

		String cccd = txtCCCD.getText();
		for (int i = 0; i < table.getRowCount(); i++) {
			if (cccd.equals(table.getValueAt(i, 5).toString())) {
				showMessage("(*)Số CCCD đã tồn tại");
				txtCCCD.requestFocus();
				return false;
			}
		}
		if (cccd.equals("") || !cccd.matches("^([0-9]{12})$")) {
			showMessage("(*)Số CCCD không để trống và chỉ được 12 số");
			txtCCCD.requestFocus();
			return false;
		}
		return true;
	}

	// kiểm tra regex
	public boolean ktraDuLieuSua() {
		int row = table.getSelectedRow();
		String ten = txtTenKH.getText();
		if (ten.equals("") || !ten.matches(
				"^[A-ZĐđ][ A-ZĐđa-za-zĐđA-ZÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠàáâãèéêìíòóôõùúăđĩũơƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂẾưăạảấầẩẫậắằẳẵặẹẻẽềềểếỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪễệỉịọỏốồổỗộớờởỡợụủứừỬỮỰỲỴÝỶỸửữựỳỵỷỹ]*")) {
			showMessage("(*) Tên không được để trống và viết hoa chữ cái đầu");
			txtTenKH.requestFocus();
			return false;
		}
		String dt = txtSDT.getText();
		for (int i = 0; i < table.getRowCount(); i++) {
			if (dt.equals(table.getValueAt(i, 4).toString()) && !dt.equals(table.getValueAt(row, 4))) {
				JOptionPane.showMessageDialog(this, "SĐT đã tồn tại");
				return false;
			}
		}
		if (dt.equals("") || !dt.matches("^(0[0-9]{9})$")) {
			showMessage("(*)Số điện thoại không để trống và chỉ được 10 số, bắt đầu bằng số 0");
			txtSDT.requestFocus();
			return false;
		}

		String cccd = txtCCCD.getText();
		for (int i = 0; i < table.getRowCount(); i++) {
			if (cccd.equals(table.getValueAt(i, 5).toString()) && !cccd.equals(table.getValueAt(row, 5))) {
				JOptionPane.showMessageDialog(this, "CCCD đã tồn tại  e");
				return false;

			}
		}
		if (cccd.equals("") || !cccd.matches("^([0-9]{12})$")) {
			showMessage("(*)Số CCCD không để trống và chỉ được 12 số");
			txtCCCD.requestFocus();
			return false;
		}
		return true;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		Object o = e.getSource();
		if (o == lbIconSearch) {
			try {
				ktraKH();
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} else
			setTextTB();

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
		}
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		Object o = e.getSource();
		if (o == lbIconSearch) {
			lbIconSearch.setBorder(new LineBorder(new Color(0, 0, 0), 0));
		}
	}

}
