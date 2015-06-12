package com.egnyte.utils.auditreporter.report;

import com.egnyte.utils.auditreporter.domain.FileInfo;
import com.egnyte.utils.auditreporter.domain.User;
import com.egnyte.utils.auditreporter.domain.UserFiles;
import org.junit.Test;
import org.unitils.reflectionassert.ReflectionAssert;

import java.util.Arrays;

/**
 * Daneel Yaitskov
 */
public class GroupReportTest {
    private static final int UID = 1;
    private static final FileInfo FILE3 = new FileInfo(UID, "medium", 20L);
    private static final FileInfo FILE1 = new FileInfo(UID, "small", 2L);
    private static final int UID2 = 2;
    private static final FileInfo FILE4 = new FileInfo(UID2, "uid2", 3L);
    private static final FileInfo FILE2 = new FileInfo(UID, "biggest1", 200L);
    private static final User USER = new User(UID, "login");
    private static final User USER2 = new User(UID2, "login2");

    @Test
    public void group() {
        GroupReport r = new GroupReport();
        ReflectionAssert.assertLenientEquals(
                Arrays.asList(new UserFiles(USER,
                                Arrays.asList(FILE1, FILE2, FILE3)),
                        new UserFiles(USER2,
                                Arrays.asList(FILE4))),
                r.build(Arrays.asList(USER, USER2),
                        Arrays.asList(FILE1, FILE2,
                                new FileInfo(UID - 3, "orphan", 20L),
                                FILE3, FILE4)));
    }
}
