package com.nttdata.banktransaction.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;

/**
 * This class defines the model of credits
 *
 * @author Alcibar Vasquez
 * @version 1.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "credits")
public class Credit {

    @Id
    private String id;

    @Field(name = "client_id", write = Field.Write.NON_NULL)
    private String clientId;
    @Field(name = "amount", write = Field.Write.NON_NULL)
    private Float amount;

    @Field(name = "fees_amount")
    private Float feesAmount;

    @Field(name = "fees_quantity")
    private Long feesQuantity;

    @Field(name = "expiration_date")
    private Date expirationDate;

    @Field(name = "credit_status")
    private String creditStatus;
    @Field(name = "created_at")
    private Date createdAt;

    @Field(name = "updated_at")
    private Date updatedAt;


    public Credit(String clientId, Float amount, Long feesQuantity, Date expirationDate,
                  String creditStatus, Date createdAt, Date updatedAt) {
        this.clientId = clientId;
        this.amount = amount;
        this.feesQuantity = feesQuantity;
        this.expirationDate = expirationDate;
        this.creditStatus = creditStatus;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
