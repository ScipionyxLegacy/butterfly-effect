package com.scipionyx.butterflyeffect.api.infrastructure.services.server.controller;

import java.lang.reflect.Array;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.collections.map.MultiValueMap;
import org.hibernate.annotations.QueryHints;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.repository.CrudRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestClientException;

import com.scipionyx.butterflyeffect.api.infrastructure.services.IHasRepository;
import com.scipionyx.butterflyeffect.api.infrastructure.services.IService;

/**
 * 
 * @author rmendes
 *
 * @param <T>
 *            Service
 * @param <E>
 *            Entity
 */
public abstract class AbstractJpaRestController<T extends IService<ENTITY>, ENTITY>
		extends AbstractRestController<IService<ENTITY>, ENTITY> {

	protected static final Logger LOGGER = LoggerFactory.getLogger(AbstractJpaRestController.class);

	@PersistenceContext()
	protected EntityManager entityManager;

	private Class<ENTITY> entityClazz;

	@SuppressWarnings("unused")
	private Class<ENTITY[]> arrayClazz;

	@SuppressWarnings("unchecked")
	@PostConstruct
	public void init() {
		// This code obtains what is the class that was provided as generic
		// parameter
		entityClazz = (Class<ENTITY>) ((ParameterizedType) this.getClass().getGenericSuperclass())
				.getActualTypeArguments()[1];
		// clazz.getComponentType().getArr
		arrayClazz = (Class<ENTITY[]>) Array.newInstance(entityClazz, 0).getClass();
	}

	/**
	 * 
	 * 
	 * @return
	 * @throws Exception
	 * @throws RestClientException
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(path = "/findAll", method = { RequestMethod.GET, RequestMethod.POST })
	public final ResponseEntity<Iterable<ENTITY>> findAll() throws RestClientException, Exception {
		LOGGER.debug("Health request");
		CrudRepository<ENTITY, Long> repository = ((IHasRepository<ENTITY, ENTITY>) service).getRepository();
		return (new ResponseEntity<>(repository.findAll(), HttpStatus.OK));
	}

	/**
	 * 
	 * 
	 * @return
	 * @throws Exception
	 * @throws RestClientException
	 */
	@RequestMapping(path = "/findAllOrderBy", method = { RequestMethod.GET })
	public final ResponseEntity<List<ENTITY>> findAllOrderBy(String orderBy) throws RestClientException, Exception {
		LOGGER.debug("findAllOrderBy");

		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

		//
		CriteriaQuery<ENTITY> criteria = criteriaBuilder.createQuery(entityClazz);

		// define the main class for the criteria
		Root<ENTITY> from = criteria.from(entityClazz);

		// create the order by - asc
		Order asc = criteriaBuilder.asc(from.get(orderBy));

		// Create the query
		TypedQuery<ENTITY> query = entityManager.createQuery(criteria.select(from).orderBy(asc));
		query.setHint(QueryHints.READ_ONLY, Boolean.TRUE);

		// Execute Query
		List<ENTITY> resultList = query.getResultList();

		return (new ResponseEntity<>(resultList, HttpStatus.OK));

	}

	/**
	 * 
	 * 
	 * @return
	 * @throws Exception
	 * @throws RestClientException
	 */
	@RequestMapping(path = "/findAllByOrderBy", method = { RequestMethod.POST, RequestMethod.GET })
	public final ResponseEntity<List<ENTITY>> findAllByOrderBy(
			@RequestBody(required = true) org.springframework.util.MultiValueMap<String, Object> parameters)
			throws RestClientException, Exception {

		LOGGER.debug("findAllByOrderBy");

		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

		//
		CriteriaQuery<ENTITY> criteria = criteriaBuilder.createQuery(entityClazz);

		// define the main class for the criteria
		Root<ENTITY> from = criteria.from(entityClazz);

		// Parameters
		CriteriaQuery<ENTITY> select = criteria.select(from);

		// Order By
		if (parameters.containsKey("orderBy")) {
			// create the order by - asc
			Object orderBy = parameters.remove("orderBy").get(0);
			Order asc = criteriaBuilder.asc(from.get((String) orderBy));
			select = select.orderBy(asc);
		}

		// Parameters
		if (parameters.size() > 0) {
			for (String key : parameters.keySet()) {
				Object value = parameters.remove(key).get(0);
				Predicate equal = criteriaBuilder.equal(from.get(key), value);
				select = select.where(equal);
			}
		}

		// Create the query
		TypedQuery<ENTITY> query = entityManager.createQuery(select);
		query.setHint(QueryHints.READ_ONLY, Boolean.TRUE);

		// Execute Query
		List<ENTITY> resultList = query.getResultList();

		return (new ResponseEntity<>(resultList, HttpStatus.OK));

	}

	/**
	 * 
	 * 
	 * @return
	 * @throws Exception
	 * @throws RestClientException
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(path = "/save", method = { RequestMethod.PUT })
	public final ResponseEntity<ENTITY> save(@RequestBody(required = true) ENTITY entity)
			throws RestClientException, Exception {
		LOGGER.debug("save");
		CrudRepository<ENTITY, Long> repository = ((IHasRepository<ENTITY, ENTITY>) service).getRepository();
		ENTITY persisted;
		try {
			persisted = repository.save(entity);
			return (new ResponseEntity<>(persisted, HttpStatus.OK));
		} catch (Exception e) {
			e.printStackTrace();
			MultiValueMap errors = new MultiValueMap();
			errors.put("ERROR", e.getMessage());
			return (new ResponseEntity<>(null, HttpStatus.BAD_REQUEST));
		}

	}

	/**
	 * 
	 * @param id
	 * @return
	 * @throws RestClientException
	 * @throws Exception
	 */
	@RequestMapping(path = "/delete", method = { RequestMethod.DELETE })
	public final ResponseEntity<String> delete(@RequestParam(required = true) Long id)
			throws RestClientException, Exception {
		LOGGER.debug("delete, paramId=", id);
		@SuppressWarnings("unchecked")
		CrudRepository<ENTITY, Long> repository = ((IHasRepository<ENTITY, ENTITY>) service).getRepository();
		try {
			repository.delete(id);
			return (new ResponseEntity<>("Ok", HttpStatus.OK));
		} catch (Exception e) {
			e.printStackTrace();
			return (new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST));
		}
	}

}
