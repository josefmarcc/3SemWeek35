package facades;

import dto.CustomerDTO;
import entities.BankCustomer;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

public class CustomerFacade {

    private static CustomerFacade instance;
    private static EntityManagerFactory emf;

    //Private Constructor to ensure Singleton
    private CustomerFacade() {
    }

    /**
     *
     * @param _emf
     * @return an instance of this facade class.
     */
    public static CustomerFacade getCustomerFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new CustomerFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }


    public CustomerDTO getCustomerById(int id) {
        EntityManager em = emf.createEntityManager();
        try {
            CustomerDTO customer = em.find(CustomerDTO.class, id);
            return customer;
        } finally {
            em.close();
        }
    }

    public List<CustomerDTO> getCustomerByName() {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<CustomerDTO> query
                    = em.createQuery("Select c FROM BANKCUSTOMER c WHERE c.name = :name", CustomerDTO.class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    public List<BankCustomer> getAllEmployees() {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<BankCustomer> query
                    = em.createQuery("Select c from BANKCUSTOMER c", BankCustomer.class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }
    
    
    public BankCustomer createCustomer(BankCustomer customer) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(customer);
            em.getTransaction().commit();
            return customer;
        } finally {
            em.close();
        }
    }
    
    public static void main(String[] args) {
        CustomerFacade CF = new CustomerFacade();
        BankCustomer cus1 = new BankCustomer("Josef", "Marc", "1", 10.000, 1, "Good customer");
        BankCustomer cus2 = new BankCustomer("Frederik", "Dahl", "2", 1.0, 2, "Bad customer");
        BankCustomer cus3 = new BankCustomer("Thor", "T", "3", 2.000, 1, "Good customer");
        
        CF.createCustomer(cus1);
        CF.createCustomer(cus2);
        CF.createCustomer(cus3);
        
        System.out.println(CF.getCustomerById(1).getFullName());
        
    }
    
}
