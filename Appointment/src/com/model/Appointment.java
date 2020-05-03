package com.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import com.util.DBConnect;

public class Appointment {

	DBConnect connect = new DBConnect();

	public String setAppointment(String userId, String docId, String hospId, String docSpec, String date) {
		String output = "";

		try {

			Connection connection = connect.connect();

			if (connection == null) {

				return "Error while connecting to the database for inserting.";

			}
			// create a prepared statement

			/*
			 * String query1 = "select Date from appointments where Date = ?";
			 * PreparedStatement ps = connection.prepareStatement(query1); ps.setString(1,
			 * date); ResultSet set = ps.executeQuery();
			 */

			String query = "insert into appointments(`User_Id`, `Doctor_Id`, `Doctor_spec`, `Hosp_Id`, `Date`)"
					+ " values (?, ?, ?, ?, ?)";

			PreparedStatement preparedStmt = connection.prepareStatement(query);
			/*
			 * preparedStmt.setInt(1, Integer.parseInt(userId));
			 * 
			 * preparedStmt.setInt(2, Integer.parseInt(docId));
			 * 
			 * preparedStmt.setString(3, docSpec);
			 * 
			 * preparedStmt.setInt(4, Integer.parseInt(hospId));
			 * 
			 * preparedStmt.setDate(5, Date.valueOf(date));
			 */
			preparedStmt.setString(1, userId);

			preparedStmt.setString(2, docId);

			preparedStmt.setString(3, docSpec);

			preparedStmt.setString(4, hospId);

			preparedStmt.setString(5, date);
			preparedStmt.execute();

			connection.close();

			output = "Inserted successfully";

			String queryUpdate = "update doctor set Availability = 0 where Doctor_Id = ?";

			int Availability = 0;
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setInt(1, Availability);
			ps.setInt(6, Integer.parseInt(docId));

			String newAppo = readAppointments();
			output = "{\"status\":\"success\", \"data\": \"" + newAppo + "\"}";
		} catch (Exception e) {

			output = "{\"status\":\"error\", \"data\":         \"Error while inserting the appointment.\"}";

			System.err.println(e.getMessage());

		}

		return output;

	}

	public String getAvailableDocs() {

		String output = "";
		try {
			Connection connection = connect.connect();

			if (connection == null) {
				return "Error while connecting to the database";
			}

			output = "<table class=\"table\" ><thead><ID><th>Doctor ID</th><th>Name</th>"
					+ "<th>Doctor Specialization</th><th>Working Hospital Id</th><th>Availability</th>" + "</thead>";

			String query = "select * from doctor  where Availability = '1'";

			// Not set yet String query = "Select D_Name from doctor"; Statement
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(query);

			while (resultSet.next()) {
				String docId = Integer.toString(resultSet.getInt("Doctor_ID"));
				String docName = resultSet.getString("D_Name");
				String docSpec = resultSet.getString("D_Type");
				String docHosp = resultSet.getString("Hospital_ID");
				String availability = resultSet.getString("Availability");

				// Add into the html table

				output += "<tbody><tr><td><input id='hidDocIdUpdate'name='hidDocIdUpdate' type='hidden' value=''"
						+ docId + "'>" + docId + "</td>";
				output += "<td>" + docName + "</td>";
				output += "<td>" + docSpec + "</td>";
				output += "<td>" + docHosp + "</td>";
				// buttons

				output += "<td><form method='post' action='appointments.jsp'>"
						+ "<input class='btn btn-success' name = 'availabilityBtn' type='submit' value='Available'\"></from>"
						+ "</td></tr></tbody>";

			}

			connection.close();

			output += "</table>";

		} catch (Exception e) { // TODO: handle exception
			System.out.println("Error while connecting to the database " + e);

			System.err.println(e.getMessage());
		}
		return output;

	}

	public String readAppointments() {

		String output = "";
		try {
			Connection connection = connect.connect();

			if (connection == null) {
				return "Error while connecting to the database";
			}

			output = "<table class='table' border='1'> <thead><tr><th>User Id</th>"
					+ "<th>Doctor ID</th><th>Doctor Specialisation</th><th>Hospital Id</th><th>Date</th>"
					+ "</th></tr></thead>";

			// Not set yet
			String query = "Select * from appointments";
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(query);

			while (resultSet.next()) {
				String appoId = resultSet.getString("Appointment_Id");
				String userId = resultSet.getString("User_Id");
				String docId = resultSet.getString("Doctor_Id");
				String docSpec = resultSet.getString("Doctor_spec");
				String hospId = resultSet.getString("Hosp_Id");
				String date = resultSet.getString("Date");

				// Add into the html table
				output += "<tbody><tr><td><input id='hidReadAppoIdUpdate'name='hidReadAppoIdUpdate'type='hidden' value=''"

						+ appoId + "'>" + userId + "</td>";

				output += "<td>" + docId + "</td>";

				output += "<td>" + docSpec + "</td>";

				output += "<td>" + hospId + "</td>";

				output += "<td>" + date + "</td>";

				// buttons

				output += "<td><input name='btnUpdate' type='button' value='Update' class='btnUpdate btn btn-warning'></td>"

						+ "<td><form method='post' action='appointments.jsp'>"

						+ "<input name='btnRemove' type='submit' value='Remove' class='btn btn-danger'>"

						+ "<input name='hidAppoIdDelete' type='hidden' data-appoid='" + appoId + "'>"

						+ "</form></td></tr><tbody>";
			}

			connection.close();

			output += "</table>";

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Error while connecting to the database " + e);

			System.err.println(e.getMessage());
		}
		return output;

	}

	public String updateAppointment(String appoId, String userId, String docId, String hospId, String docSpec,
			String date) {
		String output = "";

		try {

			Connection connection = connect.connect();

			if (connection == null) {

				return "Error while connecting to the database for updating.";

			}

			// update

			// create a prepared statement

			String query = "UPDATE appointments SET User_Id=?,Doctor_Id=?,Doctor_spec=?,Hosp_Id=?, Date=? WHERE Appointment_Id=?";

			PreparedStatement preparedStmt = connection.prepareStatement(query);

			// binding values

			preparedStmt.setInt(1, Integer.parseInt(userId));

			preparedStmt.setInt(2, Integer.parseInt(docId));

			preparedStmt.setString(3, docSpec);

			preparedStmt.setInt(4, Integer.parseInt(hospId));

			preparedStmt.setDate(5, Date.valueOf(date));

			preparedStmt.setInt(6, Integer.parseInt(appoId));

			preparedStmt.execute();

			connection.close();

			// execute the statement

			preparedStmt.execute();

			connection.close();

			String newAppo = readAppointments();
			output = "{\"status\":\"success\", \"data\": \"" + newAppo + "\"}";

		} catch (Exception e) {

			output = "{\"status\":\"error\", \"data\":         \"Error while updating the appointment.\"}";

			System.err.println(e.getMessage());

		}

		return output;

	}

	public String calcelAppointment(String appoId) {
		String output = "";

		try {

			Connection connection = connect.connect();

			if (connection == null) {

				return "Error while connecting to the database for deleting.";

			}

			// create a prepared statement

			String query = "delete from appointments where Appointment_Id=?";

			PreparedStatement preparedStmt = connection.prepareStatement(query);

			// binding values

			preparedStmt.setInt(1, Integer.parseInt(appoId));

			// execute the statement

			preparedStmt.execute();

			connection.close();
			
			String newAppo = readAppointments();
			output = "{\"status\":\"success\", \"data\": \"" + newAppo + "\"}";
			
		} catch (Exception e) {

			output = "{\"status\":\"error\", \"data\":         \"Error while deleting the appointment.\"}";

			System.err.println(e.getMessage());

		}

		return output;

	}
}
