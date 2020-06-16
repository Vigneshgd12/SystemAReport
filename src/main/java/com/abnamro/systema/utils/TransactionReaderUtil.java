package com.abnamro.systema.utils;

import com.abnamro.systema.model.Transaction;

import java.io.BufferedReader;
import java.io.StringReader;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class TransactionReaderUtil {


    public static String oneLineTransaction = "315CL  432100020001SGXDC FUSGX NK    20100910JPY01B 0000000001 0000000000000000000060DUSD000000000030DUSD000000000000DJPY201008200012380     688032000092500000000             O";


    public static List<Transaction> getTransactions(List<String> transactionLines){
        return transactionLines.stream()
                .map(transactionLine -> readTransactionFromLines(transactionLine))
                .collect(Collectors.toList());
    }


    private static Transaction readTransactionFromLines(String transactionLine){
        return Transaction.builder()
                .recordCode(transactionLine.substring(0,3).trim())
                .clientType(transactionLine.substring(3,7).trim())
                .clientNumber(transactionLine.substring(7,11).trim())
                .accountNumber(transactionLine.substring(11,15).trim())
                .subAccountNumber(transactionLine.substring(15,19).trim())
                .oppositePartyCode(transactionLine.substring(19,25).trim())
                .productGroupCode(transactionLine.substring(25,27).trim())
                .exchangeCode(transactionLine.substring(27,31).trim())
                .symbol(transactionLine.substring(31,37).trim())
                .expirationDate(transactionLine.substring(37,45).trim())
                .currencyCode(transactionLine.substring(45,48).trim())
                .movementCode(transactionLine.substring(48,50).trim())
                .buySellCode(transactionLine.substring(50,51).trim())
                .quantityLongSign(transactionLine.substring(51,52).trim())
                .quantityLong(transactionLine.substring(52,62).trim())
                .quantityShortSign(transactionLine.substring(62,63).trim())
                .quantityShort(transactionLine.substring(63,73).trim())
                .exchangeBrokerFeeDEC(transactionLine.substring(73,85).trim())
                .exchangeBrokerFeeDC(transactionLine.substring(85,86).trim())
                .exchangeBrokerFeeCurCode(transactionLine.substring(86,89).trim())
                .clearingFeeDEC(transactionLine.substring(89,101).trim())
                .clearingFeeDC(transactionLine.substring(101,102).trim())
                .clearingFeeCurCode(transactionLine.substring(102,105).trim())
                .commission(transactionLine.substring(105,117).trim())
                .commissionDC(transactionLine.substring(117,118).trim())
                .commissionCurCode(transactionLine.substring(118,121).trim())
                .transactionDate(transactionLine.substring(121,129).trim())
                .futureReference(transactionLine.substring(129,135).trim())
                .ticketNumber(transactionLine.substring(135,141).trim())
                .externalNumber(transactionLine.substring(141,147).trim())
                .transactionPriceDEC(transactionLine.substring(147,162).trim())
                .traderInitials(transactionLine.substring(162,168).trim())
                .oppositeTraderId(transactionLine.substring(168,175).trim())
                .openCloseCode(transactionLine.substring(175,176).trim())
                .filler(transactionLine.length()>177 ? transactionLine.substring(176,303).trim():null)
                .build();
    }

}
