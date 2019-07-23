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
					<li><a href="administrator/administrator/register.do"><spring:message
								code="master.page.register.admin" /></a></li>
					<li><a href="auditor/auditor/register.do"><spring:message
								code="master.page.register.auditor" /></a></li>
				</ul></li>

		</security:authorize>

		<security:authorize access="isAnonymous()">

			<!-- Sign up -->
			<li><a class="fNiv"><spring:message
						code="master.page.singup" /></a>
				<ul>
					<li class="arrow"></li>
					<li><a href="company/company/register.do"><spring:message
								code="master.page.register.company" /></a></li>
					<li><a href="rookie/rookie/register.do"><spring:message
								code="master.page.register.rookie" /></a></li>
					<li><a href="provider/provider/register.do"><spring:message
								code="master.page.register.provider" /></a></li>
				</ul></li>

		</security:authorize>
		<li><a class="fNiv"><spring:message
					code="master.page.position" /></a>
			<ul>
				<security:authorize access="!hasRole('ROOKIE')">
					<li class="arrow"></li>
					<li><a href="position/listAll.do"><spring:message
								code="master.page.position.list.all" /></a></li>
					<li><a href="finder/anon/search.do"><spring:message
								code="master.page.finder" /></a></li>
				</security:authorize>
				<security:authorize access="hasRole('ROOKIE')">
					<li class="arrow"></li>
					<li><a href="position/listAll.do"><spring:message
								code="master.page.position.list.all" /></a></li>

				</security:authorize>
				<security:authorize access="hasRole('COMPANY')">
					<li><a href="position/list.do"><spring:message
								code="master.page.position.mylist" /></a></li>


					<li><a href="position/create.do"><spring:message
								code="master.page.position.edit" /></a></li>
				</security:authorize>

			</ul></li>
		<security:authorize access="hasRole('COMPANY')">

			<li><a class="fNiv"><spring:message
						code="master.page.problem" /></a>
				<ul>
					<li class="arrow"></li>
					<li><a href="problem/list.do"><spring:message
								code="master.page.problem.list" /></a></li>
					<security:authorize access="hasRole('COMPANY')">

						<li><a href="problem/create.do"><spring:message
									code="master.page.problem.edit" /></a></li>
					</security:authorize>

				</ul></li>
		</security:authorize>
		<security:authorize access="hasRole('AUDITOR')">
			<li><a class="fNiv"><spring:message code="master.page.audit" /></a>
				<ul>
					<li class="arrow"></li>
					<li><a href="audit/list.do"><spring:message
								code="master.page.audit.list" /></a></li>
				</ul></li>
		</security:authorize>

		<security:authorize access="!hasRole('PROVIDER')">

			<li><a href="item/listAll.do"><spring:message
						code="master.page.items" /></a></li>

		</security:authorize>

		<security:authorize access="hasRole('PROVIDER')">

			<li><a class="fNiv" href="sponsorship/list.do"><spring:message
						code="master.page.sponsorship" /></a></li>

			<li><a class="fNiv"><spring:message code="master.page.items" /></a>
				<ul>
					<li class="arrow"></li>
					<li><a href="item/list.do"><spring:message
								code="master.page.item.list" /></a></li>
					<li><a href="item/create.do"><spring:message
								code="master.page.item.create" /></a></li>
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