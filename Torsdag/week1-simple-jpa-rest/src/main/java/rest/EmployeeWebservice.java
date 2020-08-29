package rest;

import com.google.gson.Gson;
import dto.EmployeeDTO;
import entities.Employee;
import facades.EmployeeFacade;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("employee")
public class EmployeeWebservice {

    //NOTE: Change Persistence unit name according to your setup
    static EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
    EmployeeFacade FE = EmployeeFacade.getEmployeeFacade(emf);

    @Path("all")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getAllEmployees() {
        EntityManager em = emf.createEntityManager();
        try {
            List<Employee> employee = FE.getAllEmployees();
            return new Gson().toJson(employee);
        } finally {
            em.close();
        }
    }

    @Path("id/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getEmployeesById(@PathParam("id") int id) {
        EntityManager em = emf.createEntityManager();
        try {
            Employee emp = FE.getEmployeeById(id);
            EmployeeDTO dtoEmp = new EmployeeDTO(emp);
            return new Gson().toJson(dtoEmp);
        } finally {
            em.close();
        }
    }

    @Path("name/{name}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getEmployeesByName(@PathParam("name") String name) {
        EntityManager em = emf.createEntityManager();
        try {
            Employee emp = FE.getEmployeeByName(name);
            EmployeeDTO dtoEmp = new EmployeeDTO(emp);
            return new Gson().toJson(dtoEmp);
        } finally {
            em.close();
        }
    }

    @Path("highestpaid")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getEmployeeWithHighestSalary() {
        EntityManager em = emf.createEntityManager();
        try {
            Employee employee = FE.getEmployeeWithHighestSalary();
            return new Gson().toJson(employee);
        } finally {
            em.close();
        }
    }
    
    @Path("lowestpaid")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getEmployeeWithLowestSalary() {
        EntityManager em = emf.createEntityManager();
        try {
            Employee employee = FE.getEmployeeWithLowestSalary();
            return new Gson().toJson(employee);
        } finally {
            em.close();
        }
    }
    
    
}
