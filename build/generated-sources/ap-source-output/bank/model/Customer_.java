package bank.model;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Customer.class)
public abstract class Customer_ {

	public static volatile SingularAttribute<Customer, Long> account_number;
	public static volatile SingularAttribute<Customer, String> address;
	public static volatile SingularAttribute<Customer, String> gender;
	public static volatile SingularAttribute<Customer, String> mobile;
	public static volatile SingularAttribute<Customer, String> netbankingAllowed;
	public static volatile SingularAttribute<Customer, String> type_of_account;
	public static volatile SingularAttribute<Customer, IFSCCode> IFSCcode;
	public static volatile SingularAttribute<Customer, String> password;
	public static volatile SingularAttribute<Customer, Double> balance;
	public static volatile SingularAttribute<Customer, String> name;
	public static volatile SingularAttribute<Customer, Date> date_of_creation;
	public static volatile SingularAttribute<Customer, String> aadhaar;
	public static volatile SingularAttribute<Customer, String> pan;
	public static volatile SingularAttribute<Customer, String> email;
	public static volatile SingularAttribute<Customer, String> username;

	public static final String ACCOUNT_NUMBER = "account_number";
	public static final String ADDRESS = "address";
	public static final String GENDER = "gender";
	public static final String MOBILE = "mobile";
	public static final String NETBANKING_ALLOWED = "netbankingAllowed";
	public static final String TYPE_OF_ACCOUNT = "type_of_account";
	public static final String I_FS_CCODE = "IFSCcode";
	public static final String PASSWORD = "password";
	public static final String BALANCE = "balance";
	public static final String NAME = "name";
	public static final String DATE_OF_CREATION = "date_of_creation";
	public static final String AADHAAR = "aadhaar";
	public static final String PAN = "pan";
	public static final String EMAIL = "email";
	public static final String USERNAME = "username";

}

