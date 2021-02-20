package top.tanmw.mybatis.test;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;

/**
 * @author TMW
 * @date 2021/2/19 17:46
 */
public class Mybatis2Test {
    public static void main(String[] args) throws Exception {
        String resource = "mybatis-config.xml";
        // 读取配置文件
        InputStream inputStream = Resources.getResourceAsStream(resource);
        final SqlSessionFactoryBuilder factoryBuilder = new SqlSessionFactoryBuilder();
        final SqlSessionFactory sessionFactory = factoryBuilder.build(inputStream);

        final SqlSession sqlSession1 = sessionFactory.openSession();
        // final SqlSession sqlSession1 = sessionFactory.openSession(true);
        final SqlSession sqlSession2 = sessionFactory.openSession();
        // final SqlSession sqlSession2 = sessionFactory.openSession(true);

        final JobMapper mapper1 = sqlSession1.getMapper(JobMapper.class);
        System.out.println("查询" + mapper1.selectUser("AC_ACCOUNT"));
        sqlSession1.commit();
        final JobMapper mapper2 = sqlSession2.getMapper(JobMapper.class);
        System.out.println("查询" + mapper2.selectUser("AC_ACCOUNT"));
        // sqlSession2.commit();
    }
}
