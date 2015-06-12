package com.egnyte.utils.auditreporter;

import com.egnyte.utils.auditreporter.report.GroupReport;
import com.egnyte.utils.auditreporter.report.Report;
import com.egnyte.utils.auditreporter.report.TopReport;
import com.egnyte.utils.auditreporter.view.CsvView;
import com.egnyte.utils.auditreporter.view.PlainView;
import com.egnyte.utils.auditreporter.view.TopPlainView;
import com.egnyte.utils.auditreporter.view.View;
import com.egnyte.utils.auditreporter.view.ViewRenderer;
import com.google.common.collect.ImmutableMap;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Daneel Yaitskov
 */
public class ProgramArgsParser {
    private static final Charset CS = Charset.forName("UTF-8");

    private String[] args;
    private int iArg;
    private Report report;
    private View view;
    private final Map<String, Runnable> options = new ImmutableMap
            .Builder<String, Runnable>()
            .put("--top", new Runnable() {
                public void run() {
                    if (view == null) {
                        view = new TopPlainView(getOut());
                    }
                    report = new TopReport(nextArgInt());
                }
            }).put("-c", new Runnable() {
                public void run() {
                    try {
                        view = new CsvView(new CSVPrinter(getOut(), CSVFormat.DEFAULT));
                    } catch (IOException e) {
                        throw new RuntimeIoException(e);
                    }
                }
            })
            .build();

    public ProgramArgsParser(String[] args) {
        this.args = args;
    }

    public ProgramArgs parse() {
        List<String> files = new ArrayList<>();
        while (iArg < args.length) {
            Runnable r = options.get(args[iArg++]);
            if (r == null) {
                if (files.size() == 2) {
                    usage();
                }
                files.add(args[iArg]);
            } else {
                r.run();
            }
        }
        if (view == null) {
            view = new PlainView(getOut());
        }
        if (report == null) {
            report = new GroupReport();
        }
        if (files.size() != 2) {
            usage();
        }
        final String usersFile = files.get(0);
        final String filesFile = files.get(1);
        return new ProgramArgs(usersFile, filesFile, new ViewRenderer(view), report);
    }

    private OutputStreamWriter getOut() {
        return new OutputStreamWriter(System.out, CS);
    }

    private static void usage() {
        System.err.println("Usage: uber.jar [ -c | --top N ] users.csv files.csv");
        System.exit(1);
    }

    private int nextArgInt() {
        return Integer.parseInt(args[iArg++]);
    }
}
