package com.main.service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.main.dao.GameDaoImpl;
import com.main.model.Game;
@Component
public class FileReaderService {
	@Autowired
	GameDaoImpl gameDao;
	int headerLine=0;
List<Game> games=new ArrayList<Game>();
	BufferedReader br = null;

	public void readFile() {
		InputStream in = this.getClass().getResourceAsStream("/list.csv");
		br=new BufferedReader(new InputStreamReader(in));
		String line;
	try {
		while((line=br.readLine())!=null)
		{
			if(headerLine>0)
			{
			splitBydelimiter(line);
			}
			headerLine++;
			
		}
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	}

	private void splitBydelimiter(String line) {
		String[] gamesArr=line.split(",");
		Game gameObj=new Game();
		gameObj.setTitle(gamesArr[0]);
		gameObj.setUrl(gamesArr[1]);
		gameObj.setPlatform(gamesArr[2]);
		gameObj.setScore(gamesArr[3]);
		gameObj.setGenre(gamesArr[4]);
		gameObj.setEditors_choice(gamesArr[5]);
		gameObj.setReleaseYear(gamesArr[6]);
		
		gameDao.insertGame(gameObj);
		
	}
}
