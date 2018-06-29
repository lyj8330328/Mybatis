package mybatis.mapper;

import java.util.List;

import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;

import mybatis.pojo.Category;

public interface CategoryMapperDynaSQL {
	@InsertProvider(type = CategoryMapperDynaSQL.class, method = "add")
	public int add(Category category);

	@DeleteProvider(type = CategoryMapperDynaSQL.class, method = "delete")
	public void delete(int id);

	@SelectProvider(type = CategoryMapperDynaSQL.class, method = "get")
	public Category get(int id);

	@UpdateProvider(type = CategoryMapperDynaSQL.class, method = "update")
	public int update(Category category);

	@SelectProvider(type = CategoryMapperDynaSQL.class, method = "list")
	public List<Category> list();

}
