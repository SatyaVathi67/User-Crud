package com.sritech.user.controller;

import java.io.IOException;
import java.util.List;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sritech.user.UserDAOimpl.UserDAOimpl;
import com.sritech.user.dao.UserDAO;
import com.sritech.user.model.User;

@WebServlet("/")
public class UserServlet extends HttpServlet {

	private UserDAO udi = null;

	@Override
	public void init(ServletConfig config) throws ServletException {

		udi = new UserDAOimpl();

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		System.out.println(" enter into dopost");

		try {

			String action = request.getServletPath();

			System.out.println(action);

			switch (action) {

			case "/new":

				newUsersList(request, response);

				break;

			case "/insert":

				newInsertList(request, response);
				break;

			case "/edit":

				userEditList(request, response);

				break;

			case "/update":
				updateUserList(request, response);
				break;

			case "/delete":

				deleteUser(request, response);

				break;

			default:

				System.out.println("default switch case");

				showUsersList(request, response);

			}

		}

		catch (Exception e) {

			e.printStackTrace();

		}

	}

	private void deleteUser(HttpServletRequest request, HttpServletResponse response) throws IOException {

		int id = Integer.parseInt(request.getParameter("id"));
		udi.deleteUser(id);
		response.sendRedirect("list");

	}

	private void updateUserList(HttpServletRequest request, HttpServletResponse response) {

		try {

			int uid = Integer.parseInt(request.getParameter("id"));
			System.out.println(uid);

			String name = request.getParameter("name");
			System.out.println(name);

			String email = request.getParameter("email");

			String country = request.getParameter("country");

			User user = new User();

			user.setUserId(uid);
			user.setUserName(name);
			user.setUserEmail(email);
			user.setUserCountry(country);

			udi.updateUser(user);

			response.sendRedirect("list");

		}

		catch (Exception e) {

			e.printStackTrace();

		}

	}

	private void userEditList(HttpServletRequest request, HttpServletResponse response) {

		try {

			int userid = Integer.parseInt(request.getParameter("id"));

			User user = udi.editUser(userid);

			RequestDispatcher dispatcher = request.getRequestDispatcher("UserEditForm.jsp");

			request.setAttribute("user", user);
			dispatcher.include(request, response);

		}

		catch (Exception e) {

			e.printStackTrace();

		}
	}

	private void newInsertList(HttpServletRequest request, HttpServletResponse response) {

		try {

			User user = new User();

			user.setUserName(request.getParameter("name"));
			user.setUserEmail(request.getParameter("email"));
			user.setUserCountry(request.getParameter("country"));

			udi.insertList(user);

			response.sendRedirect("list");

		} catch (Exception e) {

			e.printStackTrace();

		}

	}

	private void newUsersList(HttpServletRequest request, HttpServletResponse response) {

		try {

			RequestDispatcher dispatcher = request.getRequestDispatcher("UserForm.jsp");
			dispatcher.include(request, response);

		}

		catch (Exception e) {

			e.printStackTrace();

		}

	}

	public void showUsersList(HttpServletRequest request, HttpServletResponse response) {

		try {

			List<User> usersList = udi.getUsersList();

			System.out.println(usersList);

			request.setAttribute("list", usersList);

			RequestDispatcher requestDispatcher = request.getRequestDispatcher("UserList.jsp");

			requestDispatcher.include(request, response);

			// List<User> userlist = (List<User>) request.getAttribute("list");

		}

		catch (Exception e) {

			e.printStackTrace();

		}

	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);

		System.out.println("enter into doget");

	}

}
