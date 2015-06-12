package com.egnyte.utils.auditreporter;

import com.egnyte.utils.auditreporter.domain.FileInfo;
import com.egnyte.utils.auditreporter.domain.User;
import org.junit.Test;
import org.unitils.reflectionassert.ReflectionAssert;

import java.util.Arrays;

/**
 * Daneel Yaitskov
 */
public class GroupFilesByUserTest {
    private static final int UID1 = 1;
    private static final int UID2 = 2;
    private static final String LOGIN1 = "login1";
    private static final User USER1 = new User(UID1, LOGIN1);
    private static final String FILE_NAME1 = "file1";
    private static final long SIZE1 = 123L;
    private static final String FILE_NAME2 = "file2";
    private static final long SIZE2 = 321L;
    private static final String FILE_NAME3 = "file3";
    private static final long SIZE3 = 777L;
    private static final FileInfo FILE1 = new FileInfo(UID1, FILE_NAME1, SIZE1);
    private static final FileInfo FILE2 = new FileInfo(UID1, FILE_NAME2, SIZE2);
    private static final FileInfo FILE3 = new FileInfo(UID2, FILE_NAME3, SIZE3);

    @Test
    public void groupOneUserManyFiles() {
        ReflectionAssert.assertLenientEquals(
                Arrays.asList(FILE1, FILE2),
                new GroupFilesByUser().group(Arrays.asList(USER1),
                        Arrays.asList(FILE1, FILE2)).get(USER1));
    }

    @Test
    public void groupOrphanFile() {
        ReflectionAssert.assertReflectionEquals(
                Arrays.asList(FILE1),
                new GroupFilesByUser().group(Arrays.asList(USER1),
                        Arrays.asList(FILE1, FILE3)).get(USER1));
    }
}
