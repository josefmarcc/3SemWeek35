/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import dbfacade.CustomerFacade;
import javax.persistence.*;
import java.util.*;

/**
 *
 * @author josef
 */
public class EntityTested {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
        EntityManager em = emf.createEntityManager();

        Customer c1 = new Customer("Person", "Test");
        Customer c2 = new Customer("Person2", "Test2");
        Customer c3 = new Customer("Person3", "Test3");

        em.getTransaction().begin();
        em.persist(c1);
        em.persist(c2);
        em.persist(c3);

        em.getTransaction().commit();
        
        CustomerFacade facade = CustomerFacade.getCustomerFacade(emf);
        //Find customer by ID
        System.out.println("Customer1: " + facade.findByID(c1.getId()).getFirstName());
        System.out.println("Customer2: " + facade.findByID(c2.getId()).getFirstName());
        //Find all customers
        System.out.println("Number of customers: " + facade.allCustomer().size());

        em.close();
        emf.close();

    }

}
