package com.egnyte.utils.auditreporter;

import org.junit.Before;
import org.junit.Test;
import org.unitils.reflectionassert.ReflectionAssert;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Daneel Yaitskov
 */
public class CsvLoaderTest {
    CsvLoader loader;

    @Before
    public void setUp() {
        loader = new CsvLoader();
    }

    @Test
    public void loadTuples() throws IOException {
        List<List<String>> out = new ArrayList<>();
        loader.loadTuples(getClass().getResource("/users.csv").getFile(), out);
        ReflectionAssert.assertReflectionEquals(
                Arrays.asList(
                        Arrays.asList("1", "jpublic"),
                        Arrays.asList("2", "atester")),
                out);
    }
}
