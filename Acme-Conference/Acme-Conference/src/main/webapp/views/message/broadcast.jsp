<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<security:authorize access="hasRole('ADMIN')">
	<jstl:if test="${(mensaje.id == 0) && (possible) && (broadcast)}">

		<h3>Broadcast Message</h3>
		<form:form action="message/actor/broadcast.do"
			modelAttribute="mensaje">

			<form:hidden path="id" />
			<form:hidden path="version" />
			<form:hidden path="sendMoment" />
			<form:hidden path="sender" />
			<form:hidden path="receiver" />

			<form:label path="subject">
				<spring:message code="message.subject" />:
	</form:label>
			<form:input path="subject" />
			<form:errors cssClass="error" path="subject" />
			<br>
			<br>

			<form:label path="body">
				<spring:message code="message.body" />:
	</form:label>
			<form:textarea path="body" />
			<form:errors cssClass="error" path="body" />
			<br>
			<br>


			<form:label path="topic">
				<spring:message code="message.topic" /> :
	</form:label>

			<jstl:if test="${language==español}">
				<form:select path="topic" style="width:400px;" name="topic"
					id="topic">
					<jstl:forEach var="x" items="${allEsp}">
						<form:option value="${x}" label="${x}" name="topic" id="topic" />
					</jstl:forEach>

				</form:select>

				<form:errors cssClass="error" path="topic" />
				<br />
				<br />


			</jstl:if>

			<jstl:if test="${language==english}">
				<form:select path="topic" style="width:400px;" name="topic"
					id="topic">
					<jstl:forEach var="x" items="${allEn}">
						<form:option value="${x}" label="${x}" name="topic" id="topic" />
					</jstl:forEach>

				</form:select>

				<form:errors cssClass="error" path="topic" />
				<br />
				<br />


			</jstl:if>



			<input type="submit" name="save"
				value="<spring:message code="message.broadcast"/>" />&nbsp;
		

			<acme:cancel code="message.cancel" url="message/actor/list.do" />

			<br />

		</form:form>



	</jstl:if>





</security:authorize>