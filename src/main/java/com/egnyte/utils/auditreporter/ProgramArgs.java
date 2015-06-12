package com.egnyte.utils.auditreporter;

import com.egnyte.utils.auditreporter.view.View;

/**
 * Daneel Yaitskov
 */
public class ProgramArgs {
    public final String usersFile;
    public final String filesFile;
    public final View view;

    public ProgramArgs(String usersFile, String filesFile, View view) {
        this.usersFile = usersFile;
        this.filesFile = filesFile;
        this.view = view;
    }
}
