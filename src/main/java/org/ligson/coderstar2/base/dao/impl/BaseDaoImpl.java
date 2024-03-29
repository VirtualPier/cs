package org.ligson.coderstar2.base.dao.impl;

import org.apache.commons.lang.ClassUtils;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.ligson.coderstar2.base.dao.BaseDao;
import org.ligson.coderstar2.user.domains.User;
import org.springframework.beans.BeanUtils;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ReflectionUtils;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class BaseDaoImpl<T> implements BaseDao<T> {

    private static final Logger logger = Logger.getLogger(BaseDaoImpl.class);
    private SessionFactory sessionFactory;

    @Transactional("transactionManager")
    @Override
    public String add(T t) {
        return getCurrentSession().save(t).toString();
    }

    @Transactional("transactionManager")
    @Override
    public void delete(T t) {
        getCurrentSession().delete(t);
    }

    @Transactional("transactionManager")
    @Override
    public void update(T t) {
        getCurrentSession().update(t);
    }

    @Transactional("transactionManager")
    @SuppressWarnings("unchecked")
    @Override
    public List<T> findAllBy(String propertyName, Object propertyValue) {
        String hql;
        if (propertyName == null) {
            hql = "from " + this.getGenericTypeName();
        } else {
            if (propertyValue instanceof String) {

                hql = "from " + this.getGenericTypeName() + " where "
                        + propertyName + "='" + propertyValue + "'";
            } else {

                hql = "from " + this.getGenericTypeName() + " where "
                        + propertyName + "=" + propertyValue;
            }
        }
        Query query = getCurrentSession().createQuery(hql);
        return query.list();
    }

    @Transactional("transactionManager")
    @SuppressWarnings("unchecked")
    @Override
    public List<T> findAllBy(String propertyName, Object propertyValue,
                             int offset, int max) {
        String hql;
        if (propertyName == null) {
            hql = "from " + this.getGenericTypeName();
        } else {
            if (propertyValue instanceof String) {

                hql = "from " + this.getGenericTypeName() + " where "
                        + propertyName + "='" + propertyValue + "'";
            } else {

                hql = "from " + this.getGenericTypeName() + " where "
                        + propertyName + "=" + propertyValue;
            }
        }
        Query query = getCurrentSession().createQuery(hql);
        query.setFirstResult(offset);
        query.setMaxResults(max);
        List<T> list = query.list();
        return list;
    }

    @Transactional("transactionManager")
    @Override
    public long countBy(String propertyName, Object propertyValue) {
        return countExceptUserBy(propertyName, propertyValue, null);
    }

    @Transactional
    @Override
    public long countExceptUserBy(String propertyName, Object propertyValue, User user) {
        String hql;
        if (propertyName != null) {
            String exceptUser = "";
            if (user != null) {
                exceptUser = " and id <> " + user.getId();
            }
            if (propertyValue instanceof String) {
                hql = "select count(*) from " + this.getGenericTypeName()
                        + " where " + propertyName + "='" + propertyValue + "'" + exceptUser;
            } else {
                hql = "select count(*) from " + this.getGenericTypeName()
                        + " where " + propertyName + "=" + propertyValue + exceptUser;
            }
        } else {
            hql = "select count(*) from " + this.getGenericTypeName();
        }
        Query query = getCurrentSession().createQuery(hql);
        return (Long) query.uniqueResult();
    }

    @Override
    public List<T> findByExample(T t, int offset, int max) {
        PropertyDescriptor[] props = BeanUtils.getPropertyDescriptors(t.getClass());
        List<String> propNames = new ArrayList<String>();
        List<Object> propValues = new ArrayList<Object>();
        for (PropertyDescriptor prop : props) {
            try {
                Field field = t.getClass().getField(prop.getName());
                Object value = ReflectionUtils.getField(field, t);
                if (value != null) {
                    propNames.add(prop.getName());
                    propValues.add(value);
                }
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            }
        }
        return findAllByAnd(propNames, propValues, max, offset);
    }

    @Override
    public long countByExample(T t) {
        PropertyDescriptor[] props = BeanUtils.getPropertyDescriptors(t.getClass());
        List<String> propNames = new ArrayList<String>();
        List<Object> propValues = new ArrayList<Object>();
        for (PropertyDescriptor prop : props) {
            try {
                Field field = t.getClass().getField(prop.getName());
                Object value = ReflectionUtils.getField(field, t);
                if (value != null) {
                    propNames.add(prop.getName());
                    propValues.add(value);
                }
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            }
        }
        return countByAnd(propNames, propValues);
    }

    @Override
    public long countByAnd(List<String> propertyNames, List<Object> propertyValues) {
        List<T> list = new ArrayList<>();
        // TODO Auto-generated method stub
        if (propertyNames.size() == propertyValues.size()) {
            String hql = "select count(*) from " + this.getGenericTypeName() + " where ";
            int len = propertyNames.size();
            for (int i = 0; i < len; i++) {
                if (i == len - 1) {
                    if (propertyValues.get(i) instanceof String) {
                        hql += propertyNames.get(i) + "='"
                                + propertyValues.get(i) + "'";
                    } else {
                        hql += propertyNames.get(i) + "="
                                + propertyValues.get(i);
                    }
                } else {
                    if (propertyValues.get(i) instanceof String) {
                        hql += propertyNames.get(i) + "='"
                                + propertyValues.get(i) + "' and ";
                    } else {
                        hql += propertyNames.get(i) + "="
                                + propertyValues.get(i) + " and ";
                    }
                }
            }
            logger.debug(hql);
            Query query = getCurrentSession().createQuery(hql);
            return (long) query.uniqueResult();
        } else {
            return 0;
        }

    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Session getCurrentSession() {
        return getSessionFactory().getCurrentSession();
    }

    @SuppressWarnings("rawtypes")
    private Class getGenericType(int index) {
        Type genType = getClass().getGenericSuperclass();
        if (!(genType instanceof ParameterizedType)) {
            return Object.class;
        }
        Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
        if (index >= params.length || index < 0) {
            throw new RuntimeException("Index outof bounds");
        }
        if (!(params[index] instanceof Class)) {
            return Object.class;
        }
        return (Class) params[index];
    }

    private String getGenericTypeName() {
        return getGenericType(0).getSimpleName();
    }

    @Transactional("transactionManager")
    @Override
    public T findBy(String propertyName, Object propertyValue) {
        String hql;
        if (propertyValue instanceof String) {
            hql = "from " + this.getGenericTypeName() + " where "
                    + propertyName + "='" + propertyValue + "'";
        } else {

            hql = "from " + this.getGenericTypeName() + " where "
                    + propertyName + "=" + propertyValue;
        }
        Query query = getCurrentSession().createQuery(hql);
        query.setFirstResult(0);
        query.setMaxResults(1);
        @SuppressWarnings("unchecked")
        List<T> list = query.list();
        if (list.size() > 0) {
            return list.get(0);
        } else {
            return null;
        }
    }

    @Transactional("transactionManager")
    @Override
    public T findByAnd(List<String> propertyNames, List<Object> propertyValues) {
        // TODO Auto-generated method stub
        if (propertyNames.size() == propertyValues.size()) {
            String hql = "from " + this.getGenericTypeName() + " where ";
            int len = propertyNames.size();
            for (int i = 0; i < len; i++) {
                if (i == len - 1) {
                    if (propertyValues.get(i) instanceof String) {
                        hql += propertyNames.get(i) + "='"
                                + propertyValues.get(i) + "'";
                    } else {
                        hql += propertyNames.get(i) + "="
                                + propertyValues.get(i);
                    }
                } else {
                    if (propertyValues.get(i) instanceof String) {
                        hql += propertyNames.get(i) + "='"
                                + propertyValues.get(i) + "' and ";
                    } else {
                        hql += propertyNames.get(i) + "="
                                + propertyValues.get(i) + " and ";
                    }
                }
            }
            logger.debug(hql);
            Query query = getCurrentSession().createQuery(hql);
            query.setFirstResult(0);
            query.setMaxResults(1);
            @SuppressWarnings("unchecked")
            List<T> list = query.list();
            if (list.size() > 0) {
                return list.get(0);
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    @Override
    public List<T> findAllByAnd(List<String> propertyNames, List<Object> propertyValues, int max, int offset) {
        List<T> list = new ArrayList<>();
        // TODO Auto-generated method stub
        if (propertyNames.size() == propertyValues.size()) {
            String hql = "from " + this.getGenericTypeName() + " where ";
            int len = propertyNames.size();
            for (int i = 0; i < len; i++) {
                if (i == len - 1) {
                    if (propertyValues.get(i) instanceof String) {
                        hql += propertyNames.get(i) + "='"
                                + propertyValues.get(i) + "'";
                    } else {
                        hql += propertyNames.get(i) + "="
                                + propertyValues.get(i);
                    }
                } else {
                    if (propertyValues.get(i) instanceof String) {
                        hql += propertyNames.get(i) + "='"
                                + propertyValues.get(i) + "' and ";
                    } else {
                        hql += propertyNames.get(i) + "="
                                + propertyValues.get(i) + " and ";
                    }
                }
            }
            logger.debug(hql);
            Query query = getCurrentSession().createQuery(hql);
            query.setFirstResult(offset);
            query.setMaxResults(max);
            list = query.list();
            return list;
        } else {
            return list;
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<T> findAllBy(String propertyName, Object propertyValue,
                             String orderProperty) {
        // TODO Auto-generated method stub
        String hql;
        if (propertyName == null || propertyValue == null) {
            hql = "from " + this.getGenericTypeName();
        } else {
            if (propertyValue instanceof String) {

                hql = "from " + this.getGenericTypeName() + " where "
                        + propertyName + "='" + propertyValue + "' order by "
                        + orderProperty;
            } else {

                hql = "from " + this.getGenericTypeName() + " where "
                        + propertyName + "=" + propertyValue + " order by "
                        + orderProperty;
            }
        }
        Query query = getCurrentSession().createQuery(hql);
        return query.list();
    }

    @Override
    public List<T> findAllBy(String propertyName, Object propertyValue,
                             int offset, int max, String orderProperty) {
        // TODO Auto-generated method stub
        String hql;
        if (propertyName == null || propertyValue == null) {
            hql = "from " + this.getGenericTypeName();
        } else {
            if (propertyValue instanceof String) {

                hql = "from " + this.getGenericTypeName() + " where "
                        + propertyName + "='" + propertyValue + "' order by "
                        + orderProperty;
            } else {

                hql = "from " + this.getGenericTypeName() + " where "
                        + propertyName + "=" + propertyValue + " order by "
                        + orderProperty;
                ;
            }
        }
        Query query = getCurrentSession().createQuery(hql);
        query.setFirstResult(offset);
        query.setMaxResults(max);
        @SuppressWarnings("unchecked")
        List<T> list = query.list();
        return list;
    }

    @SuppressWarnings("unchecked")
    @Override
    public T getById(long id) {
        return (T) getCurrentSession().get(getGenericType(0), id);
    }

    @Override
    public void updateProperty(String property, String propertyValue, long id) {
        String hql;
        if (propertyValue instanceof String) {
            hql = "update " + this.getGenericTypeName() + " set " + property
                    + "='" + propertyValue + "' where id='" + id + "'";
        } else {
            hql = "update " + this.getGenericTypeName() + " set " + property
                    + "=" + propertyValue + "' where id='" + id + "'";
        }
        Query query = getCurrentSession().createQuery(hql);
        query.executeUpdate();
    }

    @Override
    public boolean propertyIsUnique(String property, Object propertyValue) {
        // TODO Auto-generated method stub

        String hql;
        if (propertyValue instanceof String) {
            hql = "select count(*) from " + getGenericTypeName() + " where "
                    + property + "='" + propertyValue + "'";
        } else {
            hql = "select count(*) from " + getGenericTypeName() + " where "
                    + property + "=" + propertyValue + "";
        }

        Query query = getCurrentSession().createQuery(hql);
        long count = (Long) query.uniqueResult();
        if (count == 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public List<T> list(int offset, int max) {
        Query query = getCurrentSession().createQuery("from " + getGenericTypeName());
        query.setMaxResults(max);
        query.setFirstResult(offset);
        return query.list();
    }

    @Override
    public int countAll() {
        Query query = getCurrentSession().createQuery("select count(*) from " + getGenericTypeName());
        Long count = (Long) query.uniqueResult();
        return count.intValue();
    }

    @Override
    public void saveOrUpdate(T t) {
        getCurrentSession().saveOrUpdate(t);
    }

}