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
                "\tWHERE aa.PLATFORM_ID = (SELECT id FROM PUB_SITE ps WHERE site_path = '" + sitepath + "')\n";

        if (!vip.equals("")) {
            sql += "AND aa.VIP_USER_LEVEL = " + vip + "\n";
        }

        sql += "\t\tAND aa.ACCOUNT_NAME_PATH LIKE '%/" + superior + "/%'\n" +
                ") query\n" +
                "WHERE EXISTS (\n" +
                "\tSELECT 1\n" +
                "\t\tFROM RELAY_BET rb \n" +
                "\t\tWHERE rb.ACCOUNT_ID = query.\"accid\"\n";

        if (!dateMinusMonth.equals("")) {
            if (Integer.parseInt(dateMinusMonth) <= 9) {
                dateMinusMonth = "0" + dateMinusMonth;
            }
            sql += "\t\t\tAND  rb.CLEARING_DATE >= timestamp '2023-" + dateMinusMonth + "-01 00:00:00'\n";

        } else if (!dateStart.equals("") && !dateEnd.equals("")) {
            sql += "\t\t\tAND  rb.CLEARING_DATE >= timestamp '" + dateStart + " 00:00:00'\n";
            sql += "\t\t\tAND  rb.CLEARING_DATE <= timestamp '" + dateEnd + " 23:59:59'\n";

        } else if (!dateMinusDay.equals("")) {
            sql += "\t\t\tAND  rb.CLEARING_DATE >= CURRENT_DATE - INTERVAL '" + dateMinusDay + "' DAY\n";
        }

        sql += ")\n" + "ORDER BY query.LOGIN_NAME ;";

        return sql.replaceAll("\n", "<br>").replaceAll("\t", "&nbsp;");
    }

    public String selectNotLogin(Map<String, Object> map) {
        String cb = (String) map.get("cb");
        String sitepath = (String) map.get("sitepath");
        String superior = (String) map.get("superior");
        String vip = (String) map.get("vip");
        String dateMinusMonth = (String) map.get("dateMinusMonth");
        String dateMinusDay = (String) map.get("dateMinusDay");
        String dateStart = (String) map.get("dateStart");
        String dateEnd = (String) map.get("dateEnd");

        String sql = "幫查 " + cb + "\n" +
                "```sql\n" +
                "SELECT aa.PARENT_NAME \"上级帐号\", aa.LOGIN_NAME \"用户账号\",aad.REAL_NAME \"真实姓名\",aa.VIP_USER_LEVEL \"vip等级\"\n" +
                "FROM AUTH_ACCOUNT aa\n" +
                "LEFT JOIN AUTH_ACCOUNT_DETAIL aad ON aa.ID = aad.ACCOUNT_ID\n" +
                "WHERE aa.PLATFORM_ID = (SELECT id FROM PUB_SITE ps WHERE site_path = '" + sitepath + "')\n";

        if (!vip.equals("")) {
            sql += "AND aa.VIP_USER_LEVEL = " + vip + "\n";
        }

        sql += "AND ACCOUNT_Name_PATH LIKE '%" + superior + "/%'\n" +
                "-- 排除xx天有登入\n" +
                "AND AA.ID NOT IN (\n" +
                "    SELECT ACCOUNT_ID  FROM AUTH_LOGIN_VIEW  \n" +
                "    WHERE LOGIN_STATE = '会员端:登录成功'\n";

        if (!dateMinusMonth.equals("")) {
            if (Integer.parseInt(dateMinusMonth) <= 9) {
                dateMinusMonth = "0" + dateMinusMonth;
            }
            sql += "\t\tAND LOGIN_TIME  >= timestamp '2023-09-01 00:00:00'\n";

        } else if (!dateStart.equals("") && !dateEnd.equals("")) {
            sql += "\t\t\tAND  LOGIN_TIME >= timestamp '" + dateStart + " 00:00:00'\n";
            sql += "\t\t\tAND  LOGIN_TIME <= timestamp '" + dateEnd + " 23:59:59'\n";

        } else if (!dateMinusDay.equals("")) {
            sql += "\t\tAND  LOGIN_TIME >= CURRENT_DATE - INTERVAL '" + dateMinusDay + "' DAY\n";
        }

        sql += "\t\tAND PLATFORM_ID = (SELECT id FROM PUB_SITE ps WHERE site_path = '" + sitepath + "')\n" +
                ")";

        return sql.replaceAll("\n", "<br>").replaceAll("\t", "&nbsp;");
    }
}
