package com.aode.controller;

import java.io.IOException;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.aode.bean.Shop;
import com.aode.bean.User;
import com.aode.userDao.UserDao;
@WebServlet("/UserAction")
public class UserAction extends HttpServlet {
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
		
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setHeader("content-type", "text/html;charset=utf-8");
		String choose = request.getParameter("choose");
		if(choose.equals("regist")) {
			regist(request,response);
		} else if(choose.equals("login")) {
			login(request,response);
		} else if(choose.equals("deletCart")) {
			deletCart(request,response);
		} else if(choose.equals("selectShop")) {
			selectShop(request,response);
		} else if(choose.equals("addUserShop")) {
			addUserShop(request,response);
		} else if(choose.equals("udapteUserMessage")) {
			udapteUserMessage(request,response);
		}
	}

	//修改用户信息的方法
	private void udapteUserMessage(HttpServletRequest request, HttpServletResponse response) {
		int id = Integer.parseInt(request.getParameter("id"));
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String sex = request.getParameter("sex");
		String[] hobby = request.getParameterValues("hobby");
		String history01 = request.getParameter("history01");
		String history02 = request.getParameter("history02");
		User userMessage = new User();
		userMessage.setId(id);
		userMessage.setUsername(username);
		userMessage.setPassword(password);
		userMessage.setSex(sex);
		userMessage.setHobby(hobby);
		try {
			int i = UserDao.updateMessage(userMessage,history01,history02);
			if(i!=0) {
				request.getSession().setAttribute("username", username);
				request.getSession().setAttribute("password", password);
				response.sendRedirect(request.getContextPath()+"/userHome.jsp");
			}else {
				System.out.println("修改失败");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}


	//用户删除购物车的方法
	private void deletCart(HttpServletRequest request, HttpServletResponse response) {
		int userId = Integer.parseInt(request.getParameter("userId"));
		int shopId = Integer.parseInt(request.getParameter("shopId"));
		try {
			int i = UserDao.userDeletCart(userId, shopId);
			if(i!=0) {
				response.sendRedirect(request.getContextPath()+"/shopCart.jsp");
			}else {
				System.out.println("删除失败");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}


	//用户添加购物车的方法
	private void addUserShop(HttpServletRequest request, HttpServletResponse response) {
		int userId = Integer.parseInt(request.getParameter("userId"));
		int shopId = Integer.parseInt(request.getParameter("shopId"));
		try {
			int i = UserDao.userAddShop(userId, shopId);
			if(i!=0) {
				response.sendRedirect(request.getContextPath()+"/userShop.jsp");
			}else {
				System.out.println("加入失败");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}


	//用户查询商品的方法
	private void selectShop(HttpServletRequest request, HttpServletResponse response) {
		String shopName = request.getParameter("shopName");
		try {
			Shop[] shop = UserDao.selectShop(shopName);
			if(shop!=null) {
				request.getSession().setAttribute("UserShop", shop);
				response.sendRedirect(request.getContextPath()+"/userShop.jsp");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}



	//用户登录时调用的方法
	private void login(HttpServletRequest request, HttpServletResponse response) {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		boolean flag;
		try {
			if(UserDao.userLogin(username, password)!=null) {
				request.getSession().setAttribute("username", username);
				request.getSession().setAttribute("password", password);
				response.getWriter().write("欢迎" + username + "来到奥德商城");
				response.setHeader("refresh", "3;url="+request.getContextPath()+"/userHome.jsp");
			}else {
				response.getWriter().write("登录失败");
				response.setHeader("refresh", "3;url="+request.getContextPath()+"/main.jsp");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}


	//用户注册时调用的方法
//	private void regist(HttpServletRequest request, HttpServletResponse response) {
//		String username = request.getParameter("username");
//		String password = request.getParameter("password");
//		String sex = request.getParameter("sex");
//		String[] hobby = request.getParameterValues("hobby");
//		User userMessage = new User();
//		userMessage.setUsername(username);
//		userMessage.setPassword(password);
//		userMessage.setSex(sex);
//		userMessage.setHobby(hobby);
//		try {
//			int i = UserDao.addUser(userMessage);
//			if(i!=0) {
//				response.getWriter().write("注册成功");
//				response.setHeader("refresh", "3;url="+request.getContextPath()+"/main.jsp");
//				
//			}else {
//				response.getWriter().write("注册失败");
//				response.setHeader("refresh", "3;url="+request.getContextPath()+"/main.jsp");
//			}
//		}catch(Exception e) {
//			e.printStackTrace();
//		}
//		
//	}
	
	private void regist(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String sex = request.getParameter("sex");
		String[] hobby = request.getParameterValues("hobby");
		User userMessage = new User();
		userMessage.setUsername(username);
		userMessage.setPassword(password);
		userMessage.setSex(sex);
		userMessage.setHobby(hobby);
		List<User> userList = null;
		if(request.getSession().getAttribute("userList")!=null) {
			userList = (ArrayList) request.getSession().getAttribute("userList");
		}else {
			userList = new ArrayList();
		}
		userList.add(userMessage);
		request.getSession().setAttribute("userList",userList);
		response.getWriter().write("注册成功，待管理员审核");
		response.setHeader("refresh", "3;url="+request.getContextPath()+"/main.jsp");
	}

}
