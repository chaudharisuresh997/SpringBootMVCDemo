package com.main.dao;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.main.model.Game;

@Repository
public class GameDaoImpl {
	@Autowired
	SessionFactory sessionFactory;

	@Transactional
	public void insertGame(Game gameObj) {
		getSession().save(gameObj);
	}

	Session session;
	@Transactional
	public Session getSession() {
		try {
			session = sessionFactory.getCurrentSession();
		} catch (HibernateException e) {
			session = sessionFactory.openSession();
		}
		return session;
	}

	@Transactional
	public List<Game> gameList() {
		return getSession().createQuery("From Game").list();
	}

	@Transactional
	public List<Game> getGameByPlatForm(String platFormName) {

		Query getByPlatFormQuery = getSession().createQuery("From Game where platform like ?");//
		getByPlatFormQuery.setParameter(0, platFormName);
		return getByPlatFormQuery.list();

	}

	@Transactional
	public Set<String> getPlatFormList() {
		List<String> platFormList = getSession().createQuery("Select g.platform from Game g")
				.list();
		Set<String> platforms = new HashSet<>(platFormList);
		return platforms;
	}

	public List<Game> getGameSortedByScore(String score) {
		Query sortedByScore=null;
		if(score.contains("ASC"))
		{
			sortedByScore = getSession().createQuery("From Game g ORDER BY g.score ASC");	
		}
		else
		{
			sortedByScore= getSession().createQuery("From Game g ORDER BY g.score DESC");
		}	
		
		return sortedByScore.list();
	}

	public List<Game> getGameByName(String name) {
		Query getByPlatFormQuery = getSession().createQuery("From Game where title like ?");//
		getByPlatFormQuery.setParameter(0, name);
		return getByPlatFormQuery.list();
		
	}
}
