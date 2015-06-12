package com.egnyte.utils.auditreporter.report;

import com.egnyte.utils.auditreporter.domain.FileInfo;
import com.egnyte.utils.auditreporter.domain.User;
import com.egnyte.utils.auditreporter.domain.UserFiles;
import com.egnyte.utils.auditreporter.domain.UserIdFun;
import com.google.common.collect.Maps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

/**
 * Daneel Yaitskov
 */
public class TopReport implements Report {
    private static final Logger logger = LoggerFactory.getLogger(TopReport.class);

    private final int limit;

    public TopReport(int limit) {
        this.limit = limit;
    }

    public List<UserFiles> build(List<User> users, List<FileInfo> files) {
        final List<UserFiles> result = new ArrayList<>(Math.min(limit, files.size()));
        final Set<FileInfo> sizeOrderedFiles = new TreeSet<>(new Comparator<FileInfo>() {
            public int compare(FileInfo o1, FileInfo o2) {
                return Long.compare(o2.size, o1.size);
            }
        });
        sizeOrderedFiles.addAll(files);
        Map<Integer, User> usersById = Maps.uniqueIndex(users, new UserIdFun());
        for (FileInfo fileInfo : sizeOrderedFiles) {
            User user = usersById.get(fileInfo.owner);
            if (user == null) {
                logger.warn("Orphan file [{}] of {}.", fileInfo.name, fileInfo.owner);
                continue;
            }
            result.add(new UserFiles(user, Collections.singletonList(fileInfo)));
            if (result.size() >= limit) {
                break;
            }
        }
        return result;
    }

    public String getName() {
        return "Top #" + limit + " Report";
    }
}
