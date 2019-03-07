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
	public String getPublishContent(@RequestParam(required = false) String city,
									@RequestParam(required = false) String province,
									@RequestParam(required = false) String area,
									String offset, String rows){

		Map<String, Object> params = new HashMap();
		params.put("offset", Integer.parseInt(offset));
		params.put("rows", Integer.parseInt(rows));
		params.put("province", province);
		params.put("city", city);
		params.put("area", area);
		List<PublishContent> publishContents = publishContentService.getPublishContent(params);

		if (publishContents == null){
			return "";
		}
		return JSONObject.toJSONString(publishContents);
	}
	
	/**
	 * 用于发布信息详情页面
	 * @param id
	 * @return
	 * @author liuhan
	 */
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
