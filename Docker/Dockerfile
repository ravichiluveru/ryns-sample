FROM amazoncorretto:17.0.9

# We will need this package to create user and group - does not come w/ amazoncorretto directly
# put top of file bc the package will be needed for all, and we can cache this layer
RUN yum install -y shadow-utils && yum clean all

ARG releaseVersion
ARG applicationName
# Set user and group
ARG user=ngimes
ARG group=ngimes
ARG uid=1000
ARG gid=1000


# SERVICE_NAME is injected by NGIF, and overrides this variable if it's called `SERVICE_NAME`
ENV APPLICATION_NAME $applicationName
ENV RELEASE_VERSION $releaseVersion
ENV WORK_DIR /usr/local/${APPLICATION_NAME}
ENV JAR_FILE_FOLDER app-${APPLICATION_NAME}/build/libs
ENV JAR_FILE app-${APPLICATION_NAME}-${RELEASE_VERSION}-all.jar
ENV DOCKER_FOLDER Docker
ENV APPLICATION_DOCKER_FOLDER app-${APPLICATION_NAME}/Docker
ENV REPO_SCRIPTS_FOLDER scripts
ENV APP_MIN_MEMORY=512m
ENV APP_MAX_MEMORY=512m

RUN mkdir -p ${WORK_DIR}/conf
RUN mkdir -p ${WORK_DIR}/libs
RUN mkdir -p ${WORK_DIR}/bin
RUN mkdir -p ${WORK_DIR}/logs

RUN echo -e "Building Docker image with params  \
    \n APPLICATION_NAME = ${APPLICATION_NAME} \
    \n RELEASE_VERSION = ${RELEASE_VERSION} \
    \n WORK_DIR = ${WORK_DIR} \
    \n JAR_FILE_FOLDER = ${JAR_FILE_FOLDER} \
    \n JAR_FILE = ${JAR_FILE} \
    \n DOCKER_FOLDER = ${DOCKER_FOLDER} \
    \n APPLICATION_DOCKER_FOLDER = ${APPLICATION_DOCKER_FOLDER} \
    \n "

COPY $DOCKER_FOLDER/scripts/* ${WORK_DIR}/bin/
COPY $REPO_SCRIPTS_FOLDER/include.sh ${WORK_DIR}/bin/

COPY $DOCKER_FOLDER/conf/* ${WORK_DIR}/conf/
COPY $APPLICATION_DOCKER_FOLDER/conf/* ${WORK_DIR}/conf/

COPY $JAR_FILE_FOLDER/$JAR_FILE ${WORK_DIR}/libs/

RUN chmod +x ${WORK_DIR}/bin/include.sh
RUN chmod +x ${WORK_DIR}/bin/start-application.sh

EXPOSE 8080
EXPOSE 5005
#EXPOSE 50051


RUN groupadd -g ${gid} ${group}
RUN useradd -u ${uid} -g ${group} -s /bin/sh -m ${user}
RUN chown -R ${user}:${group} ${WORK_DIR}

USER ${uid}:${gid}

WORKDIR ${WORK_DIR}

CMD ["./bin/start-application.sh"]