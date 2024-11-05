<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
	<%@ page import ="java.util.List" %>
	<%@ page import ="com.spring.sms.model.Course" %>
 <html>
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
	<body >
		<%
				List<Course> courses = (List<Course>) request.getAttribute("listCourses"); 
				%>
	       <div class="container-fluid" style="margin: 0%; padding: 0px;">
	           <div class="row">
	               <div class="col-lg-12">
					<%@ include file="navbar.jsp" %>	
	               </div>
	           </div>
	           <div class="row">
	               <div class="col-sm-2">sidebar goes here...</div>
	               <div class="col-md-10  mt-4">
	                   <div class="row mb-4 ">
	                       <div class="col-sm-4">
	                           <div class="card" >
	                               <div class="card-body">
	                                 <h5 class="card-title">Card title</h5>
	                                 <h6 class="card-subtitle mb-2 text-muted">Card subtitle</h6>
	                                 <p class="card-text">Some quick example text to build on the card title and make up the bulk of the card's content.</p>
	                                 <a href="#" class="card-link">Card link</a>
	                                 <a href="#" class="card-link">Another link</a>
	                               </div>
	                             </div>
	                       </div>
	                       <div class="col-sm-4">
	                           <div class="card" >
	                               <div class="card-body">
	                                 <h5 class="card-title">Card title</h5>
	                                 <h6 class="card-subtitle mb-2 text-muted">Card subtitle</h6>
	                                 <p class="card-text">Some quick example text to build on the card title and make up the bulk of the card's content.</p>
	                                 <a href="#" class="card-link">Card link</a>
	                                 <a href="#" class="card-link">Another link</a>
	                               </div>
	                             </div>
	                       </div>
	                       <div class="col-sm-4">
	                           <div class="card" >
	                               <div class="card-body">
	                                 <h5 class="card-title">Card title</h5>
	                                 <h6 class="card-subtitle mb-2 text-muted">Card subtitle</h6>
	                                 <p class="card-text">Some quick example text to build on the card title and make up the bulk of the card's content.</p>
	                                 <a href="#" class="card-link">Card link</a>
	                                 <a href="#" class="card-link">Another link</a>
	                               </div>
	                             </div>
	                       </div>
	                   </div>
	                    
	                   <div class="row">
	                       <div class="col-lg-12">
	                           
	                           <table class="table">
	                               <thead>
	                                 <tr>
	                                   <th scope="col">#</th>
	                                   <th scope="col">Course Name</th>
	                                   <th scope="col">Credits</th>
	                                   <th scope="col">Dept Name</th>
	                                 </tr>
	                               </thead>
	                               <tbody>
									<% for( Course c:courses){
																			%>
																			<tr>
																			  <th scope="row"> <%=c.getId() %></th>
																			  <td><%=c.getName() %></td>
																			  <td><%=c.getCredits() %></td>
																			  <td><%=c.getDepartment().getName() %></td>
																			  <td>@mdo</td>
																			</tr>										
																		<%	
																		} 
																		%>

	                               </tbody>
	                             </table>
	                       </div>
	                   </div>
	               </div>
	           </div>
	       </div>
	   </body>
</html>	