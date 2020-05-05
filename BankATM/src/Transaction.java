public interface Transaction {
    boolean withdraw(double amount);
    void deposit(double amount);
}
