FROM rtfpessoa/ubuntu-jdk8

COPY . mangotv-api/
WORKDIR mangotv-api

RUN git clone https://github.com/YoungWukJeon/mangotv-api.git
RUN mangotv-api/gradlew bootjar

EXPOSE 8080
CMD java -Dspring.profiles.active=${SPRING_PROFILE} -jar mangotv-api/build/libs/mangotv-api-0.0.1-SNAPSHOT.jar