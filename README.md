# NotionManagement
노션과 유사한 간단한 페이지 관리 API

## Table 
| `Name`    | `Type`             | `Nullable` |
|-----------|--------------------|------------|
| id        | int auto_increment | `No`       |
| title     | varchar(50)        | `No`       |
| context   | text               | `No`       |
| parent_id | int                | `Yes`      |

## PageDto
```java
class PageDetails {
    private long id;
    private String title;
    private String context;
    private List<PageSummary> breadcrumbs;
    private List<PageSummary> subPages;
    
    private class PageSummary {
    private long id;
    private String title;
    }
}
```
## Flat 구조 vs 계층 구조

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