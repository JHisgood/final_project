package mul.com.a.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mul.com.a.dao.UserDao;
import mul.com.a.dto.UserDto;

@Transactional
@Service
public class UserService {

	@Autowired
	UserDao dao;
	
	@Autowired
	private PasswordEncoder encoder;
	
	public String getpw(String id) {
		return dao.getpwd(id);
	}
	
	public UserDto login(String id, String pwd) {
		return dao.login(id, pwd);
	}
	
	public boolean register(UserDto dto) {
		dto.setPwd(encoder.encode(dto.getPwd()));
		return dao.register(dto)>0?true:false;
	}
	
	public boolean pwdchange(String id, String new_pwd) {
		
		new_pwd = encoder.encode(new_pwd);
		
		return dao.pwdchange(id, new_pwd)>0?true:false;
	}
	
	public boolean idcheck(String id) {

		return dao.idcheck(id)>0?true:false;
	}
}
