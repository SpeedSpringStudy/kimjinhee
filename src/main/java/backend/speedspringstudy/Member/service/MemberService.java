package backend.speedspringstudy.Member.service;

import backend.speedspringstudy.Member.dao.MemberDAO;
import backend.speedspringstudy.Member.dto.SignupRequestDTO;
import backend.speedspringstudy.Member.entity.Authority;
import backend.speedspringstudy.Member.entity.Member;
import backend.speedspringstudy.Member.exception.MemberAlreadyExistsException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberDAO memberDAO;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public void signup(SignupRequestDTO request) {
        if (memberDAO.findByEmail(request.getEmail()).isPresent()) {
            throw new MemberAlreadyExistsException();
        }

        Member newMember = new Member(
                null,
                request.getEmail(),
                passwordEncoder.encode(request.getPassword()),
                Authority.ROLE_USER
        );

        memberDAO.save(newMember);
    }
}