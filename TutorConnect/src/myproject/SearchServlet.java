package myproject;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import databaseHandler.Controller;

/**
 * Servlet implementation class SearchServlet
 */
public class SearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}
		  
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String page = null;
	        try{
	        	
	        	System.out.println("inside doPost SearchServlet");
	        	//System.out.println("Request: "+ request.getLocalName().toString());
	        	
	        	
	        	if(!(request.getParameter("search").toString().isEmpty())){
	        		String search = request.getParameter("search").toString();
	        		System.out.println("Searching for: " + search);
	        		
	        		page = Controller.getSearchedEvents(search);
	        		
	        		if(Controller.hasSearched()){
	        			//page = Controller.getSearchedEvents(search);
	        			System.out.println("SEARCH SERVLET doGet");
	        		} else{
	        			page = null;
	        		}
	        		
	        		System.out.println("events searched");
		           
	        	}
	        	System.out.println("Exiting SearchServlet doPost");
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
