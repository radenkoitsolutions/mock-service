package com.example.mock.controller;

import com.example.mock.data.exception.MissingFileException;
import com.example.mock.service.MockService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class MockController {
    private final MockService service;

    @GetMapping("/rest")
    public ResponseEntity<Object> processRequest(@RequestParam(value = "file", defaultValue = "api.json") String fileName) throws IOException, MissingFileException {
        return ResponseEntity.ok(service.getResponse(fileName));
    }
}


