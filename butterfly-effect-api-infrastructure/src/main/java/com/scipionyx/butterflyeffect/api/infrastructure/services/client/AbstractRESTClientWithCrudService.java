package com.scipionyx.butterflyeffect.api.infrastructure.services.client;

import java.util.Arrays;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * 
 * @author rmendes
 *
 * @param <T>
 */
@NoRepositoryBean
public abstract class AbstractRESTClientWithCrudService<T> extends AbstractRESTClientService<T>
		implements CrudRepository<T, Long> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * @param service
	 * @return
	 */
	protected List<T> findAllGeneric(String service) {
		T[] body;
		List<T> list = null;
		try {
			body = restTemplate.exchange(calculateURI(service), HttpMethod.GET, HttpEntity.EMPTY, arrayClazz).getBody();
			list = Arrays.asList(body);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * 
	 */
	public List<T> findAll() {
		return findAllGeneric("findAll");
	}

	/**
	 * 
	 * @param sortedBy
	 * @return
	 */
	public List<T> findAllOrderBy(String orderBy) {

		HttpHeaders headers = new HttpHeaders();
		HttpEntity<?> entity = new HttpEntity<>(headers);

		T[] body;
		List<T> list = null;
		try {

			UriComponentsBuilder builder = UriComponentsBuilder.fromUri(calculateURI("findAllOrderBy"))
					.queryParam("orderBy", orderBy);

			body = restTemplate.exchange(builder.build().encode().toUri(), HttpMethod.GET, entity, arrayClazz)
					.getBody();
			
			list = Arrays.asList(body);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public <S extends T> S save(S entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends T> Iterable<S> save(Iterable<S> entities) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public T findOne(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean exists(Long id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Iterable<T> findAll(Iterable<Long> ids) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long count() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(T entity) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(Iterable<? extends T> entities) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub

	}

}
