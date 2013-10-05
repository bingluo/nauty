package cn.seu.cose.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.seu.cose.dao.ArticleDAOImpl;

@Service
public class ArticleService {
	@Autowired
	ArticleDAOImpl articleDAOImpl;

}
