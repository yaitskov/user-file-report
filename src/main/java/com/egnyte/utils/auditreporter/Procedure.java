package com.egnyte.utils.auditreporter;

import java.io.IOException;

/**
 * Daneel Yaitskov
 */
public interface Procedure<T> {
    void call(T a) throws IOException;
}
