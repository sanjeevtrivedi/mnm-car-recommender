FROM maven:3.8.6-eclipse-temurin-11-alpine@sha256:c6e6e920d20631d39b1ac21a1740cb07d6b2e8e6096e8177acf5b423b2e1cb61 AS maven-build
RUN mkdir -p /mnm-project/

COPY ./target/mnm-* /mnm-project/target/
WORKDIR /mnm-project

RUN apk add --no-cache binutils
RUN $JAVA_HOME/bin/jlink \
         --verbose \
         --add-modules ALL-MODULE-PATH \
         --strip-debug \
         --no-man-pages \
         --no-header-files \
         --compress=2 \
         --output /customjre

FROM alpine:3.16.2@sha256:1304f174557314a7ed9eddb4eab12fed12cb0cd9809e4c28f29af86979a3c870
ENV JAVA_HOME=/jre
ENV PATH="${JAVA_HOME}/bin:${PATH}"
COPY --from=maven-build /customjre $JAVA_HOME

RUN apk add dumb-init && \
    apk del openssl && \
    rm -rf /var/cache/apk/* && \
    mkdir /home/app && \
	addgroup --system javauser && \
	adduser -S -s /bin/false -G javauser javauser && \
	chown -R javauser:javauser /home/app

WORKDIR /home/app
USER javauser
EXPOSE 8080
COPY --from=maven-build /mnm-project/target/mnm-car-recommender-*.jar /home/app/mnm-app.jar
ENTRYPOINT ["java", "-jar", "/home/app/mnm-app.jar"]

