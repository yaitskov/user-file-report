package com.egnyte.utils.auditreporter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * Daneel Yaitskov
 */
public class CsvLoader {
    private static final Logger logger = LoggerFactory.getLogger(CsvLoader.class);

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
        logger.info("Loaded {} tuples from [{}].", output.size(), file);
    }
}
