package mybatis.test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import mybatis.pojo.Order;
import mybatis.pojo.OrderItem;
import mybatis.pojo.Product;

public class testManyToMany {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		String resource = "mybatis-config.xml";
		InputStream inputStream = org.apache.ibatis.io.Resources.getResourceAsStream(resource);
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		SqlSession session = sqlSessionFactory.openSession();

		// deleteOrderItem(session);
		// addOrderItem(session);
		deleteOrder(session);
		listOrder(session);

		session.commit();
		session.close();
	}

	private static void listOrder(SqlSession session) {
		List<Order> orders = session.selectList("listOrder");
		for (Order order : orders) {
			System.out.println(order.getCode());
			List<OrderItem> orderItems = order.getOrderItems();
			for (OrderItem orderItem : orderItems) {
				System.out.format("\t%s\t%f\t%d\n", orderItem.getProduct().getName(), orderItem.getProduct().getPrice(),
						orderItem.getNumber());
			}
		}
	}

	private static void addOrderItem(SqlSession session) {
		Order order = session.selectOne("getOrder", 1);
		Product product = session.selectOne("getProduct", 6);
		OrderItem orderItem = new OrderItem();
		orderItem.setProduct(product);
		orderItem.setOrder(order);
		orderItem.setNumber(200);

		session.insert("addOrderItem", orderItem);
	}

	private static void deleteOrderItem(SqlSession session) {
		Order order = session.selectOne("getOrder", 1);
		Product product = session.selectOne("getProduct", 6);
		OrderItem orderItem = new OrderItem();
		orderItem.setProduct(product);
		orderItem.setOrder(order);
		session.delete("deleteOrderItem", orderItem);
	}

	private static void deleteOrder(SqlSession session) {
		Order order = session.selectOne("getOrder", 1);
		session.delete("deleteOrder", order);
	}

}
