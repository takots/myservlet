public class main123 {
    public static void main(String[] args) {
        main123 m = new main123();
        m.test1();
    }
    public void test1(){
        String paramString = "int page, int pageSize,\n" +
                "\t\t\tString createDatTime, String createDatTimeEnd, Integer accountId,String loteryId,String cpLoteryId\n" +
                "\t\t\t,String status,boolean isNew,Integer lastId,String tradeType";
        StringBuffer stringBuffer = new StringBuffer();
        StringBuffer stringBuffer2 = new StringBuffer();
        stringBuffer.append("Map<String, Object> map = new HashMap<>();<br>");
        String [] arr = paramString.split(",");
        String type = "",parameter="";
        for(String s : arr){
            // 去頭尾空白
            type = s.trim().split(" ")[0];
            parameter = s.trim().split(" ")[1];
            stringBuffer.append("map.put(\""+parameter+"\" ,"+parameter+");<br>");
            stringBuffer2.append(type +" "+ parameter + " = ("+type+") map.get(\""+parameter+"\"));<br>");
        }
        System.out.println(stringBuffer.toString());
        System.out.println(stringBuffer2.toString());
    }
}
