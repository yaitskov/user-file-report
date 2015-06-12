package com.egnyte.utils.auditreporter.view;

import com.egnyte.utils.auditreporter.domain.FileInfo;
import com.egnyte.utils.auditreporter.domain.User;
import com.google.common.collect.Multimap;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.IOException;
import java.io.OutputStreamWriter;

/**
 * Daneel Yaitskov
 */
public class JoinCsvView implements View {
    private final CSVFormat format;
    private final OutputStreamWriter out;

    public JoinCsvView(CSVFormat format, OutputStreamWriter out) {
        this.format = format;
        this.out = out;
    }

    public void call(Multimap<User, FileInfo> data) throws IOException {
        CSVPrinter printer = new CSVPrinter(out, format);
        for (User user : data.keySet()) {
            for (FileInfo fileInfo : data.get(user)) {
                printer.printRecord(fileInfo.name, user.login, fileInfo.size);
            }
        }
    }
}
