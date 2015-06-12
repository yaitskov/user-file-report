package com.egnyte.utils.auditreporter;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * Daneel Yaitskov
 */
public class CsvLoader {
    public void loadTuples(String file, List<List<String>> output)
            throws IOException
    {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            reader.readLine(); // skip header
            String line;
            while ((line = reader.readLine()) != null) {
                output.add(Arrays.asList(line.split(",")));
            }
        }
    }
}
