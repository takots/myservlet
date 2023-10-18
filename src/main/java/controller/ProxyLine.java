package controller;

import java.util.Map;

public class ProxyLine {
    public String selectNotBet(Map<String, Object> map) {
        String cb = (String) map.get("cb");
        String sitepath = (String) map.get("sitepath");
        String superior = (String) map.get("superior");
        String vip = (String) map.get("vip");
        String dateMinusMonth = (String) map.get("dateMinusMonth");
        String dateMinusDay = (String) map.get("dateMinusDay");
        String dateStart = (String) map.get("dateStart");
        String dateEnd = (String) map.get("dateEnd");

        String sql = "幫查 " + cb + " 主庫\n" +
                "```sql\n" +
                "SELECT query.LOGIN_NAME \"账号\"\n" +
                "FROM (\n" +
                "\tSELECT aa.id \"accid\", aa.LOGIN_NAME\n" +
                "\tFROM AUTH_ACCOUNT aa\n" +
                "\tWHERE aa.PLATFORM_ID = (SELECT id FROM PUB_SITE ps WHERE site_path = '" + sitepath + "')\n" +
                "\t\tAND (\n" +
                "\t\t\taa.ACCOUNT_NAME_PATH LIKE '%/" + superior + "/%'\n" +
                "\t\t)\n" +
                ") query\n" +
                "WHERE EXISTS (\n" +
                "\tSELECT 1\n" +
                "\t\tFROM RELAY_BET rb \n" +
                "\t\tWHERE rb.ACCOUNT_ID = query.\"accid\"\n";
        if(!dateMinusMonth.equals("")){
            if(Integer.parseInt(dateMinusMonth) <=9 ){
                dateMinusMonth = "0"+dateMinusMonth;
            }
            sql += "\t\t\tAND  rb.CLEARING_DATE >= timestamp '2023-"+dateMinusMonth+"-01 00:00:00'\n";
        }else if(!dateMinusDay.equals("")){
            sql += "\t\t\tAND  rb.CLEARING_DATE >= CURRENT_DATE - INTERVAL '"+dateMinusDay+"' DAY\n";
        }
        sql += ")\n" +"ORDER BY query.LOGIN_NAME ;";

        return sql.replaceAll("\n","<br>").replaceAll("\t","&nbsp;");
    }

    public String selectNotLogin(Map<String, Object> map) {
        return null;
    }
}
