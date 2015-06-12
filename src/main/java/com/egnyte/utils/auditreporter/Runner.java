package com.egnyte.utils.auditreporter;

import com.egnyte.utils.auditreporter.domain.FileInfoTupleToPojo;
import com.egnyte.utils.auditreporter.domain.UserTupleToPojo;
import com.egnyte.utils.auditreporter.view.PlainView;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;

public class Runner {
    private static final Charset CS = Charset.forName("UTF-8");

    public static void main(String[] args) throws IOException {
        final String usersFile = args[0];
        final String filesFile = args[0];
        final CsvLoader loader = new CsvLoader();
        new PlainView(new OutputStreamWriter(System.out, CS))
                .render(new GroupFilesByUser().group(
                        new ObjectsFromCsv<>(loader, new UserTupleToPojo())
                                .apply(usersFile),
                        new ObjectsFromCsv<>(loader, new FileInfoTupleToPojo())
                                .apply(filesFile)));
    }
}
