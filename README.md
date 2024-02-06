# Spring-Boot-JPA-Kotlin
Kotlin을 사용한 Spring Boot, JPA 적용 프로젝트

- 코틀린과 JPA를 사용할 때 data class를 사용하여 Entity를 정의하는것은 바람직하지 않아보임.

- data class는 copy, equals, hashcode, toString과 같은 메소드를 자동으로 구현하기 때문에 Lazy loading 시 불필요한 쿼리가 호출될 수 있고 
copy() 역시 JPA와 어울리지 않아보임. copy()를 통해 새로운 객체를 만들게 되면 dirty checking에서 문제가 발생할 것 같음.

- Data class를 활용하는 것보다 일반 클래스를 사용하는 것이 더욱 자유로운 설계를 할 수 있음.
    - 참고 문서 발췌 (JSR 338)
```
The value of its primary key uniquely identifies an entity instance within a persistence context and to EntityManager operations as described in Chapter 3. The application must not change the value of the primary key[10]. The behavior is undefined if this occurs.[11]
The state of persistent entities is synchronized to the database at transaction commit. This synchronization involves writing to the database any updates to persistent entities and their relationships as specified above.
```


### JPA Entity save test 진행 도중, 새로 생성된 객체인지 판단하기 위해 select 쿼리가 조회 된 후 insert 하는 현상

PK를 영속화 하기 전에 미리 생성했기 떄문에(UUID) isNew가 false로 판단되어 `em.merge`가 실행된다.

이를 해결하기 위해 `isNew`를 제공하는 `Persistable` 인터페이스를 구현한 엔티티를 사용한다
```java
// JPA의 save 메소드

@Transactional
@Override
public <S extends T> S save(S entity) {

  Assert.notNull(entity, "Entity must not be null.");

  if (entityInformation.isNew(entity)) {
    em.persist(entity);
    return entity;
  } else {
    return em.merge(entity);
  }
}

// isNew 메소드는 아래와 같음
public boolean isNew(T entity) {

    ID id = getId(entity);
    Class<ID> idType = getIdType();

    if (!idType.isPrimitive()) {
        return id == null;
    }

    if (id instanceof Number) {
        return ((Number) id).longValue() == 0L;
    }

    throw new IllegalArgumentException(String.format("Unsupported primitive id type %s", idType));
}
```
