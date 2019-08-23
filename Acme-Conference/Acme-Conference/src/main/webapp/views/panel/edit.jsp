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

	<form:form action="panel/edit.do?conferenceId=${conference.id}"  modelAttribute="panel">

		<form:hidden path="id" />
		<form:hidden path="version" />


		<form:label path="title">
			<spring:message code="panel.title" /> :
	</form:label>
		<form:input path="title" />
		<form:errors cssClass="error" path="title" />
		<br />
		<br />

		<form:label path="speakers">
			<spring:message code="panel.speakers" /> :
	</form:label>
	<spring:message code="panel.speakers.placeholder" var="placeh" />
		<form:input path="speakers" placeholder='${placeh}' />
		<form:errors cssClass="error" path="speakers" />
		<br />
		<br />
		

		<form:label path="startMoment">
			<spring:message code="panel.startMoment" /> :
	</form:label>
		<form:input path="startMoment" placeholder="dd/MM/yyyy HH:mm"/>
		<form:errors cssClass="error" path="startMoment" />
		<br />
		<br />

		<form:label path="duration">
			<spring:message code="panel.duration" /> :
	</form:label>
		<form:input path="duration" step="1" type="number" />
		<form:errors cssClass="error" path="duration" />
		<br />
		<br />
		<form:label path="room">
			<spring:message code="panel.room" /> :
	</form:label>
		<form:input path="room" />
		<form:errors cssClass="error" path="room" />
		<br />
		<br />
	
		<form:label path="summary">
			<spring:message code="panel.summary" /> :
	</form:label>
		<form:textarea path="summary" />
		<form:errors cssClass="error" path="summary" />
		<br />
		<br />
				<form:label path="attachments">
			<spring:message code="panel.attachments" /> :
	</form:label>
		<form:input path="attachments" placeholder="url1,url2,url3..."/>
		<form:errors cssClass="error" path="attachments" />
		<br />
		<br />


		<acme:submit code="panel.save" name="save" />&nbsp;
		<jstl:if test="${panel.id != 0 }">
		<acme:cancel
			url="panel/display.do?panelId=${panel.id}"
			code="panel.cancel" />
</jstl:if>
		<jstl:if test="${panel.id == 0 }">
		<acme:cancel
			url="conference/display.do?conferenceId=${conference.id }"
			code="panel.cancel" />
</jstl:if>
		<jstl:if test="${panel.id != 0 }">
			<input type="submit" name="delete"
				value="<spring:message code="panel.delete"/>" />

		</jstl:if>
	</form:form>
</security:authorize>





