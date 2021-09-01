package com.ainur.numbergenerator.controller;

import com.ainur.numbergenerator.service.NumberService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.ainur.numbergenerator.utils.Constants.*;

@Slf4j
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RestController
@RequestMapping(BASE_URL)
public class NumberController {

    NumberService service;

    @ApiOperation(
            value = "Получение следующего номера")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Номер получен"),
            @ApiResponse(code = 500, message = "Ошибка сервера")})
    @GetMapping(NEXT)
    public String getNextNumber() {
        log.info("GET: {}{}", BASE_URL, NEXT );
        return service.getNextNumber();
    }

    @ApiOperation(
            value = "Получение случайного номера")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Номер получен"),
            @ApiResponse(code = 500, message = "Ошибка сервера")})
    @GetMapping(RANDOM)
    public String getRandomNumber() {
        log.info("GET: {}{}", BASE_URL, RANDOM );
        return service.getRandomNumber();
    }
}
