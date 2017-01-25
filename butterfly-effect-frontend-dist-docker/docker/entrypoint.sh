#!/bin/sh

##
##
##PROJECT_VERSION='0.1.0-SNAPSHOT'
##SPRING_REDIS_HOST=localhost
##SPRING_REDIS_PORT=6379
##SPRING_APPLICATION_NAME=butterfly-effect-backend
##SERVER_PORT=8080
##SPRING_CLOUD_CONSUL_HOST=localhost
##VAADIN_SERVLET_PRODUCTION_MODE=true

exec java \
	-javaagent:aspectjweaver-1.8.9.jar \
	-javaagent:spring-instrument-4.3.5.RELEASE.jar \
	-Dspring.redis.host=$SPRING_REDIS_HOST \
	-Dspring.redis.port=$SPRING_REDIS_PORT \
	-Dspring.application.name=$SPRING_APPLICATION_NAME \
	-Dspring.cloud.consul.discovery.healthCheckPath=/health \
	-Dserver.port=$SERVER_PORT \
	-Dspring.cloud.consul.host=$SPRING_CLOUD_CONSUL_HOST \
	-Dvaadin.servlet.productionMode=$VAADIN_SERVLET_PRODUCTION_MODE \
	-jar butterfly-effect-frontend-bootstrap-$PROJECT_VERSION-exec.jar
