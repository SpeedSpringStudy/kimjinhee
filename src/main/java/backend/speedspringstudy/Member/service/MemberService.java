package backend.speedspringstudy.member.service;

import backend.speedspringstudy.member.dto.SignupRequestDTO;
import backend.speedspringstudy.member.entity.Authority;
import backend.speedspringstudy.member.entity.Member;
import backend.speedspringstudy.member.exception.MemberAlreadyExistsException;
import backend.speedspringstudy.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public void signup(SignupRequestDTO request) {
        if (memberRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new MemberAlreadyExistsException();
        }

        Member newMember = new Member(
                null,
                request.getEmail(),
                passwordEncoder.encode(request.getPassword()),
                Authority.ROLE_USER
        );

        memberRepository.save(newMember);
    }
}