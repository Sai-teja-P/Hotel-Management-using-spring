package com.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.entities.Customer;
import com.entities.FoodOrder;

@Repository
public class CustomerDao {
  @Autowired
  EntityManagerFactory emf;

  @Autowired
  EntityManager em;
  
  @Autowired
  EntityTransaction et;
 
  public void saveCustomer(Customer customer)
  {
	  et.begin();
      em.persist(customer);
      et.commit();
  }
  
  public Customer findCustomerById(int id){
    return em.find(Customer.class, id);
  }
  
  public void updateCustomer(Customer coustemer)
  {
    et.begin();
    em.merge(coustemer);
    et.commit();
  }
  
  public void deleteByid(int id)
  {
    et.begin();
    em.remove(em.find(Customer.class, id));
    et.commit();
  }
  public Customer login(String email,String password){
	  Query query=em.createQuery("select c from Customer c where c.email=?1 and password=?2");
      query.setParameter(1, email);
      query.setParameter(2, password);
      try {
        return (Customer) query.getSingleResult();
      }
      catch(NoResultException e){
        return null;
      }
   }

public FoodOrder getBillDetails(int id) {
	FoodOrder foodOrder = new FoodOrder();
	Customer customer = findCustomerById(id);
	foodOrder.setName(customer.getName());
	foodOrder.setAddress(customer.getAddress());
	foodOrder.setMobilenumber(customer.getMobileNumber());
	return foodOrder;
}
}
