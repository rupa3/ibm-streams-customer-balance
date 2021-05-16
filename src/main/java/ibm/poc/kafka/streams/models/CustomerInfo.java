package ibm.poc.kafka.streams.models;

public class CustomerInfo {

    private String customerId;
    private String name;
    private String phoneNumber;
    private String accountId;

    public CustomerInfo(){

    }

    public CustomerInfo(String customerId, String name, String phoneNumber, String accountId) {
        this.customerId = customerId;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.accountId = accountId;
    }

    public String getCustomerId() {
        return customerId;
    }



    public String getName() {
        return name;
    }



    public String getPhoneNumber() {
        return phoneNumber;
    }



    public String getAccountId() {
        return accountId;
    }


}
