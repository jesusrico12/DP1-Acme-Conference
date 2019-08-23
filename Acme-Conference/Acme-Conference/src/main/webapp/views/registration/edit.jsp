<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<security:authorize access="hasRole('AUTHOR')">

	<form:form action="registration/edit.do"
		modelAttribute="registration">

		<form:hidden path="id" />
		<form:hidden path="version" />
		<form:hidden path="author" />
		<form:hidden path="conference" />

		<form:label path="creditCard.holder">
			<spring:message code="registration.holder" /> :
	</form:label>
		<form:input path="creditCard.holder" />
		<form:errors cssClass="error" path="creditCard.holder" />
		<br />
		<br />
		
		<form:label path="creditCard.make">
			<spring:message code="registration.make" /> :
	</form:label>

		<form:select path="creditCard.make" id="makes">
			<form:options items="${makes}" />
			
		</form:select>
		<form:errors cssClass="error" path="creditCard.make" />
		<br />
		<br />

		<form:label path="creditCard.number">
			<spring:message code="registration.number" /> :
	</form:label>
		<form:input path="creditCard.number" placeholder="4387749971680407"/>
		<form:errors cssClass="error" path="creditCard.number" />
		<br />
		<br />


		<form:label path="creditCard.expirationMonth">
			<spring:message code="registration.expirationMonth" /> :
	</form:label>
		<form:input path="creditCard.expirationMonth" step="1" type="number"/>
		<form:errors cssClass="error" path="creditCard.expirationMonth" />
		<br />
		<br />



		

		<form:label path="creditCard.expirationYear">
			<spring:message code="registration.expirationYear" /> :
	</form:label>
		<form:input path="creditCard.expirationYear" step="1" type="number" />
		<form:errors cssClass="error" path="creditCard.expirationYear" />
		<br />
		<br />
		
				<form:label path="creditCard.CVV">
			<spring:message code="registration.CVV" /> :
	</form:label>
		<form:input path="creditCard.CVV" step="1" type="number"/>
		<form:errors cssClass="error" path="creditCard.CVV" />
		<br />
		<br />
		


		<input type="submit" name="save"
			value="<spring:message code="resgitration.save"/>" />&nbsp;
				
	<input type="button" name="back"
			value="<spring:message code="registration.back" />"
			onclick="redirect: location.href = 'registration/list.do';" />
	</form:form>
</security:authorize>





