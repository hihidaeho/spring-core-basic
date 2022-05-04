package hello.core;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;


// 순수 자바
//public class AppConfig {
//	
//	public MemberService memberService() {
//		return new MemberServiceImpl(memberRepository());
//	}
//	
//	private MemberRepository memberRepository() {
//		return new MemoryMemberRepository();
//	}
//	
//	public OrderService orderService() {
//		return new OrderServiceImpl(memberRepository(), discountPolicy());
//	}
//	
//	public DiscountPolicy discountPolicy() {
////		return new FixDiscountPolicy();
//		return new RateDiscountPolicy();
//	}
//}


// spring
@Configuration
public class AppConfig {
	
	//call AppConfig.memberService
	//call AppConfig.memberRepository
	//call AppConfig.memberRepository
	//call AppConfig.orderService
	//call AppConfig.memberRepository
	
	//call AppConfig.memberService
	//call AppConfig.orderService
	//call AppConfig.memberRepository

	@Bean
	public MemberService memberService() {
//		System.out.println("call AppConfig.memberService");
		return new MemberServiceImpl(memberRepository());
	}
	
	@Bean
	public MemberRepository memberRepository() {
//		System.out.println("call AppConfig.memberRepository");
		return new MemoryMemberRepository();
	}
	
	@Bean
	public OrderService orderService() {
//		System.out.println("call AppConfig.orderService");
		return new OrderServiceImpl(memberRepository(), discountPolicy());
//		return null;
	}
	
	@Bean
	public DiscountPolicy discountPolicy() {
//		return new FixDiscountPolicy();
		return new RateDiscountPolicy();
	}
	
}