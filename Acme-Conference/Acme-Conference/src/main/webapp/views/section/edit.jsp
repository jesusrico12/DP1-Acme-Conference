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

	<form:form action="section/edit.do?tutorialId=${tutorial.id}"  modelAttribute="section">

		<form:hidden path="id" />
		<form:hidden path="version" />


		<form:label path="title">
			<spring:message code="section.title" /> :
	</form:label>
		<form:input path="title" />
		<form:errors cssClass="error" path="title" />
		<br />
		<br />

		
	
		<form:label path="summary">
			<spring:message code="section.summary" /> :
	</form:label>
		<form:textarea path="summary" />
		<form:errors cssClass="error" path="summary" />
		<br />
		<br />
				<form:label path="pictures">
			<spring:message code="section.pictures" /> :
	</form:label>
		<form:input path="pictures" placeholder="url1,url2,url3..."/>
		<form:errors cssClass="error" path="pictures" />
		<br />
		<br />


		<acme:submit code="section.save" name="save" />&nbsp;
		
		<acme:cancel
			url="tutorial/display.do?tutorialId=${tutorial.id }"
			code="section.cancel" />

		<jstl:if test="${section.id != 0 }">
			<input type="submit" name="delete"
				value="<spring:message code="section.delete"/>" />

		</jstl:if>
	</form:form>
	
</security:authorize>





