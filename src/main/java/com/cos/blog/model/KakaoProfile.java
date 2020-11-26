package com.cos.blog.model;

import lombok.Data;
//http://www.jsonschema2pojo.org/ 사이트를 통해서 만든 객체임 json데이터를 바로받을수 있도록 객체화 시켜주는걸 대신해주는 사이트
//_로 구성되어있는경우 자동으로 카멜case로 바꿔주기때문에 확인이 필요함
@Data
public class KakaoProfile {

	public Integer id;
	public String connected_at;
	public Properties properties;
	public KakaoAccount kakao_account;

	@Data
	public class Properties {

		public String nickname;
		public String profile_image;
		public String thumbnail_image;

	}
	@Data
	public class KakaoAccount {

		public Boolean profile_needs_agreement;
		public Profile profile;
		public Boolean has_email;
		public Boolean email_needs_agreement;
		public Boolean is_email_valid;
		public Boolean is_email_verified;
		public String email;

		@Data
		public class Profile {

			public String nickname;
			public String thumbnail_image_url;
			public String profile_image_url;

		}

	}
}
