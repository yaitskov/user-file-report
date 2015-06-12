package com.egnyte.utils.auditreporter.domain;

/**
 * Daneel Yaitskov
 */
public class FileInfo {
    public final int owner;
    public final String name;
    public final long size;

    public FileInfo(int owner, String name, long size) {
        this.owner = owner;
        this.name = name;
        this.size = size;
    }
}
