package com.ainur.numbergenerator.service.impl;

import com.ainur.numbergenerator.service.NumberService;
import com.ainur.numbergenerator.storage.Storage;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

import static com.ainur.numbergenerator.utils.Constants.*;

@Slf4j
@Service
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class NumberServiceImpl implements NumberService {

    // numbers - представление номера A111AA в виде массива чисел
    int[] numbers;

    // Хранилище созданных номеров
    Storage storage;

    @Autowired
    NumberServiceImpl(Storage storage) {
        this.storage = storage;
        numbers = new int[]{0, 0, 0, 0, 0, 0};
    }

    @Override
    public String getNextNumber() {

        String number = createNextNumber();

        while (storage.contains(number)) {
            number = createNextNumber();
        }

        storage.addNumber(number);

        return number;
    }

    @Override
    public String getRandomNumber() {

        String number = createRandomNumber();

        while (storage.contains(number)) {
            number = createRandomNumber();
        }

        storage.addNumber(number);

        return number;
    }

    /**
     * Массив чисел преобразует в строку формата A111AA 116 RUS
     *
     * @return - строка формата A111AA 116 RUS
     */
    private String createNextNumber() {

        char[] chars = new char[NUMBER_LENGTH];

        log.info("{}", Arrays.toString(numbers));

        chars[0] = SYMBOLS.charAt(numbers[0]);
        chars[1] = (char) (numbers[3] + '0');
        chars[2] = (char) (numbers[4] + '0');
        chars[3] = (char) (numbers[5] + '0');
        chars[4] = SYMBOLS.charAt(numbers[1]);
        chars[5] = SYMBOLS.charAt(numbers[2]);
        chars[6] = ' ';

        String number = new String(chars);

        increment();

        return number + REGION;

    }

    /**
     * получение случайного номера
     */
    private String createRandomNumber() {

        char[] chars = new char[NUMBER_LENGTH];


        chars[0] = SYMBOLS.charAt((int) (Math.random() * 12));
        chars[1] = (char) ((int) (Math.random() * 9) + '0');
        chars[2] = (char) ((int) (Math.random() * 9) + '0');
        chars[3] = (char) ((int) (Math.random() * 9) + '0');
        chars[4] = SYMBOLS.charAt((int) (Math.random() * 12));
        chars[5] = SYMBOLS.charAt((int) (Math.random() * 12));
        chars[6] = ' ';

        String number = new String(chars);

        return number + REGION;

    }

    /**
     * Инкрементирует числовое представление номера
     *
     * Элементы i = 0, 1, 2 - представляют буквы номера. Увеличение
     * разряда происходит по достижению 13
     */
    private void increment() {

        boolean flag = true;

        for (int i = numbers.length - 1; i >= 0; i--) {
            if (flag) {
                numbers[i]++;
                flag = false;
            }
            if ((i == 5 || i == 4 || i == 3) && numbers[i] == 10) { // элементы, отвечающие за цифры
                flag = true;
                numbers[i] = 0;
            } else if ((i == 2 || i == 1) && numbers[i] == 13) { // элементы, отвечающие за буквы
                flag = true;
                numbers[i] = 0;
            } else if (i == 0 && numbers[i] == 13) { // первая буква номера
                for (int j = 0; j < numbers.length; j++) {
                    numbers[i] = 0;
                }
            }
        }
    }

}
