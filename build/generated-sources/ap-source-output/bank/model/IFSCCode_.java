package bank.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(IFSCCode.class)
public abstract class IFSCCode_ {

	public static volatile SingularAttribute<IFSCCode, String> Address;
	public static volatile SingularAttribute<IFSCCode, Integer> id;
	public static volatile SingularAttribute<IFSCCode, String> BranchName;
	public static volatile SingularAttribute<IFSCCode, Employee> employee;
	public static volatile SingularAttribute<IFSCCode, String> IFSC;
	public static volatile SingularAttribute<IFSCCode, Customer> customer;

	public static final String ADDRESS = "Address";
	public static final String ID = "id";
	public static final String BRANCH_NAME = "BranchName";
	public static final String EMPLOYEE = "employee";
	public static final String I_FS_C = "IFSC";
	public static final String CUSTOMER = "customer";

}

