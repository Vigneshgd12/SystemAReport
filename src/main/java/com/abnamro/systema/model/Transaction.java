package com.abnamro.systema.model;


import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Transaction {
    private String recordCode;
    private String clientType;
    private String clientNumber;
    private String accountNumber;
    private String subAccountNumber;
    private String oppositePartyCode;
    private String productGroupCode;
    private String exchangeCode;
    private String symbol;
    private String expirationDate;
    private String currencyCode;
    private String movementCode;
    private String buySellCode;
    private String quantityLongSign;
    private String quantityLong;
    private String quantityShortSign;
    private String quantityShort;
    private String exchangeBrokerFeeDEC;
    private String exchangeBrokerFeeDC;
    private String exchangeBrokerFeeCurCode;
    private String clearingFeeDEC;
    private String clearingFeeDC;
    private String clearingFeeCurCode;
    private String commission;
    private String commissionDC;
    private String commissionCurCode;
    private String transactionDate;
    private String futureReference;
    private String ticketNumber;
    private String externalNumber;
    private String transactionPriceDEC;
    private String traderInitials;
    private String oppositeTraderId;
    private String openCloseCode;
    private String filler;

}
