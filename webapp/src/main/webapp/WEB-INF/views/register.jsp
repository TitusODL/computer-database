<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page isELIgnored="false"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<!DOCTYPE html>
<html>

<head>
<spring:url value="/resources/css/bootstrap.min.css" var="bootstrapStyle" />
<spring:url value="/resources/css/font-awesome.css" var="fontAweSomeStyle" />
<spring:url value="/resources/css/main.css" var="mainCss" />

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous"></head>
<link href="${bootstrapStyle}" rel="stylesheet" media="screen">
<link href="${fontAweSomeStyle}" rel="stylesheet" media="screen">
<link href="${mainCss}" rel="stylesheet" media="screen">
</head>
 <h1>Application - Computer Database</h1>
    <header class="navbar navbar-inverse navbar-fixed-top">
        <div class="container">
            <a class="navbar-brand" href="Dashboard"> Go to Dashboard </a>
             
        </div>
    </header>
	<body>
		<div class="container">
			<div class="row">
				<div class="col">
					<form method="post" action="register" class="form">
			  <div class="form-group">
			    <label for="username">Name</label>
			    <input type="text" class="form-control" id="userNameDTO" name="userNameDTO" placeholder="name">
			  </div>
			  <div class="form-group">
			    <label for="password">Password</label>
			    <input type="password" class="form-control" id="passwordDTO" name="passwordDTO" >
			  </div>
			  <div class="form-group">
			    <label for="role">Role select(pas super intelligent)</label>
			    <select class="form-control" id="roleDTO" name="roleDTO">
			      <option>ADMIN</option>
			      <option>USER</option>
			    </select>
			  </div>
			  <button class="btn btn-primary" type="submit">Create user</button>
			</form>	
				</div>
			</div>
		</div>
	</body>
</html>