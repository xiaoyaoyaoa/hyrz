package cn.com.web;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.com.model.Activity;
import cn.com.model.Organization;
import cn.com.service.ActivityService;
import cn.com.service.MemberService;
import cn.com.util.DateUtils;
import cn.com.util.StringUtil;


@Controller
@RequestMapping("/api/activity")
public class ActivityApiController{

	public static final Logger logger = LoggerFactory.getLogger(ActivityController.class);

	@Autowired MemberService memberService;
	@Autowired ActivityService activityService;

	@RequestMapping("/list/{organizationId}")
	public @ResponseBody Map<String,Object> list(Model model, @PathVariable int organizationId,HttpServletRequest request){
		Map<String,Object> res=new HashMap<String, Object>();
		Organization organization = memberService.getOrganizationById(organizationId);
		if(null == organization){
			model.addAttribute("errorMsg","未找到该组织");
			return null;
		}
		model.addAttribute("organization",organization);
		String activityType = request.getParameter("activityType");
		String activityDate = request.getParameter("activityDate");
		String activityEndDate = request.getParameter("activityEndDate");
		if (StringUtil.isEmpty(activityType)) {
			activityType = "all";
		}
		if (StringUtil.isEmpty(activityDate)) {
			activityDate = "all";
		}
		if (StringUtil.isEmpty(activityEndDate)) {
			activityEndDate = "all";
		}
		//查询活动类型列表
		//List<Map<String,Object>> actTypeList = activityService.getActTypeByOrganizationId(organizationId);
		//查询活动日期列表
		List<Map<String,Object>> actDateList = activityService.getActDateByOrganizationId(organizationId);
		//查询活动列表
		List<Activity> actList = activityService.getActByOrganizationId(organizationId,activityType,activityDate,activityEndDate);
		//model.addAttribute("actTypeList",actTypeList);
		model.addAttribute("actDateList",actDateList);
		model.addAttribute("actList",actList);
		model.addAttribute("activityType",activityType);
		model.addAttribute("activityDate",activityDate);
		model.addAttribute("activityEndDate",activityEndDate);
//		res.put("actTypeList",actTypeList);
		res.put("actDateList",actDateList);
		res.put("actList",actList);
		res.put("activityType",activityType);
		res.put("activityDate",activityDate);
		return res;
	}
	@ResponseBody
	@RequestMapping("/addActivity")
	public int addActivity(Model model, @RequestParam Map<String,Object> params){
		int add_flag = 0;//是否修改成功 0修改失败 1修改成功
		try {
			String activityType = !StringUtil.isEmpty(params.get("activityType"))?params.get("activityType").toString():"";
			String activityDate = !StringUtil.isEmpty(params.get("activityDate"))?params.get("activityDate").toString():"";
			String activityName = !StringUtil.isEmpty(params.get("activityName"))?params.get("activityName").toString():"";
			String organizationId = !StringUtil.isEmpty(params.get("organizationId"))?params.get("organizationId").toString():"";
			String examine = !StringUtil.isEmpty(params.get("examine"))?params.get("examine").toString():"";
			Activity activity = new Activity();
			activity.setActivityType(Integer.parseInt(activityType));
			activity.setActivityDate(DateUtils.getDate(activityDate));
			activity.setActivityName(activityName);
			activity.setOrganizationId(Integer.parseInt(organizationId));
			activity.setExamine(Integer.parseInt(examine));
			//根据活动类型初始化活动基础数据
			if (null != params && params.size()>0 && activityService.saveActivity(activity)>0) {
				add_flag = 1;
			}
		} catch (Exception e) {
			logger.error("新增活动失败:",e);
			add_flag = 0;
			return add_flag;
		}
		return add_flag;
	}
	@RequestMapping("/edit/{organizationId}/{activityId}/")
	public String fortScore(Model model, @PathVariable int organizationId,@PathVariable int activityId){
		Organization organization = memberService.getOrganizationById(organizationId);
		if(null == organization){
			model.addAttribute("errorMsg","未找到该组织");
			return "error";
		}
		model.addAttribute("organization",organization);
		Activity activity = activityService.getActivityById(activityId);
		if(null == activity){
			model.addAttribute("errorMsg","未找到该活动");
			return "error";
		}
		model.addAttribute("activity",activity);
		//活动类型(0战力涨幅,1本服要塞,2跨服要塞,3天地战场,4本服争霸,5跨服争霸,6叛忍)
		if(activity.getActivityType() == 0){//战力涨幅
			//根据活动日期查询战力详细数据
			List<Map<String,Object>> figthList = activityService.getFightByActivityId(activityId);
			model.addAttribute("figthList",figthList);
			return "activity/activity-fight";
		}else if(activity.getActivityType() == 1){//本服要塞
			//查询本服要塞详细数据
			List<Map<String,Object>> fortScoreList = activityService.getFortsByActivityId(activityId);
			model.addAttribute("fortScoreList",fortScoreList);
			return "activity/fort-score";
		}else if(activity.getActivityType() == 2){//跨服要塞
			//查询跨服要塞详细数据
			List<Map<String,Object>> spanFortScoreList = activityService.getSpanFortsByActivityId(activityId);
			model.addAttribute("spanFortScoreList",spanFortScoreList);
			return "activity/span-fort-score";

		}else if(activity.getActivityType() == 3){//天地战场
			//查询天地战场详细数据
			List<Map<String,Object>> skyLandList = activityService.getSkyLandByActivityId(activityId);
			model.addAttribute("skyLandList",skyLandList);
			return "activity/sky-land";
		}else if(activity.getActivityType() == 4){//本服争霸
			//查询本服争霸详细数据
			List<Map<String,Object>> conquesList = activityService.getConquestByActivityId(activityId);
			model.addAttribute("conquesList",conquesList);
			return "activity/span-conquest";

		}else if(activity.getActivityType() == 5){//跨服争霸
			List<Map<String,Object>> conquesList = activityService.getConquestByActivityId(activityId);
			model.addAttribute("conquesList",conquesList);
			return "activity/span-conquest";
		}else if(activity.getActivityType() == 6){//叛忍
			List<Map<String,Object>> ninjaList = activityService.getDefectNinjaByActivityId(activityId);
			model.addAttribute("ninjaList",ninjaList);
			return "activity/defect-ninja";
		}
		model.addAttribute("errorMsg","活动类型无法识别");
		return "error";
	}

	@ResponseBody
	@RequestMapping("/updateFortScore")
	public int updateFortScore(Model model, @RequestParam Map<String,Object> params){
		int update_flag = 0;//是否修改成功 0修改失败 1修改成功
		if (null != params && params.size()>0 && activityService.updateFortScore(params)>0) {
			update_flag = 1;
		}
		return update_flag;
	}

	@ResponseBody
	@RequestMapping("/updateSpanFortScore")
	public int updateSpanFortScore(Model model, @RequestParam Map<String,Object> params){
		int update_flag = 0;//是否修改成功 0修改失败 1修改成功
		if (null != params && params.size()>0 && activityService.updateSpanFortScore(params)>0) {
			update_flag = 1;
		}
		return update_flag;
	}
	@ResponseBody
	@RequestMapping("/updateConquest")
	public int updateConquest(Model model, @RequestParam Map<String,Object> params){
		int update_flag = 0;//是否修改成功 0修改失败 1修改成功
		if (null != params && params.size()>0 && activityService.updateConquest(params)>0) {
			update_flag = 1;
		}
		return update_flag;
	}
	@ResponseBody
	@RequestMapping("/updateSkyLand")
	public int updateSkyLand(Model model, @RequestParam Map<String,Object> params){
		int update_flag = 0;//是否修改成功 0修改失败 1修改成功
		if (null != params && params.size()>0 && activityService.updateSkyLand(params)>0) {
			update_flag = 1;
		}
		return update_flag;
	}
	@ResponseBody
	@RequestMapping("/updateNinja")
	public int updateNinja(Model model, @RequestParam Map<String,Object> params){
		int update_flag = 0;//是否修改成功 0修改失败 1修改成功
		if (null != params && params.size()>0 && activityService.updateNinja(params)>0) {
			update_flag = 1;
		}
		return update_flag;
	}
	@ResponseBody
	@RequestMapping("/deleteActivity")
	public int deleteActivity(Model model, @RequestParam Integer activityId){
		int delete_flag = 0;//是否修改成功 0修改失败 1修改成功
		if (!StringUtil.isEmpty(activityId) && activityService.deleteActivity(activityId)>0) {
			delete_flag = 1;
		}
		return delete_flag;
	}
}
