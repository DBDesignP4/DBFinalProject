import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class tester {


    public static void main(String[] args) {

        // Date date = new Date(); 

        // Calendar cal = Calendar.getInstance();
        // cal.setTime(date);

        // System.out.printf("%s\n", cal.getTime());

        // cal.add(Calendar.DATE, 120);
        // date = cal.getTime();

        // System.out.printf("+ 120 days = %s", date);



        // Date date = new Date();
        // DateTime dt = new DateTime(date);
        // dt = dt.plusDays(120);
        // date = dt.toDate();


        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();

        c.setTime(new Date()); // Using today's date

        String today = sdf.format(c.getTime());
        System.out.println(today);

        c.add(Calendar.DATE, 120); // Adding 120 days

        String future = sdf.format(c.getTime());
        System.out.println(future);
        
        Queries q = new Queries();
        
        System.out.println(q.query1("01/05/2021"));

        
        
        System.out.println(q.query3("05/2021"));
        System.out.println(q.query4("1"));


    }
    
}
