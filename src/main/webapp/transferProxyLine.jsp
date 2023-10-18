<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<link  rel="stylesheet" type="text/css" href="css/transferProxyLine.css">
<div class="tab">
<div>CB - 转移代理线</div>
  <button class="transferProxyLine_functionDescription tablinks" type="button">功能说明</button>
  <button class="tablinks" id="clearall" type="button">clear all</button>
  <button class="tablinks" onclick="openTabs(event, 'transferProxyLine_tab1')">step1</button>
  <button class="tablinks" onclick="openTabs(event, 'transferProxyLine_tab2')">step2</button>
  <button class="tablinks" onclick="openTabs(event, 'transferProxyLine_tab3')">step3</button>
  <button class="tablinks" onclick="openTabs(event, '')">close</button>
</div>

<div id="transferProxyLine_tab1" class="tabcontent">
  <h3>查詢</h3>

  <div class="border">
      <button type="button" class="transferProxyLine_functionDescription_inside btn btn-info mb-1">功能说明</button>
      <button id="clear1" type="button" class="btn btn-danger mb-1">clear</button>
      <button id="test1" type="button" class="btn btn-primary mb-1">带入测试值</button><br>
      測試: CB5 F108 上级 flcp2023/下级 flc001<br>
  </div>
  <div class="border">
      <div class="col step1Div">
          <div class="form-group row"><label class="col-sm-2 col-form-label">CB:                </label><input type="text" name="cb" id="cb1"><br></div>
          <div class="form-group row"><label class="col-sm-2 col-form-label">站点路径(SITE_PATH): </label><input type="text" name="sitepath" id="sitepath1"><br></div>
          <div class="form-group row"><label class="col-sm-2 col-form-label">上级:               </label><input type="text" name="superior" id="superior1"><br></div>
          <div class="form-group row"><label class="col-sm-2 col-form-label">下级:               </label><input type="text" name="subordinate" id="subordinate1"><br></div>
      </div>
      <div class="col">
          <div class="form-group row"><button id="step1" type="button" class="btn btn-primary mb-1">go</button></div>
      </div>
      <div class="border">
          <button id="copy1" type="button" class="btn btn-primary mb-1">copy</button><br>
          <div id="selectSql1"></div>
      </div>
  </div>
</div>

<div id="transferProxyLine_tab2" class="tabcontent">
  <h3>更新</h3>

  <div class="border">
      <button type="button" class="transferProxyLine_functionDescription_inside btn btn-info mb-1">功能说明</button>
      <button id="clear2" type="button" class="btn btn-danger mb-1">clear</button>
      <button id="test2" type="button" class="btn btn-primary mb-1">带入测试值</button><br>
       測試: CB5 F108 上级 flcp2023 ,405112342/下级 flc001 ,405112641<br>
              liebian001/weihu001/flc001/<br>
              405237091/405244005/405112641/<br>
              40,005<br>
  </div>
  <div class="border">
      <div class="col step2Div">
          <div class="form-group row"><label class="col-sm-2 col-form-label">CB:                   </label><input type="text" name="cb" id="cb2"><br></div>
          <div class="form-group row"><label class="col-sm-2 col-form-label">站点路径(SITE_PATH):   </label><input type="text" name="sitepath" id="sitepath2"><br></div>
          <div class="form-group row"><label class="col-sm-2 col-form-label">accountNamePath:      </label><input type="text" name="accountNamePath" id="accountNamePath"><br></div>
          <div class="form-group row"><label class="col-sm-2 col-form-label">accountIdPath:        </label><input type="text" name="accountIdPath" id="accountIdPath"><br></div>
          <div class="form-group row"><label class="col-sm-2 col-form-label">platformId:           </label><input type="text" name="platformId" id="platformId"><br></div>
          <div class="form-group row"><label class="col-sm-2 col-form-label">上级:                  </label><input type="text" name="superior" id="superior2"><br></div>
          <div class="form-group row"><label class="col-sm-2 col-form-label">上级ID:                </label><input type="text" name="superiorId" id="superiorId"><br></div>
          <div class="form-group row"><label class="col-sm-2 col-form-label">下级:                  </label><input type="text" name="subordinate" id="subordinate2"><br></div>
          <div class="form-group row"><label class="col-sm-2 col-form-label">下级ID:                </label><input type="text" name="subordinateId" id="subordinateId"><br></div>
      </div>
      <div class="col">
          <div class="form-group row"><button id="step2" type="button" class="btn btn-primary mb-1">go</button></div>
      </div>
      <div class="border">
          <button id="copy2" type="button" class="btn btn-primary mb-1">copy</button><br>
          <div id="selectSql2"></div>
      </div>
  </div>
</div>

<div id="transferProxyLine_tab3" class="tabcontent">
  <h3>檢查</h3>

    <div class="border">
        <button type="button" class="transferProxyLine_functionDescription_inside btn btn-info mb-1">功能说明</button>
        <button id="clear3" type="button" class="btn btn-danger mb-1">clear</button>
        <button id="test3" type="button" class="btn btn-primary mb-1">带入测试值</button><br>
        測試: CB5 F108 上级 flcp2023/下级 flc001<br>
    </div>
    <div class="border">
        <div class="col step3Div">
            <div class="form-group row"><label class="col-sm-2 col-form-label">CB:                </label><input type="text" name="cb" id="cb3"><br></div>
            <div class="form-group row"><label class="col-sm-2 col-form-label">站点路径(SITE_PATH): </label><input type="text" name="sitepath" id="sitepath3"><br></div>
            <div class="form-group row"><label class="col-sm-2 col-form-label">上级:               </label><input type="text" name="superior" id="superior3"><br></div>
            <div class="form-group row"><label class="col-sm-2 col-form-label">下级:               </label><input type="text" name="subordinate" id="subordinate3"><br></div>
        </div>
        <div class="col">
            <div class="form-group row"><button id="step3" type="button" class="btn btn-primary mb-1">go</button></div>
        </div>
        <div class="border">
            <button id="copy3" type="button" class="btn btn-primary mb-1">copy</button><br>
            <div id="selectSql3"></div>
        </div>
    </div>
</div>
<script src="js/transferProxyLine.js"></script>