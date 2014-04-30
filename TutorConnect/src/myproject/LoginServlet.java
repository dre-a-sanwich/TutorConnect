package myproject;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import databaseHandler.Controller;
import databaseHandler.User;


/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	      
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
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
	        	
	        	System.out.println("inside doPost LoginServlet");
	        	//System.out.println("Request: "+ request.getLocalName().toString());
	        	
	        	if(!(request.getParameter("Username").toString().isEmpty())){
	        		
	        		String loginInfo = request.getParameter("Username").toString();
	        		System.out.println("Checking user : user info : "+loginInfo);
	        		User user = new User(loginInfo);
	        		boolean check = Controller.userExist(user);	       	
		        	if(check){
		        		System.out.println("userExist returned true");
		        		/*Cookie loginCookie = new Cookie("user", user.getName());
		        		loginCookie.setMaxAge(30*60);
		        		response.addCookie(loginCookie);*/
		        		page = "success";
		        	} else  {
		        		System.out.println("username and password did not match");
		        		page = "failed";
		        	}
		           
	        	}
	        	System.out.println("Exiting LoginServlet doPost");
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
