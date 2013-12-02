package practicework_fluent;


import org.apache.commons.lang3.SystemUtils;
import org.fluentlenium.adapter.FluentTest;
import org.fluentlenium.core.annotation.Page;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import practicework_fluent.pages.ReserveConfirmPage;
import practicework_fluent.pages.ReserveInputPage;

import java.io.File;
import java.util.concurrent.TimeUnit;

import static org.fest.assertions.Assertions.assertThat;


public class PracticeWork2 extends FluentTest {

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
    public ReserveInputPage inputPage;

    @Page
    public ReserveConfirmPage confirmPage;

    @Test
    public void test() {
        // Setup
        goTo(inputPage);

        // 1ページ目入力画面
        inputPage.fillReserveDate("2013", "12", "7");
        inputPage.fillReserveTerm("1");

        inputPage.fillHeadCount("9");
        inputPage.selectBreakfast(true);
        inputPage.clickPlanA(true);
        inputPage.clickPlanB(true);
        inputPage.fillGuestName("b");

        // Exercise
        inputPage.clickGoToNext();

        // Verify
        // 2ページ目入力画面
        assertThat(confirmPage.price()).isEqualTo("105750");
        assertThat(confirmPage.dateFrom()).isEqualTo("2013年12月7日");
        assertThat(confirmPage.dateTo()).isEqualTo("2013年12月8日");
        assertThat(confirmPage.daysCount()).isEqualTo("1");

        confirmPage.commit();
    }

}
