package com.main.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.main.model.Game;
import com.main.service.FileReaderService;
import com.main.service.GameServiceImpl;

@Controller
public class GameController {
	@Autowired
	FileReaderService fileReader;
	@Autowired 
	GameServiceImpl gameServ;
	
	@RequestMapping("/")
	public ModelAndView M()
	{	
		fileReader.readFile();
		ModelAndView mo=new ModelAndView("index");
		List<Game> gameList=gameServ.getGameList();
		if(gameList!=null && gameList.size()>0)
		{
		mo.addObject("gameList",gameList);
		}
	return mo;
	}
	@RequestMapping("/search")
	public ModelAndView searchGameByName()
	{	
		fileReader.readFile();
		ModelAndView mo=new ModelAndView("index");
		List<Game> gameList=gameServ.getGameList();
		if(gameList!=null && gameList.size()>0)
		{
		mo.addObject("gameList",gameList);
		}
	return mo;
	}
	@RequestMapping(value="/getGameByPlatForm/{platForm}",method=RequestMethod.GET)
	public ModelAndView getGameByPlatForm(@PathVariable("platForm") String platForm)
	{
		ModelAndView mo=new ModelAndView("platform");
		List<Game> gamesByPlat=gameServ.getGameByPlatForm(platForm);
		if(gamesByPlat!=null && gamesByPlat.size()>0)
		{
		mo.addObject("platforms", gamesByPlat);
		}
		return mo;
	}
	/*@RequestMapping(value="/getGameByPlatForm/{platForm}",method=RequestMethod.GET)
	public ModelAndView getGameByPlatForm(@PathVariable("platForm") String platForm)
	{
		ModelAndView mo=new ModelAndView("platform");
		List<Game> gamesByPlat=gameServ.getGameByPlatForm(platForm);
		if(gamesByPlat!=null && gamesByPlat.size()>0)
		{
		mo.addObject("platforms", gamesByPlat);
		}
		return mo;
	}*/
	
}
