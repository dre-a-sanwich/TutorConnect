package myproject;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import databaseHandler.Controller;
import databaseHandler.User;

/**
 * Servlet implementation class NewUserServlet
 */
public class NewUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NewUserServlet() {
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
		String page = null;
	        try{
	        	
	        	System.out.println("inside doPost NewUserServlet");
	        	//System.out.println("Request: "+ request.getLocalName().toString());
	        	
	        	if(!(request.getParameter("createUsername").toString().isEmpty())) {
	        		
	        		String loginInfo = request.getParameter("createUsername").toString();
	        		System.out.println("Creating user :"+ loginInfo);
	        		User user = new User(loginInfo);
	        		boolean check = Controller.addUser(user);	       	
		        	if(check){
		        		System.out.println("addUser returned true");		        		
		        		page = "success";
		        	} else  {
		        		System.out.println("call for addUser failed");
		        		page = "failed";
		        	}
	        	}
	        	System.out.println("Exiting NewUserServlet doPost");
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
