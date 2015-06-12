package com.egnyte.utils.auditreporter;

import java.io.IOException;

/**
 * Daneel Yaitskov
 */
public class RuntimeIoException extends RuntimeException {
    public RuntimeIoException(IOException cause) {
        super(cause);
    }
}
