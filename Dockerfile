FROM alpine:latest

MAINTAINER crdiaz324
USER root

RUN apk update
RUN apk fetch openjdk8
RUN apk add --no-cache openjdk8 \
    bash
RUN mkdir /conf

WORKDIR /app

COPY target/scala-2.12/gatling_cassandra_timeslice /app
COPY application.conf /conf
COPY startsim.sh /app
COPY entrypoint.sh /app

ENTRYPOINT ["/app/entrypoint.sh"]




