package com.egnyte.utils.auditreporter.view;

import com.egnyte.utils.auditreporter.domain.FileInfo;
import com.egnyte.utils.auditreporter.domain.UserFiles;
import org.apache.commons.csv.CSVPrinter;

import java.io.IOException;

/**
 * Daneel Yaitskov
 */
public class CsvView implements View {
    private final CSVPrinter printer;

    public CsvView(CSVPrinter printer) {
        this.printer = printer;
    }

    public void header(String text) {
        // do nothing
    }

    public void section(UserFiles userFiles) throws IOException {
        for (FileInfo fileInfo : userFiles.getFiles()) {
            printer.printRecord(fileInfo.name, userFiles.user.login, fileInfo.size);
        }
    }
}
