package bank.model;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Transactions {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long transaction_no;
    private String comment;
    private String debit;
    private String credit;
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateOfTransaction;
    private long account_number;

    public long getAccount_number() {
        return account_number;
    }

    public void setAccount_number(long account_number) {
        this.account_number = account_number;
    }

    
    

    
    
    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    
    
    public long getTransaction_no() {
        return transaction_no;
    }

    public void setTransaction_no(long transaction_no) {
        this.transaction_no = transaction_no;
    }

    public String getDebit() {
        return debit;
    }

    public void setDebit(String debit) {
        this.debit = debit;
    }

    public String getCredit() {
        return credit;
    }

    public void setCredit(String credit) {
        this.credit = credit;
    }

    public Date getDateOfTransaction() {
        return dateOfTransaction;
    }

    public void setDateOfTransaction(Date dateOfTransaction) {
        this.dateOfTransaction = dateOfTransaction;
    }
    
    
    
}
