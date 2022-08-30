package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    Member save(Member member);
    Optional<Member> findById(Long id); //Optional (null값 처리)- Java8
    Optional<Member> findByName(String name);
    List<Member> findAll();
}
