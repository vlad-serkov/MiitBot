package io.proj3ct.miitbot.service;

import org.springframework.stereotype.Service;

@Service
public class PhoneNumberService {
    public String formatNumber(String number) {
        return number.replaceFirst("(\\d{1})(\\d{3})(\\d{3})(\\d{2})(\\d{2})", "$1 ($2) $3-$4-$5");
    }
}
