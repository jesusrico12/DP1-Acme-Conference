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
			<td><strong> <spring:message code="tutorial.title" />
					:
			</strong></td>
			<td><jstl:out value="${tutorial.title}"></jstl:out></td>
		</tr>		
		<tr>
			<td><strong> <spring:message code="tutorial.speakers" />
					:
			</strong></td>
			<td><jstl:out value="${tutorial.speakers}"></jstl:out></td>
		</tr>
	<tr>
			<td><strong> <spring:message code="tutorial.startMoment" />
					:
			</strong></td>
			<td><fmt:formatDate type="both" dateStyle="short"
					timeStyle="short" value="${tutorial.startMoment}" /></td>
		</tr>
			<tr>
			<td><strong> <spring:message code="tutorial.duration" />
					:
			</strong></td>
			<td><jstl:out value="${tutorial.duration}"> </jstl:out></td>
			
		</tr>
		<tr>
			<td><strong> <spring:message code="tutorial.room" />
					:
			</strong></td>
			<td><jstl:out value="${tutorial.room}"></jstl:out></td>
		</tr>
				<tr>
			<td><strong> <spring:message code="tutorial.summary" />
					:
			</strong></td>
			<td><jstl:out value="${tutorial.summary}"></jstl:out></td>
		</tr>
		<jstl:if test="${not empty tutorial.attachments}">
						<tr>
			<td><strong> <spring:message code="tutorial.attachments" />
					:
			</strong>			<td>
			<jstl:forEach items="${tutorial.attachments}" var="attachment">
			<a href="${attachment}"><jstl:out value="${attachment}"></jstl:out></a>
			</jstl:forEach>
			</td>
		</tr>
		</jstl:if>

<jstl:if test="${permission}">

	<tr><td>
		<input type="button" name="edit"
			value="<spring:message code="tutorial.edit"	/>"
			onclick="redirect: location.href = 'tutorial/edit.do?tutorialId=${tutorial.id}';" />
</td>
</tr>
</jstl:if>
</table>

<display:table pagesize="10" class="displaytag" name="sections"
			 id="row" requestURI="tutorial/display.do?tutorialId=${tutorial.id}">
			 

			<display:column titleKey="section.display" >
				<input type="button" value="<jstl:out value="${row.title}"/>"
								onclick="redirect: location.href = 'section/display.do?sectionId=${row.id}';" />
			</display:column>
			<display:column titleKey="section.summary" sortable="true">
				<jstl:out value="${row.summary}"></jstl:out>
			</display:column>
			
</display:table>

	<input type="button" name="back"
		value="<spring:message code="tutorial.back" />"
		onclick="redirect: location.href = 'conference/display.do?conferenceId=${conference.id}';" />
		<jstl:if test="${permission}">
		<input type="button" value="<spring:message code="section.create"	/>"
			onclick="redirect: location.href = 'section/create.do?tutorialId=${tutorial.id}';" />
</jstl:if>


