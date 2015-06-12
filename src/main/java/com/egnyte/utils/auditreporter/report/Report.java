package com.egnyte.utils.auditreporter.report;

import com.egnyte.utils.auditreporter.domain.FileInfo;
import com.egnyte.utils.auditreporter.domain.User;
import com.egnyte.utils.auditreporter.domain.UserFiles;

import java.util.List;

/**
 * Daneel Yaitskov
 */
public interface Report {
    List<UserFiles> build(List<User> users, List<FileInfo> files);
    String getName();
}
