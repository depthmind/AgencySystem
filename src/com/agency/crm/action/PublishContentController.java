/**
 * 
 */
package com.agency.crm.action;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.agency.crm.common.action.BaseSimpleFormController;
import com.agency.crm.common.model.base.value.baseconfig.Json;
import com.agency.crm.entity.PublishContent;
import com.agency.crm.service.PublishContentService;

/**
 * @author zyy
 *
 */
@Controller
@RequestMapping("/publish")
public class PublishContentController extends BaseSimpleFormController {

	@Autowired
	private PublishContentService publishContentService;
	
	@RequestMapping(value = "/savePublishContent.do", produces = "application/json;charset=utf-8")
	@ResponseBody
	public Json savePublishContent(PublishContent publishContent) {
		Json json = new Json();
		json.setSuccess(false);
		int result = publishContentService.savePublishContent(publishContent);
		if (result > 0) {
			json.setSuccess(true);
		}
		return json;
	}
	
	@RequestMapping(value = "/uploadImage.do", produces = "application/json;charset=utf-8")
	@ResponseBody
	public Object uploadImage(MultipartHttpServletRequest request, @RequestParam("dataId") String dataId, String param) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("success", true);
		MultiValueMap<String, MultipartFile> multiFileMap = request.getMultiFileMap();
		List<MultipartFile> files = multiFileMap.get("files");
		if (null == files || files.size() == 0) {
			map.put("success", false);
			map.put("msg", "检测到您未选择图片，请刷新页面后重试");
			return map;
		}
		for (MultipartFile multipartFile : files) {
			BigDecimal size = new BigDecimal(multipartFile.getSize());
			BigDecimal sizeMB = size.divide(new BigDecimal(1024 * 1024));
			BigDecimal MAX_FILE_SIZE = size.divide(new BigDecimal(1024 * 1024 * 2));
			if (sizeMB.compareTo(MAX_FILE_SIZE) > 0) {
				
			}
		}
		return map;
	}

	@ResponseBody
	@RequestMapping(value = "/getPublish.do", produces = "application/json;charset=UTF-8")
	public String getPublishContent(@RequestParam(required = false) String latitude, @RequestParam(required = false) String longitude, String offset, String rows){

		Map<String, Object> params = new HashMap();
		params.put("offset", Integer.parseInt(offset));
		params.put("rows", Integer.parseInt(rows));
		List<PublishContent> publishContents = publishContentService.getPublishContent(params);

		if (publishContents == null){
			return "";
		} else {
			if (!StringUtils.isEmpty(latitude) && !StringUtils.isEmpty(longitude)) {
				BigDecimal latitude_num = new BigDecimal(latitude);
				BigDecimal longitude_num = new BigDecimal(longitude);
				BigDecimal base = new BigDecimal("0.005");    //经纬度相差0.005之内表示附近
				for (int i = 0; i < publishContents.size(); i++) {
					String[] locationStr = publishContents.get(i).getLocation().split(",");
					BigDecimal location_lat = new BigDecimal(locationStr[0]);
					BigDecimal location_lon = new BigDecimal(locationStr[1]);
					if ((location_lat.subtract(latitude_num)).abs().compareTo(base) != -1 || (location_lon.subtract(longitude_num)).abs().compareTo(base) != -1) {
						publishContents.remove(i);    //删除不在附近的publish
					}
				}
			}
		}
		return JSONObject.toJSONString(publishContents);
	}
	
	@RequestMapping(value = "/findPublishContentById.do", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String findPublishContentById(@RequestParam(value="id") Integer id) {
		String result = "";
		PublishContent publishContent = new PublishContent();
		publishContent = publishContentService.findPublishContentById(id);
		result = JSONObject.toJSONString(publishContent);
		return result;
	}
}
