package com.engage.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by hasiermetal on 08/01/2018.
 */
@Entity
public class Expense implements Serializable {

    private static final long serialVersionUID = -3566147500066418359L;

    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date date;

    @NotNull
    private BigDecimal amount;

    @NotNull
    private String reason;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
