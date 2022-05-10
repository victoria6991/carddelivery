package ru.netology;

import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class DeliveryTesting {
    @Test
    public void shouldSubmitForm(){
        open("http://localhost:9999/");
        SelenideElement form = $(".App_appContainer__3jRx1");

    }
}
