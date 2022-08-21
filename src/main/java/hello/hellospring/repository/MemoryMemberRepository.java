package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.*;

//비즈니스 리포지토리 구현 class ( 리포지토리 : 데이터베이스에 접근, 객체를 DB에 저장하고 관리)
public class MemoryMemberRepository implements MemberRepository{

    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L;

    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream()
                .filter(member -> member.getName().equals(name))
                .findAny(); // member에서 member의 getname 이 파라미터 name이랑 같은 것을 찾기 (findAny : 하나라도 찾는 것)
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    public void clearStore(){
        store.clear(); //store 초기화
    }
}
