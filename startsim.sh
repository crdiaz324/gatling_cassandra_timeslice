#!/bin/sh

if [ -n "${JAVA_HOME}" ]; then
    JAVA="${JAVA_HOME}"/bin/java
else
    JAVA=java
fi

DEFAULT_JAVA_OPTS="-server"
DEFAULT_JAVA_OPTS="${DEFAULT_JAVA_OPTS} -Xms2M -Xmx2G"
DEFAULT_JAVA_OPTS="${DEFAULT_JAVA_OPTS} -XX:+UseG1GC -XX:MaxGCPauseMillis=30 -XX:G1HeapRegionSize=16m -XX:InitiatingHeapOccupancyPercent=75 -XX:+ParallelRefProcEnabled"
DEFAULT_JAVA_OPTS="${DEFAULT_JAVA_OPTS} -XX:+PerfDisableSharedMem -XX:+AggressiveOpts -XX:+OptimizeStringConcat"
DEFAULT_JAVA_OPTS="${DEFAULT_JAVA_OPTS} -XX:+HeapDumpOnOutOfMemoryError"
DEFAULT_JAVA_OPTS="${DEFAULT_JAVA_OPTS} -Djava.net.preferIPv4Stack=true -Djava.net.preferIPv6Addresses=false -Dconfig.file=application.conf"
DEFAULT_JAVA_OPTS="${DEFAULT_JAVA_OPTS} -XX:+UseTLAB -XX:+ResizeTLAB"
DEFAULT_JAVA_OPTS="${DEFAULT_JAVA_OPTS} -Duser.timezone=UTC"
#DEFAULT_JAVA_OPTS="${DEFAULT_JAVA_OPTS} -Dlog.root=WARN"
echo "Running $@"
exec "$JAVA" ${DEFAULT_JAVA_OPTS} ${JAVA_OPTS} -jar target/gatling-dse-sims  "$@"
exit 1
