package ru.netology;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.exactValue;
import static com.codeborne.selenide.Selenide.*;

public class DeliveryTesting {

    LocalDate date = LocalDate.now();

    @Test
    public void shouldSubmitForm(){
        open("http://localhost:9999/");
        $("[data-test-id=city] input").setValue("Москва");
        String month = "0";
        if (date.getMonthValue() <= 9) {
            month = "0" + String.valueOf(date.getMonthValue());
        } else {
            month = String.valueOf(date.getMonthValue());
        }
        $("[placeholder=\"Дата встречи\"]").sendKeys(Keys.CONTROL + "a" + Keys.DELETE);
        $("[placeholder=\"Дата встречи\"]").setValue(String.valueOf((date.getDayOfMonth() + 5)) + month + (String.valueOf(date.getYear())));
        $("[data-test-id=name] input").setValue("Иванов Иван Иванович");
        $("[name=phone]").setValue("+79157809999");
        $(".checkbox__box").click();
        $(".button").click();
        $("[data-test-id=\"notification\"]").shouldBe(Condition.visible, Duration.ofSeconds(15));
    }

    @Test
    public void shouldSubmitForm1(){
        open("http://localhost:9999/");
        $("[data-test-id=city] input").setValue("Мо");
        $x("//*[text()='Москва']").click();
        $(".icon-button__text").click();
        int newDate = date.getDayOfMonth() + 7;
        $$(".calendar__day").find(exactText(String.valueOf(newDate))).click();
        $("[data-test-id=name] input").setValue("Иванов Иван Иванович");
        $("[name=phone]").setValue("+79157809999");
        $(".checkbox__box").click();
        $(".button").click();
        $("[data-test-id=\"notification\"]").shouldBe(Condition.visible, Duration.ofSeconds(15));
    }
}
