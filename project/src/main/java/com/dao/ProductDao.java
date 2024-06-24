package com.dao;

import java.util.Collections;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.entities.Hotel;
import com.entities.Product;

@Repository
public class ProductDao {
  @Autowired
  EntityManagerFactory emf;

  @Autowired
  EntityManager em;
  
  @Autowired
  EntityTransaction et;

  public void saveproduct( Product product){
	   et.begin();
       em.persist(product);
       et.commit();
   }
  public void updateProduct(Product product){  
        et.begin();
        em.merge(product);
        et.commit();
   }
  public void deleteById(int id){
        et.begin();
        em.remove(em.find(Product.class,id));
        et.commit();
  }
   public Product findbyId(int id){
        return  em.find(Product.class, id);
   }
   public List<Product> findAll(){
         Query query = em.createQuery("select p from Product p");
         List<Product> products= query.getResultList();
        return products ;
   }
   public List<Product> fetchProductsByHotel(Integer hotelid){
	   	try{ 
	   		Query query = em.createQuery("select h from Hotel h where h.id=?1");
	   		query.setParameter(1, hotelid);
	   		Hotel hotel = (Hotel) query.getSingleResult();
	   		return hotel.getProducts();
	   	}catch (NoResultException e) {
	   		System.out.println("No hotel found with the given name: " + hotelid);
            return Collections.emptyList();
	   	}
  	 
   }
   public List<Product> fetchProductsBetweenPriceRange( double min, double max){
	   Query query = em.createQuery("select p from Product p where p.cost between ?1 and ?2");
	   query.setParameter(1, min);
	   query.setParameter(2, max);
	   
	   List<Product> products = query.getResultList();
	   return products;
	   
   }
public List<Product> fetchProductsByHotelname(String hotelname) {
	try{ 
   		Query query = em.createQuery("select h from Hotel h where h.name=?1");
   		query.setParameter(1, hotelname);
   		Hotel hotel = (Hotel) query.getSingleResult();
   		return hotel.getProducts();
   	}catch (NoResultException e) {
   		System.out.println("No hotel found with the given name: " + hotelname);
        return Collections.emptyList();
   	}
}
}