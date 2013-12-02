package plactice.pages

import org.openqa.selenium.By
import org.openqa.selenium.Keys
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.ui.Select


class ReserveInputPage {
    WebDriver driver
    By datePick = By.id("datePick")
    By reserveYear = By.id("reserve_year")
    By reserveMonth = By.id("reserve_month")
    By reserveDay = By.id("reserve_day")
    By reserveTerm = By.id("reserve_term")
    By headCount = By.id("headcount")
    By breakfastOn = By.id("breakfast_on")
    By breakfastOff = By.id("breakfast_off")
    By planA = By.id("plan_a")
    By planB = By.id("plan_b")
    By guestName = By.id("guestname")
    By goToNext = By.id("agree_and_goto_next")

    String getReserveYear() {
        driver.findElement(reserveYear).getAttribute("value")
    }

    String getReserveMonth() {
        driver.findElement(reserveMonth).getAttribute("value")
    }

    String getReserveDay() {
        driver.findElement(reserveDay).getAttribute("value")
    }

    void setReserveDate(String year, String month, String day) {
        WebElement element = driver.findElement(datePick)
        element.clear()
        element.sendKeys(year + "/" + month + "/" + day)
        element.sendKeys(Keys.RETURN)
    }

    void setReserveTerm(String value) {
        new Select(driver.findElement(reserveTerm)).selectByValue(value)
    }

    String getReserveTerm() {
        driver.findElement(reserveTerm).getAttribute("value")
    }

    void setHeadCount(String value) {
        new Select(driver.findElement(headCount)).selectByValue(value)
    }

    String getHeadCount() {
        driver.findElement(headCount).getAttribute("value")
    }

    void setBreakfast(boolean on) {
        if (on) {
            driver.findElement(breakfastOn).click()
        } else {
            driver.findElement(breakfastOff).click()
        }
    }

    boolean getBreakfast() {
        driver.findElement(breakfastOn).selected
    }

    void setPlanA(boolean checked) {
        WebElement element = driver.findElement(planA)
        if (element.selected != checked) {
            element.click()
        }
    }

    boolean getPlanA() {
        driver.findElement(planA).selected
    }

    void setPlanB(boolean checked) {
        WebElement element = driver.findElement(planB)
        if (element.selected != checked) {
            element.click()
        }
    }

    boolean getPlanB() {
        driver.findElement(planB).selected
    }

    void setGuestName(String value) {
        WebElement element = driver.findElement(guestName)
        element.clear()
        element.sendKeys(value)
    }

    String getGuestName() {
        driver.findElement(guestName).getAttribute("value")
    }

    ReserveConfirmPage goToNext() {
        driver.findElement(goToNext).click()
        new ReserveConfirmPage(driver: driver)
    }
}
