package mybatis.test;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import mybatis.pojo.Product;

public class testForEach {
	public static void main(String[] args) throws IOException {
		String resource = "mybatis-config.xml";
		InputStream inputStream = org.apache.ibatis.io.Resources.getResourceAsStream(resource);
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		SqlSession session = sqlSessionFactory.openSession();

		List<Integer> idsIntegers = new ArrayList<Integer>();
		idsIntegers.add(1);
		idsIntegers.add(3);
		idsIntegers.add(5);

		List<Product> products = session.selectList("listProduct7", idsIntegers);

		for (Product product : products) {
			System.out.println(product);
		}

		session.commit();
		session.close();
	}
}
