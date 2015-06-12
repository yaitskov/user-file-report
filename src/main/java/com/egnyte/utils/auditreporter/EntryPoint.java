package com.egnyte.utils.auditreporter;

import com.egnyte.utils.auditreporter.domain.FileInfoTupleToPojo;
import com.egnyte.utils.auditreporter.domain.UserTupleToPojo;

import java.io.IOException;

public class EntryPoint {
    public static void main(String[] args) throws IOException {
        ProgramArgs programArguments = new ProgramArgsParser(args).parse();
        final CsvLoader loader = new CsvLoader();
        programArguments.viewRenderer.render(
                programArguments.report.getName(),
                programArguments.report.build(
                        new ObjectsFromCsv<>(loader, new UserTupleToPojo())
                                .apply(programArguments.usersFile),
                        new ObjectsFromCsv<>(loader, new FileInfoTupleToPojo())
                                .apply(programArguments.filesFile)));
        programArguments.out.close();
    }
}
