package com.egnyte.utils.auditreporter.domain;

import java.util.List;

/**
 * Daneel Yaitskov
 */
public class UserTupleToPojo implements TupleToPojo<User> {
    public User apply(List<String> tuple) {
        return new User(Integer.parseInt(tuple.get(0)),
                tuple.get(1));
    }
}
