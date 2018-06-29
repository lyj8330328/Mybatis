package mybatis.mapper;

import java.util.List;

import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import mybatis.pojo.Product;

public interface ProductMapper {
	@Select("select * from product where cid=#{cid}")
	public List<Product> listByCategory(int cid);

	@Select("select * from product")
	@Results({ @Result(property = "id", column = "id"), @Result(property = "name", column = "name"),
			@Result(property = "price", column = "price"),
			@Result(property = "category", column = "cid", one = @One(select = "mybatis.mapper.CategoryMapper.get")) })
	public List<Product> list();

	@Select("select * from product where id=#{id}")
	public Product get(int id);
}
