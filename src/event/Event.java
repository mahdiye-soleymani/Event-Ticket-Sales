package event;

import exceptions.LogTextFormatter;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.FileHandler;
import java.util.logging.Logger;

public class Event {
    private String EventName;
    private String EventDate;
    private String eventTime;
    private String Eventlocation;
    private int Eventcapacity;
    private int remainingCapacity;
    private ArrayList<Double> Eventcost;
    private int EventNumber;


    private HashMap<Integer, Integer> discounts;

//    public HashMap<Integer, Integer> getDiscounts() {
//        return discounts;
//    }
//
//    public void setDiscounts(HashMap<Integer, Integer> discounts) {
//        this.discounts = discounts;
//    }

    public static Logger myLogger;
    FileWriter myWriter = new FileWriter("C:\\Users\\emiteess\\IdeaProjects\\EventTicketSales\\src\\event.Event.csv", true);
    FileWriter myWriterShow = new FileWriter("C:\\Users\\emiteess\\IdeaProjects\\EventTicketSales\\src\\ShowEvent.csv", true);


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


    public Event(int EventNumber, String EventName, String EventDate, String eventTime, String Eventlocation, int Eventcapacity, ArrayList<Double> eventcosts, HashMap<Integer, Integer> discounts) throws IOException {
        this.EventName = EventName;
        this.EventDate = EventDate;
        this.eventTime = eventTime;
        this.Eventlocation = Eventlocation;
        this.Eventcapacity = Eventcapacity;
        this.remainingCapacity = Eventcapacity;
        this.Eventcost = eventcosts;
        this.EventNumber = EventNumber;
        this.discounts = discounts;
        //InitialFilling();
        myWriter.append("EventNumbe:"+new Integer(EventNumber).toString() + "\t");
        myWriter.append(",");
        myWriter.append("EventName:"+EventName + "\t");
        myWriter.append(",");
        myWriter.append("EventDate:"+EventDate + "\t");
        myWriter.append(",");
        myWriter.append("eventTime="+eventTime + "\t");
        myWriter.append(",");
        myWriter.append("Eventlocation:"+Eventlocation + "\t");
        myWriter.append(",");
        myWriter.append("Eventcapacity:"+new Integer(Eventcapacity).toString() + "\t");
        myWriter.append(",");
        myWriter.append("remainingCapacity"+new Integer(remainingCapacity).toString() + "\t");
//        myWriterShow.append(",");
        for (Double cost : eventcosts
        ) {
            myWriter.append("costs: "+cost + System.lineSeparator());
        }
//        myWriter.append(",");
        myWriter.write("Discount code and its percentage:"+String.valueOf(discounts)+"%");

        myWriter.append("\n");
        myWriter.close();

        //---------------------
        myWriterShow.append("EventNumbe:"+new Integer(EventNumber).toString() + "\t");
        myWriterShow.append(",");
        myWriterShow.append("EventName:"+EventName + "\t");
        myWriterShow.append(",");
        myWriterShow.append("EventDate:"+EventDate + "\t");
        myWriterShow.append(",");
        myWriterShow.append("eventTime="+eventTime + "\t");
        myWriterShow.append(",");
        myWriterShow.append("Eventlocation:"+Eventlocation + "\t");
        myWriterShow.append(",");
        myWriterShow.append("remainingCapacity"+new Integer(remainingCapacity).toString() + "\t");
        myWriterShow.append(",");
        for (Double cost : eventcosts
        ) {
            myWriterShow.append("costs: "+cost + System.lineSeparator());
        }
        myWriterShow.append("\n");
        myWriterShow.close();
        myLogger.info("event.Event saved.");
        // System.out.println("\nEvent saved.");

    }

//    public Event() throws IOException {
//
//    }

//    public Event(int eventCode, double type) throws IOException {
//
//    }

    public void CapacityAccount(int num) {
        remainingCapacity=remainingCapacity-num;
    }

//    public static void InitialFilling() throws IOException {
//        FileWriter myWriter = new FileWriter("C:\\Users\\emiteess\\IdeaProjects\\EventTicketSales\\src\\event.Event.csv", true);
//        myWriter.append("EventNumber\t");
//        myWriter.append(",");
//        myWriter.append("EventName\t");
//        myWriter.append(",");
//        myWriter.append("EventDate\t");
//        myWriter.append(",");
//        myWriter.append("Eventlocation\t");
//        myWriter.append(",");
//        myWriter.append("Eventcapacity\t");
//        myWriter.append(",");
//        myWriter.append("Remaining Capacity\t");
//        myWriter.append(",");
//        myWriter.append("Eventcost\t");
//        myWriter.append("\n");
//    }
    //تابع برای چک کردن کد تخفیف و حساب کردنش
    //تابع برای کم کردن ظذفیت 
}
