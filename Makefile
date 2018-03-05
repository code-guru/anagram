.PHONY: test build demo docker-test docker-demo

clean:
	@./gradlew clean

test:
	@./gradlew clean test

build:
	@./gradlew build

demo:
	@java -classpath build/classes/java/main se.king.mehdi.CmdLineDemo

docker-test:
	@docker-compose run test

docker-demo:
	@docker-compose run demo

