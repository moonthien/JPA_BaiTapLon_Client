package client;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.CountDownLatch;

import entitys.KhachHang;
import entitys.LoaiKhachHang;

public class Client_KhachHangDao {
	
	private static ObjectOutputStream out ;
	private static ObjectInputStream in;

	
	public Client_KhachHangDao() throws  IOException {
	
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

	public List<KhachHang> getDSKH() throws IOException, ClassNotFoundException {
			out.writeUTF("getDSKH");
			out.flush();
			List<KhachHang> list = (List<KhachHang>) in.readObject();
			return list;
	}
	
	public List<LoaiKhachHang> getDSLKH() throws IOException, ClassNotFoundException {
		out.writeUTF("getDSLKH");
		out.flush();
		List<LoaiKhachHang> list = (List<LoaiKhachHang>) in.readObject();
		return list;
	}
	
	public LoaiKhachHang getLoaiKhachHang(int id) throws IOException, ClassNotFoundException {
		out.writeUTF("getLoaiKhachHang");
		out.writeInt(id);
		out.flush();
		return (LoaiKhachHang) in.readObject();
	}
	
	public boolean themKhachHang(KhachHang KhachHang) throws IOException, ClassNotFoundException {
		out.writeUTF("themKhachHang");
		out.flush();
		
		out.writeObject(KhachHang);
		out.flush();
		boolean result = in.readBoolean();
		System.out.println(result);
		return result;	
	}

	public boolean updateKhachHang(KhachHang KhachHang) throws IOException, ClassNotFoundException {
		out.writeUTF("updateKhachHang");
        out.flush();
        out.writeObject(KhachHang);
        out.flush();
        boolean result = in.readBoolean();
        System.out.println(result);
        return result;
        }
	public static void main(String[] args) throws ClassNotFoundException, IOException {
		
		try {
			Client_KhachHangDao client = new Client_KhachHangDao();
			List<LoaiKhachHang> list = client.getDSLKH();
			

			List<KhachHang> lists = client.getDSKH();
			for (KhachHang khachHang : lists) {
				System.out.println(khachHang);
				
				client.updateKhachHang(khachHang);
			}
			
			LoaiKhachHang kh = client.getLoaiKhachHang(1);
			System.out.println("---------------------------------------------------------------");
			System.out.println(kh);
			
			
		} catch (IOException e) {
			// TODO: handle exception
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
		
//		List<KhachHang> lists = client.getDSKH();
//		for (KhachHang khachHang : lists) {
//			System.out.println(khachHang);
//		}
	
		

		
		
	}
//	}
//	public static void main(String[] args) {
//		try (Socket client = new Socket("DESKTOP-R9M9IMC", 3482); Scanner sc = new Scanner(System.in);) {
//			DataOutputStream out = new DataOutputStream(client.getOutputStream());
//			ObjectInputStream in = new ObjectInputStream(client.getInputStream());
//
//			System.out.println("Connected to server");
//			int choose = 0;
//			while (true) {
//				System.out.println(
//						"Enter your choice: \n1. Search student enrollment in Course\n2. Search student enrollment in Year");
//				choose = sc.nextInt();
//				out.writeInt(choose);
//
//				switch (choose) {
//				case 1:
//					sc.nextLine();
//					System.out.println("Enter course title: ");
//					String title = sc.nextLine();
//					out.writeUTF(title);
//					out.flush();
//
//					// Nhận ds sinh viên từ server
//					List<KhachHang> KhachHang = (List<KhachHang>) in.readObject();
//					KhachHang.forEach(System.out::println);
//
//					break;
//				case 2:
//					
////					System.out.println("Enter enrollment year: ");
////					int year = sc.nextInt();
////					sc.nextLine();
////					
////					out.writeInt(year);
////					out.flush();
////
////					// Nhận ds sinh viên từ server theo năm đăng ký
////					List<Student> student2 = (List<Student>) in.readObject();
////					student2.forEach(System.out::println);
//					break;
//				}
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
}
