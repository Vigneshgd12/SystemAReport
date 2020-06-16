package com.abnamro.systema.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;


@Data
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Report {

    @JsonProperty("Client_Information")
    public String clientInformation;
    @JsonProperty("Product_Information")
    public String productInformation;
    @JsonProperty("Total_Transaction_Amount")
    public double totalTransactionAmount;
}
