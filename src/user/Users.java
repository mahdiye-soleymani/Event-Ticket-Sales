package user;
import event.Film;
import event.Football;
import exceptions.*;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.FileHandler;
import java.util.logging.Logger;

public class Users {
    public static final String ANSI_RED = "\u001B[31m";//رنگ های کنسول
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_YELLOW = "\u001B[33m";//رنگ های کنسول

    private int eventCode;//کد
    private int eventNum;//تعداد
    private int discount;//کد تخفیف
    private double type;//نوع بلیت

    private String username;
    private String password;

    private boolean isLoggedIn = false;//برای چک کردن لاگ این بودن
    private Map<String, Users> userMap = new HashMap<>();//برای چک کردن یوزنیم
    private Scanner input = new Scanner(System.in);
    public static Logger mylogger;
    public static Logger mylogger2;

    //لاگر
    static {
        mylogger = Logger.getAnonymousLogger();
        mylogger2 = Logger.getAnonymousLogger();
        String path = "C:\\Users\\emiteess\\IdeaProjects\\EventTicketSales\\src\\user.log";
        String path2 = "C:\\Users\\emiteess\\IdeaProjects\\EventTicketSales\\src\\buy.log";
        FileHandler handeler = null;
        FileHandler handeler2 = null;
        try {
            handeler = new FileHandler(path, 1024 * 1024 * 5, 10, true);
            handeler2 = new FileHandler(path2, 1024 * 1024 * 5, 10, true);
        } catch (IOException e) {
            e.printStackTrace();
        }
        handeler.setFormatter(new LogTextFormatter());
        mylogger.addHandler(handeler);

        handeler2.setFormatter(new LogTextFormatter());
        mylogger2.addHandler(handeler2);

    }


    public Users() {
    }

    public Users(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public boolean isLoggedIn() {
        return isLoggedIn;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setLoggedIn(boolean loggedIn) {//برای وضعیت لاگین
        isLoggedIn = loggedIn;
    }

    public void signUp() throws UsernameAlreadyExists, PasswordTooShort {//ثبتنام

        System.out.println(ANSI_PURPLE + "username: ");
        String username = input.nextLine();
        System.out.println(ANSI_PURPLE + "password: ");
        String password = input.nextLine();
        try {
            if (userMap.containsKey(username)) {// یوزر نیم را چک میکند تکراری نباشد
                mylogger.info("ERROR:exists username");//لاگ کاربر
                throw new UsernameAlreadyExists(ANSI_RED + "This username exists!!!");//اکسپشن
            } else if (password.length() < 4) {//پ تداد پسوورد از 4 تا بیشتر باشد
                mylogger.info("WARNING!short password");//لاگ کاربر
                throw new PasswordTooShort(ANSI_RED + "password is too short");
            } else {
                Users user = new Users(username, password);//ثبت یوزر جدید
                userMap.put(user.getUsername(), user);//ذخیره یوزر و یوزر نیم
                //ذخیره نام و نام کاربری
                FileWriter myWriter = new FileWriter("C:\\Users\\emiteess\\IdeaProjects\\EventTicketSales\\src\\userlog.csv", true);
                myWriter.append(username);
                myWriter.append(",");
                myWriter.append(password);
                myWriter.append("\n");
                myWriter.close();

                System.out.println(ANSI_GREEN + "You have registered");
            }
        } catch (UsernameAlreadyExists | PasswordTooShort | IOException e) {
            System.out.println(e.getMessage());
        }

    }


    public void login() throws InvalidPassword, InvalidUsername {//لاگین کردن
        System.out.println(ANSI_PURPLE + "username: ");
        String username = input.nextLine();
        System.out.println(ANSI_PURPLE + "password: ");
        String password = input.nextLine();
        try {
            if (!userMap.containsKey(username)) {//بررسی وجود نام کاربری
                throw new InvalidUsername(ANSI_RED + "username not exists!!! sign up");
            } else if (!userMap.get(username).getPassword().equals(password)) {
                //بررسی اینکه پسوورئ برای ان نام کاربری درست است یا ن
                throw new InvalidPassword(ANSI_RED + "wrong password");
            } else {//لاگین شدن
                userMap.get(username).setLoggedIn(true);
                isLoggedIn = true;
                System.out.println(ANSI_GREEN + "you are now logged in");

            }
        } catch (InvalidUsername | InvalidPassword e) {
            System.out.println(e.getMessage());
        }

    }

//تابع خرید بلیت
    public void BuyTicket(int userEventNum, int ticketNum, int discountuser, int type) throws IOException {
        this.eventCode = userEventNum;
        this.eventNum = ticketNum;
        this.discount = discountuser;
        this.type = type;

        if (userEventNum == 1) {

            if ((ticketNum <= Football.getEventcapacity())) {

                mylogger2.info(".:Successful football purchase:.");//لاگ خرید بلیط
                System.out.println(ANSI_GREEN + "Purchase Invoice.");
                showDate();
                System.out.println("user:"+username);
                Football.CapacityAccount(ticketNum);
                double cost = Football.getEventcost().get(type);
                System.out.println(ANSI_GREEN + "Unit price= " + cost + "$");
                if (Football.checkDiscount(discountuser)) {

                    double percent = Football.getDiscounts().get(discountuser);
                    System.out.println("percent= " + (int) percent + "%");
                    float paymentAmount = (float) (((100 - percent) / 100) * (cost * ticketNum));
                    System.out.println("Total price:" + (cost * ticketNum) + "$");
                    System.out.println("Payment Amount= " + paymentAmount + "$");
                } else if (discountuser == 0) {
                    System.out.println("percent= -");
                    System.out.println("Payment Amount= " + (cost * ticketNum) + "$");
                } else System.out.println("This discount code does not exist!!!");
                System.out.println(ANSI_YELLOW + "---------------------------------------------------");
            }else System.out.println(ANSI_RED+"This number is not available.");

        } else if (userEventNum == 2) {
            if (ticketNum <= Film.getEventcapacity()) {//اگر تعداد کم تر از تعداد موجود بود بخره
            mylogger2.info(".:Successful film purchase:.");
            System.out.println(ANSI_GREEN + "Purchase Invoice.");
            showDate();
            Film.CapacityAccount(ticketNum);
            double cost = Film.getEventcost().get(type);
            System.out.println(ANSI_GREEN + "Unit price= " + cost + "$");
            System.out.println("Number: " + ticketNum);
            if (Film.checkDiscount(discountuser)) {
                double percent = Film.getDiscounts().get(discountuser);
                System.out.println("percent= " + (int) percent + "%");
                float paymentAmount = (float) (((100 - percent) / 100) * (cost * ticketNum));
                System.out.println("Total price:" + (cost * ticketNum) + "$");
                System.out.println("Payment Amount= " + paymentAmount + "$");
            } else if (discountuser == 0) {
                System.out.println("percent= -");
                System.out.println("Payment Amount= " + (cost * ticketNum) + "$");
            } else System.out.println("This discount code does not exist!!!");
            System.out.println(ANSI_YELLOW + "---------------------------------------------------");
            }else System.out.println(ANSI_RED+"This numbers is not available.");
        } else {
            mylogger2.info(".:Unsuccessful purchase:.");
            System.out.println(ANSI_RED + "Your request is not available!");
        }
    }

    public void showDate() {//تابع نمایش ساعت و تاریخ
        SimpleDateFormat shopDate = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat shopTime = new SimpleDateFormat("HH:mm:ss");
        System.out.println(ANSI_CYAN + "Date: " + shopDate.format(new Date()) + "\ttime: " + shopTime.format(new Date()));

    }

}
