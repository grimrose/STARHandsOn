package practicework_fluent;


import org.apache.commons.lang3.SystemUtils;
import org.fluentlenium.adapter.FluentTest;
import org.fluentlenium.core.annotation.Page;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import practicework_fluent.pages.RenewalReserveConfirmPage;
import practicework_fluent.pages.RenewalReserveInputPage;

import java.io.File;
import java.util.concurrent.TimeUnit;

import static org.fest.assertions.Assertions.assertThat;

public class PracticeWork4 extends FluentTest{

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

    @Page
    public RenewalReserveConfirmPage confirmPage;

    @Test
    public void testWork2() {
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
        inputPage.clickAgreeAndGoToNext();

        // Verify
        // 2ページ目入力画面
        assertThat(confirmPage.price()).isEqualTo("105750");
        assertThat(confirmPage.dateFrom()).isEqualTo("2013年12月7日");
        assertThat(confirmPage.dateTo()).isEqualTo("2013年12月8日");
        assertThat(confirmPage.daysCount()).isEqualTo("1");

        confirmPage.commit();
    }

    @Test
    public void testWork3() {
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
        inputPage.clickAgreeAndGoToNext();

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
