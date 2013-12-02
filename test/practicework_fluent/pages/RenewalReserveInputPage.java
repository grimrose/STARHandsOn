package practicework_fluent.pages;

import org.fluentlenium.core.FluentPage;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import java.io.File;

public class RenewalReserveInputPage extends FluentPage {

    @Override
    public String getUrl() {
        File html = new File("reserveApp_Renewal/index.html");
        return "file:///" + html.getAbsolutePath();
    }


    public void fillReserveDate(String year, String month, String day) {
        fill("#datePick").with(String.format("%1$s/%2$s/%3$s", year, month, day));
        $("#datePick").first().getElement().sendKeys(Keys.RETURN);
    }

    public void fillReserveTerm(String term) {
        fillSelect("#reserve_term").withValue(term);
    }

    public void clickAgreeAndGoToNext() {
        click("#agree_and_goto_next");
    }

    public void fillHeadCount(String headCount) {
        fillSelect("#headcount").withValue(headCount);
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
