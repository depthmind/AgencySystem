/**
 * 
 */
package com.agency.crm.action;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.agency.crm.common.action.BaseSimpleFormController;
import com.agency.crm.common.model.base.value.baseconfig.Json;
import com.agency.crm.entity.Favorite;
import com.agency.crm.service.FavoriteService;

/**
 * @author zyy
 *
 */
@Controller
@RequestMapping("/favorite")
public class FavoriteController extends BaseSimpleFormController {

	@Autowired
	private FavoriteService favoriteService;
	
	@RequestMapping(value="/favoriteAdd.do", produces="application/json;charset=utf-8")
	@ResponseBody
	public Json favoriteAdd(String openId, String unionId, String type, String favoriteId) {
		Favorite favorite = new Favorite();
		int result = 0;
		Json json = new Json();
		json.setSuccess(false);
		
		favorite.setOpenId(openId);
		favorite.setUnionId(unionId);
		favorite.setType(type);
		favorite.setFavoriteId(favoriteId);
		result = favoriteService.saveFavorite(favorite);
		if (result > 0) {
			json.setSuccess(true);
		}
		
		return json;
	}
	
	@RequestMapping(value="/findFavorite.do", produces="application/json;charset=utf-8")
	@ResponseBody
	public Favorite findFavoriteByParam(String openId, String unionId, String type, String favoriteId) {
		Favorite favorite = new Favorite();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("openId", openId);
		map.put("type", type);
		map.put("favoriteId", favoriteId);
		favorite = favoriteService.findFavoriteByParam(map);
		return favorite;
	}
	
	@RequestMapping(value="/updateFavorite.do", produces="application/json;charset=utf-8")
	@ResponseBody
	public Json updateFavorite(String openId, String unionId, String type, String favoriteId) {
		Favorite favorite = new Favorite();
		int result = 0;
		Json json = new Json();
		json.setSuccess(false);

		favorite.setOpenId(openId);
		favorite.setType(type);
		favorite.setFavoriteId(favoriteId);
		result = favoriteService.updateFavoriteByParam(favorite);
		if (result > 0) {
			json.setSuccess(true);
		}
		
		return json;
	}
	
	@RequestMapping(value="/deleteFavorite.do", produces="application/json;charset=utf-8")
	@ResponseBody
	public Json deleteFavorite(String openId, String unionId, String type, String favoriteId) {
		Favorite favorite = new Favorite();
		int result = 0;
		Json json = new Json();
		json.setSuccess(false);
		
		favorite.setOpenId(openId);
		favorite.setType(type);
		favorite.setFavoriteId(favoriteId);
		result = favoriteService.deleteFavorite(favorite);
		if (result > 0) {
			json.setSuccess(true);
		}
		
		return json;
	}
}
