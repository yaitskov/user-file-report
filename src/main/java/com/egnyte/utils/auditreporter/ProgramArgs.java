package com.egnyte.utils.auditreporter;

import com.egnyte.utils.auditreporter.view.PlainView;

import java.io.OutputStreamWriter;
import java.nio.charset.Charset;

/**
 * Daneel Yaitskov
 */
public class ProgramArgs {
    private static final Charset CS = Charset.forName("UTF-8");

    public final String usersFile;
    public final String filesFile;
    public final PlainView view;

    public ProgramArgs(String usersFile, String filesFile, PlainView view) {
        this.usersFile = usersFile;
        this.filesFile = filesFile;
        this.view = view;
    }

    public static ProgramArgs parseCmdline(String[] args) {
        if (args.length < 2) {
            System.err.println("Usage: uber.jar users.csv files.csv");
            System.exit(1);
        }
        PlainView view = new PlainView(new OutputStreamWriter(System.out, CS));
        int firstFile = 0;
        if (firstFile + 2 != args.length) {
            System.err.println("Usage: uber.jar users.csv files.csv");
            System.exit(1);
        }
        final String usersFile = args[firstFile];
        final String filesFile = args[firstFile + 1];
        return new ProgramArgs(usersFile, filesFile, view);
    }
}
