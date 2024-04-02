package com.sritech.user.UserDAOimpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.sritech.user.dao.UserDAO;
import com.sritech.user.model.User;

public class UserDAOimpl implements UserDAO {

	private String USER_DRIVER = "com.mysql.cj.jdbc.Driver";

	private String url = "jdbc:mysql://localhost:3306/dev?useSSL=false";
	private String uid = "root";
	private String pwd = "root";

	private static final String SELECT_USER = "SELECT* FROM USERS";

	private static final String INSERT_USER = " INSERT INTO USERS(name,email,country ) VALUES(?,?,?)";

	private static final String SELECT_USER_ID = "SELECT* FROM users WHERE id=?";
	private static final String UPDATE_USER = "update users set name=?,email=?,country=? where id=?;";
	private static final String DELETE_USER = "delete from users where id=?;";

	private Connection getConnection() throws SQLException {
		Connection connection = null;
		try {
			Class.forName(USER_DRIVER);
			connection = DriverManager.getConnection(url, uid, pwd);
		} catch (SQLException e) {

			e.printStackTrace();
		} catch (ClassNotFoundException e) {

			e.printStackTrace();
		}

		return connection;
	}

	public List<User> getUsersList() {

		List<User> list = null;

		try (Connection connection = getConnection();) {

			list = new ArrayList<User>();

			PreparedStatement prepareStatement = connection.prepareStatement(SELECT_USER);

			ResultSet resultSet = prepareStatement.executeQuery();

			while (resultSet.next()) {

				User user = new User();
				user.setUserId(resultSet.getInt(1));
				user.setUserName(resultSet.getString(2));
				user.setUserEmail(resultSet.getString(3));
				user.setUserCountry(resultSet.getString(4));

				list.add(user);

			}

		}

		catch (Exception e) {

			e.printStackTrace();

		}
		return list;

	}

	public void insertList(User user) {

		try (Connection connection = getConnection();

		) {

			PreparedStatement prepareStatement = connection.prepareStatement(INSERT_USER);

			prepareStatement.setString(1, user.getUserName());
			prepareStatement.setString(2, user.getUserEmail());
			prepareStatement.setString(3, user.getUserCountry());
			prepareStatement.executeUpdate();

		}

		catch (Exception e) {

			e.printStackTrace();

		}

	}

	public User editUser(int userid) {

		User user = null;

		try (Connection connection = getConnection();) {

			PreparedStatement prepareStatement = connection.prepareStatement(SELECT_USER_ID);

			prepareStatement.setInt(1, userid);
			ResultSet resultSet = prepareStatement.executeQuery();

			resultSet.next();

			user = new User();
			user.setUserId(resultSet.getInt(1));
			user.setUserName(resultSet.getString(2));
			user.setUserEmail(resultSet.getString(3));
			user.setUserCountry(resultSet.getString(4));

			System.out.println(user);

		} catch (Exception e) {
			e.printStackTrace();

		}
		return user;

	}

	public boolean updateUser(User user) {

		boolean rowupdated = false;

		try (Connection connection = getConnection();

		) {

			PreparedStatement prepareStatement = connection.prepareStatement(UPDATE_USER);

			System.out.println("sqlstatement");
			prepareStatement.setString(1, user.getUserName());
			prepareStatement.setString(2, user.getUserEmail());
			prepareStatement.setString(3, user.getUserCountry());
			prepareStatement.setInt(4, user.getUserId());

			rowupdated = prepareStatement.executeUpdate() > 0;

		}

		catch (Exception e) {

			e.printStackTrace();

		}
		return rowupdated;

	}

	public boolean deleteUser(int id) {
		boolean rowDeleted = false;

		try (Connection connection = getConnection();) {

			PreparedStatement prepareStatement = connection.prepareStatement(DELETE_USER);
			prepareStatement.setInt(1, id);

			rowDeleted = prepareStatement.executeUpdate() > 0;

		}

		catch (Exception e) {

			e.printStackTrace();

		}

		return rowDeleted;

	}

}
