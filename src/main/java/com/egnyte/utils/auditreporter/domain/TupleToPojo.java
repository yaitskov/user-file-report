package com.egnyte.utils.auditreporter.domain;

import com.google.common.base.Function;

import java.util.List;

/**
 * Daneel Yaitskov
 */
public interface TupleToPojo<T> extends Function<List<String>, T> {
    T apply(List<String> tuple);
}
