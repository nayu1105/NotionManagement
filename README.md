# NotionManagement
노션과 유사한 간단한 페이지 관리 API

## Table 
[테이블 구조 설계 Issue #1 참고](https://github.com/nayu1105/NotionManagement/issues/1)
| `Name`    | `Type`             | `Nullable` |
|-----------|--------------------|------------|
| id        | int auto_increment | `No`       |
| title     | varchar(50)        | `No`       |
| context   | text               | `No`       |
| parent_id | int                | `Yes`      |

## 비즈니스 로직

### API
```java
GET /page/{id}
```

### Reponse 결과
```json
{
    "id": 3,
    "title": "3",
    "context": "3",
    "breadcrumbs": [
        {
            "id": 1,
            "title": "1"
        },
        {
            "id": 2,
            "title": "2"
        }
    ],
    "subPages": [
        {
            "id": 4,
            "title": "4"
        }
    ]
}
```

### PageDetails
```java
public class PageDetails {
    private long id;
    private String title;
    private String context;
    private List<PageSummary> breadcrumbs;
    private List<PageSummary> subPages;
    
    public class PageSummary {
    private long id;
    private String title;
    }
}
```

### Flat 구조 vs 계층 구조

계층 구조 예시
```json
{
  "parent": {
    "id": 4,
    "title": "page4",
    "parent": {
      "id": 5,
      "title": "page5",
      "parent": null
    }
  }
}
```

Flat 구조 예시
```json
{
 "breadcrumbs": [
    {
      "id": 2,
      "title": "page2"
    },
    {
      "id": 3,
      "title": "page3"
    }
  ]
}
```

### Flat 구조 선택 이유

프론트 개발자와의 협업 경험 중 계층 구조로 보내도 괜찮다고 한 기억이 있어 계층 구조로 설계하려 했으나,

프론트 개발자가 데이터를 2차 가공하여 사용하기보다 **백엔드 개발자가 요구에 맞게 데이터를 가공한 후 제공하는 것이 좋다**고 생각하여

Flat 구조 개발하기로 하였습니다.

## 개발방법
- 아래 개발방법은 브래드크럼를 어떻게 더 효율적으로 제공할 것인 가를 중점적으로 다루었습니다.

### 재귀쿼리를 사용한 이유

브래드크럼을 구현하는 방법은 두 가지 있었다.

1. select detail을 parent_id 가 null일 때 까지 반복해서 가져오기 (비즈니스 로직에 while문)
2. 재귀쿼리르 사용해 한번에 db에서 상위계층 가져오기

1번 방법은 잦은 DB 접근을 해야해서, 서비스 지연이 발생할 것이라 예상했다.

2번 방법을 택해서, 브래드크럼을 얻을 수 있는 재귀쿼리를 작성하여 서비스를 구현했다.

각자 개발한 재귀쿼리는 다음과 같다.

#### 나유진

- Mybatis 사용

```sql
with recursive cte (id, title, parent_id) as (
      select     id,
                 title,
                 parent_id
      from       page
      where      id = #{id}
      union all
      select     r.id,
                 r.title,
                 r.parent_id
      from       page r
                   inner join cte
                              on r.id = cte.parent_id
    ) select * from cte;
```

#### 문성하

- JdbcTemplate 사용

```sql
WITH RECURSIVE breadcrumbs AS (
                      SELECT id, title, parent_id, 1 as depth
                      FROM `page`
                      WHERE id = ?
                      UNION ALL
                      SELECT p.id, p.title, p.parent_id, b.depth + 1
                      FROM `page` p
                               INNER JOIN breadcrumbs b ON p.id = b.parent_id
                  )
                  SELECT id, title FROM breadcrumbs ORDER BY depth DESC
```

#### 김승철

- JPA Native Query 사용
  
```java
@Query(value = "WITH RECURSIVE p3 (id, title, context, parent_id) AS\n" +
        "            (\n" +
        "            SELECT p1.id, p1.title, p1.context, p1.parent_id\n" +
        "            FROM page p1\n" +
        "            WHERE p1.id = ?\n" +
        "            \n" +
        "            UNION ALL\n" +
        "            \n" +
        "            SELECT p2.id, p2.title, p2.context, p2.parent_id\n" +
        "            FROM page p2\n" +
        "            INNER JOIN p3 ON p2.id = p3.parent_id\n" +
        "            )\n" +
        "            \n" +
        "SELECT * FROM p3 ORDER BY id", nativeQuery = true)
List<Page> findPageAndParent(Long id);
```
- FetchType.LAZY 
```java
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PARENT_ID")
    private Page parent;
```
지연 로딩을 사용함으로써 성능 이슈가 발생할 가능성을 줄여주었다.
