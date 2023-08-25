package tr.com.bookcell.payment;

import tr.com.bookcell.user.Customer;

import java.time.LocalDateTime;
import java.util.Objects;

public class Payment {
    private Customer customer;
    private LocalDateTime paymentDate;
    private double paymentAmount;

    public Payment() {
    }

    public Payment(Customer customer, LocalDateTime paymentDate, double paymentAmount) {
        this.customer = customer;
        this.paymentDate = paymentDate;
        this.paymentAmount = paymentAmount;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public LocalDateTime getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(LocalDateTime paymentDate) {
        this.paymentDate = paymentDate;
    }

    public double getPaymentAmount() {
        return paymentAmount;
    }

    public void setPaymentAmount(double paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Payment payment = (Payment) o;
        return paymentAmount == payment.paymentAmount && Objects.equals(customer, payment.customer) && Objects.equals(paymentDate, payment.paymentDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customer, paymentDate, paymentAmount);
    }

    @Override
    public String toString() {
        return "Payment{" +
                "customer='" + customer + '\'' +
                ", paymentDate=" + paymentDate +
                ", paymentAmount=" + paymentAmount +
                '}';
    }
}
