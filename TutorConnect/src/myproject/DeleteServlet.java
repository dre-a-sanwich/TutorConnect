package myproject;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import databaseHandler.Controller;
import event.Event;

/**
 * Servlet implementation class DeleteServlet
 */
public class DeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String page=null;
        try{
        	System.out.println("inside doPost DeleteServlet");
        	//System.out.println("Request: "+ request.getLocalName().toString());
        	if(!(request.getParameter("deleteID").toString().isEmpty())){	
	        	
        		System.out.println("We are deleting an event");
	        	String id = request.getParameter("deleteID").toString();
	        	
	        	//Event event = new Event(eventInfo);
	        	//System.out.println("new event has been deleted");
	        	
	        	Cookie[] cookies = request.getCookies();

				  String username = null;
				  for(Cookie cookie : cookies){
				      if("username".equals(cookie.getName())){
				          username = cookie.getValue();
				      }
				  }
	        	
	        	boolean status = Controller.deleteEvent(username, id);
	        	System.out.println("Event has been deleted");
	           if(status == true){
	        	   page = "success";
	           } else {
	        	   page = "fail";
	           }
        	}
        	response.setContentType("text/plain");  
        	response.setCharacterEncoding("UTF-8"); 
        	response.getWriter().write(page); 
        }
       catch(Exception ex)
      {
      ex.getStackTrace();
      }
}

}
