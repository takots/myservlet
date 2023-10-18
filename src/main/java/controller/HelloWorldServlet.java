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
    GetIp getIp = new GetIp();
    SomeCookie someCookie = new SomeCookie();
    private void isme(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();
        String password = request.getParameter("password").trim();
        if(password.equals("qwe123")){
            if(!"10.252.1.149".equals(getIp.getLocalIP())){
                out.write("ipno");
            }else {
                someCookie.setCookie("isme", "bobwu" ,60000 ,response);
                response.setHeader("Refresh", "1;URL=home.jsp");
                out.write("sus");
            }
        }else {
            out.write("run");
        }
        out.close();
    }

    /**
     * CB - 转移代理线
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
        String selectSql = "";
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

            selectSql = "<pre><code class=\"sql-code\">";
            selectSql += transferProxyLine.select1(map);
            selectSql += transferProxyLine.select2(map);
            selectSql += "</code></pre>";

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

            selectSql = "<pre><code class=\"sql-code\">";
            selectSql += transferProxyLine.updateProxyData(map);
            selectSql += transferProxyLine.updateProxyData(sitepath);
            selectSql += "</code></pre>";

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

            selectSql = "<pre><code class=\"sql-code\">";
            selectSql += transferProxyLine.masterDatabaseCheck(map);
            selectSql += transferProxyLine.masterDatabaseCheck(sitepath);
            selectSql += transferProxyLine.masterDatabaseCheck();
            selectSql += "</code></pre>";
        }
        PrintWriter out = response.getWriter();
        out.write(selectSql.replaceAll(" ", "&nbsp;"));
        out.close();
    }


    /**
     * int page, int pageSize,
     * 			String createDatTime, String createDatTimeEnd
     * 		to
     * 	map.put("page",page);
     * 	map.put("createDatTime",createDatTime);
     * 	map.put("createDatTimeEnd",createDatTimeEnd);
     * 	將參數統一放在map，在別支方法由map取出
     */
    private void param2Map2paramStr(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String paramString = request.getParameter("adjustStr");
        StringBuffer stringBuffer = new StringBuffer();
        StringBuffer stringBuffer2 = new StringBuffer();
        stringBuffer.append("Map<String, Object> map = new HashMap<>();<br>");
        String type = "",parameter="";
        for(String s : paramString.replaceAll("&nbsp;","").split(",")){
            // 去頭尾空白
            type = s.trim().split(" ")[0];
            parameter = s.trim().split(" ")[1];
            stringBuffer.append("map.put(\""+parameter+"\" ,"+parameter+");<br>");
            stringBuffer2.append(type +" "+ parameter + " = ("+type+") map.get(\""+parameter+"\"));<br>");
        }
        PrintWriter out = response.getWriter();
        out.write(stringBuffer.toString()+"<br>"+stringBuffer2.toString());
        out.close();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String step = request.getParameter("step");
        if (step != null) {
            if (step.equals("isme")) {
                isme(request, response);
            }else if (step.equals("adjust")) {
                param2Map2paramStr(request, response);
            } else {
                transferProxyLine(request, response);
            }
        } else {
            RequestDispatcher dispatcher = request.getRequestDispatcher("/home.jsp");
            dispatcher.forward(request, response);
        }
    }
}