package client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;

import entitys.DichVu;
import entitys.LoaiDichVu;

public class Client_DichVuDao {
	private static ObjectOutputStream out;
	private static ObjectInputStream in;

	public Client_DichVuDao() throws IOException {

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
	 * case "getDSLoaiDichVu": List<LoaiDichVu> dsLoaiDichVu =
	 * loaiDichVuDAO.getDSLoaiDichVu(); out.writeObject(dsLoaiDichVu); out.flush();
	 * break; case "themDichVu": DichVu dichVu = (DichVu) in.readObject(); boolean
	 * result3 = dichVuDAO.themDichVu(dichVu); out.writeBoolean(result3);
	 * out.flush(); break;
	 * 
	 * case "updateDichVu": DichVu dichVu2 = (DichVu) in.readObject(); boolean
	 * result4 = dichVuDAO.updateDichVu(dichVu2); out.writeBoolean(result4);
	 * out.flush(); break; case "getDSDichVu": List<DichVu> dsDichVu =
	 * dichVuDAO.getDSDichVu(); out.writeObject(dsDichVu); out.flush(); break; case
	 * "getDichVuTheoMa": int maDichVu = in.readInt(); DichVu dv =
	 * dichVuDAO.getDichVuTheoMa(maDichVu); out.writeObject(dv); out.flush(); break;
	 * case "getDSDichVuTheoLoaiDichVu": int idLoaiDichVu = in.readInt();
	 * List<DichVu> dsDichVu2 = dichVuDAO.getDSDichVuTheoLoaiDichVu(idLoaiDichVu);
	 * out.writeObject(dsDichVu2); out.flush(); break; case "getSLTTheoMa": int id2
	 * = in.readInt(); int slt = dichVuDAO.getSLTTheoMa(id2); out.writeInt(slt);
	 * out.flush(); break; case "getDonGiaTheoMa": int id3 = in.readInt(); float
	 * donGia = dichVuDAO.getDonGiaTheoMa(id3); out.writeFloat(donGia); out.flush();
	 * break; case "updateSLTTheoMa": int id4 = in.readInt(); int slt2 =
	 * in.readInt(); boolean result5 = dichVuDAO.updateSLTTheoMa(id4, slt2);
	 * out.writeBoolean(result5); out.flush(); break; case "getDichVuTheoTen":
	 * String ten = in.readUTF(); DichVu dv2 = dichVuDAO.getDichVuTheoTen(ten);
	 * out.writeObject(dv2); out.flush(); break; case "getMaDichVuTheoTen": String
	 * ten2 = in.readUTF(); int ma = dichVuDAO.getMaDichVuTheoTen(ten2);
	 * out.writeInt(ma); out.flush(); break;
	 * 
	 */
	// "getDSLoaiDichVu":
	public List<LoaiDichVu> getDSLKH() throws IOException, ClassNotFoundException {
		out.writeUTF("getDSLoaiDichVu");
		out.flush();
		List<LoaiDichVu> list = (List<LoaiDichVu>) in.readObject();
		return list;
	}

	// themDichVu
	public boolean themDichVu(DichVu dichVu) throws IOException, ClassNotFoundException {
		out.writeUTF("themDichVu");
		out.flush();
		out.writeObject(dichVu);
		out.flush();
		boolean result = in.readBoolean();
		System.out.println(result);
		return result;
	}

	// updateDichVu
	public boolean updateDichVu(DichVu dichVu) throws IOException, ClassNotFoundException {
		out.writeUTF("updateDichVu");
		out.flush();
		out.writeObject(dichVu);
		out.flush();
		boolean result = in.readBoolean();
		System.out.println(result);
		return result;
	}

	// getDSDichVu
	public List<DichVu> getDSDichVu() throws IOException, ClassNotFoundException {
		out.writeUTF("getDSDichVu");
		out.flush();
		List<DichVu> list = (List<DichVu>) in.readObject();
		return list;
	}

	// getDichVuTheoMa
	public DichVu getDichVuTheoMa(int maDichVu) throws IOException, ClassNotFoundException {
		out.writeUTF("getDichVuTheoMa");
		out.writeInt(maDichVu);
		out.flush();
		return (DichVu) in.readObject();
	}
	//getDSDichVuTheoLoaiDichVu
	public List<DichVu> getDSDichVuTheoLoaiDichVu(int idLoaiDichVu) throws IOException, ClassNotFoundException {
		out.writeUTF("getDSDichVuTheoLoaiDichVu");
		out.writeInt(idLoaiDichVu);
		out.flush();
		List<DichVu> list = (List<DichVu>) in.readObject();
		return list;
	}
	//getSLTTheoMa
	public int getSLTTheoMa(int id2) throws IOException {
		out.writeUTF("getSLTTheoMa");
		out.writeInt(id2);
		out.flush();
		return in.readInt();
	}
	//getDonGiaTheoMa
	public float getDonGiaTheoMa(int id3) throws IOException {
		out.writeUTF("getDonGiaTheoMa");
		out.writeInt(id3);
		out.flush();
		return in.readFloat();
	}
	//updateSLTTheoMa
	public boolean updateSLTTheoMa(int id4, int slt2) throws IOException {
		out.writeUTF("updateSLTTheoMa");
		out.writeInt(id4);
		out.writeInt(slt2);
		out.flush();
		return in.readBoolean();
	}
	//getDichVuTheoTen
	public DichVu getDichVuTheoTen(String ten) throws IOException, ClassNotFoundException {
		out.writeUTF("getDichVuTheoTen");
		out.writeUTF(ten);
		out.flush();
		return (DichVu) in.readObject();
	}
	//getMaDichVuTheoTen
	public int getMaDichVuTheoTen(String ten2) throws IOException {
		out.writeUTF("getMaDichVuTheoTen");
		out.writeUTF(ten2);
		out.flush();
		return in.readInt();
	}
	
	public static void main(String[] args) throws ClassNotFoundException, IOException {

		try {
			Client_DichVuDao client = new Client_DichVuDao();
			List<LoaiDichVu> list = client.getDSLKH();

			List<DichVu> lists = client.getDSDichVu();
			for (DichVu dichVu : lists) {
				System.out.println(dichVu);
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
            // Đảm bảo rằng ObjectOutputStream và ObjectInputStream được đóng
            if (out != null) {
                out.close();
            }
            if (in != null) {
                in.close();
            }
        }
	}
}
