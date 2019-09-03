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

<security:authorize access="hasRole('AUTHOR')">
	

		<display:table pagesize="10" class="displaytag" name="submissions"
			requestURI="submission/author/listUpload.do" id="row">



			<display:column titleKey="submission.ticker" sortable="true">
				<jstl:out value="${row.ticker}"></jstl:out>
			</display:column>

			<display:column titleKey="submission.paper.title" sortable="true">
				<jstl:out value="${row.paper.title}"></jstl:out>
			</display:column>

			
			<display:column>
				<a href="paper/edit.do?paperId=${row.paper.id}"> <spring:message
						code="submission.upload" />
				</a>

			</display:column>

		</display:table>

	


	


</security:authorize>