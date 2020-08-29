package dto;

import entities.BankCustomer;

/**
 *
 * @author josef
 */
public class CustomerDTO {

    int customerID;
    String fullName;
    String accountNumber;
    double balance;

    public CustomerDTO() {
    }

    public CustomerDTO(BankCustomer customer) {
        this.customerID = customer.getId();
        this.fullName = customer.getFirstName() + " " + customer.getLastName();
        this.accountNumber = customer.getAccountNumber();
        this.balance = customer.getBalance();
    }

    public int getCustomerID() {
        return customerID;
    }

    public String getFullName() {
        return fullName;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public double getBalance() {
        return balance;
    }
}
