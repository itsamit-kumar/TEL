package com.eventlabs.dao;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;

import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.orm.hibernate4.HibernateTemplate;

import com.eventlabs.domain.City;

public class HibernateConnectionDAO {

	public HibernateConnectionDAO() {
		// Default Constructor
	}

	private HibernateTemplate hibernateTemplate;

	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}

	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

	public Object getById(Class<?> clazz, Serializable objectId) {
		Object result = hibernateTemplate.get(clazz, objectId);
		return result;
	}

	public Serializable executeSave(Object object) {
		Serializable generatedId = hibernateTemplate.save(object);
		getHibernateTemplate().flush();
		getHibernateTemplate().clear();
		return generatedId;
	}

	public void executeUpdate(Object object) {
		hibernateTemplate.update(object);
	}

	public void executeDelete(Object object) {
		hibernateTemplate.delete(object);
		getHibernateTemplate().flush();
		getHibernateTemplate().clear();
	}

	public void executeSaveOrUpdate(Object object) {
		hibernateTemplate.saveOrUpdate(object);
		getHibernateTemplate().flush();
		getHibernateTemplate().clear();
	}

	public void executeSaveOrUpdate(List<?> list) {
		Iterator<?> iterator = list.iterator();
		while (iterator.hasNext()) {
			hibernateTemplate.saveOrUpdate(iterator.next());
		}
	}

	public void executeSave(List<?> list) {
		Iterator<?> iterator = list.iterator();
		while (iterator.hasNext()) {
			hibernateTemplate.save(iterator.next());
		}
	}

	public Integer saveCity(City city) {
		return (Integer)executeSave(city);
	}
	
	@SuppressWarnings({ "rawtypes"})
	public List executeNamedQuery(String queryName) {
		return this.getHibernateTemplate().findByNamedQuery(queryName);
	}
	
	@SuppressWarnings({ "rawtypes"})
	public List executeNamedQuery(String queryName, String param,
			Object value){
		return this.getHibernateTemplate().findByNamedQueryAndNamedParam(
				queryName, param, value);
	}
	
	@SuppressWarnings({ "rawtypes"})
	public List executeNamedQuery(String queryName, String[] params,
			Object[] values) {
		return this.getHibernateTemplate().findByNamedQueryAndNamedParam(
				queryName, params, values);
	}
	
	@SuppressWarnings({ "rawtypes"})
	public List findHQLQuery(String queryName) {
		return this.getHibernateTemplate().find(queryName);
	}

	/*public List<Object> executeStoreProc(final String storedProcName,
			final String[] paramNames, final Object[] paramValues) {
		String namedQuery = getNamedQuery(storedProcName);
		return executeStoredProcedure(namedQuery, paramNames, paramValues);
	}
*/
	/*@SuppressWarnings({"unchecked", "rawtypes"})
	public String getNamedQuery(final String queryOrStoredProcName) {
		return (String) hibernateTemplate.execute(new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				Query query = session.getNamedQuery(queryOrStoredProcName);
				return query.getQueryString();
			}
		});
	}

	@SuppressWarnings({"unchecked", "rawtypes"})
	private List<Object> executeStoredProcedure(final String queryString,
			final String[] paramNames, final Object[] paramValues) {
		return (List<Object>) hibernateTemplate
				.execute(new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						SQLQuery query = session.createSQLQuery(queryString);
						for (int i = 0; i < paramValues.length; i++) {
							if (paramNames != null && paramNames.length > 0) {
								query.setParameter((String) paramNames[i],
										paramValues[i]);
							} else {
								query.setParameter(i, paramValues[i]);
							}
						}

						return query.list();
					}
				});
	}*/

}
