package com.zhgd.service;




public interface IResourceService {

	Object selectTree()throws Exception;

	Object resourceTreeAll()throws Exception;

	Object findByRoleId(Long roleId)throws Exception;

}
