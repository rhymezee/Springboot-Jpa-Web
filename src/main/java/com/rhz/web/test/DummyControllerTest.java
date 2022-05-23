package com.rhz.web.test;

import java.util.List;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.rhz.web.model.RoleType;
import com.rhz.web.model.User;
import com.rhz.web.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class DummyControllerTest {
	
	private final UserRepository userRepository;
	
	@GetMapping("/dummy/user/{id}")
	public User detail(@PathVariable int id) {
		User user = userRepository.findById(id).orElseThrow(() -> {
			return new IllegalArgumentException("해당 유저는 없습니다. id : " + id);
		});
		// MessageConverter가 응답시에 자동 작동
		// Java Object (User)를 리턴하게 되면 MessageConverter가 Jackson 라이브러리를 호출해서 User 오브젝트를 json으로 변환해서 브라우저에게 전달해준다.
		return user;
	}
	
	@GetMapping("/dummy/users")
	public List<User> list() {
		return userRepository.findAll();
	}
	
	@GetMapping("/dummy/user")
	public List<User> pageList(@PageableDefault(size = 2, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
		Page<User> pagingUser = userRepository.findAll(pageable);
		
		List<User> users = pagingUser.getContent();
		return users;
	}
	
	@PostMapping("/dummy/join")
	public String join(User user) {
		System.out.println("userName : " + user.getUsername());
		System.out.println("password : " + user.getPassword());
		System.out.println("characterName : " + user.getCharacterName());
		user.setRole(RoleType.USER);
		userRepository.save(user);
		return "회원가입이 완료되었습니다.";
	}
	
	@Transactional // 함수 종료 시 자동 Commit이 됨.
	@PutMapping("/dummy/user/{id}")
	public User updateUser(@PathVariable int id, @RequestBody User requestUser) {
		// json 데이터를 받을 때는 @RequestBody 어노테이션을 붙여줘야 한다.
		// json 데이터를 요청 -> MessageConverter의 Jackson 라이브러리가 Java Object로 변환해서 받아준다.
		System.out.println("id : " + id);
		System.out.println("password : " + requestUser.getPassword());
		System.out.println("characterName : " + requestUser.getCharacterName());
		
		// 1. 영속화 (SELECT) : 영속성 컨텍스트의 1차 캐시에 영속화를 시킨다.
		User user = userRepository.findById(id).orElseThrow(() -> {
			return new IllegalArgumentException("수정에 실패했습니다.");
		});
		
		// 2. 값 변경 (UPDATE)
		user.setPassword(requestUser.getPassword());
		user.setCharacterName(requestUser.getCharacterName());
		
		// 3. 변경 감지 (FLUSH) : 더티 체킹
		return user;
	}
	
	@DeleteMapping("/dummy/user/{id}")
	public String deleteUser(@PathVariable int id) {
		try {
			userRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			return "삭제에 실패했습니다. 해당 id는 없는 id입니다.";
		}
		
		return "삭제 되었습니다. id : " + id;
	}

}