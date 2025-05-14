package com.dbapplication.service;

import com.opencsv.CSVWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

public class CsvService {
    private static CSVWriter csvWriter;

    private CsvService() {}

    /**
     * Inițializează CSVWriter-ul dacă nu a fost deja creat.
     * @param outputFilePath calea fișierului CSV în care vom scrie
     */
    public static void init(String outputFilePath) throws IOException {
        if (csvWriter == null) {
            Writer fileWriter = new FileWriter(outputFilePath, /* append = */ true);
            csvWriter = new CSVWriter(
                    fileWriter,
                    CSVWriter.DEFAULT_SEPARATOR,      // separator implicit: ','
                    CSVWriter.DEFAULT_QUOTE_CHARACTER, // quote: '"'
                    CSVWriter.DEFAULT_ESCAPE_CHARACTER, // escape: '\\'
                    CSVWriter.DEFAULT_LINE_END         // line end: '\n'
            );
        }
    }

    /**
     * Scrie o linie de date în CSV.
     */
    public static void writeNext(String[] entries) {
        if (csvWriter == null) {
            throw new IllegalStateException("CSVWriter not initialized. Call init() first.");
        }
        csvWriter.writeNext(entries);
    }

    /**
     * Închide writer-ul și eliberează resursele.
     */
    public static void close() throws IOException {
        if (csvWriter != null) {
            csvWriter.close();
            csvWriter = null;
        }
    }
}
