# Demo
Spring Boot Demo Application

```
mvn clean install
mvn spring-boot:run
```

Application URL: `http://localhost:8080/hero`

API for Prometheus: `http://localhost:8080/actuator/prometheus`

# Application Monitoring

```
cd application-monitoring
docker-compose up
```

start Prometheus and Grafana

```
Grafana: http://localhost:3000
Username: admin
Passwort: pass

Prometheus: http://localhost:9090
```

## Simulation Application Requests

```
siege -c 100 http://localhost:8080/hero 

siege -c 50 -u "http://localhost:8080/hero/new POST hero.name=wonderwoman&hero.city=London&hero.universum=MARVEL&repository=duplicateHeroRepository"
siege -c 20 -u "http://localhost:8080/hero/new POST hero.name=ironfist&hero.city=NewYork&hero.universum=MARVEL&repository=uniqueHeroRepository" 

```

