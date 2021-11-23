import event.Film;
import event.Football;
import exceptions.*;
import user.Manager;
import user.Users;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.FileHandler;
import java.util.logging.Logger;

public class EventTicketSales {
    public static final String ANSI_RED = "\u001B[31m";//رنگ کنسول
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";//رنگ کنسول

    private static Scanner input = new Scanner(System.in);
    private static Users user = new Users();

    // Manager manager = new Manager();
//   public static Logger mylogger;
    public static void logger(String msg) {

        Logger myLogger = Logger.getAnonymousLogger();
        String path = "C:\\Users\\emiteess\\IdeaProjects\\EventTicketSales\\src\\Error.log";
        FileHandler handeler = null;
        try {
            handeler = new FileHandler(path, 1024 * 1024 * 5, 20, true);

        } catch (IOException e) {
            e.printStackTrace();
        }

        handeler.setFormatter(new LogTextFormatter());
        myLogger.addHandler(handeler);
        myLogger.info(msg);
    }

    public static void main(String[] args) throws IOException, PasswordTooShort, UsernameAlreadyExists, InvalidUsername, InvalidPassword {
//        Football myfootball = null;
//        Film myfilm = null;

        Manager manager = new Manager();//مدیر
        Boolean open = true;// برای تکرار عملیات تا اینکه دستور خروج داده شود
        int userinput;//ورودی کاربر
        while (open) {
            ShowObtionList();//نمایش تابع لیست ابشن ها
            userinput = input.nextInt();
            if (userinput == 1) {//در صورت انتخاب ورود مدیر
                System.out.println(ANSI_PURPLE + "\t\t\t\tLogin to the Management panel");
                System.out.println(ANSI_PURPLE + "Username :");
                String inputusername1 = input.next();
                System.out.println(ANSI_PURPLE + "Password :");
                int inputPassword2 = input.nextInt();
                //Manager manager = new Manager();
                //چک کردن رمز و نام کاربری مدیر
                if ((inputusername1.equalsIgnoreCase("mah")) && (inputPassword2 == 123)) {
                    System.out.println("Select the option you want.\n" +
                            "1- Define a new event\n" +
                            "2- Reporting");
                    System.out.println(ANSI_YELLOW + "++++++++++++++++++++++");
                    System.out.println(ANSI_PURPLE + "Number: ");
                    int inputManager = input.nextInt();
                    if (inputManager == 1) {//تعریف رویداد جدید
                        System.out.println("How many event want to define?");
                        int defineevent = input.nextInt();
                        for (int i = 1; i <= defineevent; i++) {
                            System.out.println("\t\t\t\tNew event definition");
                            System.out.println("Enter event number:" +
                                    "\n1-footbal" +
                                    "\n2-film");
                            int eventNumber = input.nextInt();
                            System.out.println("Enter event name:");
                            String eventName = input.next();
                            System.out.println("Enter event date:");
                            String eventDate = input.next();
                            System.out.println("Enter event time:");
                            String eventTime = input.next();
                            System.out.println("Enter the location of the event:");
                            String eventlocation = input.next();
                            System.out.println("Enter event capacity:");
                            int eventcapacity = input.nextInt();
                            System.out.println("How many types of tickets are there?");
                            int typeNum = input.nextInt();
                            ArrayList<Double> eventcosts = new ArrayList<>();//برای گرفتن چند نوع قیمت
                            for (int j = 1; j <= typeNum; j++) {
                                System.out.print("Enter the " + j + " type:");
                                double eventcost = input.nextDouble();
                                eventcosts.add(eventcost);
                            }
                            //گرفتن کد های تخفیف و درصد انان که در صورت نبود 0 وارد میشود
                            System.out.println("How many discount codes are there?(If you do not have a discount code, enter 0)");
                            int discountNum = input.nextInt();
                            HashMap<Integer, Integer> discounts = new HashMap<>();
                            for (int j = 1; j <= discountNum; j++) {
                                System.out.print("Enter the discount " + j + " code:");
                                int discount = input.nextInt();
                                System.out.print("Enter the discount " + j + " percentage:");
                                int percentage = input.nextInt();
                                discounts.put(discount, percentage);
                            }

                            System.out.println("-----------------");
                            if (eventNumber == 1) {//اگر مدیر خواست رویداد فوتبال تعریف کند
                                manager.setFootbalRemain(eventcapacity);//فرستادن ظرفیت به کلاس مدیر براس گزارشگیری
                                Football myfootball = new Football(eventName, eventDate, eventTime, eventlocation, eventcapacity, eventcosts, discounts);

                            } else if (eventNumber == 2) {//اگر مدیر خواست رویداد فیلم تعریف کند
                                manager.setFilmRemain(eventcapacity);
                                Film myfilm = new Film(eventName, eventDate, eventTime, eventlocation, eventcapacity, eventcosts, discounts);

                            } else {
                                logger("wrong event");//شماره رویداد اشتباه
                                System.out.println(ANSI_RED + "Your request is not available!");
                            }
                        }

                        //  event.Event event = new event.Event(eventNumber, eventName, eventDate, eventTime, eventlocation, eventcapacity, eventcosts, discounts);
                    } else if (inputManager == 2) {
                        System.out.println(ANSI_CYAN + "film Report");
                        System.out.println("Film Tickets sold:" + manager.getFilmSold());
                        System.out.println("Film Remaining tickets:" + manager.filmReport());
                        System.out.println("----------------------------------------------");
                        System.out.println(ANSI_CYAN + "football Report");
                        System.out.println("Film Tickets sold:" + manager.getFootbalSold());
                        System.out.println("Film Remaining tickets:" + manager.footbalReport());
                        System.out.println(ANSI_YELLOW + "----------------------------------------------");

                    } else {
                        logger("wrong event user");//کاربر رویدادی انتخاب کرده ک موجود نیست
                        System.out.println(ANSI_RED + "Your request is not available!");
                    }
                } else {
                    Toolkit.getDefaultToolkit().beep();//صدای خطا بدهد
                    logger("Manger Wrong Password/usernam");//مدیر نام کاربری یا رمز اشتباه زده
                    System.out.println(ANSI_RED + "Access denied!!!");
                }
            } else if (userinput == 2) {//درصورت انتخاب ورود کاربر
                System.out.println("Select the option you want." +
                        "\n1- login\n" +
                        "2- signin\n" +
                        "0- exit");
                System.out.println(ANSI_YELLOW + "++++++++++++++++++++++");
                System.out.println(ANSI_PURPLE + "Number: ");
                int inputuser = input.nextInt();
                if (inputuser == 1) {//لاگین کاربر
                    user.login();//تابع لاگین در کلاس یوز را فراخوانی میکند
                    if (user.isLoggedIn() == true) {//اگر کار بر لاگین بود
                        try {//لیست های رویداد های فیلم و فوتبال را نمایش بدهد
                            File myObj = new File("C:\\Users\\emiteess\\IdeaProjects\\EventTicketSales\\src\\ShowFootballEvent.txt");
                            File myObj2 = new File("C:\\Users\\emiteess\\IdeaProjects\\EventTicketSales\\src\\FilmShowEvent.txt");
                            Scanner myReader = new Scanner(myObj);
                            Scanner myReader2 = new Scanner(myObj2);
                            System.out.println(ANSI_CYAN + "\tFootball Events:");
                            while (myReader.hasNextLine()) {
                                String data = myReader.nextLine();
                                System.out.println(data);
                            }
                            System.out.println("\t Film Events");
                            while (myReader2.hasNextLine()) {
                                String data = myReader2.nextLine();
                                System.out.println(data);
                            }
                            myReader.close();
                            myReader2.close();
                            //خرید رویداد
                            System.out.println(ANSI_YELLOW + "---------Buy a ticket--------");
                            System.out.println(ANSI_PURPLE + "Enter event number:" +
                                    "\n1-footbal" +
                                    "\n2-film");
                            System.out.println(ANSI_YELLOW + "++++++++++++++++++++++");
                            System.out.println(ANSI_PURPLE + "Number: ");
                            int userEventNum = input.nextInt();
                            System.out.println(ANSI_PURPLE + "How many Ticket do you want?");
                            int ticketNum = input.nextInt();
                            // Football.CapacityAccount(ticketNum);
                            System.out.println(ANSI_PURPLE + "If you have a discount code, enter it");
                            System.out.println("Eenter 0 if you dont one.");//نداشتن کد تخفیف صفر وارد کند
                            int discountuser = input.nextInt();
                            System.out.println("whitch type of ticket do you want?");//شماره نوع بلیتی ک میخواهد
                            int type = input.nextInt();
                            System.out.println("--------------------------");

                                //نوع رویداد و تعداد و کد تخفیف و نوع بلیت را به تابع کلاس کاربر میفرستد
                                user.BuyTicket(userEventNum, ticketNum, discountuser, type - 1);//به خاطر ایندکس
                                if (userEventNum == 1) {//فوتبال را انتخاب کرد
                                    manager.setFootbalSold(ticketNum);//برای مدیر تعداد خریداری شده را میفرستد
                                } else if (userEventNum == 2) {//فیلم
                                    manager.setFilmSold(ticketNum);//برای مدیر تعداد خریداری شده را میفرستد

                                } else {
                                    logger("wrong event");
                                    System.out.println(ANSI_RED + "Your request is not available!");
                                }
                                // event.Event userevent = new Event();
                                //***************************************************
                                //int cpacity= userevent.CapacityAccount(TicketNum);


                        } catch (FileNotFoundException e) {
                            logger("Error");
                            System.out.println(ANSI_RED + "ERROR!");
                            e.printStackTrace();
                        }
                    }


                } else if (inputuser == 2) {//ثبت نام کاربر
                    user.signUp();

                } else if (inputuser == 0) {//فرمان خروج توسط کاربر
                    open = false;
                } else {
                    logger("User wrong choice");//گزینه ناموجود در منو کاربری
                    System.out.println(ANSI_RED + "The option you selected does not exist.");
                }
            } else if (userinput == 0)// فرمان خروج کلی
                open = false;
            else {
                logger("wrong menu number");//نبودن عدد ورودی کاربر در منو اولیه
                System.out.println(ANSI_RED + "Your request is not available!");
            }

        }

    }

    private static void ShowObtionList() {//تابع منوی اولیه
        System.out.println(ANSI_CYAN + "\t\t\t\tEvent ticket sales\n" + ANSI_PURPLE +
                "1- Manager login\n" +
                "2- User login\n" +
                "0- Exit");
        System.out.println(ANSI_YELLOW + "++++++++++++++++++++++");
        System.out.println(ANSI_PURPLE + "Number: ");

    }


}
