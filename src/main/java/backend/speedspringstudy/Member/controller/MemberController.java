package backend.speedspringstudy.member.controller;

import backend.speedspringstudy.member.dto.SignupRequestDTO;
import backend.speedspringstudy.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/signup")
    public ResponseEntity<Void> signup(@RequestBody SignupRequestDTO request) {
        memberService.signup(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
