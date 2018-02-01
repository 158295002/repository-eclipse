package mybatis.model;

import java.io.InputStream;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MyBatisUtil {
	public static SqlSession getSqlSession(){
		//　　　　mybatis的配置文件
		String conf = "SqlMapConfig.xml";
		//　　　　使用类加载器加载mybatis的配置文件
		InputStream is = MyBatisUtil.class.getClassLoader().getResourceAsStream(conf);
		//　　　　构建sqlSession的工厂
		SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
		SqlSessionFactory factory = builder.build(is);
		SqlSession session = factory.openSession();
		return session;
	}

}
