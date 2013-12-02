package plactice

import org.apache.commons.lang3.SystemUtils
import org.fluentlenium.adapter.FluentTest
import org.fluentlenium.core.annotation.Page
import org.junit.Test
import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver
import practicework_fluent.pages.RenewalReserveInputPage

import java.nio.file.Paths
import java.util.concurrent.TimeUnit

import static org.fluentlenium.core.filter.FilterConstructor.withName

class PracticeWork5WithFluent extends FluentTest {

    @Override
    WebDriver getDefaultDriver() {
        System.properties["webdriver.chrome.driver"] = {
            String path
            if (SystemUtils.IS_OS_MAC || SystemUtils.IS_OS_MAC_OSX) {
                path = "chromedriver/mac/chromedriver" // Mac環境の場合
            } else {
                path = "chromedriver/win/chromedriver.exe" // Windows環境の場合
            }
            Paths.get(path).toAbsolutePath()
        }().toString()
        WebDriver driver = new ChromeDriver();
        // ページ遷移の際に少し待機するため
        driver.manage().timeouts().implicitlyWait 500, TimeUnit.MILLISECONDS
        return driver;
    }

    @Page
    RenewalReserveInputPage inputPage

    @Test
    void test() {
        // Setup
        goTo(inputPage)

        def now = new Date()
        assert inputPage.$("#datePick").value == now.format("yyyy/M/d")
        assert inputPage.$("#reserve_year").value ==  now.format("yyyy")
        assert inputPage.$("#reserve_month").value ==  now.format("M")
        assert inputPage.$("#reserve_day").value ==  now.format("d")
        assert inputPage.$("#reserve_term").value == "1"
        assert inputPage.$("#headcount").value == "1"
        assert inputPage.$("input", 0, withName("bf")).selected
        assert !inputPage.$("input", 1, withName("bf")).selected
        assert !inputPage.findFirst("#plan_a").selected
        assert !inputPage.findFirst("#plan_b").selected
        assert !inputPage.$("#guestname").value
    }
}
