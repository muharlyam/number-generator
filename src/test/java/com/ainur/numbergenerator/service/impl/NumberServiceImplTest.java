package com.ainur.numbergenerator.service.impl;

import com.ainur.numbergenerator.service.NumberService;
import com.ainur.numbergenerator.storage.Storage;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class NumberServiceImplTest {

    @Autowired
    NumberService service;

    @Autowired
    Storage storage;

    @Test
    void getNextNumber() {

        // Первый элемент
        String test = service.getNextNumber();
        assertEquals(test, "A000AA 116 RUS");

        // Второй элемент
        test = service.getNextNumber();
        assertEquals(test, "A001AA 116 RUS");

        // 1002 элемент для проверки правильности изменения буквы
        for (int i = 0; i < 1000; i++) {
            service.getNextNumber();
        }
        test = service.getNextNumber();
        assertEquals(test, "A002AB 116 RUS");

        // Проверка на неповторяющиеся номера
        storage.addNumber("A003AB 116 RUS");

        test = service.getNextNumber();
        assertEquals(test, "A004AB 116 RUS");


    }

    @Test
    void getRandomNumber() {

        int size = storage.size();

        for (int i = 0; i < 1000; i++) {
            service.getRandomNumber();
        }

        assertEquals(storage.size() - size, 1000);
    }
}