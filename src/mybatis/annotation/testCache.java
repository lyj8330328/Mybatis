package mybatis.annotation;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import mybatis.pojo.Category;

public class testCache {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		String resource = "mybatis-config.xml";
		InputStream inputStream = org.apache.ibatis.io.Resources.getResourceAsStream(resource);
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		SqlSession session = sqlSessionFactory.openSession();

		Category category = session.selectOne("getCategory", 1);
		System.out.println(category);

		Category category2 = session.selectOne("getCategory", 1);
		System.out.println(category2);
		session.commit();
		session.close();

		SqlSession session2 = sqlSessionFactory.openSession();
		Category category3 = session2.selectOne("getCategory", 1);
		System.out.println(category3);
		session2.commit();
		session2.close();
	}

}
