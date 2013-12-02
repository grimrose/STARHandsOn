package practicework_fluent;

import org.apache.commons.lang3.SystemUtils;
import org.fluentlenium.adapter.FluentTest;
import org.fluentlenium.core.annotation.Page;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import practicework_fluent.pages.RenewalReserveInputPage;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import static org.fest.assertions.Assertions.assertThat;
import static org.fluentlenium.core.filter.FilterConstructor.*;

public class PracticeWork5 extends FluentTest {

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

    @Page
    public RenewalReserveInputPage inputPage;


    @Test
    public void test() {
        // Setup
        goTo(inputPage);

        String pattern = "yyyy/M/d";
        String dateExpected = new SimpleDateFormat(pattern).format(new Date());
        assertThat(inputPage.$("#datePick").getValue()).isEqualTo(dateExpected);
        assertThat(inputPage.$("#reserve_term").getValue()).isEqualTo("1");
        assertThat(inputPage.$("#headcount").getValue()).isEqualTo("1");
        assertThat(inputPage.$("input", 0, withName("bf")).isSelected()).isTrue();
        assertThat(inputPage.$("input", 1, withName("bf")).isSelected()).isFalse();
        assertThat(inputPage.findFirst("#plan_a").isSelected()).isFalse();
        assertThat(inputPage.findFirst("#plan_b").isSelected()).isFalse();
        assertThat(inputPage.$("#guestname").getValue()).isEmpty();
    }

}
