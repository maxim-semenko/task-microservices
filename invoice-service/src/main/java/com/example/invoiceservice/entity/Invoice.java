package com.example.invoiceservice.entity;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.UUID;

/**
 * The entity Invoice.
 *
 * @author Maxim Semenko
 * @version 0.0.1
 */
@Entity
@Table(name = "invoices")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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

    @Enumerated(EnumType.STRING)
    @NotNull
    private BetTypeEnum type;

    @Override
    public String toString() {
        return "Invoice{" +
                ", betUUID=" + betUUID +
                ", previousBetUUID=" + previousBetUUID +
                ", userId=" + userId +
                ", amountOfMoney=" + amountOfMoney +
                ", betTimeStamp=" + betTimeStamp +
                '}';
    }
}
