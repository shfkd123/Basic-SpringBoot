package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.*;

public class MemoryMemberRepository implements MemberRepository { //Option + Enter
    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L;

    /**
     * 회원정보 저장
     * @param member
     * @return member
     * id : 시퀀스 증가
     * member는 store(map)에 저장
     */
    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    /**
     * 회원 아이디 찾기
     * @param id
     * @return Optional로 감싸서 store(map)안에 있는 아이디 값 반환
     */
    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    /**
     * 회원 이름 찾기
     * @param name
     * @return 파라미터 값과 일치하는 값이 있는 지 확인 후 반환
     */
    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream()
                .filter(member -> member.getName().equals(name))
                .findAny(); //하나라도 찾으면 반환 끝까지 돌렸는데 없으면 Optional로 null값 처리해서 반환 됨.
    }

    /**
     * 전체회원조회
     * @return arrayList로 map안에 있는 값 리턴
     */
    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    public void clearStore() {
        store.clear(); //map에서 모든 요소를 제거
    }
}
