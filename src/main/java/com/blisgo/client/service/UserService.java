package com.blisgo.client.service;

import java.util.ArrayList;

import com.blisgo.client.dto.DictionaryDTO;
import com.blisgo.client.dto.UserDTO;

public interface UserService {

	// 회원가입 회원 추가 메서드
	public boolean insert(UserDTO user);
	
	//회원 로그인 메서드
	public String userLogin(UserDTO user); 
	
	// 회원 전체 정보 조회 메서드
	public UserDTO getUser(UserDTO user);
	
	// 이메일 중복 확인 메서드
	public int emailCheck(String email);
	
	// 회원 QR 생성 메서드
	public String qrCreate(UserDTO user);
	
	// 회원 닉네임 변경 메서드
	public boolean modifyAccount(UserDTO user);
	
	// 회원 탈퇴 메서드
	public boolean deleteAccount(UserDTO user);
	
	// 회원 비밀번호 변경 메서드
	public boolean modifyPassword(String pass, String email, int mem_no);
	
	// 도감 목록 조회 메서드
	public ArrayList<DictionaryDTO> mydogamList(String dogamList);
	
	// 도감 더보기 메서드
	public ArrayList<DictionaryDTO> dogamLoadMore(String dogamList);
	
	// 회원 순위 조회 메서드
	public ArrayList<UserDTO> rankList();
}
