package top.tanmw.mybatis.test;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import top.tanmw.mybatis.entity.Jobs;

import java.io.InputStream;

/**
 * @author TMW
 * @date 2021/2/18 10:35
 */
public class MybatisTest {
    public static void main(String[] args) throws Exception {
        String resource = "mybatis-config.xml";
        // 读取配置文件
        InputStream inputStream = Resources.getResourceAsStream(resource);
        final SqlSessionFactoryBuilder factoryBuilder = new SqlSessionFactoryBuilder();
        final SqlSessionFactory sessionFactory = factoryBuilder.build(inputStream);
        final SqlSession sqlSession = sessionFactory.openSession();
        Jobs jobs1 = sqlSession.selectOne("tmw.selectUser", "AC_ACCOUNT");
        System.out.println(jobs1);
        Jobs jobs2 = sqlSession.selectOne("tmw.selectUser", "AC_ACCOUNT");
        System.out.println(jobs2);
        Jobs jobs3 = sqlSession.selectOne("tmw.selectUser", "AC_ACCOUNT");
        System.out.println(jobs3);

    }
}
