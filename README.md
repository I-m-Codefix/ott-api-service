# IMCF OTT API Service
- Test Account : imcf / imcf
- mybatis, jpa 동시 사용
- application.yml, generatorConfig.xml - DB 정보 제거
- redis 연동완료

# Redis 테스트
```bash
127.0.0.1:18089> ttl RAccount
(integer) -1
127.0.0.1:18089> type RAccount
set
127.0.0.1:18089> type RAccount:1
hash
127.0.0.1:18089> hgetall RAccount:1
1) "_class"
2) "kr.imcf.ott.domain.redis.RAccount"
3) "accessToken"
4) "ACCESSTOKEN"
5) "email"
6) "rojae@kakao.com"
7) "id"
8) "1"
9) "name"
10) "rojae"
11) "platformType"
12) "IMCF"
13) "profileImage"
14) ""
15) "refreshToken"
16) "REFRESHTOKEN"
```