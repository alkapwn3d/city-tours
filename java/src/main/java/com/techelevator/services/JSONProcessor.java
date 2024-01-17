package com.techelevator.services;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

public class JSONProcessor {
    public static Map<String, String> JSONParser(String input, ArrayList<String> requiredOutput) {
        Map<String, String> output = new TreeMap<>();
        String jsonString = input;
        ObjectMapper mapper = new ObjectMapper();

        try {
            JsonNode jsonNode = mapper.readTree(jsonString);
            System.out.println(jsonNode.toString());
            for(String entry : requiredOutput) {
                String requestedData = jsonNode.get(entry).asText();
                output.put(entry, requestedData);
                System.out.println(jsonNode+": " + entry);
            }
        } catch (Exception e) {
            System.out.println("JSON parse error");
        }
        return output;
    }
}

