/**
 * 
 */
package com.agency.crm.action;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.agency.crm.common.action.BaseSimpleFormController;

/**
 * @author zyy
 *
 */
@Controller
@RequestMapping("/help")
public class HelpCenterController extends BaseSimpleFormController {

	@RequestMapping(value="/umeditor.html", produces="application/json;charset=utf-8")
	public String umeditor(Model model) {
		
		return "/helpCenter/umeditor";
	}
}
