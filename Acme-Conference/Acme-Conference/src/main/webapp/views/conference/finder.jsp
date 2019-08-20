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



	<form:form action="conference/finder.do" mehtod="POST">
		<b><spring:message code="conference.finder" />&#160;</b>
        <input id="keyword" type="text" name="keyword" size="20" />
        
	
	
        <input type="submit" value='<spring:message code="conference.search" />'/>
        

      </form:form>
      	
      	<display:table pagesize="10" class="displaytag" name="conferencesFinder"
			 id="row">
			 

			<display:column titleKey="conference.filter" >
				<input type="button" value="<jstl:out value="${row.title}"/>"
								onclick="redirect: location.href = 'conference/display.do?conferenceId=${row.id}';" />
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
      	<display:table pagesize="10" class="displaytag" name="conferencesFinals"
			 id="row">
			 

			<display:column titleKey="conference.total" >
				<input type="button" value="<jstl:out value="${row.title}"/>"
								onclick="redirect: location.href = 'conference/display.do?conferenceId=${row.id}';" />
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