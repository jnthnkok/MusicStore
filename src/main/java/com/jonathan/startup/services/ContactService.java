package com.jonathan.startup.services;

import com.jonathan.startup.domain.Customer;
import com.jonathan.startup.domain.CustomerAddress;

/**
 *
 * @author Jonathan Kok
 */
public interface ContactService extends Services<Customer, Long>{
    public CustomerAddress getAddress(String customerNumber);
    public String getCellphoneNumber(String customerNumber); 
    public CustomerAddress  persistAddress(CustomerAddress address); 
}
