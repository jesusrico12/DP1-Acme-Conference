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



	<security:authorize access="hasRole('REVIEWER')">
	<h3> <spring:message
						code="submission.reviewer" /></h3>

		<display:table pagesize="10" class="displaytag" name="submissionsOfReviewer"
			requestURI="report/list.do" id="row">



			<display:column titleKey="submission.ticker">
				<jstl:out value="${row.ticker}"></jstl:out>
			</display:column>

		

			<display:column titleKey="submission.madeMoment">
				<fmt:formatDate type="both" dateStyle="short" timeStyle="short"
					value="${row.madeMoment}" />
			</display:column>

			<!-- poner display de momento pongo create para probar 
			<display:column>
				<a href="submission/display.do?submissionId=${row.id}"> <spring:message
						code="submission.display" />
				</a>

			</display:column>
			-->
				<display:column>
				<a href="report/create.do?submissionId=${row.id}"> <spring:message
						code="submission.display" />
				</a>

			</display:column>

		</display:table>
		
			<h3> <spring:message
						code="submission.reports.do" /></h3>
		
			<display:table pagesize="10" class="displaytag" name="reportsPerReviewer"
			requestURI="report/list.do" id="row" >



			<display:column titleKey="report.decision">
				<jstl:out value="${row.decision}"></jstl:out>
			</display:column>

		

			
			<display:column>
				<a href="report/display.do?reportId=${row.id}"> <spring:message
						code="submission.display" />
				</a>

			</display:column>

		</display:table>

	

</security:authorize>
	
