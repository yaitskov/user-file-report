package com.egnyte.utils.auditreporter;

import com.egnyte.utils.auditreporter.domain.FileInfoTupleToPojo;
import com.egnyte.utils.auditreporter.domain.UserTupleToPojo;

import java.io.IOException;

public class EntryPoint {
    public static void main(String[] args) throws IOException {
        ProgramArgs programArguments = ProgramArgs.parseCmdline(args);
        final CsvLoader loader = new CsvLoader();
        programArguments.view.render(new GroupFilesByUser().group(
                new ObjectsFromCsv<>(loader, new UserTupleToPojo())
                        .apply(programArguments.usersFile),
                new ObjectsFromCsv<>(loader, new FileInfoTupleToPojo())
                        .apply(programArguments.filesFile)));
    }
}
