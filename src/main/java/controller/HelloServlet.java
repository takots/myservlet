package controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import tool.GetIp;
import tool.SomeCookie;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/proxy")//打在網頁根目錄後
public class HelloServlet  extends HttpServlet {
    private static final Logger logger = LogManager.getLogger(HelloServlet.class);

    GetIp getIp = new GetIp();
    SomeCookie someCookie = new SomeCookie();
    /**
     * ip
     * @param request
     * @param response
     * @throws IOException
     */
    private void isme(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();
        String password = request.getParameter("password").trim();
        if (password.equals("qwe123")) {
            // 比對本地
//            if (!"10.252.1.149".equals(getIp.getLocalIP())) {
//                out.write("ipno");
//            } else {
            logger.info("hello ip is {}",getIp.getLocalIP());
            someCookie.setCookie("isme", getIp.getLocalIP(), 600, response);
            response.setHeader("Refresh", "1;URL=home.jsp");
            out.write("sus");
//            }
        } else {
            out.write("run");
        }
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
        logger.info("HelloServlet: {}",step);
        if (step != null) {
            if (step.equals("isme")) {
                isme(request, response);
            } else if (step.equals("adjust")) {
                param2Map2paramStr(request, response);
            }
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher("/home.jsp");
        dispatcher.forward(request, response);
    }
}
