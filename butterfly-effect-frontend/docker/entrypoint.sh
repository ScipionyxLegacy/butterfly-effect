#!/bin/sh

##
##
##
##

exec java \
	-javaagent:/spring-instrument-3.0.4.RELEASE.jar \
	-Dspring.application.name=$SPRING_APPLICATION_NAME \
	-Dspring.cloud.consul.discovery.healthCheckPath=/health \
	-Dserver.port=$SERVER_PORT \
	-Dspring.cloud.consul.host=$SPRING_CLOUD_CONSUL_HOST \
	-Dvaadin.servlet.productionMode=$VAADIN_SERVLET_PRODUCTION_MODE \
	-jar $PROJECT_ARTIFACTID-bootstrap-$PROJECT_VERSION-exec.jar