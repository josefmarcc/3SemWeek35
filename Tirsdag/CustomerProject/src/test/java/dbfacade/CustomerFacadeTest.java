/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbfacade;

import entity.Customer;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author josef
 */
public class CustomerFacadeTest {
    
    public CustomerFacadeTest() {
    }

    @org.junit.jupiter.api.BeforeAll
    public static void setUpClass() throws Exception {
    }

    @org.junit.jupiter.api.AfterAll
    public static void tearDownClass() throws Exception {
    }

    @org.junit.jupiter.api.BeforeEach
    public void setUp() throws Exception {
    }

    @org.junit.jupiter.api.AfterEach
    public void tearDown() throws Exception {
    }

    /**
     * Test of addCustomer method, of class CustomerFacade.
     */
    @org.junit.jupiter.api.Test
    public void testAddCustomer() {
        System.out.println("addCustomer");
        String fName = "Testperson";
        String lName = "Persontest";

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
        CustomerFacade facade = CustomerFacade.getCustomerFacade(emf);

        Customer cus = facade.addCustomer(fName, lName);

        String expResult = cus.getFirstName();
        String result = facade.findByID(cus.getId()).getFirstName();
        assertEquals(expResult, result);
    }

    /**
     * Test of allCustomer method, of class CustomerFacade.
     */
    @org.junit.jupiter.api.Test
    public void testAllCustomer() {
        System.out.println("allCustomer");
        
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
        CustomerFacade facade = CustomerFacade.getCustomerFacade(emf);
        
        int expResult = 0;
        int result = facade.allCustomer().size();
        assertEquals(expResult, result);
    }

}
