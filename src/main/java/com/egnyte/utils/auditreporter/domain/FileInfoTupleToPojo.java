package com.egnyte.utils.auditreporter.domain;

import java.util.List;

/**
 * Daneel Yaitskov
 */
public class FileInfoTupleToPojo implements TupleToPojo<FileInfo> {
    public FileInfo apply(List<String> tuple) {
        return new FileInfo(Integer.parseInt(tuple.get(3)),
                tuple.get(2), Long.parseLong(tuple.get(1)));
    }
}
