package facades;

import entities.BankCustomer;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CustomerFacadeTest {

    private static final EntityManagerFactory ENF = Persistence.createEntityManagerFactory("pu");
    private static final CustomerFacade FE = CustomerFacade.getCustomerFacade(ENF);

    public CustomerFacadeTest() {
    }

    @BeforeAll
    public static void setUpClass() {
        BankCustomer cus1 = new BankCustomer("Josef", "Marc", "1", 10000, 1, "Good customer");
        BankCustomer cus2 = new BankCustomer("Frederik", "Dahl", "2", 10, 2, "Bad customer");
        BankCustomer cus3 = new BankCustomer("Thor", "T", "3", 2000, 1, "Good customer");
        
        FE.createCustomer(cus1);
        FE.createCustomer(cus2);
        FE.createCustomer(cus3);
        

    }

    @AfterAll
    public static void tearDownClass() {
//        Clean up database after test is done or use a persistence unit with drop-and-create to start up clean on every test
    }

    @BeforeEach
    public void setUp() {
    }

    @AfterEach
    public void tearDown() {
//        Remove any data after each test was run
    }

    /**
     * CreateEmployee was tested in the beforeAll.
     */
    @Test
    public void testGetCustomerByID() {
        int expected = 1;
        int result = FE.getCustomerById(expected).getCustomerID();

        assertEquals(expected, result);

    }
}
