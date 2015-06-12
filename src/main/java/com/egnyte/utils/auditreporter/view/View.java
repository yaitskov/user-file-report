package com.egnyte.utils.auditreporter.view;

import com.egnyte.utils.auditreporter.domain.UserFiles;

import java.io.IOException;

/**
 * Daneel Yaitskov
 */
public interface View {
    void header(String title) throws IOException;
    void section(UserFiles userFiles) throws IOException;
}
