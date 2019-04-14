/**
 * 
 */
package com.agency.crm.mapper.brandCategory;

import java.util.List;
import java.util.Map;

import com.agency.crm.entity.AgencyBase;
import com.agency.crm.entity.BrandCategory;
import com.agency.crm.entity.Favorite;
import com.agency.crm.entity.Goods;

/**
 * @author zyy
 *
 */
public interface BrandCategoryMapper {
	Favorite selectFavoriteByParam(Map<String, Object> map);
	
	int saveBrandCategory(BrandCategory brandCategory);
	
	int updateFavoriteByParam(Favorite favorite);
	
	int deleteFavorite(Favorite favorite);
	
	List<BrandCategory> selectBrandCategoryByOpenId(String openId);
	
	List<BrandCategory> selectBrandCategoryByAgencyId(String agencyId);
	
	List<AgencyBase> selectFavoriteAgencyByTypeAndUnionId(Map<String, Object> map);
}
