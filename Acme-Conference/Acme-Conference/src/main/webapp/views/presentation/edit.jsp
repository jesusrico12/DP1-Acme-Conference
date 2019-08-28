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

	<form:form action="presentation/edit.do?conferenceId=${conference.id}"  modelAttribute="presentation">

		<form:hidden path="id" />
		<form:hidden path="version" />


		<form:label path="title">
			<spring:message code="presentation.title" /> :
	</form:label>
		<form:input path="title" />
		<form:errors cssClass="error" path="title" />
		<br />
		<br />

		<form:label path="speakers">
			<spring:message code="presentation.speakers" /> :
	</form:label>
	<spring:message code="presentation.speakers.placeholder" var="placeh" />
		<form:input path="speakers" placeholder='${placeh}' />
		<form:errors cssClass="error" path="speakers" />
		<br />
		<br />
		

		<form:label path="startMoment">
			<spring:message code="presentation.startMoment" /> :
	</form:label>
		<form:input path="startMoment" placeholder="dd/MM/yyyy HH:mm"/>
		<form:errors cssClass="error" path="startMoment" />
		<br />
		<br />

		<form:label path="duration">
			<spring:message code="presentation.duration" /> :
	</form:label>
		<form:input path="duration" step="1" type="number"/>
		<form:errors cssClass="error" path="duration" />
		<br />
		<br />
		<form:label path="room">
			<spring:message code="presentation.room" /> :
	</form:label>
		<form:input path="room" />
		<form:errors cssClass="error" path="room" />
		<br />
		<br />
	
		<form:label path="summary">
			<spring:message code="presentation.summary" /> :
	</form:label>
		<form:textarea path="summary" />
		<form:errors cssClass="error" path="summary" />
		<br />
		<br />
				<form:label path="attachments">
			<spring:message code="presentation.attachments" /> :
	</form:label>
		<form:input path="attachments" placeholder="url1,url2,url3..."/>
		<form:errors cssClass="error" path="attachments" />
		<br />
		<br />
		
	
		<acme:select items="${papers}" itemLabel="title"
				code="presentation.paper.title" path="paper" />
				<jstl:if test="${ empty papers }">
				<h3 class="error"><spring:message code="papers.empty" /> </h3>
				</jstl:if>
				


		<acme:submit code="presentation.save" name="save" />&nbsp;
		<jstl:if test="${presentation.id != 0 }">
		<acme:cancel
			url="presentation/display.do?presentationId=${presentation.id}"
			code="presentation.cancel" />
</jstl:if>
		<jstl:if test="${presentation.id == 0 }">
		<acme:cancel
			url="conference/display.do?conferenceId=${conference.id }"
			code="presentation.cancel" />
</jstl:if>
		<jstl:if test="${presentation.id != 0 }">
			<input type="submit" name="delete" 
				value="<spring:message code="presentation.delete"/>" />

		</jstl:if>
	</form:form>
</security:authorize>





