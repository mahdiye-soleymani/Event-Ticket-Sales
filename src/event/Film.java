package event;

import exceptions.LogTextFormatter;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.FileHandler;
import java.util.logging.Logger;

public class Film {
    private String EventName;
    private String EventDate;
    private String eventTime;
    private String Eventlocation;
    private static int remainingCapacity;//ظرفیت باقی مانده
    private static int Eventcapacity;//ظرفیت کل
    private static ArrayList<Double> Eventcost; //قیمت ها
    // private int EventNumber;
    private static HashMap<Integer, Integer> discounts;//کد های تخفیف
    public static Logger myLogger;

    public static int getEventcapacity() {
        return Eventcapacity;
    }

    public static ArrayList<Double> getEventcost() {
        return Eventcost;
    }

    public static HashMap<Integer, Integer> getDiscounts() {
        return discounts;
    }

    FileWriter myWriter = new FileWriter("C:\\Users\\emiteess\\IdeaProjects\\EventTicketSales\\src\\FilmEvent.txt");
    FileWriter myWriterShow = new FileWriter("C:\\Users\\emiteess\\IdeaProjects\\EventTicketSales\\src\\FilmShowEvent.txt");

    //لاگ ثبت رویداد
    static {
        myLogger = Logger.getAnonymousLogger();
        String path = "C:\\Users\\emiteess\\IdeaProjects\\EventTicketSales\\src\\EventReport.log";
        FileHandler handeler = null;
        try {
            handeler = new FileHandler(path, 1024 * 1024 * 5, 20, true);

        } catch (IOException e) {
            e.printStackTrace();
        }

        handeler.setFormatter(new LogTextFormatter());
        myLogger.addHandler(handeler);

    }


    public Film(String EventName, String EventDate, String eventTime, String Eventlocation, int Eventcapacity, ArrayList<Double> eventcosts, HashMap<Integer, Integer> discounts) throws IOException {
        this.EventName = EventName;
        this.EventDate = EventDate;
        this.eventTime = eventTime;
        this.Eventlocation = Eventlocation;
        this.Eventcapacity = Eventcapacity;
        this.remainingCapacity = Eventcapacity;
        this.Eventcost = eventcosts;
        // this.EventNumber = EventNumber;
        this.discounts = discounts;
        //InitialFilling();
        // myWriter.append("EventNumbe:"+new Integer(EventNumber).toString() + "\t");
        //myWriter.append(",");
        //ذخیره رویداد در فایل اصلی
       // myWriter.append("event name:").append(EventDate).append()
        myWriter.append("Event Name:" + EventName + "\t");
        myWriter.append(",");
        myWriter.append("Event Date:" + EventDate + "\t");
        myWriter.append(",");
        myWriter.append("Event Time: " + eventTime + "\t");
        myWriter.append(",");
        myWriter.append("Event location:" + Eventlocation + "\t");
        myWriter.append(",");
        myWriter.append("Event capacity:" + new Integer(Eventcapacity).toString() + "\t");
        myWriter.append(",");
        myWriter.append("Remaining Capacity: " + new Integer(remainingCapacity).toString() + "\t");
//        myWriterShow.append(",");////////////
        int costnum=1;
        for (Double cost : eventcosts//ذخیره قیمت ها در فایل
        ) {
            myWriter.append("costs"+costnum+" :" + cost + System.lineSeparator());
            costnum++;
        }
        myWriter.append(",");
        //ذخیره کد های تخفیف
        myWriter.write("Discount code and its percentage:" + String.valueOf(discounts) + "%");
        myWriter.append("\n");
        myWriter.close();

        //---------------------
        // myWriterShow.append("EventNumbe:"+new Integer(EventNumber).toString() + "\t");
        // myWriterShow.append(",");
        //ذخیره ذر فایلی که به کاربر نمایش داده میشود
        myWriterShow.append("Event Name: " + EventName + "\t");
        myWriterShow.append(",");
        myWriterShow.append("Event Date: " + EventDate + "\t");
        myWriterShow.append(",");
        myWriterShow.append("Event Time: " + eventTime + "\t");
        myWriterShow.append(",");
        myWriterShow.append("Event location: " + Eventlocation + "\t");
        myWriterShow.append(",");
        myWriterShow.append("Remaining Capacity: " + new Integer(remainingCapacity).toString() + "\t");
        myWriterShow.append("\n");
        int costnum2=1;
        for (Double cost : eventcosts
        ) {
            myWriterShow.append("costs"+costnum2+": "+ + cost + System.lineSeparator());
            costnum2++;
        }
        myWriterShow.append("\n");
        myWriterShow.close();
        myLogger.info("Event saved.");

    }

//    public Film() throws IOException {
//
//    }

    public static void CapacityAccount(int num) {//حساب کردن ظرفیت بعد از خرید
        remainingCapacity = remainingCapacity - num;
    }

    public static boolean checkDiscount(int num) {//چک کردن کد های تخفیف
        return discounts.containsKey(num);
    }
}
