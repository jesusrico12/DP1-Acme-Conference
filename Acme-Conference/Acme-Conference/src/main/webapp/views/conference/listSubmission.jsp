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

<security:authorize access="hasAnyRole('AUTHOR')">
	<jstl:if test="${isToMakeSubmission == true}">

		<display:table pagesize="10" class="displaytag"
			name="conferencesToMakeSubmission"
			requestURI="conference/listSubmission.do" id="row">



			<display:column titleKey="conference.title">
				<jstl:out value="${row.title}"></jstl:out>
			</display:column>

			<display:column titleKey="conference.acronym">
				<jstl:out value="${row.acronym}"></jstl:out>
			</display:column>

			<display:column titleKey="conference.venue">
				<jstl:out value="${row.venue}"></jstl:out>
			</display:column>

			<display:column titleKey="conference.startDate">
				<fmt:formatDate type="both" dateStyle="short" timeStyle="short"
					value="${row.startDate}" />
			</display:column>

			<display:column titleKey="conference.endDate">
				<fmt:formatDate type="both" dateStyle="short" timeStyle="short"
					value="${row.endDate}" />

			</display:column>
			
			<display:column titleKey="conference.submissionDL">
				<fmt:formatDate type="both" dateStyle="short" timeStyle="short"
					value="${row.submissionDeadline}" />

			</display:column>

			<display:column>
				<a href="paper/create.do?conferenceId=${row.id}"> <spring:message
						code="conference.makeSubmission" />
				</a>

			</display:column>

		</display:table>

	</jstl:if>


	<jstl:if test="${isToMakeSubmission == false}">


		<spring:message code="conference.zero" var="zero" />

		<p>
			<jstl:out value="${zero}"></jstl:out>
		</p>

	</jstl:if>





</security:authorize>
