package backend.speedspringstudy.member.dao;

import backend.speedspringstudy.member.entity.Member;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class MemberDAO {

    private final EntityManager em;

    public Optional<Member> findByEmail(String email) {
        try {
            Member member = em.createQuery("SELECT m FROM Member m WHERE m.email = :email", Member.class)
                    .setParameter("email", email)
                    .getSingleResult();
            return Optional.of(member);
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }

    public void save(Member member) {
        em.persist(member);
    }
}