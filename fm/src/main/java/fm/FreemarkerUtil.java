package fm;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public class FreemarkerUtil {
	//getTemplate("01.ftl")
	public Template getTemplate(String name) {
		try {
			// 第一步：创建一个Configuration对象，直接new一个对象。构造方法的参数就是freemarker对于的版本号。
			Configuration configuration = new Configuration(Configuration.getVersion());
			// 第二步：设置模板文件所在的路径。
			configuration.setClassForTemplateLoading(this.getClass(),"/ftl");
			// 第三步：设置模板文件使用的字符集。一般就是utf-8.
			configuration.setDefaultEncoding("utf-8");
			// 第四步：加载一个模板，创建一个模板对象。
			Template template = configuration.getTemplate(name);
			return template;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 输出到控制台
	 * @param name
	 * @param root
	 */
	public void print(String name,Map<String,Object> dataModel) {
		try {
			//通过Template可以将模板文件输出到相应的流
			Template temp = this.getTemplate(name);
			temp.process(dataModel, new PrintWriter(System.out));//输出到控制台
		} catch (TemplateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 输出到文件
	 * @param name
	 * @param root
	 * @param outFile
	 */
	public void fprint(String name,Map<String,Object> dataModel,String outFile) {
		FileWriter out = null;
		try {
			//通过一个文件输出流，就可以写到相应的文件中
			Template temp = this.getTemplate(name);
			out = new FileWriter(new File("D:/"+outFile));
			temp.process(dataModel, out);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (TemplateException e) {
			e.printStackTrace();
		} finally {
			try {
				if(out!=null) out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
}
