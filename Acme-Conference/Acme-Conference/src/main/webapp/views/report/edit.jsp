<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<security:authorize access="hasRole('REVIEWER')">

	<form:form action="report/edit.do?submissionId=${submissionId}"
		modelAttribute="report">

		<form:hidden path="id" />
		<form:hidden path="version" />
		<form:hidden path="reviewer" />

		<form:label path="originalityScore">
			<spring:message code="report.originalityScore" /> :
	</form:label>
		<form:input path="originalityScore"  step="0.1" type="number" />
		<form:errors cssClass="error" path="originalityScore" />
		<br />
		<br />

		<form:label path="qualityScore">
			<spring:message code="report.qualityScore" /> :
	</form:label>
		<form:input path="qualityScore"  step="0.1" type="number"/>
		<form:errors cssClass="error" path="qualityScore" />
		<br />
		<br />


		<form:label path="readabilityScore">
			<spring:message code="report.readabilityScore" /> :
	</form:label>
		<form:input path="readabilityScore" step="0.1" type="number"/>
		<form:errors cssClass="error" path="readabilityScore" />
		<br />
		<br />



		<form:label path="decision">
			<spring:message code="report.decision" /> :
	</form:label>

		<form:select path="decision" id="decisions">
			<form:option value="ACCEPT" />
			<form:option value="REJECT" />
			<form:option value="BORDER-LINE" />
		</form:select>
		<form:errors cssClass="error" path="decision" />
		<br />
		<br />


		<form:label path="comments">
			<spring:message code="report.comments" /> :
	</form:label>
		<form:input path="comments" />
		<form:errors cssClass="error" path="comments" />
		<br />
		<br />


		<input type="submit" name="save"
			value="<spring:message code="report.save"/>" />&nbsp;
				
		<input type="button" name="back"
			value="<spring:message code="report.back" />"
			onclick="redirect: location.href = 'report/list.do';" />
			
	</form:form>
</security:authorize>





