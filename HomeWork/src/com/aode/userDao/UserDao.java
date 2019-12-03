package com.aode.userDao;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.aode.bean.Shop;
import com.aode.bean.User;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

public class UserDao {
	//�������ݿ��url
	private static final String url = "jdbc:mysql://localhost:3306/jdbc";
	//�������ݿ���û���
	private static final String Jdbcname = "root";
	//�������ݿ������
	private static final String Jdbcpassword = "123456";
	//������
	static Connection conn = null;			
	//Ԥ������   Statement��
	static PreparedStatement pstmt = null;
	
	//�û���¼
	public static User userLogin(String username,String password) throws Exception{
		//����MySql������,����������ص�jvm��
		Class.forName("com.mysql.jdbc.Driver");
		User user = null;
		int i = 0;
		try {
		//ͨ��DriverManager���е�getConnection��̬��������һ������
		conn = (Connection)DriverManager.getConnection(url,Jdbcname, Jdbcpassword);
		//�����ѯ������sql���
		String sql = "select * from userMessage where username=? and password=?";
		//ʹ��PreparedStatment�����Ԥ����
		pstmt = (PreparedStatement) conn.prepareStatement(sql);
		//����PrepareStatement��setString()�����滻����sql����е��ʺ�
		pstmt.setString(1, username);
		pstmt.setString(2, password);
		ResultSet rs = pstmt.executeQuery(); //ִ�в�ѯ����������һ�����������
		sql =  "select * from hobby where username=? and password=?";
		pstmt = (PreparedStatement) conn.prepareStatement(sql);
		pstmt.setString(1, username);
		pstmt.setString(2, password);
		ResultSet rs01 = pstmt.executeQuery();
		if(rs.next()) {
			System.out.println("233333333333333333333333333333333");
			int id = rs.getInt("id");
			String username01  = rs.getString("username");
			String password01 = rs.getString("password");
			String sex = rs.getString("sex");
			String[] hobby = null;
			rs01.last();
			if(rs01.getRow()!=0) {
				hobby = new String[rs01.getRow()];
			}
			rs01.beforeFirst();
			while(rs01.next()) {
				hobby[i] = rs01.getString("hobby");
				System.out.println(hobby[i]);
				i++;
			}
			user = new User(id,username01,password01,sex,hobby);
		}else {
			System.out.println("��ѯʧ��");
		}
		}catch(Exception e) {
			e.printStackTrace();
		} finally {
			if(pstmt!=null)
				pstmt.close();//�ر�Ԥ������Դ
			if(conn!=null)
				conn.close();//�ر����ݿ�����
		}
		return user;
	}
	
	
	public static int addUser(User user) throws Exception {
		//����MySql������,����������ص�jvm��
		Class.forName("com.mysql.jdbc.Driver");
		int i = 0;
		try {
		//ͨ��DriverManager���е�getConnection��̬��������һ������
		conn = (Connection)DriverManager.getConnection(url,Jdbcname, Jdbcpassword);
		conn.setAutoCommit(false); //��������
		//�����ѯ������sql���
		String sql = "insert into userMessage (username,password,sex) values(?,?,?)";
		//ʹ��PreparedStatment�����Ԥ����
		pstmt = (PreparedStatement) conn.prepareStatement(sql);
		pstmt.setString(1, user.getUsername());
		pstmt.setString(2, user.getPassword());
		pstmt.setString(3, user.getSex());
		i = pstmt.executeUpdate();
		sql = "insert into hobby (username,password,hobby) values(?,?,?)";
		pstmt = (PreparedStatement) conn.prepareStatement(sql);
		//���û��İ��ò������
		for(int n = 0;n<user.getHobby().length;n++) {
			pstmt.setString(1, user.getUsername());
			pstmt.setString(2, user.getPassword());
			pstmt.setString(3, user.getHobby()[n]);
			pstmt.executeUpdate();
		}
		conn.commit(); //�ύ����
		}catch(Exception e) {
			e.printStackTrace();
			conn.rollback(); //�ع�����
		}finally {
			if(conn!=null)
			conn.close(); //�ر����ݿ�����
			if(pstmt!=null)
			pstmt.close(); //�ر�Ԥ������
			
		}
		return i;
	}
	
	//��ѯ��Ʒ�б�
	public static Shop[] selectBook() throws Exception {
		//����MySql������,����������ص�jvm��
		Class.forName("com.mysql.jdbc.Driver");
		int i = 0;
		Shop[] bookList = null;
		try {
		//ͨ��DriverManager���е�getConnection��̬��������һ������
		conn = (Connection)DriverManager.getConnection(url,Jdbcname, Jdbcpassword);
		//�����ѯ������sql���
		String sql = "select * from book";
		//ʹ��PreparedStatment�����Ԥ����
		pstmt = (PreparedStatement) conn.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		rs.last();
		if(rs.getRow()!=0) {
			bookList = new Shop[rs.getRow()];
		}
		rs.beforeFirst();
		if(rs!=null) {
			while(rs.next()) {
				int id = rs.getInt("id");
				String shopName = rs.getString("shopName");
				double prvice = rs.getDouble("prvice");
				String time = rs.getString("time");
				bookList[i] = new Shop(id,shopName,prvice,time);
				i++;
			}
		}
		
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(conn!=null)
				conn.close();
			if(pstmt!=null)
				pstmt.close();
		}
		return bookList;
	}
	
	public static int addShop(Shop shop) throws Exception {
		//����MySql������,����������ص�jvm��
		Class.forName("com.mysql.jdbc.Driver");
		int i = 0;
		try {
		//ͨ��DriverManager���е�getConnection��̬��������һ������
		conn = (Connection)DriverManager.getConnection(url,Jdbcname, Jdbcpassword);
		//�����ѯ������sql���
		String sql = "insert into book (shopName,prvice,time) values(?,?,?)";
		//ʹ��PreparedStatment�����Ԥ����
		pstmt = (PreparedStatement) conn.prepareStatement(sql);
		pstmt.setString(1, shop.getShopName());
		pstmt.setDouble(2, shop.getPrvice());
		pstmt.setString(3, shop.getTime());
		i = pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(conn!=null)
				conn.close();
			if(pstmt!=null)
				pstmt.close();
		}
		return i;
	}
	
	public static int userAddShop(int userId,int shopId) throws Exception {
		//����MySql������,����������ص�jvm��
		Class.forName("com.mysql.jdbc.Driver");
		int i = 0;
		try {
		//ͨ��DriverManager���е�getConnection��̬��������һ������
		conn = (Connection)DriverManager.getConnection(url,Jdbcname, Jdbcpassword);
		//�����ѯ������sql���
		String sql = "insert into shopCart (userId,shopId) values(?,?)";	
		pstmt = (PreparedStatement)conn.prepareStatement(sql);
		pstmt.setInt(1, userId);
		pstmt.setInt(2, shopId);
		i = pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(conn!=null)
				conn.close();
			if(pstmt!=null)
				pstmt.close();
		}
		return i;
	}
	
	//��ѯ��Ʒ�б�
		public static Shop[] selectShop(String shopName) throws Exception {
			//����MySql������,����������ص�jvm��
			Class.forName("com.mysql.jdbc.Driver");
			int i = 0;
			Shop[] bookList = null;
			try {
			//ͨ��DriverManager���е�getConnection��̬��������һ������
			conn = (Connection)DriverManager.getConnection(url,Jdbcname, Jdbcpassword);
			//�����ѯ������sql���
			String sql = "select * from book where shopName like ? ";
			//ʹ��PreparedStatment�����Ԥ����
			pstmt = (PreparedStatement) conn.prepareStatement(sql);
			pstmt.setString(1, "%"+shopName+"%");
			ResultSet rs = pstmt.executeQuery();
			rs.last();
			if(rs.getRow()!=0) {
				bookList = new Shop[rs.getRow()];
			}
			rs.beforeFirst();
			if(rs!=null) {
				while(rs.next()) {
					int id = rs.getInt("id");
					String shopName01 = rs.getString("shopName");
					double prvice = rs.getDouble("prvice");
					String time = rs.getString("time");
					bookList[i] = new Shop(id,shopName01,prvice,time);
					i++;
				}
			}
			
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				if(conn!=null)
					conn.close();
				if(pstmt!=null)
					pstmt.close();
			}
			return bookList;
		}
		
		public static Shop[] selectToCart(int userId) throws Exception {
			//����MySql������,����������ص�jvm��
			Class.forName("com.mysql.jdbc.Driver");
			int i = 0;
			Shop[] shopList = null;
			try {
			//ͨ��DriverManager���е�getConnection��̬��������һ������
			conn = (Connection)DriverManager.getConnection(url,Jdbcname, Jdbcpassword);
			//�����ѯ������sql���
			String sql = "select * from book where id in (select shopId from shopCart where userId = ?) ";
			//ʹ��PreparedStatment�����Ԥ����
			pstmt = (PreparedStatement) conn.prepareStatement(sql);
			pstmt.setInt(1, userId);
			ResultSet rs = pstmt.executeQuery();
			rs.last();
			if(rs.getRow()!=0) {
				shopList = new Shop[rs.getRow()];
			}
			rs.beforeFirst();
			if(rs!=null) {
				while(rs.next()) {
					int id = rs.getInt("id");
					String shopName01 = rs.getString("shopName");
					double prvice = rs.getDouble("prvice");
					String time = rs.getString("time");
					shopList[i] = new Shop(id,shopName01,prvice,time);
					i++;
				}
			}
			
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				if(conn!=null)
					conn.close();
				if(pstmt!=null)
					pstmt.close();
			}
			return shopList;
		}
		
		public static int userDeletCart(int userId,int shopId) throws Exception {
			//����MySql������,����������ص�jvm��
			Class.forName("com.mysql.jdbc.Driver");
			int i = 0;
			try {
			//ͨ��DriverManager���е�getConnection��̬��������һ������
			conn = (Connection)DriverManager.getConnection(url,Jdbcname, Jdbcpassword);
			//�����ѯ������sql���
			String sql = "delete from shopCart where userId=? and shopId=?";	
			pstmt = (PreparedStatement)conn.prepareStatement(sql);
			pstmt.setInt(1, userId);
			pstmt.setInt(2, shopId);
			i = pstmt.executeUpdate();
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				if(conn!=null)
					conn.close();
				if(pstmt!=null)
					pstmt.close();
			}
			return i;
		}
		
		public static int updateMessage(User userMessage,String history01,String history02) throws Exception {
			//����MySql������,����������ص�jvm��
			Class.forName("com.mysql.jdbc.Driver");
			int i = 0;
			try {
			//ͨ��DriverManager���е�getConnection��̬��������һ������
			conn = (Connection)DriverManager.getConnection(url,Jdbcname, Jdbcpassword);
			conn.setAutoCommit(false); //��������
			//�����ѯ������sql���
			String sql = "update usermessage set username=? , password=? , sex=? where id = ?";	
			pstmt = (PreparedStatement)conn.prepareStatement(sql);
			pstmt.setString(1, userMessage.getUsername());
			pstmt.setString(2, userMessage.getPassword());
			pstmt.setString(3, userMessage.getSex());
			pstmt.setInt(4, userMessage.getId());
			i = pstmt.executeUpdate();
			sql = "delete from hobby where username = ? and password = ?";
			pstmt = (PreparedStatement) conn.prepareStatement(sql);
			pstmt.setString(1, history01);
			pstmt.setString(2, history02);
			pstmt.executeUpdate();
			sql = "insert into hobby (username,password,hobby) values(?,?,?)";
			pstmt = (PreparedStatement) conn.prepareStatement(sql);
			//���û��İ��ò������
			for(int n = 0;n<userMessage.getHobby().length;n++) {
				pstmt.setString(1, userMessage.getUsername());
				pstmt.setString(2, userMessage.getPassword());
				pstmt.setString(3, userMessage.getHobby()[n]);
				pstmt.executeUpdate();
			}
			conn.commit(); //�ύ����
			}catch(Exception e) {
				e.printStackTrace();
				conn.rollback(); //�ع�����
			}finally {
				if(conn!=null)
					conn.close();
				if(pstmt!=null)
					pstmt.close();
			}
			return i;
		}
		
		public static int managerDeletShop(int id) throws Exception {
			//����MySql������,����������ص�jvm��
			Class.forName("com.mysql.jdbc.Driver");
			int i = 0;
			try {
			//ͨ��DriverManager���е�getConnection��̬��������һ������
			conn = (Connection)DriverManager.getConnection(url,Jdbcname, Jdbcpassword);
			//�����ѯ������sql���
			String sql = "delete from book where id=?";	
			pstmt = (PreparedStatement)conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			i = pstmt.executeUpdate();
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				if(conn!=null)
					conn.close();
				if(pstmt!=null)
					pstmt.close();
			}
			return i;
		}
}
