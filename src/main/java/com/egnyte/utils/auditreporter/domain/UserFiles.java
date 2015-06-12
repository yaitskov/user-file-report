package com.egnyte.utils.auditreporter.domain;

import java.util.List;

/**
 * Daneel Yaitskov
 */
public class UserFiles {
    public final User user;
    private final List<FileInfo> files;

    public UserFiles(User user, List<FileInfo> files) {
        this.user = user;
        this.files = files;
    }

    public List<FileInfo> getFiles() {
        return files;
    }
}
