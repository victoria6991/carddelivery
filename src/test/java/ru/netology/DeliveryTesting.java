package ru.netology;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.Test;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class DeliveryTesting {
    @Test
    public void shouldSubmitForm(){
        open("http://localhost:9999/");
        $("[data-test-id=city] input").setValue("Москва");
        $("[placeholder=\"Дата встречи\"]").setValue("18.05.2022");
        $("[data-test-id=name] input").setValue("Иванов Иван Иванович");
        $("[name=phone]").setValue("+79157809999");
        $(".checkbox__box").click();
        $(".button").click();
        $("[data-test-id=\"notification\"]").shouldBe(Condition.visible, Duration.ofSeconds(15));
    }
}
