package emp.vm;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.zkoss.bind.BindContext;
import org.zkoss.bind.Converter;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.util.media.Media;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.UiException;
import org.zkoss.zk.ui.event.UploadEvent;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.ListModel;

import emp.model.EmpDAO_interface;
import emp.model.EmpService;
import emp.model.EmpServiceImp;
//import emp.model.EmpHibernateDAO;
//import emp.model.EmpService;
import emp.model.EmpVO;

@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
// @ComponentScan
public class EmpVm extends SelectorComposer<Component>
// implements Converter
{
	// @WireVariable
	private EmpVO empVO = new EmpVO();
	// @WireVariable("empDAO")
	// private EmpDAO_interface empDAO ;

	@WireVariable("empService")
	private EmpService empService;
	// @WireVariable
	private List<EmpVO> emps;
	// @WireVariable("EmpService")
	// private EmpService service ;
	// private ApplicationContext context = new
	// ClassPathXmlApplicationContext("beans-config.xml");
	// private EmpDAO_interface empService =(EmpDAO_interface)
	// context.getBean("empDAO");

	private String uploadedfile;

	public String getUploadedfile() {
		return uploadedfile;
	}

	public void setUploadedfile(String uploadedfile) {
		this.uploadedfile = uploadedfile;
	}

	public List<EmpVO> getEmps() {
		return emps;
	}

	public void setEmps(List<EmpVO> emps) {
		this.emps = emps;
	}

	@Init
	public void init2() {
		this.emps = empService.getAll();
	}

	public EmpVO getEmpvo() {
		return empVO;
	}

	public void setEmpvo(EmpVO empvo) {
		this.empVO = empvo;
	}

	@Command
	@NotifyChange("empvo")
	public void getOneEmp() {

		this.empVO = empService.findByPrimaryKey(empVO.getEmpno());
	}

	public void getAll() {
		this.emps = empService.getAll();

	}

	@Command
	@NotifyChange("empvo")
	public void addEmp() {

		empService.insert(empVO);

	}

	public void deleteEmp() {
		empService.delete(empVO.getEmpno());
	}
//	简单来说，
//	write(byte[] b, int off, int len)就是将数组 b 中的 len 个字节按顺序写入输出流。
//	所以如果 b 为 null，则抛出 NullPointerException。如果 off 为负，或 len 为负，又或者 off+len 大于数组 b 的长度，
//	则抛出 IndexOutOfBoundsException。如果 len 为零，则不写入字节。
//	否则，首先写入字节 b[off]，然后写入字节 b[off+1]，依此类推；最后一个写入字节是 b[off+len-1]。
//	楼主的问题是off+len>b.length了，就是写入的输出字节数超出了要写入的字节的长度；
//	就好像我要只有4个字节的文字，却要输出到第5个字节，那当然会报错啦~~

	@Command("fileUpload")
	 @NotifyChange("uploadedfilep")
	public void fileUpload(@BindingParam("uploadedfilep") Media files) {
		uploadedfile = files.getName();
		File file = new File(uploadedfile);
		System.out.println(uploadedfile);
		InputStream in = null;
		OutputStream out = null;
		String savingplace = "C:\\Users\\asus\\Desktop\\資拓上傳練習";
		try {
			in = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		BufferedInputStream bf = new BufferedInputStream(in);
		byte[] bt = new byte[1024];
		try {
			while (bf.read(bt) != -1) {
				out = new FileOutputStream(savingplace);
				System.out.println("before writing");
				System.out.println(bt);
				out.write(bt, 0, bt.length);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	@Command			
	public void onFileUploadByte(@ContextParam(ContextType.BIND_CONTEXT) BindContext ctx) {		
		UploadEvent upEvent = null;	
		Object objUploadEvent = ctx.getTriggerEvent();	
			
		if (objUploadEvent != null && (objUploadEvent instanceof UploadEvent)) {	
			upEvent = (UploadEvent) objUploadEvent;
			
			Media media = upEvent.getMedia();
			
			// Initialize components
			String name = media.getName();
			String format = media.getFormat();
			System.out.println(name);
			byte[] bFile = media.getByteData();
			
			InputStream in = null;
			OutputStream out = null;
			String savingplace = "C:\\Users\\asus\\Desktop\\資拓上傳練習\\pfile.jpg";
			File fileo = new File(savingplace);
			if(!fileo.exists())
			{
			    fileo.getParentFile().mkdirs();
			    try {
			        fileo.createNewFile();
			    } catch (IOException e) {
			        e.printStackTrace();
			    }
			}
			in = new ByteArrayInputStream(bFile);
			BufferedInputStream bf = new BufferedInputStream(in);			
			try {
				//如果自己指定byte陣列大小會有可能不夠大
				byte[] bt = new byte[bf.available()];
				
				//this int is placed for the purpose to get the bytes actually read, in order to avoid writing duplicated data in write()。
				int read =0;
				//read to byte[], and then write
				while (( read=bf.read(bt)) != -1) {
					out = new FileOutputStream(fileo);
					System.out.println(read);
					//taking data from the same byte[] bucket and write them out.
					out.write(bt, 0, read);
					out.flush();
					out.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}

		}	
	}		
	@Command			
	public void onFileUploadString(@ContextParam(ContextType.BIND_CONTEXT) BindContext ctx) {		
		UploadEvent upEvent = null;	
		Object objUploadEvent = ctx.getTriggerEvent();	
			
		if (objUploadEvent != null && (objUploadEvent instanceof UploadEvent)) {	
			upEvent = (UploadEvent) objUploadEvent;
			
			Media media = upEvent.getMedia();
			
			// Initialize components
			String name = media.getName();
			String format = media.getFormat();
			System.out.println(name);
			String bfile = media.getStringData();
			
			Reader in = null;
			FileWriter out = null;
			String savingplace = "C:\\Users\\asus\\Desktop\\資拓上傳練習\\pfile.txt";
			File fileo = new File(savingplace);
			if(!fileo.exists())
			{
			    fileo.getParentFile().mkdirs();
			    try {
			        fileo.createNewFile();
			    } catch (IOException e) {
			        e.printStackTrace();
			    }
			}
			in = media.getReaderData();
			BufferedReader bf = new BufferedReader(in);
			
			//this int is placed for the purpose to get the bytes actually read, in order to avoid writing duplicated data in write()。
			int read = 0;
			char[] bt = new char[bfile.length()];
			//read to byte[], and then write
			try {
				while ((read =bf.read(bt)) != -1) {
					out = new FileWriter(fileo);
					System.out.println(bt);
					//taking data from the same byte[] bucket and write them out.
					out.write( bt, 0, read);
					out.flush();
					out.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}

		}	
	}	
	@Command			
	public void onFileUploadByte2(@BindingParam("byteparam") UploadEvent byteparam ) {		


			
			Media media =byteparam.getMedia();
			InputStream in =byteparam.getMedia().getStreamData();
			// Initialize components
			String name = media.getName();
			String format = media.getFormat();
			System.out.println(name);
			byte[] bFile = media.getByteData();
			
			OutputStream out = null;
			String savingplace = "C:\\Users\\asus\\Desktop\\資拓上傳練習\\pfile.jpg";
			File fileo = new File(savingplace);
			if(!fileo.exists())
			{
			    fileo.getParentFile().mkdirs();
			    try {
			        fileo.createNewFile();
			    } catch (IOException e) {
			        e.printStackTrace();
			    }
			}
			in = new ByteArrayInputStream(bFile);
			BufferedInputStream bf = new BufferedInputStream(in);			
			try {
				//read to byte[], and then write
				byte[] bt = new byte[bf.available()];			
				//this int is placed for the purpose to get the bytes actually read, in order to avoid writing duplicated data in write()。
				int read = 0;				
				while ( (read=bf.read(bt)) != -1) {
					out = new FileOutputStream(fileo);
					System.out.println(bt);
					//taking data from the same byte[] bucket and write them out.
					out.write(bt, 0, read);
					out.flush();
					out.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}

		}	
		
	@Command			
	public void onFileUploadString2(@BindingParam("stringparam") UploadEvent byteparam) {		
	

			Media media =byteparam.getMedia();
			
			// Initialize components
			String name = media.getName();
			String format = media.getFormat();
			System.out.println(name);
			String bfile = media.getStringData();
			
			Reader in = null;
			FileWriter out = null;
			String savingplace = "C:\\Users\\asus\\Desktop\\資拓上傳練習\\pfile.txt";
			File fileo = new File(savingplace);
			if(!fileo.exists())
			{
			    fileo.getParentFile().mkdirs();
			    try {
			        fileo.createNewFile();
			    } catch (IOException e) {
			        e.printStackTrace();
			    }
			}
			in = media.getReaderData();
			BufferedReader bf = new BufferedReader(in);
			
			//read to char[], and then write
			char[] bt = new char[bfile.length()];	
			//this int is placed for the purpose to get the bytes actually read, in order to avoid writing duplicated data in write()。
			int read = 0;		
			try {
				while ((read=bf.read(bt)) >-1) {
					out = new FileWriter(fileo);
					System.out.println(bt);
					//taking data from the same byte[] bucket and write them out.
					out.write( bt, 0, read);
					out.flush();
					out.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}

		
	}		
}
