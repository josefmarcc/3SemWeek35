package dto;

import entities.Employee;

public class EmployeeDTO {
    
    
    int id;
    String name;
    String address;

    public EmployeeDTO(Employee emp) {
        this.id = emp.getId();
        this.name = emp.getName();
        this.address = emp.getAddress();
    }
}