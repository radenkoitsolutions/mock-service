package com.example.mock.service;

import com.example.mock.data.exception.MissingFileException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
@RequiredArgsConstructor
public class MockService {
    @Value("${json.response.file.dir}")
    private String responseFolder;

    public Object getResponse(String fileName) {
        String responseFilePath = responseFolder + File.separator + fileName;
        try {
            // check if file exists
            File responseFile = new File(responseFilePath);
            if (!responseFile.exists()) throw new MissingFileException("File not found: " + responseFilePath);

            ObjectMapper objectMapper = new ObjectMapper();

            return objectMapper.readValue(responseFile, Object.class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}