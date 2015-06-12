package com.egnyte.utils.auditreporter;

import com.egnyte.utils.auditreporter.domain.FileInfo;
import com.egnyte.utils.auditreporter.domain.User;
import com.egnyte.utils.auditreporter.domain.UserIdFun;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Maps;
import com.google.common.collect.Multimap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;

/**
 * Daneel Yaitskov
 */
public class GroupFilesByUser {
    private static final Logger logger = LoggerFactory.getLogger(GroupFilesByUser.class);

    public Multimap<User, FileInfo> group(List<User> users, List<FileInfo> files) {
        final Multimap<User, FileInfo> result = HashMultimap.create();
        Map<Integer, User> usersById = Maps.uniqueIndex(users, new UserIdFun());
        for (FileInfo fileInfo : files) {
            User user = usersById.get(fileInfo.owner);
            if (user == null) {
                logger.warn("Orphan file [{}] of {}.", fileInfo.name, fileInfo.owner);
                continue;
            }
            result.put(user, fileInfo);
        }
        return result;
    }
}
