.PHONY: test build demo

clean:
	@./gradlew clean

test:
	@./gradlew clean test

build:
	@./gradlew build

demo:
	@java -classpath build/classes/java/main se.king.mehdi.CmdLineDemo