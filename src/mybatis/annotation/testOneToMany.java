package mybatis.annotation;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import mybatis.mapper.CategoryMapper;
import mybatis.pojo.Category;
import mybatis.pojo.Product;

public class testOneToMany {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		String resource = "mybatis-config.xml";
		InputStream inputStream = org.apache.ibatis.io.Resources.getResourceAsStream(resource);
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		SqlSession session = sqlSessionFactory.openSession();
		CategoryMapper categoryMapper = session.getMapper(CategoryMapper.class);

		listAll(categoryMapper);

		session.commit();
		session.close();
	}

	private static void listAll(CategoryMapper mapper) {
		List<Category> categories = mapper.list2();
		for (Category c : categories) {
			System.out.println(c.getName());
			List<Product> products = c.getProducts();
			if (products.size() != 0) {
				for (Product product : products) {
					System.out.println("\t" + product.getName());
				}
			}
		}
	}

}
