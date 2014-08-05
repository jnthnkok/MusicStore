package com.jonathan.startup.services.Impl;

import com.jonathan.startup.domain.Customer;
import com.jonathan.startup.domain.CustomerAddress;
import com.jonathan.startup.repository.CustomerAddressRepository;
import com.jonathan.startup.repository.CustomerRepository;
import com.jonathan.startup.services.ContactService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
    
/**
 *
 * @author Jonathan Kok
 */
@Service
public class ContactServiceImpl implements ContactService{

    @Autowired 
    private CustomerRepository customerRepository;
    
    @Autowired 
    private CustomerAddressRepository customerAddressRepository;
    
    //Get Address
    @Override
    public CustomerAddress getAddress(String customerNumber) {
        ArrayList<Customer> listCustomers = new ArrayList<Customer>();
        listCustomers = (ArrayList<Customer>) customerRepository.findAll();
        
        for(Customer c: listCustomers)
            if(c.getCustomerNumber().equalsIgnoreCase(customerNumber))
                return c.getCustomerAddress();
        
        return null;
    }

    //Get Cellphone Number
    @Override
    public String getCellphoneNumber(String customerNumber) {
        ArrayList<Customer> listCustomers = new ArrayList<Customer>();
        listCustomers = (ArrayList<Customer>) customerRepository.findAll();
        
        for(Customer c: listCustomers)
            if(c.getCustomerNumber().equalsIgnoreCase(customerNumber))
                return c.getContact().getCellNumber();   
        
        return null;
    }

    @Override
    public Customer find(Long id) {
      return customerRepository.findOne(id);
    }

    @Override
    public Customer persist(Customer entity) {
       return customerRepository.save(entity); 
    }

    @Override
    public Customer merge(Customer entity) {
       return customerRepository.save(entity);
    }

    @Override
    public void remove(Customer entity) {
       customerRepository.delete(entity);
    }

    @Override
    public List<Customer> findAll() {
        return customerRepository.findAll();
    } 

    @Override
    public CustomerAddress persistAddress(CustomerAddress address) {
        return customerAddressRepository.save(address);
   }
}
