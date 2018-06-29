package mybatis.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import mybatis.pojo.Category;

public interface CategoryMapper {
	@Insert("insert into category (name) values (#{name})")
	public int add(Category category);

	@Delete("delete from category where id= #{id}")
	public void delete(int id);

	@Select("select * from category where id= #{id}")
	public Category get(int id);

	@Update("update category set name=#{name} where id=#{id}")
	public int update(Category category);

	@Select("select * from category")
	public List<Category> list();

	@Select("select * from category limit #{start},#{count}")
	public List<Category> listByPage(@Param("start") int start, @Param("count") int count);

	// 一对多
	@Select("select * from category")
	@Results({ @Result(property = "id", column = "id"),
			@Result(property = "products", javaType = List.class, column = "id", many = @Many(select = "mybatis.mapper.ProductMapper.listByCategory")) })
	public List<Category> list2();
}
