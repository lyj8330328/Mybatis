package mybatis.test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import mybatis.pojo.Category;
import mybatis.pojo.Product;

public class testOneToMany {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		String resource = "mybatis-config.xml";
		InputStream inputStream = org.apache.ibatis.io.Resources.getResourceAsStream(resource);
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		SqlSession session = sqlSessionFactory.openSession();
		List<Category> categories = session.selectList("listCategory");
		for (Category c : categories) {
			System.out.println(c);
			List<Product> products = c.getProducts();
			for (Product product : products) {
				System.out.println("\t" + product.toString());
			}
		}
		session.commit();
		session.close();
	}

}
