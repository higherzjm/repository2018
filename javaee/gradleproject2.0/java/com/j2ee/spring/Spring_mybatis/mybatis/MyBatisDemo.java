package com.j2ee.spring.Spring_mybatis.mybatis;

/**
 *
 * @author dengbin
 * @date 2018/4/18
 */
public class MyBatisDemo {

    /*public static SqlSession getSqlSession() throws FileNotFoundException {
        String file="/mybatis-config.xml";
        //配置文件
        InputStream configFile = new FileInputStream(file);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(configFile);
        //加载配置文件得到SqlSessionFactory
        return sqlSessionFactory.openSession();
    }

    public static void main(String[] args) throws FileNotFoundException {
        TestMapper testMapper = getSqlSession().getMapper(TestMapper.class);
        Test test = testMapper.selectByPrimaryKey(1);
    }*/

}
