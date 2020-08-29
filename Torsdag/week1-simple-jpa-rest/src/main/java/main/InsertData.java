package main;

import facades.EmployeeFacade;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class InsertData {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
        EntityManager em = emf.createEntityManager();

        EmployeeFacade empfacade = EmployeeFacade.getEmployeeFacade(emf);

        empfacade.createEmployee("Josef", "Glostrupvej", 1000000);
        empfacade.createEmployee("Frederik", "Tagensvej", 10);
        empfacade.createEmployee("Thor", "Frederiksbergvej", 26000);
    }
}
