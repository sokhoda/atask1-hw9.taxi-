package hw9.taxi.domain;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by s_okhoda on 20.01.2016.
 */
@Entity
@Table(name = "CLIENTS")
public class Client {
    @Id
    @SequenceGenerator(name = "sequence", sequenceName = "CLIENT_SEQ",
            allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence")
    @Column(name = "ID")
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "SURNAME")
    private String surname;

    @Column(name = "PHONE")
    private String phone;

    @Column(name = "ADDRESS")
    private String address;

    @Column(name = "ORDER_AMOUNT")
    private Double orderAmount;

    @Column(name = "LAST_ORDER_DATE")
    private Date lastOrderDate;

    @OneToMany(mappedBy = "client")//, fetch = FetchType.EAGER)
    private List<Order> orders;

    @Transient
    SimpleDateFormat format1 = new SimpleDateFormat("dd.MM.yyyy");

    public Client(String name, String surname, String phone, String address) {
        this(name, surname, phone, address, 0.0, null);
    }

    public Client(String name, String surname, String phone, String
            address, Double orderAmount, Date lastOrderDate) {
        this.name = name;
        this.surname = surname;
        this.phone = phone;
        this.address = address;
        this.orderAmount = orderAmount;
        this.lastOrderDate = lastOrderDate;
    }

    public Client(){

    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                ", orderAmount=" + Double.toString(orderAmount) +
                ", lastOrderDate=" + (lastOrderDate == null ? "null" :
                format1.format(lastOrderDate)) +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getLastOrderDate() {
        return lastOrderDate;
    }

    public void setLastOrderDate(Date lastOrderDate) {
        this.lastOrderDate = lastOrderDate;
    }

    public Double getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(Double orderAmount) {
        this.orderAmount = orderAmount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
