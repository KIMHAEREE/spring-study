package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;

import java.util.List;
import java.util.Optional;

//서비스 클래스 네이밍은 비즈니스 로직 관련해서 작성
public class MemberService {

    private final MemberRepository memberRepository;

    //생성자로 만들어서 외부에서 넣어주도록 변경
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }


    /**
     * 회원 가입
     */
    public Long join(Member member) {
        //같은 이름이 있는 중복 회원은 저장 불가능

        validateDuplicateMember(member); //중복회원 검증

        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        //Optional<Member> result = memberRepository.findByName(member.getName()); => 을 제외하고 바로 사용할 수 있음
        //if null이 아니면을 대신해서 사용 가능 (optional 을 이용해서 사용할 수 있음, m 은 option이 받아온 멤버객체)
        //추가적으로 orElseGet 도 많이쓴다 ( 값이 있으면 실행 )
        memberRepository.findByName(member.getName())
                .ifPresent(m -> {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        });
    }

    /**
     * 전체 회원조회
     */
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
