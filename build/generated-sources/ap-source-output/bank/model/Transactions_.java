package bank.model;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Transactions.class)
public abstract class Transactions_ {

	public static volatile SingularAttribute<Transactions, Long> account_number;
	public static volatile SingularAttribute<Transactions, Date> dateOfTransaction;
	public static volatile SingularAttribute<Transactions, String> comment;
	public static volatile SingularAttribute<Transactions, String> debit;
	public static volatile SingularAttribute<Transactions, String> credit;
	public static volatile SingularAttribute<Transactions, Long> transaction_no;

	public static final String ACCOUNT_NUMBER = "account_number";
	public static final String DATE_OF_TRANSACTION = "dateOfTransaction";
	public static final String COMMENT = "comment";
	public static final String DEBIT = "debit";
	public static final String CREDIT = "credit";
	public static final String TRANSACTION_NO = "transaction_no";

}

