# example
```
docker build -t maven-test-example .
docker run -v `pwd`:/usr/app/ maven-test-example mvn test
docker run -v `pwd`:/usr/app/ -e BASE_URL=https://reqbin.com maven-test-example mvn test
```