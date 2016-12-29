#!/usr/bin/env bash
gunzip -c adly-auth.tar.gz | docker load
gunzip -c adly-config-server.tar.gz | docker load
gunzip -c adly-eureka-service.tar.gz | docker load
gunzip -c adly-beacon-service.tar.gz | docker load
gunzip -c adly-edge-service.tar.gz | docker load
gunzip -c adly-device-service.tar.gz | docker load
gunzip -c adly-notification-sender-service.tar.gz | docker load
gunzip -c adly-profile-service.tar.gz | docker load
gunzip -c adly-rule-service.tar.gz | docker load
gunzip -c adly-ui.tar.gz | docker load


