package emp.model;

/*
 Hibernate is providing a factory.getCurrentSession() method for retrieving the current session. A
 new session is opened for the first time of calling this method, and closed when the transaction is
 finished, no matter commit or rollback. But what does it mean by the ��current session��? We need to
 tell Hibernate that it should be the session bound with the current thread.

 <hibernate-configuration>
 <session-factory>
 ...
 <property name="current_session_context_class">thread</property>
 ...
 </session-factory>
 </hibernate-configuration>

 */

import org.hibernate.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import java.util.*;
public class EmpHibernateDAO implements EmpDAO_interface {

	private static final String GET_ALL_STMT = "from EmpVO order by empno";
	
	private SessionFactory sessionFactory;
	public void setSessionFactory(SessionFactory sessionFactory) {
	   this.sessionFactory = sessionFactory;
	}

	@Override
	public void insert(EmpVO empVO) {
		Session session = sessionFactory.getCurrentSession();
		try {
			session.beginTransaction();
			session.saveOrUpdate(empVO);
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
	}

	@Override
	public void update(EmpVO empVO) {
		Session session = sessionFactory.getCurrentSession();
		try {
			session.beginTransaction();
			session.saveOrUpdate(empVO);
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
	}

	@Override
	public void delete(Integer empno) {
		Session session = sessionFactory.getCurrentSession();
		try {
			session.beginTransaction();

//        �i���ɦh��(�y)�i�ĥ�HQL�R���j
//			Query query = session.createQuery("delete EmpVO where empno=?");
//			query.setParameter(0, empno);
//			System.out.println("�R��������=" + query.executeUpdate());

//        �i�Φ��ɦh��(�])�i�ĥΥh�����p���Y��A�A�R�����覡�j
			EmpVO empVO = new EmpVO();
			empVO.setEmpno(empno);
			session.delete(empVO);

//        �i���ɦh�褣�i(���y)�ĥ�cascade�p�ŧR���j
//        �i�h��emp2.hbm.xml�p�G�]�� cascade="all"�� cascade="delete"�N�|�R���Ҧ��������-�]�A���ݳ����P�P�������䥦���u�N�|�@�ֳQ�R���j
//			EmpVO empVO = (EmpVO) session.get(EmpVO.class, empno);
//			session.delete(empVO);

			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
	}

	@Override
	public EmpVO findByPrimaryKey(Integer empno) {
		EmpVO empVO = null;
		Session session = sessionFactory.getCurrentSession();
		try {
			session.beginTransaction();
			empVO = (EmpVO) session.get(EmpVO.class, empno);
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
		return empVO;
	}

	@Override
	public List<EmpVO> getAll() {
		List<EmpVO> list = null;
		Session session = sessionFactory.getCurrentSession();
		try {
			session.beginTransaction();
			Query query = session.createQuery(GET_ALL_STMT);
			list = query.list();
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
		return list;
	}

	public static void main(String[] args) {

		//EmpHibernateDAO dao = new EmpHibernateDAO();
		//����K�@�����ε{��main�誺����,�ҥH���U��beans-config.xml����dataSource�]�w�O�ĥ�org.springframework.jdbc.datasource.DriverManagerDataSource
		ApplicationContext context = new ClassPathXmlApplicationContext("beans-config.xml");
        
        // �إ�DAO����
		EmpDAO_interface dao =(EmpDAO_interface) context.getBean("empDAO");

		//�� �s�W


//		EmpVO empVO1 = new EmpVO();
//		empVO1.setEname("�d�ç�1");
//		empVO1.setJob("MANAGER");
//		empVO1.setHiredate(java.sql.Date.valueOf("2005-01-01"));
//		empVO1.setSal(new Double(50000));
//		empVO1.setComm(new Double(500));
//		empVO1.setDeptno(10);
//		dao.insert(empVO1);



		//�� �ק�
//		EmpVO empVO2 = new EmpVO();
//		empVO2.setEmpno(7001);
//		empVO2.setEname("�d�ç�2");
//		empVO2.setJob("MANAGER2");
//		empVO2.setHiredate(java.sql.Date.valueOf("2002-01-01"));
//		empVO2.setSal(new Double(20000));
//		empVO2.setComm(new Double(200));
//		empVO2.setDeptno(10);
//		dao.update(empVO2);



		//�� �R��(�p��cascade - �h��emp2.hbm.xml�p�G�]�� cascade="all"��
		// cascade="delete"�N�|�R���Ҧ��������-�]�A���ݳ����P�P�������䥦���u�N�|�@�ֳQ�R��)
//		dao.delete(7014);



		//�� �d��-findByPrimaryKey (�h��emp2.hbm.xml�����]��lazy="false")(�u!)
//		EmpVO empVO3 = dao.findByPrimaryKey(7001);
//		System.out.print(empVO3.getEmpno() + ",");
//		System.out.print(empVO3.getEname() + ",");
//		System.out.print(empVO3.getJob() + ",");
//		System.out.print(empVO3.getHiredate() + ",");
//		System.out.print(empVO3.getSal() + ",");
//		System.out.print(empVO3.getComm() + ",");
//		// �`�N�H�U�T�檺�g�k (�u!)
//		System.out.print(empVO3.getDeptno() + ",");
//		System.out.print(empVO3.getDeptno() + ",");
//		System.out.print(empVO3.getDeptno());
//		System.out.println("\n---------------------");



		//�� �d��-getAll (�h��emp2.hbm.xml�����]��lazy="false")(�u!)
		List<EmpVO> list = dao.getAll();
		for (EmpVO aEmp : list) {
			System.out.print(aEmp.getEmpno() + ",");
			System.out.print(aEmp.getEname() + ",");
			System.out.print(aEmp.getJob() + ",");
			System.out.print(aEmp.getHiredate() + ",");
			System.out.print(aEmp.getSal() + ",");
			System.out.print(aEmp.getComm() + ",");
			// �`�N�H�U�T�檺�g�k (�u!)
			System.out.print(aEmp.getDeptno()+ ",");
			System.out.print(aEmp.getDeptno() +",");
			System.out.print(aEmp.getDeptno());
			System.out.println();
		}
	}
}
