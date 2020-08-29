package facades;

import entities.Employee;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.*;
import javax.persistence.TypedQuery;

/**
 *
 * Rename Class to a relevant name Add add relevant facade methods
 */
public class EmployeeFacade {

    private static EmployeeFacade instance;
    private static EntityManagerFactory emf;


    //Private Constructor to ensure Singleton
    private EmployeeFacade() {
    }

    /**
     *
     * @param _emf
     * @return an instance of this facade class.
     */
    public static EmployeeFacade getEmployeeFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new EmployeeFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public Employee createEmployee(String name, String address, int salary) {
        Employee employee = new Employee(name, address, salary);
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(employee);
            em.getTransaction().commit();
            return employee;
        } finally {
            em.close();
        }
    }

    public Employee getEmployeeById(int id) {
        EntityManager em = emf.createEntityManager();
        try {
            Employee employee = em.find(Employee.class, id);
            return employee;
        } finally {
            em.close();
        }
    }

    public Employee getEmployeeByName(String name) {
        EntityManager em = emf.createEntityManager();
        try {
            Query query = em.createQuery("Select e FROM Employee e WHERE e.name = :name");
            query.setParameter("name", name);
            Employee emp = (Employee) query.getSingleResult();
            return emp;
        } finally {
            em.close();
        }
    }

    public List<Employee> getAllEmployees() {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Employee> query
                    = em.createQuery("Select employee from Employee employee", Employee.class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    public Employee getEmployeeWithHighestSalary() {
        EntityManager em = emf.createEntityManager();
        try {
            Query query = em.createQuery("Select e FROM Employee e WHERE e.salary = (SELECT MAX(e.salary) FROM Employee e)");
            Employee emp = (Employee) query.getSingleResult();
            return emp;
        } finally {
            em.close();
        }
    }
    
    public Employee getEmployeeWithLowestSalary() {
        EntityManager em = emf.createEntityManager();
        try {
            Query query = em.createQuery("Select e FROM Employee e WHERE e.salary = (SELECT MIN(e.salary) FROM Employee e)");
            Employee emp = (Employee) query.getSingleResult();
            return emp;
        } finally {
            em.close();
        }
    }

}
