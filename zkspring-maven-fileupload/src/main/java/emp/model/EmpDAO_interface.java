package emp.model;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.session.SqlSessionFactory;

//@Component("empDAO")
public interface EmpDAO_interface {

	
	public void insert (@Param(value = "empVO") EmpVO empVO);

	public void update(@Param(value = "empVO") EmpVO empVO);

	public void delete(@Param(value = "empno") Integer empno);

	public EmpVO findByPrimaryKey(@Param(value = "empno") Integer empno);

	public List<EmpVO> getAll();
	// public List<EmpVO> getAll(Map<String, String[]> map);
}
