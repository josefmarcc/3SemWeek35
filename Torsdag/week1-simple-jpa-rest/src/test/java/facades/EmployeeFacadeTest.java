package facades;

import entities.Employee;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class EmployeeFacadeTest {

    private static final EntityManagerFactory ENF = Persistence.createEntityManagerFactory("pu");
    private static final EmployeeFacade FE = EmployeeFacade.getEmployeeFacade(ENF);

    public EmployeeFacadeTest() {
    }

    @BeforeAll
    public static void setUpClass() {
        FE.createEmployee("Josef", "Lyngby", 1000);

//        Add code to setup entities for test before running any test methods
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
    public void testCreateEmployee() {
        String expectedEmployee = "Josef";
        String actualEmployee = FE.getEmployeeByName(expectedEmployee).getName();

        assertEquals(expectedEmployee, actualEmployee);

    }

    @Test
    public void testGetEmployeesByName() {
        String expectedEmployee = "Josef";
        String actualEmployee = FE.getEmployeeByName("Josef").getName();
        assertEquals(expectedEmployee, actualEmployee);

    }
    
    @Test
    public void testGetEmployeesById() {
        int expectedEmployee = 1;
        int actualEmployee = FE.getEmployeeByName("Josef").getId();
        assertEquals(expectedEmployee, actualEmployee);

    }

    @Test
    public void testGetAllEmployees() {

        int expectedSize = 1;
        int actualSize = FE.getAllEmployees().size();

        assertEquals(expectedSize, actualSize);
    }
    
    @Test
    public void testGetEmployeeWithHighestSalary() {
        
        Employee actualEmployee = FE.getEmployeeWithHighestSalary();
        
        String expEmployee = "Josef";
        String result = actualEmployee.getName();
        
        assertEquals(expEmployee, result);
    }
    

}
