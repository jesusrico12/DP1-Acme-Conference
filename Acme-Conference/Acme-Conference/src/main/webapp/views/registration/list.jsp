<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>



	<security:authorize access="hasRole('AUTHOR')">
	<h3> <spring:message
						code="registration.conferences" /></h3>

		<display:table pagesize="10" class="displaytag" name="conferencesNotStarted"
			requestURI="registration/list.do" id="row">



			<display:column titleKey="conference.title" sortable="true">
				<jstl:out value="${row.title}"></jstl:out>
			</display:column>

		

			<display:column titleKey="conference.startDate" sortable="true">
				<fmt:formatDate type="both" dateStyle="short" timeStyle="short"
					value="${row.startDate}" />
			</display:column>
			<display:column titleKey="conference.endDate" sortable="true">
				<fmt:formatDate type="both" dateStyle="short" timeStyle="short"
					value="${row.endDate}" />
			</display:column>
			
			<display:column titleKey="conference.fee" sortable="true">
				<jstl:out value="${row.fee}"></jstl:out>
			</display:column>
			

	<display:column>
				<a href="conference/display.do?conferenceId=${row.id}"> <spring:message
						code="conference.display" />
				</a>

		</display:column>
				<display:column>
				<a href="registration/create.do?conferenceId=${row.id}"> <spring:message
						code="registration.create" />
				</a>

			</display:column>

		</display:table>
		
			<h3> <spring:message
						code="registration.all" /></h3>
		
			<display:table pagesize="10" class="displaytag" name="registrationsPerAuthor"
			requestURI="registration/list.do" id="row" >



			<display:column titleKey="conference.title" sortable="true">
				<jstl:out value="${row.conference.title}"></jstl:out>
			</display:column>
	<display:column titleKey="registration.number" sortable="true">
				<jstl:out value="${row.creditCard.number}"></jstl:out>
			</display:column>
				<display:column titleKey="registration.make" sortable="true">
				<jstl:out value="${row.creditCard.make}"></jstl:out>
			</display:column>
		

			
			<display:column>
				<a href="registration/display.do?registrationId=${row.id}"> <spring:message
						code="registration.display" />
				</a>

			</display:column>

		</display:table>

	

</security:authorize>
	

