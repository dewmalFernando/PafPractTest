package com.service;

import java.sql.Date;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.parser.Parser;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.model.Appointment;

@Path("/Appointments")
public class AppointmentService {

	Appointment appointment = new Appointment();

	
	@GET
	@Path("/docs")
	@Produces(MediaType.TEXT_HTML)
	public String getDocs() {
		return appointment.getAvailableDocs();
		// return "asdf";
	}

	
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String getAppointments() {
		return appointment.readAppointments();
	}
	
	@POST

	@Path("/")

	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)

	@Produces(MediaType.TEXT_PLAIN)

	public String insertAppointment(

			@FormParam("userId") String userId,

			@FormParam("docId") String docId,
			
			@FormParam("docSpec") String docSpec,

			@FormParam("hospId") String hospId,

			

			@FormParam("date") String date

	) {

		String output = appointment.setAppointment(userId, docId,docSpec, hospId,  date);

		return output;

	}

	@PUT

	@Path("/")

	@Consumes(MediaType.APPLICATION_JSON) //use to specify which MIME media types of representations a resource can accept, or consume, from the client

	@Produces(MediaType.TEXT_PLAIN) //use to specify the media type or representations a resource can produce and send back to the client

	public String updateAppoinement(String dData)

	{

		JsonObject jsonObject = new JsonParser().parse(dData).getAsJsonObject();

		String appoId = jsonObject.get("appoId").getAsString();
		
		String userId = jsonObject.get("userId").getAsString();

		String docId = jsonObject.get("docId").getAsString();

		String hospId = jsonObject.get("hospId").getAsString();

		String docSpec = jsonObject.get("docSpec").getAsString();

		String date = jsonObject.get("date").getAsString();

		String output = appointment.updateAppointment(appoId,userId, docId, hospId, docSpec, date);

		return output;

	}

	@DELETE

	@Path("/")

	@Consumes(MediaType.APPLICATION_XML)

	@Produces(MediaType.TEXT_PLAIN)

	public String deleteAppointment(String dData)

	{

		Document doc = Jsoup.parse(dData, "", Parser.xmlParser());

		String appoId = doc.select("Appointment_Id").text();

		String output = appointment.calcelAppointment(appoId);

		return output;

	}
}
