# Testing Architecture Demo
Demo for the workshop ["Die Wirksamkeit Eurer Architektur automatisiert testen"](https://software-architecture-summit.de/domain-driven-design/die-wirksamkeit-eurer-architektur-automatisiert-testen/) at Software Architecture Summit 2020 in Munich.
Download [Slides]()

## Used Software
- [Spring Boot](https://spring.io/projects/spring-boot)
- [Micrometer](https://micrometer.io)
- [Dependabot](https://dependabot.com/)
- [TravisCI](https://travis-ci.org/)
- [Snyk.io](https://snyk.io/)
- [Grafana](https://grafana.com/)
- [Prometheus](https://prometheus.io/)
- [Scientist4J](https://github.com/rawls238/Scientist4J)


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
# Performance
siege -c 10 http://localhost:8080/hero
siege -c 10 http://localhost:8080/hero/list

# Maintainbility
siege -c 50 -u "http://localhost:8080/hero/new"
siege -c 20 -u "http://localhost:8080/hero/list?search=Batman" 

```

