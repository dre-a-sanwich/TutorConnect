package myproject;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import databaseHandler.Controller;
import event.Event;
//import event.Filters;

/**
 * Servlet implementation class Servlet
 */
@WebServlet("/Servlet")
public class Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Servlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String page = null;
		 
		if(request.getParameter("page") == null || request.getParameter("page").toString().equals("home")){
			  //page = Controller.getEventList();
			  page = Controller.getEventList(); 
			  System.out.println("what are you doing?");
			  //page = "<br/> List 1 <br/> List 2 <br/>";
		  } else if(request.getParameter("page").toString().equals("login")){
			  page = "<span> Logout </span>";
		  } else if(request.getParameter("page").toString().equals("about")){
			  page = "<span>We are an awesome team!! </span>";
		  } else if(request.getParameter("page").toString().equals("contact")){
			  page = "<span>Oops ! We are anti social </span> ";
		  } else if (request.getParameter("page").toString().equals("myEvents")){ 
			  System.out.println("MY EVENTS SHOULD BE PRINTING!!!!");
			  
			  Cookie[] cookies = request.getCookies();

			  String username = null;
			  for(Cookie cookie : cookies){
			      if("username".equals(cookie.getName())){
			          username = cookie.getValue();
			      }
			  }
			  page = Controller.getMyEvents(username).toString();
			  System.out.println("Username: " +  username);
			  
		  } else if(request.getParameter("page").toString().equals("")){
			  //page = "<span>Sorry we dont support that feature 1</span>";
			  page = Controller.getEventList();
			  System.out.println("ALL events are displaying");
			 
		  } else {
			  page = "<span>Sorry we dont support that feature 2</span>";
		  }
		  //page = Controller.getEventList(); 
		  response.setContentType("text/plain");  
		  response.setCharacterEncoding("UTF-8"); 
		  response.getWriter().write(page);  
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String page=null;
        try{
        	System.out.println("inside doPost Servlet");
        	System.out.println("Request: "+ request.getLocalName().toString());
        	if(!(request.getParameter("CreatorName").toString().isEmpty())){	
	        	
        		System.out.println("We are creating an event...hopefully");
	        	String eventInfo = request.getParameter("CreatorName").toString();
	        	
	        	Event event = new Event(eventInfo);
	        	System.out.println("new event has been created");
	        	Controller.pushEvent(event);
	        	System.out.println("Event has been pushed");
	           
	        	page = "<span>EVENT FORRRRRMMMM</span>" + eventInfo;
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
