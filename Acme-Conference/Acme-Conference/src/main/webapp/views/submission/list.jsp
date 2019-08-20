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
		requestURI="submission/author/list.do" id="row">



		<display:column titleKey="submission.ticker">
			<jstl:out value="${row.ticker}"></jstl:out>
		</display:column>

		<display:column titleKey="submission.paper.title">
			<jstl:out value="${row.paper.title}"></jstl:out>
		</display:column>


		<display:column>
			<a href="submission/author/display.do?submissionId=${row.id}"> <spring:message
					code="submission.display" />
			</a>

		</display:column>

	</display:table>







</security:authorize>


<security:authorize access="hasRole('ADMIN')">

	<jstl:if test="${isAccepted == true}">

		<spring:message code="accepted.title" var="acceptedtitle" />



		<h3>
			<jstl:out value="${acceptedtitle}"></jstl:out>
		</h3>

		<display:table pagesize="10" class="displaytag" name="accepted"
			requestURI="submission/administrator/list.do" id="row">



			<display:column titleKey="submission.ticker">
				<jstl:out value="${row.ticker}"></jstl:out>
			</display:column>

			<display:column titleKey="submission.paper.title">
				<jstl:out value="${row.paper.title}"></jstl:out>
			</display:column>


			<display:column>
				<a href="submission/administrator/display.do?submissionId=${row.id}">
					<spring:message code="submission.display" />
				</a>

			</display:column>

		</display:table>

	</jstl:if>

	<jstl:if test="${isAccepted == false}">

		<spring:message code="accepted.title" var="acceptedtitle" />



		<h3>
			<jstl:out value="${acceptedtitle}"></jstl:out>
		</h3>

		<spring:message code="submission.zero" var="zero" />

		<p>
			<jstl:out value="${zero}"></jstl:out>
		</p>

	</jstl:if>





	<jstl:if test="${isRejected== true}">

		<spring:message code="rejected.title" var="rejectedtitle" />



		<h3>
			<jstl:out value="${rejectedtitle}"></jstl:out>
		</h3>

		<display:table pagesize="10" class="displaytag" name="rejected"
			requestURI="submission/administrator/list.do" id="row">



			<display:column titleKey="submission.ticker">
				<jstl:out value="${row.ticker}"></jstl:out>
			</display:column>

			<display:column titleKey="submission.paper.title">
				<jstl:out value="${row.paper.title}"></jstl:out>
			</display:column>


			<display:column>
				<a href="submission/administrator/display.do?submissionId=${row.id}">
					<spring:message code="submission.display" />
				</a>

			</display:column>

		</display:table>

	</jstl:if>

	<jstl:if test="${isRejected== false}">

		<spring:message code="rejected.title" var="rejectedtitle" />



		<h3>
			<jstl:out value="${rejectedtitle}"></jstl:out>
		</h3>

		<spring:message code="submission.zero" var="zero" />

		<p>
			<jstl:out value="${zero}"></jstl:out>
		</p>

	</jstl:if>






	<jstl:if test="${isUnder== true}">

		<spring:message code="under.title" var="undertitle" />



		<h3>
			<jstl:out value="${undertitle}"></jstl:out>
		</h3>

		<display:table pagesize="10" class="displaytag" name="under"
			requestURI="submission/administrator/list.do" id="row">



			<display:column titleKey="submission.ticker">
				<jstl:out value="${row.ticker}"></jstl:out>
			</display:column>

			<display:column titleKey="submission.paper.title">
				<jstl:out value="${row.paper.title}"></jstl:out>
			</display:column>


			<display:column>
				<a href="submission/administrator/display.do?submissionId=${row.id}">
					<spring:message code="submission.display" />
				</a>

			</display:column>

		</display:table>

	</jstl:if>

	<jstl:if test="${isUnder== false}">

		<spring:message code="under.title" var="undertitle" />



		<h3>
			<jstl:out value="${undertitle}"></jstl:out>
		</h3>

		<spring:message code="submission.zero" var="zero" />

		<p>
			<jstl:out value="${zero}"></jstl:out>
		</p>

	</jstl:if>




	<jstl:if test="${isBorder== true}">

		<spring:message code="border.title" var="bordertitle" />



		<h3>
			<jstl:out value="${bordertitle}"></jstl:out>
		</h3>

		<display:table pagesize="10" class="displaytag" name="border"
			requestURI="submission/administrator/list.do" id="row">



			<display:column titleKey="submission.ticker">
				<jstl:out value="${row.ticker}"></jstl:out>
			</display:column>

			<display:column titleKey="submission.paper.title">
				<jstl:out value="${row.paper.title}"></jstl:out>
			</display:column>


			<display:column>
				<a href="submission/administrator/display.do?submissionId=${row.id}">
					<spring:message code="submission.display" />
				</a>

			</display:column>

		</display:table>


	</jstl:if>

	<jstl:if test="${isBorder== false}">

		<spring:message code="border.title" var="bordertitle" />



		<h3>
			<jstl:out value="${bordertitle}"></jstl:out>
		</h3>

		<spring:message code="submission.zero" var="zero" />

		<p>
			<jstl:out value="${zero}"></jstl:out>
		</p>

	</jstl:if>



</security:authorize>