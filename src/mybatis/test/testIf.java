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

public class testIf {
	public static void main(String[] args) throws IOException {
		String resource = "mybatis-config.xml";
		InputStream inputStream = org.apache.ibatis.io.Resources.getResourceAsStream(resource);
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		SqlSession session = sqlSessionFactory.openSession();

		// System.out.println("查询所有的");
		// List<Product> products = session.selectList("listProduct1");
		// for (Product product : products) {
		// System.out.println(product);
		// }
		//
		// System.out.println("模糊查询");
		// Map<String, Object> params = new HashMap<String, Object>();
		// params.put("name", "a");
		// List<Product> products2 = session.selectList("listProductByName",
		// params);
		// for (Product product : products2) {
		// System.out.println(product);
		// }

		System.out.println("查询所有的");
		List<Product> products = session.selectList("listProduct2");
		for (Product product : products) {
			System.out.println(product);
		}

		System.out.println("模糊查询");
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("name", "a");
		List<Product> products2 = session.selectList("listProduct2", params);
		for (Product product : products2) {
			System.out.println(product);
		}
		session.commit();
		session.close();
	}
}
