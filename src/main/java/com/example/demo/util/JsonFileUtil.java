package com.example.demo.util;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.example.demo.model.User;
import com.fasterxml.jackson.core.type.TypeReference;
// Objet mapper is used to convert Java objects to JSON and vice versa
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonFileUtil {

    private static final String FILE_PATH = "src/main/resources/users.json";
    private static final ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule()).disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);


    public static List<User> readUsers() {
        try {
            File file = new File(FILE_PATH);
            if (!file.exists()) {
                return new ArrayList<>();
            }
            return objectMapper.readValue(file, new TypeReference<List<User>>() {});
        } catch (IOException e) {
            return new ArrayList<>();
        }
    }

    public static void writeUsers(List<User> users){
        try{
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File(FILE_PATH), users);
        } catch(IOException e){
            e.printStackTrace();
        }
    }
}
