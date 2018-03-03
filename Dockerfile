FROM openjdk:8-jdk-alpine
RUN mkdir -p /app
WORKDIR /app
COPY ./ ./
RUN ./gradlew --no-daemon build
VOLUME /app
CMD java -classpath build/classes/java/main se.king.mehdi.CmdLineDemo
