package md.mi.domain.entity;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import md.mi.domain.base.DomainBase;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(schema="public", name="account")
public class Account extends DomainBase {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private String id;
    private String userid;
    private Date date_open;
    private Date date_close;
    private Integer balance;
    private Integer currency_code;
    private String status;

    public Account(){
        super();
    }

    public Account(String userid, Integer balance, Integer currency_code, String status) {
        super();

        this.userid = userid;
        this.balance = balance;
        this.currency_code = currency_code;
        this.status = status;
    }


    public Account(String id, String userid, Date date_open, Date date_close,
            Integer balance, Integer currency_code, String status) {
        super();
        this.id = id;
        this.userid = userid;
        this.date_open = date_open;
        this.date_close = date_close;
        this.balance = balance;
        this.currency_code = currency_code;
        this.status = status;
    }

    @Id
    @Column(name = "id", nullable=false)
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid",
    strategy = "uuid")
    public String getId() {
        return id;
    }

    @Column(name = "userid", nullable=false)
    public String getUserid() {
        return userid;
    }

    @PrePersist
    public void preInsert(){
        date_open= new java.util.Date();
    }

    @Column(name = "date_open")
    public Date getDate_open() {
        return date_open;
    }

    @Column(name = "date_close")
    public Date getDate_close() {
        return date_close;
    }

    @Column(name = "balance")
    public Integer getBalance() {
        return balance;
    }

    @Column(name = "currency_code")
    public Integer getCurrency_code() {
        return currency_code;
    }

    @Column(name = "status")
    public String getStatus() {
        return status;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public void setDate_open(Date date_open) {
        this.date_open = date_open;
    }

    public void setDate_close(Date date_close) {
        this.date_close = date_close;
    }

    public void setBalance(Integer balance) {
        this.balance = balance;
    }

    public void setCurrency_code(Integer currency_code) {
        this.currency_code = currency_code;
    }

    public void setStatus(String status) {
        this.status = status;
    }


}
