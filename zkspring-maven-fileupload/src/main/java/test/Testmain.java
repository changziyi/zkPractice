package test;
import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import emp.model.EmpService;
import emp.model.EmpVO;

public class Testmain {
		
//	@Autowired	
//	private static Transactiontest transactiontest;
	
	public static void main(String[] args) {

		ApplicationContext context = new ClassPathXmlApplicationContext("beans-config.xml");
		Transactiontest transactiontest =(Transactiontest) context.getBean("transactiontest");

		// test1
		EmpVO empVO = new EmpVO();
		empVO.setEname("cat");
		empVO.setComm(12.0);
		empVO.setDeptno(10);
		Date hiredate = Date.valueOf("2010-01-01");
		empVO.setHiredate(hiredate);
		empVO.setJob("as");
		empVO.setSal(12.0);
		transactiontest.comprehensivetest(empVO, 7003);

		// //test2
		// EmpVO empVO2 = new EmpVO();
		// empVO2.setEname("cat2");
		// empVO2.setComm(120.0);
		// empVO2.setDeptno(10);
		// Date hiredate2 = Date.valueOf("2010-03-01");
		// empVO2.setHiredate(hiredate2);
		// empVO2.setJob("mu");
		// empVO2.setSal(120.0);
		// ts.comprehensivetest2(empVO2,7002);
		//
		// //test3
		// EmpVO empVO3 = new EmpVO();
		// empVO3.setEname("cat3");
		// empVO3.setComm(120.0);
		// empVO3.setDeptno(10);
		// Date hiredate3 = Date.valueOf("2010-03-01");
		// empVO3.setHiredate(hiredate3);
		// empVO3.setJob("mu");
		// empVO3.setSal(120.0);
		// ts.comprehensivetest3(empVO3,7003);
		//
		// //test4
		// EmpVO empVO4 = new EmpVO();
		// empVO4.setEname("cat4");
		// empVO4.setComm(120.0);
		// empVO4.setDeptno(10);
		// Date hiredate4 = Date.valueOf("2010-03-01");
		// empVO4.setHiredate(hiredate4);
		// empVO4.setJob("mu");
		// empVO4.setSal(120.0);
		// ts.comprehensivetest4(empVO4,7004);
	}
}
