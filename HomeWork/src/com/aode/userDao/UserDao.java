package com.aode.userDao;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.aode.bean.Shop;
import com.aode.bean.User;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

public class UserDao {
	//连接数据库的url
	private static final String url = "jdbc:mysql://localhost:3306/jdbc";
	//连接数据库的用户名
	private static final String Jdbcname = "root";
	//连接数据库的密码
	private static final String Jdbcpassword = "123456";
	//连接类
	static Connection conn = null;			
	//预处理类   Statement类
	static PreparedStatement pstmt = null;
	
	//用户登录
	public static User userLogin(String username,String password) throws Exception{
		//加载MySql驱动类,将驱动类加载到jvm中
		Class.forName("com.mysql.jdbc.Driver");
		User user = null;
		int i = 0;
		try {
		//通过DriverManager类中的getConnection静态方法创建一个连接
		conn = (Connection)DriverManager.getConnection(url,Jdbcname, Jdbcpassword);
		//定义查询操作的sql语句
		String sql = "select * from userMessage where username=? and password=?";
		//使用PreparedStatment类进行预处理
		pstmt = (PreparedStatement) conn.prepareStatement(sql);
		//调用PrepareStatement的setString()方法替换上面sql语句中的问号
		pstmt.setString(1, username);
		pstmt.setString(2, password);
		ResultSet rs = pstmt.executeQuery(); //执行查询操作，返回一个结果集对象
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
			System.out.println("查询失败");
		}
		}catch(Exception e) {
			e.printStackTrace();
		} finally {
			if(pstmt!=null)
				pstmt.close();//关闭预处理资源
			if(conn!=null)
				conn.close();//关闭数据库连接
		}
		return user;
	}
	
	
	public static int addUser(User user) throws Exception {
		//加载MySql驱动类,将驱动类加载到jvm中
		Class.forName("com.mysql.jdbc.Driver");
		int i = 0;
		try {
		//通过DriverManager类中的getConnection静态方法创建一个连接
		conn = (Connection)DriverManager.getConnection(url,Jdbcname, Jdbcpassword);
		conn.setAutoCommit(false); //开启事务
		//定义查询操作的sql语句
		String sql = "insert into userMessage (username,password,sex) values(?,?,?)";
		//使用PreparedStatment类进行预处理
		pstmt = (PreparedStatement) conn.prepareStatement(sql);
		pstmt.setString(1, user.getUsername());
		pstmt.setString(2, user.getPassword());
		pstmt.setString(3, user.getSex());
		i = pstmt.executeUpdate();
		sql = "insert into hobby (username,password,hobby) values(?,?,?)";
		pstmt = (PreparedStatement) conn.prepareStatement(sql);
		//将用户的爱好插入表中
		for(int n = 0;n<user.getHobby().length;n++) {
			pstmt.setString(1, user.getUsername());
			pstmt.setString(2, user.getPassword());
			pstmt.setString(3, user.getHobby()[n]);
			pstmt.executeUpdate();
		}
		conn.commit(); //提交事务
		}catch(Exception e) {
			e.printStackTrace();
			conn.rollback(); //回滚事务
		}finally {
			if(conn!=null)
			conn.close(); //关闭数据库连接
			if(pstmt!=null)
			pstmt.close(); //关闭预处理类
			
		}
		return i;
	}
	
	//查询商品列表
	public static Shop[] selectBook() throws Exception {
		//加载MySql驱动类,将驱动类加载到jvm中
		Class.forName("com.mysql.jdbc.Driver");
		int i = 0;
		Shop[] bookList = null;
		try {
		//通过DriverManager类中的getConnection静态方法创建一个连接
		conn = (Connection)DriverManager.getConnection(url,Jdbcname, Jdbcpassword);
		//定义查询操作的sql语句
		String sql = "select * from book";
		//使用PreparedStatment类进行预处理
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
		//加载MySql驱动类,将驱动类加载到jvm中
		Class.forName("com.mysql.jdbc.Driver");
		int i = 0;
		try {
		//通过DriverManager类中的getConnection静态方法创建一个连接
		conn = (Connection)DriverManager.getConnection(url,Jdbcname, Jdbcpassword);
		//定义查询操作的sql语句
		String sql = "insert into book (shopName,prvice,time) values(?,?,?)";
		//使用PreparedStatment类进行预处理
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
		//加载MySql驱动类,将驱动类加载到jvm中
		Class.forName("com.mysql.jdbc.Driver");
		int i = 0;
		try {
		//通过DriverManager类中的getConnection静态方法创建一个连接
		conn = (Connection)DriverManager.getConnection(url,Jdbcname, Jdbcpassword);
		//定义查询操作的sql语句
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
	
	//查询商品列表
		public static Shop[] selectShop(String shopName) throws Exception {
			//加载MySql驱动类,将驱动类加载到jvm中
			Class.forName("com.mysql.jdbc.Driver");
			int i = 0;
			Shop[] bookList = null;
			try {
			//通过DriverManager类中的getConnection静态方法创建一个连接
			conn = (Connection)DriverManager.getConnection(url,Jdbcname, Jdbcpassword);
			//定义查询操作的sql语句
			String sql = "select * from book where shopName like ? ";
			//使用PreparedStatment类进行预处理
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
			//加载MySql驱动类,将驱动类加载到jvm中
			Class.forName("com.mysql.jdbc.Driver");
			int i = 0;
			Shop[] shopList = null;
			try {
			//通过DriverManager类中的getConnection静态方法创建一个连接
			conn = (Connection)DriverManager.getConnection(url,Jdbcname, Jdbcpassword);
			//定义查询操作的sql语句
			String sql = "select * from book where id in (select shopId from shopCart where userId = ?) ";
			//使用PreparedStatment类进行预处理
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
			//加载MySql驱动类,将驱动类加载到jvm中
			Class.forName("com.mysql.jdbc.Driver");
			int i = 0;
			try {
			//通过DriverManager类中的getConnection静态方法创建一个连接
			conn = (Connection)DriverManager.getConnection(url,Jdbcname, Jdbcpassword);
			//定义查询操作的sql语句
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
			//加载MySql驱动类,将驱动类加载到jvm中
			Class.forName("com.mysql.jdbc.Driver");
			int i = 0;
			try {
			//通过DriverManager类中的getConnection静态方法创建一个连接
			conn = (Connection)DriverManager.getConnection(url,Jdbcname, Jdbcpassword);
			conn.setAutoCommit(false); //开启事务
			//定义查询操作的sql语句
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
			//将用户的爱好插入表中
			for(int n = 0;n<userMessage.getHobby().length;n++) {
				pstmt.setString(1, userMessage.getUsername());
				pstmt.setString(2, userMessage.getPassword());
				pstmt.setString(3, userMessage.getHobby()[n]);
				pstmt.executeUpdate();
			}
			conn.commit(); //提交事务
			}catch(Exception e) {
				e.printStackTrace();
				conn.rollback(); //回滚事务
			}finally {
				if(conn!=null)
					conn.close();
				if(pstmt!=null)
					pstmt.close();
			}
			return i;
		}
		
		public static int managerDeletShop(int id) throws Exception {
			//加载MySql驱动类,将驱动类加载到jvm中
			Class.forName("com.mysql.jdbc.Driver");
			int i = 0;
			try {
			//通过DriverManager类中的getConnection静态方法创建一个连接
			conn = (Connection)DriverManager.getConnection(url,Jdbcname, Jdbcpassword);
			//定义查询操作的sql语句
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
