package com.zhgd.controller;

import com.zhgd.service.IResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;


/**
 * 权限管理
 * @author wuyudong
 *
 */
@Controller
@RequestMapping("/resource")
public class ResourceController  {
	
	@Resource
	private IResourceService resourceService;

	  /**
     * 菜单树(右边)
     *
     * @return
     */
	@RequestMapping(value = "/tree", method = RequestMethod.POST)
    public ResponseEntity<Object> tree() {
		/*ShiroUser shiroUser = getShiroUser();*/
		ResponseEntity<Object> responseEntity = null ;
		try{
			responseEntity = ResponseEntity.ok(resourceService.selectTree());
		}catch(Exception e){
			Map<String ,String > map = new HashMap<String ,String >();
			map.put("code","1");
			map.put("message",e.getMessage());
			ResponseEntity.ok(map);
		}
		return responseEntity;
    }

}
