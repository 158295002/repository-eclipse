package mybatis.model;

import java.io.InputStream;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MyBatisUtil {
	public static SqlSession getSqlSession(){
		//��������mybatis�������ļ�
		String conf = "SqlMapConfig.xml";
		//��������ʹ�������������mybatis�������ļ�
		InputStream is = MyBatisUtil.class.getClassLoader().getResourceAsStream(conf);
		//������������sqlSession�Ĺ���
		SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
		SqlSessionFactory factory = builder.build(is);
		SqlSession session = factory.openSession();
		return session;
	}

}
