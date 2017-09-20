<%--
  Created by IntelliJ IDEA.
  User: TienDungNguyen
  Date: 9/19/2017
  Time: 7:47 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="admin.survey.form.title"/></title>
    <meta name="heading" content="Survey Management"/>
</head>
<div class="pathway">
    <c:choose>
        <c:when test="${not empty item.pojo.surveyID}">
            Edit <fmt:message key="label.survey"/>
        </c:when>
        <c:otherwise>
            Add <fmt:message key="label.survey"/>
        </c:otherwise>
    </c:choose>
</div>
<c:set var="date_format" value="${Constants['DATE_FORMAT'] }"/>
<c:url var="url" value="/admin/survey/edit.html"/>
<c:url var="listURL" value="/admin/survey/list.html"/>

<div id="content">
    <form:form commandName="item" action="${url}" method="post" id="itemForm">
        <div class="box_container">
            <div class="header">
                <fmt:message key="admin.survey.form.heading"/>
            </div>

            <div class="form">
                <table width="100%" cellpadding="5" cellspacing="5" border="0">
                    <tr>
                        <td><fmt:message key="admin.survey.form.surveyname"/></td>
                        <td>
                            <form:input path="pojo.surveyName" size="40"/>
                            <form:errors path="pojo.surveyName" cssClass="validateError"/>
                        </td>
                    </tr>

                    <tr>
                        <td><fmt:message key="label.starteddate"/></td>
                        <td>
                            <fmt:formatDate value='${item.startedDate }' pattern='${date_format }'/>
                            <form:input path="pojo.startedDate" id="startedDate" value='${item.startedDate}' size="40" />
                            <form:errors path="pojo.startedDate" cssClass="validateError"/>
                        </td>
                    </tr>
                    <tr>
                        <td><fmt:message key="label.endeddate"/></td>
                        <td>
                            <fmt:formatDate value='${item.endedDate }' pattern='${date_format }'/>
                            <form:input path="pojo.endedDate" id="endedDate" value='${item.endedDate}' size="40"/>
                            <form:errors path="pojo.endedDate" cssClass="validateError"/>
                        </td>
                    </tr>


                    <tr>
                        <td><fmt:message key="label.status"/></td>
                        <td>
                            <form:radiobutton path="pojo.status" value="${Constants['SURVEY_COMMENCING'] }" cssClass="radioCls"/>&nbsp;<label><fmt:message key="label.commencing"/></label>
                            <form:radiobutton path="pojo.status" value="${Constants['SURVEY_ONGOING'] }" cssClass="radioCls"/>&nbsp;<label><fmt:message key="label.ongoing"/></label>
                            <form:radiobutton path="pojo.status" value="${Constants['SURVEY_COMPLETED'] }" cssClass="radioCls"/>&nbsp;<label><fmt:message key="label.completed"/></label>
                            <form:errors path="pojo.status" cssClass="validateError"/>
                        </td>
                    </tr>
                    <tr>
                        <td></td>
                        <td>
                            <form:hidden path="crudaction" id="crudaction" value="insert-update"/>
                            <form:hidden path="pojo.surveyID"/>
                            <input type="button" value="<fmt:message key="button.save"/>" onclick="$('#itemForm').submit();"/>
                            <input type="button" value="<fmt:message key="button.back"/>" onclick="document.location.href='${listURL}';"/>

                        </td>
                    </tr>
                </table>
            </div>
            <c:if test="${not empty messageResponse}">
                <div style="text-align: left; color: red;">
                    <label>${messageResponse}</label>
                </div>
            </c:if>
        </div>

    </form:form>
</div>

<script type="text/javascript">
    $(function() {

        $("#startedDate").datepicker({
            showOn: "button",
            buttonImageOnly: true,
            buttonImage: '<c:url value="/images/iconCalendar.png"/>',
            dateFormat: 'dd/mm/yy'
        });
        $("#endedDate").datepicker({
            showOn: "button",
            buttonImageOnly: true,
            buttonImage: '<c:url value="/images/iconCalendar.png"/>',
            dateFormat: 'dd/mm/yy'
        });
    })

</script>
