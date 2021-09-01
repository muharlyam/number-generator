package com.ainur.numbergenerator.service.impl;

import com.ainur.numbergenerator.service.NumberService;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class NumberServiceImpl implements NumberService {


    @Override
    public String getNextNumber() {

        return "Next";
    }

    @Override
    public String getRandomNumber() {

        return "random";
    }


}
