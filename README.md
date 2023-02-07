# search-sample-app

# APIs

## Search Blog

`카카오`, `네이버` API를 사용한 블로그 검색기능

### HTTP Request

```
GET localhost:8080/search/blog HTTP/1.1
```

### Request parameters

| Parameter | Type   | require | Description                               |
|:----------|:-------|:--------|:------------------------------------------|
| keyword   | String | O       | 검색용 키워드                                   |
| sort      | String | X       | 정렬조건 accuracy(정확도, default), recency(최신순) |
| page      | Int    | X       | 페이징을 위한 페이지 위치 (default 1)                |
| size      | Int    | X       | 노출 리스트 수 (default 10)                     |

### Response

| Name               | Type    | Description   |
|:-------------------|:--------|:--------------|
| hasNext            | Boolean | 데이터 추가 존재 여부  |
| blogs              | Array   | 블로그 리스트       |
| blogs.blogName     | String  | 블로그명          |
| blogs.blogUrl      | String  | 블로그 url       |
| blogs.postTitle    | String  | 블로그 포스트 제목    |
| blogs.postContent  | String  | 블로그 포스트 설명/본문 |
| blogs.postCreateAt | Long    | 블로그 포스트 생성 날짜 |

### Response Example

```
 http://localhost:8080/search/blog?keyword=토트넘

{
    "hasNext": true,
    "blogs": [
        {
            "blogName": "사천질리",
            "blogUrl": "http://isle.hotissue-korea.com/148",
            "postTitle": "<b>토트넘</b> 맨시티 중계 손흥민골 하이라이트",
            "postContent": "▼<b>토트넘</b> 맨시티 중계▼ 손흥민이 뛰고 있는 <b>토트넘</b> 이 과연 4위로 올라설수 있을지 기대가 되는 경기가 있습니다 2월 6일 새벽에 열리는 <b>토트넘</b> 맨시티 22라운드 경기인데요 지난 FA컵에서 멀티골로 자신감을 회복한 손흥민이 두 경기 연속 득점을 기대하고 있습니다 이번 <b>토트넘</b> 맨체스터시티 중계 는 아래를 통해서...",
            "postCreateAt": 1675593887
        },
        ...
    ]
}
```

## Keyword List

위에서 검색한 검색어들의 상위 10개 리스트

### HTTP Request

```
GET localhost:8080/list/keyword HTTP/1.1
```

### Response

| Name    | Type   | Description |
|:--------|:-------|:------------|
| rank    | Int    | 순위          |
| keyword | String | 검색어         |
| count   | Int    | 검색횟수        |

### Response Example

```
 http://localhost:8080/list/keyword

[
    {
        "rank": 1,
        "keyword": "토트넘",
        "count": 1
    },
    ...
]
```