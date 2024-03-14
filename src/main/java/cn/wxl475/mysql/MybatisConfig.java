package cn.wxl475.mysql;

import com.baomidou.mybatisplus.core.MybatisConfiguration;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.Objects;

/**
 * 多数据源需要手动配置SqlSessionFactory
 */
@Configuration
@EnableTransactionManagement
public class MybatisConfig {

    @Resource(name = "targetDataSource")
    private DataSource dataSource;

    /**
     * 配置SqlSessionFactory
     * @return
     * @throws Exception
     */
    @Bean
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        MybatisSqlSessionFactoryBean sessionFactory = new MybatisSqlSessionFactoryBean();
        sessionFactory.setDataSource(dataSource);
        MybatisConfiguration configuration=new MybatisConfiguration();
//        日志输出
        configuration.setLogImpl(org.apache.ibatis.logging.stdout.StdOutImpl.class);
//        驼峰命名
        configuration.setMapUnderscoreToCamelCase(true);
//        扫描实体类
        configuration.getTypeAliasRegistry().registerAliases("cn.wxl475.pojo");
        sessionFactory.setConfiguration(configuration);
//        配置映射文件路径
        sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath*:/mapper/**/*.xml"));
        return sessionFactory.getObject();
    }

    /**
     * 配置事务管理
     * @return
     */
    @Bean
    public PlatformTransactionManager transactionManager(){
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean
    public SqlSessionTemplate sqlSessionTemplate(@Qualifier("sqlSessionFactory") SqlSessionFactory sqlSessionFactory){
        return new SqlSessionTemplate(sqlSessionFactory);
    }

}
