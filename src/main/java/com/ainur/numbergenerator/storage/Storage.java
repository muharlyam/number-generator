package com.ainur.numbergenerator.storage;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

// Хранилище созданных номеров
@Component
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class Storage {

    Set<String> numbersSet;

    public Storage() {
        numbersSet = new HashSet<>();
    }

    public void addNumber(String number) {
        numbersSet.add(number);
    }

    public boolean contains(String number) {
        return numbersSet.contains(number);
    }

    public int size() {
        return numbersSet.size();
    }
}
