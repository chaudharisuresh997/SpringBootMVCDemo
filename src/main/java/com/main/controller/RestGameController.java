package com.main.controller;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.main.model.Game;
import com.main.service.GameServiceImpl;

@RestController
public class RestGameController {
	@Autowired
	GameServiceImpl gameService;
	@RequestMapping(value="/getPlatFormList",method=RequestMethod.GET)
	public @ResponseBody String getGamePlafromList(@RequestHeader(value="Accept") String accept,
			@RequestHeader(value="Accept-Language") String acceptLanguage,
			@RequestHeader(value="User-Agent", defaultValue="foo") String userAgent)
	{
		Gson g=new Gson();
		Set<String> gamesByPlatForm=gameService.getPlatFormList();
		Type listType = new TypeToken<HashSet<String>>(){}.getType();
		
		return g.toJson(gamesByPlatForm,listType);
	}
	@RequestMapping(value="/getGameByScore",method=RequestMethod.GET)
	public @ResponseBody String getGamesByScore(@RequestParam("score") String score)
	{
		Gson g=new Gson();
		Type listType = new TypeToken<ArrayList<Game>>(){}.getType();
		return g.toJson(gameService.getGameSortedByScore(score), listType);
		
	}
	@RequestMapping(value="/getGameByName",method=RequestMethod.GET)
	public @ResponseBody String getGamesByName(@RequestParam("name") String name)
	{
		Gson g=new Gson();
		Type listType = new TypeToken<ArrayList<Game>>(){}.getType();
		return g.toJson(gameService.getGameByName(name), listType);
		
	}
}
