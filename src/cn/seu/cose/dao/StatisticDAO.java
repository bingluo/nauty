package cn.seu.cose.dao;

import java.util.List;

import cn.seu.cose.entity.Statistic;

public interface StatisticDAO {
	List<Statistic> getAllStatistics();

	Statistic getStatisticByName(String name);

	void updateStatistic(Statistic statistic);

	void increaseByName(String name);
}
