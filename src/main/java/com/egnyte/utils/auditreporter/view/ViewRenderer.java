package com.egnyte.utils.auditreporter.view;

import com.egnyte.utils.auditreporter.domain.UserFiles;
import com.egnyte.utils.auditreporter.report.Report;

import java.io.IOException;
import java.util.List;

/**
 * Daneel Yaitskov
 */
public class ViewRenderer {
    private final View view;

    public ViewRenderer(View view) {
        this.view = view;
    }

    public void render(String report, List<UserFiles> data) throws IOException {
        view.header(report);
        for (UserFiles userFiles : data) {
            view.section(userFiles);
        }
    }
}
