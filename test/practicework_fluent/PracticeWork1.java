package practicework_fluent;

import org.apache.commons.lang3.SystemUtils;
import org.fluentlenium.adapter.FluentTest;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.util.concurrent.TimeUnit;

import static org.fest.assertions.Assertions.assertThat;


public class PracticeWork1 extends FluentTest {

    @Override
    public WebDriver getDefaultDriver() {
        String path;
        if (SystemUtils.IS_OS_MAC || SystemUtils.IS_OS_MAC_OSX) {
            path = "chromedriver/mac/chromedriver"; // Mac環境の場合
        } else {
            path = "chromedriver/win/chromedriver.exe"; // Windows環境の場合
        }
        System.setProperty("webdriver.chrome.driver", new File(path).getAbsolutePath());
        WebDriver driver = new ChromeDriver();
        // ページ遷移の際に少し待機するため
        driver.manage().timeouts().implicitlyWait(500, TimeUnit.MILLISECONDS);
        return driver;
    }


    @Test
    public void test() throws Exception {
        // Setup
        File html = new File("reserveApp/index.html");
        String url = "file:///" + html.getAbsolutePath();
        goTo(url);

        // 1ページ目入力画面
        fill("#reserve_year").with("2013");
        fill("#reserve_month").with("12");
        fill("#reserve_day").with("7");
        fill("#reserve_term").with("1");

        fill("#headcount").with("9");
        click("#breakfast_on");
        click("#plan_a");
        click("#plan_b");
        fill("#guestname").with("a");

        // Exercise
        click("#goto_next");

        // Verify
        // 2ページ目入力画面
        assertThat(find("#price").contains("105750"));
        assertThat(find("#datefrom").contains("2013年12月7日"));
        assertThat(find("#dateto").contains("2013年12月8日"));
        assertThat(find("#dayscount").contains("1"));

        assertThat($("#hc").getText()).isEqualTo("9");
        assertThat($("#bf_order").getText()).isEqualTo("あり");
        assertThat($("#plan_a_order").getText()).isEqualTo("昼からチェックインプラン");
        assertThat($("#plan_b_order").getText()).isEqualTo("お得な観光プラン");
        assertThat($("#gname").getText()).isEqualTo("a");

        click("#commit");
    }

}
