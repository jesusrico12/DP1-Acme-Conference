<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>


<security:authorize access="hasRole('AUTHOR')">

	<form:form action="submission/edit.do?conferenceId=${conference.id}" modelAttribute="submission">

		<form:hidden path="id" />
		<form:hidden path="version" />
		<form:hidden path="madeMoment" />
		<form:hidden path="status" />
		<form:hidden path="toReview" />
		<form:hidden path="author" />
		<form:hidden path="conference" />
		<form:hidden path="reviewers" />
		<form:hidden path="reports" />

		<form:label path="paper">
			<spring:message code="conference.title" /> :
	</form:label>
		<form:input path="title" />
		<form:errors cssClass="error" path="title" />
		<br />
		<br />

		<form:label path="acronym">
			<spring:message code="conference.acronym" /> :
	</form:label>
		<form:input path="acronym" />
		<form:errors cssClass="error" path="acronym" />
		<br />
		<br />

		<form:label path="venue">
			<spring:message code="conference.venue" /> :
	</form:label>
		<form:input path="venue" />
		<form:errors cssClass="error" path="venue" />
		<br />
		<br />


		<form:label path="submissionDeadline">
			<spring:message code="conference.submissionDL" /> :
	</form:label>
		<form:input path="submissionDeadline" placeholder="dd/MM/yyyy HH:mm" />
		<form:errors cssClass="error" path="submissionDeadline" />
		<br />
		<br />

		<form:label path="notificationDeadline">
			<spring:message code="conference.notificationDL" /> :
	</form:label>
		<form:input path="notificationDeadline" placeholder="dd/MM/yyyy HH:mm" />
		<form:errors cssClass="error" path="notificationDeadline" />
		<br />
		<br />

		<form:label path="cameraReadyDeadline">
			<spring:message code="conference.cameraReadyDL" /> :
	</form:label>
		<form:input path="cameraReadyDeadline" placeholder="dd/MM/yyyy HH:mm" />
		<form:errors cssClass="error" path="cameraReadyDeadline" />
		<br />
		<br />

		<form:label path="startDate">
			<spring:message code="conference.startDate" /> :
	</form:label>
		<form:input path="startDate" placeholder="dd/MM/yyyy HH:mm" />
		<form:errors cssClass="error" path="startDate" />
		<br />
		<br />

		<form:label path="endDate">
			<spring:message code="conference.endDate" /> :
	</form:label>
		<form:input path="endDate" placeholder="dd/MM/yyyy HH:mm" />
		<form:errors cssClass="error" path="endDate" />
		<br />
		<br />

		<form:label path="summary">
			<spring:message code="conference.summary" /> :
	</form:label>
		<form:textarea path="summary" />
		<form:errors cssClass="error" path="summary" />
		<br />
		<br />

		<form:label path="fee">
			<spring:message code="conference.fee" /> :
	</form:label>
		<form:input path="fee" min="0" />
		<form:errors cssClass="error" path="fee" />
		<br />
		<br />

		<input type="submit" name="saveNormal"
			value="<spring:message code="conference.saveNormal"/>" />&nbsp;
	
	<input type="submit" name="saveFinal"
			value="<spring:message code="conference.saveFinal"/>" />&nbsp;
				
		<input type="button" name="back"
			value="<spring:message code="conference.back" />"
			onclick="window.history.back()" />
	</form:form>
</security:authorize>





