package hello.core.discount;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import hello.core.AppConfig;
import hello.core.member.Grade;
import hello.core.member.Member;

public class RateDiscountPolicyTest {

	DiscountPolicy discountPolicy;
	
	@BeforeEach
	void beforeEach() {
		AppConfig appConfig = new AppConfig();
		discountPolicy = appConfig.discountPolicy();
	}
	
	@Test
	@DisplayName("VIP는 10% 할인이 적용되어야 한다")
	void vip_o() {
		//given
		Member member = new Member(1L, "memberVIP", Grade.VIP);
		
		//when
		int discount = discountPolicy.discount(member, 10000);
		
		//then
		assertThat(discount).isEqualTo(1000);
	}
	
	@Test
	@DisplayName("VIP가 아니면 할인이 적용되지 않아야 한다")
	void vip_x() {
		//given
		Member member = new Member(2L, "memberBasic", Grade.BASIC);
		
		//when
		int discount = discountPolicy.discount(member, 10000);
		
		//then
		assertThat(discount).isEqualTo(0);
	}
}
