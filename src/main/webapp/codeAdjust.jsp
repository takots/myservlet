<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<div class="tab">
<div>Adjustment</div>
  <button class="tablinks" onclick="openTabs(event, 'adjustment_tab1')">adjust1</button>
  <button class="tablinks" onclick="openTabs(event, 'adjustment_tab2')">adjust2</button>
  <button class="tablinks" onclick="openTabs(event, '')">close</button>
</div>
<div id="adjustment_tab1" class="tabcontent">
    <h3>Add some parameter to a Map</h3>
    <div class="border">
      <button id="adjustClear1" type="button" class="btn btn-danger mb-1">clear</button>
      <button id="adjustTest1" type="button" class="btn btn-primary mb-1">带入测试值1</button>
      <button id="adjustTest2" type="button" class="btn btn-primary mb-1">带入测试值2</button><br>
     <img src="/images/snipaste101618.png" with="600" heigh="400" alt="snipaste101618.png"><br>
     测试值1:<br>
     int page, int pageSize,&nbsp;&nbsp;<br>
     			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;String createDatTime, String createDatTimeEnd, Integer accountId,String loteryId,String cpLoteryId&nbsp;&nbsp;&nbsp;<br>
     			&nbsp;&nbsp;&nbsp;,String status,boolean isNew,Integer lastId,String tradeType<br>
    <img src="/images/snipaste101817.png" with="600" heigh="400" alt="snipaste101817.png"><br>.
    测试值2:<br>
        String customSettingId = request.getParameter("customSettingId");<br>
        		&nbsp;&nbsp;&nbsp;String remarks = request.getParameter("remarks").trim();<br>
        		&nbsp;&nbsp;&nbsp;String result = request.getParameter("result");<br>
        		&nbsp;&nbsp;&nbsp;String ids = request.getParameter("ids");<br>
    </div>
  <div class="border">
      <div class="col adjust1Div">
          <div class="form-group row"><label class="col-sm-2 col-form-label">參數: </label><input class="col-sm-10" type="text" name="adjustStr" id="adjustStr"><br></div>
      </div>
      <div class="col">
          <div class="form-group row"><button id="adjust" type="button" class="btn btn-primary mb-1">go</button></div>
      </div>
      <div class="border">
          <button id="adjustCopy1" type="button" class="btn btn-primary mb-1">copy</button><br>
          <div id="adjustSql1"></div>
      </div>
  </div>
</div>
<div id="adjustment_tab2" class="tabcontent">
    <div class="border">
    nothing here
    </div>
</div>
<script src="js/codeAdjust.js"></script>