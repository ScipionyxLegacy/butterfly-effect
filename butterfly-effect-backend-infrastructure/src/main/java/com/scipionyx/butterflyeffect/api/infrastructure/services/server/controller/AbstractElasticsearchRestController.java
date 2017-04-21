package com.scipionyx.butterflyeffect.api.infrastructure.services.server.controller;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

import org.elasticsearch.index.query.MatchAllQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.RangeQueryBuilder;
import org.elasticsearch.search.sort.FieldSortBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.data.repository.CrudRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestClientException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.scipionyx.butterflyeffect.api.infrastructure.services.client.Value;
import com.scipionyx.butterflyeffect.api.infrastructure.services.server.IRepositoryService;

/**
 * 
 * @author Renato Mendes
 *
 * @param <T>
 *            Service
 * @param <E>
 *            Entity
 */
public abstract class AbstractElasticsearchRestController<T extends IRepositoryService<ENTITY>, ENTITY>
		extends AbstractRestController<IRepositoryService<ENTITY>, ENTITY> {

	protected static final Logger LOGGER = LoggerFactory.getLogger(AbstractElasticsearchRestController.class);

	private Class<ENTITY> entityClazz;

	@SuppressWarnings("unused")
	private Class<ENTITY[]> arrayClazz;

	private String indices;

	@Autowired
	private ElasticsearchTemplate elasticsearchTemplate;

	@SuppressWarnings("unchecked")
	@PostConstruct
	public void init() {
		// This code obtains what is the class that was provided as generic
		// parameter
		entityClazz = (Class<ENTITY>) ((ParameterizedType) this.getClass().getGenericSuperclass())
				.getActualTypeArguments()[1];
		// clazz.getComponentType().getArr
		arrayClazz = (Class<ENTITY[]>) Array.newInstance(entityClazz, 0).getClass();

		Document docAnnotation = AnnotationUtils.findAnnotation(entityClazz, Document.class);
		if (docAnnotation != null) {
			indices = docAnnotation.indexName();
		}

	}

	/**
	 * 
	 * 
	 * @return
	 * @throws Exception
	 * @throws RestClientException
	 */
	@RequestMapping(path = "/findAll", method = { RequestMethod.GET, RequestMethod.POST })
	public final ResponseEntity<Iterable<ENTITY>> findAll() throws RestClientException, Exception {

		LOGGER.debug("findAll using Scroll");

		List<ENTITY> result = new ArrayList<>();

		SearchQuery query = new NativeSearchQueryBuilder(). //
				withQuery(new MatchAllQueryBuilder()). //
				withIndices(indices). //
				withPageable(new PageRequest(0, 1000)). //
				build();

		String scrollId = elasticsearchTemplate.scan(query, 10000L, false);
		boolean loop = true;

		while (loop) {
			Page<ENTITY> page = elasticsearchTemplate.scroll(scrollId, 10000L, entityClazz);
			if (page != null && !page.getContent().isEmpty()) {
				result.addAll(page.getContent());
				continue;
			}
			break;
		}

		return (new ResponseEntity<>(result, HttpStatus.OK));

	}

	/**
	 * TODO - not sure if the sort is working
	 * 
	 * @return
	 * @throws Exception
	 * @throws RestClientException
	 */
	@RequestMapping(path = "/findAllOrderBy", method = { RequestMethod.GET })
	public final ResponseEntity<List<ENTITY>> findAllOrderBy(String orderBy) throws RestClientException, Exception {

		LOGGER.debug("findAllOrderBy using Scroll");

		List<ENTITY> result = new ArrayList<>();

		SearchQuery query = new NativeSearchQueryBuilder(). //
				withQuery(new MatchAllQueryBuilder()). //
				withIndices(indices). //
				withSort((new FieldSortBuilder(orderBy)).order(SortOrder.ASC)). //
				withPageable(new PageRequest(0, 1000)). //
				build();

		String scrollId = elasticsearchTemplate.scan(query, 10000L, false);
		boolean loop = true;

		while (loop) {
			Page<ENTITY> page = elasticsearchTemplate.scroll(scrollId, 10000L, entityClazz);
			if (page != null && !page.getContent().isEmpty()) {
				result.addAll(page.getContent());
				continue;
			}
			break;
		}

		return (new ResponseEntity<>(result, HttpStatus.OK));

	}

	/**
	 * 
	 * 
	 * @return
	 * @throws Exception
	 * @throws RestClientException
	 */
	@RequestMapping(path = "/findAllByOrderBy", method = { RequestMethod.PUT })
	public final ResponseEntity<List<ENTITY>> findAllByOrderBy(
			@RequestBody(required = true) Map<String, Object> parameters, HttpServletRequest request)
			throws RestClientException, Exception {

		LOGGER.debug("findAllByOrderBy");

		List<ENTITY> result = new ArrayList<>();

		String orderBy = request.getHeader("orderBy");
		SortOrder sortOrder = request.getHeader("orderByAsc").equalsIgnoreCase("ASC") ? SortOrder.ASC : SortOrder.DESC;

		NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();

		// Parameters
		ObjectMapper mapper = new ObjectMapper();
		if (parameters.size() > 0) {
			for (String key : parameters.keySet()) {
				Value readValue = mapper.convertValue(parameters.remove(key), Value.class);
				RangeQueryBuilder rangeQueryBuilder = QueryBuilders.rangeQuery(key);
				switch (readValue.getOperation()) {
				case EQUALS:
					break;
				case GREATER_THAN:
					rangeQueryBuilder.gt(readValue.getValue());
					queryBuilder = queryBuilder.withQuery(rangeQueryBuilder);
					break;
				case GREATER_THAN_EQUALS:
					rangeQueryBuilder.gte(readValue.getValue());
					queryBuilder = queryBuilder.withQuery(rangeQueryBuilder);
					break;
				default:
					break;
				}
			}
		}

		SearchQuery query = queryBuilder. //
				withIndices(indices). //
				withSort((new FieldSortBuilder(orderBy)).order(sortOrder)). //
				withPageable(new PageRequest(0, 1000)).//
				build();

		String scrollId = elasticsearchTemplate.scan(query, 10000L, false);
		boolean loop = true;

		while (loop) {
			Page<ENTITY> page = elasticsearchTemplate.scroll(scrollId, 10000L, entityClazz);
			if (page != null && !page.getContent().isEmpty()) {
				result.addAll(page.getContent());
				continue;
			}
			break;
		}

		Method getter = new PropertyDescriptor(orderBy, entityClazz).getReadMethod();

		result.sort(new Comparator<ENTITY>() {

			@SuppressWarnings("unchecked")
			@Override
			public int compare(ENTITY o1, ENTITY o2) {
				try {
					Comparable<Object> c1 = (Comparable<Object>) getter.invoke(o1);
					Object c2 = (Object) getter.invoke(o2);
					return c1.compareTo(c2);
				} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
					e.printStackTrace();
				}
				return 0;
			}

		});

		return (new ResponseEntity<>(result, HttpStatus.OK));

	}

	/**
	 * 
	 * 
	 * @return
	 * @throws Exception
	 * @throws RestClientException
	 */
	@RequestMapping(path = "/save", method = { RequestMethod.PUT })
	public final ResponseEntity<ENTITY> save(@RequestBody(required = true) ENTITY entity)
			throws RestClientException, Exception {
		LOGGER.debug("save");
		CrudRepository<ENTITY, Long> repository = service.getRepository();
		ENTITY persisted;
		try {
			persisted = repository.save(entity);
			return (new ResponseEntity<>(persisted, HttpStatus.OK));
		} catch (Exception e) {
			e.printStackTrace();
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
		CrudRepository<ENTITY, Long> repository = service.getRepository();
		try {
			repository.delete(id);
			return (new ResponseEntity<>("Ok", HttpStatus.OK));
		} catch (Exception e) {
			e.printStackTrace();
			return (new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST));
		}
	}

	/**
	 * 
	 * @param id
	 * @return
	 * @throws RestClientException
	 * @throws Exception
	 */
	@RequestMapping(path = "/findOne/{id}", method = { RequestMethod.GET })
	public final ResponseEntity<ENTITY> findOne(@PathVariable Long id) throws RestClientException, Exception {
		LOGGER.debug("findOne, paramId=", id);
		CrudRepository<ENTITY, Long> repository = service.getRepository();
		try {
			return (new ResponseEntity<>(repository.findOne(id), HttpStatus.OK));
		} catch (Exception e) {
			e.printStackTrace();
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
	@RequestMapping(path = "/count", method = { RequestMethod.GET })
	public final ResponseEntity<Long> count(@RequestParam(required = true) String all)
			throws RestClientException, Exception {
		LOGGER.debug("count, all=", all);
		CrudRepository<ENTITY, Long> repository = service.getRepository();
		try {
			return (new ResponseEntity<>(repository.count(), HttpStatus.OK));
		} catch (Exception e) {
			e.printStackTrace();
			return (new ResponseEntity<>(-1l, HttpStatus.BAD_REQUEST));
		}
	}

}
