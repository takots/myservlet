<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<div class="tab">
<div>CB - 后台账号</div>
  <button class="tablinks" onclick="openTabs(event, 'platformAccount_tab1')">冻结原因</button>
  <button class="tablinks" onclick="openTabs(event, '')">close</button>
</div>

<div id="platformAccount_tab1" class="tabcontent">
  <h3 id="PAfreezeReason1">冻结原因1: 連續輸入錯誤被凍結</h3>
  <div class="PAcloseSearch1Div">
       <div class="border">
          <button id="PAclear1" type="button" class="btn btn-danger mb-1">clear</button>
          <button id="PAtest1" type="button" class="btn btn-primary mb-1">带入测试值</button><br>
            測試: CB3 A021 后台登录账号cpcaiwu04、cpcaiwu03、 cpcaiwu01被冻结，请协助查询什么原因冻结<br>
        </div>
          <div class="border" style="display: flex;">
            <div class="border form-group row">
            欄位:<br>
                IS_FREEZE:    是否冻结，1=未冻结，2=已冻结<br>
                ERROR_TIMES:  当前登录密码错误次数
            </div>
            <div class="border form-group row">
            回覆:<br>
                三個會員都是 登入時密碼連續輸入錯誤被凍結(ERROR_TIMES)<br>
                以下是被凍結的時間(最后登录时间 + 账号)<br>
                2023/10/19 09:06:22 cpcaiwu01<br>
                2023/10/19 03:58:03 cpcaiwu03<br>
                2023/10/18 21:07:32 cpcaiwu04
            </div>
        </div>
        <div class="border">
          <div class="col PA1Div">
            <div class="form-group row"><label class="col-sm-2 col-form-label">CB:                        </label><input type="text" name="cb" id="PAcb1"><br></div>
              <div class="form-group row"><label class="col-sm-2 col-form-label">站点路径(SITE_PATH):       </label><input type="text" name="sitepath" id="PAsitepath1"><br></div>
              <div class="form-group row"><label class="col-sm-2 col-form-label">后台登录账号(分隔號:、):     </label><input type="text" name="loginname" id="PAloginname1"><br></div>
            </div>
          <div class="col">
              <div class="form-group row"><button id="PAfreeze1" type="button" class="btn btn-primary mb-1">go</button></div>
          </div>
          <div class="border">
              <button id="PAcopy1" type="button" class="btn btn-primary mb-1">copy</button><br>
              <div id="PAselectSql1"></div>
          </div>
      </div>
  </div>
</div>
<script src="cb/js/platformAccount.js"></script>