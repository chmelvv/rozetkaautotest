#!/usr/bin/env bash

# How to install
# https://docs.docker.com/install/linux/docker-ce/centos/

# to run on system boot
# sudo systemctl enable docker

# to check
# sudo chkconfig docker on
# sudo chkconfig docker

# sudo dockerd &

#Windows com.docker.service

# sudo sysctl net.bridge.bridge-nf-call-iptables=1
# sudo sysctl net.bridge.bridge-nf-call-ip6tables=1

# sudo docker pull selenoid/firefox:65.0
# sudo docker pull selenoid/firefox:66.0

# sudo docker pull selenoid/vnc:chrome_72.0
# sudo docker pull selenoid/vnc:chrome_73.0
set -x

docker rm -f selenoid
docker rm -f selenoid-ui

DOCKER_PORT=8001

#run selenoid
docker --debug run --detach                     \
--name selenoid                                 \
-p $DOCKER_PORT:4444                            \
-v /var/run/docker.sock:/var/run/docker.sock    \
-v `pwd`/config/:/etc/selenoid/:ro              \
aerokube/selenoid:latest-release                \
-limit `nproc` -timeout 10m

#WEBDRIVER_HUB_IP=`docker inspect --format '{{ .NetworkSettings.IPAddress }}' selenoid`
#echo $WEBDRIVER_HUB_IP

# Selenoid UI service
docker run --detach             \
--name selenoid-ui              \
--link selenoid  \
-p 8080:8080                    \
aerokube/selenoid-ui:1.0.0 --selenoid-uri=http://selenoid:4444

#debug
curl -s http://localhost:$DOCKER_PORT/ping
curl -s http://localhost:$DOCKER_PORT/status
curl -s http://localhost:8080
docker logs selenoid
