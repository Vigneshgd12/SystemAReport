package com.abnamro.systema.service;

import com.abnamro.systema.model.Report;
import com.abnamro.systema.model.Transaction;
import com.abnamro.systema.utils.ReportUtil;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;

import static com.abnamro.systema.utils.TransactionReaderUtil.getTransactions;

@Service
public class ReportService {


    private static final String CSV_SEPARATOR = ",";

    public List<Report> getDailyReport(MultipartFile inputFile) throws IOException {
        return ReportUtil.getDailyReport(getTransactionLines(inputFile));
    }


    public List<Report> getFinalReport(MultipartFile inputFile) throws IOException {
        return ReportUtil.getFinalReport(getTransactionLines(inputFile));
    }

    public File getDailyReportFile(MultipartFile inputFile, String fileName) throws IOException {
        return getReportFile(fileName, getDailyReport(inputFile));
    }

    public File getFinalReportFile(MultipartFile inputFile, String fileName) throws IOException {
        return getReportFile(fileName, getFinalReport(inputFile));

    }

    private File getReportFile(String fileName, List<Report> reports) {
        File reportFile = new File(fileName);

        try
        {
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(reportFile), "UTF-8"));
            StringBuffer oneLine = new StringBuffer();
            oneLine.append("Client_Information");
            oneLine.append(CSV_SEPARATOR);
            oneLine.append("Product_Information");
            oneLine.append(CSV_SEPARATOR);
            oneLine.append("Total_Transaction_Amount");
            bw.write(oneLine.toString());
            bw.newLine();
            for (Report report : reports)
            {
                oneLine = new StringBuffer();
                oneLine.append(report.getClientInformation());
                oneLine.append(CSV_SEPARATOR);
                oneLine.append(report.getProductInformation());
                oneLine.append(CSV_SEPARATOR);
                oneLine.append(report.getTotalTransactionAmount());
                bw.write(oneLine.toString());
                bw.newLine();
            }
            bw.flush();
            bw.close();
        }
        catch (UnsupportedEncodingException e) {}
        catch (FileNotFoundException e){}
        catch (IOException e){}
        return reportFile;
    }


    private List<Transaction> getTransactionLines(MultipartFile inputFile) throws IOException {
        List<String> transactionLines = new BufferedReader(new InputStreamReader(inputFile.getInputStream(), StandardCharsets.UTF_8))
                .lines()
                .collect(Collectors.toList());
        return getTransactions(transactionLines);
    }
}
