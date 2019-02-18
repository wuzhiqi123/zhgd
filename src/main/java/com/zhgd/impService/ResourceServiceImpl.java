package com.zhgd.impService;

import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.zhgd.pojo.Student;
import com.zhgd.pojo.TreeResult;
import com.zhgd.service.IResourceService;
import com.zhgd.utile.DMIClient;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;


@Service
public class ResourceServiceImpl  implements IResourceService {

	DMIClient  iface = new DMIClient();

	/*private static final int RESOURCE_MENU = 0; // �˵�*/

	@Override
	public List<TreeResult> selectTree()throws Exception {
		List<TreeResult> trees = new ArrayList<TreeResult>();
		List<String> list = new ArrayList<String>();
		list.add("NM");
		list.add("MC");
		list.add("DZ");
		list.add("DKFS");
		list.add("ZYJS");
		list.add("ZYTB");
		list.add("FJZY");
		list.add("XH");
		list.add("ZT");
		list.add("DKZT");
		list.add("ZYLB");
		list.add("CJSJ");
		Map<Integer, Map<String,String>> map = iface.getDMIclient().DMI_FilterParam("resource_menu",list,null,"","");
		for(Map.Entry<Integer,Map<String,String> > resourceMap :map.entrySet()){
			TreeResult tree = new TreeResult();

			for (Map.Entry<String ,String > resource : resourceMap.getValue().entrySet()) {
				if("NM".equals(resource.getKey())){
					tree.setId(Long.valueOf(resource.getValue()));
				}
				if("FJZY".equals(resource.getKey())){
					if (resource.getValue()!=null && !"".equals(resource.getValue())) { //�����menu���ϵ��ӱ�ǩ����ͨ��iframe�ϵ�tab����
						tree.setPid(Long.valueOf(resource.getValue()));
						tree.setTargetType("iframe-tab");
					}
				}
				if("MC".equals(resource.getKey())){
					tree.setName(resource.getValue());
					//tree.setPath(resource.getValue());
				}
				if("XH".equals(resource.getKey())){
					tree.setName(resource.getValue());
				}
				if("ZYTB".equals(resource.getKey())){
					tree.setIconCls(resource.getValue());
				}
				if("DZ".equals(resource.getKey())){
					tree.setPath(resource.getValue());
					//tree.setAttributes(resource.getValue());
				}
				if("DKZT".equals(resource.getKey())){
					tree.setState(resource.getValue());
				}
			}
			trees.add(tree);
		}
		//父级菜单数据
		List<TreeResult> trees2 = new ArrayList<TreeResult>();
		//子菜单数据
		List<TreeResult> pidNotNull = new ArrayList<TreeResult>();
		for(TreeResult treeResult:trees){
			if(treeResult.getPid() == null){
				trees2.add(treeResult);
			}else{
				pidNotNull.add(treeResult);
			}
		}

		for( TreeResult treeResult1:trees2){
			List<TreeResult> pid = new ArrayList<TreeResult>();
			for(TreeResult treeResult :pidNotNull){
				if(treeResult.getPid().equals(treeResult1.getId())){
					pid.add(treeResult);
					treeResult1.setChildren(pid);
				}
			}
		}
		return trees2;
	}

	@Override
	public List<TreeResult> resourceTreeAll()throws Exception {
		List<TreeResult> trees = new ArrayList<TreeResult>();
/*		List<Resource> resourceLists = resourceMapper.resourceTreeAll();
		if (resourceLists == null) {
			return trees;
		}*/
/*		for (Resource resource : resourceLists) {
			TreeResult tree = new TreeResult();
			tree.setId(resource.getId());
			tree.setPid(resource.getPid());
			tree.setText(resource.getName());
			tree.setIconCls(resource.getIcon());
			tree.setAttributes(resource.getUrl());
			tree.setOpenMode(resource.getOpenMode());
			tree.setState(resource.getOpened());
			tree.setDescription(resource.getDescription());
			if (resource.getPid()!=null) {
				tree.setTargetType("iframe-tab");
			}
			trees.add(tree);
		}*/
		List<String> list = new ArrayList<String>();
		list.add("NM");
		list.add("MC");
		list.add("DZ");
		list.add("DKFS");
		list.add("ZYJS");
		list.add("ZYTB");
		list.add("FJZY");
		list.add("XH");
		list.add("ZT");
		list.add("DKZT");
		list.add("ZYLB");
		list.add("CJSJ");
		Map<Integer, Map<String,String>> map = iface.getDMIclient().DMI_FilterParam("resource_menu",list,null,null,null);
		for(Map.Entry<Integer,Map<String,String> > resourceMap :map.entrySet()){
			for (Map.Entry<String ,String > resource : resourceMap.getValue().entrySet()) {
				TreeResult tree = new TreeResult();
				if("NM".equals(resource.getKey())){
					tree.setId(Long.valueOf(resource.getValue()));
				}
				if("FJZY".equals(resource.getKey())){
					tree.setPid(Long.valueOf(resource.getValue()));
					if (resource.getValue()!=null) {
						tree.setTargetType("iframe-tab");
					}
				}
				if("MC".equals(resource.getKey())){
					tree.setId(Long.valueOf(resource.getValue()));
				}
				if("ZYTB".equals(resource.getKey())){
					tree.setId(Long.valueOf(resource.getValue()));
				}
				if("DZ".equals(resource.getKey())){
					tree.setId(Long.valueOf(resource.getValue()));
				}
				if("DKZT".equals(resource.getKey())){
					tree.setId(Long.valueOf(resource.getValue()));
				}
			/*	tree.setPid(resource.getPid());
				tree.setText(resource.getName());
				tree.setIconCls(resource.getIcon());
				tree.setAttributes(resource.getUrl());
				tree.setOpenMode(resource.getOpenMode());
				tree.setState(resource.getOpened());*/
				/*if (resource.getPid()!=null) { //
					tree.setTargetType("iframe-tab");
				}*/
				trees.add(tree);
			}
		}
		return trees;
	}

	@Override
	public List<TreeResult> findByRoleId(Long roleId)throws Exception {
		List<TreeResult> trees = new ArrayList<TreeResult>();
		/*List<Resource> resourceLists = resourceMapper.findByRoleId(roleId);
		if (resourceLists == null) {
			return trees;
		}
		for (Resource resource : resourceLists) {
			TreeResult tree = new TreeResult();
			tree.setId(resource.getId());
			tree.setPid(resource.getPid());
			tree.setText(resource.getName());
			tree.setIconCls(resource.getIcon());
			tree.setAttributes(resource.getUrl());
			tree.setOpenMode(resource.getOpenMode());
			tree.setState(resource.getOpened());
			tree.setDescription(resource.getDescription());
			if (resource.getPid()!=null) {
				tree.setTargetType("iframe-tab");
			}
			trees.add(tree);
		}*/		List<String> list = new ArrayList<String>();
		list.add("NM");
		list.add("MC");
		list.add("DZ");
		list.add("DKFS");
		list.add("ZYJS");
		list.add("ZYTB");
		list.add("FJZY");
		list.add("XH");
		list.add("ZT");
		list.add("DKZT");
		list.add("ZYLB");
		list.add("CJSJ");
		Map<Integer, Map<String,String>> map = iface.getDMIclient().DMI_FilterParam("resource_menu",list,null,null,null);
		for(Map.Entry<Integer,Map<String,String> > resourceMap :map.entrySet()){
			for (Map.Entry<String ,String > resource : resourceMap.getValue().entrySet()) {
				TreeResult tree = new TreeResult();
				if("NM".equals(resource.getKey())){
					tree.setId(Long.valueOf(resource.getValue()));
				}
				if("FJZY".equals(resource.getKey())){
					tree.setPid(Long.valueOf(resource.getValue()));
					if (resource.getValue()!=null) { //�����menu���ϵ��ӱ�ǩ����ͨ��iframe�ϵ�tab����
						tree.setTargetType("iframe-tab");
					}
				}
				if("MC".equals(resource.getKey())){
					tree.setId(Long.valueOf(resource.getValue()));
				}
				if("ZYTB".equals(resource.getKey())){
					tree.setId(Long.valueOf(resource.getValue()));
				}
				if("DZ".equals(resource.getKey())){
					tree.setId(Long.valueOf(resource.getValue()));
				}
				if("DKZT".equals(resource.getKey())){
					tree.setId(Long.valueOf(resource.getValue()));
				}
			/*	tree.setPid(resource.getPid());
				tree.setText(resource.getName());
				tree.setIconCls(resource.getIcon());
				tree.setAttributes(resource.getUrl());
				tree.setOpenMode(resource.getOpenMode());
				tree.setState(resource.getOpened());*/
				/*if (resource.getPid()!=null) { //�����menu���ϵ��ӱ�ǩ����ͨ��iframe�ϵ�tab����
					tree.setTargetType("iframe-tab");
				}*/
				trees.add(tree);
			}
		}
		return trees;
	}
	
/*	 public List<Resource> selectByType(Integer type) {
	        EntityWrapper<Resource> wrapper = new EntityWrapper<Resource>();
	        Resource resource = new Resource();
	        wrapper.setEntity(resource);
	        wrapper.addFilter("resource_type = {0}", type);
	        wrapper.orderBy("seq");
	        return resourceMapper.selectList(wrapper);
	    }*/
}
