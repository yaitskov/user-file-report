package com.egnyte.utils.auditreporter.view;

import com.egnyte.utils.auditreporter.domain.FileInfo;
import com.egnyte.utils.auditreporter.domain.User;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import org.junit.Test;
import org.unitils.reflectionassert.ReflectionAssert;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

/**
 * Daneel Yaitskov
 */
public class PlainViewTest {
    @Test
    public void render() throws IOException {
        ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
        OutputStreamWriter out = new OutputStreamWriter(byteOut);
        Multimap<User, FileInfo> data = ArrayListMultimap.create();
        data.put(new User(1, "name"), new FileInfo(1, "file", 123L));
        new PlainView(out).render(data);
        out.close();
        ReflectionAssert.assertReflectionEquals(
                "Audit Report\n"
                        + "============\n"
                        + "## User: name\n"
                        + "* file ==> 123 bytes\n",
                byteOut.toString());
    }
}
