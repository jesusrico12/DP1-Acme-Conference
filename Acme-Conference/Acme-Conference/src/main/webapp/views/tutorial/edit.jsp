<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags" %>

<security:authorize access="hasRole('ADMIN')">

	<form:form action="tutorial/edit.do?conferenceId=${conference.id}"  modelAttribute="tutorial">

		<form:hidden path="id" />
		<form:hidden path="version" />
		<form:hidden path="sections" />


		<form:label path="title">
			<spring:message code="tutorial.title" /> :
	</form:label>
		<form:input path="title" />
		<form:errors cssClass="error" path="title" />
		<br />
		<br />

		<form:label path="speakers">
			<spring:message code="tutorial.speakers" /> :
	</form:label>
	<spring:message code="tutorial.speakers.placeholder" var="placeh" />
		<form:input path="speakers" placeholder='${placeh}' />
		<form:errors cssClass="error" path="speakers" />
		<br />
		<br />
		

		<form:label path="startMoment">
			<spring:message code="tutorial.startMoment" /> :
	</form:label>
		<form:input path="startMoment" placeholder="dd/MM/yyyy HH:mm"/>
		<form:errors cssClass="error" path="startMoment" />
		<br />
		<br />

		<form:label path="duration">
			<spring:message code="tutorial.duration" /> :
	</form:label>
		<form:input path="duration" />
		<form:errors cssClass="error" path="duration" />
		<br />
		<br />
		<form:label path="room">
			<spring:message code="tutorial.room" /> :
	</form:label>
		<form:input path="room" />
		<form:errors cssClass="error" path="room" />
		<br />
		<br />
	
		<form:label path="summary">
			<spring:message code="tutorial.summary" /> :
	</form:label>
		<form:textarea path="summary" />
		<form:errors cssClass="error" path="summary" />
		<br />
		<br />
				<form:label path="attachments">
			<spring:message code="tutorial.attachments" /> :
	</form:label>
		<form:input path="attachments" placeholder="url1,url2,url3..."/>
		<form:errors cssClass="error" path="attachments" />
		<br />
		<br />


		<acme:submit code="tutorial.save" name="save" />&nbsp;
		<jstl:if test="${tutorial.id != 0 }">
		<acme:cancel
			url="tutorial/display.do?tutorialId=${tutorial.id}"
			code="tutorial.cancel" />
</jstl:if>
		<jstl:if test="${tutorial.id == 0 }">
		<acme:cancel
			url="conference/display.do?conferenceId=${conference.id }"
			code="tutorial.cancel" />
</jstl:if>
		<jstl:if test="${tutorial.id != 0 }">
			<input type="submit" name="delete"
				value="<spring:message code="tutorial.delete"/>" />

		</jstl:if>
	</form:form>
</security:authorize>





