## XML(eXtensible Markup Language)
- 데이터를 구조화하고 나타내는 마크업 언어
- 사람이 이해할 수 있는 구조
- 기계가 해석할 수 있는 데이터 형식
- 반정형 데이터

### XML 구성요소
1. 선언부
2. 태그 (Tag)
   - 데이터를 감싸는 구조화된 요소
   - 시작태그와 종료태그의 쌍으로 이루어짐 (열면 닫아야 함)
```xml
<data>데이터 내용(컨텐츠)</data>
```
3. 엘리먼트 (Element)
   - 시작 태그와 종료 태그 사이에 있는 컨텐츠
   - 태그 내부의 하위 태그, 하위 엘리멘트(요소)를 포함
   - 엘리먼트(요소) = 태그 + 컨텐츠(내용)
```xml
<person>
    <name>홍길동</name>
    <age>30</age>
</person>
```
4. 속성(Attributes)
   - 태그에 추가 정보를 제공
   - Key-Value 쌍
```xml
<person name="홍길동" age="30" />
```
5. 네임스페이스 (Namespace)
   - 태그 이름의 충돌을 피하기 위해 고유한 접두사를 부여
   - 선언부 또는 태그 내부의 'xmlns' 속성을 사용하여 URI로 정의
```xml
<myname:person xmlns:myname="http://myname.com/myname">
    <myname:name>홍길동</myname:name>
</myname:person>
```

- 주의사항
   - XML 태그는 대소문자를 구분한다.
   - 태그의 쌍은 정확하게 매칭이 되어야 함.
   - 속성의 값은 반드시 큰따옴표나 작은따옴표로 감싸야 함.
   - 루트 엘리먼트(최상위)는 하나여야 함.
