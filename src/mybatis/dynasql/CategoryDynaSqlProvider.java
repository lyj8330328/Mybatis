package mybatis.dynasql;

import org.apache.ibatis.jdbc.SQL;

public class CategoryDynaSqlProvider {
	public String list() {
		return new SQL().SELECT("*").FROM("category").toString();
	}

	public String get() {
		return new SQL().SELECT("*").FROM("category").WHERE("id=#{id}").toString();
	}

	public String add() {
		return new SQL().INSERT_INTO("category").VALUES("name", "#{name}").toString();
	}

	public String update() {
		return new SQL().UPDATE("category").SET("name=#{name}").WHERE("id=#{id}").toString();
	}

	public String delete() {
		return new SQL().DELETE_FROM("category").WHERE("id=#{id}").toString();
	}
}
