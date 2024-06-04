### Retrofit 라이브러리

Retrofit은 Square에서 개발한 HTTP 클라이언트 라이브러리로, 안드로이드 및 자바에서 RESTful API를 쉽게 사용할 수 있도록 도와줍니다. Retrofit을 사용하면 HTTP 요청을 보다 간결하고 직관적으로 작성할 수 있으며, 네트워크 통신을 비동기적으로 처리할 수 있습니다. 또한, Retrofit은 JSON 데이터를 자동으로 파싱하여 자바 객체로 변환해주는 편리한 기능을 제공합니다.

#### 주요 기능 및 장점
- 간편한 HTTP 요청 작성: 인터페이스 선언을 통해 HTTP 요청을 정의할 수 있습니다.
- 자동 데이터 변환: Gson, Jackson 등의 라이브러리를 이용해 JSON 데이터를 자동으로 파싱하여 객체로 변환합니다.
- 비동기 처리: 네트워크 요청을 비동기적으로 처리하여 UI 스레드가 블록되지 않도록 합니다.
- 확장성: OkHttp, RxJava, Coroutine 등 다양한 라이브러리와 쉽게 통합할 수 있습니다.

- [Retrofit 공식 사이트](https://square.github.io/retrofit/)

#### 1. Retrofit 라이브러리 설치

- Retrofit을 사용하기 위해서는 먼저 프로젝트의 `build.gradle` 파일에 Retrofit 및 관련 의존성을 추가

```gradle
// build.gradle (app level)
dependencies {
    implementation 'com.squareup.retrofit2:retrofit:2.9.0'
    implementation 'com.squareup.retrofit2:converter-gson:2.9.0'
}
```

#### 2. 데이터 클래스 정의

- API 응답을 매핑할 데이터 클래스를 정의합니다.
- 예를 들어, `Post`라는 이름의 데이터 클래스를 정의한다면,

```kotlin
// Post.kt
data class Post(
    val userId: Int,
    val id: Int,
    val title: String,
    val body: String
)
```

#### 3. API 인터페이스 정의

- @GET: GET 요청을 지정하는 어노테이션
- @POST: POST 요청을 지정하는 어노테이션
- @PUT: PUT 요청을 지정하는 어노테이션
- @DELETE: DELETE 요청을 지정하는 어노테이션
- @Path: URL 경로에 변수 삽입
- @Query: URL 쿼리 매개변수 추가
- @Body: 요청 본문에 객체 전달

```java
public interface ApiService {
    @GET("posts")
    Call<List<Post>> getPosts();  // 모든 포스트를 가져오는 GET 요청

    @GET("posts/{id}")
    Call<Post> getPostById(@Path("id") int id);  // 특정 ID의 포스트를 가져오는 GET 요청

    @POST("posts")
    Call<Post> createPost(@Body Post post);  // 새로운 포스트를 생성하는 POST 요청

    @PUT("posts/{id}")
    Call<Post> updatePost(@Path("id") int id, @Body Post post);  // 특정 ID의 포스트를 수정하는 PUT 요청

    @DELETE("posts/{id}")
    Call<Void> deletePost(@Path("id") int id);  // 특정 ID의 포스트를 삭제하는 DELETE 요청
}
```

#### 4. Retrofit 인스턴스 생성

Retrofit 인스턴스를 생성하고, `ApiService` 인터페이스를 구현하는 객체를 생성합니다.
- Retrofit.Builder: Retrofit 인스턴스를 생성하는 빌더 클래스
- Base URL: 기본 URL 설정
- ConverterFactory: JSON 파싱을 위한 컨버터 팩토리 설정 (여기서는 Gson 사용)


```kotlin
// RetrofitClient.kt
object RetrofitClient {
    private const val BASE_URL = "https://api.example.com/"

    val instance: ApiService by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()) // JSON 변환을 위해 GsonConverterFactory 추가
            .build()

        retrofit.create(ApiService::class.java)
    }
}
```

#### 5. Retrofit을 사용한 네트워크 호출

- Call: 네트워크 요청을 나타내는 객체
- enqueue: 비동기 요청 처리 메서드
- Callback: 응답 처리 콜백 인터페이스

```java
ApiService apiService = retrofit.create(ApiService.class);

// GET 요청 예시
Call<List<Post>> call = apiService.getPosts();
call.enqueue(new Callback<List<Post>>() {
    @Override
    public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
        if (response.isSuccessful()) {
            List<Post> posts = response.body();
            // 응답 데이터 처리
        }
    }

    @Override
    public void onFailure(Call<List<Post>> call, Throwable t) {
        // 오류 처리
    }
});
```

#### 6. AndroidManifest.xml 설정

- 인터넷 권한을 사용하기 위해 `AndroidManifest.xml` 파일에 아래 내용을 추가

```xml
<manifest xmlns:android="http://schemas.android.com/apk/res/android"
    package="com.busanit.androidstudy">

    <application
        ...>
        <!-- 인터넷 권한 추가 -->
        <uses-permission android:name="android.permission.INTERNET" />
    </application>
</manifest>
```

---
### Retrofit의 주요 클래스 및 인터페이스

#### 1. `Call`
`Call`은 Retrofit에서 네트워크 요청을 나타내는 인터페이스입니다. 이 인터페이스는 요청을 실행하고, 응답을 처리하는 메서드를 제공합니다.

- 메서드
    - `enqueue(Callback<T> callback)`: 비동기적으로 요청을 실행합니다. 요청이 완료되면 `Callback` 객체의 메서드가 호출됩니다.
    - `execute()`: 동기적으로 요청을 실행합니다. 이 메서드는 네트워크 요청이 완료될 때까지 호출을 차단(blocking)합니다.

예시 코드

```kotlin
interface ApiService {
    @GET("posts")
    fun getPosts(): Call<List<Post>> // 네트워크 요청을 나타내는 Call 객체 반환
}
```

#### 2. `Callback`
`Callback`은 Retrofit에서 비동기 네트워크 요청의 응답을 처리하기 위한 인터페이스입니다. `Call` 객체의 `enqueue` 메서드와 함께 사용됩니다.

- 메서드
    - `onResponse(call: Call<T>, response: Response<T>)`: 요청이 성공적으로 완료되면 호출됩니다. `Response` 객체를 통해 응답 데이터를 접근할 수 있습니다.
    - `onFailure(call: Call<T>, t: Throwable)`: 요청이 실패하면 호출됩니다. 실패 원인은 `Throwable` 객체를 통해 확인할 수 있습니다.

예시 코드

```kotlin
val api = RetrofitClient.instance
api.getPosts().enqueue(object : Callback<List<Post>> {
    override fun onResponse(call: Call<List<Post>>, response: Response<List<Post>>) {
        if (response.isSuccessful) {
            val posts = response.body()
            // 성공적으로 응답을 받은 경우 처리
        }
    }

    override fun onFailure(call: Call<List<Post>>, t: Throwable) {
        // 요청 실패 처리
    }
})
```

#### 3. `Response`
`Response`는 Retrofit에서 HTTP 응답을 나타내는 클래스입니다. 이 클래스는 응답 데이터와 함께 상태 코드, 헤더 등도 제공합니다.

- 메서드
    - `body()`: 응답 데이터를 반환합니다. 일반적으로 요청이 성공한 경우 사용됩니다.
    - `code()`: HTTP 상태 코드를 반환합니다.
    - `message()`: 상태 코드와 관련된 메시지를 반환합니다.
    - `headers()`: 응답의 헤더를 반환합니다.
    - `isSuccessful()`: 응답이 성공적(상태 코드 200~299)인지 여부를 반환합니다.

예시 코드

```kotlin
override fun onResponse(call: Call<List<Post>>, response: Response<List<Post>>) {
    if (response.isSuccessful) {
        val posts = response.body()
        // 성공적으로 응답을 받은 경우 처리
    } else {
        // 응답은 받았지만 성공적이지 않은 경우 처리
    }
}
```

#### 4. `ConverterFactory`
`ConverterFactory`는 Retrofit에서 HTTP 응답을 원하는 형식으로 변환하기 위한 팩토리 패턴 클래스입니다. 일반적으로 JSON 데이터를 자바 객체로 변환하는 데 사용됩니다.

- GsonConverterFactory
    - Retrofit에서 가장 많이 사용되는 `ConverterFactory` 중 하나입니다. Gson을 이용해 JSON 데이터를 자바 객체로 변환합니다.

예시 코드

```kotlin
val retrofit = Retrofit.Builder()
    .baseUrl("https://jsonplaceholder.typicode.com/")
    .addConverterFactory(GsonConverterFactory.create()) // Gson을 이용한 데이터 변환
    .build()
```

---