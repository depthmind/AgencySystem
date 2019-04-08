/**
 * 
 */
package com.agency.crm.mapper.seriesCategory;

import java.util.List;
import java.util.Map;

import com.agency.crm.entity.AgencyBase;
import com.agency.crm.entity.Favorite;
import com.agency.crm.entity.Goods;
import com.agency.crm.entity.SeriesCategory;

/**
 * @author zyy
 *
 */
public interface SeriesCategoryMapper {
	Favorite selectFavoriteByParam(Map<String, Object> map);
	
	int saveSeriesCategory(SeriesCategory seriesCategory);
	
	int updateFavoriteByParam(Favorite favorite);
	
	int deleteFavorite(Favorite favorite);
	
	List<SeriesCategory> selectSeriesCategoryByBrandId(String brandId);
	
	List<Goods> selectFavoriteGoodsByTypeAndUnionId(Map<String, Object> map);
	
	List<AgencyBase> selectFavoriteAgencyByTypeAndUnionId(Map<String, Object> map);
}
