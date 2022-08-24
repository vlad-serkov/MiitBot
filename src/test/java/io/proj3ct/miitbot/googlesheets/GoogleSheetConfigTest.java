package io.proj3ct.miitbot.googlesheets;

import com.google.api.services.sheets.v4.Sheets;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class GoogleSheetConfigTest {

    @Autowired
    Sheets sheets;



    @Test
    void sheets() {

    }
}