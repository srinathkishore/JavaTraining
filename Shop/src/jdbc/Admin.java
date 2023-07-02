package jdbc;

import java.sql.*;

public class Admin {

	public boolean login(Connection con, String name, String pass) {

		String sql = "select * from users where uname = ? and pass = ? and level = \"admin\"";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, name);
			ps.setString(2, pass);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				return true;
			} else {
				return false;
			}

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public void addAgent(Connection con, String name, String pass) {
		String add = "insert into users value(?,?,\"agent\")";
		try {
			PreparedStatement ps = con.prepareStatement(add);
			ps.setString(1, name);
			ps.setString(2, pass);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void display(Connection con) {
		String show = "select * from users";
		try {
			PreparedStatement ps = con.prepareStatement(show);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				String name = rs.getString("uname");
				String pass = rs.getString("pass");
				System.out.println(name);
				System.out.println(pass);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
