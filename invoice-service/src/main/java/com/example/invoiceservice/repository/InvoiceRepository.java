package com.example.invoiceservice.repository;

import com.example.invoiceservice.entity.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface InvoiceRepository extends JpaRepository<Invoice, Long> {

    @Query("select sum(invoice.amountOfMoney) " +
            "from Invoice invoice " +
            "where invoice.userId=:userId")
    Double getBalanceByUserId(@Param("userId") Long userId);
}
