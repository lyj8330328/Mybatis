package mybatis.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import mybatis.pojo.Order;

public interface OrderMapper {
	@Select("select * from order_")
	@Results({ @Result(property = "id", column = "id"), @Result(property = "code", column = "code"),
			@Result(property = "orderItems", column = "id", javaType = List.class, many = @Many(select = "mybatis.mapper.OrderItemMapper.listByOrder")) })
	public List<Order> list();
}
