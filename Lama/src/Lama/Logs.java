package Lama;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Logs {

    // Method to write a list of logs to a JSON file, appending to existing logs
    public void writeLogs(ArrayList<String> logs, String fileName) {
        // Create a JSON parser to read existing logs
        JSONParser parser = new JSONParser();
        JSONObject jsonObject = new JSONObject();
        JSONArray jsonLogs = new JSONArray();

        // Try to read existing logs from the file
        try (FileReader reader = new FileReader(fileName)) {
            Object obj = parser.parse(reader);
            jsonObject = (JSONObject) obj;
            jsonLogs = (JSONArray) jsonObject.get("logs");
        } catch (IOException | ParseException e) {
            // If the file doesn't exist or is empty, start with a new JSON object
            System.out.println("Creating a new log file.");
        }

        // Add new logs with timestamps
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        for (String log : logs) {
            JSONObject logEntry = new JSONObject();
            logEntry.put("timestamp", LocalDateTime.now().format(formatter));
            logEntry.put("log", log);
            jsonLogs.add(logEntry);
        }

        // Add the logs array to the main JSON
        jsonObject.put("logs", jsonLogs);

        // Write JSON to the file, appending if it exists
        try (FileWriter fileWriter = new FileWriter(fileName, true)) {
            fileWriter.write(jsonObject.toJSONString());
            fileWriter.flush();
            System.out.println("Logs appended to file successfully!");
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }
}
