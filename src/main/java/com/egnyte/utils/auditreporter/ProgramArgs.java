package com.egnyte.utils.auditreporter;

import com.egnyte.utils.auditreporter.report.Report;
import com.egnyte.utils.auditreporter.view.View;
import com.egnyte.utils.auditreporter.view.ViewRenderer;

import java.io.OutputStreamWriter;

/**
 * Daneel Yaitskov
 */
public class ProgramArgs {
    public final String usersFile;
    public final String filesFile;
    public final ViewRenderer viewRenderer;
    public final Report report;
    public final OutputStreamWriter out;

    public ProgramArgs(String usersFile, String filesFile,
                       ViewRenderer viewRenderer,
                       Report report, OutputStreamWriter out) {
        this.usersFile = usersFile;
        this.filesFile = filesFile;
        this.viewRenderer = viewRenderer;
        this.report = report;
        this.out = out;
    }
}
