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
 * @param <ENTITY>
 */
@NoRepositoryBean
public abstract class AbstractRESTClientWithCrudService<ENTITY> extends AbstractRESTClientService<ENTITY>
		implements CrudRepository<ENTITY, Long> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	public List<ENTITY> findAll() {
		try {
			ENTITY[] body = restTemplate.exchange(calculateURI("findAll"), HttpMethod.GET, HttpEntity.EMPTY, arrayClazz)
					.getBody();
			return Arrays.asList(body);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 
	 * @param id
	 * @return
	 */
	public List<ENTITY> findAllByOrderBy(List<CrudParameter> paramters, String orderBy) {
		HttpEntity<?> entity = new HttpEntity<>(paramters, new HttpHeaders());
		try {
			ENTITY[] body = restTemplate.exchange(calculateURI("findAllByOrderBy"), HttpMethod.GET, entity, arrayClazz)
					.getBody();
			return Arrays.asList(body);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 
	 * @param sortedBy
	 * @return
	 */
	public List<ENTITY> findAllOrderBy(String orderBy) {

		HttpHeaders headers = new HttpHeaders();
		HttpEntity<?> entity = new HttpEntity<>(headers);

		try {
			UriComponentsBuilder builder = UriComponentsBuilder.fromUri(calculateURI("findAllOrderBy"))
					.queryParam("orderBy", orderBy);
			ENTITY[] body = restTemplate.exchange(builder.build().encode().toUri(), HttpMethod.GET, entity, arrayClazz)
					.getBody();
			return Arrays.asList(body);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * 
	 */
	@SuppressWarnings("unchecked")
	@Override
	public <S extends ENTITY> S save(S s) {

		HttpHeaders headers = new HttpHeaders();
		HttpEntity<?> entity = new HttpEntity<>(s, headers);

		try {
			return (S) restTemplate.exchange(calculateURI("save"), HttpMethod.PUT, entity, clazz).getBody();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

	/**
	 * 
	 */
	@SuppressWarnings("unchecked")
	@Override
	public <S extends ENTITY> Iterable<S> save(Iterable<S> entities) {

		HttpHeaders headers = new HttpHeaders();
		HttpEntity<?> entity = new HttpEntity<>(entities, headers);

		try {
			ENTITY[] body = restTemplate.exchange(calculateURI("saveAll"), HttpMethod.PUT, entity, arrayClazz).getBody();
			return (Iterable<S>) Arrays.asList(body);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 
	 */
	@Override
	public ENTITY findOne(Long id) {
		HttpEntity<?> entity = new HttpEntity<>(id, new HttpHeaders());
		try {
			return restTemplate.exchange(calculateURI("findOne"), HttpMethod.GET, entity, clazz).getBody();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 
	 * @param id
	 * @return
	 */
	public ENTITY findOneBy(CrudParameter parameter) {
		HttpEntity<?> entity = new HttpEntity<>(parameter, new HttpHeaders());
		try {
			return restTemplate.exchange(calculateURI("findOneBy"), HttpMethod.GET, entity, clazz).getBody();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 
	 * @param id
	 * @return
	 */
	public ENTITY findOneBy(List<CrudParameter> parameter) {
		HttpEntity<?> entity = new HttpEntity<>(parameter, new HttpHeaders());
		try {
			return restTemplate.exchange(calculateURI("findOneBy"), HttpMethod.GET, entity, clazz).getBody();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean exists(Long id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Iterable<ENTITY> findAll(Iterable<Long> ids) {
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
	public void delete(ENTITY entity) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(Iterable<? extends ENTITY> entities) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub

	}

}
