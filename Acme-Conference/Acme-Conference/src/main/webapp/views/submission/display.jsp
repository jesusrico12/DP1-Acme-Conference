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

<security:authorize access="hasAnyRole('AUTHOR','ADMIN')">

	<table class="displayStyle">
		<tr>
			<td><strong> <spring:message code="submission.ticker" />
					:
			</strong></td>
			<td><jstl:out value="${submission.ticker}"></jstl:out></td>
		</tr>

		<tr>
			<td><strong> <spring:message code="submission.status" />
					:
			</strong></td>
			<td><jstl:out value="${submission.status}"></jstl:out></td>
		</tr>

		<tr>
			<td><strong> <spring:message code="submission.toReview" />
					:
			</strong></td>
			<td><jstl:out value="${submission.toReview}"></jstl:out></td>
		</tr>

		<tr>
			<td><strong> <spring:message
						code="submission.madeMoment" /> :
			</strong></td>

			<td><fmt:formatDate type="both" dateStyle="short"
					timeStyle="short" value="${submission.madeMoment}" /></td>
		</tr>



		<tr>
			<td><strong> <spring:message code="submission.author" />
					:
			</strong></td>
			<td><jstl:out value="${submission.author.userAccount.username}"></jstl:out></td>
		</tr>

		<tr>
			<td><strong> <spring:message
						code="submission.conference" /> :
			</strong></td>
			<td><jstl:out value="${submission.conference.title}"></jstl:out></td>
		</tr>

		<tr>
			<td><strong> <spring:message
						code="submission.reviewers" /> :
			</strong></td>

			<jstl:forEach items="${submission.reviewers}" var="x">

				<td><jstl:out value="${x.userAccount.username}"></jstl:out></td>
			</jstl:forEach>

		</tr>

		<tr>
			<td><strong> <spring:message code="submission.reports" />
					:
			</strong></td>

			<jstl:forEach items="${submission.reports}" var="x">

				<td><jstl:out value="${x.originalityScore}"></jstl:out></td>
			</jstl:forEach>

		</tr>

	</table>

	<input type="button" name="back"
		value="<spring:message code="submission.back" />"
		onclick="window.history.back()" />

	<spring:message code="submission.display.paper" var="disPaper">
		
	</spring:message>
	
	<a href="paper/display.do?paperId=${paper.id}">
		<jstl:out value="${disPaper}"></jstl:out>
	</a>
</security:authorize>