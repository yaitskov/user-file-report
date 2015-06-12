package com.egnyte.utils.auditreporter.view;

import com.egnyte.utils.auditreporter.domain.FileInfo;
import com.egnyte.utils.auditreporter.domain.User;
import com.google.common.collect.Multimap;

/**
 * Daneel Yaitskov
 */
public class PlainView {
    public void render(Multimap<User, FileInfo> data) {
        printHeader();
        for (User user : data.keySet()) {
            printUserHeader(user.login);
            for (FileInfo fileInfo : data.get(user)) {
                printFile(fileInfo.name, fileInfo.size);
            }
        }
    }

    private void printHeader() {
        System.out.println("Audit Report");
        System.out.println("============");
    }

    private void printUserHeader(String userName) {
        System.out.println("## User: " + userName);
    }

    private void printFile(String fileName, long fileSize) {
        System.out.println("* " + fileName + " ==> " + fileSize + " bytes");
    }
}
