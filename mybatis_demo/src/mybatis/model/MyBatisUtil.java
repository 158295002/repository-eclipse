package mybatis.model;

import java.io.InputStream;
import java.util.Properties;

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
		Properties properties = new Properties();
        properties.setProperty("jdbc.driver", "com.mysql.jdbc.Driver");
        properties.setProperty(
                "jdbc.url",
                "jdbc:mysql://localhost:3306/test?useUnicode=true&characterEncoding=UTF-8&autoReconnect=true");     
        properties.setProperty("jdbc.username", "root");
        properties.setProperty("jdbc.password", "root");
		SqlSessionFactory factory = builder.build(is,properties);
		SqlSession session = factory.openSession();
		return session;
	}

}
