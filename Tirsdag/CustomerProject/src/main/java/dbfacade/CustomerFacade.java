/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbfacade;

import entity.Customer;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

/**
 *
 * @author josef
 */
public class CustomerFacade {

    private static EntityManagerFactory emf;
    private static CustomerFacade instance;

    private CustomerFacade() {
    }

    public static CustomerFacade getCustomerFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new CustomerFacade();
        }
        return instance;
    }

    public Customer addCustomer(String fName, String lName) {
        Customer cus = new Customer(fName, lName);
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(cus);
            em.getTransaction().commit();
            return cus;
        } finally {
            em.close();
        }
    }

    public Customer findByID(int id) {
        EntityManager em = emf.createEntityManager();
        try {
            Customer cus = em.find(Customer.class, id);
            return cus;
        } finally {
            em.close();
        }
    }

    public List<Customer> allCustomer() {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Customer> query
                    = em.createQuery("SELECT customer FROM Customer customer", Customer.class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

}
