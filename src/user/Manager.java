package user;

public class Manager {
    private int footbalSold;//تعداد بلیت فروخته شده ی فوتبال
    private int filmSold;//تعداد بلیت فروخته شده ی فیلم
    private int footbalRemain;//تعداد کل بلیت فوتبال
    private int filmRemain;//تعداد کل بلیت فیلم

    public void setFootbalRemain(int footbalRemain) {
        this.footbalRemain = footbalRemain;
    }

    public int getFootbalRemain() {
        return footbalRemain;
    }


    public void setFootbalSold(int footbalSold) {
        this.footbalSold += footbalSold;
    }

    public int getFootbalSold() {
        return footbalSold;
    }


    public void setFilmRemain(int filmRemain) {
        this.filmRemain = filmRemain;
    }

    public int getFilmRemain() {
        return filmRemain;
    }

    public void setFilmSold(int filmSold) {
        this.filmSold += filmSold;
    }

    public int getFilmSold() {
        return filmSold;
    }

    public int footbalReport() {//تابع حساب تعداد باقیمانده فوتبال
        int report = footbalRemain - footbalSold;
        return report;
    }

    public int filmReport() {//تابع حساب تعداد باقیمانده فیلم
        int report = filmRemain - filmSold;
        return report;
    }
}
