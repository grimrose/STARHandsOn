package plactice

import org.apache.commons.lang3.SystemUtils
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver
import plactice.pages.ReserveInputPage

import java.nio.file.Paths
import java.util.concurrent.TimeUnit

import static org.hamcrest.CoreMatchers.is
import static org.junit.Assert.assertThat

class PracticeWork5 {

    WebDriver driver

    @Before
    void setUp() {
        System.properties["webdriver.chrome.driver"] = {
            String path
            if (SystemUtils.IS_OS_MAC || SystemUtils.IS_OS_MAC_OSX) {
                path = "chromedriver/mac/chromedriver" // Mac環境の場合
            } else {
                path = "chromedriver/win/chromedriver.exe" // Windows環境の場合
            }
            Paths.get(path).toAbsolutePath()
        }().toString()
        driver = new ChromeDriver()
        // ページ遷移の際に少し待機するため
        driver.manage().timeouts().implicitlyWait 500, TimeUnit.MILLISECONDS
    }

    @After
    void tearDown() {
        driver.quit()
    }

    @Test
    void test() {
        driver.get "file:///" + Paths.get("reserveApp_Renewal/index.html").toAbsolutePath()

        def now = new Date()
        ReserveInputPage inputPage = new ReserveInputPage(driver: driver)
        assertThat inputPage.reserveYear, is(now.format("yyyy"))
        assertThat inputPage.reserveMonth, is(now.format("M"))
        assertThat inputPage.reserveDay, is(now.format("d"))
        assertThat inputPage.reserveTerm, is("1")
        assertThat inputPage.headCount, is("1")
        assertThat inputPage.breakfast, is(true)
        assertThat inputPage.planA, is(false)
        assertThat inputPage.planB, is(false)
        assertThat inputPage.guestName, is("")
    }

}
