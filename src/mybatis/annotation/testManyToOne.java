package mybatis.annotation;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import mybatis.mapper.ProductMapper;
import mybatis.pojo.Product;

public class testManyToOne {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		String resource = "mybatis-config.xml";
		InputStream inputStream = org.apache.ibatis.io.Resources.getResourceAsStream(resource);
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		SqlSession session = sqlSessionFactory.openSession();
		ProductMapper productMapper = session.getMapper(ProductMapper.class);

		List<Product> products = productMapper.list();
		for (Product product : products) {
			System.out.println(product + "\t对应的分类是：\t" + product.getCategory().getName());
		}

		session.commit();
		session.close();
	}

}
