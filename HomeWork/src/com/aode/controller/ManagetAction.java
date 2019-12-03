package com.aode.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.aode.bean.Shop;
import com.aode.bean.User;
import com.aode.userDao.UserDao;
@WebServlet("/ManagerAction")
public class ManagetAction extends HttpServlet {

	private final String ACCOUNT = "78899455";
	private final String PASSWORD = "chj5548";
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setHeader("content-type", "text/html;charset=utf-8");
		String choose = request.getParameter("choose");
		if(choose.equals("login")) {
			login(request,response);
		}else if(choose.equals("audit")) {
			audit(request,response);
		}else if(choose.equals("addShop")) {
			addShop(request,response);
		}else if(choose.equals("deletShop")) {
			deletShop(request,response);
		}
	}
	
	//删除商品的方法
	private void deletShop(HttpServletRequest request, HttpServletResponse response) {
		int id = Integer.parseInt(request.getParameter("id"));
		try {
			int i = UserDao.managerDeletShop(id);
			if(i!=0) {
				response.sendRedirect(request.getContextPath()+"/shop.jsp");
			}else {
				System.out.println("下架失败");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}


	//管理员添加商品的方法
	private void addShop(HttpServletRequest request, HttpServletResponse response) {
		String shopName = request.getParameter("shopName");
		double prvice = Double.parseDouble(request.getParameter("prvice"));
		Date date = new Date();  
        System.out.println(date);  
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");  
        String time = sdf.format(date);
        Shop shop = new Shop(shopName,prvice,time);
        try {
        	int i = UserDao.addShop(shop);
        	if(i!=0) {
        		System.out.println("添加成功");
        		response.sendRedirect(request.getContextPath()+"/shop.jsp");
        	}
        	else {
        		System.out.println("添加失败");
        	}
        }catch(Exception e) {
        	e.printStackTrace();
        }
	}


	//审核用户的方法
	private void audit(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String flag = request.getParameter("flag");
		int id = Integer.parseInt(request.getParameter("id"));
		List<User> userList = (ArrayList)request.getSession().getAttribute("userList");
		if(flag.equals("1")) {
			try {
				int i = UserDao.addUser(userList.get(id));
				if(i!=0) {
					System.out.println("审核成功");
//					response.setHeader("refresh", "3;url="+request.getContextPath()+"/managerHome.jsp");
					response.sendRedirect(request.getContextPath()+"/managerHome.jsp");
					
				}else {
					System.out.println("审核失败");
//					response.setHeader("refresh", "0;url="+request.getContextPath()+"/managerHome.jsp");
					response.sendRedirect(request.getContextPath()+"/managerHome.jsp");
				}
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				userList.remove(id);
				request.getSession().setAttribute("userList", userList);
			}
		}else {
			userList.remove(id);
			request.getSession().setAttribute("userList", userList);
//			response.setHeader("refresh", "3;url="+request.getContextPath()+"/managerHome.jsp");
			response.sendRedirect(request.getContextPath()+"/managerHome.jsp");
		}
		
	}

	//管理员登录的方法
	private void login(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String account = request.getParameter("account");
		String password = request.getParameter("password");
		if(account.equals(ACCOUNT)&&password.equals(PASSWORD)) {
//			response.getWriter().write("欢迎管理员");
//			response.setHeader("refresh", "3;url="+request.getContextPath()+"/managerHome.jsp");
			response.sendRedirect(request.getContextPath()+"/managerHome.jsp");
		}else {
			response.getWriter().write("请你要点脸，不要冒充管理员");
			response.setHeader("refresh", "3;url="+request.getContextPath()+"/managerRegist.jsp");
		}
		
	}

}
