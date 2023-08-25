package tr.com.bookcell.reservation;

import tr.com.bookcell.branch.Branch;
import tr.com.bookcell.user.Customer;

import java.time.LocalDateTime;
import java.util.Objects;

public class Reservation {
    private Customer customer;
    private LocalDateTime startDate;
    private LocalDateTime expiryDate;
    private Branch pickUpBranch;
    private Branch dropOffBranch;
    private boolean isPay;

    public Reservation() {
    }

    public Reservation(Customer customer, LocalDateTime startDate, LocalDateTime expiryDate, Branch pickUpBranch, Branch dropOffBranch, boolean isPay) {
        this.customer = customer;
        this.startDate = startDate;
        this.expiryDate = expiryDate;
        this.pickUpBranch = pickUpBranch;
        this.dropOffBranch = dropOffBranch;
        this.isPay = isPay;
    }

    public Customer getcustomer() {
        return customer;
    }

    public void setcustomer(Customer customer) {
        this.customer = customer;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(LocalDateTime expiryDate) {
        this.expiryDate = expiryDate;
    }

    public Branch getPickUpBranch() {
        return pickUpBranch;
    }

    public void setPickUpBranch(Branch pickUpBranch) {
        this.pickUpBranch = pickUpBranch;
    }

    public Branch getDropOffBranch() {
        return dropOffBranch;
    }

    public void setDropOffBranch(Branch dropOffBranch) {
        this.dropOffBranch = dropOffBranch;
    }

    public boolean isPay() {
        return isPay;
    }

    public void setPay(boolean pay) {
        isPay = pay;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reservation that = (Reservation) o;
        return isPay == that.isPay && Objects.equals(customer, that.customer) && Objects.equals(startDate, that.startDate) && Objects.equals(expiryDate, that.expiryDate) && Objects.equals(pickUpBranch, that.pickUpBranch) && Objects.equals(dropOffBranch, that.dropOffBranch);
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "customer='" + customer + '\'' +
                ", startDate=" + startDate +
                ", expiryDate=" + expiryDate +
                ", pickUpBranch=" + pickUpBranch +
                ", dropOffBranch=" + dropOffBranch +
                ", isPay=" + isPay +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(customer, startDate, expiryDate, pickUpBranch, dropOffBranch, isPay);
    }
}
