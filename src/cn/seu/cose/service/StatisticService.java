package cn.seu.cose.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.seu.cose.dao.StatisticDAO;

@Service
public class StatisticService {
	@Autowired
	StatisticDAO statisticDAOImpl;

	public void IncreasePV() {
		statisticDAOImpl.increaseByName("page_views");
	}

	public void IncreaseIP() {
		statisticDAOImpl.increaseByName("sessions");
	}

	public long getPV() {
		return statisticDAOImpl.getStatisticByName("page_views").getValue();
	}

	public long getIP() {
		return statisticDAOImpl.getStatisticByName("sessions").getValue();
	}
}
