package plactice.pages

import org.openqa.selenium.By
import org.openqa.selenium.WebDriver


class ReserveConfirmPage {
    WebDriver driver
    By price = By.id("price")
    By dateFrom = By.id("datefrom")
    By dateTo = By.id("dateto")
    By daysCount = By.id("dayscount")
    By hc = By.id("hc")
    By bfOrder = By.id("bf_order")
    By planAOrder = By.id("plan_a_order")
    By planBOrder = By.id("plan_b_order")
    By gName = By.id("gname")
    By commit = By.id("commit")

    String getPrice() {
        driver.findElement(price).text
    }

    String getDateFrom() {
        driver.findElement(dateFrom).text
    }

    String getDateTo() {
        driver.findElement(dateTo).text
    }

    String getDaysCount() {
        driver.findElement(daysCount).text
    }

    String getHeadCount() {
        driver.findElement(hc).text
    }

    String getBreakfast() {
        driver.findElement(bfOrder).text
    }

    boolean existsPlanA() {
        driver.findElements(planAOrder).size() > 0
    }

    String getPlanA() {
        driver.findElement(planAOrder).text
    }

    boolean existsPlanB() {
        driver.findElements(planBOrder).size() > 0
    }

    String getPlanB() {
        driver.findElement(planBOrder).text
    }

    String getGuestName() {
        driver.findElement(gName).text
    }

    void commit() {
        driver.findElement(commit).click()
    }
}
