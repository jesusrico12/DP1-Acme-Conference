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
			<td><strong> <spring:message code="presentation.title" />
					:
			</strong></td>
			<td><jstl:out value="${presentation.title}"></jstl:out></td>
		</tr>		
		<tr>
			<td><strong> <spring:message code="presentation.speakers" />
					:
			</strong></td>
			<td><jstl:out value="${presentation.speakers}"></jstl:out></td>
		</tr>
	<tr>
			<td><strong> <spring:message code="presentation.startMoment" />
					:
			</strong></td>
			<td><fmt:formatDate type="both" dateStyle="short"
					timeStyle="short" value="${presentation.startMoment}" /></td>
		</tr>
			<tr>
			<td><strong> <spring:message code="presentation.duration" />
					:
			</strong></td>
			<td><jstl:out value="${presentation.duration}"> </jstl:out></td>
			
		</tr>
		<tr>
			<td><strong> <spring:message code="presentation.room" />
					:
			</strong></td>
			<td><jstl:out value="${presentation.room}"></jstl:out></td>
		</tr>
				<tr>
			<td><strong> <spring:message code="presentation.summary" />
					:
			</strong></td>
			<td><jstl:out value="${presentation.summary}"></jstl:out></td>
		</tr>
		<jstl:if test="${not empty presentation.attachments}">
						<tr>
			<td><strong> <spring:message code="presentation.attachments" />
					:
			</strong><td>
			<jstl:forEach items="${presentation.attachments}" var="attachment">
			<a href="${attachment}"><jstl:out value="${attachment}"></jstl:out></a>
			</jstl:forEach>
			</td>
		</tr>
			
	</jstl:if>


</table>
<h3 style="center;"><spring:message code="presentation.paper" /></h3>
	<table class="displayStyle">
	
			<tr>
			<td><strong> <spring:message code="presentation.paper.title" />
					:
			</strong></td>
			<td><jstl:out value="${presentation.paper.title}"></jstl:out></td>
		</tr>
			<tr>
			<td><strong> <spring:message code="presentation.paper.authors" />
					:
			</strong></td>
			<td><jstl:out value="${presentation.paper.authors}"></jstl:out></td>
		</tr>
					<tr>
			<td><strong> <spring:message code="presentation.paper.summary" />
					:
			</strong></td>
			<td><jstl:out value="${presentation.paper.summary}"></jstl:out></td>
		</tr>
						<tr>
			<td><strong> <spring:message code="presentation.paper.document" />
					:
			</strong></td>
	
			<td><a href="${presentation.paper.document}"><jstl:out value="${presentation.paper.document}"></jstl:out></a></td>
		</tr>
		
		
	</table>

<security:authorize access="hasRole('ADMIN')">
<jstl:if test="${permission}">
	<tr><td>
		<input type="button" name="edit"
			value="<spring:message code="presentation.edit"	/>"
			onclick="redirect: location.href = 'presentation/edit.do?presentationId=${presentation.id}';" />
</td>
</tr>
</jstl:if>

</security:authorize>

	<input type="button" name="back"
		value="<spring:message code="presentation.back" />"
		onclick="redirect: location.href = 'conference/display.do?conferenceId=${conference.id}';" />


