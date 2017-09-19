<%--
  Created by IntelliJ IDEA.
  User: dung.nguyen-tien
  Date: 9/19/2017
  Time: 9:53 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="admin.survey.form.title"/></title>
    <meta name="heading" content="Survey Management"/>
</head>
<div class="pathway">
    <fmt:message key="admin.survey.form.heading"/>
</div>
<c:set var="date_format" value="${Constants['DATE_FORMAT'] }"/>
<c:url var="url" value="/admin/survey/list.html"/>
<c:url var="editURL" value="/admin/survey/edit.html"/>
<div id="content">
    <form:form commandName="items" action="${url}" method="post" id="listForm">
        <div class="box_container">
            <div class="header">
                <fmt:message key="admin.survey.form.heading"/>
            </div>
            <div class="form">
                <table width="100%" cellpadding="5" cellspacing="5" border="0">
                    <tr>
                        <td><fmt:message key="label.survey"/></td>
                        <td><form:input path="pojo.surveyName" size="40"/></td>

                    </tr>
                    <tr>
                        <td><fmt:message key="label.createddate"/></td>
                        <td><input id="fromDate" name="createdDateFrom" size="15" value="<fmt:formatDate value='${items.createdDateFrom }' pattern='${date_format }'/>"/></td>

                        <td><fmt:message key="label.to"/></td>
                        <td><input id="toDate" name="createdDateTo" size="15" value="<fmt:formatDate value='${items.createdDateTo }' pattern='${date_format }'/>"/></td>
                    </tr>
                    <tr>
                        <td><fmt:message key="label.status"/></td>
                        <td colspan="3">
                            <form:radiobutton path="pojo.status" value="" cssClass="radioCls" checked="true"/>&nbsp;<label><fmt:message key="label.all"/></label>
                            <form:radiobutton path="pojo.status" value="${Constants['USER_ACTIVE'] }" cssClass="radioCls"/>&nbsp;<label><fmt:message key="label.commencing"/></label>
                            <form:radiobutton path="pojo.status" value="${Constants['USER_INACTIVE'] }" cssClass="radioCls"/>&nbsp;<label><fmt:message key="label.ongoing"/></label>
                            <form:radiobutton path="pojo.status" value="${Constants['USER_DISABLED'] }" cssClass="radioCls"/>&nbsp;<label><fmt:message key="label.completed"/></label>
                        </td>

                    </tr>
                    <tr>
                        <td></td>
                        <td colspan="3">
                            <form:hidden path="crudaction" id="crudaction" value="search"/>
                            <input type="button" value="<fmt:message key="button.search"/>" onclick="$('#listForm').submit();"/>
                        </td>
                    </tr>
                </table>
            </div>

        </div>

        <br/>
        <div class="box_container" >

            <display:table name="items.listResult" cellspacing="0" cellpadding="0" requestURI="${url}"
                           partialList="true" sort="external" size="${items.totalItems}" defaultsort="4" uid="tableList" pagesize="${items.maxPageItems}" class="table bright_blue_body" export="false">
                <display:column headerClass="table_header" sortable="false" style="width: 3%" title="<input type=\"checkbox\" name=\"allCheck\" id=\"allCheck\" onclick=\"checkAll('listForm', 'checkList', this)\">">
                    <input type="checkbox" name="checkList" value="${tableList.surveyID}" onclick="checkAllIfOne('listForm', 'checkList', this, 'allCheck')">
                </display:column>
                <display:column headerClass="table_header" property="surveyName" escapeXml="true" sortable="true" sortName="surveyName" titleKey="Survey Name" style="width: 30%"/>

                <display:column headerClass="table_header" sortName="status" escapeXml="true" sortable="true" titleKey="label.status" style="width:10%">
                    <c:choose>
                        <c:when test="${tableList.status == Constants['COMMENCING_SURVEY']}">
                            <fmt:message key="label.commencing"/>
                        </c:when>
                        <c:when test="${tableList.status == Constants['COMPLETED_SURVEY']}">
                            <fmt:message key="label.completed"/>
                        </c:when>
                        <c:otherwise>
                            <fmt:message key="label.ongoing"/>
                        </c:otherwise>
                    </c:choose>
                </display:column>
                <display:column headerClass="table_header" sortName="createdDate" escapeXml="true" sortable="true" titleKey="label.createddate" style="width:10%">
                    <fmt:formatDate pattern="${date_format}" value="${tableList.createdDate }"/>
                </display:column>
                <display:column headerClass="table_header" sortName="modifiedDate" escapeXml="true" sortable="true" titleKey="label.modifieddate" style="width:10%">
                    <fmt:formatDate pattern="${date_format}" value="${tableList.modifiedDate }"/>
                </display:column>
                <display:column sortable="false"  headerClass="table_header" url="/admin/survey/edit.html"
                                titleKey="label.options" style="width: 10%">
                    <a href="${editURL}?pojo.surveyID=${tableList.surveyID}"><img src="<c:url value='/images/icons/edit.png'/>"/></a>
                    <a name="deleteLink" id="d_${tableList.surveyID}_a" href="<c:url value='/admin/survey/list.html'><c:param name="checkList" value="${tableList.surveyID}"/><c:param name="crudaction" value="delete"/></c:url>"><img id="d_${tableList.surveyID}" src="<c:url value='/images/icons/no.png'/>"/></a>

                </display:column>
                <display:setProperty name="paging.banner.item_name" value="survey"/>
                <display:setProperty name="paging.banner.items_name" value="surveys"/>
                <display:setProperty name="paging.banner.placement" value="bottom"/>
                <display:setProperty name="paging.banner.no_items_found" value=""/>
            </display:table>
            <div class="clear"></div>
        </div>
        <div class="adminListButton">
            <c:if test="${not empty totalDeleted}">
                <div class="msg-response"><fmt:message key="database.items.deleted"><fmt:param value="${totalDeleted}"/></fmt:message></div>
                <br />
            </c:if>
            <c:if test="${not empty messageResponse}">
                <div class="msg-response">${messageResponse }</div>
                <br/>
            </c:if>
            <input type="button" value="<fmt:message key="button.add"/>" onclick="document.location.href='${editURL}';"/>

            <c:if test="${not empty items.totalItems && items.totalItems > 0}">
                <input type="button" value="<fmt:message key="button.delete"/>" onclick="confirmDeleteItems();"/>
            </c:if>
        </div>

    </form:form>
</div>

<script type="text/javascript">
    <c:if test="${not empty items.crudaction}">
    highlightTableRows("tableList");
    </c:if>
    $(function() {
        $("#fromDate" ).datepicker({ showOn: "button", buttonImageOnly: true, buttonImage: '<c:url value="/images/iconCalendar.png"/>', dateFormat:'dd/mm/yy' });
        $("#toDate" ).datepicker({ showOn: "button", buttonImageOnly: true, buttonImage: '<c:url value="/images/iconCalendar.png"/>', dateFormat:'dd/mm/yy' });

        $("#hiddenDeleteConfirmLink").click(function() {
            $('#crudaction').val('delete');
            jConfirm('<fmt:message key="survey.deleted.confirm.message"/>', '<fmt:message key="delete.confirm.title"/>', function(r) {
                if(r) {
                    document.forms['listForm'].submit();
                }
            });
            return false;
        });
        $('a[name="deleteLink"]').click(function(eventObj) {
            jConfirm('<fmt:message key="survey.deleted.one.confirm.message"/>', '<fmt:message key="delete.confirm.title"/>', function(r) {
                if(r) {
                    location.href = $('#' + eventObj.target.id + '_a').attr('href');
                }
            });
            return false;
        });

    });
    function confirmDeleteItems(){
        var fb = checkIsOneItemSelected('listForm', 'checkList');
        if(fb) {
            $("#hiddenDeleteConfirmLink").trigger('click');
        }else {
            $("#hidenDeleteWarningLink").trigger('click');
        }
    }

</script>
