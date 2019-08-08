<%--
 * header.jsp
 *
 * Copyright (C) 2019 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<div>
	<a href="#"><img src="${banner}" alt="Acme-Rookie Co., Inc."
		style="margin-bottom: 0.5em;" /></a>
</div>

<div>
	<ul id="jMenu">
		<!-- Do not forget the "fNiv" class for the first level links !! -->
		<security:authorize access="hasRole('ADMIN')">
			<!-- Register admin -->
			<li><a class="fNiv"><spring:message
						code="master.page.register" /></a>
				<ul>
					<li class="arrow"></li>
					<li><a href="administrator/create.do"><spring:message
								code="master.page.register.admin" /></a></li>
					
				</ul></li>

		</security:authorize>

		<security:authorize access="isAnonymous()">

			<!-- Sign up -->
			<li><a class="fNiv"><spring:message
						code="master.page.singup" /></a>
				<ul>
					<li class="arrow"></li>
					<li><a href="author/create.do"><spring:message
								code="master.page.register.author" /></a></li>
									<li><a href="reviewer/create.do"><spring:message
								code="master.page.register.reviewer" /></a></li>
	
	
				</ul></li>

		</security:authorize>

		



		

		<security:authorize access="isAnonymous()">
			<li><a class="fNiv" href="security/login.do"><spring:message
						code="master.page.login" /></a></li>
		</security:authorize>
		

	

		<security:authorize access="isAuthenticated()">
			<li><a class="fNiv"> <spring:message
						code="master.page.profile" /> (<security:authentication
						property="principal.username" />)
			</a>
				<ul>
					<li class="arrow"></li>
					<security:authorize access="hasRole('ADMIN')">
						<li><a href="statistics/administrator/display.do"><spring:message
									code="master.page.dashboard" /></a></li>
						
						<li><a href="sysconfig/administrator/display.do"><spring:message
									code="master.page.system" /></a></li>
						<li><a href="administrator/display.do"><spring:message
									code="actor.view" /></a></li>
					 	
						<li><a href="administrator/edit.do"><spring:message
									code="master.page.actor.edit" /></a></li>
									
									</security:authorize>	
									
									<security:authorize access="hasRole('AUTHOR')">
				
						<li><a href="author/display.do"><spring:message
									code="actor.view" /></a></li>
				
						<li><a href="author/edit.do"><spring:message
									code="master.page.actor.edit" /></a></li>
									
									</security:authorize>
												<security:authorize access="hasRole('REVIEWER')">
				
						<li><a href="reviewer/display.do"><spring:message
									code="actor.view" /></a></li>
				
						<li><a href="reviewer/edit.do"><spring:message
									code="master.page.actor.edit" /></a></li>
									
									</security:authorize>
						
						
						</ul>
					<li><a href="j_spring_security_logout"><spring:message
								code="master.page.logout" /> </a></li>
		</security:authorize>

	</ul>

</div>

<div style="float: right;">

	<a href="?language=en"><img style="width: 20px; height: 15px"
		src="https://upload.wikimedia.org/wikipedia/en/thumb/a/ae/Flag_of_the_United_Kingdom.svg/1280px-Flag_of_the_United_Kingdom.svg.png" alt="EN"></a> <span>|</span>

	<a href="?language=es"><img style="width: 20px; height: 15px;"
		src="http://www.ahb.es/m/100150RES.jpg"
		alt="ES"></a>

</div>
<security:authorize access="isAuthenticated()">
	
</security:authorize>
