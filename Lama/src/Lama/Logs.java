package Lama;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Logs {

    // Method to write a list of logs to a JSON file
    public void writeLogs(ArrayList<String> logs, String fileName) {
        // Create a main JSON object
        JSONObject jsonObject = new JSONObject();
        // Create a JSON array to store logs
        JSONArray jsonLogs = new JSONArray();
        jsonLogs.addAll(logs);
        // Add the array to the main JSON
        jsonObject.put("logs", jsonLogs);

        // Write JSON to the file
        try (FileWriter fileWriter = new FileWriter(fileName)) {
            fileWriter.write(jsonObject.toJSONString());
            fileWriter.flush();
            System.out.println("JSON file written successfully!");
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }
}
