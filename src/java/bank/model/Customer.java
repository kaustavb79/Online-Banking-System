package bank.model;

import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;

@Entity
public class Customer {
    @Id
    private long account_number;
    @Column(nullable = true)
    private String name;
    @ManyToOne(cascade = CascadeType.ALL)
    private IFSCCode IFSCcode;
    @Column(nullable = true)
    private String email;
    @Column(nullable = true)
    private String mobile;
    @Column(nullable = true)
    private String username;
    @Column(nullable = true)
    private String password;
    @Column(nullable = true)
    private double balance;
    @Column(nullable = true)
    private String pan;
    @Column(nullable = true)
    private String aadhaar;
    @Column(nullable = true)
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date date_of_creation;
    @Column(nullable = true)
    private String type_of_account;
    @Column(nullable = true)
    private String netbankingAllowed;
    @Column(nullable = true)
    private String address;
    private String gender;
    
    public long getAccount_number() {
        return account_number;
    }

    public void setAccount_number(long account_number) {
        this.account_number = account_number;
    }

  

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public IFSCCode getIFSCcode() {
        return IFSCcode;
    }

    public void setIFSCcode(IFSCCode IFSCcode) {
        this.IFSCcode = IFSCcode;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    } 

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getPan() {
        return pan;
    }

    public void setPan(String pan) {
        this.pan = pan;
    }

    public String getAadhaar() {
        return aadhaar;
    }

    public void setAadhaar(String aadhaar) {
        this.aadhaar = aadhaar;
    }

    public Date getDate_of_creation() {
        return date_of_creation;
    }

    public void setDate_of_creation(Date date_of_creation) {
        this.date_of_creation = date_of_creation;
    }

    public String getType_of_account() {
        return type_of_account;
    }

    public void setType_of_account(String type_of_account) {
        this.type_of_account = type_of_account;
    }

    public String getNetbankingAllowed() {
        return netbankingAllowed;
    }

    public void setNetbankingAllowed(String netbankingAllowed) {
        this.netbankingAllowed = netbankingAllowed;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    
    
    
}
