<%@page import="org.springframework.context.i18n.LocaleContextHolder"%>
<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<jstl:set value="<%=LocaleContextHolder.getLocale()%>" var="locale"></jstl:set>


<head>
<style>
.modal {
	display: none;
	position: fixed;
	z-index: 1;
	padding-top: 100px;
	left: 0;
	top: 0;
	width: 100%;
	height: 100%;
	background-color: rgb(0, 0, 0);
	background-color: rgba(0, 0, 0, 0.4);
	overflow: auto;
}

.modal-content {
	background-color: #fefefe;
	margin: auto;
	padding: 20px;
	border: 1px solid #888;
	width: 65%;
}

.close {
	color: #aaaaaa;
	float: right;
	font-size: 28px;
	font-weight: bold;
}


.close:hover, .close:focus {
	color: #000;
	text-decoration: none;
	cursor: pointer;
}
</style>
</head>




<form:form action="reviewer/edit.do"
	modelAttribute="reviewerForm" id="form" >
 
	<acme:hiddenInputs attributes="id" />

	<acme:textbox code="reviewer.userAccount.username" path="username" />
	<br/>
	<jstl:if test="${reviewerForm.id > 0}">
		<acme:password code="reviewer.userAccount.oldPassword"
			path="oldPassword" />
			<br/>
	</jstl:if>
	<acme:password code="reviewer.userAccount.newPassword"
		path="newPassword" />
		<br/>
	<acme:password code="reviewer.userAccount.confirmPassword"
		path="confirmPassword" />
		<br/>
	<acme:textbox code="reviewer.name" path="name" />
	<br/>
	<acme:textbox code="reviewer.middleName" path="middleName" />
	<br/>
	<acme:textbox code="reviewer.surname" path="surname" />
	<br/>
	<acme:textbox code="reviewer.email" path="email" />
	<br/>
	<acme:textbox code="reviewer.picture" path="photo" placeholder='picture.url'/>
	<br/>
	<acme:textbox code="reviewer.phoneNumber" path="phoneNumber" />
	<br/>
	<acme:textbox code="reviewer.address" path="address" />
	<br/>
	<acme:textbox code="reviewer.keywords" path="keywords" placeholder='placeholder.keywords' />
	<br/>



	
	<jstl:if test="${reviewerForm.id > 0}">
		<input type="submit" name="save"
			value="<spring:message code="reviewer.save" />"
			onclick="if(!/^(\+[0-9]{1,3}[ ]{0,1}(\([0-9]{1,3}\)[ ]{0,1}){0,1}){0,1}[0-9]{1}[0-9 ]{3,}$/.test(document.getElementById('phoneNumber').value)) { return confirm('<spring:message code="reviewer.confirm.phoneNumber" />')}" />
	<acme:cancel code="message.cancel" url="welcome/index.do" />
	</jstl:if>

	<jstl:if test="${reviewerForm.id == 0}">
	
		<acme:editButtons entity="reviewer" role="reviewer"
			save="true" customCancelPath="welcome/index.do"
			checkPhoneNumber="true" />

	</jstl:if>
	


 
</form:form>



<script>
	var modal = document.getElementById('myModal');

	var btn = document.getElementById("myBtn");

	var span = document.getElementsByClassName("close")[0];

	btn.onclick = function() {
		modal.style.display = "block";
	}

	span.onclick = function() {
		modal.style.display = "none";
	}

	window.onclick = function(event) {
		if (event.target == modal) {
			modal.style.display = "none";
		}
	}



</script>