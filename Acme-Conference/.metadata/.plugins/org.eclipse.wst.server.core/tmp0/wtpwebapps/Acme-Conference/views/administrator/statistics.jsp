<%--
 * action-2.jsp
 *
 * Copyright (C) 2019 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<security:authorize access="hasRole('ADMIN')">
	
	
	<table class="displayStyle" style="width: 50%">
			<tr>
				<th colspan="2"><spring:message
						code="administrator.statistics.C" /></th>
			</tr>
	
			
	
			<tr>
				<td><spring:message code="administrator.submissionsPerConference.max" /></td>
				<td style="text-align: right">${submissionsPerConference[0]}</td>
			</tr>
			<tr>
				<td><spring:message code="administrator.submissionsPerConference.min" /></td>
				<td style="text-align: right">${submissionsPerConference[1]}</td>
			</tr>
			<tr>
				<td><spring:message code="administrator.submissionsPerConference.avg" /></td>
				<td style="text-align: right">${submissionsPerConference[2]}</td>
			</tr>
			<tr>
				<td><spring:message code="administrator.submissionsPerConference.stdev" /></td>
				<td style="text-align: right">${submissionsPerConference[3]}</td>
			</tr>
					<tr>
				<td><spring:message code="administrator.registrationsPerConference.max" /></td>
				<td style="text-align: right">${registrationsPerConference[0]}</td>
			</tr>
			<tr>
				<td><spring:message code="administrator.registrationsPerConference.min" /></td>
				<td style="text-align: right">${registrationsPerConference[1]}</td>
			</tr>
			<tr>
				<td><spring:message code="administrator.registrationsPerConference.avg" /></td>
				<td style="text-align: right">${registrationsPerConference[2]}</td>
			</tr>
			<tr>
				<td><spring:message code="administrator.registrationsPerConference.stdev" /></td>
				<td style="text-align: right">${registrationsPerConference[3]}</td>
			</tr>
								<tr>
				<td><spring:message code="administrator.feesPerConference.max" /></td>
				<td style="text-align: right">${feesPerConference[0]}</td>
			</tr>
			<tr>
				<td><spring:message code="administrator.feesPerConference.min" /></td>
				<td style="text-align: right">${feesPerConference[1]}</td>
			</tr>
			<tr>
				<td><spring:message code="administrator.feesPerConference.avg" /></td>
				<td style="text-align: right">${feesPerConference[2]}</td>
			</tr>
			<tr>
				<td><spring:message code="administrator.feesPerConference.stdev" /></td>
				<td style="text-align: right">${feesPerConference[3]}</td>
			</tr>
			<tr>
				<td><spring:message code="administrator.numberOfDaysPerConference.max" /></td>
				<td style="text-align: right">${numberOfDaysPerConference[0]}</td>
			</tr>
			<tr>
				<td><spring:message code="administrator.numberOfDaysPerConference.min" /></td>
				<td style="text-align: right">${numberOfDaysPerConference[1]}</td>
			</tr>
			<tr>
				<td><spring:message code="administrator.numberOfDaysPerConference.avg" /></td>
				<td style="text-align: right">${numberOfDaysPerConference[2]}</td>
			</tr>
			<tr>
				<td><spring:message code="administrator.numberOfDaysPerConference.stdev" /></td>
				<td style="text-align: right">${numberOfDaysPerConference[3]}</td>
			</tr>
	
	
	
	
	
	
			


		</table>

	</security:authorize>