package emp.model;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.zkoss.zk.ui.select.annotation.WireVariable;
@Component("empService")
public class EmpServiceImp implements EmpService{
	@Autowired
	@Qualifier("empDAO")
	private EmpDAO_interface dao;

//	public EmpService() {
//		dao = new EmpHibernateDAO();
//	}

	public EmpDAO_interface getDao() {
		return dao;
	}

	public void setDao(EmpDAO_interface dao) {
		this.dao = dao;
	}
	
	public EmpVO insert(String ename, String job, java.sql.Date hiredate,
			Double sal, Double comm, Integer deptno) {

		EmpVO empVO = new EmpVO();

		empVO.setEname(ename);
		empVO.setJob(job);
		empVO.setHiredate(hiredate);
		empVO.setSal(sal);
		empVO.setComm(comm);
		empVO.setDeptno(deptno);
		dao.insert(empVO);

		return empVO;
	}
	
	
	public void insert(EmpVO empVO) {
		dao.insert(empVO);

				
	}
	
	
	public EmpVO update(Integer empno, String ename, String job,
			java.sql.Date hiredate, Double sal, Double comm, Integer deptno) {

		EmpVO empVO = new EmpVO();

		empVO.setEmpno(empno);
		empVO.setEname(ename);
		empVO.setJob(job);
		empVO.setHiredate(hiredate);
		empVO.setSal(sal);
		empVO.setComm(comm);
		empVO.setDeptno(deptno);
		dao.update(empVO);

		return dao.findByPrimaryKey(empno);
	}
	
	
	public void update(EmpVO empVO) {
		dao.update(empVO);
	}

	public void delete(Integer empno) {
		dao.delete(empno);
		
	}

	public EmpVO findByPrimaryKey(Integer empno) {
		return dao.findByPrimaryKey(empno);
	}
	@Override
	public List<EmpVO> getAll() {
		return dao.getAll();
	}
	//for transaction test
	@Transactional(propagation = Propagation.REQUIRED)
	public void testInsert(EmpVO empVO){
		dao.insert(empVO);
	}
	
	//REQUIRES_NEW
	@Transactional(propagation = Propagation.REQUIRED)
	public void testUpdate(EmpVO empVO){
		dao.update(empVO);
	}
	
	public void testDelete(Integer empno){
		dao.delete(empno);
		throw new RuntimeException("all lie here");
	}
	@Transactional(propagation = Propagation.REQUIRED)
	public void comprehensivetest(EmpVO empVO, Integer empno) {
		dao.insert(empVO);
		System.out.println("test1 insert");
		
		empVO.setEmpno(7001);
		dao.update(empVO);
		System.out.println("test1 testUpdate");
		dao.delete(empno);
		System.out.println("test1 delete");
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void comprehensivetest2(EmpVO empVO, Integer empno) {

		dao.insert(empVO);
		empVO.setEmpno(7002);
		Date hiredate = Date.valueOf("2010-03-01");
		empVO.setHiredate(hiredate);
		dao.update(empVO);
		System.out.println("test2 testUpdate");

		dao.delete(empno);
		System.out.println("test2 delete");
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void comprehensivetest3(EmpVO empVO, Integer empno) {
		dao.insert(empVO);
		System.out.println("test3 insert");

		// throw exception
		empVO.setEmpno(7003);
		empVO.setHiredate(null);
		dao.update(empVO);

		dao.delete(empno);
		System.out.println("test3 delete");
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void comprehensivetest4(EmpVO empVO, Integer empno) {
		dao.insert(empVO);
		System.out.println("test4 insert");

		empVO.setEmpno(7004);
		dao.update(empVO);
		System.out.println("test4 update");
		// throw exception
		dao.delete(empno);
		System.out.println("test4 delete");
	}
}
