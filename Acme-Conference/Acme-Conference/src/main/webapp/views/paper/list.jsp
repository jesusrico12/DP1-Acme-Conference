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

<security:authorize access="hasAnyRole('AUTHOR','REVIEWER')">
	<jstl:if test="${isForthcoming == true}">

		<spring:message code="forthcoming.title" var="fortitle" />

		<h3>
			<jstl:out value="${fortitle}"></jstl:out>
		</h3>


		<display:table pagesize="10" class="displaytag" name="forthcoming"
			requestURI="conference/list.do" id="row">



			<display:column titleKey="conference.title">
				<jstl:out value="${row.title}"></jstl:out>
			</display:column>

			<display:column titleKey="conference.acronym">
				<jstl:out value="${row.acronym}"></jstl:out>
			</display:column>

			<display:column titleKey="conference.venue">
				<jstl:out value="${row.venue}"></jstl:out>
			</display:column>

			<display:column titleKey="conference.startDate">
				<fmt:formatDate type="both" dateStyle="short" timeStyle="short"
					value="${row.startDate}" />
			</display:column>

			<display:column titleKey="conference.endDate">
				<fmt:formatDate type="both" dateStyle="short" timeStyle="short"
					value="${row.endDate}" />

			</display:column>

			<display:column>
				<a href="conference/display.do?conferenceId=${row.id}"> <spring:message
						code="conference.display" />
				</a>

			</display:column>

		</display:table>

	</jstl:if>


	<jstl:if test="${isForthcoming == false}">

		<spring:message code="forthcoming.title" var="fortitle" />



		<h3>
			<jstl:out value="${fortitle}"></jstl:out>
		</h3>

		<spring:message code="conference.zero" var="zero" />

		<p>
			<jstl:out value="${zero}"></jstl:out>
		</p>

	</jstl:if>

	<jstl:if test="${isPast == true}">

		<spring:message code="past.title" var="pastitle" />



		<h3>
			<jstl:out value="${pastitle}"></jstl:out>
		</h3>


		<display:table pagesize="10" class="displaytag" name="past"
			requestURI="conference/list.do" id="row">



			<display:column titleKey="conference.title">
				<jstl:out value="${row.title}"></jstl:out>
			</display:column>

			<display:column titleKey="conference.acronym">
				<jstl:out value="${row.acronym}"></jstl:out>
			</display:column>

			<display:column titleKey="conference.venue">
				<jstl:out value="${row.venue}"></jstl:out>
			</display:column>

			<display:column titleKey="conference.startDate">
				<fmt:formatDate type="both" dateStyle="short" timeStyle="short"
					value="${row.startDate}" />
			</display:column>

			<display:column titleKey="conference.endDate">
				<fmt:formatDate type="both" dateStyle="short" timeStyle="short"
					value="${row.endDate}" />

			</display:column>

		</display:table>

	</jstl:if>


	<jstl:if test="${isPast == false}">

		<spring:message code="past.title" var="pastitle" />



		<h3>
			<jstl:out value="${pastitle}"></jstl:out>
		</h3>


		<spring:message code="conference.zero" var="zero" />

		<p>
			<jstl:out value="${zero}"></jstl:out>
		</p>

	</jstl:if>


	<jstl:if test="${isRunning == true}">

		<spring:message code="running.title" var="runtitle" />



		<h3>
			<jstl:out value="${runtitle}"></jstl:out>
		</h3>


		<display:table pagesize="10" class="displaytag" name="past"
			requestURI="conference/list.do" id="row">



			<display:column titleKey="conference.title">
				<jstl:out value="${row.title}"></jstl:out>
			</display:column>


			<display:column titleKey="conference.acronym">
				<jstl:out value="${row.acronym}"></jstl:out>
			</display:column>

			<display:column titleKey="conference.venue">
				<jstl:out value="${row.venue}"></jstl:out>
			</display:column>

			<display:column titleKey="conference.startDate">
				<fmt:formatDate type="both" dateStyle="short" timeStyle="short"
					value="${row.startDate}" />
			</display:column>

			<display:column titleKey="conference.endDate">
				<fmt:formatDate type="both" dateStyle="short" timeStyle="short"
					value="${row.endDate}" />

			</display:column>

		</display:table>

	</jstl:if>


	<jstl:if test="${isPast == false}">

		<spring:message code="running.title" var="runtitle" />



		<h3>
			<jstl:out value="${runtitle}"></jstl:out>
		</h3>


		<spring:message code="conference.zero" var="zero" />

		<p>
			<jstl:out value="${zero}"></jstl:out>
		</p>

	</jstl:if>


</security:authorize>

<security:authorize access="hasRole('AUTHOR')">
	<a  href="conference/listSubmission.do"> <spring:message
			code="conference.listToSubmission" />
	</a>
</security:authorize>



<security:authorize access="isAnonymous()">
	<jstl:if test="${isForthcoming == true}">

		<spring:message code="forthcoming.title" var="fortitle" />



		<h3>
			<jstl:out value="${fortitle}"></jstl:out>
		</h3>


		<display:table pagesize="10" class="displaytag" name="forthcoming"
			requestURI="conference/list.do" id="row">



			<display:column titleKey="conference.title">
				<jstl:out value="${row.title}"></jstl:out>
			</display:column>

			<display:column titleKey="conference.acronym">
				<jstl:out value="${row.acronym}"></jstl:out>
			</display:column>

			<display:column titleKey="conference.venue">
				<jstl:out value="${row.venue}"></jstl:out>
			</display:column>

			<display:column titleKey="conference.startDate">
				<fmt:formatDate type="both" dateStyle="short" timeStyle="short"
					value="${row.startDate}" />
			</display:column>

			<display:column titleKey="conference.endDate">
				<fmt:formatDate type="both" dateStyle="short" timeStyle="short"
					value="${row.endDate}" />

			</display:column>

			<display:column>
				<a href="conference/display.do?conferenceId=${row.id}"> <spring:message
						code="conference.display" />
				</a>

			</display:column>

		</display:table>

	</jstl:if>


	<jstl:if test="${isForthcoming == false}">

		<spring:message code="forthcoming.title" var="fortitle" />



		<h3>
			<jstl:out value="${fortitle}"></jstl:out>
		</h3>

		<spring:message code="conference.zero" var="zero" />

		<p>
			<jstl:out value="${zero}"></jstl:out>
		</p>

	</jstl:if>

	<jstl:if test="${isPast == true}">

		<spring:message code="past.title" var="pastitle" />



		<h3>
			<jstl:out value="${pastitle}"></jstl:out>
		</h3>


		<display:table pagesize="10" class="displaytag" name="past"
			requestURI="conference/list.do" id="row">



			<display:column titleKey="conference.title">
				<jstl:out value="${row.title}"></jstl:out>
			</display:column>

			<display:column titleKey="conference.acronym">
				<jstl:out value="${row.acronym}"></jstl:out>
			</display:column>

			<display:column titleKey="conference.venue">
				<jstl:out value="${row.venue}"></jstl:out>
			</display:column>

			<display:column titleKey="conference.startDate">
				<fmt:formatDate type="both" dateStyle="short" timeStyle="short"
					value="${row.startDate}" />
			</display:column>

			<display:column titleKey="conference.endDate">
				<fmt:formatDate type="both" dateStyle="short" timeStyle="short"
					value="${row.endDate}" />

			</display:column>

		</display:table>

	</jstl:if>


	<jstl:if test="${isPast == false}">

		<spring:message code="past.title" var="pastitle" />



		<h3>
			<jstl:out value="${pastitle}"></jstl:out>
		</h3>


		<spring:message code="conference.zero" var="zero" />

		<p>
			<jstl:out value="${zero}"></jstl:out>
		</p>

	</jstl:if>


	<jstl:if test="${isRunning == true}">

		<spring:message code="running.title" var="runtitle" />



		<h3>
			<jstl:out value="${runtitle}"></jstl:out>
		</h3>


		<display:table pagesize="10" class="displaytag" name="past"
			requestURI="conference/list.do" id="row">



			<display:column titleKey="conference.title">
				<jstl:out value="${row.title}"></jstl:out>
			</display:column>


			<display:column titleKey="conference.acronym">
				<jstl:out value="${row.acronym}"></jstl:out>
			</display:column>

			<display:column titleKey="conference.venue">
				<jstl:out value="${row.venue}"></jstl:out>
			</display:column>

			<display:column titleKey="conference.startDate">
				<fmt:formatDate type="both" dateStyle="short" timeStyle="short"
					value="${row.startDate}" />
			</display:column>

			<display:column titleKey="conference.endDate">
				<fmt:formatDate type="both" dateStyle="short" timeStyle="short"
					value="${row.endDate}" />

			</display:column>

		</display:table>

	</jstl:if>


	<jstl:if test="${isPast == false}">

		<spring:message code="running.title" var="runtitle" />



		<h3>
			<jstl:out value="${runtitle}"></jstl:out>
		</h3>


		<spring:message code="conference.zero" var="zero" />

		<p>
			<jstl:out value="${zero}"></jstl:out>
		</p>

	</jstl:if>


</security:authorize>

<security:authorize access="hasRole('ADMIN')">
	<jstl:if test="${isSubmission == true}">

		<spring:message code="submission.title" var="subtitle" />



		<h3>
			<jstl:out value="${subtitle}"></jstl:out>
		</h3>


		<display:table pagesize="10" class="displaytag" name="submission"
			requestURI="conference/list.do" id="row">



			<display:column titleKey="conference.title">
				<jstl:out value="${row.title}"></jstl:out>
			</display:column>

			<display:column titleKey="conference.acronym">
				<jstl:out value="${row.acronym}"></jstl:out>
			</display:column>

			<display:column titleKey="conference.venue">
				<jstl:out value="${row.venue}"></jstl:out>
			</display:column>

			<display:column titleKey="conference.startDate">
				<fmt:formatDate type="both" dateStyle="short" timeStyle="short"
					value="${row.startDate}" />
			</display:column>

			<display:column titleKey="conference.endDate">
				<fmt:formatDate type="both" dateStyle="short" timeStyle="short"
					value="${row.endDate}" />

			</display:column>

			<display:column titleKey="conference.submissionDL">
				<fmt:formatDate type="both" dateStyle="short" timeStyle="short"
					value="${row.submissionDeadline}" />

			</display:column>

			<display:column>
				<a href="conference/display.do?conferenceId=${row.id}"> <spring:message
						code="conference.display" />
				</a>

			</display:column>


		</display:table>

	</jstl:if>


	<jstl:if test="${isSubmission == false}">

		<spring:message code="submission.title" var="subtitle" />



		<h3>
			<jstl:out value="${subtitle}"></jstl:out>
		</h3>

		<spring:message code="conference.zero" var="zero" />

		<p>
			<jstl:out value="${zero}"></jstl:out>
		</p>

	</jstl:if>

	<jstl:if test="${isNotification == true}">

		<spring:message code="notification.title" var="notitle" />



		<h3>
			<jstl:out value="${notitle}"></jstl:out>
		</h3>


		<display:table pagesize="10" class="displaytag" name="notification"
			requestURI="conference/list.do" id="row">



			<display:column titleKey="conference.title">
				<jstl:out value="${row.title}"></jstl:out>
			</display:column>

			<display:column titleKey="conference.acronym">
				<jstl:out value="${row.acronym}"></jstl:out>
			</display:column>

			<display:column titleKey="conference.venue">
				<jstl:out value="${row.venue}"></jstl:out>
			</display:column>

			<display:column titleKey="conference.startDate">
				<fmt:formatDate type="both" dateStyle="short" timeStyle="short"
					value="${row.startDate}" />
			</display:column>

			<display:column titleKey="conference.endDate">
				<fmt:formatDate type="both" dateStyle="short" timeStyle="short"
					value="${row.endDate}" />

			</display:column>

			<display:column titleKey="conference.notificationDL">
				<fmt:formatDate type="both" dateStyle="short" timeStyle="short"
					value="${row.notificationDeadline}" />

			</display:column>

		</display:table>

	</jstl:if>


	<jstl:if test="${isNotification == false}">

		<spring:message code="notification.title" var="notitle" />



		<h3>
			<jstl:out value="${notitle}"></jstl:out>
		</h3>

		<spring:message code="conference.zero" var="zero" />

		<p>
			<jstl:out value="${zero}"></jstl:out>
		</p>

	</jstl:if>

	<jstl:if test="${isCamera == true}">

		<spring:message code="camera.title" var="cameratitle" />



		<h3>
			<jstl:out value="${cameratitle}"></jstl:out>
		</h3>


		<display:table pagesize="10" class="displaytag" name="camera"
			requestURI="conference/list.do" id="row">



			<display:column titleKey="conference.title">
				<jstl:out value="${row.title}"></jstl:out>
			</display:column>

			<display:column titleKey="conference.acronym">
				<jstl:out value="${row.acronym}"></jstl:out>
			</display:column>

			<display:column titleKey="conference.venue">
				<jstl:out value="${row.venue}"></jstl:out>
			</display:column>

			<display:column titleKey="conference.startDate">
				<fmt:formatDate type="both" dateStyle="short" timeStyle="short"
					value="${row.startDate}" />
			</display:column>

			<display:column titleKey="conference.endDate">
				<fmt:formatDate type="both" dateStyle="short" timeStyle="short"
					value="${row.endDate}" />

			</display:column>

			<display:column titleKey="conference.cameraReadyDL">
				<fmt:formatDate type="both" dateStyle="short" timeStyle="short"
					value="${row.cameraReadyDeadline}" />

			</display:column>

		</display:table>

	</jstl:if>


	<jstl:if test="${isCamera == false}">

		<spring:message code="camera.title" var="cameratitle" />



		<h3>
			<jstl:out value="${cameratitle}"></jstl:out>
		</h3>

		<spring:message code="conference.zero" var="zero" />

		<p>
			<jstl:out value="${zero}"></jstl:out>
		</p>

	</jstl:if>

	<jstl:if test="${isStart == true}">

		<spring:message code="start.title" var="startitle" />



		<h3>
			<jstl:out value="${startitle}"></jstl:out>
		</h3>


		<display:table pagesize="10" class="displaytag" name="start"
			requestURI="conference/list.do" id="row">



			<display:column titleKey="conference.title">
				<jstl:out value="${row.title}"></jstl:out>
			</display:column>

			<display:column titleKey="conference.acronym">
				<jstl:out value="${row.acronym}"></jstl:out>
			</display:column>

			<display:column titleKey="conference.venue">
				<jstl:out value="${row.venue}"></jstl:out>
			</display:column>

			<display:column titleKey="conference.startDate">
				<fmt:formatDate type="both" dateStyle="short" timeStyle="short"
					value="${row.startDate}" />
			</display:column>

			<display:column titleKey="conference.endDate">
				<fmt:formatDate type="both" dateStyle="short" timeStyle="short"
					value="${row.endDate}" />

			</display:column>

			<display:column>

			</display:column>

		</display:table>

	</jstl:if>


	<jstl:if test="${isStart == false}">

		<spring:message code="start.title" var="startitle" />



		<h3>
			<jstl:out value="${startitle}"></jstl:out>
		</h3>

		<spring:message code="conference.zero" var="zero" />

		<p>
			<jstl:out value="${zero}"></jstl:out>
		</p>

	</jstl:if>

	<jstl:if test="${isAll == true}">

		<spring:message code="all.title" var="alltitle" />



		<h3>
			<jstl:out value="${alltitle}"></jstl:out>
		</h3>


		<display:table pagesize="10" class="displaytag" name="conferences"
			requestURI="conference/list.do" id="row">



			<display:column titleKey="conference.title">
				<jstl:out value="${row.title}"></jstl:out>
			</display:column>

			<display:column titleKey="conference.acronym">
				<jstl:out value="${row.acronym}"></jstl:out>
			</display:column>

			<display:column titleKey="conference.venue">
				<jstl:out value="${row.venue}"></jstl:out>
			</display:column>

			<display:column titleKey="conference.startDate">
				<fmt:formatDate type="both" dateStyle="short" timeStyle="short"
					value="${row.startDate}" />
			</display:column>

			<display:column titleKey="conference.endDate">
				<fmt:formatDate type="both" dateStyle="short" timeStyle="short"
					value="${row.endDate}" />

			</display:column>

			<display:column>
				<a href="conference/display.do?conferenceId=${row.id}"> <spring:message
						code="conference.display" />
				</a>

			</display:column>

		</display:table>

	</jstl:if>


	<jstl:if test="${isAll == false}">

		<spring:message code="all.title" var="alltitle" />



		<h3>
			<jstl:out value="${alltitle}"></jstl:out>
		</h3>

		<spring:message code="conference.zero" var="zero" />

		<p>
			<jstl:out value="${zero}"></jstl:out>
		</p>

	</jstl:if>



</security:authorize>
