package com.egnyte.utils.auditreporter.domain;

import com.google.common.base.Function;

/**
 * Daneel Yaitskov
 */
public class UserIdFun implements Function<User, Integer> {
    public Integer apply(User user) {
        return user.id;
    }
}
