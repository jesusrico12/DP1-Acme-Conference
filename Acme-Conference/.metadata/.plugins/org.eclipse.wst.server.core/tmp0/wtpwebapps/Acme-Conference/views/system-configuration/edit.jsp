<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags"%>


<security:authorize access="hasRole('ADMIN')">

	<form:form action="sysconfig/administrator/edit.do"
		modelAttribute="sysConfig" methodParam="post">

		<form:hidden path="id" />
		<form:hidden path="version" />
		

		<acme:textbox code="system.name" path="systemName" />
		<br>
		<br>

		<acme:textbox code="system.bannerURL" path="banner" />
		<br>
		<br>



		<acme:textbox code="system.countrycode" path="countryCode" />
		<br>
		<br>
			<acme:textarea code="system.makes" path="creditCardMakes" />
		<br>
		<br>



		<p>
			<spring:message code="wel.name.es" />
		</p>
		<input type="text" name="nameES" id="nameES" size="100%"
			value="${sysConfig.welcomeMessage.get('Español')}"
			placeholder="<spring:message code='sysconfig.edit.welcome.message.es' />"
			required>

		<form:errors cssClass="error" path="welcomeMessage" />
		<br />
		<br />

		<p>
			<spring:message code="wel.name.en" />
		</p>
		<input type="text" name="nameEN" id="nameEN" size="100%"
			value="${sysConfig.welcomeMessage.get('English')}"
			placeholder="<spring:message code='sysconfig.edit.welcome.message.en' />"
			required>
		<form:errors cssClass="error" path="welcomeMessage" />
		<br />
		<br />
				<p>
			<spring:message code="topics.name.es" />
		</p>
		<input type="text" name="topicsES" id="topicsES" size="100%"
			value="${sysConfig.topics.get('Español')}"
			placeholder="<spring:message code='sysconfig.edit.topics.es' />"
			required>

		<form:errors cssClass="error" path="topics" />
		<br />
		<br />

		<p>
			<spring:message code="topics.name.en" />
		</p>
		<input type="text" name="topicsEN" id="topicsEN" size="100%"
			value="${sysConfig.topics.get('English')}"
			placeholder="<spring:message code='sysconfig.edit.topics.en' />"
			required>
		<form:errors cssClass="error" path="topics" />
		<br />
		<br />

		
		
		<acme:submit code="system.save" name="save" />&nbsp;
		<acme:cancel code="system.cancel"
			url="sysconfig/administrator/display.do" />

		<br />
		<br />

		<jstl:if test="${not empty errMessages}">
			<jstl:forEach items="${errMessages}" var="err">
				<div class="error">
					<spring:message code="${err}" />
					<br />
				</div>
			</jstl:forEach>
		</jstl:if>
	</form:form>

</security:authorize>