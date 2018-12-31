package test;
import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.WireVariable;

import emp.model.EmpDAO_interface;
import emp.model.EmpService;
import emp.model.EmpServiceImp;
import emp.model.EmpVO;


public class Transactiontest {
//	@Autowired
	private EmpService empService;
	
	public EmpService getEmpService() {
		return empService;
	}

	public void setEmpService(EmpService empService) {
		this.empService = empService;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void comprehensivetest(EmpVO empVO, Integer empno) {
		empService.testInsert(empVO);
		System.out.println("test1 insert");
		
		empVO.setEmpno(7001);
		empService.testUpdate(empVO);
		System.out.println("test1 testUpdate");
		empService.testDelete(empno);
		System.out.println("test1 delete");
	}
	
	@Transactional(propagation = Propagation.REQUIRED)
	public void comprehensivetest2(EmpVO empVO, Integer empno) {

		empService.insert(empVO);
		empVO.setEmpno(7002);
		Date hiredate = Date.valueOf("2010-03-01");
		empVO.setHiredate(hiredate);
		empService.update(empVO);
		System.out.println("test2 testUpdate");

		empService.delete(empno);
		System.out.println("test2 delete");
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void comprehensivetest3(EmpVO empVO, Integer empno) {
		empService.insert(empVO);
		System.out.println("test3 insert");

		// throw exception
		empVO.setEmpno(7003);
		empVO.setHiredate(null);
		empService.update(empVO);

		empService.delete(empno);
		System.out.println("test3 delete");
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void comprehensivetest4(EmpVO empVO, Integer empno) {
		empService.insert(empVO);
		System.out.println("test4 insert");

		empVO.setEmpno(7004);
		empService.update(empVO);
		System.out.println("test4 update");
		// throw exception
		empService.delete(empno);
		System.out.println("test4 delete");
	}

	

	
}
