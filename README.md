# Testing Architecture Demo
Demo for the workshop ["Softwarearchitektur automatisiert testen"](https://software-architecture-summit.de/softwarearchitektur/softwarearchitektur-automatisiert-testen/) at Software Architecture Summit 2019 in Berlin.
Download [Slides](https://www.embarc.de/workshop-software-architektur-summit-2019-berlin/)

## Used Software
- [Spring Boot](https://spring.io/projects/spring-boot)
- [Micrometer](https://micrometer.io)
- [Dependabot](https://dependabot.com/)
- [TravisCI](https://travis-ci.org/)
- [Snyk.io](https://snyk.io/)
- [Grafana](https://grafana.com/)
- [Prometheus](https://prometheus.io/)


## Application

```
mvn clean install
mvn spring-boot:run
```

Application URL: `http://localhost:8080/hero`

API for Prometheus: `http://localhost:8080/actuator/prometheus`

## Application Monitoring

```
cd application-monitoring
docker-compose up
```

Start Prometheus and Grafana

```
Grafana: http://localhost:3000
Username: admin
Passwort: pass

Prometheus: http://localhost:9090
```

### Simulation Application Requests

```
siege -c 100 http://localhost:8080/hero/list 

siege -c 50 -u "http://localhost:8080/hero/new POST hero.name=wonderwoman&hero.city=London&hero.universum=MARVEL"
siege -c 20 -u "http://localhost:8080/hero" 

```

