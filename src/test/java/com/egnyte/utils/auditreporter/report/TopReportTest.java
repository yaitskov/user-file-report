package com.egnyte.utils.auditreporter.report;

import com.egnyte.utils.auditreporter.domain.FileInfo;
import com.egnyte.utils.auditreporter.domain.User;
import com.egnyte.utils.auditreporter.domain.UserFiles;
import org.junit.Test;
import org.unitils.reflectionassert.ReflectionAssert;

import java.util.Arrays;
import java.util.Collections;

/**
 * Daneel Yaitskov
 */
public class TopReportTest {
    private static final int UID = 1;
    private static final FileInfo BIGGEST = new FileInfo(UID, "biggest1", 200L);
    private static final User USER = new User(UID, "login");

    @Test
    public void moreThanN() {
        TopReport r = new TopReport(1);
        ReflectionAssert.assertReflectionEquals(
                Arrays.asList(new UserFiles(USER,
                        Arrays.asList(BIGGEST))),
                r.build(Collections.singletonList(USER),
                        Arrays.asList(
                                new FileInfo(UID, "small", 2L),
                                BIGGEST,
                                new FileInfo(UID, "medium", 20L),
                                new FileInfo(UID-2, "orphan", BIGGEST.size * 2))));
    }
}
