package ibm.poc.kafka.streams.models;

public class CustomerBalanceInfo {

    private String accountId;
    private String customerId;
    private String phoneNumber;
    private double balance;

    public CustomerBalanceInfo() {

    }

    public CustomerBalanceInfo(String accountId, String customerId, String phoneNumber, double balance) {
        this.accountId = accountId;
        this.customerId = customerId;
        this.phoneNumber = phoneNumber;
        this.balance = balance;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}
