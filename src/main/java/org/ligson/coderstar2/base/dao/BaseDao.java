package org.ligson.coderstar2.base.dao;

import org.ligson.coderstar2.user.domains.User;

import java.util.List;

public interface BaseDao<T> {
    public String add(T t);

    public void saveOrUpdate(T t);

    public void delete(T t);

    public void update(T t);

    public void updateProperty(String property, String propertyValue, long id);

    public T getById(long id);

    public T findBy(String propertyName, Object propertyValue);

    public T findByAnd(List<String> propertyNames, List<Object> propertyValues);

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