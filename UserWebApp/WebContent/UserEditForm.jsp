<%@ page language="java" contentType="text/html; charset=UTF-8"
 pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
        <html>

        <head>
            <title>User Management Application</title>
            <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
        </head>

        <body>

            <header>
                <nav class="navbar navbar-expand-md navbar-dark" style="background-color: tomato">
                    <div>
                        <h1> Edit User Details </h1>             
      				 </div>               
                </nav>
            </header>
            
            <br>
            
            <div class="container col-md-5">
                <div class="card">
                    <div class="card-body">                      
                       
                        <form action="update" method="post">   
                        
                        <fieldset class="form-group">
                            <label>User </label> <input type="text"   class="form-control" name="id" value="${user.userId}" readonly />
                        </fieldset>
              
                        <fieldset class="form-group">
                            <label>User Name</label> <input type="text"   class="form-control" name="name" value="${user.userName}" />
                        </fieldset>

                        <fieldset class="form-group">
                            <label>User Email</label> <input type="text" class="form-control" name="email" value="${user.userEmail}"/>
                        </fieldset>

                        <fieldset class="form-group">
                            <label>User Country </label> <input type="text"  class="form-control" name="country" value="${user.userCountry}" />
                        </fieldset>

                        	<button type="submit" class="btn btn-success">Save</button>
                        </form>
                    </div>
                </div>
            </div>
        </body>

        </html>

