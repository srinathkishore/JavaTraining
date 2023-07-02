package jdbc;

import java.sql.*;
import java.util.Scanner;

public class Main {

	static Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return;
		}

		Connection con = null;

		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "123456789");
		} catch (SQLException e) {
			e.printStackTrace();
			return;
		}

		if (con != null) {
			System.out.println("Connection Established");
		} else {
			System.out.println("Not Connected");
			return;
		}

		System.out.println("Login\n1.Admin\n2.Agent\n3.Exit");
		int choice = scanner.nextInt();
		scanner.nextLine();

		switch (choice) {

		case 1:
			System.out.println("Name: ");
			String name = scanner.nextLine();
			System.out.println("Password: ");
			String password = scanner.nextLine();

			Admin admin = new Admin();
			if (admin.login(con, name, password)) {
				System.out.println("Admin LoggedIn");

				System.out.print("1.Add Agents\n2.Remove Agents\nEnter admin action: ");
				int admChc = scanner.nextInt();
				scanner.nextLine();
				switch (admChc) {
				case 1:
					System.out.print("Name: ");
					String aname = scanner.nextLine();
					System.out.print("Password: ");
					String apass = scanner.nextLine();
					admin.addAgent(con, aname, apass);
					break;

				case 2:
					admin.display(con);
					break;
				}
			} else {
				System.out.println("Couldnt Login");
			}

		case 2:
			break;

		case 3:
			break;

		default:
			System.out.println("Invalid choice");
			break;
		}

		try {
			if (con != null) {
				con.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
