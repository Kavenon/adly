#!/usr/bin/env bash
docker save adly/adly-auth | gzip > adly-auth.tar.gz
docker save adly/adly-config-server | gzip > adly-config-server.tar.gz
docker save adly/adly-eureka-service | gzip > adly-eureka-service.tar.gz
docker save adly/adly-beacon-service | gzip > adly-beacon-service.tar.gz
docker save adly/adly-edge | gzip > adly-edge-service.tar.gz
docker save adly/adly-device-service | gzip > adly-device-service.tar.gz
docker save adly/adly-notification-sender-service | gzip > adly-notification-sender-service.tar.gz
docker save adly/adly-profile-service | gzip > adly-profile-service.tar.gz
docker save adly/adly-rule-service | gzip > adly-rule-service.tar.gz
docker save adly/adly-ui | gzip > adly-ui.tar.gz
docker save adly/adly-zipkin-service | gzip > adly-zipkin-service.tar.gz


