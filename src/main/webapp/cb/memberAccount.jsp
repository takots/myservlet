<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<div class="tab">
<div>CB - 前台账号</div>
  <button class="tablinks" onclick="openTabs(event, 'memberAccount_tab1')">冻结原因</button>
  <button class="tablinks" onclick="openTabs(event, '')">close</button>
</div>

<div id="memberAccount_tab1" class="tabcontent">
  <h3 id="MAfreezeReason1">冻结原因1: 連續輸入錯誤被凍結</h3>
  <div class="MAcloseSearch1Div">
       <div class="border">
          <button id="MAclear1" type="button" class="btn btn-danger mb-1">clear</button>
          <button id="MAtest1" type="button" class="btn btn-primary mb-1">带入测试值</button><br>
            測試: CB3 A012 526126564 这边查看该会员原为CB3 A010导入进A012的<br>
                目前从A010查看该会员为冻结状态但查询不到操作记录 请协助查询下是什麽时后把该会员冻结的
        </div>
          <div class="border" style="display: flex;">
            <div class="border form-group row">
            欄位:<br>
            </div>
            <div class="border form-group row">
            回覆:<br>
                2018/05/31 14:56:33<br>
                连续登录失败超过6次，账号已被冻结
            </div>
        </div>
        <div class="border">
          <div class="col MA1Div">
            <div class="form-group row"><label class="col-sm-2 col-form-label">CB:                    </label><input type="text" name="cb" id="MAcb1"><br></div>
              <div class="form-group row"><label class="col-sm-2 col-form-label">站点路径(SITE_PATH):   </label><input type="text" name="sitepath" id="MAsitepath1"><br></div>
              <div class="form-group row"><label class="col-sm-2 col-form-label">后台登录账号(分隔號:、):  </label><input type="text" name="loginname" id="MAloginname1"><br></div>
            </div>
          <div class="col">
              <div class="form-group row"><button id="MAfreeze1" type="button" class="btn btn-primary mb-1">go</button></div>
          </div>
          <div class="border">
              <button id="MAcopy1" type="button" class="btn btn-primary mb-1">copy</button><br>
              <div id="MAselectSql1"></div>
          </div>
      </div>
  </div>
</div>
<script src="cb/js/memberAccount.js"></script>