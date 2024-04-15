package client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import entitys.ChucVu;
import entitys.NhanVien;

public class Client_NhanVienDao {
	private static ObjectOutputStream out ;
	private static ObjectInputStream in;

	
	public Client_NhanVienDao() throws  IOException {
	
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
	 * case "getALLRoleByID":
					int maChucVu = in.readInt();
					ChucVu chucVu = danhSachChucVu_DAO.getAllRoleByID(maChucVu);
					out.writeObject(chucVu);
					out.flush();
					break;
				case "getAllRole":
					ArrayList<ChucVu> chucVus = danhSachChucVu_DAO.getAllRole();
					out.writeObject(chucVus);
					out.flush();
					break;
				case "themNhanVien":
					NhanVien nhanVien = (NhanVien) in.readObject();
					boolean result6 = nhanVienDao.themNhanVien(nhanVien);
					out.writeBoolean(result6);
					out.flush();
					break;
				case "updateNhanVien":
					NhanVien nhanVien2 = (NhanVien) in.readObject();
					boolean result7 = nhanVienDao.updateNhanVien(nhanVien2);
					out.writeBoolean(result7);
					out.flush();
					break;
				case "getDSNhanVien":
					List<NhanVien> dsNhanVien = nhanVienDao.getDSNhanVien();
					out.writeObject(dsNhanVien);
					out.flush();
					break;
				case "getNhanVienTheoMa":
					int id5 = in.readInt();
					NhanVien nv = nhanVienDao.getNhanVienTheoMa(id5);
					out.writeObject(nv);
					out.flush();
					break;
				case "getNhanVienTheoSDT":
					String sdt = in.readUTF();
					NhanVien nv2 = nhanVienDao.getNhanVienTheoSDT(sdt);
					out.writeObject(nv2);
					out.flush();
					break;
				
	 */
	public boolean themNhanVien(NhanVien nhanVien) throws IOException, ClassNotFoundException {
		out.writeUTF("themNhanVien");
		out.flush();
		out.writeObject(nhanVien);
		out.flush();
		return in.readBoolean();
	}
	//"getALLRoleByID"
	public ChucVu getAllRoleByID(int maChucVu) throws IOException, ClassNotFoundException {
		out.writeUTF("getALLRoleByID");
		out.flush();
		out.writeInt(maChucVu);
		out.flush();
		return (ChucVu) in.readObject();
	}
	//"getAllRole"
	public List<ChucVu> getAllRole() throws IOException, ClassNotFoundException {
		out.writeUTF("getAllRole");
		out.flush();
		return  (List<ChucVu>) in.readObject();
	}
	//"updateNhanVien"
	public boolean updateNhanVien(NhanVien nhanVien) throws IOException, ClassNotFoundException {
		out.writeUTF("updateNhanVien");
		out.flush();
		out.writeObject(nhanVien);
		out.flush();
		return in.readBoolean();
	}
	//"getDSNhanVien"
	public List<NhanVien> getDSNhanVien() throws IOException, ClassNotFoundException {
		out.writeUTF("getDSNhanVien");
		out.flush();
		return (List<NhanVien>) in.readObject();
	}
	//"getNhanVienTheoMa"
	public NhanVien getNhanVienTheoMa(int id) throws IOException, ClassNotFoundException {
		out.writeUTF("getNhanVienTheoMa");
		out.flush();
		out.writeInt(id);
		out.flush();
		return (NhanVien) in.readObject();
	}
	//"getNhanVienTheoSDT"
	public NhanVien getNhanVienTheoSDT(String sdt) throws IOException, ClassNotFoundException {
		out.writeUTF("getNhanVienTheoSDT");
		out.flush();
		out.writeUTF(sdt);
		out.flush();
		return (NhanVien) in.readObject();
	}
	public static void main(String[] args) {
		try {
			Client_NhanVienDao clientNV = new Client_NhanVienDao();
			ChucVu chucVu = clientNV.getAllRoleByID(1);
			ChucVu chucVu1 = clientNV.getAllRoleByID(2);
//			NhanVien nhanVien1 = new NhanVien("Nguyen Van A", "0123456789", "Ha Noi", "123456", true, chucVu,LocalDate.now(), true);
//			NhanVien nhanVien2 = new NhanVien("Nguyen Van B", "01148756789", "SG", "123456", true, chucVu,LocalDate.now(), true);
//			NhanVien nhanVien3= new NhanVien("Nguyen Van C", "098755689", "PY", "123456", true, chucVu1,LocalDate.now(), true);
//			NhanVien nhanVien4= new NhanVien("Nguyen Van D", "012155489", " Noi", "123456", true, chucVu1,LocalDate.now(), true);
//			clientNV.themNhanVien(nhanVien1);
//			clientNV.themNhanVien(nhanVien2);
//			clientNV.themNhanVien(nhanVien3);
//			clientNV.themNhanVien(nhanVien4);
		
			List<NhanVien> dsNhanVien = clientNV.getDSNhanVien();
			List<ChucVu> chucVus = clientNV.getAllRole();
			for (ChucVu nhanVien : chucVus) {
				System.out.println(nhanVien);
			}
			
		} catch (IOException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
