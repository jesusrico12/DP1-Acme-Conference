<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>





	<table class="displayStyle">
		<tr>
			<td><strong> <spring:message code="panel.title" />
					:
			</strong></td>
			<td><jstl:out value="${panel.title}"></jstl:out></td>
		</tr>		
		<tr>
			<td><strong> <spring:message code="panel.speakers" />
					:
			</strong></td>
			<td><jstl:out value="${panel.speakers}"></jstl:out></td>
		</tr>
	<tr>
			<td><strong> <spring:message code="panel.startMoment" />
					:
			</strong></td>
			<td><fmt:formatDate type="both" dateStyle="short"
					timeStyle="short" value="${panel.startMoment}" /></td>
		</tr>
			<tr>
			<td><strong> <spring:message code="panel.duration" />
					:
			</strong></td>
			<td><jstl:out value="${panel.duration}"> </jstl:out></td>
			
		</tr>
		<tr>
			<td><strong> <spring:message code="panel.room" />
					:
			</strong></td>
			<td><jstl:out value="${panel.room}"></jstl:out></td>
		</tr>
				<tr>
			<td><strong> <spring:message code="panel.summary" />
					:
			</strong></td>
			<td><jstl:out value="${panel.summary}"></jstl:out></td>
		</tr>
						<tr>
			<td><strong> <spring:message code="panel.attachments" />
					:
			</strong></td>
			<td>
			<jstl:forEach items="${panel.attachments}" var="attachment">
			<a href="${attachment}"><jstl:out value="${attachment}"></jstl:out></a>
			</jstl:forEach>
			</td>
		</tr>
		



	<tr><td>
		<input type="button" name="edit"
			value="<spring:message code="panel.edit"	/>"
			onclick="redirect: location.href = 'panel/edit.do?panelId=${panel.id}';" />
</td>
</tr>
</table>

	<input type="button" name="back"
		value="<spring:message code="panel.back" />"
		onclick="window.history.back()" />



