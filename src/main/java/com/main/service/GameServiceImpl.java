package com.main.service;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.main.dao.GameDaoImpl;
import com.main.model.Game;

@Service
public class GameServiceImpl {
	@Autowired
	GameDaoImpl gameDao;

	public List<Game> getGameList() {
		return gameDao.gameList();

	}

	public List<Game> getGameByPlatForm(String platFormName) {
		return gameDao.getGameByPlatForm(platFormName);
	}
	public Set<String> getPlatFormList() {
		return gameDao.getPlatFormList();
	}

	public List<Game> getGameSortedByScore(String score) {
		
		return gameDao.getGameSortedByScore(score);
	}

	public List<Game> getGameByName(String name) {
		// TODO Auto-generated method stub
		return gameDao.getGameByName(name);
	}
	
}
