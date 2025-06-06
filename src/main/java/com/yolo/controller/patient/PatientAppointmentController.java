package com.yolo.controller.patient;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.yolo.service.patient.PatientAppointmentService;
import com.yolo.util.SessionUtil;

/**
 * PatientAppointmentController is responsible for handling patient appointment requests.
 * It manages the display and processing of the patient appointment page.
 * This servlet responds to both "/patient-appointment" and root "/" URL patterns.
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/patient-appointment"})
public class PatientAppointmentController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private PatientAppointmentService patientAppointmentService;
    /**
     * Constructor for PatientAppointmentController.
     * Initializes the servlet by calling the parent constructor.
     */
    public PatientAppointmentController() {
    	this.patientAppointmentService = new PatientAppointmentService();
    }

	/**
	 * Handles GET requests to the patient appointment page.
	 * Forwards the request to the patient_appointment.jsp view.
	 *
	 * @param request  HttpServletRequest object
	 * @param response HttpServletResponse object
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException      if an I/O error occurs
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Integer userID = (Integer) SessionUtil.getAttribute(request, "userID");
		// Get the search query from the request
        String searchQuery = request.getParameter("search");
        
        // Get appointment details for the logged-in patient
        request.setAttribute("appointmentDetailsList", patientAppointmentService.getPatientAppointmentDetails(userID, searchQuery));
        
		request.getRequestDispatcher("/WEB-INF/pages/patient/patient_appointment.jsp").forward(request, response);
	}

	/**
	 * Handles POST requests for patient appointment submissions.
	 * Currently delegates to doGet method, but can be extended to process form submissions.
	 *
	 * @param request  HttpServletRequest object
	 * @param response HttpServletResponse object
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException      if an I/O error occurs
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Currently forwards to the same view as doGet
		// This can be enhanced to process appointment form data
		doGet(request, response);
	}
}