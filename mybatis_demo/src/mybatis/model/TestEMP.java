package mybatis.model;

import java.io.IOException;

import org.apache.ibatis.session.SqlSession;

public class TestEMP {

	public static void main(String[] args) throws IOException {
		SqlSession session = MyBatisUtil.getSqlSession();
		// //ֱ�ӷ��ط�װ�õĶ���
		User user = session.selectOne("getUserByID", 1);
		// //other word
		session.commit();
		session.close();
	}
}
