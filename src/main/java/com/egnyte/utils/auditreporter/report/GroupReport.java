package com.egnyte.utils.auditreporter.report;

import com.egnyte.utils.auditreporter.domain.FileInfo;
import com.egnyte.utils.auditreporter.domain.User;
import com.egnyte.utils.auditreporter.domain.UserFiles;
import com.egnyte.utils.auditreporter.domain.UserIdFun;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.ListMultimap;
import com.google.common.collect.Maps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Daneel Yaitskov
 */
public class GroupReport implements Report {
    private static final Logger logger = LoggerFactory.getLogger(GroupReport.class);

    public List<UserFiles> build(List<User> users, List<FileInfo> files) {
        final List<UserFiles> result = new ArrayList<>(users.size());
        Map<Integer, User> usersById = Maps.uniqueIndex(users, new UserIdFun());
        ListMultimap<User, FileInfo> filesByUser = ArrayListMultimap.create();
        for (FileInfo fileInfo : files) {
            User user = usersById.get(fileInfo.owner);
            if (user == null) {
                logger.warn("Orphan file [{}] of {}.", fileInfo.name, fileInfo.owner);
                continue;
            }
            filesByUser.put(user, fileInfo);
        }
        for (User user : filesByUser.keySet()) {
            result.add(new UserFiles(user, filesByUser.get(user)));
        }
        return result;
    }

    public String getName() {
        return "Audit Report";
    }
}
