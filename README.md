# IMCF OTT API Service
- Test Account : imcf / imcf (현재 인증절차 비활성화 - 개발진행)
- mybatis, jpa 동시 사용
- application.yml, generatorConfig.xml - DB 정보 제거
- redis 연동완료, 카카오 로그인 개발완료

# Redis 테스트
```bash
127.0.0.1:18089> keys *
1) "RAccount"
2) "RAccount:KAKAO:rojae@kakao.com"
127.0.0.1:18089> ttl RAccount:KAKAO:rojae@kakao.com
(integer) 43025
127.0.0.1:18089> ttl RAccount:KAKAO:rojae@kakao.com
(integer) 43023
127.0.0.1:18089> ttl RAccount:KAKAO:rojae@kakao.com
(integer) 43022
127.0.0.1:18089> keys *
1) "RAccount"
2) "RAccount:KAKAO:rojae@kakao.com"
127.0.0.1:18089> ttl RAccount:KAKAO:rojae@kakao.com
(integer) 42992
127.0.0.1:18089> type RAccount
set
127.0.0.1:18089> type RAccount:KAKAO:rojae@kakao.com
hash
127.0.0.1:18089> hgetall RAccount:KAKAO:rojae@kakao.com
 1) "_class"
 2) "kr.imcf.ott.domain.redis.RAccount"
 ...
  생략
 ...
127.0.0.1:18089>  
```