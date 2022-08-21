package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.List;
import java.util.Optional;

//비즈니스 리포지토리 interface
public interface MemberRepository {
    Member save(Member member);
    Optional<Member> findById(Long id);
    Optional<Member> findByName(String name);  //optional 은 자바8에서 나온 기능 (Null 처리에 사용)
    List<Member> findAll();
}
