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
			<td><strong> <spring:message code="section.title" />
					:
			</strong></td>
			<td><jstl:out value="${section.title}"></jstl:out></td>
		</tr>		

				<tr>
			<td><strong> <spring:message code="section.summary" />
					:
			</strong></td>
			<td><jstl:out value="${section.summary}"></jstl:out></td>
		</tr>
						<tr>
			<td><strong> <spring:message code="section.pictures" />:
					
			</strong></td>
			<td>
			<jstl:forEach items="${section.pictures}" var="picture">
			<img class="picture" src="<jstl:out value="${picture}"/>" />
			</jstl:forEach>
			</td>
		</tr>
		



	<tr><td>
		<input type="button" name="edit"
			value="<spring:message code="section.edit"	/>"
			onclick="redirect: location.href = 'section/edit.do?sectionId=${section.id}';" />
</td>
</tr>
</table>

	<input type="button" name="back"
		value="<spring:message code="section.back" />"
		onclick="window.history.back()" />



