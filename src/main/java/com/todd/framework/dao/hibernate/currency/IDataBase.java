//package com.todd.framework.dao.hibernate.currency;
//
//import java.io.Serializable;
//import java.util.List;
//
//public interface IDataBase<T> {
//	/**
//	 * 插入一条数据
//	 * @param entity
//	 */
//	public void insert(T entity);
//	/**
//	 * 修改一条数据
//	 * @param entity
//	 */
//	public void update(T entity);
//	
//	/**
//	 * 删除一条数据
//	 * @param id
//	 */
//	public void delete(Serializable id);  
//	  
//	/**
//	 * 根据id查找类型
//	 * @param id
//	 * @return
//	 */
//    public T findById(Serializable id);  
//  
//    /**
//     * 根据hql查询数据
//     * @param hql
//     * @param params
//     * @return
//     */
//    public List<T> findByHQL(String hql, Object... params);  
//}
