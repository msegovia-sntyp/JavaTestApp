package com.example;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        System.out.println("[INFO] Starting JavaTestApp...");

        // Archivo JSON de prueba
        String filePath = "test.json";
        File file = new File(filePath);

        // Crear un JSON de prueba si no existe
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.err.println("[ERROR] Error creating test.json file: " + e.getMessage());
                return;
            }
        }

        // Intentar leer el JSON con Jackson (vulnerable en versiones antiguas)
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            TestData data = objectMapper.readValue(file, TestData.class);
            System.out.println("[SUCCESS] Parsed JSON data: " + data);
        } catch (IOException e) {
            System.err.println("[ERROR] Error parsing JSON: " + e.getMessage());
        }
    }
}

// Clase para mapear el JSON
class TestData {
    public String name;
    public int age;

    @Override
    public String toString() {
        return "{name=" + name + ", age=" + age + "}";
    }
}
