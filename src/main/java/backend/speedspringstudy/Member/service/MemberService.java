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
    public void signup(SignupRequestDTO signupRequestDTO) {
        if (memberRepository.findByEmail(signupRequestDTO.email()).isPresent()) {
            throw new MemberAlreadyExistsException();
        }

        Member newMember = Member.builder()
                .email(signupRequestDTO.email())
                .password(passwordEncoder.encode(signupRequestDTO.password()))
                .authority(Authority.ROLE_USER)
                .build();

        memberRepository.save(newMember);
    }

    @Transactional
    public Member kakaoSignup(String email, Long kakaoId) {
        if (memberRepository.findByEmail(email).isPresent()) {
            throw new MemberAlreadyExistsException();
        }

        Member member = Member.builder()
                .email(email)
                .kakaoId(kakaoId)
                .password(passwordEncoder.encode("password"))
                .authority(Authority.ROLE_USER)
                .build();

        return memberRepository.save(member);
    }
}