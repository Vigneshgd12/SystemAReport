package com.abnamro.systema.utils;

import com.abnamro.systema.model.Report;
import com.abnamro.systema.model.Transaction;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ReportUtil {

    public static List<Report> getDailyReport(List<Transaction> transactionDetails){
        List<Report> reports =  transactionDetails.stream()
                .map(transaction -> getProductReport(transaction))
                .collect(Collectors.toList());
        return reports;
    }

    private static Report getProductReport(Transaction transaction){
        return Report.builder()
                .clientInformation(getClientInformation(transaction))
                .productInformation(getProductInformation(transaction))
                .totalTransactionAmount(getTransactionAmount(transaction))
                .build();
    }


    private static String getClientInformation(Transaction transaction){
        return String.join("_",transaction.getClientType(),transaction.getClientNumber(),transaction.getAccountNumber(),transaction.getSubAccountNumber());
    }


    private static String getProductInformation(Transaction transaction){
        return String.join("_",transaction.getExchangeCode(),transaction.getProductGroupCode(),transaction.getSymbol(),transaction.getExpirationDate());
    }


    private static double getTransactionAmount(Transaction transaction){
        return Double.valueOf(transaction.getQuantityLong()) - Double.valueOf(transaction.getQuantityShort());

    }


    public static List<Report> getFinalReport(List<Transaction> transactionDetails){
        List<Report> finalReports = new ArrayList<>();
        List<Report> reports = getDailyReport(transactionDetails);

        reports.stream().forEach(report -> {
            Optional<Report> existingReport = finalReports.stream()
                    .filter(
                            finalReport->
                                    finalReport.getClientInformation().equals(report.getClientInformation())
                                            && finalReport.getProductInformation().equals(report.getProductInformation())
                    ).findFirst();
            if(existingReport.isEmpty()) {
                finalReports.add(report);
            }else{
                existingReport.get().setTotalTransactionAmount(existingReport.get().getTotalTransactionAmount()+ report.getTotalTransactionAmount());
            }
        });
        return finalReports;
    }
}
