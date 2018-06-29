package mybatis.test;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import mybatis.pojo.Product;

public class testWhere {
	public static void main(String[] args) throws IOException {
		String resource = "mybatis-config.xml";
		InputStream inputStream = org.apache.ibatis.io.Resources.getResourceAsStream(resource);
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		SqlSession session = sqlSessionFactory.openSession();

		// Product product = new Product();
		// product.setId(6);
		// product.setName("product xzz");
		// product.setPrice(6.99f);
		// session.update("updateProduct2", product);

		System.out.println("多条件查询");
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("name", "a");
		params.put("price", "10");
		List<Product> products2 = session.selectList("listProduct6", params);
		for (Product products : products2) {
			System.out.println(products);
		}

		session.commit();
		session.close();
	}
}
