package mybatis.annotation;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.github.pagehelper.PageHelper;

import mybatis.mapper.CategoryMapper;
import mybatis.pojo.Category;

public class testPaging {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		String resource = "mybatis-config.xml";
		InputStream inputStream = org.apache.ibatis.io.Resources.getResourceAsStream(resource);
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		SqlSession session = sqlSessionFactory.openSession();
		CategoryMapper categoryMapper = session.getMapper(CategoryMapper.class);

		// add(categoryMapper);
		// xmlPage(session);
		// annotationPage(categoryMapper);
		pageHelper(session);
		session.commit();
		session.close();
	}

	public static void list(CategoryMapper categoryMapper) {
		List<Category> categories = categoryMapper.list();
		for (Category category : categories) {
			System.out.println(category);
		}
	}

	public static void add(CategoryMapper categoryMapper) {
		for (int i = 0; i < 100; i++) {
			Category category = new Category();
			category.setName("category name " + i);
			categoryMapper.add(category);
		}
	}

	public static void xmlPage(SqlSession session) {
		Map<String, Integer> params = new HashMap<String, Integer>();
		params.put("start", 0);
		params.put("count", 5);
		List<Category> categories = session.selectList("listByPageXML", params);
		for (Category category : categories) {
			System.out.println(category);
		}
	}

	public static void annotationPage(CategoryMapper categoryMapper) {
		List<Category> categories = categoryMapper.listByPage(1, 1);
		for (Category category : categories) {
			System.out.println(category);
		}
	}

	public static void pageHelper(SqlSession session) {
		PageHelper.offsetPage(0, 5);
		List<Category> categories = session.selectList("listByPageHelper");
		for (Category category : categories) {
			System.out.println(category);
		}
	}
}
