package com.egnyte.utils.auditreporter;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Runner {
    private final List<List<String>> users = new ArrayList<>();
    private final List<List<String>> files = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        Runner r = new Runner();
        r.loadData(args[0], args[1]);
        r.run();
    }

    private void run() {
        printHeader();
        for (List<String> userRow : users) {
            long userId = Long.parseLong(userRow.get(0));
            String userName = userRow.get(1);

            printUserHeader(userName);
            for (List<String> fileRow : files) {
                String fileId = fileRow.get(0);
                long size = Long.parseLong(fileRow.get(1));
                String fileName = fileRow.get(2);
                long ownerUserId = Long.parseLong(fileRow.get(3));
                if (ownerUserId == userId) {
                    printFile(fileName, size);
                }
            }
        }
    }

    private void loadData(String userFn, String filesFn) throws IOException {
        loadCsvFile(userFn, users);
        loadCsvFile(filesFn, files);
    }

    private void loadCsvFile(String file, List<List<String>> output)
            throws IOException
    {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            reader.readLine(); // skip header
            String line;
            while ((line = reader.readLine()) != null) {
                output.add(Arrays.asList(line.split(",")));
            }
        }
    }

    private void printHeader() {
        System.out.println("Audit Report");
        System.out.println("============");
    }

    private void printUserHeader(String userName) {
        System.out.println("## User: " + userName);
    }

    private void printFile(String fileName, long fileSize) {
        System.out.println("* " + fileName + " ==> " + fileSize + " bytes");
    }

}
