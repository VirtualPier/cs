package org.ligson.coderstar2.base.dao;

import org.ligson.coderstar2.user.domains.User;

import java.util.List;

/***
 * hibernate dao封装
 *
 * @param <T>
 */
public interface BaseDao<T> {

    /***
     * save操作
     *
     * @param t
     * @return
     */
    public String add(T t);

    /***
     * save or update 操作
     *
     * @param t
     */
    public void saveOrUpdate(T t);

    /***
     * 删除操作
     *
     * @param t
     */
    public void delete(T t);

    /***
     * 更新操作
     *
     * @param t
     */
    public void update(T t);

    /***
     * 更新某一个属性
     *
     * @param property
     * @param propertyValue
     * @param id
     */
    public void updateProperty(String property, String propertyValue, long id);

    /***
     * 通过id查找对象
     *
     * @param id
     * @return
     */
    public T getById(long id);

    /***
     * 通过一个属性查询
     *
     * @param propertyName
     * @param propertyValue
     * @return
     */
    public T findBy(String propertyName, Object propertyValue);

    /***
     * 通过多个属性查询一个
     *
     * @param propertyNames
     * @param propertyValues
     * @return
     */
    public T findByAnd(List<String> propertyNames, List<Object> propertyValues);

    /***
     * 查询多个
     *
     * @param propertyNames
     * @param propertyValues
     * @param max
     * @param offset
     * @return
     */
    public List<T> findAllByAnd(List<String> propertyNames, List<Object> propertyValues, int max, int offset);

    public List<T> findAllBy(String propertyName, Object propertyValue);

    public List<T> findAllBy(String propertyName, Object propertyValue,
                             String orderProperty);

    public List<T> findAllBy(String propertyName, Object propertyValue,
                             int offset, int max);

    public List<T> findAllBy(String propertyName, Object propertyValue,
                             int offset, int max, String orderProperty);

    public long countBy(String propertyName, Object propertyValue);

    public long countByAnd(List<String> propertyNames, List<Object> propertyValues);

    public boolean propertyIsUnique(String property, Object propertyValue);

    public List<T> list(int offset, int max);

    public int countAll();

    /**
     * 除了制定用户之外下的属性和值
     *
     * @param propertyName  属性名称
     * @param propertyValue 属性值
     * @param user          除此用户之外
     * @return 数据库中含有的个数
     */
    long countExceptUserBy(String propertyName, Object propertyValue, User user);

    public List<T> findByExample(T t, int offset, int max);

    public long countByExample(T t);
}