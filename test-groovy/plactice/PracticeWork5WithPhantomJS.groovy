package plactice

import org.apache.commons.lang3.SystemUtils
import org.fluentlenium.adapter.FluentTest
import org.fluentlenium.core.annotation.Page
import org.junit.Test
import org.openqa.selenium.Capabilities
import org.openqa.selenium.WebDriver
import org.openqa.selenium.phantomjs.PhantomJSDriver
import org.openqa.selenium.phantomjs.PhantomJSDriverService
import org.openqa.selenium.remote.DesiredCapabilities
import practicework_fluent.pages.RenewalReserveInputPage

import java.nio.file.Paths
import java.util.concurrent.TimeUnit

import static org.fluentlenium.core.filter.FilterConstructor.withName

@org.junit.experimental.categories.Category(Integer)
class PracticeWork5WithPhantomJS extends FluentTest {

    @Override
    WebDriver getDefaultDriver() {
        def phantomjsPath = {
            String path
            if (SystemUtils.IS_OS_MAC || SystemUtils.IS_OS_MAC_OSX) {
                path = "tools/phantomjs/1.9.2/macosx/bin/phantomjs" // Mac環境の場合
            } else {
                path = "tools/phantomjs/1.9.2/windows/phantomjs.exe" // Windows環境の場合
            }
            Paths.get(path).toAbsolutePath()
        }().toString()

        println(phantomjsPath)
        Capabilities capabilities = new DesiredCapabilities()
        capabilities.setCapability(PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY, phantomjsPath)
        WebDriver driver = new PhantomJSDriver(capabilities)
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
        assert inputPage.$("#reserve_year").value == now.format("yyyy")
        assert inputPage.$("#reserve_month").value == now.format("M")
        assert inputPage.$("#reserve_day").value == now.format("d")
        assert inputPage.$("#reserve_term").value == "1"
        assert inputPage.$("#headcount").value == "1"
        assert inputPage.$("input", 0, withName("bf")).selected
        assert !inputPage.$("input", 1, withName("bf")).selected
        assert !inputPage.findFirst("#plan_a").selected
        assert !inputPage.findFirst("#plan_b").selected
        assert !inputPage.$("#guestname").value
    }
}
