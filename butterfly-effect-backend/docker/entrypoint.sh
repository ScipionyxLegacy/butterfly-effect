#!/bin/sh

##
##
##
##

exec java \
	-Dspring.cloud.consul.host=$SPRING_CLOUD_CONSUL_HOST \
	-jar $PROJECT_ARTIFACTID-bootstrap-$PROJECT_VERSION-exec.jar