package ru.netology;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;
import org.w3c.dom.Text;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.exactValue;
import static com.codeborne.selenide.Selenide.*;

public class DeliveryTesting {

    LocalDate date = LocalDate.now();

    public String generateDate(int days) {
        return LocalDate.now().plusDays(days).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }

    @Test
    public void shouldSubmitForm(){
        open("http://localhost:9999/");
        $("[data-test-id=city] input").setValue("Москва");
        String planningDate = generateDate(4);
        $("[placeholder=\"Дата встречи\"]").sendKeys(Keys.CONTROL + "a" + Keys.DELETE);
        $("[placeholder=\"Дата встречи\"]").setValue(planningDate);
        $("[data-test-id=name] input").setValue("Иванов Иван Иванович");
        $("[name=phone]").setValue("+79157809999");
        $(".checkbox__box").click();
        $(".button").click();
        $("[data-test-id=\"notification\"]").shouldHave(Condition.text("Встреча успешно забронирована на " + planningDate), Duration.ofSeconds(15));
    }

    @Test
    public void shouldSubmitForm1(){
        open("http://localhost:9999/");
        $("[data-test-id=city] input").setValue("Мо");
        $$(".menu-item__control").find(exactText("Москва")).click();
        $(".icon-button__text").click();
        int newDate = date.getDayOfMonth() + 7;
        String planningDate = generateDate(7);
        int month = date.getMonthValue();
        if ((month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12) && newDate > 31) {
            $("[data-step='1']").click();
            $$(".calendar__day").find(exactText(String.valueOf(newDate - 31))).click();
        }
        else if ((month == 4 || month == 6 || month == 9 || month == 11) && newDate > 30) {
            $("[data-step='1']").click();
            $$(".calendar__day").find(exactText(String.valueOf(newDate-30))).click();
        }
        else if (month == 2 && newDate > 28) {
            $("[data-step='1']").click();
            $$(".calendar__day").find(exactText(String.valueOf(newDate-28))).click();
        }
        else {
            $$(".calendar__day").find(exactText(String.valueOf(newDate))).click();
        }
        $("[data-test-id=name] input").setValue("Иванов Иван Иванович");
        $("[name=phone]").setValue("+79157809999");
        $(".checkbox__box").click();
        $(".button").click();
        $("[data-test-id=\"notification\"]").shouldHave(Condition.text("Встреча успешно забронирована на " + planningDate), Duration.ofSeconds(15));
    }
}
