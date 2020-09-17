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

db/up:
	docker-compose up -d db

db/bash:
	docker exec -ti todoapp bash

mysql:
	docker exec -ti todoapp_db mysql -uroot -ppassword todoapp