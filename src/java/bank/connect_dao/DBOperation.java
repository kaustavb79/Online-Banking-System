package bank.connect_dao;

import java.util.List;
import bank.model.Customer;
import bank.model.Employee;
import bank.model.IFSCCode;
import bank.model.Manager;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import bank.model.Transactions;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

//Contains methods to perform all the operations
//in the Software.
//All the methods called in each and every module's jsp or servlet page
//is defined here.
//I have create multiple sessions for each transaction with the database.
public class DBOperation {    
   
    //Session Creation
    private Session openSession(){
        Session session=HibernateConnection.createFactory().openSession();
        return session;
    }
    
    //Bank Module
//-----------------------------------------------------------------------------     
    
    public static boolean updateManager(String query,String manager_id){
        boolean flag;
         Session s=new DBOperation().openSession();
         Transaction t=s.beginTransaction();
         Query q=s.createQuery(query);
         
         int stat=q.executeUpdate();
         System.out.println("Record Updated for manager : "+manager_id);
         try{
           t.commit();
           flag=true;
        }catch(Exception e){
            System.err.print(e);
            if(t!=null){
                t.rollback();
            }
            flag=false;
        }
        
        s.close();
        return flag;
    }
    
    public static List<IFSCCode> returnIfsc(){
        Session session=new DBOperation().openSession();
        Query q=session.createQuery("from IFSCcode");
        List<IFSCCode> list=q.list();
        session.close();
        return list;
    }
    
    public static List<Manager> returnManager(){
        Session session=new DBOperation().openSession();
        Query q=session.createQuery("from Manager");
        List<Manager> list=q.list();
        session.close();
        return list;
    }  
    
    public static Manager fetchManagerDetailById(long id){
        Session session=new DBOperation().openSession();
        Query<Manager> query=session.createQuery("from Manager where manager_id="+id);
        Manager m=query.getSingleResult();
        session.close();
        return m;
    }
    
    public static boolean addManager(Manager manager,IFSCCode ifscc){
       Session session=new DBOperation().openSession();
        boolean flag;
        session.save(manager);
        manager.setIFSCcode(ifscc);
        session.save(ifscc);
        Transaction t=session.beginTransaction();
        try{
            t.commit();
            flag=true;
        }catch(Exception e){
            e.printStackTrace();
            if(t!=null){
            t.rollback();
            }
            flag=false;
        }
        session.close();
       return flag; 
    }
//----------------------------------------------------------------------------- 
    
    //Manager Module
//-----------------------------------------------------------------------------     
    
    public static Manager loginManager(String username, String password) {
        Session s=new DBOperation().openSession();
        Criteria cr=s.createCriteria(Manager.class);
        cr.add(Restrictions.eq("username", username));
        cr.add(Restrictions.eq("password", password));
        List<Manager> list=cr.list();
        Manager m=null;
        for(Manager man:list){
            m=man;
        }
        return m; 
    }
    
     public static boolean addEmployee(Employee emp,IFSCCode ifsc){
        Session session=new DBOperation().openSession();
        boolean flag;
        emp.setIFSCcode(ifsc);
        session.save(emp);
        Transaction t=session.beginTransaction();
        
        try{
            t.commit();
            flag=true;
        }
        catch(Exception e){
            e.printStackTrace();
            if(t!=null){
                t.rollback();
            }
            flag=false;
        }
        session.close();
        return flag;
    }
    
     public static Employee fetchEmployeeDetailById(long id){
        Session session=new DBOperation().openSession();
        Query<Employee> query=session.createQuery("from Employee where employee_id="+id);
        Employee e=query.getSingleResult();
        session.close();
        return e;
    }
    
    public static List<Employee> returnAllEmployeeByIFSC(long id){
        Session session=new DBOperation().openSession();
        Manager m=fetchManagerDetailById(id);
        
        Query<Employee> query=session.createQuery("from Employee where IFSCcode_id="+m.getIFSCcode().getId());
        List<Employee> list=query.list();
        session.close();
        return list;
    }
     
    public static List<Employee> returnEmployee(){
        Session session=new DBOperation().openSession();
        List<Employee> list=session.createQuery("from Employee").list();
        
        session.close();
        return list;
    }
    
    public static Employee fetchEmployeeById(long id){
        Session session=new DBOperation().openSession();
        Employee emp=(Employee)session.createQuery("from Employee where employee_id="+id).getSingleResult();
        session.close();
        return emp;
    }
    
    public static boolean removeEmployee(long id){
        Session session=new DBOperation().openSession();
        boolean flag;
        Transaction t=session.beginTransaction();
        Query<Employee> query=session.createQuery("delete from Employee where employee_id=:id");
        query.setLong("id", id);
        
        int result=query.executeUpdate();
        
        try{
           t.commit();
           flag=true;
        }catch(Exception e){
            System.err.print(e);
            if(t!=null){
                t.rollback();
            }
            flag=false;
        }
        
        session.close();
        return flag;
    }
    
    public static boolean updateEmployee(String query,String emp_id){
        boolean flag;
         Session s=new DBOperation().openSession();
         Transaction t=s.beginTransaction();
         Query q=s.createQuery(query);
         int stat=q.executeUpdate();
         System.out.println("Record Updated for employee : "+emp_id);
         try{
           t.commit();
           flag=true;
        }catch(Exception e){
            System.err.print(e);
            if(t!=null){
                t.rollback();
            }
            flag=false;
        }
        
        s.close();
        return flag;
    }
//----------------------------------------------------------------------------- 
    
    //Employee Module
//-----------------------------------------------------------------------------     
    
    public static Employee loginEmployee(String username,String password){
        Session s=new DBOperation().openSession();
        Criteria cr=s.createCriteria(Employee.class);
        cr.add(Restrictions.eq("username", username));
        cr.add(Restrictions.eq("password", password));
        List<Employee> list=cr.list();
        Employee e=null;
        for(Employee emp:list){
            e=emp;
        }
        return e; 
    }
    
    public static List<Customer> returnAllCustomerByIFSC(long id){
        Session session=new DBOperation().openSession();
        Employee e=fetchEmployeeDetailById(id);
        
        Query<Customer> query=session.createQuery("from Customer where IFSCcode_id="+e.getIFSCcode().getId());
        List<Customer> list=query.list();
        session.close();
        return list;
    }
    
    public static boolean addCustomer(Customer c,IFSCCode ifsc){
        boolean flag;
        Session s=new DBOperation().openSession();
        c.setIFSCcode(ifsc);
        s.save(c);
        Transaction t=s.beginTransaction();
        
        try{
            t.commit();
            flag=true;
        }
        catch(Exception e){
            System.err.print(e);
            if(t!=null){
                t.rollback();
            }
            flag=false;
        }
        s.close();
        return flag;
    }
    
    public static long returnAccountNumber(){
        Session s=new DBOperation().openSession();
        Customer c=(Customer)s.createQuery("from Customer order by account_number DESC").setMaxResults(1).uniqueResult();
        long x;
        if(c==null){
         x=0;
        }
        else{
            x=c.getAccount_number();
        }
        s.close();
        return x;
    }
    
    public static Customer returnCustomerByAccountNumber(long acc_num){
        Session s=new DBOperation().openSession();
        Criteria cr=s.createCriteria(Customer.class);
        cr.add(Restrictions.eq("account_number", acc_num));
        Customer c=null;
        List<Customer> list=cr.list();
        for(Customer cus:list){
            c=cus;
        }      
        s.close();
        return c;
    }
    
     public static boolean removeCustomer(long acc_num) {
         Session session=new DBOperation().openSession();
        boolean flag;
        Transaction t=session.beginTransaction();
        Query<Customer> query=session.createQuery("delete from Customer where account_number=:acc_num");
        Query<Transactions> query1=session.createQuery("delete from Transactions where account_number=:anum");
        query.setLong("acc_num", acc_num);
        query1.setLong("anum", acc_num);
        
        int result=query.executeUpdate();
        int result1=query1.executeUpdate();
        try{
           t.commit();
           flag=true;
        }catch(Exception e){
            System.err.print(e);
            if(t!=null){
                t.rollback();
            }
            flag=false;
        }
        
        session.close();
        return flag;
         
     }
     
     public static boolean updateBalance(double balance,long acc_num){
         boolean flag;
         Session s=new DBOperation().openSession();
         Transaction t=s.beginTransaction();
         Query q=s.createQuery("update Customer set balance=:bal where account_number=:anum");
         q.setParameter("bal", balance);
         q.setParameter("anum", acc_num);         
         int stat=q.executeUpdate();
         System.out.println("Balance Updated!!!\n"+ stat+" Records updated");
         try{
           t.commit();
           flag=true;
        }catch(Exception e){
            System.err.print("Invalid Account Number!!!");
            if(t!=null){
                t.rollback();
            }
            flag=false;
        }        
        s.close();
        return flag;
     }
     
     public static boolean updateCustomerDetails(String query,String anum){
         boolean flag;         
         Session s=new DBOperation().openSession();
         Transaction tr=s.beginTransaction();
         Query q=s.createQuery(query);
         int res=q.executeUpdate();
         System.out.println("Record Updated Form Account Number : "+anum);
         try{
           tr.commit();
           flag=true;
         }catch(Exception e){
            System.err.print(e);
            if(tr!=null){
                tr.rollback();
            }
            flag=false;
        }
        
        s.close();
         return flag;
     }
     
     public static boolean freezeUnfreezeAccount(String value,long account_num){
         boolean flag;         
         Session s=new DBOperation().openSession();
         Transaction tr=s.beginTransaction();
         Query q=s.createQuery("update Customer set netBankingAllowed=:value where account_number=:anum");
         if("yes".equals(value)){
            q.setParameter("value", "no");
         }
         else{
            q.setParameter("value", "yes");
         }
         q.setParameter("anum", account_num);
         int res=q.executeUpdate();
         System.out.println("Column NetBankingAllowed Updated for Account Number : "+account_num);
         try{
           tr.commit();
           flag=true;
         }catch(Exception e){
            System.err.print(e);
            if(tr!=null){
                tr.rollback();
            }
            flag=false;
        }
        
        s.close();
         return flag;
     }
//-----------------------------------------------------------------------------      
     //CUSTOMER module
//-----------------------------------------------------------------------------      

    public static Customer loginCustomer(String username,String password){
        Session s=new DBOperation().openSession();
        Criteria cr=s.createCriteria(Customer.class);
        cr.add(Restrictions.eq("username", username));
        cr.add(Restrictions.eq("password", password));
        List<Customer> list=cr.list();
        Customer c=null;
        for(Customer cus:list){
            c=cus;
        }
        return c;        
    }
     
    public static void registerCustomer(String query){
        Session s=new DBOperation().openSession();
        Transaction tr=s.beginTransaction();
        int rs=s.createQuery(query).executeUpdate();
        tr.commit();
        s.close();
    }
     
   
//-----------------------------------------------------------------------------      
     
     //TRANSACTION 
//-----------------------------------------------------------------------------      
     public static boolean commitTransaction(Transactions t){
         Session s=new DBOperation().openSession();
         boolean flag;         
         Transaction tr=s.beginTransaction();         
         s.save(t);         
         try{
           tr.commit();
           flag=true;
        }catch(Exception e){
            System.err.print(e);
            if(tr!=null){
                tr.rollback();
            }
            flag=false;
        }        
        s.close();
        return flag;         
     }
     
     public static List<Transactions> fetchTransactions(long account_number){
         Session s=new DBOperation().openSession();
         Query<Transactions> query=s.createQuery("from Transactions where account_number="+account_number);
         List<Transactions> list=query.list();
         s.close();
         return list;
     }
     
//-----------------------------------------------------------------------------      
}
