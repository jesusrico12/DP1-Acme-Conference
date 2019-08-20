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

<security:authorize access="isAuthenticated()">
	<jstl:if test="${isAll == true}">

		<spring:message code="all.title" var="all" />

		<h3>
			<jstl:out value="${all}"></jstl:out>
		</h3>


		<display:table pagesize="10" class="displaytag" name="all"
			requestURI="message/actor/list.do" id="row">



			<display:column titleKey="message.subject">
				<jstl:out value="${row.subject}"></jstl:out>
			</display:column>

			<jstl:if test="${language==español }">
				<display:column titleKey="message.topic">
					<jstl:out value="${row.topic.get('Español')}"></jstl:out>
				</display:column>
			</jstl:if>

			<jstl:if test="${language==english}">
				<display:column titleKey="message.topic">
					<jstl:out value="${row.topic.get('English')}"></jstl:out>
				</display:column>
			</jstl:if>


			<display:column>
				<a href="message/actor/display.do?messageId=${row.id}"> <spring:message
						code="message.display" />
				</a>

			</display:column>

		</display:table>

	</jstl:if>


	<jstl:if test="${isAll== false}">

		<spring:message code="all.title" var="all" />



		<h3>
			<jstl:out value="${all}"></jstl:out>
		</h3>

		<spring:message code="messages.zero" var="zero" />

		<p>
			<jstl:out value="${zero}"></jstl:out>
		</p>

	</jstl:if>

	<spring:message code="list.sender" var="bySender"></spring:message>
	<h1>
		<jstl:out value="${bySender}"></jstl:out>
	</h1>


	<jstl:if test="${isSenderAdmin == true}">

		<spring:message code="sender.admin.title" var="sendAdmin" />

		<h3>
			<jstl:out value="${sendAdmin}"></jstl:out>
		</h3>


		<display:table pagesize="10" class="displaytag" name="senderAdmin"
			requestURI="message/actor/list.do" id="row">



			<display:column titleKey="message.subject">
				<jstl:out value="${row.subject}"></jstl:out>
			</display:column>

			<jstl:if test="${language==español }">
				<display:column titleKey="message.topic">
					<jstl:out value="${row.topic.get('Español')}"></jstl:out>
				</display:column>
			</jstl:if>

			<jstl:if test="${language==english}">
				<display:column titleKey="message.topic">
					<jstl:out value="${row.topic.get('English')}"></jstl:out>
				</display:column>
			</jstl:if>


			<display:column>
				<a href="message/actor/display.do?messageId=${row.id}"> <spring:message
						code="message.display" />
				</a>

			</display:column>

		</display:table>

	</jstl:if>


	<jstl:if test="${isSenderAdmin== false}">

		<spring:message code="sender.admin.title" var="sendAdmin" />



		<h3>
			<jstl:out value="${sendAdmin}"></jstl:out>
		</h3>

		<spring:message code="messages.zero" var="zero" />

		<p>
			<jstl:out value="${zero}"></jstl:out>
		</p>

	</jstl:if>


	<jstl:if test="${isSenderAuthor == true}">

		<spring:message code="sender.author.title" var="sendAuthor" />

		<h3>
			<jstl:out value="${sendAuthor}"></jstl:out>
		</h3>


		<display:table pagesize="10" class="displaytag" name="senderAuthor"
			requestURI="message/actor/list.do" id="row">



			<display:column titleKey="message.subject">
				<jstl:out value="${row.subject}"></jstl:out>
			</display:column>

			<jstl:if test="${language==español }">
				<display:column titleKey="message.topic">
					<jstl:out value="${row.topic.get('Español')}"></jstl:out>
				</display:column>
			</jstl:if>

			<jstl:if test="${language==english}">
				<display:column titleKey="message.topic">
					<jstl:out value="${row.topic.get('English')}"></jstl:out>
				</display:column>
			</jstl:if>


			<display:column>
				<a href="message/actor/display.do?messageId=${row.id}"> <spring:message
						code="message.display" />
				</a>

			</display:column>

		</display:table>

	</jstl:if>


	<jstl:if test="${isSenderAuthor== false}">

		<spring:message code="sender.author.title" var="sendAuthor" />



		<h3>
			<jstl:out value="${sendAuthor}"></jstl:out>
		</h3>

		<spring:message code="messages.zero" var="zero" />

		<p>
			<jstl:out value="${zero}"></jstl:out>
		</p>

	</jstl:if>

	<jstl:if test="${isSenderReviewer== true}">

		<spring:message code="sender.reviewer.title" var="sendReviewer" />

		<h3>
			<jstl:out value="${sendReviewer}"></jstl:out>
		</h3>


		<display:table pagesize="10" class="displaytag" name="senderReviewer"
			requestURI="message/actor/list.do" id="row">



			<display:column titleKey="message.subject">
				<jstl:out value="${row.subject}"></jstl:out>
			</display:column>

			<jstl:if test="${language==español }">
				<display:column titleKey="message.topic">
					<jstl:out value="${row.topic.get('Español')}"></jstl:out>
				</display:column>
			</jstl:if>

			<jstl:if test="${language==english}">
				<display:column titleKey="message.topic">
					<jstl:out value="${row.topic.get('English')}"></jstl:out>
				</display:column>
			</jstl:if>


			<display:column>
				<a href="message/actor/display.do?messageId=${row.id}"> <spring:message
						code="message.display" />
				</a>

			</display:column>

		</display:table>

	</jstl:if>


	<jstl:if test="${isSenderReviewer== false}">

		<spring:message code="sender.reviewer.title" var="sendReviewer" />



		<h3>
			<jstl:out value="${sendReviewer}"></jstl:out>
		</h3>

		<spring:message code="messages.zero" var="zero" />

		<p>
			<jstl:out value="${zero}"></jstl:out>
		</p>

	</jstl:if>

	<spring:message code="list.receiver" var="byReceiver"></spring:message>
	<h1>
		<jstl:out value="${byReceiver}"></jstl:out>
	</h1>

	<jstl:if test="${isReceiverAdmin== true}">

		<spring:message code="receiver.admin.title" var="recAdmin" />

		<h3>
			<jstl:out value="${recAdmin}"></jstl:out>
		</h3>


		<display:table pagesize="10" class="displaytag" name="receiverAdmin"
			requestURI="message/actor/list.do" id="row">



			<display:column titleKey="message.subject">
				<jstl:out value="${row.subject}"></jstl:out>
			</display:column>

			<jstl:if test="${language==español }">
				<display:column titleKey="message.topic">
					<jstl:out value="${row.topic.get('Español')}"></jstl:out>
				</display:column>
			</jstl:if>

			<jstl:if test="${language==english}">
				<display:column titleKey="message.topic">
					<jstl:out value="${row.topic.get('English')}"></jstl:out>
				</display:column>
			</jstl:if>


			<display:column>
				<a href="message/actor/display.do?messageId=${row.id}"> <spring:message
						code="message.display" />
				</a>

			</display:column>

		</display:table>

	</jstl:if>


	<jstl:if test="${isReceiverAdmin== false}">

		<spring:message code="receiver.admin.title" var="recAdmin" />



		<h3>
			<jstl:out value="${recAdmin}"></jstl:out>
		</h3>

		<spring:message code="messages.zero" var="zero" />

		<p>
			<jstl:out value="${zero}"></jstl:out>
		</p>

	</jstl:if>

	<jstl:if test="${isReceiverAuthor== true}">

		<spring:message code="receiver.author.title" var="recAuthor" />

		<h3>
			<jstl:out value="${recAuthor}"></jstl:out>
		</h3>


		<display:table pagesize="10" class="displaytag" name="receiverAuthor"
			requestURI="message/actor/list.do" id="row">



			<display:column titleKey="message.subject">
				<jstl:out value="${row.subject}"></jstl:out>
			</display:column>

			<jstl:if test="${language==español }">
				<display:column titleKey="message.topic">
					<jstl:out value="${row.topic.get('Español')}"></jstl:out>
				</display:column>
			</jstl:if>

			<jstl:if test="${language==english}">
				<display:column titleKey="message.topic">
					<jstl:out value="${row.topic.get('English')}"></jstl:out>
				</display:column>
			</jstl:if>


			<display:column>
				<a href="message/actor/display.do?messageId=${row.id}"> <spring:message
						code="message.display" />
				</a>

			</display:column>

		</display:table>

	</jstl:if>


	<jstl:if test="${isReceiverAuthor== false}">

		<spring:message code="receiver.author.title" var="recAuthor" />



		<h3>
			<jstl:out value="${recAuthor}"></jstl:out>
		</h3>

		<spring:message code="messages.zero" var="zero" />

		<p>
			<jstl:out value="${zero}"></jstl:out>
		</p>

	</jstl:if>


	<jstl:if test="${isReceiverReviewer== true}">

		<spring:message code="receiver.reviewer.title" var="recReviewer" />

		<h3>
			<jstl:out value="${recReviewer}"></jstl:out>
		</h3>


		<display:table pagesize="10" class="displaytag"
			name="receiverReviewer" requestURI="message/actor/list.do" id="row">



			<display:column titleKey="message.subject">
				<jstl:out value="${row.subject}"></jstl:out>
			</display:column>

			<jstl:if test="${language==español }">
				<display:column titleKey="message.topic">
					<jstl:out value="${row.topic.get('Español')}"></jstl:out>
				</display:column>
			</jstl:if>

			<jstl:if test="${language==english}">
				<display:column titleKey="message.topic">
					<jstl:out value="${row.topic.get('English')}"></jstl:out>
				</display:column>
			</jstl:if>


			<display:column>
				<a href="message/actor/display.do?messageId=${row.id}"> <spring:message
						code="message.display" />
				</a>

			</display:column>

		</display:table>

	</jstl:if>


	<jstl:if test="${isReceiverReviewer== false}">

		<spring:message code="receiver.reviewer.title" var="recReviewer" />



		<h3>
			<jstl:out value="${recReviewer}"></jstl:out>
		</h3>

		<spring:message code="messages.zero" var="zero" />

		<p>
			<jstl:out value="${zero}"></jstl:out>
		</p>

	</jstl:if>

</security:authorize>
