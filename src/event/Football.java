package event;

import exceptions.LogTextFormatter;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.FileHandler;
import java.util.logging.Logger;

public class Football {
    private String EventName;
    private String EventDate;
    private String eventTime;
    private String Eventlocation;
    private static int Eventcapacity;
    private static int remainingCapacity;
    private static ArrayList<Double> Eventcost;
    private static HashMap<Integer, Integer> discounts;
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


    FileWriter myWriter = new FileWriter("C:\\Users\\emiteess\\IdeaProjects\\EventTicketSales\\src\\FootballEvent.txt");
    FileWriter myWriterShow = new FileWriter("C:\\Users\\emiteess\\IdeaProjects\\EventTicketSales\\src\\ShowFootballEvent.txt");

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
    public Football(String EventName, String EventDate, String eventTime, String Eventlocation, int Eventcapacity, ArrayList<Double> eventcosts, HashMap<Integer, Integer> discounts) throws IOException {
        this.EventName = EventName;
        this.EventDate = EventDate;
        this.eventTime = eventTime;
        this.Eventlocation = Eventlocation;
        this.Eventcapacity = Eventcapacity;
        this.remainingCapacity = Eventcapacity;
        this.Eventcost = eventcosts;
        this.discounts = discounts;
        myWriter.append("Event Name:" + EventName + "\t");
        myWriter.append(",");
        myWriter.append("Event Date:" + EventDate + "\t");
        myWriter.append(",");
        myWriter.append("Event Time: " + eventTime + "\t");
        myWriter.append(",");
        myWriter.append("Event location: " + Eventlocation + "\t");
        myWriter.append(",");
        myWriter.append("Event capacity: " + new Integer(Eventcapacity).toString() + "\t");
        myWriter.append(",");
        myWriter.append("Remaining Capacity: " + new Integer(remainingCapacity).toString() + "\t");
        int costnum = 1;
        for (Double cost : eventcosts
        ) {
            myWriter.append("costs: " + costnum + ": " + cost + System.lineSeparator());
            costnum++;
        }
        myWriter.write("Discount code and its percentage:" + String.valueOf(discounts) + "%");
        myWriter.append("\n");
        myWriter.close();
        myWriterShow.append("Event Name:" + EventName + "\t");
        myWriterShow.append(",");
        myWriterShow.append("Event Date:" + EventDate + "\t");
        myWriterShow.append(",");
        myWriterShow.append("Event Time: " + eventTime + "\t");
        myWriterShow.append(",");
        myWriterShow.append("Event location: " + Eventlocation + "\t");
        myWriterShow.append(",");
        myWriterShow.append("Remaining Capacity: " + new Integer(remainingCapacity).toString() + "\t");
        myWriterShow.append("\n");
        int costnum2 = 1;
        for (Double cost : eventcosts
        ) {

            myWriterShow.append("costs" + costnum2 + ": " + cost + System.lineSeparator());
            costnum2++;
        }
        myWriterShow.append("\n");
        myWriterShow.close();
        myLogger.info("event.Event saved.");

    }

    public static int CapacityAccount(int num) {
        remainingCapacity = remainingCapacity - num;
        return num;
    }

    public static boolean checkDiscount(int num) {
        return discounts.containsKey(num);
    }

}
