package com.egnyte.utils.auditreporter.view;

import com.egnyte.utils.auditreporter.domain.FileInfo;
import com.egnyte.utils.auditreporter.domain.User;
import com.google.common.collect.Multimap;

import java.io.IOException;
import java.io.OutputStreamWriter;

/**
 * Daneel Yaitskov
 */
public class PlainView {
    private final OutputStreamWriter out;

    public PlainView(OutputStreamWriter out) {
        this.out = out;
    }

    public void render(Multimap<User, FileInfo> data) throws IOException {
        printHeader();
        for (User user : data.keySet()) {
            printUserHeader(user.login);
            for (FileInfo fileInfo : data.get(user)) {
                printFile(fileInfo.name, fileInfo.size);
            }
        }
    }

    private void printHeader() throws IOException {
        out.write("Audit Report\n");
        out.write("============\n");
    }

    private void printUserHeader(String userName) throws IOException {
        out.write("## User: " + userName + "\n");
    }

    private void printFile(String fileName, long fileSize) throws IOException {
        out.write("* " + fileName + " ==> " + fileSize + " bytes\n");
    }
}
