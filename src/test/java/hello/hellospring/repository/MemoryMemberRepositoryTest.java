package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

public class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();
    
    //메소드가 끝날때마다 호출되는 함수
    //TEST는 서로 순서와 관계없이 의존관계가 설정되면 안됨
    //하나의 테스트가 끝날때마다 저장소나 공용 데이터들을 초기화해주는 작업이 필요함
    //TDD => 테스트 주도개발 ( 테스트를 먼저 구현 후 실제 코드들을 구현하는 작업 방식 )
    @AfterEach
    public void afterEach() {
        repository.clearStore();
    }
    @Test
    public void save() {
        Member member = new Member();
        member.setName("spring");

        repository.save(member); //저장하기
        Member result = repository.findById(member.getId()).get(); //optional 에서 데이터 꺼내기
        //System.out.println("result = " + (result == member)); //soutv
        //Assertions.assertEquals(member, result); // 둘이같은지 확인하는 방법 (Junit)
        assertThat(member).isEqualTo(result);
    }
    @Test
    public void findByName(){
        Member member1= new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2= new Member();
        member2.setName("spring2");
        repository.save(member2);

        Member result = repository.findByName("spring1").get();

        assertThat(result).isEqualTo(member1);
    }

    @Test
    public void findAll() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);


        List<Member> result = repository.findAll();
        assertThat(result.size()).isEqualTo(2);
    }
}
