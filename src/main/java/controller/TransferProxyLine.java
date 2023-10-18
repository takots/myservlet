package controller;

import java.util.Map;

/**
 * 转移代理线
 * station 站
 * Superior 上級
 * Subordinate 下級
 */
public class TransferProxyLine {

    public String select1(Map<String, Object> map) {
        String cb = (String) map.get("cb");
        String station = (String) map.get("station");
        String superior = (String) map.get("superior");
        String subordinate = (String) map.get("subordinate");

//        String sql = "--1\n" +
//                "SELECT\n" +
//                "CASE LOGIN_NAME\n" +
//                "WHEN '"+subordinate+"' THEN 1 --上級帳號\n" +
//                "ELSE 2\n" +
//                "END AS sort,\n" +
//                "a.PLATFORM_ID, a.ID, a.LOGIN_NAME, a.ACCOUNT_NAME_PATH, a.ACCOUNT_ID_PATH ,s.SITE_PATH\n" +
//                "FROM AUTH_ACCOUNT a\n" +
//                "LEFT JOIN PUB_SITE s ON a.PLATFORM_ID = s.ID\n" +
//                "WHERE s.SITE_PATH = '"+station+"' AND LOGIN_NAME IN (\n" +
//                "'" + superior + "','"+subordinate+"'\n" +
//                ")";

        return "幫 " + cb + "<br>"
                + "```sql<br>"
                + "--1<br>"
                + "SELECT<br>"
                + "     CASE LOGIN_NAME<br>"
                + "     WHEN '" + superior + "' THEN 1 -- 上級帳號<br>"
                + "     ELSE 2<br>"
                + "     END AS sort,<br>"
                + "     a.PLATFORM_ID, a.ID, a.LOGIN_NAME, a.ACCOUNT_NAME_PATH, a.ACCOUNT_ID_PATH ,s.SITE_PATH<br>"
                + "FROM AUTH_ACCOUNT a<br>"
                + "LEFT JOIN PUB_SITE s ON a.PLATFORM_ID = s.ID<br>"
                + "WHERE s.SITE_PATH = '" + station + "' AND LOGIN_NAME IN ('" + superior + "','" + subordinate + "');<br><br>";
    }

    public String select2(Map<String, Object> map) {
        String station = (String) map.get("station");
        String superior = (String) map.get("superior");
        String subordinate = (String) map.get("subordinate");

        return "--2<br>"
                + "WITH d AS (<br>"
                + "     SELECT ID, LOGIN_NAME, PLATFORM_ID FROM LG.AUTH_ACCOUNT<br>"
                + "     WHERE PLATFORM_ID = (SELECT ID FROM LG.PUB_SITE WHERE SITE_PATH = '" + station + "') AND LOGIN_NAME = '" + superior + "'-- 上级账号<br>"
                + ")<br>"
                + "SELECT<br>"
                + "     DECODE(a.\"TYPE\", 1, '福利彩票', 2, '体育彩票', 5, '六合彩', 9, '时时彩', 10, '快三', 11, '11选5', 12, '快乐彩', 13, '赛车', 14, 'QQ彩', 15, '棋牌', 16, '百家樂', 17, '对战') \"采种\"<br>"
                + "     , c.ACCOUNT_NAME_PATH \"层级\", c.LOGIN_NAME \"用户帐号\", a.REBATE \"返点\", a.LOWER_REBATE \"下级返点上限\", d.LOGIN_NAME \"上级帐号\", b.REBATE \"上级返点\", b.LOWER_REBATE \"上级的下级返点上限\"<br>"
                + "FROM LG.AUTH_AGENT_REBATE a<br>"
                + "LEFT JOIN d ON d.PLATFORM_ID = a.SITE_ID<br>"
                + "LEFT JOIN LG.AUTH_AGENT_REBATE b ON a.SITE_ID = b.SITE_ID AND a.\"TYPE\" = b.\"TYPE\" AND b.ACCOUNT_ID = d.ID -- 上级账号ID<br>"
                + "LEFT JOIN LG.AUTH_ACCOUNT c ON a.ACCOUNT_ID = c.ID<br>"
                + "WHERE 1=1<br>"
                + "AND a.SITE_ID = d.PLATFORM_ID<br>"
                + "AND a.AGENT_PATH IN (<br>"
                + "     SELECT DISTINCT ACCOUNT_ID_PATH<br>"
                + "     FROM LG.AUTH_ACCOUNT <br>"
                + "     WHERE PLATFORM_ID = (SELECT ID FROM LG.PUB_SITE WHERE SITE_PATH = '" + station + "')<br>"
                + "     CONNECT BY PARENT_ID = PRIOR ID START WITH LOGIN_NAME IN ('" + subordinate + "')<br>"
                + ") -- 直属下级账号<br>"
                + "AND (a.REBATE > b.REBATE OR a.REBATE > NVL(NVL(b.LOWER_REBATE, b.REBATE), 9))<br>"
                + "ORDER BY c.ACCOUNT_NAME_PATH, a.\"TYPE\";<br>";
    }

    /**
     * station 站
     * Superior 上級
     * Subordinate 下級
     */
    public String updateProxyData(Map<String, Object> map) {
        String cb = (String) map.get("cb");
        String accountNamePath = (String) map.get("accountNamePath");
        String accountIdPath = (String) map.get("accountIdPath");
        String superior = (String) map.get("superior");
        String superiorId = (String) map.get("superiorId");
        String subordinate = (String) map.get("subordinate");
        String subordinateId = (String) map.get("subordinateId");
        String platformId = (String) map.get("platformId");
        String newAccountNamePath = accountNamePath.replaceFirst(".*/" + subordinate + "/", superior + "/" + subordinate + "/");
        String newAccountIdPath = accountIdPath.replaceFirst(".*/" + subordinateId + "/", superiorId + "/" + subordinateId + "/");
        StringBuffer stringBuffer = new StringBuffer();
        /**
         * 更新代理資料 (代理線第一個帳號另外處理)
         * ex: ACCOUNT_NAME_PATH查出來是 xxx/yyy/a01/  ,上級為 bbb 則改成 bbb/a01
         */
        stringBuffer.append("幫 " + cb + "<br>"
                + "```sql<br>"
                + "--更新代理資料 (代理線第一個帳號另外處理)<br>"
                + "UPDATE LG.AUTH_ACCOUNT a SET<br>"
                + "a.ACCOUNT_NAME_PATH =  REPLACE(a.ACCOUNT_NAME_PATH,'" + accountNamePath + "',<br>"
                + "'" + newAccountNamePath + "'),<br>"
                + "a.ACCOUNT_ID_PATH  =  REPLACE(a.ACCOUNT_ID_PATH, '" + accountIdPath + "',<br>"
                + "'" + newAccountIdPath + "')<br>"
                + "WHERE (a.ACCOUNT_NAME_PATH LIKE '%/" + subordinate + "/%' )<br>"
                + "AND a.PLATFORM_ID  = " + platformId + ";<br><br>"
        );

        /**
         * 更新上級資料
         */
        stringBuffer.append(
                "--更新上級資料<br>"
                        + "UPDATE LG.AUTH_ACCOUNT a<br>"
                        + "SET a.PARENT_ID =" + superiorId + " , a.PARENT_NAME = '" + superior + "'<br>"
                        + "WHERE a.PLATFORM_ID = " + platformId + " AND a.LOGIN_NAME IN ('" + subordinate + "');<br>"

                        + "UPDATE LG.AUTH_AGENT_REBATE SET PARENT_ID = " + superiorId + " WHERE ACCOUNT_ID =" + subordinateId + ";<br><br>"
        );

        /**
         * 更新推廣鏈接
         */
        stringBuffer.append(
                "--更新推廣鏈接<br>"
                        + "UPDATE LG.AUTH_AGENT_PROMOTION a<br>"
                        + "SET a.AGENT_PATH = REPLACE(a.AGENT_PATH, '" + accountIdPath + "' , '" + newAccountIdPath + "')<br>"
                        + "WHERE a.AGENT_PATH LIKE '%" + accountIdPath + "%'<br>"
                        + "AND a.PLATFORM_ID = " + platformId + ";<br><br>"
        );

        /**
         * 更新返點
         */
        stringBuffer.append(
                "--更新返點<br>"
                        + "UPDATE LG.AUTH_AGENT_REBATE a<br>"
                        + "SET a.AGENT_PATH = REPLACE(a.AGENT_PATH, '" + accountIdPath + "' , '" + newAccountIdPath + "')<br>"
                        + "WHERE a.AGENT_PATH LIKE '%" + accountIdPath + "%'<br>"
                        + "AND a.SITE_ID = " + platformId + ";<br><br>"
        );

        /**
         * 更新層級
         */
        stringBuffer.append(
                "--更新層級<br>"
                        + "UPDATE LG.AUTH_ACCOUNT a SET a.\"LEVEL\" = regexp_count (a.ACCOUNT_name_PATH , '/') -1<br>"
                        + "WHERE (a.ACCOUNT_NAME_PATH LIKE '%/" + subordinate + "%' )<br>"
                        + "AND a.PLATFORM_ID = " + platformId + ";<br><br>"
        );

        /**
         * 更新代理統計
         */
        stringBuffer.append(
                "--更新代理統計<br>"
                        + "UPDATE LG.BET_ACCOUNT_PER_DAY a<br>"
                        + "SET a.ACCOUNT_ID_PATH = REPLACE(a.ACCOUNT_ID_PATH, '" + accountIdPath + "' , '" + newAccountIdPath + "')<br>"
                        + "WHERE a.ACCOUNT_ID_PATH LIKE '%" + accountIdPath + "%'<br>"
                        + "AND a.SITE_ID = " + platformId + ";<br><br>"
        );

        /**
         * 更新中繼表
         */
        stringBuffer.append("--更新中繼表<br>");
        String[] tableName = {"RELAY_ACTIVITY", "RELAY_BET", "RELAY_BET_NOCLEAR", "RELAY_EXTBET", "RELAY_OTHER", "RELAY_REBATE", "RELAY_RECHARGE", "RELAY_TNBET", "RELAY_YUEBAO"};
        for (String s : tableName) {
            stringBuffer.append(
                    "UPDATE LG." + s + " a<br>"
                            + "SET a.ACCOUNT_PATH = REPLACE(a.ACCOUNT_PATH, '" + accountIdPath + "' , '" + newAccountIdPath + "')<br>"
                            + "WHERE a.ACCOUNT_PATH LIKE '%" + accountIdPath + "%'<br>"
                            + "AND a.SITE_ID = " + platformId + ";<br>"
            );
        }

        stringBuffer.append(
                "UPDATE LG.RELAY_BET_BATTLE a<br>"
                        + "SET a.ACCOUNT_ID_PATH = REPLACE(a.ACCOUNT_ID_PATH, '" + accountIdPath + "' , '" + newAccountIdPath + "')<br>"
                        + "WHERE a.ACCOUNT_ID_PATH LIKE '%" + accountIdPath + "%'<br>"
                        + "AND a.SITE_ID = " + platformId + ";<br><br>"

        );
        return stringBuffer.toString();
    }

    public String updateProxyData(String station) {
        return "--更新<br>"
                + "BEGIN<br>"
                + "    FOR i IN (<br>"
                + "        WITH c AS (<br>"
                + "            SELECT<br>"
                + "                a.PLATFORM_ID, a.ID, a.LOGIN_NAME, a.\"LEVEL\", b.NEW_LEVEL, a.ACCOUNT_ID_PATH, a.ACCOUNT_NAME_PATH, a.PARENT_ID, a.PARENT_NAME,<br>"
                + "                CASE b.NEW_LEVEL<br>"
                + "                   WHEN 0 THEN to_number(NULL)<br>"
                + "                   WHEN 1 THEN to_number(SUBSTR(a.ACCOUNT_ID_PATH, 1, INSTR(ACCOUNT_ID_PATH, '/', 1, b.NEW_LEVEL) -1))<br>"
                + "                   ELSE to_number(SUBSTR(a.ACCOUNT_ID_PATH, INSTR(ACCOUNT_ID_PATH, '/', 1, b.NEW_LEVEL-1) +1, INSTR(ACCOUNT_ID_PATH, '/', 1, b.NEW_LEVEL) - INSTR(ACCOUNT_ID_PATH, '/', 1, b.NEW_LEVEL-1) -1))<br>"
                + "               END AS NEW_PARENT_ID,<br>"
                + "               CASE b.NEW_LEVEL<br>"
                + "                   WHEN 0 THEN '厅主'<br>"
                + "                   WHEN 1 THEN SUBSTR(a.ACCOUNT_NAME_PATH, 1, INSTR(ACCOUNT_NAME_PATH, '/', 1, b.NEW_LEVEL) -1)<br>"
                + "                   ELSE SUBSTR(a.ACCOUNT_NAME_PATH, INSTR(ACCOUNT_NAME_PATH, '/', 1, b.NEW_LEVEL-1) +1, INSTR(ACCOUNT_NAME_PATH, '/', 1, b.NEW_LEVEL) - INSTR(ACCOUNT_NAME_PATH, '/', 1, b.NEW_LEVEL-1) -1)<br>"
                + "               END AS NEW_PARENT_NAME<br>"
                + "           FROM LG.AUTH_ACCOUNT a<br>"
                + "           LEFT JOIN (<br>"
                + "               SELECT ID, REGEXP_COUNT(ACCOUNT_NAME_PATH , '/') -1 AS NEW_LEVEL FROM LG.AUTH_ACCOUNT<br>"
                + "               WHERE 1=1 AND PLATFORM_ID = (SELECT ID FROM LG.PUB_SITE WHERE SITE_PATH = '" + station + "')<br>"
                + "           ) b ON b.ID = a.ID<br>"
                + "           WHERE 1=1 AND PLATFORM_ID = (SELECT ID FROM LG.PUB_SITE WHERE SITE_PATH = '" + station + "')<br>"
                + "        )<br>"
                + "       SELECT a.PLATFORM_ID, a.ID, a.LOGIN_NAME, b.NEW_LEVEL, b.NEW_SUBORDINATES, c.NEW_PARENT_ID, c.NEW_PARENT_NAME<br>"
                + "       FROM LG.AUTH_ACCOUNT a<br>"
                + "       LEFT JOIN(<br>"
                + "           SELECT a.ID, a.LOGIN_NAME, c.NEW_LEVEL, count(1) -1 AS NEW_SUBORDINATES FROM c<br>"
                + "           LEFT JOIN LG.AUTH_ACCOUNT a ON a.ID = c.ID<br>"
                + "           CONNECT BY c.ID = PRIOR c.NEW_PARENT_ID START WITH c.ID = a.ID --特殊处理，与一般上下级关联不同<br>"
                + "           GROUP BY a.ID, a.LOGIN_NAME, c.NEW_LEVEL<br>"
                + "       ) b ON b.ID = a.ID <br>"
                + "       LEFT JOIN c ON c.ID = a.ID <br>"
                + "       WHERE 1=1<br>"
                + "           AND a.PLATFORM_ID = (SELECT ID FROM LG.PUB_SITE WHERE SITE_PATH = '" + station + "')<br>"
                + "           AND (a.\"LEVEL\" != b.NEW_LEVEL OR a.SUBORDINATES != b.NEW_SUBORDINATES OR a.PARENT_ID != c.NEW_PARENT_ID OR a.PARENT_NAME != c.NEW_PARENT_NAME)<br>"
                + "   )<br>"
                + "   LOOP<br>"
                + "      	UPDATE LG.AUTH_ACCOUNT d SET \"LEVEL\" = i.NEW_LEVEL, SUBORDINATES = i.NEW_SUBORDINATES, PARENT_ID = i.NEW_PARENT_ID, PARENT_NAME = i.NEW_PARENT_NAME<br>"
                + "       WHERE d.PLATFORM_ID = i.PLATFORM_ID AND d.ID = i.ID;<br>"
                + "   END LOOP;<br>"
                + "END;<br>";
    }

    /**
     * 主库檢查1
     * station 站
     * Superior 上級
     * Subordinate 下級
     */
    public String masterDatabaseCheck(Map<String, Object> map) {
        String cb = (String) map.get("cb");
        String station = (String) map.get("station");
        String superior = (String) map.get("superior");
        String subordinate = (String) map.get("subordinate");

        return "幫 " + cb + "<br>"
                + "```sql<br>"
                + "--主库檢查1<br>"
                + "WITH c AS (<br>"
                + "     SELECT ID, LOGIN_NAME, ACCOUNT_ID_PATH, ACCOUNT_NAME_PATH, PLATFORM_ID FROM LG.AUTH_ACCOUNT<br>"
                + "     WHERE PLATFORM_ID = (SELECT ID FROM LG.PUB_SITE WHERE SITE_PATH = '" + station + "') AND LOGIN_NAME = '" + superior + "'<br>" //上级账号
                + ")<br>"
                + "SELECT a.* FROM LG.AUTH_ACCOUNT a<br>"
                + "LEFT JOIN c ON c.PLATFORM_ID = a.PLATFORM_ID<br>"
                + "WHERE a.PLATFORM_ID = c.PLATFORM_ID<br>"
                + "AND (<br>"
                + "a.ACCOUNT_NAME_PATH NOT LIKE c.ACCOUNT_NAME_PATH ||'%' OR a.ACCOUNT_ID_PATH NOT LIKE c.ACCOUNT_ID_PATH || '%'<br>"
                + "     OR (<br>"
                + "         (a.PARENT_ID != c.ID OR a.PARENT_NAME != c.LOGIN_NAME) AND a.LOGIN_NAME IN ('" + subordinate + "')<br>" //直属下级账号
                + "		)<br>"
                + "	)<br>"
                + "CONNECT BY a.PARENT_ID = PRIOR a.ID<br>"
                + "START WITH a.LOGIN_NAME IN ('" + subordinate + "');<br><br>";
    }

    /**
     * 主库檢查2
     * station 站
     */
    public String masterDatabaseCheck(String station) {
        return "--主库檢查2<br>"
                + "WITH c AS (<br>"
                + " SELECT <br>"
                + "     a.PLATFORM_ID, a.ID, a.LOGIN_NAME, a.\"LEVEL\", b.NEW_LEVEL, a.ACCOUNT_ID_PATH, a.ACCOUNT_NAME_PATH, a.PARENT_ID, a.PARENT_NAME,<br>"
                + "     CASE b.NEW_LEVEL<br>"
                + "         WHEN 0 THEN to_number(NULL)<br>"
                + "	        WHEN 1 THEN to_number(SUBSTR(a.ACCOUNT_ID_PATH, 1, INSTR(ACCOUNT_ID_PATH, '/', 1, b.NEW_LEVEL) -1))<br>"
                + "         ELSE to_number(SUBSTR(a.ACCOUNT_ID_PATH, INSTR(ACCOUNT_ID_PATH, '/', 1, b.NEW_LEVEL-1) +1, INSTR(ACCOUNT_ID_PATH, '/', 1, b.NEW_LEVEL) - INSTR(ACCOUNT_ID_PATH, '/', 1, b.NEW_LEVEL-1) -1))<br>"
                + "     END AS NEW_PARENT_ID,<br>"
                + "     CASE b.NEW_LEVEL<br>"
                + "         WHEN 0 THEN '厅主'<br>"
                + "         WHEN 1 THEN SUBSTR(a.ACCOUNT_NAME_PATH, 1, INSTR(ACCOUNT_NAME_PATH, '/', 1, b.NEW_LEVEL) -1)<br>"
                + "         ELSE SUBSTR(a.ACCOUNT_NAME_PATH, INSTR(ACCOUNT_NAME_PATH, '/', 1, b.NEW_LEVEL-1) +1, INSTR(ACCOUNT_NAME_PATH, '/', 1, b.NEW_LEVEL) - INSTR(ACCOUNT_NAME_PATH, '/', 1, b.NEW_LEVEL-1) -1)<br>"
                + "     END AS NEW_PARENT_NAME<br>"
                + " FROM LG.AUTH_ACCOUNT a<br>"
                + " LEFT JOIN (<br>"
                + "     SELECT ID, REGEXP_COUNT(ACCOUNT_NAME_PATH , '/') -1 AS NEW_LEVEL FROM LG.AUTH_ACCOUNT <br>"
                + "     WHERE 1=1 AND PLATFORM_ID = (SELECT ID FROM LG.PUB_SITE WHERE SITE_PATH = '" + station + "')<br>"
                + " ) b ON b.ID = a.ID<br>"
                + " WHERE 1=1 AND PLATFORM_ID = (SELECT ID FROM LG.PUB_SITE WHERE SITE_PATH = '" + station + "')<br>"
                + ")<br>"
                + "SELECT a.ID, a.LOGIN_NAME, a.\"LEVEL\", c.NEW_LEVEL, a.SUBORDINATES, b.NEW_SUBORDINATES, a.ACCOUNT_ID_PATH, a.ACCOUNT_NAME_PATH<br>"
                + "	, a.PARENT_ID, c.NEW_PARENT_ID, a.PARENT_NAME, c.NEW_PARENT_NAME<br>"
                + "	, CASE <br>"
                + "   WHEN a.\"LEVEL\" != b.NEW_LEVEL THEN<br>"
                + "         CASE <br>"
                + "             WHEN a.SUBORDINATES != b.NEW_SUBORDINATES THEN<br>"
                + "                 CASE<br>"
                + "                     WHEN a.PARENT_ID != c.NEW_PARENT_ID THEN<br>"
                + "                         CASE <br>"
                + "                             WHEN a.PARENT_NAME != c.NEW_PARENT_NAME THEN '层级错误 且 下级人数错误 且 上级ID错误 且 上级账号错误'<br>"
                + "                             ELSE '层级错误 且 下级人数错误 且 上级ID错误'<br>"
                + "                         END<br>"
                + "                     ELSE -- 上级ID正确<br>"
                + "                         CASE <br>"
                + "                             WHEN a.PARENT_NAME != c.NEW_PARENT_NAME THEN '层级错误 且 下级人数错误 且 上级账号错误'<br>"
                + "                             ELSE '层级错误 且 下级人数错误'<br>"
                + "                         END<br>"
                + "                      END<br>"
                + "                  ELSE -- 下级人数正确<br>"
                + "                      CASE<br>"
                + "                         WHEN a.PARENT_ID != c.NEW_PARENT_ID THEN<br>"
                + "                         　　CASE <br>"
                + "                                 WHEN a.PARENT_NAME != c.NEW_PARENT_NAME THEN '层级错误 且 上级ID错误 且 上级账号错误'<br>"
                + "                                 ELSE '层级错误 且 上级ID错误'<br>"
                + "                             END<br>"
                + "                         ELSE --上级ID正确<br>"
                + "                             CASE <br>"
                + "                                 WHEN a.PARENT_NAME != c.NEW_PARENT_NAME THEN '层级错误 且 上级账号错误'<br>"
                + "                                 ELSE '层级错误'<br>"
                + "                             END<br>"
                + "                            END<br>"
                + "			          END<br>"
                + "		        ELSE -- 层级正确<br>"
                + "			        CASE <br>"
                + "                     WHEN a.SUBORDINATES != b.NEW_SUBORDINATES THEN<br>"
                + "                         CASE<br>"
                + "                              WHEN a.PARENT_ID != c.NEW_PARENT_ID THEN<br>"
                + "                                 CASE <br>"
                + "                                     WHEN a.PARENT_NAME != c.NEW_PARENT_NAME THEN '下级人数错误 且 上级ID错误 且 上级账号错误'<br>"
                + "                                     ELSE '下级人数错误 且 上级ID错误'<br>"
                + "                                 END<br>"
                + "                              ELSE -- 上级ID正确<br>"
                + "                                 CASE <br>"
                + "                                     WHEN a.PARENT_NAME != c.NEW_PARENT_NAME THEN '下级人数错误 且 上级账号错误'<br>"
                + "                                     ELSE '下级人数错误'<br>"
                + "                                 END<br>"
                + "                            END<br>"
                + "                         ELSE -- 下级人数正确<br>"
                + "                            CASE<br>"
                + "                               WHEN a.PARENT_ID != c.NEW_PARENT_ID THEN<br>"
                + "                                 CASE <br>"
                + "                                      WHEN a.PARENT_NAME != c.NEW_PARENT_NAME THEN '上级ID错误 且 上级账号错误'<br>"
                + "                                     　ELSE '上级ID错误'<br>"
                + "                                 END<br>"
                + "                             ELSE -- 上级ID正确<br>"
                + "                                 CASE <br>"
                + "                                     WHEN a.PARENT_NAME != c.NEW_PARENT_NAME THEN '上级账号错误'<br>"
                + "                                     ELSE '都正确，不应该出现'<br>"
                + "                                 END<br>"
                + "             	            END<br>"
                + "                         END<br>"
                + "             END AS \"错误原因\"<br>"
                + "FROM LG.AUTH_ACCOUNT a <br>"
                + "LEFT JOIN (<br>"
                + "     SELECT a.ID, a.LOGIN_NAME, c.NEW_LEVEL, count(1) -1 AS NEW_SUBORDINATES FROM c<br>"
                + "     LEFT JOIN LG.AUTH_ACCOUNT a ON a.ID = c.ID<br>"
                + "     CONNECT BY c.ID = PRIOR c.NEW_PARENT_ID START WITH c.ID = a.ID -- 特殊处理，与一般上下级关联不同<br>"
                + "     GROUP BY a.ID, a.LOGIN_NAME, c.NEW_LEVEL<br>"
                + ") b ON b.ID = a.ID <br>"
                + "LEFT JOIN c ON c.ID = a.ID<br>"
                + "WHERE 1=1<br>"
                + "	AND a.PLATFORM_ID = (SELECT ID FROM LG.PUB_SITE WHERE SITE_PATH = '" + station + "')<br>"
                + "	AND (a.\"LEVEL\" != b.NEW_LEVEL OR a.SUBORDINATES != b.NEW_SUBORDINATES OR a.PARENT_ID != c.NEW_PARENT_ID OR a.PARENT_NAME != c.NEW_PARENT_NAME)<br>"
                + ";<br><br>";
    }

    /**
     * 主库檢查3
     */
    public String masterDatabaseCheck() {
        return "--主库檢查3<br>"
                + "SELECT ACCOUNT_ID FROM (<br>"
                + "  SELECT count(1) AS \"denum\" , ACCOUNT_ID FROM (<br>"
                + "     SELECT count(1) , r.ACCOUNT_ID ,r.ACCOUNT_PATH<br>"
                + "     FROM LG.BET_GPCP_CLEARING_RECORD_TEMP r<br>"
                + "     WHERE r.clearing_date > trunc(sysdate)<br>"
                + "     GROUP BY r.ACCOUNT_ID,r.ACCOUNT_PATH<br>"
                + " ) GROUP BY ACCOUNT_ID<br>"
                + ") dd WHERE dd.\"denum\" > 1;<br>";
    }
}
