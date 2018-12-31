package emp.vm;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.zkoss.bind.BindContext;
import org.zkoss.bind.Converter;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.UiException;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.ListModel;

import emp.model.EmpDAO_interface;
import emp.model.EmpHibernateDAO;
import emp.model.EmpService;
import emp.model.EmpVO;


@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
//@ComponentScan
public class EmpVm extends SelectorComposer<Component> 
//implements Converter 
{
//	@WireVariable
	private EmpVO empVO = new EmpVO();	
	@WireVariable("empDAO")
	private EmpHibernateDAO empDAO ;
	//@WireVariable
	private List<EmpVO> emps;
//	@WireVariable("EmpService")
//	private EmpService service ;
//	private ApplicationContext context = new ClassPathXmlApplicationContext("beans-config.xml");
//	private EmpDAO_interface empDAO =(EmpDAO_interface) context.getBean("empDAO");
	
	public List<EmpVO> getEmps() {
		return emps;
	}

	public void setEmps(List<EmpVO> emps) {
		this.emps = emps;
	}


	
	@Init
	public void init2(){
		this.emps = empDAO.getAll();				
	}
	
	public EmpVO getEmpvo() {
		return empVO;
	}

	public void setEmpvo(EmpVO empvo) {
		this.empVO = empvo;
	}
	@Command
	@NotifyChange("empvo")
	public void getOneEmp(){
		this.empVO = empDAO.findByPrimaryKey(empVO.getEmpno());		
	}
	
	public void getAll(){
		this.emps = empDAO.getAll();
		
	}		
	@Command
	@NotifyChange("empvo")
	public void addEmp(){
		
		empDAO.insert(empVO);
		
	}
	
	public void deleteEmp(){
		empDAO.delete(empVO.getEmpno());
	}
	
//	public Object coerceToUi(Object val, Component comp, BindContext ctx) {
//        //user sets format in annotation of binding or args when calling binder.addPropertyBinding()
//		//if val is a Date
//		 /**
//	     * Convert Date to String.
//	     * @param val date to be converted
//	     * @param comp associated component
//	     * @param ctx bind context for associate Binding and extra parameter (e.g. format)
//	     * @return the converted String
//	     */
////		if(val instanceof java.sql.Date){
////			final String format = (String) ctx.getConverterArg("format");
////	        if (format == null) throw new NullPointerException("format attribute not found");
////	        final Date date = (Date) val;
////	        return date == null ? null : new SimpleDateFormat(format).format(date);
////        	}//end if
////		else{
//			//if val is a string
//			final String format = (String) ctx.getConverterArg("format");
//	        if (format == null) throw new NullPointerException("format attribute not found");	        
//	        final Date date = Date.valueOf("2012-02-02");
//	        return date == null ? null : val;
//			}//else
////        }
//    /**
//     * Convert String to Date.
//     * @param val date in string form
//     * @param comp associated component
//     * @param ctx bind context for associate Binding and extra parameter (e.g. format)
//     * @return the converted Date
//     */
//    public Object coerceToBean(Object val, Component comp, BindContext ctx) {
//    	// if val is a Date
//    	if(val instanceof java.sql.Date){
//	    	final String format = (String) ctx.getConverterArg("format");
//	        if (format == null) throw new NullPointerException("format attribute not found");
//	        SimpleDateFormat sdf = new SimpleDateFormat(format);
//	      	final String date = sdf.format(val);
//	        try {
//	            return date == null ? null : new SimpleDateFormat(format).parse(date);
//	        } catch (ParseException e) {
//	            throw UiException.Aide.wrap(e);
//	        }
//    	}//end if
////    	if val is a String
//    	else{
//	    	final String format = (String) ctx.getConverterArg("format");
//	        if (format == null) throw new NullPointerException("format attribute not found");
//	        final String date = (String) val;
//	        try {
//	            return date == null ? null : new SimpleDateFormat(format).parse(date);
//	        } catch (ParseException e) {
//	            throw UiException.Aide.wrap(e);
//	        }
//    	}//end else
//    }
}
