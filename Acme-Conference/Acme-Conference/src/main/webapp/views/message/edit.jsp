<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>


<jstl:if test="${possible == true && mensaje.id==0 && (!broadcast)}">
	<jstl:if test="${(mensaje.id == 0) && (!broadcast)}">
		<form:form action="message/actor/edit.do" modelAttribute="mensaje">

			<form:hidden path="id" />
			<form:hidden path="version" />
			<form:hidden path="sendMoment" />
			<form:hidden path="sender" />

			<form:label path="receiver">
				<spring:message code="message.receiver.userAccount" />:
	</form:label>
			<form:select path="receiver">
				<form:option label="-----" value="0" />
				<form:options items="${recipients}" itemLabel="userAccount.username"
					itemValue="id" />
			</form:select>
			<form:errors cssClass="error" path="receiver" />
			<br />
			<br />


			<acme:textbox code="message.subject" path="subject" />
			<br>
			<br>

			<spring:message code="message.body" />
			<br />
			<form:textarea code="message.body" path="body" />
			<br>
			<br>
			
			<jstl:out value="${allEsp}"></jstl:out>
			<jstl:if test="${language==español}">

			
			</jstl:if>

			<jstl:if test="${language==english}">

				<form:select path="topic" style="width:400px;">
					<jstl:forEach var="x" items="${topics}">
						<form:options value="${x.get('English')}" />
					</jstl:forEach>

				</form:select>
			</jstl:if>


			<jstl:if test="${mensaje.id == 0}">
				<acme:submit code="message.send" name="save" />&nbsp;
  			</jstl:if>

			<jstl:if test="${mensaje.id != 0}">
				<acme:submit code="message.delete" name="delete" />&nbsp;
  			</jstl:if>


			<acme:cancel code="message.cancel" url="message/actor/list.do" />
			<br />
			<br />
		</form:form>
	</jstl:if>
</jstl:if>




