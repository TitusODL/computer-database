<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>

<%@ page isELIgnored="false"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE HTML>
<html>
<head>
<title>Computer Database</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta charset="utf-8">
<!-- Bootstrap -->
<spring:url value="/resources/css/bootstrap.min.css" var="bootstrapStyle" />
<spring:url value="/resources/css/font-awesome.css" var="fontAweSomeStyle" />
<spring:url value="/resources/css/main.css" var="mainCss" />

</head>
<link href="${bootstrapStyle}" rel="stylesheet" media="screen">
<link href="${fontAweSomeStyle}" rel="stylesheet" media="screen">
<link href="${mainCss}" rel="stylesheet" media="screen">
</head>
<body>
	<header class="navbar navbar-inverse navbar-fixed-top">
		<div class="container">
			<a class="navbar-brand" href="Dashboard"> Application - Computer Database </a>
			<a class="navbar-brand" href="logout" >Logout</a> 
		</div>
		<script type="text/javascript">
			function googleTranslateElementInit() {
				new google.translate.TranslateElement({
					pageLanguage : 'en'
				}, 'google_translate_element');
			}
		</script>

		<script type="text/javascript"
			src="//translate.google.com/translate_a/element.js?cb=googleTranslateElementInit"></script>
		<script type="text/javascript" src="https://translate.google.com/translate_a/element.js?cb=googleTranslateElementInit">
		
	</script>
	</header>

	<section id="main">
		<div class="container">

			<h1 id="homeTitle">
				<b>Computer Database : </b>
				<c:out value="${nbRows}"></c:out>
				<b> computers found</b>
			</h1>


			<div id="actions" class="form-horizontal">
				<div class="pull-left">
					<form id="searchForm" action="#" method="GET" class="form-inline">
						<input type="search" id="searchbox" name="search"
							class="form-control" placeholder="Search name" /> <input
							type="submit" id="searchsubmit" value="Filter by name"
							class="btn btn-primary" />
					</form>
				</div>
				<div class="pull-right">
					<a class="btn btn-success" id="addComputer" href="AddComputer">Add
						Computer</a> <a class="btn btn-default" id="editComputer" href="#"
						onclick="$.fn.toggleEditMode();">Edit</a>
				</div>
			</div>
		</div>
		<form id="deleteForm" action="DeleteComputer" method="POST">
			<input type="hidden" name="selection" value="">
		</form>
		<div class="container" style="margin-top: 10px;">
			<table class="table table-striped table-bordered">
				<thead>
					<tr>
						<!-- Variable declarations for passing labels as parameters -->
						<!-- Table header for Computer Name -->
						<th class="editMode" style="width: 60px; height: 22px;"><input
							type="checkbox" id="selectall" /> <span
							style="vertical-align: top;"> - <a href="#"
								id="deleteSelected" onclick="$.fn.deleteSelected();"> <i
									class="fa fa-trash-o fa-lg"></i>
							</a>
						</span></th>
						<th><a
							href="Dashboard?order=computer.name&direction=${direction+1}">Computer
								name</a></th>
						<th>Introduced date</th>
						<!-- Table header for Discontinued Date -->
						<th>Discontinued date</th>
						<!-- Table header for Company -->
						<th><a
							href="Dashboard?order=company.name&direction=${direction+1}">Company
								Name</a></th>



					</tr>
				</thead>

				<!-- Browse attribute computers -->
				<tbody id="results">

					<c:forEach items="${computerListPage}" var="computer">
						<tr>
							<td class="editMode"><input type="checkbox" name="cb"
								class="cb" value="${computer.id}"></td>
							<td><a href="EditComputer?computerId=${computer.id}"
								onclick=""> <c:out value="${computer.name}"></c:out></a></td>
							<td><c:out value="${computer.introduced}"></c:out></td>
							<td><c:out value="${computer.discontinued}"></c:out></td>
							<td><c:out value="${computer.company_name}"></c:out></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</section>

	<footer class="navbar-fixed-bottom">
		<div class="container text-center">
			<ul class="pagination">
				<c:if test="${pageNum>0}">
					<li><a
						href="?pageNum=${pageNum-1}&pageTaille=${pageTaille}&search=${search}&order=${order}&direction=${direction}"
						aria-label="Previous"> <span aria-hidden="true">&laquo;</span></a>
					</li>
				</c:if>

				<c:forEach var="i" begin="1" end="5">
					<c:if test="${pageNum+i<=pageMax}">
						<li><a
							href="?pageNum=${pageNum+i}&pageTaille=${pageTaille}&search=${search}&order=${order}&direction=${direction}"><c:out
									value="${pageNum+i}"></c:out></a></li>
					</c:if>
				</c:forEach>
				<c:if test="${pageNum<pageMax}">
					<li><a
						href="?pageNum=${pageNum+1}&pageTaille=${pageTaille}&search=${search}&order=${order}&direction=${direction}"
						aria-label="Next"> <span aria-hidden="true">&raquo;</span></a></li>
				</c:if>

			</ul>

			<div class="btn-group btn-group-sm pull-right" role="group">
				<a
					href="?pageTaille=10&search=${search}&order=${order}&direction=${direction}&pageNum=${pageNum}"><button
						type="button" class="btn btn-default">10</button></a> <a
					href="?pageTaille=50&search=${search}&order=${order}&direction=${direction}&pageNum=${pageNum}"><button
						type="button" class="btn btn-default">50</button></a> <a
					href="?pageTaille=100&search=${search}&order=${order}&direction=${direction}&pageNum=${pageNum}"><button
						type="button" class="btn btn-default">100</button></a>

			</div>
		</div>

	</footer>
	<spring:url value="/resources/js/jquery.min.js" var="jqueryMinJS" />
	<spring:url value="/resources/js/bootstrap.min.js" var="bootsrapJS" />
	<spring:url value="/resources/js/dashboard.js" var="dashboardJS" />

	<script src="${jqueryMinJS }"></script>
	<script src="${bootsrapJS }"></script>
	<script src="${dashboardJS }"></script>

</body>
</html>