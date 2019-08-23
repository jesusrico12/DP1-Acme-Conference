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

	<security:authorize access="hasRole('REVIEWER')">

	<table class="displayStyle">
		<tr>
			<td><strong> <spring:message code="report.decision" />
					:
			</strong></td>
			<td><jstl:out value="${report.decision}"></jstl:out></td>
		</tr>
		<tr>
			<td><strong> <spring:message code="report.originalityScore" />
					:
			</strong></td>
			<td><jstl:out value="${report.originalityScore}"></jstl:out></td>
		</tr>
				<tr>
			<td><strong> <spring:message code="report.qualityScore" />
					:
			</strong></td>
			<td><jstl:out value="${report.qualityScore}"></jstl:out></td>
		</tr>
			<tr>
			<td><strong> <spring:message code="report.readabilityScore" />
					:
			</strong></td>
			<td><jstl:out value="${report.readabilityScore}"></jstl:out></td>
		</tr>
	
		<jstl:forEach items="${report.comments}" var="comment">
		
		<tr>
			<td><strong> <spring:message code="report.comment" />
					:
			</strong></td>
			<td><jstl:out value="${comment}"></jstl:out></td>
		</tr>
		
		</jstl:forEach>
	



		</table>
		
		<jstl:if test="${timeToComment}">
		<form:form action="report/comment.do?reportId=${report.id}" mehtod="POST">
		
		<b><spring:message code="conference.addComment" />&#160;</b>
        <input id="comment" type="text" name="comment" size="50" />
        
	
	
        <input type="submit" value='<spring:message code="report.submit" />'/>
        

      </form:form>
		
</jstl:if>
	
	



</security:authorize>
	<jstl:if test="${permissionAuthor}">
	
	<table class="displayStyle">
		<tr>
			<td><strong> <spring:message code="report.decision" />
					:
			</strong></td>
			<td><jstl:out value="${report.decision}"></jstl:out></td>
		</tr>
		<tr>
			<td><strong> <spring:message code="report.originalityScore" />
					:
			</strong></td>
			<td><jstl:out value="${report.originalityScore}"></jstl:out></td>
		</tr>
				<tr>
			<td><strong> <spring:message code="report.qualityScore" />
					:
			</strong></td>
			<td><jstl:out value="${report.qualityScore}"></jstl:out></td>
		</tr>
			<tr>
			<td><strong> <spring:message code="report.readabilityScore" />
					:
			</strong></td>
			<td><jstl:out value="${report.readabilityScore}"></jstl:out></td>
		</tr>
	
		<jstl:forEach items="${report.comments}" var="comment">
		
		<tr>
			<td><strong> <spring:message code="report.comment" />
					:
			</strong></td>
			<td><jstl:out value="${comment}"></jstl:out></td>
		</tr>
		
		</jstl:forEach>
	



		</table>
		
	
	</jstl:if>


