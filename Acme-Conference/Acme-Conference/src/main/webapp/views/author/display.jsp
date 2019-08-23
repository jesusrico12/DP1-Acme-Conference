<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>






<security:authorize access="hasRole('AUTHOR')">

		<!-- Actor Attributes -->
		<fieldset style="width:35%">
			<legend style="font-size: 21px">
				<spring:message code="actor.personalData" />
			</legend>

			<div style="float: left;">
				<div>
					<strong><spring:message code="actor.name" />: </strong>
					<jstl:out value="${author.name}" />
				</div>

				<br />
				
					<div>
					<strong><spring:message code="author.middleName" />: </strong>
					<jstl:out value="${author.middleName}" />
				</div>

				<br />

				<div>
					<strong><spring:message code="actor.surname" />: </strong>
					<jstl:out value="${author.surname}" />
				</div>

				<br />


				<br />

				<div>
					<strong><spring:message code="actor.email" />: </strong>
					<jstl:out value="${author.email}" />
				</div>

				<br />

				<div>
					<strong><spring:message code="actor.phone" />: </strong>
					<jstl:out value="${author.phoneNumber}" />
				</div>

				<br />

				<div>
					<strong><spring:message code="actor.address" />: </strong>
					<jstl:out value="${author.address}" />
				</div>
			</div>

			<div style="float: right;">
				<img class="picture"   src="${author.photo}">
			</div>

		</fieldset>
		<br />
		
	
		
			

</security:authorize>