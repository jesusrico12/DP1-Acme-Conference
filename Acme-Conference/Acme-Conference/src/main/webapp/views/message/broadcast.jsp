<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>
<jstl:set var="localeCode" value="${pageContext.response.locale}" />

<security:authorize access="hasRole('ADMIN')">
	<jstl:if test="${(mensaje.id == 0) && (possible) && (broadcast)}">

		<h3>Broadcast Message</h3>
		<form:form
			action="message/actor/broadcast.do?conferenceId=${conferenceId}"
			modelAttribute="mensaje" method="POST">

			<form:hidden path="id" />
			<form:hidden path="version" />
			<form:hidden path="sendMoment" />
			<form:hidden path="sender" />
			<form:hidden path="receiver" />


			<spring:message code="subject.notBlank" var="subNB"></spring:message>
			<jstl:if test="${isSubject}">
				<h4 style="color: red;">
					<jstl:out value="${subNB}" />
				</h4>
			</jstl:if>
			<acme:textbox code="message.subject" path="subject" />
			<br>
			<br>

			<spring:message code="body.notBlank" var="bodNB"></spring:message>
			<jstl:if test="${isBody}">
				<h4 style="color: red;">
					<jstl:out value="${bodNB}" />
				</h4>
			</jstl:if>

			<spring:message code="message.body" />
			<br />
			<form:textarea code="message.body" path="body" />
			<br>
			<br>

			<spring:message code="topic.notNull" var="topicNN"></spring:message>
			<jstl:if test="${isTopic}">
				<h4 style="color: red;">
					<jstl:out value="${topicNN}" />
				</h4>
			</jstl:if>

			<form:label path="topic">
				<spring:message code="message.topic" /> :
	</form:label>

			<jstl:if test="${localeCode == 'es'}">
				<form:select multiple="false" path="topic" style="width:400px;"
					name="topic" id="topic">
					<jstl:forEach var="x" items="${allEsp}">

						<form:option value="${x}" label="${x}" name="topic" id="topic" />
					</jstl:forEach>

				</form:select>

				<form:errors cssClass="error" path="topic" />
				<br />
				<br />


			</jstl:if>

			<jstl:if test="${localeCode == 'en'}">
				<form:select multiple="false" path="topic" style="width:400px;"
					name="topic" id="topic">
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
		
			<input type="submit" name="saveAuthorsSubmission"
				value="<spring:message code="message.broadcast.authors.submission"/>" />&nbsp;
			
			
			<input type="submit" name="saveAuthorsRegistration"
				value="<spring:message code="message.broadcast.authors.registration"/>" />&nbsp;
			
			<input type="submit" name="saveAuthors"
				value="<spring:message code="message.broadcast.authors"/>" />&nbsp;
		
		
			<acme:cancel code="message.cancel" url="message/actor/list.do" />

			<br />

		</form:form>



	</jstl:if>





</security:authorize>