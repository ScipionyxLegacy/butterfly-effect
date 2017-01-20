#!/bin/sh

##
##

exec java \
	-javaagent:/aspectjweaver-1.8.9.jar \
	-javaagent:/spring-instrument-4.3.5.RELEASE.jar \
	-Dspring.redis.host=$SPRING_REDIS_HOST \
	-Dspring.redis.port=$SPRING_REDIS_PORT \
	-Dspring.application.name=$SPRING_APPLICATION_NAME \
	-Dspring.cloud.consul.discovery.healthCheckPath=/health \
	-Dserver.port=$SERVER_PORT \
	-Dspring.cloud.consul.host=$SPRING_CLOUD_CONSUL_HOST \
	-Dvaadin.servlet.productionMode=$VAADIN_SERVLET_PRODUCTION_MODE \
	-jar butterfly-effect-frontend-bootstrap-$PROJECT_VERSION-exec.jar