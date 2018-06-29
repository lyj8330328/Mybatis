package mybatis.mapper;

import java.util.List;

import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import mybatis.pojo.OrderItem;

public interface OrderItemMapper {
	@Select("select * from order_item where oid=#{oid}")
	@Results({ @Result(property = "id", column = "id"), @Result(property = "number", column = "number"),
			@Result(property = "product", column = "pid", one = @One(select = "mybatis.mapper.ProductMapper.get")) })
	public List<OrderItem> listByOrder(int oid);
}
