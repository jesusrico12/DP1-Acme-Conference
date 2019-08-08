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

<security:authorize access="isAnonymous()">

	<table class="displayStyle">
		<tr>
			<td><strong> <spring:message code="conference.title" />
					:
			</strong></td>
			<td><jstl:out value="${conference.title}"></jstl:out></td>
		</tr>

		<tr>
			<td><strong> <spring:message code="conference.acronym" />
					:
			</strong></td>
			<td><jstl:out value="${conference.acronym}"></jstl:out></td>
		</tr>

		<tr>
			<td><strong> <spring:message code="conference.venue" />
					:
			</strong></td>
			<td><jstl:out value="${conference.venue}"></jstl:out></td>
		</tr>

		<tr>
			<td><strong> <spring:message
						code="conference.submissionDL" /> :
			</strong></td>

			<td><fmt:formatDate type="both" dateStyle="short"
					timeStyle="short" value="${conference.submissionDeadline}" /></td>
		</tr>

		<tr>
			<td><strong> <spring:message
						code="conference.notificationDL" /> :
			</strong></td>
			<td><fmt:formatDate type="both" dateStyle="short"
					timeStyle="short" value="${conference.notificationDeadline}" /></td>

		</tr>

		<tr>
			<td><strong> <spring:message
						code="conference.cameraReadyDL" /> :
			</strong></td>
			<td><fmt:formatDate type="both" dateStyle="short"
					timeStyle="short" value="${conference.cameraReadyDeadline}" /></td>
		</tr>

		<tr>
			<td><strong> <spring:message
						code="conference.startDate" /> :
			</strong></td>
			<td><fmt:formatDate type="both" dateStyle="short"
					timeStyle="short" value="${conference.startDate}" /></td>
		</tr>

		<tr>
			<td><strong> <spring:message code="conference.endDate" />
					:
			</strong></td>
			<td><fmt:formatDate type="both" dateStyle="short"
					timeStyle="short" value="${conference.endDate}" /></td>
		</tr>

		<tr>
			<td><strong> <spring:message code="conference.summary" />
					:
			</strong></td>
			<td><jstl:out value="${conference.summary}"></jstl:out></td>
		</tr>

		<tr>
			<td><strong> <spring:message code="conference.fee" />
					:
			</strong></td>
			<td><jstl:out value="${conference.fee}"></jstl:out></td>
		</tr>

		<tr>
			<td><strong> <spring:message code="conference.isDraft" />
					:
			</strong></td>
			<td><jstl:out value="${conference.isDraft}"></jstl:out></td>
		</tr>

		<jstl:if test="${isActivity == true}">
			<tr>
				<td><strong> <spring:message
							code="conference.activities" /> :
				</strong></td>


				<td><jstl:forEach items="${activities}" var="x">
						<jstl:out value="${x.title}"></jstl:out>
					</jstl:forEach></td>

			</tr>
		</jstl:if>
		<tr>
			<td><strong> <spring:message
						code="conference.administrator" /> :
			</strong></td>

			<td><jstl:out
					value="${conference.administrator.userAccount.username}">

				</jstl:out></td>
		</tr>



	</table>

	<input type="button" name="back"
		value="<spring:message code="conference.back" />"
		onclick="window.history.back()" />
</security:authorize>

<security:authorize access="hasRole('ADMIN')">
	<table class="displayStyle">
		<tr>
			<td><strong> <spring:message code="conference.title" />
					:
			</strong></td>
			<td><jstl:out value="${conference.title}"></jstl:out></td>
		</tr>

		<tr>
			<td><strong> <spring:message code="conference.acronym" />
					:
			</strong></td>
			<td><jstl:out value="${conference.acronym}"></jstl:out></td>
		</tr>

		<tr>
			<td><strong> <spring:message code="conference.venue" />
					:
			</strong></td>
			<td><jstl:out value="${conference.venue}"></jstl:out></td>
		</tr>

		<tr>
			<td><strong> <spring:message
						code="conference.submissionDL" /> :
			</strong></td>

			<td><fmt:formatDate type="both" dateStyle="short"
					timeStyle="short" value="${conference.submissionDeadline}" /></td>
		</tr>

		<tr>
			<td><strong> <spring:message
						code="conference.notificationDL" /> :
			</strong></td>
			<td><fmt:formatDate type="both" dateStyle="short"
					timeStyle="short" value="${conference.notificationDeadline}" /></td>

		</tr>

		<tr>
			<td><strong> <spring:message
						code="conference.cameraReadyDL" /> :
			</strong></td>
			<td><fmt:formatDate type="both" dateStyle="short"
					timeStyle="short" value="${conference.cameraReadyDeadline}" /></td>
		</tr>

		<tr>
			<td><strong> <spring:message
						code="conference.startDate" /> :
			</strong></td>
			<td><fmt:formatDate type="both" dateStyle="short"
					timeStyle="short" value="${conference.startDate}" /></td>
		</tr>

		<tr>
			<td><strong> <spring:message code="conference.endDate" />
					:
			</strong></td>
			<td><fmt:formatDate type="both" dateStyle="short"
					timeStyle="short" value="${conference.endDate}" /></td>
		</tr>

		<tr>
			<td><strong> <spring:message code="conference.summary" />
					:
			</strong></td>
			<td><jstl:out value="${conference.summary}"></jstl:out></td>
		</tr>

		<tr>
			<td><strong> <spring:message code="conference.fee" />
					:
			</strong></td>
			<td><jstl:out value="${conference.fee}"></jstl:out></td>
		</tr>

		<tr>
			<td><strong> <spring:message code="conference.isDraft" />
					:
			</strong></td>
			<td><jstl:out value="${conference.isDraft}"></jstl:out></td>
		</tr>

		<jstl:if test="${isActivity == true}">
			<tr>
				<td><strong> <spring:message
							code="conference.activities" /> :
				</strong></td>


				<td><jstl:forEach items="${activities}" var="x">
						<jstl:out value="${x.title}"></jstl:out>
					</jstl:forEach></td>

			</tr>
		</jstl:if>
		<tr>
			<td><strong> <spring:message
						code="conference.administrator" /> :
			</strong></td>

			<td><jstl:out
					value="${conference.administrator.userAccount.username}">

				</jstl:out></td>
		</tr>



	</table>

	<jstl:if test="${conference.isDraft}">
		<input type="button" name="edit"
			value="<spring:message code="conference.edit"	/>"
			onclick="redirect: location.href = 'conference/edit.do?conferenceId=${conference.id}';" />

	</jstl:if>

	<input type="button" name="back"
		value="<spring:message code="conference.back" />"
		onclick="window.history.back()" />


</security:authorize>