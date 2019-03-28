/**
 * 
 */
package com.agency.crm.mapper.favorite;

import java.util.Map;

import com.agency.crm.entity.Favorite;

/**
 * @author zyy
 *
 */
public interface FavoriteMapper {
	Favorite selectFavoriteByParam(Map<String, Object> map);
	
	int saveFavorite(Favorite favorite);
	
	int updateFavoriteByParam(Favorite favorite);
	
	int deleteFavorite(Favorite favorite);
}
