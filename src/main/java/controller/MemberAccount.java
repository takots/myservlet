package controller;

import java.util.Map;

public class MemberAccount {
    public String freeze(Map<String, Object> map) {
        String cb = (String) map.get("cb");
        String sitepath = (String) map.get("sitepath");
        String superior = (String) map.get("superior");
        String newSuperior = "";
        boolean only = false;
        if (superior.lastIndexOf("、") >= 0) {
            for (String s : superior.split("、")) {
                newSuperior += "'" + s.trim() + "'" + ",";
            }
            newSuperior = newSuperior.substring(0, newSuperior.lastIndexOf(","));
        } else {
            only = true;
            newSuperior = superior;
        }

        // sq1
        String sql = "幫查 " + cb + "\n" +
                "```sql\n" +
                "-- sql1 id = PLATFORM_ID\n" +
                "SELECT id FROM PUB_SITE ps WHERE SITE_PATH = '" + sitepath + "'\n" +
                "\n\n" +
                "SELECT * FROM AUTH_ACCOUNT_EDIT_LOG l\n" +
                "WHERE 1=1\n" +
                "\tAND l.ACCOUNT_ID = (\n" +
                "\t\tSELECT ID FROM AUTH_ACCOUNT \n" +
                "\t\tWHERE PLATFORM_ID = (SELECT id FROM PUB_SITE ps WHERE SITE_PATH = '" + sitepath + "') \n";

        if (only) {
            sql += "\t\tAND LOGIN_NAME = '" + superior + "'\n";
        } else {
            sql += "AND LOGIN_NAME IN (" + newSuperior + ")\n";
        }
        sql += "\t)\n" +
                "ORDER BY CREATE_DATE DESC\n" +
                ";\n";

        // sq2
        sql += "-- sql2 id = ACCOUNT_ID\n" +
                "select id ,LOGIN_NAME ,CREATE_TIME ,FREEZE_STATUS ,UPDATE_TIME \n" +
                "from AUTH_ACCOUNT where 1=1";
        if (only) {
            sql += " and LOGIN_NAME='" + superior + "' \n";
        } else {
            sql += "AND LOGIN_NAME IN (" + newSuperior + ")\n";
        }

        sql +=  "and PLATFORM_ID=(sql1的id);\n\n" +
                // sq3
                "select * from AUTH_ACCOUNT_LOCK_DATE where ACCOUNT_ID=(sql2的id);\n\n" +
                // sq4
                "select * from AUTH_LOCK_RECORD where ACCOUNT_ID=(sql2的id);";


        return sql.replaceAll("\n", "<br>").replaceAll("\t", "&nbsp;");
    }
}
