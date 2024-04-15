package client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import entitys.DichVu;
import entitys.HoaDonPhong;
import entitys.KhachHang;
import entitys.NhanVien;
import entitys.Phong;

public class Client_HoaDonDao {
	private static ObjectOutputStream out;
	private static ObjectInputStream in;

	public Client_HoaDonDao() throws IOException {

		try {
			Socket socket = new Socket("DESKTOP-R9M9IMC", 3481);
			out = new ObjectOutputStream(socket.getOutputStream());
			out.flush();
			in = new ObjectInputStream(socket.getInputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/*
	 * case "getSoHDTheoMaKHTheoNgay":
					int maKhachHang = in.readInt();
					LocalDate ngayBatDau = (LocalDate) in.readObject();
					LocalDate ngayKetThuc = (LocalDate) in.readObject();
					long soHD = hoaDonPhongDAO.getSoHDTheoMaKHTheoNgay(maKhachHang, ngayBatDau, ngayKetThuc);
					out.writeLong(soHD);
					out.flush();
					break;
				case "getDSKhachHangTheoNgay":
					LocalDate ngayBatDau2 = (LocalDate) in.readObject();
					LocalDate ngayKetThuc2 = (LocalDate) in.readObject();
					ArrayList<KhachHang> dsKH = hoaDonPhongDAO.getDSKhachHangTheoNgay(ngayBatDau2, ngayKetThuc2);
					out.writeObject(dsKH);
					out.flush();
					break;
				// getDSNhanVienTheoNgay
				case "getDSNhanVienTheoNgay":
					LocalDate ngayBatDau3 = (LocalDate) in.readObject();
					LocalDate ngayKetThuc3 = (LocalDate) in.readObject();
					ArrayList<NhanVien> dsNV = hoaDonPhongDAO.getDSNhanVienTheoNgay(ngayBatDau3, ngayKetThuc3);
					out.writeObject(dsNV);
					out.flush();
					break;
					// getDSPhongTheoNgay
				case "getDSPhongTheoNgay":
					LocalDate ngayBatDau4 = (LocalDate) in.readObject();
					LocalDate ngayKetThuc4 = (LocalDate) in.readObject();
					ArrayList<Phong> dsPhongg = hoaDonPhongDAO.getDSPhongTheoNgay(ngayBatDau4, ngayKetThuc4);
					out.writeObject(dsPhongg);
					out.flush();
					break;
				// getDSPhongTheoDSMaPhong
				case "getDSPhongTheoDSMaPhong":
					List<Integer> dsMaPhong = (List<Integer>) in.readObject();
					ArrayList<Phong> dsPhongg2 = hoaDonPhongDAO.getDSPhongTheoDSMaPhong(dsMaPhong);
					out.writeObject(dsPhongg2);
					out.flush();
					break;
				// getDSKHTheoDSMaKhachHang
				case "getDSKHTheoDSMaKhachHang":
					List<Integer> dsMaKH = (List<Integer>) in.readObject();
					ArrayList<KhachHang> dsKH2 = hoaDonPhongDAO.getDSKHTheoDSMaKhachHang(dsMaKH);
					out.writeObject(dsKH2);
					out.flush();
					break;
				// getDSNVTheoDSMaNV
				case "getDSNVTheoDSMaNV":
					List<Integer> maNV = (List<Integer>) in.readObject();
					ArrayList<NhanVien> dsNV2 = hoaDonPhongDAO.getDSNVTheoDSMaNV(maNV);
					out.writeObject(dsNV2);
					out.flush();
					break;
				// tongSoNhanVienTheoNgay
				case "tongSoNhanVienTheoNgay":
					LocalDate ngayBatDau5 = (LocalDate) in.readObject();
					LocalDate ngayKetThuc5 = (LocalDate) in.readObject();
					long tongSoNV = hoaDonPhongDAO.tongSoNhanVienTheoNgay(ngayBatDau5, ngayKetThuc5);
					out.writeLong(tongSoNV);
					out.flush();
					break;
				// tongSoPhongTheoNgay
				case "tongSoPhongTheoNgay":
					LocalDate ngayBatDau6 = (LocalDate) in.readObject();
					LocalDate ngayKetThuc6 = (LocalDate) in.readObject();
					long tongSoPhong = hoaDonPhongDAO.tongSoPhongTheoNgay(ngayBatDau6, ngayKetThuc6);
					out.writeLong(tongSoPhong);
					out.flush();
					break;
				//getDSHDTheoNgay
				case "getDSHDTheoNgay":
					LocalDate ngayBatDau7 = (LocalDate) in.readObject();
					LocalDate ngayKetThuc7 = (LocalDate) in.readObject();
					ArrayList<HoaDonPhong> dsHD = hoaDonPhongDAO.getDSHDTheoNgay(ngayBatDau7, ngayKetThuc7);
					out.writeObject(dsHD);
					out.flush();
					break;
				//tinhTongTienHDTheoNgay
				case "tinhTongTienHDTheoNgay":
					LocalDate ngayBatDau8 = (LocalDate) in.readObject();
					LocalDate ngayKetThuc8 = (LocalDate) in.readObject();
					double tongTien = hoaDonPhongDAO.tinhTongTienHDTheoNgay(ngayBatDau8, ngayKetThuc8);
					out.writeDouble(tongTien);
					out.flush();
					break;
				//tinhTongTienTheoMaKHTheoNgay
				case "tinhTongTienTheoMaKHTheoNgay":
					int maKH2 = in.readInt();
					LocalDate ngayBatDau9 = (LocalDate) in.readObject();
					LocalDate ngayKetThuc9 = (LocalDate) in.readObject();
					double tongTien2 = hoaDonPhongDAO.tinhTongTienTheoMaKHTheoNgay(maKH2, ngayBatDau9, ngayKetThuc9);
					out.writeDouble(tongTien2);
					out.flush();
					break;
				//getDSDichVuTheoNgay
				case "getDSDichVuTheoNgay":
					LocalDate ngayBatDau10 = (LocalDate) in.readObject();
					LocalDate ngayKetThuc10 = (LocalDate) in.readObject();
					ArrayList<DichVu> dsDV = hoaDonPhongDAO.getDSDichVuTheoNgay(ngayBatDau10, ngayKetThuc10);
					out.writeObject(dsDV);
					out.flush();
					break;
				//tongSoKHTheoNgay
				case "tongSoKHTheoNgay":
					LocalDate ngayBatDau11 = (LocalDate) in.readObject();
					LocalDate ngayKetThuc11 = (LocalDate) in.readObject();
					long tongSoKH = hoaDonPhongDAO.tongSoKHTheoNgay(ngayBatDau11, ngayKetThuc11);
					out.writeLong(tongSoKH);
					out.flush();
					break;
				//getTongSoDichVuTheoMaTheoNgay
				case "getTongSoDichVuTheoMaTheoNgay":
					int maDV = in.readInt();
					LocalDate ngayBatDau12 = (LocalDate) in.readObject();
					LocalDate ngayKetThuc12 = (LocalDate) in.readObject();
					int tongSoDV = hoaDonPhongDAO.getTongSoDichVuTheoMaTheoNgay(maDV, ngayBatDau12, ngayKetThuc12);
					out.writeInt(tongSoDV);
					out.flush();
					break;
				//getDSHD
				case "getDSHD":
					List<HoaDonPhong> dsHD2 = hoaDonPhongDAO.getDSHD();
					out.writeObject(dsHD2);
					out.flush();
					break;
				//getSoLanDichVuDatNNTrongNgay
				case "getSoLanDichVuDatNNTrongNgay":
					LocalDate ngayBatDau13 = (LocalDate) in.readObject();
					LocalDate ngayKetThuc13 = (LocalDate) in.readObject();
					DichVu dv3 = hoaDonPhongDAO.getSoLanDichVuDatNNTrongNgay(ngayBatDau13, ngayKetThuc13);
					out.writeObject(dv3);
					out.flush();
					break;
				//getDSHDThue
				case "getDSHDThue":
					ArrayList<HoaDonPhong> dsHDThue = hoaDonPhongDAO.getDSHDThue();
					out.writeObject(dsHDThue);
					out.flush();
					break;
				//getDSHoaDonThuePhongTheoMaKH
				case "getDSHoaDonThuePhongTheoMaKH":
					int maKH3 = in.readInt();
					ArrayList<HoaDonPhong> dsHDThue2 = hoaDonPhongDAO.getDSHoaDonThuePhongTheoMaKH(maKH3);
					out.writeObject(dsHDThue2);
					out.flush();
					break;
				//getDSHoaDonDatPhongTheoMaKH
				case "getDSHoaDonDatPhongTheoMaKH":
					int maKH4 = in.readInt();
					ArrayList<HoaDonPhong> dsHDDat = hoaDonPhongDAO.getDSHoaDonDatPhongTheoMaKH(maKH4);
					out.writeObject(dsHDDat);
					out.flush();
					break;
				//tongTienDVTheoNgay
				case "tongTienDVTheoNgay":
					LocalDate ngayBatDau14 = (LocalDate) in.readObject();
					LocalDate ngayKetThuc14 = (LocalDate) in.readObject();
					Map<Integer, Double> map = hoaDonPhongDAO.tongTienDVTheoNgay(ngayBatDau14, ngayKetThuc14);
					out.writeObject(map);
					out.flush();
					break;
				//getAllRoomStatusByDate
				case "getAllRoomStatusByDate":
					ArrayList<HoaDonPhong> dsHDPhong = hoaDonPhongDAO.getAllRoomStatusByDate();
					out.writeObject(dsHDPhong);
					out.flush();
					break;
				//themHoaDonDat
				case "themHoaDonDat":
					HoaDonPhong hdp = (HoaDonPhong) in.readObject();
					boolean result10 = hoaDonPhongDAO.themHoaDonDat(hdp);
					out.writeBoolean(result10);
					out.flush();
					break;
				//getHoaDonById
				case "getHoaDonById":
					int maHoaDon = in.readInt();
					HoaDonPhong hdp2 = hoaDonPhongDAO.getHoaDonById(maHoaDon);
					out.writeObject(hdp2);
					out.flush();
					break;
				//huyDatPhong
				case "huyDatPhong":
					int maHoaDon2 = in.readInt();
					boolean result11 = hoaDonPhongDAO.huyDatPhong(maHoaDon2);
					out.writeBoolean(result11);
					out.flush();
					break;
	 */
	// getSoHDTheoMaKHTheoNgay
	public long getSoHDTheoMaKHTheoNgay(int maKhachHang, LocalDate ngayBatDau, LocalDate ngayKetThuc)
			throws IOException {
		out.writeUTF("getSoHDTheoMaKHTheoNgay");
		out.writeInt(maKhachHang);
		out.writeObject(ngayBatDau);
		out.writeObject(ngayKetThuc);
		out.flush();
		return in.readLong();
	}
	// getDSKhachHangTheoNgay
	public ArrayList<KhachHang> getDSKhachHangTheoNgay(LocalDate ngayBatDau, LocalDate ngayKetThuc) throws IOException, ClassNotFoundException {
		out.writeUTF("getDSKhachHangTheoNgay");
		out.writeObject(ngayBatDau);
		out.writeObject(ngayKetThuc);
		out.flush();
		return (ArrayList<KhachHang>) in.readObject();
	}
	// getDSNhanVienTheoNgay
	public ArrayList<NhanVien> getDSNhanVienTheoNgay(LocalDate ngayBatDau, LocalDate ngayKetThuc)
			throws IOException, ClassNotFoundException {
		out.writeUTF("getDSNhanVienTheoNgay");
		out.writeObject(ngayBatDau);
		out.writeObject(ngayKetThuc);
		out.flush();
		return (ArrayList<NhanVien>) in.readObject();
	}
	// getDSPhongTheoNgay
	public ArrayList<Phong> getDSPhongTheoNgay(LocalDate ngayBatDau, LocalDate ngayKetThuc)
			throws IOException, ClassNotFoundException {
		out.writeUTF("getDSPhongTheoNgay");
		out.writeObject(ngayBatDau);
		out.writeObject(ngayKetThuc);
		out.flush();
		return (ArrayList<Phong>) in.readObject();
	}
	// getDSPhongTheoDSMaPhong
	public ArrayList<Phong> getDSPhongTheoDSMaPhong(List<Integer> dsMaPhong)
			throws IOException, ClassNotFoundException {
		out.writeUTF("getDSPhongTheoDSMaPhong");
		out.writeObject(dsMaPhong);
		out.flush();
		return (ArrayList<Phong>) in.readObject();
	}
	// getDSKHTheoDSMaKhachHang
	public ArrayList<KhachHang> getDSKHTheoDSMaKhachHang(List<Integer> dsMaKH)
			throws IOException, ClassNotFoundException {
		out.writeUTF("getDSKHTheoDSMaKhachHang");
		out.writeObject(dsMaKH);
		out.flush();
		return (ArrayList<KhachHang>) in.readObject();
	}
	// getDSNVTheoDSMaNV
	public ArrayList<NhanVien> getDSNVTheoDSMaNV(List<Integer> maNV) throws IOException, ClassNotFoundException {
		out.writeUTF("getDSNVTheoDSMaNV");
		out.writeObject(maNV);
		out.flush();
		return (ArrayList<NhanVien>) in.readObject();
	}
	// tongSoNhanVienTheoNgay
	public long tongSoNhanVienTheoNgay(LocalDate ngayBatDau, LocalDate ngayKetThuc) throws IOException {
		out.writeUTF("tongSoNhanVienTheoNgay");
		out.writeObject(ngayBatDau);
		out.writeObject(ngayKetThuc);
		out.flush();
		return in.readLong();
	}
	// tongSoPhongTheoNgay
	public long tongSoPhongTheoNgay(LocalDate ngayBatDau, LocalDate ngayKetThuc) throws IOException {
		out.writeUTF("tongSoPhongTheoNgay");
		out.writeObject(ngayBatDau);
		out.writeObject(ngayKetThuc);
		out.flush();
		return in.readLong();
	}
	// getDSHDTHeoNgay	
	public ArrayList<HoaDonPhong> getDSHDTheoNgay(LocalDate ngayBatDau, LocalDate ngayKetThuc)
			throws IOException, ClassNotFoundException {
		out.writeUTF("getDSHDTheoNgay");
		out.writeObject(ngayBatDau);
		out.writeObject(ngayKetThuc);
	
		return (ArrayList<HoaDonPhong>) in.readObject();
	}
	// tinhTongTienHDTheoNgay
	public double tinhTongTienHDTheoNgay(LocalDate ngayBatDau, LocalDate ngayKetThuc) throws IOException {
		out.writeUTF("tinhTongTienHDTheoNgay");
		out.writeObject(ngayBatDau);
		out.writeObject(ngayKetThuc);
		out.flush();
		return in.readDouble();
	}
	// tinhTongTienTheoMaKHTheoNgay
	public double tinhTongTienTheoMaKHTheoNgay(int maKH2, LocalDate ngayBatDau, LocalDate ngayKetThuc) throws IOException {
        out.writeUTF("tinhTongTienTheoMaKHTheoNgay");
        out.writeInt(maKH2);
        out.writeObject(ngayBatDau);
        out.writeObject(ngayKetThuc);
        out.flush();
        return in.readDouble();
	}
	// getDSDichVuTheoNgay
	public ArrayList<DichVu> getDSDichVuTheoNgay(LocalDate ngayBatDau, LocalDate ngayKetThuc)
			throws IOException, ClassNotFoundException {
		out.writeUTF("getDSDichVuTheoNgay");
		out.writeObject(ngayBatDau);
		out.writeObject(ngayKetThuc);
		out.flush();
		return (ArrayList<DichVu>) in.readObject();
	}
	// tongSoKHTheoNgay	
	public long tongSoKHTheoNgay(LocalDate ngayBatDau, LocalDate ngayKetThuc) throws IOException {
		out.writeUTF("tongSoKHTheoNgay");
		out.writeObject(ngayBatDau);
		out.writeObject(ngayKetThuc);
		out.flush();
		return in.readLong();
	}
	// getTongSoDichVuTheoMaTheoNgay
	public int getTongSoDichVuTheoMaTheoNgay(int maDV, LocalDate ngayBatDau, LocalDate ngayKetThuc) throws IOException {
		out.writeUTF("getTongSoDichVuTheoMaTheoNgay");
		out.writeInt(maDV);
		out.writeObject(ngayBatDau);
		out.writeObject(ngayKetThuc);
		out.flush();
		return in.readInt();
	}
	// getDSHD
	public List<HoaDonPhong> getDSHD() throws IOException, ClassNotFoundException {
		out.writeUTF("getDSHD");
		out.flush();
		return (List<HoaDonPhong>) in.readObject();
	}
	// getSoLanDichVuDatNNTrongNgay
	public DichVu getSoLanDichVuDatNNTrongNgay(LocalDate ngayBatDau, LocalDate ngayKetThuc)
			throws IOException, ClassNotFoundException {
		out.writeUTF("getSoLanDichVuDatNNTrongNgay");
		out.writeObject(ngayBatDau);
		out.writeObject(ngayKetThuc);
		out.flush();
		return (DichVu) in.readObject();
	}
	// getDSHDThue
	public ArrayList<HoaDonPhong> getDSHDThue() throws IOException, ClassNotFoundException {
		out.writeUTF("getDSHDThue");
		out.flush();
		return (ArrayList<HoaDonPhong>) in.readObject();
	}
	// getDSHoaDonThuePhongTheoMaKH
	public ArrayList<HoaDonPhong> getDSHoaDonThuePhongTheoMaKH(int maKH3) throws IOException, ClassNotFoundException {
		out.writeUTF("getDSHoaDonThuePhongTheoMaKH");
		out.writeInt(maKH3);
		out.flush();
		return (ArrayList<HoaDonPhong>) in.readObject();
	}
	// getDSHoaDonDatPhongTheoMaKH
	public ArrayList<HoaDonPhong> getDSHoaDonDatPhongTheoMaKH(int maKH4) throws IOException, ClassNotFoundException {
		out.writeUTF("getDSHoaDonDatPhongTheoMaKH");
		out.writeInt(maKH4);
		out.flush();
		return (ArrayList<HoaDonPhong>) in.readObject();
	}
	// tongTienDVTheoNgay
	public Map<Integer, Double> tongTienDVTheoNgay(LocalDate ngayBatDau, LocalDate ngayKetThuc)
			throws IOException, ClassNotFoundException {
		out.writeUTF("tongTienDVTheoNgay");
		out.writeObject(ngayBatDau);
		out.writeObject(ngayKetThuc);
		out.flush();
		return (Map<Integer, Double>) in.readObject();
	}
	// getAllRoomStatusByDate
	public ArrayList<HoaDonPhong> getAllRoomStatusByDate() throws IOException, ClassNotFoundException {
		out.writeUTF("getAllRoomStatusByDate");
		out.flush();
		return (ArrayList<HoaDonPhong>) in.readObject();
	}
	// themHoaDonDat
	public boolean themHoaDonDat(HoaDonPhong hdp) throws IOException {
		out.writeUTF("themHoaDonDat");
		out.writeObject(hdp);
		out.flush();
		return in.readBoolean();
	}
	// getHoaDonById
	public HoaDonPhong getHoaDonById(int maHoaDon) throws IOException, ClassNotFoundException {
		out.writeUTF("getHoaDonById");
		out.writeInt(maHoaDon);
		out.flush();
		return (HoaDonPhong) in.readObject();
	}
	// huyDatPhong
	public boolean huyDatPhong(int maHoaDon2) throws IOException {
		out.writeUTF("huyDatPhong");
		out.writeInt(maHoaDon2);
		out.flush();
		return in.readBoolean();
	}
	
	public static void main(String[] args) {
		try {
            Client_HoaDonDao client = new Client_HoaDonDao();
            Client_KhachHangDao clientKH = new Client_KhachHangDao();
            // getSoHDTheoMaKHTheoNgay
            HoaDonPhong hdp =client.getHoaDonById(2);
//            ArrayList<HoaDonPhong> dsHD = (ArrayList<HoaDonPhong>) client.getDSHD();
//            System.out.println(dsHD);
//           System.out.println(hdp);
//            KhachHang kh = clientKH.getKhachHangTheoMa(1);
           ArrayList<HoaDonPhong> dsHDThue = (ArrayList<HoaDonPhong>) client.getDSHDThue();
           for (HoaDonPhong hoaDonPhong : dsHDThue) {
//        	   System.out.println(hoaDonPhong.getMaKhachHang().getSoDienThoai());
				
        	   KhachHang kh = clientKH.getKhachHangTheoMa(hoaDonPhong.getMaKhachHang().getMaKhachHang());
        	   System.out.println(kh.getSoDienThoai());
           }  
            // getDSKhachHangTheoNgay
//            System.out.println(client.getDSKhachHangTheoNgay(LocalDate.now(), LocalDate.now()));
	}catch (IOException | ClassNotFoundException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    }
	}
	
}
