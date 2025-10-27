package org.example.task4;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Task4 {

    public static void main(String[] args) {
        String url = "https://kpi.ua/";
        Map<String, Integer> tagCounts = new HashMap<>();

        try {
            HttpResponse<String> response;
            try (HttpClient client = HttpClient.newHttpClient()) {
                HttpRequest request = HttpRequest.newBuilder()
                        .uri(URI.create(url))
                        .build();

                response = client.send(request, HttpResponse.BodyHandlers.ofString());
            }
            String html = response.body();

            Pattern tagPattern = Pattern.compile("<([a-zA-Z0-9]+)");
            Matcher matcher = tagPattern.matcher(html);

            while (matcher.find()) {
                String tagName = matcher.group(1).toLowerCase();
                tagCounts.put(tagName, tagCounts.getOrDefault(tagName, 0) + 1);
            }

            System.out.println("Tag counts for URL: " + url + "\n");

            System.out.println("--- Sorted by tag name (A-Z) ---");
            Map<String, Integer> sortedByName = new TreeMap<>(tagCounts);
            for (Map.Entry<String, Integer> entry : sortedByName.entrySet()) {
                System.out.printf("%-10s: %d%n", entry.getKey(), entry.getValue());
            }

            System.out.println("\n--- Sorted by frequency (ascending) ---");
            Map<String, Integer> sortedByFrequency = tagCounts.entrySet()
                    .stream()
                    .sorted(Map.Entry.comparingByValue())
                    .collect(Collectors.toMap(
                            Map.Entry::getKey,
                            Map.Entry::getValue,
                            (e1, e2) -> e1,
                            LinkedHashMap::new
                    ));

            for (Map.Entry<String, Integer> entry : sortedByFrequency.entrySet()) {
                System.out.printf("%-10s: %d%n", entry.getKey(), entry.getValue());
            }

        } catch (Exception e) {
            System.err.println("Error while fetching or processing URL: " + e.getMessage());
        }
    }
}
