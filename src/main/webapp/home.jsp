<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="tool.SomeCookie" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.14.7/dist/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-cookie/1.4.1/jquery.cookie.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
    <script src="js/openTabs.js"></script>
    <script src="js/home.js"></script>
    <link  rel="stylesheet" type="text/css" href="css/home.css">
    <link  rel="stylesheet" type="text/css" href="css/tabs.css">
    <title>stopitgetsomehelp</title>
</head>
<body>
<%
SomeCookie someCookie = new SomeCookie();
boolean isme = someCookie.checkCookie(request, response);
%>
<div class="col">
    <div class="row">
        <div class="form-group col-1" style="background-color:#f1f1f1;">
        <!--
            target= _blank 新分頁
            target= _self  相同頁面
        -->
            <a href="cb" target="_self" class="btn toOtherPage">cb</a><br>
        </div>
        <div class="form-group col-11">
            <%if(isme){%>
                <jsp:include page="hidden.jsp" />
            </div>
            <%}else{%>
                <jsp:include page="personal.jsp" />
            <%}%>
            <jsp:include page="codeAdjust.jsp" />
        </div>
    </div>
</div>
</body>
</html>