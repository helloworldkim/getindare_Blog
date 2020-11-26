package com.cos.blog.config.auth;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.cos.blog.model.User;

import lombok.Getter;

@Getter
public class PrincipalDetail implements UserDetails{
		/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
		private User user; //콤포지션
		
		public PrincipalDetail(User user) {
			this.user = user;
		}
		@Override
		public String getPassword() {
			return user.getPassword();
		}

		@Override
		public String getUsername() {
			return  user.getUsername();
		}
		//계정이 만료되지 않았는지 리턴한다. (true = 만료안됨)
		@Override
		public boolean isAccountNonExpired() {
			return true;
		}
		//계정이 잠겨있지 않았는지 리턴한다. (true = 잠기지않음)
		@Override
		public boolean isAccountNonLocked() {
			return true;
		}
		//비밀번호가 만료되지 않았는지 리턴한다. (true = 만료안됨)
		@Override
		public boolean isCredentialsNonExpired() {
			return true;
		}
		//계정이 활성화(사용가능)인지 리턴한다. (true = 활성화)
		@Override
		public boolean isEnabled() {
			return true;
		}
		
		//계정이 갖고있는 권한 목록을 리턴한다(권한이 여러개 있을 수 있어서 루프(for등)를 돌아야하는데 현재 user만 있어서 한개만!)
		@Override
		public Collection<? extends GrantedAuthority> getAuthorities() {
			Collection<GrantedAuthority> colletors = new ArrayList<>(); //arrayList는 collection타입임!
		/*	colletors.add(new GrantedAuthority() {
				//GrantedAuthority 인터페이스라 익명클래스를 생성함
				@Override
				public String getAuthority() {
					
					return "ROLE_"+user.getRole(); //ROLE_USER 의 형식이여야 스프링 시큐리티가 확인가능함! _빠지면안됨
				}
			});*/
			//람다식으로 바꿨을때! 위의 GrantedAuthority인터페이스가 딱 하나의 메서드만 가지고있어서 이렇게 사용가능 
			colletors.add(()->{return"ROLE_"+user.getRole();});
			
			return colletors;
		}
}
