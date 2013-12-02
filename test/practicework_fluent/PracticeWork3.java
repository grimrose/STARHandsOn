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

public class PracticeWork3 extends FluentTest {

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
        inputPage.fillReserveDate("2013", "12", "8");
        inputPage.fillReserveTerm("3");

        inputPage.selectBreakfast(false);
        inputPage.clickPlanA(false);
        inputPage.clickPlanB(false);
        inputPage.fillGuestName("huga");

        // Exercise
        inputPage.clickGoToNext();

        // 2ページ目入力画面
        assertThat(confirmPage.price()).isEqualTo("22750");
        assertThat(confirmPage.dateFrom()).isEqualTo("2013年12月8日");
        assertThat(confirmPage.dateTo()).isEqualTo("2013年12月11日");
        assertThat(confirmPage.daysCount()).isEqualTo("3");

        assertThat(confirmPage.breakfast()).isEqualTo("なし");
        assertThat(confirmPage.existsPlanA()).isFalse();
        assertThat(confirmPage.existsPlanB()).isFalse();
        assertThat(confirmPage.guestName()).isEqualTo("huga");

        confirmPage.commit();
    }

}
