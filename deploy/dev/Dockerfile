FROM rtfpessoa/ubuntu-jdk8

RUN git clone https://github.com/YoungWukJeon/mangotv-api.git
COPY . mangotv-api/
WORKDIR mangotv-api

RUN chmod a+x gradlew
RUN ./gradlew bootjar --stacktrace
RUN ls

EXPOSE 8080
CMD java -Dspring.profiles.active=dev -jar build/libs/mangotv-api.jar