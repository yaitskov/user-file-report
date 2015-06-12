package com.egnyte.utils.auditreporter.view;

import com.egnyte.utils.auditreporter.domain.FileInfo;
import com.egnyte.utils.auditreporter.domain.UserFiles;

import java.io.IOException;
import java.io.OutputStreamWriter;

/**
 * Daneel Yaitskov
 */
public class TopPlainView extends PlainView {
    private final OutputStreamWriter out;

    public TopPlainView(OutputStreamWriter out) {
        super(out);
        this.out = out;
    }

    public void header(String title) throws IOException {
        out.write(title + "\n");
        out.write("=============\n");
    }

    public void section(UserFiles userFiles) throws IOException {
        for (FileInfo fileInfo : userFiles.getFiles()) {
            out.write("* " + fileInfo.name + " ==> user "
                            + userFiles.user.login + ", "
                            + fileInfo.size + " bytes\n");
        }
    }
}
