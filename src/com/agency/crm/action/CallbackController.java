/**
 * 
 */
package com.agency.crm.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.agency.crm.common.action.BaseSimpleFormController;

/**
 * @author zyy
 *
 */
@Controller
@RequestMapping("/pay")
public class CallbackController extends BaseSimpleFormController {

	@RequestMapping(value = "/callback", produces = "application/json;charset=utf-8")
	@ResponseBody
	public String callback(HttpServletRequest request, HttpServletResponse response) {
		String data = "<xml><return_code><![CDATA[SUCCESS]]</return_code><return_msg><![CDATA[OK]]></return_msg></xml>";
		return data;
	}
}
