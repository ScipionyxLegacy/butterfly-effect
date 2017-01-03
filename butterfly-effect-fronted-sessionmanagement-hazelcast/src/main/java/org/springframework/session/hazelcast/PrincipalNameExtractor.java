package org.springframework.session.hazelcast;

import com.hazelcast.query.extractor.ValueCollector;
import com.hazelcast.query.extractor.ValueExtractor;

import org.springframework.expression.Expression;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.session.FindByIndexNameSessionRepository;
import org.springframework.session.MapSession;
import org.springframework.session.Session;

/**
 * Hazelcast {@link ValueExtractor} responsible for extracting principal name
 * from the {@link MapSession}.
 *
 * @author Vedran Pavic
 * @since 1.3.0
 */
public class PrincipalNameExtractor extends ValueExtractor<MapSession, String> {

	private static final PrincipalNameResolver PRINCIPAL_NAME_RESOLVER = new PrincipalNameResolver();

	@SuppressWarnings("unchecked")
	public void extract(MapSession target, String argument, ValueCollector collector) {
		String principalName = PRINCIPAL_NAME_RESOLVER.resolvePrincipal(target);
		if (principalName != null) {
			collector.addObject(principalName);
		}
	}

	/**
	 * Resolves the Spring Security principal name.
	 */
	static class PrincipalNameResolver {

		private static final String SPRING_SECURITY_CONTEXT = "SPRING_SECURITY_CONTEXT";

		private SpelExpressionParser parser = new SpelExpressionParser();

		public String resolvePrincipal(Session session) {
			String principalName = session.getAttribute(FindByIndexNameSessionRepository.PRINCIPAL_NAME_INDEX_NAME);
			if (principalName != null) {
				return principalName;
			}
			Object authentication = session.getAttribute(SPRING_SECURITY_CONTEXT);
			if (authentication != null) {
				Expression expression = this.parser.parseExpression("authentication?.name");
				return expression.getValue(authentication, String.class);
			}
			return null;
		}

	}

}
