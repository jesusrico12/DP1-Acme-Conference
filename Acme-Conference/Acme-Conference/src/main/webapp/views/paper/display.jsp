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

<security:authorize access="hasAnyRole('AUTHOR','ADMIN','REVIEWER')">

	<jstl:if test="${possible}">
		<table class="displayStyle">
			<tr>
				<td><strong> <spring:message code="paper.title" /> :
				</strong></td>
				<td><jstl:out value="${paper.title}"></jstl:out></td>
			</tr>

			<tr>
				<td><strong> <spring:message
							code="paper.isCameraReady" /> :
				</strong></td>
				<td><jstl:out value="${paper.isCameraReady}"></jstl:out></td>
			</tr>

			<tr>
				<td><strong> <spring:message code="paper.summary" />
						:
				</strong></td>
				<td><jstl:out value="${paper.summary}"></jstl:out></td>
			</tr>




			<tr>
				<td><strong> <spring:message code="paper.document" />
						:
				</strong></td>
				<td><a href=" ${paper.document}"><jstl:out
							value="${paper.document}"></jstl:out></a></td>
			</tr>




			<tr>
				<td><strong> <spring:message code="paper.authors" />
						:
				</strong></td>
				<td><jstl:out value="${paper.authors}"></jstl:out></td>
			</tr>

		</table>

		<input type="button" name="back"
			value="<spring:message code="paper.b" />"
			onclick="window.history.back()" />

	</jstl:if>


	<jstl:if test="${!possible}">
		<spring:message code="paper.permission" var="per" />

		<jstl:out value="${per}"></jstl:out>
	</jstl:if>




</security:authorize>

