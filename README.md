# City search REST API

Based on spring boot ,gradle, h2 database.

## Installation

1) Using **Docker**.

```bash
docker pull ilyastmn/search-api:test
docker run -p 8080:8080 ilyastmn/search-api:test
```
2) Download and run using **Gradle**
```bash
git clone https://github.com/aliilyas/search-api-test.git
./gradlew bootJar
java -jar build/libs/search-api-0.0.1-SNAPSHOT.jar
```

## Usage

```
curl --request GET --url http://localhost:8080/api/search/Prague
curl --request GET --url http://localhost:8080/api/search/Pra+Czechia
curl --request GET --url http://localhost:8080/api/search/Pra
curl --request GET --url http://localhost:8080/api/search/+Czechia
```

