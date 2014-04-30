	
	function setCookie(cname,cvalue,exdays) {
		var d = new Date();
		d.setTime(d.getTime()+(exdays*24*60*60*1000));
		var expires = "expires="+d.toGMTString();
		document.cookie = cname + "=" + cvalue + "; " + expires;
	}
	
	function getCookie(cname) {
		var name = cname + "=";
		var ca = document.cookie.split(';');
		
		for(var i=0; i<ca.length; i++) {
			var c = ca[i].trim();
		  	if (c.indexOf(name)==0) return c.substring(name.length,c.length);
		}
		return "";
	}
	
	function deleteCookie() {
		document.cookie = "username=; expires=Thu, 01 Jan 1970 00:00:00 GMT";
	}
	
	$.ajax({
		  url: 'Servlet',
		  cache: false
	})
	
  	.done(function( html ) {
  		$( "#content" ).append( html );
	});
			
    $(document).ready(function() {   
    	
	   	$('#addForm').hide();
	   	$('#deleteForm').hide();
	   	$('#loginContent').hide();
	   	$('#createLoginContent').hide();
	   	
	   	$.get('Servlet', "page", function(responseText) { 
	   		$('#content').html(responseText);  
	   	});
    	
    	if(getCookie("username")){
    		$('#welcometext').text("Logout");
    	} else{
    		$('#welcometext').text("Login");
    	}
   
                $('#homebutton').click(function(event) { 
                	$('#container').show();
                	$('#addButton').show();
                	$('#myEvents').show();
                	$('#deleteButton').show();
                	$('#addForm').hide();
            	   	$('#deleteForm').hide();
                	$('#loginContent').hide();
                	$('#createLoginContent').hide();
                	$('#eventBar').show();
                    var home="home";
                     /* alert("inside homebutton click event");  */
                 $.get('Servlet',{page:home},function(responseText) { 
                        $('#content').html(responseText);  
                        //alert(responseText);
                    });
                });
                $('#welcometext').click(function(event) {
                	var text = $('#welcometext').text();
                	if($.trim(text) == 'Login'){
	                	$('#container').hide();
	                	$('#createLoginContent').hide();
	                	$('#loginContent').show().css("#loginContent");
                	} else if($.trim(text) == 'Logout'){
                		var answer = confirm('Are you sure you want to logout?');
                		if (answer){
							deleteCookie();
							$('#welcometext').text("Login");
							alert(getCookie("username"));
							var home="home";
		                	$.get('Servlet',{page:home},function(responseText) { 
		                        $('#content').html(responseText);
		                    });
                		}
                	}
                	
                });
                $('#about').click(function(event) { 
                	$('#addButton').hide();
                	$('#deleteButton').hide();
                	$('#myEvents').hide();
                	$('#eventBar').hide();
                    var home="about";
                    /*  alert("inside login click event");  */
                 $.get('Servlet',{page:home},function(responseText) { 
                        $('#content').html(responseText);  
                        //alert(responseText);
                    });
                });
                $('#contact').click(function(event) { 
                	$('#addButton').hide();
                	$('#deleteButton').hide();
                	$('#myEvents').hide();
                	$('#eventBar').hide();
                    var home="contact";
                    /*  alert("inside login click event");  */
                 $.get('Servlet',{page:home},function(responseText) { 
                        $('#content').html(responseText);  
                        //alert(responseText);
                    });
                });
                $('#addButton').click(function(event) {  
                	if(getCookie("username") != ""){
                		$('#addForm').show();
                		$('#deleteForm').hide();
                	} else{
                		alert("You must be logged in to add an Event");
                	}
          		});
                
                $('#myEvents').click(function(event) {  
                	var home = "myEvents";
                	if(getCookie("username") != ""){
		            	$('#addForm').hide();
		        		$('#deleteForm').hide();
		            	$.get('Servlet',{page:home},function(responseText) { 
		            		$('#content').html(responseText);  
		                });
                	} else{
                		alert("Login to view your events");
                	}
          		});
                
                $('#deleteButton').click(function(event) {
                	//alert("delete button clicked");
                	if(getCookie("username") != ""){
                		$('#deleteForm').show();
                		$('#addForm').hide();
                	} else{
                		alert("You must be logged in to delete an Event");
                	}
          		});
                  
                $('#newUserLink').click(function(event) {  
                	$('#createLoginContent').show();
                	$('#loginContent').hide();
                    });
                
                $('#returnToLogin').click(function(event){
                	$('#loginContent').show();
                	$('#createLoginContent').hide();
                });
                
                $('form[name="searchBox"]').submit(function(event) { 
                	event.preventDefault();
                	var search = $.trim($('#search').val());
                	if(search == ''){
           				alert("Enter a keyword to search events");
                	} else{
	                	
	                	
	                	$.post('SearchServlet', {search : search},
	             			   function(responseText) {
		             			response = responseText;
		             			if(response == ''){
		             				alert("Event could not be found");
		             			} else{
	             			   	 	$('#content').html(response);
		             			}
	             			   });
                	}
                	
               });
                
                $('form[name="eventForm"]').submit(function(event){
                	//event.preventDefault();
                	var title = $("#Title").val();
                	var name =  getCookie("username");
                	var location =  $("#Location").val();
                	var description =  $("#Description").val();
                	var day =  $.trim($("#Day").val());
                	var time =  $.trim($("#Time").val());
                	var string = name.concat("#!", location, "#!", description, "#!", day, " ", time, "#!", title);
                	var response;
                	
                	if(title === "" || name === "" || location === "" || day === "" || time === "" || description === ""){
    					alert("All fields are required");  
                		return;         		
                	} else{
                	
	                	$.post('Servlet', { CreatorName : string},
	                			   function(responseText) {
	                			     //alert(responseText);
	                			     response = responseText;
	                			   });
                	}
                    //alert("Submitted");
                   //$('#content').html(response);
                  });
                
                $('form[name="deleteEventForm"]').submit(function(event){
                	event.preventDefault();
                	var deleteID = $.trim($('#deleteID').val());
                	//alert("ID before: " + deleteID);
                	var response;
                	
                	
                	
                	$.post('DeleteServlet', { deleteID : deleteID},
                			function(responseText) {
                				//alert("ResponseText: " + responseText);
                				//alert("ID: " + deleteID);
                		
                				response = responseText;
                				if(response == "success"){
                					alert("Event Deleted");
                				} else {
                					alert("deletion failed");
                				}
                				//alert("Response: " + response);
                			});
                });
                
                $('form[name="loginForm"]').submit(function(event){
                	event.preventDefault();
                	var username =  $("#Username").val();
                	var password =  $("#Password").val();
              
                	var string = username.concat("#!", password);
                	var response;
                	alert("inside loginSubmit");
                	
                	$.post('LoginServlet', { Username : string},
                			   function(response) {
                			     if(response == "success"){
                			    	 $('#welcometext').text("Logout");
                			    	 setCookie("username", username, 1);
                			    	 alert("You are now logged in as " + getCookie("username"));
                			    	 
                			     }else{
                			    	 alert("login failed" + responseText);
                			     }
                			     response = responseText;
                			   });
                    //alert("Submitted Login");
                   //$('#content').html(response);
                  });
                $('form[name="createLoginForm"]').submit(function(event){
                	event.preventDefault();
                	var username =  $("#createUsername").val();
                	var password =  $("#createPassword").val();
              
                	var string = username.concat("#!", password);
                	var response;
                	
                	$.post('NewUserServlet', { createUsername : string},
                			   function(response) {
                			     if(response == "success"){
                			    	 alert("successfully created an account");
                			     } else {
                			    	 alert("Account creation failed");
                			     }
                			     response = responseText;
                			   });
                    //alert("Submitted Login");
                   //$('#content').html(response);
                  });
            });