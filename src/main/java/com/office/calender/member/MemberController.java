package com.office.calender.member;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.log4j.Log4j2;



@Log4j2
@Controller
@RequestMapping("/member")
public class MemberController {

	final private MemberService memberService;
	
	public MemberController(MemberService memberService) {
		this.memberService = memberService;
		
	}
	
	/*
	 * 회원 가입 양식
	 */
	@GetMapping("/create_account_form")
	public String createAccountForm() {
		log.info("createAccountForm()");
		
		String nextPage = "member/create_account_form";
		
		return nextPage;
		
	}
	
	/*
	 * 회원 가입 확인
	 */
	@PostMapping("/create_account_confirm")
	public String createAccountConfirm(MemberDto memberDto) {
		log.info("createAccountConfirm()");
		
		String nextPage = "member/create_account_ok";
		
		int result = memberService.createAccountConfirm(memberDto);
		if (result <= MemberService.INSERT_FAIL_AT_DATABASE)
			nextPage = "member/create_account_ng";
		
		return nextPage;
		
	}
	
	/*
	 * 회원 로그인 양식
	 */
	@GetMapping("/member_login_form")
	public String memberLoginForm() {
		log.info("memberLoginForm()");
		
		String nextPage = "/member/member_login_form";
		
		return nextPage;
		
	}
	
	/*
	 * 회원 계정 수정 양식
	 */
	@GetMapping("/member_modify_form")
	public String memberModifyForm(Model model, Principal principal) {
		log.info("memberModifyForm()");
		
		String nextPage = "member/member_modify_form";
		
		MemberDto loginedMemberDto =
				memberService.memberModifyForm(principal.getName());
		
		model.addAttribute("loginedMemberDto", loginedMemberDto);
		
		return nextPage;
		
	}
	
	/*
	 * 회원 계정 수정 확인
	 */
	@PostMapping("/member_modify_confirm")
	public String memberModifyConfirm(MemberDto memberDto) {
		log.info("memberModifyConfirm()");
		
		String nextPage = "member/member_modify_ok";
		
		int result = memberService.memberModifyConfirm(memberDto);
		if (result <= 0)
			nextPage = "member/member_modify_ng";
			
		return nextPage;
		
	}
	
	/*
	 * 회원 탈퇴
	 */
	@GetMapping("/member_delete_confirm")
	public String memberDeleteConfirm(Principal principal) {
		log.info("memberDeleteConfirm()");
		
		String nextPage = "redirect:/member/member_logout_confirm";
		
		int result = memberService.memberDeleteConfirm(principal.getName());
		if (result <= 0)
			nextPage = "member/member_delete_ng";
		
		return nextPage;
		
	}
	
	/*
	 * access_denied(권한거부)
	 */
	@GetMapping("/access_denied")
	public String accessDenied() {
		log.info("accessDenied()");
		
		String nextPage = "member/access_denied";
		
		return nextPage;
		
	}
	
}
