package bank.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Manager.class)
public abstract class Manager_ {

	public static volatile SingularAttribute<Manager, IFSCCode> IFSCcode;
	public static volatile SingularAttribute<Manager, String> password;
	public static volatile SingularAttribute<Manager, String> address;
	public static volatile SingularAttribute<Manager, String> gender;
	public static volatile SingularAttribute<Manager, Long> manager_id;
	public static volatile SingularAttribute<Manager, String> name;
	public static volatile SingularAttribute<Manager, String> mobile;
	public static volatile SingularAttribute<Manager, String> email;
	public static volatile SingularAttribute<Manager, String> username;

	public static final String I_FS_CCODE = "IFSCcode";
	public static final String PASSWORD = "password";
	public static final String ADDRESS = "address";
	public static final String GENDER = "gender";
	public static final String MANAGER_ID = "manager_id";
	public static final String NAME = "name";
	public static final String MOBILE = "mobile";
	public static final String EMAIL = "email";
	public static final String USERNAME = "username";

}

