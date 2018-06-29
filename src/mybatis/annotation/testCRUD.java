package mybatis.annotation;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import mybatis.mapper.CategoryMapper;
import mybatis.pojo.Category;

public class testCRUD {
	public static void main(String[] args) throws IOException {
		String resource = "mybatis-config.xml";
		InputStream inputStream = org.apache.ibatis.io.Resources.getResourceAsStream(resource);
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		SqlSession session = sqlSessionFactory.openSession();
		CategoryMapper categoryMapper = session.getMapper(CategoryMapper.class);
		// add(categoryMapper);
		get(categoryMapper);
		// listAll(categoryMapper);
		session.commit();
		session.close();

	}

	private static void update(CategoryMapper mapper) {
		Category category = mapper.get(0);
		category.setName("修改了的Category名称");
		mapper.update(category);
		listAll(mapper);
	}

	private static void delete(CategoryMapper mapper) {
		mapper.delete(2);
		listAll(mapper);
	}

	private static void add(CategoryMapper mapper) {
		Category category = new Category();
		category.setName("新增的Category");
		mapper.add(category);
		listAll(mapper);
	}

	private static void get(CategoryMapper mapper) {
		Category category = mapper.get(1);
		System.out.println(category.getName());
	}

	private static void listAll(CategoryMapper mapper) {
		List<Category> cs = mapper.list();
		for (Category c : cs) {
			System.out.println(c.getName());
		}
	}
}
