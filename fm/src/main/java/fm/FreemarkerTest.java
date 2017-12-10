package fm;

import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;


import freemarker.template.Configuration;
import freemarker.template.Template;

public class FreemarkerTest {

	@Test
	public void test01() throws Exception {
		
		
//		第一步：创建一个Configuration对象，直接new一个对象。构造方法的参数就是freemarker对应的版本号。
		Configuration configuration = new Configuration(Configuration.getVersion());
		
//		第二步：设置模板文件所在的路径。
		configuration.setClassForTemplateLoading(this.getClass(), "/ftl");
		
//		第三步：设置模板文件使用的字符集。一般就是utf-8.
		configuration.setDefaultEncoding("utf-8");
		
//		第四步：加载一个模板，创建一个模板对象。
		Template template = configuration.getTemplate("01.ftl");
		
//		第五步：创建一个模板使用的数据集，可以是pojo也可以是map。一般是Map。
		Map<String, Object> dataModel = new HashMap<String, Object>();
		dataModel.put("username", "Tom");
		
//		第六步：创建一个Writer对象，一般创建一FileWriter对象，指定生成的文件名。
		Writer out = new FileWriter(new File("d:/01.html"));
		
//		第七步：调用模板对象的process方法输出文件。
		template.process(dataModel, out);
		
//		第八步：关闭流。
		out.close();
		
	}
	
	@Test
	public void test02() {
		
		FreemarkerUtil fu = new FreemarkerUtil();
		
		Map<String, Object> dataModel = new HashMap<String, Object>();
		dataModel.put("username", "Tom");
		
		fu.print("02.ftl", dataModel);
		fu.fprint("02.ftl", dataModel, "02.html");
	}
	
	@Test
	public void test03() {
		
		FreemarkerUtil fu = new FreemarkerUtil();
		
		User user = new User(1, "Annie", 25);
		
		Map<String, Object> dataModel = new HashMap<String, Object>();
		dataModel.put("user", user);
		
		fu.print("03.ftl", dataModel);
		fu.fprint("03.ftl", dataModel, "03.html");
	}
	
	@Test
	public void test04() {
		
		FreemarkerUtil fu = new FreemarkerUtil();
		
		List<User> list = new ArrayList<User>();
		for(int i = 0; i < 10; i++) {
			User user = new User(i + 1, "name" + i, i + 1);
			list.add(user);
		}
		
		
		Map<String, Object> dataModel = new HashMap<String, Object>();
		dataModel.put("list", list);
		
		fu.print("04.ftl", dataModel);
		fu.fprint("04.ftl", dataModel, "04.html");
	}
	
	/**
	 * include 
	 */
	@Test
	public void test05() {
		
		FreemarkerUtil fu = new FreemarkerUtil();
		
		List<User> list = new ArrayList<User>();
		for(int i = 0; i < 10; i++) {
			User user = new User(i + 1, "name" + i, i + 1);
			list.add(user);
		}
		
		
		Map<String, Object> dataModel = new HashMap<String, Object>();
		dataModel.put("list", list);
		
		fu.print("05.ftl", dataModel);
		fu.fprint("05.ftl", dataModel, "05.html");
	}
	
	
	/**
	 * 空值处理
	 */
	@Test
	public void test06() {
		
		FreemarkerUtil fu = new FreemarkerUtil();
		
		User user = new User();
		user.setId(1);
		user.setAge(10);
		//没有姓名
		
		Map<String, Object> dataModel = new HashMap<String, Object>();
		dataModel.put("user", user);
		
		fu.print("06.ftl", dataModel);
		fu.fprint("06.ftl", dataModel, "06.html");
	}
}
