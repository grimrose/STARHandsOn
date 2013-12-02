package practicework_fluent.pages;

import org.fluentlenium.core.FluentPage;

import java.io.File;

public class ReserveInputPage extends FluentPage {

    @Override
    public String getUrl() {
        File html = new File("reserveApp/index.html");
        return "file:///" + html.getAbsolutePath();
    }


    public void fillReserveDate(String year, String month, String day) {
        fill("#reserve_year").with(year);
        fill("#reserve_month").with(month);
        fill("#reserve_day").with(day);
    }

    public void fillReserveTerm(String term) {
        fill("#reserve_term").with(term);
    }

    public void clickGoToNext() {
        click("#goto_next");
    }

    public void fillHeadCount(String headCount) {
        fill("#headcount").with(headCount);
    }

    public void selectBreakfast(boolean on) {
        if (on) {
            $("#breakfast_on").click();
        } else {
            $("#breakfast_off").click();
        }
    }

    public void clickPlanA(boolean planA) {
        if (planA) {
            click("#plan_a");
        }
    }

    public void clickPlanB(boolean planB) {
        if (planB) {
            click("#plan_b");
        }
    }

    public void fillGuestName(String guestName) {
        fill("#guestname").with(guestName);
    }

}
