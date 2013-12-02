package practicework_fluent.pages;


import org.fest.assertions.ListAssert;
import org.fest.assertions.StringAssert;
import org.fluentlenium.core.FluentPage;
import org.fluentlenium.core.domain.FluentList;
import org.fluentlenium.core.domain.FluentWebElement;

import java.io.File;

import static org.fest.assertions.Assertions.assertThat;

public class ReserveConfirmPage extends FluentPage {

    @Override
    public String getUrl() {
        File html = new File("reserveApp/check_info.html");
        return "file:///" + html.getAbsolutePath();
    }

    public String price() {
       return find("#price").getText();
    }

    public String dateFrom() {
        return find("#datefrom").getText();
    }

    public String dateTo() {
        return find("#dateto").getText();
    }

    public String daysCount() {
        return find("#dayscount").getText();
    }

    public void commit() {
        click("#commit");
    }

    public String breakfast() {
        return $("#bf_order").getText();
    }

    public boolean existsPlanA() {
        return $("#plan_a_order").isEmpty() == false;
    }

    public boolean existsPlanB() {
        return $("#plan_b_order").isEmpty() == false;
    }

    public String guestName() {
        return $("#gname").getText();
    }
}
