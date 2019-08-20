<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<h3>
	<b><spring:message code="message.subject" />: </b>
	<jstl:out value="${message0.subject}" />
</h3>
<jstl:if test="${not empty messageCode}">
	<h4>
		<spring:message code="${messageCode}" />
	</h4>
</jstl:if>

<b><spring:message code="message.actor.sender" />: </b>
<jstl:out value="${message0.sender.userAccount.username}" />
<br />

<b><spring:message code="message.actor.recipient" />: </b>
<jstl:out value="${message0.receiver.userAccount.username}" />
<br />

<b><spring:message code="message.sendTime" />: </b>
<jstl:out value="${message0.sendMoment}" />
<br />


<b><spring:message code="message.body" />: </b>
<jstl:out value="${message0.body}" />
<br />



<jstl:if test="${language==español}">
	<b><spring:message code="message.topic" />: </b> 
	<jstl:out value="${message0.topic.get('Español')}" />
</jstl:if>


<jstl:if test="${language==english}">
	<b><spring:message code="message.topic" />: </b> 
	<jstl:out value="${message0.topic.get('English')}" />
</jstl:if>



<br>

<button onClick="window.location.href='message/actor/list.do'">
	<spring:message code="message.cancel" />
</button>