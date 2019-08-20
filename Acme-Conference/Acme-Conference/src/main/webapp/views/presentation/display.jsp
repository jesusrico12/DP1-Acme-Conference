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
						<tr>
			<td><strong> <spring:message code="presentation.attachments" />
					:
			</strong></td>
			<td><img class="picture" src="<jstl:out value="${presentation.attachments}"/>" /></td>
		</tr>
			
	


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
			<td><jstl:out value="${presentation.paper.document}"></jstl:out></td>
		</tr>
		
		
	</table>

<security:authorize access="hasRole('ADMIN')">
	<tr><td>
		<input type="button" name="edit"
			value="<spring:message code="presentation.edit"	/>"
			onclick="redirect: location.href = 'presentation/edit.do?presentationId=${presentation.id}';" />
</td>
</tr>

</security:authorize>

	<input type="button" name="back"
		value="<spring:message code="presentation.back" />"
		onclick="window.history.back()" />


