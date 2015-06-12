package com.egnyte.utils.auditreporter.view;

import com.egnyte.utils.auditreporter.Procedure;
import com.egnyte.utils.auditreporter.domain.FileInfo;
import com.egnyte.utils.auditreporter.domain.User;
import com.google.common.collect.Multimap;

/**
 * Daneel Yaitskov
 */
public interface View extends Procedure<Multimap<User, FileInfo>> {
}
