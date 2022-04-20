<h1 align="center">Performance Comparsion Between HTTP/1.1 and HTTP/2.0</h1></br>

HTTP/1.1 프로토콜과 HTTP/2.0 프토토콜의 성능 비교를 위한 안드로이드 데모 애플리케이션으로 샘플 이미지 100개 요청에 대한 RTT(Round-Trip-Time)를 ms 단위로 측정하였습니다.

<p align="center">
  <a href="https://android-arsenal.com/api?level=21"><img alt="API" src="https://img.shields.io/badge/API-21%2B-brightgreen.svg?style=flat"/></a>
  <a href="https://medium.com/@kldaji/simple-android-demo-of-the-difference-in-performance-between-http-1-1-and-http-2-0-7c21e5322ea6"><img alt="Medium" src="https://img.shields.io/badge/Story-Medium-orange"/></a>
  <a href="https://github.com/kldaji"><img alt="Profile" src="https://img.shields.io/badge/Github-kldaji-blue"/></a> 
</p>

## Screenshots
<p>
  <h3>HTTP/1.1</h3>
    <img src="https://user-images.githubusercontent.com/78070388/164164030-547038b0-29f9-4034-b962-35948aa39e8b.gif" width="320px" height="640px"/>
  <h3>HTTP/2.0</h3>
    <img src="https://user-images.githubusercontent.com/78070388/164163717-1eb20027-d28f-46e4-91aa-719e120e834f.gif" width="320px" height="640px"/>
</p>

## Tech stack
- Minimum SDK level 21
- 100% [Kotlin](https://kotlinlang.org/) based + [Coroutines](https://github.com/Kotlin/kotlinx.coroutines) for asynchronous.
- JetPack
  - Lifecycle - dispose observing data when lifecycle state changes.
  - ViewModel - UI related data holder, lifecycle aware.
- [Retrofit2 & OkHttp3](https://github.com/square/retrofit) - construct REST APIs

## API
- [Lorem Picsum](https://picsum.photos/) - Sample Image API

