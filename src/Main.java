import java.util.ArrayList;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) {

        String sql =  "   SELECT *  FROM db.test;  " +
                "   SELECT 'test;test'  FROM db.test " ;

        String[] sqldata = sql.trim().split("[ ]{1,10}");

        ArrayList data = new ArrayList(Arrays.asList(sqldata));

        ArrayList<StringBuilder> modifiedSql = new ArrayList<StringBuilder>();
        int i = 0;
        String previous = new String();
        for (Object str: data) {
            String d_ex = (String) str;
            if ( d_ex.matches("select|SELECT") ) {
                if( previous.matches(".*;") && i != 0){
                    System.out.println(previous);
                    i++;
                    modifiedSql.add(i, modifiedSql.get(i).append(" "+d_ex) );
                }
                if( i == 0) {
                    modifiedSql.add(i,new StringBuilder(" "+d_ex));
                }else modifiedSql.set(i,modifiedSql.get(i).append(" "+d_ex));
            } else modifiedSql.set(i,modifiedSql.get(i).append(" "+d_ex));
            previous = d_ex;
        }

        modifiedSql.stream().forEach((d)-> System.out.println(d));
    }
}
