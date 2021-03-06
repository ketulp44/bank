
package com.example.bank.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author world
 */
@Entity
@Table(name="account")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="account_id")
    private Long accountId;
    @Column(name="bank_id")
    private Long bankId;
    @Column(name="account_type")
    private String accountType;
    @Column(name="account_holder_name")
    private String name;
    @Column(name="phone")
    private String phone;
    @Column(name="email")
    private String email;
    @Column(name="address")
    private String address;
    @Column(name="birth_date")
    private Date birthDate;
    @Column(name="pan_no")
    private String panNo;
    @Column(name="balance")
    private Long balance;
    @Column(name="pan_img_url")
    private String panImgUrl;
    public Account() {
        
    }

    public Account(Long bankId, String accountType, String name, String phone, String email, String address, Date birthDate, String panNO, Long balance,String panImgUrl) {
        this.bankId = bankId;
        this.accountType = accountType;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.birthDate = birthDate;
        this.panNo = panNO;
        this.balance = balance;
        this.panImgUrl =panImgUrl;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public Long getBankId() {
        return bankId;
    }

    public void setBankId(Long bankId) {
        this.bankId = bankId;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getPanNo() {
        return panNo;
    }

    public void setPanNo(String panNo) {
        this.panNo = panNo;
    }

    public Long getBalance() {
        return balance;
    }

    public void setBalance(Long balance) {
        this.balance = balance;
    }

    public String getPanImgUrl() {
        return panImgUrl;
    }

    public void setPanImgUrl(String panImgUrl) {
        this.panImgUrl = panImgUrl;
    }

    @Override
    public String toString() {
        return "Account{" + "accountId=" + accountId + ", bankId=" + bankId + ", accountType=" + accountType + ", name=" + name + ", phone=" + phone + ", email=" + email + ", address=" + address + ", birthDate=" + birthDate + ", panNO=" + panNo + ", balance=" + balance + ", panImgUrl=" + panImgUrl + '}';
    }

    
}
