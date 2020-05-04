package servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model.Appointment;

/**
 * Servlet implementation class AppoAPI
 */
@WebServlet("/AppoAPI")
public class AppoAPI extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	Appointment appointment = new Appointment();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AppoAPI() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
    	Map paras = getParasMap(request);

		String output = appointment.updateAppointment(paras.get("hidAppoIdSave").toString(), paras.get("userId").toString(),
				paras.get("docId").toString(), paras.get("hospId").toString(), paras.get("docSpec").toString(), paras.get("date").toString());

		response.getWriter().write(output);
	}
    
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
    	Map paras = getParasMap(request); 
		 
		 String output = appointment.calcelAppointment(paras.get("appoId").toString()); 
		 
		 response.getWriter().write(output);
	}
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String output = appointment.setAppointment(request.getParameter("userId"), request.getParameter("docId"),
				request.getParameter("hospId"), request.getParameter("docSpec"), request.getParameter("date"));

		response.getWriter().write(output);
	}
	
	private static Map getParasMap(HttpServletRequest request) {
		Map<String, String> map = new HashMap<String, String>();
		try {
			Scanner scanner = new Scanner(request.getInputStream(), "UTF-8");
			String queryString = scanner.hasNext() ? scanner.useDelimiter("\\A").next() : "";
			scanner.close();

			String[] params = queryString.split("&");
			for (String param : params) {

				String[] p = param.split("=");
				map.put(p[0], p[1]);
			}
		} catch (Exception e) {
		}
		return map;
	}

}
