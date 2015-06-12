package com.egnyte.utils.auditreporter;

import com.egnyte.utils.auditreporter.view.JoinCsvView;
import com.egnyte.utils.auditreporter.view.PlainView;
import com.egnyte.utils.auditreporter.view.View;
import org.apache.commons.csv.CSVFormat;

import java.io.OutputStreamWriter;
import java.nio.charset.Charset;

/**
 * Daneel Yaitskov
 */
public class ProgramArgsParser {
    private static final Charset CS = Charset.forName("UTF-8");
    private static final String CSV_JOIN = "-c";
    private int firstFileArg;

    public ProgramArgs parse(String[] args) {
        firstFileArg = 0;
        if (args.length < 2) {
            usage();
        }
        View view = getView(args);
        if (firstFileArg + 2 != args.length) {
            usage();
        }
        final String usersFile = args[firstFileArg];
        final String filesFile = args[firstFileArg + 1];
        return new ProgramArgs(usersFile, filesFile, view);
    }

    private View getView(String[] args) {
        if (args[firstFileArg].equals(CSV_JOIN)) {
            ++firstFileArg;
            return new JoinCsvView(CSVFormat.DEFAULT, getOut());
        } else {
            return new PlainView(getOut());
        }
    }

    private OutputStreamWriter getOut() {
        return new OutputStreamWriter(System.out, CS);
    }

    private static void usage() {
        System.err.println("Usage: uber.jar [ -c ] users.csv files.csv");
        System.exit(1);
    }
}
