#!/bin/sh

##
##
##
##

exec java \
	-Dspring.cloud.consul.host=$SPRING_CLOUD_CONSUL_HOST \
	-Dvaadin.servlet.productionMode=$VAADIN_SERVLET_PRODUCTION_MODE \
	-jar $PROJECT_ARTIFACTID-bootstrap-$PROJECT_VERSION-exec.jar