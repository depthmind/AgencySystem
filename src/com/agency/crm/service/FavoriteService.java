/**
 * 
 */
package com.agency.crm.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.agency.crm.common.framework.BaseService;
import com.agency.crm.entity.Favorite;
import com.agency.crm.mapper.favorite.FavoriteMapper;

/**
 * @author zyy
 *
 */
@Service
@Transactional
public class FavoriteService extends BaseService {
	
	@Autowired
	private FavoriteMapper mapper;

	public Favorite findFavoriteByParam(Map<String, Object> map) {
		Favorite favorite = new Favorite();
		try {
			favorite = mapper.selectFavoriteByParam(map);
		} catch (Exception e) {
			logger.error("查找收藏内容出错");
		}
		return favorite;
	}
	
	public int saveFavorite(Favorite favorite) {
		int result = 0;
		try {
			result = mapper.saveFavorite(favorite);
		} catch (Exception e) {
			logger.error("保存收藏信息出错");
		}
		return result;
	}
	
	public int updateFavoriteByParam(Favorite favorite) {
		int result = 0;
		try {
			result = mapper.updateFavoriteByParam(favorite);
		} catch (Exception e) {
			logger.error("更新收藏信息出错");
		}
		return result;
	}
	
	public int deleteFavorite(Favorite favorite) {
		int result = 0;
		try {
			result = mapper.deleteFavorite(favorite);
		} catch (Exception e) {
			logger.error("取消收藏信息出错");
		}
		return result;
	}
}
