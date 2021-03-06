package com.egnyte.utils.auditreporter.view;

import com.egnyte.utils.auditreporter.domain.FileInfo;
import com.egnyte.utils.auditreporter.domain.UserFiles;

import java.io.IOException;
import java.io.OutputStreamWriter;

/**
 * Daneel Yaitskov
 */
public class PlainView implements View {
    private final OutputStreamWriter out;

    public PlainView(OutputStreamWriter out) {
        this.out = out;
    }

    public void header(String title) throws IOException {
        out.write(title + "\n");
        out.write("============\n");
    }

    public void section(UserFiles userFiles) throws IOException {
        out.write("## User: " + userFiles.user.login + "\n");
        for (FileInfo fileInfo : userFiles.getFiles()) {
            out.write("* " + fileInfo.name + " ==> " + fileInfo.size + " bytes\n");
        }
    }
}
