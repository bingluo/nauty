package cn.seu.cose.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.springframework.stereotype.Component;

import cn.seu.cose.entity.Statistic;

import com.ibatis.sqlmap.client.SqlMapClient;

@Component
public class StatisticDAOImpl extends SqlMapClientDaoSupport implements
		StatisticDAO {

	@Autowired(required = true)
	public void setSqlMapClientTemp(SqlMapClient sqlMapClient) {
		setSqlMapClient(sqlMapClient);
	}

	@Override
	public List<Statistic> getAllStatistics() {
		return getSqlMapClientTemplate().queryForList(
				"STATISTIC.selectAllStatistic");
	}

	@Override
	public Statistic getStatisticByName(String name) {
		return (Statistic) getSqlMapClientTemplate().queryForObject(
				"STATISTIC.selectStatisticByName", name);
	}

	@Override
	public void updateStatistic(Statistic statistic) {
		getSqlMapClientTemplate()
				.update("STATISTIC.updateStatistic", statistic);
	}

	@Override
	public void increaseByName(String name) {
		getSqlMapClientTemplate().update("STATISTIC.increaseByName", name);
	}
}
