<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>


<security:authorize access="hasRole('AUTHOR')">
	<jstl:if test="${paper.id == 0}">
		<form:form action="paper/edit.do?conferenceId=${conferenceId}" modelAttribute="paper" id="form">

			<form:hidden path="id" />
			<form:hidden path="version" />
			<form:hidden path="isCameraReady" />
			

			<form:label path="title">
				<spring:message code="paper.title" /> :
	</form:label>
			<form:input path="title" />
			<form:errors cssClass="error" path="title" />
			<br />
			<br />

			<form:label path="authors">
				<spring:message code="paper.authors" /> :
	</form:label>
			<form:input path="authors" />
			<form:errors cssClass="error" path="authors" />
			<br />
			<br />

			<form:label path="summary">
				<spring:message code="paper.summary" /> :
	</form:label>
			<form:textarea path="summary" />
			<form:errors cssClass="error" path="summary" />
			<br />
			<br />

			<form:label path="document">
				<spring:message code="paper.document" /> :
	</form:label>
			<form:input path="document" />
			<form:errors cssClass="error" path="document" />
			<br />
			<br />

			<input type="submit" name="save"
				value="<spring:message code="paper.save"/>" />&nbsp;
	
	
		<input type="button" name="back"
				value="<spring:message code="paper.back" />"
				onclick="window.history.back()" />


		</form:form>


	</jstl:if>
	
	
	<jstl:if test="${paper.id!=0}">
		<form:form action="paper/edit.do?conferenceId=${conferenceId}" modelAttribute="paper" id="form">

			<form:hidden path="id" />
			<form:hidden path="version" />
			<form:hidden path="isCameraReady" />
			

			<form:label path="title">
				<spring:message code="paper.title" /> :
	</form:label>
			<form:input path="title" />
			<form:errors cssClass="error" path="title" />
			<br />
			<br />

			<form:label path="authors">
				<spring:message code="paper.authors" /> :
	</form:label>
			<form:input path="authors" />
			<form:errors cssClass="error" path="authors" />
			<br />
			<br />

			<form:label path="summary">
				<spring:message code="paper.summary" /> :
	</form:label>
			<form:textarea path="summary" />
			<form:errors cssClass="error" path="summary" />
			<br />
			<br />

			<form:label path="document">
				<spring:message code="paper.document" /> :
	</form:label>
			<form:input path="document" />
			<form:errors cssClass="error" path="document" />
			<br />
			<br />

			<input type="submit" name="save"
				value="<spring:message code="paper.save"/>" />&nbsp;
	
	
		<input type="button" name="back"
				value="<spring:message code="paper.back" />"
				onclick="window.history.back()" />


		</form:form>
	</jstl:if>
</security:authorize>





