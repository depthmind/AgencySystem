/**
 * 
 */
package com.agency.crm.mapper.favorite;

import java.util.List;
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
	
	List<Favorite> findAllFavoriteByTypeAndUnionId(Map<String, Object> map);
}
