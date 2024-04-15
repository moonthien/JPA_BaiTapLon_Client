package client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;

import entitys.LoaiPhong;
import entitys.Phong;
import entitys.TinhTrangPhong;

public class Client_PhongDao {
	private static ObjectOutputStream out ;
	private static ObjectInputStream in;

	
	public Client_PhongDao() throws  IOException {
	
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
	 * case "getLoaiPhongTheoMa":
					int id6 = in.readInt();
					LoaiPhong loaiPhong = loaiPhongDAO.getLoaiPhongTheoMa(id6);
					out.writeObject(loaiPhong);
					out.flush();
					break;
				case "getDSLoaiPhong":
					List<LoaiPhong> dsLoaiPhong = loaiPhongDAO.getDSLoaiPhong();
					out.writeObject(dsLoaiPhong);
					out.flush();
					break;
				case "getTinhTrangPhongTheoMa":
					int id7 = in.readInt();
					TinhTrangPhong tinhTrangPhong = tinhTrangPhongDAO.getTinhTrangPhongTheoMa(id7);
					out.writeObject(tinhTrangPhong);
					out.flush();
					break;

				case "getDSTinhTrangPhong":
					List<TinhTrangPhong> dsTinhTrangPhong = tinhTrangPhongDAO.getDSTinhTrangPhong();
					out.writeObject(dsTinhTrangPhong);
					out.flush();
					break;
				case "themPhong":
					Phong phong = (Phong) in.readObject();
					boolean result8 = phongDAO.themPhong(phong);
					out.writeBoolean(result8);
					out.flush();
					break;
				case "updatePhong":
					Phong phong2 = (Phong) in.readObject();
					boolean result9 = phongDAO.updatePhong(phong2);
					out.writeBoolean(result9);
					out.flush();
					break;
				case "getDSPhong":
					List<Phong> dsPhong = phongDAO.getDSPhong();
					out.writeObject(dsPhong);
					out.flush();
					break;
				case "getPhongTheoMa":
					int id8 = in.readInt();
					Phong p = phongDAO.getPhongTheoMa(id8);
					out.writeObject(p);
					out.flush();
					break;
				case "getDSPhongTheoMa":
					int id9 = in.readInt();
					List<Phong> dsPhong2 = phongDAO.getDSPhongTheoMa(id9);
					out.writeObject(dsPhong2);
					out.flush();
					break;
	 */
	//getLoaiPhongTheoMa
	public LoaiPhong getLoaiPhongTheoMa(int id) throws IOException, ClassNotFoundException {
		out.writeUTF("getLoaiPhongTheoMa");
		out.flush();
		out.writeInt(id);
		out.flush();
		return (LoaiPhong) in.readObject();
	}
	//getDSLoaiPhong
	public List<LoaiPhong> getDSLoaiPhong() throws IOException, ClassNotFoundException {
		out.writeUTF("getDSLoaiPhong");
		out.flush();
		return (List<LoaiPhong>) in.readObject();
	}
	//getTinhTrangPhongTheoMa
	public TinhTrangPhong getTinhTrangPhongTheoMa(int id) throws IOException, ClassNotFoundException {
		out.writeUTF("getTinhTrangPhongTheoMa");
		out.flush();
		out.writeInt(id);
		out.flush();
		return (TinhTrangPhong) in.readObject();
	}
	//getDSTinhTrangPhong
	public List<TinhTrangPhong> getDSTinhTrangPhong() throws IOException, ClassNotFoundException {
		out.writeUTF("getDSTinhTrangPhong");
		out.flush();
		return (List<TinhTrangPhong>) in.readObject();
	}
	//themPhong
	public boolean themPhong(Phong phong) throws IOException, ClassNotFoundException {
		out.writeUTF("themPhong");
		out.flush();
		out.writeObject(phong);
		out.flush();
		return in.readBoolean();
	}
	//updatePhong
	public boolean updatePhong(Phong phong) throws IOException, ClassNotFoundException {
		out.writeUTF("updatePhong");
		out.flush();
		out.writeObject(phong);
		out.flush();
		return in.readBoolean();
	}
	//getDSPhong
	public List<Phong> getDSPhong() throws IOException, ClassNotFoundException {
		out.writeUTF("getDSPhong");
		out.flush();
		return (List<Phong>) in.readObject();
	}
	//getPhongTheoMa
	public Phong getPhongTheoMa(int id) throws IOException, ClassNotFoundException {
		out.writeUTF("getPhongTheoMa");
		out.flush();
		out.writeInt(id);
		out.flush();
		return (Phong) in.readObject();
	}
	//getDSPhongTheoMa
	public List<Phong> getDSPhongTheoMa(int id) throws IOException, ClassNotFoundException {
		out.writeUTF("getDSPhongTheoMa");
		out.flush();
		out.writeInt(id);
		out.flush();
		return (List<Phong>) in.readObject();
	}
	
	public static void main(String[] args) {
		try {
			Client_PhongDao client_PhongDao = new Client_PhongDao();
//			LoaiPhong loaiPhong = client_PhongDao.getLoaiPhongTheoMa(1);
//			System.out.println(loaiPhong);
//			List<LoaiPhong> dsLoaiPhong = client_PhongDao.getDSLoaiPhong();
//			System.out.println(dsLoaiPhong);
			TinhTrangPhong tinhTrangPhong = client_PhongDao.getTinhTrangPhongTheoMa(1);
			System.out.println(tinhTrangPhong);
			List<TinhTrangPhong> dsTinhTrangPhong = client_PhongDao.getDSTinhTrangPhong();
			System.out.println(dsTinhTrangPhong);
			
			
//			Phong phong = client_PhongDao.getPhongTheoMa(1);
//			System.out.println(phong);
//			List<Phong> dsPhong = client_PhongDao.getDSPhong();
//			System.out.println(dsPhong);
			
		} catch (IOException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
