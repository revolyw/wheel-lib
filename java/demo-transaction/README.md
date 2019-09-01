# TESTS for spring TRANSACTION

## 1. Overview

Tests for figure out all about Spring Transaction using.

## 2. Config Test Environment

### Prepare JPA and MySql Runtime environment

- Run MySql instance in localhost:3306
- Config JPA properties
  - Config Maven dependencies, including `mysql-connector-java`、`spring-boot-starter-data-jpa`
  - Config `DriverManagerDataSource`
  - Config `LocalContainerEntityManagerFactoryBean` (Unnecessary unless need to customize properties. e.g. `HibernateJpaVendorAdapter`)
  - Config `PlatformTransactionManager`, assigning  `EntityManagerFactory`  and setting `JpaProperties`

## 3. Run Test Cases

All useful test cases is in the path `src/test`'s package `cn.yangw.demo.transaction`. just run it. Of course, the most important part is the commnets that is under the test case methods. 

## 4. Conclusion

All conclusions in under the test case methods. 