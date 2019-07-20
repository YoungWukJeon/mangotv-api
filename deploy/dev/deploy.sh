SPRING_PROFILE=$1

echo "SPRING_PROFILE - ${SPRING_PROFILE}"

## DOCKER PUSH
## docker login ~~~
# docker image build -t study/mangotv-api:latest deploy/${SPRING_PROFILE}/Dockerfile -e SPRING_PROFILE=${SPRING_PROFILE}
docker image build -t study/mangotv-api:latest . --build-arg SPRING_PROFILE=dev
docker container run -t -p 9000:8080 study/mangotv-api:latest