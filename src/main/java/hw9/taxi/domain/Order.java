package hw9.taxi.domain;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by s_okhoda on 23.01.2016.
 */
@Entity
@Table(name = "ORDERS")
public class Order {
    //дата, клиент, сумма, адрес подачи, адрес назначения)
    @Id
    @SequenceGenerator(name = "sequence", sequenceName = "ORDER_SEQ",
            allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence")
    @Column(name = "ID")
    private Long id;

    private Date date;

    private Double orderAmount;

    private String addressPickup;

    private String addressGetOut;

    @ManyToOne
    @JoinColumn( name = "CLIENT_ID")
    private Client client;

    public Order(Date date, Double orderAmount, String addressPickup, String
            addressGetOut) {
        this.date = date;
        this.orderAmount = orderAmount;
        this.addressPickup = addressPickup;
        this.addressGetOut = addressGetOut;
    }

    public Order() {
    }

    public String getAddressPickup() {
        return addressPickup;
    }

    public void setAddressPickup(String addressPickup) {
        this.addressPickup = addressPickup;
    }

    public String getAddressGetOut() {
        return addressGetOut;
    }

    public void setAddressGetOut(String addressGetOut) {
        this.addressGetOut = addressGetOut;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Double getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(Double orderAmount) {
        this.orderAmount = orderAmount;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
}
