package com.example.invoiceservice.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "invoices")
@Getter
@Setter
@EqualsAndHashCode
public class Invoice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private UUID betUUID;

    @NotNull
    private UUID previousBetUUID;

    @NotNull
    private Long userId;

    @NotNull
    private Double amountOfMoney;

    @NotNull
    private Date betTimeStamp;

    @Override
    public String toString() {
        return "Invoice{" +
                "id=" + id +
                ", betUUID=" + betUUID +
                ", previousBetUUID=" + previousBetUUID +
                ", userId=" + userId +
                ", amountOfMoney=" + amountOfMoney +
                ", betTimeStamp=" + betTimeStamp +
                '}';
    }
}
