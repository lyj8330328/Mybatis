package mybatis.annotation;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import mybatis.mapper.OrderMapper;
import mybatis.pojo.Order;
import mybatis.pojo.OrderItem;

public class testManyToMany {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		String resource = "mybatis-config.xml";
		InputStream inputStream = org.apache.ibatis.io.Resources.getResourceAsStream(resource);
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		SqlSession session = sqlSessionFactory.openSession();
		OrderMapper orderMapper = session.getMapper(OrderMapper.class);
		listOrder(orderMapper);
		session.commit();
		session.close();
	}

	private static void listOrder(OrderMapper orderMapper) {
		List<Order> orders = orderMapper.list();
		for (Order order : orders) {
			System.out.println(order.getCode());
			List<OrderItem> orderItems = order.getOrderItems();
			for (OrderItem orderItem : orderItems) {
				System.out.format("\t%s\t%f\t%d%n", orderItem.getProduct().getName(), orderItem.getProduct().getPrice(),
						orderItem.getNumber());
			}
		}
	}

}
