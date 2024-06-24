package com.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.entities.Item;

@Repository
public class ItemDao {
  @Autowired
  EntityManagerFactory emf;

  @Autowired
  EntityManager em;
  
  @Autowired
  EntityTransaction et;

  public void saveItem( Item item)
  {
      et.begin();
      em.persist(item);
      et.commit();
  }
  public void updateItem(Item item)
  {
      et.begin();
      em.merge(item);
      et.commit();
  }
  public void deleteById(int id)
  {
      et.begin();
      em.remove(em.find(Item.class,id));
      et.commit();
  }
  public Item findbyId(int id)
  {
	  return  em.find(Item.class, id);
  }
public List<Item> removeItemById(List<Item> items, int item_id) {
	items.removeIf(item -> item.getId() == item_id);
	return items;
}
}
