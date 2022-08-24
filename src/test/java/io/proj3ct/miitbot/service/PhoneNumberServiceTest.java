package io.proj3ct.miitbot.service;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PhoneNumberServiceTest {

    @Test
    void formatNumber() {
        PhoneNumberService phoneNumberService = new PhoneNumberService();
        System.out.println(phoneNumberService.formatNumber("+79788576201"));
    }
}