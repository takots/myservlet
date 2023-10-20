package cb;

import java.util.Map;

public class PlatformAccount {
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

        // sql1
        String sql = "幫查 " + cb + "\n" +
                "```sql\n" +
                "select login_name, error_times, is_freeze ,LAST_LOGIN_TIME \n" +
                "from auth_user au\n" +
                "join PUB_SITE ps on ps.ID = au.COMPANY_ID\n" +
                "where ps.SITE_PATH = '" + sitepath + "'\n";
        if (only) {
            sql += "AND LOGIN_NAME = " + newSuperior + "\n\n";
        } else {
            sql += "AND LOGIN_NAME IN (" + newSuperior + ")\n\n";
        }

        // sql2
        sql += "-- 如果上面无资料可能是TS站点给错\n" +
                "-- 站点查询\n" +
                "select id ,SITE_PATH from PUB_SITE where id in(\n" +
                "\t\tselect COMPANY_ID from AUTH_USER\n" +
                "WHERE 1=1\n";
        if (only) {
            sql += "AND LOGIN_NAME = " + newSuperior + "\n\n";
        } else {
            sql += "AND LOGIN_NAME IN (" + newSuperior + ")\n\n";
        }
        sql += ");\n\n";

        // sql3
        sql += "-- 直接查询已冻结帐户\n" +
                "select login_name, error_times, is_freeze ,LAST_LOGIN_TIME \n" +
                "from auth_user au\n" +
                "WHERE IS_FREEZE = 2";

        return sql.replaceAll("\n", "<br>").replaceAll("\t", "&nbsp;");
    }
}
