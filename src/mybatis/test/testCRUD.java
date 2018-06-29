package mybatis.test;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import mybatis.pojo.Category;

public class testCRUD {

	private SqlSession session;
	private InputStream inputStream;
	private SqlSessionFactory sqlSessionFactory;

	public testCRUD() throws IOException {
		// TODO Auto-generated constructor stub
		String resource = "mybatis-config.xml";
		this.inputStream = org.apache.ibatis.io.Resources.getResourceAsStream(resource);
		this.sqlSessionFactory = new SqlSessionFactoryBuilder().build(this.inputStream);
	}

	public void create() throws IOException {
		session = sqlSessionFactory.openSession();
	}

	public void close() {
		session.commit();
		session.close();
	}

	// 1.增加
	private void Myinset() throws IOException {
		create();
		for (int i = 0; i < 10; i++) {
			Category category = new Category();
			category.setName("xiaomi" + i);
			session.insert("addCategory", category);
		}
		listAll(session);
		close();
	}

	// 2.删除
	private void Mydelete() throws IOException {
		create();
		Category category = new Category();
		category.setId(6);
		session.delete("deleteCategory", category);
		listAll(session);
		close();
	}

	// 3.查询
	private void Myquery() throws IOException {
		create();
		Category category = session.selectOne("getCategory", 3);
		System.out.println(category.getName());
		close();
	}

	// 4.更新
	private void Myupdate() throws IOException {
		create();
		Category category = session.selectOne("getCategory", 3);
		category.setName("修改了的Category名字");
		session.update("updateCategory", category);
		listAll(session);

	}

	// 5.查询所有
	private void Allquery() throws IOException {
		create();
		listAll(session);
		close();
	}

	// 6.模糊查询
	private void queryByName() throws IOException {
		create();
		List<Category> categories = session.selectList("listCategoryByName", "i");
		for (Category category : categories) {
			System.out.println(category.getName());
		}
		close();
	}

	// 7.多条件查询
	private void queryByNameId() throws IOException {
		create();
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", 8);
		params.put("name", "xiao");
		List<Category> categories = session.selectList("listCategoryByNameId", params);
		for (Category category : categories) {
			System.out.println(category.getName());
		}
		close();
	}

	// 列出所有的对象名字
	private void listAll(SqlSession session) {
		List<Category> cs = session.selectList("listCategory");
		for (Category c : cs) {
			System.out.println(c.getName());
		}
	}

	public static void main(String[] args) throws IOException {
		testCRUD testCRUD = new testCRUD();
		// testCRUD.Myinset();
		// testCRUD.Mydelete();
		// testCRUD.Myquery();
		// testCRUD.Myupdate();
		// testCRUD.Allquery();
		// testCRUD.queryByName();
		testCRUD.queryByNameId();
	}
}
