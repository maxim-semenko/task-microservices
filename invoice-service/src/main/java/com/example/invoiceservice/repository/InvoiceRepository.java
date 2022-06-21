package com.example.invoiceservice.repository;

import com.example.invoiceservice.entity.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * The Invoice repository for working with entity {@link Invoice}.
 *
 * @author Maxim Semenko
 * @version 0.0.1
 */
public interface InvoiceRepository extends JpaRepository<Invoice, Long> {

    /**
     * Method that gets user's balance by their id.
     *
     * @param userId the user's id {@link Long}
     * @return the user's balance {@link Double}
     */
    @Query("select sum(invoice.amountOfMoney) " +
            "from Invoice invoice " +
            "where invoice.userId=:userId")
    Double getBalanceByUserId(@Param("userId") Long userId);

    List<Invoice> findAllByUserIdOrderByBetTimeStamp(Long userId);

    @Query("select invoice " +
            "from Invoice invoice " +
            "where invoice.userId=:userId " +
            "order by invoice.betTimeStamp")
    List<Invoice> findAllByUserId(Long userId);

}
