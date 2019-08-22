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
						code="submission.toAuthor" /></h3>
		
			<display:table pagesize="10" class="displaytag" name="reports"
			requestURI="report/toAuthor.do" id="row" >



			<display:column titleKey="report.decision" sortable="true" >
				<jstl:out value="${row.decision}"></jstl:out>
			</display:column>

		

			
			<display:column>
				<a href="report/display.do?reportId=${row.id}"> <spring:message
						code="submission.display" />
				</a>

			</display:column>
			
			</display:table>

	

</security:authorize>
	

