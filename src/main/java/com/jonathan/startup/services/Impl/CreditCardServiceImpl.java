package com.jonathan.startup.services.Impl;

import com.jonathan.startup.exceptions.CreditCardRejectionException;
import com.jonathan.startup.domain.CreditCard;
import com.jonathan.startup.repository.CreditCardRepository;
import com.jonathan.startup.services.CreditCardService;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author hashcode
 */

@Service
public class CreditCardServiceImpl implements CreditCardService {

    @Autowired
    private CreditCardRepository creditCardRepository;

    @Override
    public String processPayment(String number, BigDecimal amount, Date expiryDate) {
        //Search Credit card By creditNumber property on The Entity to Find if it is in the System
        CreditCard creditCard = getCreditCardByNumber(number);
        //Test if we have a credit card. 
        // If Not Jump to the Last ELSE and throw CC Rejection  Exception --Wrong Number 
        if (creditCard != null) {
            // If we have a Card Check For sufficient Funds
            //If Less Funds Throw Exception ELSE APPROVE
            if (amount.doubleValue() > creditCard.getBalance().doubleValue()) {
                throw new CreditCardRejectionException("Insufficient Funds In the Account");
            } else if (expiryDate.after(creditCard.getExpiryDate())) {
                //IF expiry Date is Before Credit card EXPIRY DATE Throw Exception
                throw new CreditCardRejectionException("Sorry Credit Date Expired");
            } else {
                return "APPROVED";
            }
        } else {
            throw new CreditCardRejectionException("Credit Number is Wrong");
        }
    }

    @Override
    public CreditCard find(Long id) {
        return creditCardRepository.findOne(id); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public CreditCard persist(CreditCard entity) {
        return creditCardRepository.save(entity); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public CreditCard merge(CreditCard entity) {
        if (entity.getId() != null) {
            return creditCardRepository.save(entity);
        }
        return null; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void remove(CreditCard entity) {
        creditCardRepository.delete(entity); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<CreditCard> findAll() {
        return creditCardRepository.findAll(); //To change body of generated methods, choose Tools | Templates.
    }
    private CreditCard getCreditCardByNumber(String number) {
        CreditCard card = null;
        List<CreditCard> creditCards = findAll();
        for (CreditCard creditCard : creditCards) {
            if (number.equalsIgnoreCase(creditCard.getCreditNumber())) {
                card = creditCard;
            }
        }
        return card;
    }
}
