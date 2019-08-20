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

	<table class="displayStyle">
		<tr>
			<td><strong> <spring:message code="conference.title" />
					:
			</strong></td>
			<td>
			<input type="button" value="<jstl:out value="${registration.conference.title}"/>"
								onclick="redirect: location.href = 'conference/display.do?conferenceId=${registration.conference.id}';" />
		</td>
		</tr>
		<tr>
			<td><strong> <spring:message code="registration.holder" />
					:
			</strong></td>
			<td><jstl:out value="${registration.creditCard.holder}"></jstl:out></td>
		</tr>
				<tr>
			<td><strong> <spring:message code="registration.make" />
					:
			</strong></td>
			<td><jstl:out value="${registration.creditCard.make}"></jstl:out></td>
		</tr>
			<tr>
			<td><strong> <spring:message code="registration.number" />
					:
			</strong></td>
			<td><jstl:out value="${registration.creditCard.number}"></jstl:out></td>
		</tr>
				<tr>
			<td><strong> <spring:message code="registration.expirationMonth" />
					:
			</strong></td>
			<td><jstl:out value="${registration.creditCard.expirationMonth}"></jstl:out></td>
		</tr>
	
			<tr>
			<td><strong> <spring:message code="registration.expirationYear" />
					:
			</strong></td>
			<td><jstl:out value="${registration.creditCard.expirationYear}"></jstl:out></td>
		</tr>
			<tr>
			<td><strong> <spring:message code="registration.CVV" />
					:
			</strong></td>
			<td><jstl:out value="${registration.creditCard.CVV}"></jstl:out></td>
		</tr>	
	

</table>

</security:authorize>
