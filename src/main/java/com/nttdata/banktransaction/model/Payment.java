package com.nttdata.banktransaction.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * This class defines the model of payments
 *
 * @author Alcibar Vasquez
 * @version 1.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "payments")
public class Payment {

    @Id
    private String id;

    @Field(name = "amount", write = Field.Write.NON_NULL)
    private Float amount;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Field(name = "payment_date")
    private Date paymentDate;

    public Payment(Float amount, Date paymentDate) {
        this.amount = amount;
        this.paymentDate = paymentDate;
    }
}
