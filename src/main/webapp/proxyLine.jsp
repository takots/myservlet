<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<div id="proxyLine_tab4" class="tabcontent">
    <h3>代理线xxx底下xx天未登入的下级账号</h3>

    <div class="border">
        <button type="button" class="proxyLine_functionDescription_inside btn btn-info mb-1">功能说明</button>
        <button id="clear4" type="button" class="btn btn-danger mb-1">clear</button>
        <button id="test4" type="button" class="btn btn-primary mb-1">带入测试值</button><br>
        測試: CB5 B136 代理线sszy底下
        VIP2超过30天未登入的会员
        需要的栏位：上级帐号，用户账号，真实姓名，vip等级<br>
    </div>

    <div class="border">
        <div class="col proxyLine4Div">
            <div class="form-group row"><label class="col-sm-2 col-form-label">CB:                </label><input type="text" name="cb" id="cb4"><br></div>
            <div class="form-group row"><label class="col-sm-2 col-form-label">站点路径(SITE_PATH): </label><input type="text" name="sitepath" id="sitepath4"><br></div>
            <div class="form-group row"><label class="col-sm-2 col-form-label">上级:               </label><input type="text" name="superior" id="superior4"><br></div>
            <div class="form-group row"><label class="col-sm-2 col-form-label">VIP:               </label><input type="text" name="vip" id="vip4"><br></div>
            <div class="form-group row"><label class="col-sm-2 col-form-label">時間範圍0:  </label><input type="text" name="date" id="date4M"><br></div>
            <div class="form-group row"><label class="col-sm-2 col-form-label">時間範圍1:  </label><input type="text" name="date" id="date4D"><br></div>
            <div class="row">
                <div class="form-group col-2">
                    <label class="col-form-label">時間範圍2(區間):</label>
                </div>
                <div class="form-group col-1">
                    <button id="clearRangeDate" type="button" class="btn btn-danger mb-1">clear Range</button>
                </div>
                <div class="form-group col-2">
                    <input type="date" class="form-control" id="date4S" name="date">
                </div>
                <div class="form-group col-2">
                    <input type="date" class="form-control" id="date4E" name="date">
                </div>
            </div>
        </div>
        <div class="col">
            <div class="form-group row"><button id="proxyLineNotLogin" type="button" class="btn btn-primary mb-1">go</button></div>
        </div>
        <div class="border">
            <button id="copy4" type="button" class="btn btn-primary mb-1">copy</button><br>
            <div id="selectSql4"></div>
        </div>
    </div>
</div>

<div id="proxyLine_tab5" class="tabcontent">
     <h3>代理线xxx底下xx天未投注的下级账号</h3>

    <div class="border">
        <button type="button" class="proxyLine_functionDescription_inside btn btn-info mb-1">功能说明</button>
        <button id="clear5" type="button" class="btn btn-danger mb-1">clear</button>
        <button id="test5" type="button" class="btn btn-primary mb-1">带入测试值</button><br>
        測試: CB5 B136  sszy
            麻烦协助导出该账号的所有超过两个月未投注的下级账号
            只需要账号栏位即可<br>
    </div>

    <div class="border">
        <div class="col proxyLine5Div">
            <div class="form-group row"><label class="col-sm-2 col-form-label">CB:                </label><input type="text" name="cb" id="cb5"><br></div>
            <div class="form-group row"><label class="col-sm-2 col-form-label">站点路径(SITE_PATH): </label><input type="text" name="sitepath" id="sitepath5"><br></div>
            <div class="form-group row"><label class="col-sm-2 col-form-label">上级:               </label><input type="text" name="superior" id="superior5"><br></div>
            <div class="form-group row"><label class="col-sm-2 col-form-label">VIP:               </label><input type="text" name="vip" id="vip5"><br></div>
            <div class="form-group row"><label class="col-sm-2 col-form-label">時間範圍0:  </label><input type="text" name="date" id="date5M"><br></div>
            <div class="form-group row"><label class="col-sm-2 col-form-label">時間範圍1:  </label><input type="text" name="date" id="date5D"><br></div>
            <div class="row">
                <div class="form-group col-2">
                    <label class="col-form-label">時間範圍2(區間):</label>
                </div>
                <div class="form-group col-1">
                    <button id="clearRangeDate" type="button" class="btn btn-danger mb-1">clear Range</button>
                </div>
                <div class="form-group col-2">
                    <input type="date" class="form-control" id="date5S" name="date">
                </div>
                <div class="form-group col-2">
                    <input type="date" class="form-control" id="date5E" name="date">
                </div>
            </div>
        </div>
        <div class="col">
            <div class="form-group row"><button id="proxyLineNotBet" type="button" class="btn btn-primary mb-1">go</button></div>
        </div>
        <div class="border">
            <button id="copy5" type="button" class="btn btn-primary mb-1">copy</button><br>
            <div id="selectSql5"></div>
        </div>
    </div>
</div>
<script src="js/proxyLine.js"></script>