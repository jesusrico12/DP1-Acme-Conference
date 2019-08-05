<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<script>
	function checkPhone(msg) {
		var phone = document.getElementById("phoneNumber").value;
		var pattern = /^(((([+][1-9]{1}[0-9]{0,2}[\s]){0,1}([(][1-9]{1}[0-9]{0,2}[)][\s]){0,1})){0,1}([0-9]{4}){1}([0-9]{0,}))$/;
		var pat = pattern.test(phone);
		if (pat) {
			return true;
		} else {
			return confirm(msg);
		}
	}
</script>

<style>
.tooltip {
	position: relative;
	display: inline-block;
	border-bottom: 1px dotted black;
}

.tooltip .tooltiptext {
	visibility: hidden;
	width: 120px;
	background-color: black;
	color: #fff;
	text-align: center;
	border-radius: 6px;
	padding: 5px 0;
	position: absolute;
	z-index: 1;
	bottom: 150%;
	left: 50%;
	margin-left: -60px;
	width: 200px;
}

.tooltip .tooltiptext::after {
	content: "";
	position: absolute;
	top: 100%;
	left: 50%;
	margin-left: -5px;
	border-width: 5px;
	border-style: solid;
	border-color: black transparent transparent transparent;
}

.tooltip:hover .tooltiptext {
	visibility: visible;
}
</style>

<spring:message code="phone.confirmation" var="confirmTelephone" />
<security:authorize access="hasRole('ADMIN')">
	<form:form modelAttribute="administrator"
		action="administrator/administrator/register.do"
		onsubmit="javascript: return checkPhone('${confirmTelephone}');">
			
			
				<form:hidden path="id" />
		<form:hidden path="version" />
			
		<!-- User Account Attributes -->
		<fieldset>
			<legend style="font-size: 21px">
				<spring:message code="actor.userAccount" />
			</legend>

			<div>
					<strong>	<form:label path="userAccount.username">
				<spring:message code="actor.username" />:
			</form:label>
			</strong>
			<form:input path="userAccount.username" />
			<form:errors cssClass="error" path="userAccount.username" />
			<br />

			</div>

			<br />

			<div>
				
				
				
				<strong><form:label path="userAccount.password">
						<spring:message code="actor.password" />
					</form:label></strong>
				<form:password path="userAccount.password" />
				<form:errors cssClass="error" path="userAccount.password" />
			
			</div>

	
			<br />
		</fieldset>
		<br />

		<!-- Actor Attributes -->
		<fieldset>
			<legend style="font-size: 21px">
				<spring:message code="actor.personalData" />
			</legend>

			<div>
				
				<strong><form:label path="name">
						<spring:message code="actor.name" />
					</form:label></strong>
				<form:input path="name" />
				
					<form:errors path="name" cssClass="error" />
				
			</div>

				<br/>
				<div>
			
					
			
				<strong><form:label path="middleName">
						<spring:message code="actor.middleName" />
					</form:label></strong>
				<form:input path="middleName" />
				<form:errors path="middleName" cssClass="error" />
			</div>
				<br/>
				
			<div>
			
					
			
				<strong><form:label path="surname">
						<spring:message code="actor.surname" />
					</form:label></strong>
				<form:input path="surname" />
				<form:errors path="surname" cssClass="error" />
			</div>

			<br />

			
	

			<br />

			<div>
				
				<strong><form:label path="photo">
						<spring:message code="actor.photo" />
					</form:label></strong>
				<form:input path="photo" />
				<form:errors path="photo" cssClass="error">
					<p class="error">
						<spring:message code="photo.error" />
					</p>
				</form:errors>
			</div>

			<br />

			<div>
				<form:errors path="email" cssClass="error">
					<p class="error">
						<spring:message code="email.error" />
					</p>
				</form:errors>
				<strong><form:label path="email">
						<spring:message code="actor.email" />
					</form:label></strong>
				<form:input path="email" />
			</div>

			<div>
				<p>
					<form:errors path="phoneNumber" cssClass="error" />
				</p>
				<strong><form:label path="phoneNumber">
						<spring:message code="actor.phone" />
					</form:label></strong>
				<form:input path="phoneNumber" />
			</div>

			<div>
				<p>
					<form:errors path="address" cssClass="error" />
				</p>
				<strong><form:label path="address">
						<spring:message code="actor.address" />
					</form:label></strong>
				<form:input path="address" />
			</div>

			<br />
		</fieldset>
		<br />

	
		

		<!-- Buttons -->
		<input type="submit" name="save"
			value="<spring:message code="form.save" />" />

		<button type="button" onclick="javascript: relativeRedir('/')">
			<spring:message code="form.cancel" />
		</button>

	</form:form>
</security:authorize>