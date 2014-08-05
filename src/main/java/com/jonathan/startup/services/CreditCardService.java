package com.jonathan.startup.services;

import com.jonathan.startup.domain.CreditCard;
import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author hashcode
 */
public interface CreditCardService extends Services<CreditCard, Long>{
     public String processPayment(String number, BigDecimal amount,Date expiryDate);
}
