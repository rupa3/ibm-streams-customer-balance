package ibm.poc.kafka.streams.models;

public class BalanceInfo {

    private String balanceId;
    private String accountId;
    private double balance;

    public BalanceInfo() {

    }

    public BalanceInfo(String balanceId, String accountId, float balance) {
        this.balanceId = balanceId;
        this.accountId = accountId;
        this.balance = balance;
    }

    public String getAccountId() {
        return accountId;
    }

    public String getBalanceId() {
        return balanceId;
    }

    public double getBalance() {
        return balance;
    }
}
