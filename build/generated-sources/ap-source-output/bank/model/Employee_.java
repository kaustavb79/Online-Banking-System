package bank.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Employee.class)
public abstract class Employee_ {

	public static volatile SingularAttribute<Employee, IFSCCode> IFSCcode;
	public static volatile SingularAttribute<Employee, String> password;
	public static volatile SingularAttribute<Employee, String> address;
	public static volatile SingularAttribute<Employee, String> gender;
	public static volatile SingularAttribute<Employee, Long> employee_id;
	public static volatile SingularAttribute<Employee, String> name;
	public static volatile SingularAttribute<Employee, String> mobile;
	public static volatile SingularAttribute<Employee, String> aadhaar;
	public static volatile SingularAttribute<Employee, Double> salary;
	public static volatile SingularAttribute<Employee, String> email;
	public static volatile SingularAttribute<Employee, String> username;

	public static final String I_FS_CCODE = "IFSCcode";
	public static final String PASSWORD = "password";
	public static final String ADDRESS = "address";
	public static final String GENDER = "gender";
	public static final String EMPLOYEE_ID = "employee_id";
	public static final String NAME = "name";
	public static final String MOBILE = "mobile";
	public static final String AADHAAR = "aadhaar";
	public static final String SALARY = "salary";
	public static final String EMAIL = "email";
	public static final String USERNAME = "username";

}

