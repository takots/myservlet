package controller;

import tool.GetIp;
import tool.SomeCookie;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/proxy")//打在網頁根目錄後
public class HelloWorldServlet extends HttpServlet {
    TransferProxyLine transferProxyLine = new TransferProxyLine();
    ProxyLine proxyLine = new ProxyLine();

    PlatformAccount platformAccount = new PlatformAccount();
    MemberAccount memberAccount = new MemberAccount();
    GetIp getIp = new GetIp();
    SomeCookie someCookie = new SomeCookie();

    /**
     * 判斷是我ip
     *
     * @param request
     * @param response
     * @throws IOException
     */
    private void isme(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();
        String password = request.getParameter("password").trim();
        if (password.equals("qwe123")) {
            if (!"10.252.1.149".equals(getIp.getLocalIP())) {
                out.write("ipno");
            } else {
                someCookie.setCookie("isme", "bobwu", 600, response);
                response.setHeader("Refresh", "1;URL=home.jsp");
                out.write("sus");
            }
        } else {
            out.write("run");
        }
        out.close();
    }

    private void memberAccountFreeze(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");//对返回浏览器数据没啥用，不过建议添加
        response.setHeader("Content-type", "text/html;charset=UTF-8");//告知浏览器编码方式;
        response.setCharacterEncoding("UTF-8"); //向前台的页面输出结果的输出流
        Map<String, Object> map = new HashMap<>();
        String step = request.getParameter("step");
        step = step.replace("memberAccount", "").toLowerCase();
        String selectSql = "";
        selectSql = "<pre><code&nbsp;class=\"sql-code\">";

        if(step.contains("freeze")) {
            String cb = request.getParameter("MAcb1").trim();
            String sitepath = request.getParameter("MAsitepath1").trim();
            String superior = request.getParameter("MAloginname1").trim();

            map.put("cb" ,cb);
            map.put("sitepath" ,sitepath);
            map.put("superior" ,superior);
            selectSql += memberAccount.freeze(map);
        }

        PrintWriter out = response.getWriter();
        selectSql += "</code></pre>";
        out.write(selectSql);
        out.close();
    }

    private void platformAccountFreeze(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");//对返回浏览器数据没啥用，不过建议添加
        response.setHeader("Content-type", "text/html;charset=UTF-8");//告知浏览器编码方式;
        response.setCharacterEncoding("UTF-8"); //向前台的页面输出结果的输出流
        Map<String, Object> map = new HashMap<>();
        String step = request.getParameter("step");
        step = step.replace("platformAccount", "").toLowerCase();
        String selectSql = "";
        selectSql = "<pre><code&nbsp;class=\"sql-code\">";

        if(step.contains("freeze")) {
            String cb = request.getParameter("PAcb1").trim();
            String sitepath = request.getParameter("PAsitepath1").trim();
            String superior = request.getParameter("PAloginname1").trim();

            map.put("cb" ,cb);
            map.put("sitepath" ,sitepath);
            map.put("superior" ,superior);
            selectSql += platformAccount.freeze(map);
        }

        PrintWriter out = response.getWriter();
        selectSql += "</code></pre>";
        out.write(selectSql);
        out.close();
    }
    /**
     * CB - 代理线未登入 / 未投注
     *
     * @param request
     * @param response
     * @throws IOException
     */
    private void proxyLineNot(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");//对返回浏览器数据没啥用，不过建议添加
        response.setHeader("Content-type", "text/html;charset=UTF-8");//告知浏览器编码方式;
        response.setCharacterEncoding("UTF-8"); //向前台的页面输出结果的输出流
        Map<String, Object> map = new HashMap<>();
        String step = request.getParameter("step");
        step = step.replace("proxyLine", "").toLowerCase();
        String selectSql = "";
        selectSql = "<pre><code&nbsp;class=\"sql-code\">";

        if (step.contains("notbet")) {
            String cb = request.getParameter("cb5").trim();
            String sitepath = request.getParameter("sitepath5").trim();
            String superior = request.getParameter("superior5").trim();
            String vip = request.getParameter("vip5").trim();
            String dateMinusMonth = request.getParameter("date5M").trim();
            String dateMinusDay = request.getParameter("date5D").trim();
            String dateStart = request.getParameter("date5S").trim();
            String dateEnd = request.getParameter("date5E").trim();

            map.put("cb", cb);
            map.put("sitepath", sitepath);
            map.put("superior", superior);
            map.put("vip", vip);
            map.put("dateMinusMonth", dateMinusMonth);
            map.put("dateMinusDay", dateMinusDay);
            map.put("dateStart", dateStart);
            map.put("dateEnd", dateEnd);

            selectSql += proxyLine.selectNotBet(map);

        } else if (step.contains("notlogin")) {
            String cb = request.getParameter("cb4").trim();
            String sitepath = request.getParameter("sitepath4").trim();
            String superior = request.getParameter("superior4").trim();
            String vip = request.getParameter("vip4").trim();
            String dateMinusMonth = request.getParameter("date4M").trim();
            String dateMinusDay = request.getParameter("date4D").trim();
            String dateStart = request.getParameter("date4S").trim();
            String dateEnd = request.getParameter("date4E").trim();

            map.put("cb", cb);
            map.put("sitepath", sitepath);
            map.put("superior", superior);
            map.put("vip", vip);
            map.put("dateMinusMonth", dateMinusMonth);
            map.put("dateMinusDay", dateMinusDay);
            map.put("dateStart", dateStart);
            map.put("dateEnd", dateEnd);

            selectSql += proxyLine.selectNotLogin(map);
        }

        PrintWriter out = response.getWriter();
        selectSql += "</code></pre>";
        out.write(selectSql);
        out.close();
    }

    /**
     * CB - 转移代理线
     *
     * @param request
     * @param response
     * @throws IOException
     */
    private void transferProxyLine(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");//对返回浏览器数据没啥用，不过建议添加
        response.setHeader("Content-type", "text/html;charset=UTF-8");//告知浏览器编码方式;
        response.setCharacterEncoding("UTF-8"); //向前台的页面输出结果的输出流
        Map<String, Object> map = new HashMap<>();
        String step = request.getParameter("step");
        step = step.replace("transferProxyLine", "").toLowerCase();
        String selectSql = "";
        selectSql = "<pre><code&nbsp;class=\"sql-code\">";

        if (step.equals("step1")) {
            String cb = request.getParameter("cb1").trim();
            String sitepath = request.getParameter("sitepath1").trim();
            String superior = request.getParameter("superior1").trim();
            String subordinate = request.getParameter("subordinate1").trim();

//            System.out.println("cb>"+cb);
//            System.out.println("sitepath>"+sitepath);
//            System.out.println("superior>"+superior);
//            System.out.println("subordinate>"+subordinate);

            map.put("cb", cb);
            map.put("sitepath", sitepath);
            map.put("superior", superior);
            map.put("subordinate", subordinate);

            selectSql += transferProxyLine.select1(map);
            selectSql += transferProxyLine.select2(map);

        } else if (step.equals("step2")) {
            String cb = request.getParameter("cb2").trim();
            String sitepath = request.getParameter("sitepath2").trim();
            String accountNamePath = request.getParameter("accountNamePath").trim();
            String accountIdPath = request.getParameter("accountIdPath").trim();
            String superior = request.getParameter("superior2").trim();
            String superiorId = request.getParameter("superiorId").trim();
            String subordinate = request.getParameter("subordinate2").trim();
            String subordinateId = request.getParameter("subordinateId").trim();
            String platformId = request.getParameter("platformId").trim();

//            System.out.println("cb>"+cb);
//            System.out.println("sitepath>"+sitepath);
//            System.out.println("accountNamePath>"+accountNamePath);
//            System.out.println("accountIdPath>"+accountIdPath);
//            System.out.println("superior>"+superior);
//            System.out.println("superiorId>"+superiorId);
//            System.out.println("subordinate>"+subordinate);
//            System.out.println("subordinateId>"+subordinateId);
//            System.out.println("platformId>"+platformId);

            map.put("cb", cb);
            map.put("accountNamePath", accountNamePath);
            map.put("accountIdPath", accountIdPath);
            map.put("superior", superior);
            map.put("superiorId", superiorId);
            map.put("subordinate", subordinate);
            map.put("subordinateId", subordinateId);
            map.put("platformId", platformId);

            selectSql += transferProxyLine.updateProxyData(map);
            selectSql += transferProxyLine.updateProxyData(sitepath);

        } else if (step.equals("step3")) {
            String cb = request.getParameter("cb3").trim();
            String sitepath = request.getParameter("sitepath3").trim();
            String superior = request.getParameter("superior3").trim();
            String subordinate = request.getParameter("subordinate3").trim();

            map.put("cb", cb);
            map.put("sitepath", sitepath);
            map.put("superior", superior);
            map.put("subordinate", subordinate);

//            System.out.println("cb>"+cb);
//            System.out.println("sitepath>"+sitepath);
//            System.out.println("superior>"+superior);
//            System.out.println("subordinate>"+subordinate);

            selectSql += transferProxyLine.masterDatabaseCheck(map);
            selectSql += transferProxyLine.masterDatabaseCheck(sitepath);
            selectSql += transferProxyLine.masterDatabaseCheck();
        }
        PrintWriter out = response.getWriter();
        selectSql += selectSql.replaceAll(" ", "&nbsp;");
        selectSql += "</code></pre>";
        out.write(selectSql);
        out.close();
    }


    /**
     * int page, int pageSize,
     * String createDatTime, String createDatTimeEnd
     * to
     * map.put("page",page);
     * map.put("createDatTime",createDatTime);
     * map.put("createDatTimeEnd",createDatTimeEnd);
     * 將參數統一放在map，在別支方法由map取出
     */
    private void param2Map2paramStr(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String paramString = request.getParameter("adjustStr");
        StringBuffer stringBuffer = new StringBuffer();
        StringBuffer stringBuffer2 = new StringBuffer();
        stringBuffer.append("Map<String, Object> map = new HashMap<>();<br>");
        String type = "", parameter = "";
//        System.out.println(paramString);
        if (paramString.contains(",")) {
//            System.out.println(",>"+paramString);
            for (String s : paramString.split(",")) {
//                System.out.println("s>"+s);
                // 去頭尾空白
                if(!s.trim().equals("")){
                    type = s.trim().split(" ")[0];
                    parameter = s.trim().split(" ")[1];
                }
                stringBuffer.append("map.put(\"" + parameter + "\" ," + parameter + ");<br>");
                stringBuffer2.append(type + " " + parameter + " = (" + type + ") map.get(\"" + parameter + "\");<br>");
            }
        } else if (paramString.contains(";")) {
//            System.out.println(";>"+paramString);
            String str1 = "";
            for (String s : paramString.split(";")) {
                // 去頭尾空白
                if(!s.trim().equals("")){
                    str1 = s.trim().split("=")[0];
                    type = str1.trim().split(" ")[0];
                    parameter = str1.trim().split(" ")[1];
                }
                stringBuffer.append("map.put(\"" + parameter + "\" ," + parameter + ");<br>");
                stringBuffer2.append(type + " " + parameter + " = (" + type + ") map.get(\"" + parameter + "\");<br>");
            }
        }

        PrintWriter out = response.getWriter();
        out.write(stringBuffer.toString() + "<br>" + stringBuffer2.toString());
        out.close();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String step = request.getParameter("step");
        System.out.println("doPost>"+step);
        if (step != null) {
            if (step.equals("isme")) {
                isme(request, response);
            } else if (step.equals("adjust")) {
                param2Map2paramStr(request, response);
            } else if (step.contains("platformAccountFreeze")) {
                platformAccountFreeze(request, response);
            } else if (step.contains("memberAccountFreeze")) {
                memberAccountFreeze(request, response);
            } else if (step.contains("proxyLineNot")) {
                proxyLineNot(request, response);
            } else if (step.contains("transferProxyLine")) {
                transferProxyLine(request, response);
            }
        } else {
            RequestDispatcher dispatcher = request.getRequestDispatcher("/home.jsp");
            dispatcher.forward(request, response);
        }
    }
}