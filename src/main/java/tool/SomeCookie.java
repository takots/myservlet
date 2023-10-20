package tool;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SomeCookie {
    private static final Logger logger = LogManager.getLogger(SomeCookie.class);

    GetIp getIp = new GetIp();
    /**
     * 獲取cookie 目前只有 isme 這個cookie
     * @param request
     * @param response
     * @throws IOException
     */
    public boolean checkCookie(HttpServletRequest request, HttpServletResponse response) throws IOException {
        boolean isme = false;
        // 获取所有的Cookie
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("isme".equals(cookie.getName())) {
                    String username = cookie.getValue();
                    logger.info("checkCookie ip is {}",getIp.getLocalIP());
                    if (getIp.getLocalIP().equals(username)) {
                        isme = true;
                    }
                }
            }
        }
        return isme;
    }

    /**
     * 放個cookie 給個人畫面使用
     * @param name
     * @param value
     * @param setMaxAge
     * @param response
     */
    public void setCookie(String name ,String value ,Integer setMaxAge,HttpServletResponse response){
        Cookie cookie = new Cookie(name, value);
        cookie.setMaxAge(setMaxAge); //以秒为单位
        response.addCookie(cookie);
    }
}
