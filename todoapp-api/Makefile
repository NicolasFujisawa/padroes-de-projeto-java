build/test:
	./gradlew build -xtest

test:
	./gradlew test

.PHONY: build

build:
	./gradlew build

clean:
	./gradlew clean

run:
	./gradlew bootRun

dev:
	./gradlew build --continuous -xtest

db-up:
	docker-compose up -d

postgres:
	docker exec -it todoapp-db psql -U todoapp todoapp

