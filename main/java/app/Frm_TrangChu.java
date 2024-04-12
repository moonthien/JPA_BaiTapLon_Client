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

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.LineBorder;

import com.toedter.calendar.JDateChooser;
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

public class Frm_TrangChu extends JFrame {
	JLabel lbBGQLDP, pnTC1, pnTC2, pnTC3, pnTC4;
	FixButton btnLamMoi, btnHuyDatPhong, btnDatPhong, btnNhanPhong;
	private Date ngayHienTai;
	Panel pnPnTC;
	private int ngay, thang, nam;
	private DefaultTableModel model, model1;
	private JPanel panelTong;

	public Panel getFrmTrangChu() {
		return this.pnPnTC;
	}

	public Frm_TrangChu() {
		setTitle("QUẢN LÝ ĐẶT PHÒNG");
		setSize(1400, 670);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(true);
		setLocationRelativeTo(null);

		gui();
	}

	public void gui() {
		getContentPane().setLayout(null);

		pnPnTC = new Panel();
		pnPnTC.setBounds(0, 0, 1400, 670);
		getContentPane().add(pnPnTC);
		pnPnTC.setLayout(null);

		// add background ở cuối
		lbBGQLDP = new JLabel("");
		lbBGQLDP.setIcon(new ImageIcon(Frm_TrangChu.class.getResource("/imgs/bg_trangchu.png")));
		lbBGQLDP.setBounds(0, 0, 1400, 670);
		pnPnTC.add(lbBGQLDP);

	}

}
