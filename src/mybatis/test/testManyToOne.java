package mybatis.test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import mybatis.pojo.Category;
import mybatis.pojo.Product;

public class testManyToOne {

	public static void update() throws IOException {
		String resource = "mybatis-config.xml";
		InputStream inputStream = org.apache.ibatis.io.Resources.getResourceAsStream(resource);
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		SqlSession session = sqlSessionFactory.openSession();

		Category category = session.selectOne("getCategory", 1);
		Product product = session.selectOne("getProduct", 6);
		product.setCategory(category);
		session.update("updateProduct2", product);

		// Map<String, Integer> params = new HashMap<String, Integer>();
		// params.put("cid", category.getId());
		// params.put("pid", product.getId());
		//
		// session.update("updateProduct", params);

		session.commit();
		session.close();
	}

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		update();
		String resource = "mybatis-config.xml";
		InputStream inputStream = org.apache.ibatis.io.Resources.getResourceAsStream(resource);
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		SqlSession session = sqlSessionFactory.openSession();

		List<Product> products = session.selectList("listProduct");
		for (Product product : products) {
			System.out.println(product + "\t对应的分类是：" + product.getCategory());

		}

		session.commit();
		session.close();

	}

}
