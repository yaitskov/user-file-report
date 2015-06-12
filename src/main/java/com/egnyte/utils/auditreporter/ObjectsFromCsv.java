package com.egnyte.utils.auditreporter;

import com.google.common.base.Function;
import com.google.common.collect.Lists;

import java.io.IOException;
import java.util.List;

/**
 * Daneel Yaitskov
 */
public class ObjectsFromCsv<T> implements Function<String, List<T>> {
    private final CsvLoader lineLoader;
    private final Function<List<String>, T> tupleToPojo;

    public ObjectsFromCsv(CsvLoader lineLoader, Function<List<String>, T> tupleToPojo) {
        this.lineLoader = lineLoader;
        this.tupleToPojo = tupleToPojo;
    }

    public List<T> apply(String fileName) {
        List<List<String>> tuples = Lists.newArrayList();
        try {
            lineLoader.loadTuples(fileName, tuples);
        } catch (IOException e) {
            throw new RuntimeIoException(e);
        }
        return Lists.transform(tuples, tupleToPojo);
    }
}
