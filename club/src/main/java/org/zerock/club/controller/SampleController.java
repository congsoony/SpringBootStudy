package org.zerock.club.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Log4j2
@RequestMapping("/sample/")
public class SampleController {

	@GetMapping("/all")
	public void exAll(){
		//모든 사용자가 접근가능
		log.info("exAll.........");
	}

	@GetMapping("/member")
	public void exMember(){

		//로그인한 사용자만 접근가능
		log.info("exMember.............");
	}

	@GetMapping("/admin")
	public void exAdmin(){
		//권한이있는 사용자만 접근가능
		log.info("exAdmin..........");
	}
}
