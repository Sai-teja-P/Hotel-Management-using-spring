package com.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.entities.FoodOrder;

@Repository
public class FoodOrderDao {

  @Autowired
  EntityManagerFactory emf;

  @Autowired
  EntityManager em;
  
  @Autowired
  EntityTransaction et;
 

   public void saveFoodOrder( FoodOrder foodorder)
   {
     et.begin();
       em.persist(foodorder);
       et.commit();


   }
   public void updateFoodOrder(FoodOrder foodorder)
   {
     et.begin();
        em.merge(foodorder);
        et.commit();
   }
    public void deleteById(int id)
    {
      et.begin();
        em.remove(em.find(FoodOrder.class,id));
        et.commit();
    }
     public FoodOrder findbyId(int id)
     {
        return  em.find(FoodOrder.class, id);

     }
}
