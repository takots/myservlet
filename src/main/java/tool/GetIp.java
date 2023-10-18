package tool;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class GetIp {

    /**
     *  获取本地主机的InetAddress实例
     * @return
     */
    public String getLocalIP(){
        InetAddress localhost = null;
        try {
            localhost = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return localhost.getHostAddress();
    }
}
