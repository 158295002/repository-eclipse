package mybatis.model;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

public class TestEMP {

	public static void main(String[] args) throws IOException {
		SqlSession session = MyBatisUtil.getSqlSession();
		// //直接返回封装好的对象
//		User user = session.selectOne("getUserByID", 1);
//		Map<String,String> map = session.selectOne("getUserById", 1);
		List<Map<String,String>> list = session.selectList("selectAllTableByschemas","test");
		// //other word
		session.commit();
		session.close();
	}
}
