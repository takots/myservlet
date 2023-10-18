<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<link  rel="stylesheet" type="text/css" href="css/hidden.css">
<div class="tab">
<div>Personal</div>
  <button class="tablinks" onclick="openTabs(event, 'personal_tab1')">舒马特方格</button>
  <button class="tablinks" onclick="openTabs(event, 'personal_tab2')">GO2</button>
  <button class="tablinks" onclick="openTabs(event, '')">close</button>
</div>
<div id="personal_tab1" class="tabcontent">
    <h3>舒马特方格專注力訓練</h3>
    5 x 5: 15秒内依序找出 1 到 25 <br>
    倒數計時: <div class="border" id="timer">10</div>
    <br>
    <div class="border randomDiv">
    </div>
    <div class="border">
      <button id="restart1" type="button" class="btn btn-primary mb-1">restart</button><br>
    </div>
</div>
<div id="personal_tab2" class="tabcontent">
    <div class="border">
    </div>
</div>
<script src="js/hidden.js"></script>