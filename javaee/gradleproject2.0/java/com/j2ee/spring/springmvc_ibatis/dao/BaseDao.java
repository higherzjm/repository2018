package com.j2ee.spring.springmvc_ibatis.dao;

import com.ibatis.sqlmap.client.SqlMapClient;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

/**
 * BaseDao,Dao需继承此Dao
 * 
 * @author archie2010 since 2011-3-3 下午02:52:36
 */
public class BaseDao extends SqlMapClientDaoSupport {

	@Resource(name = "sqlMapClient")
	private SqlMapClient sqlMapClient;

	@PostConstruct
	public void initSqlMapClient() {
		super.setSqlMapClient(sqlMapClient);
	}
}
