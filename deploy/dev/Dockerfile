FROM rtfpessoa/ubuntu-jdk8

COPY . mangotv-api/
WORKDIR mangotv-api

RUN git clone https://github.com/YoungWukJeon/mangotv-api.git
RUN chmod a+x mangotv-api/gradlew
RUN mangotv-api/gradlew clean bootjar --stacktrace
RUN ls mangotv-api

EXPOSE 8080
CMD java -Dspring.profiles.active=dev -jar mangotv-api/build/libs/mangotv-api.jar