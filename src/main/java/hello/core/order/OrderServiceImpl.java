package hello.core.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import hello.core.annotation.MainDiscountPolicy;
import hello.core.discount.DiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import lombok.RequiredArgsConstructor;

@Component
//@RequiredArgsConstructor	// final이 붙은 필드를 생성자로 만들어 줌
public class OrderServiceImpl implements OrderService{

	private final MemberRepository memberRepository;
	private final DiscountPolicy discountPolicy;    
	// 필드 주입 - 비추천
//	@Autowired private MemberRepository memberRepository;
//	@Autowired private DiscountPolicy discountPolicy;
//	private final DiscountPolicy discountPolicy = new FixDiscountPolicy();
//	private final DiscountPolicy discountPolicy = new RateDiscountPolicy();
	
	
	// 생성자 주입
	// 생성자가 하나일때 @Autowired 생략 가능
////	@Autowired
	public OrderServiceImpl(MemberRepository memberRepository, @MainDiscountPolicy DiscountPolicy discountPolicy) {
		this.memberRepository = memberRepository;
		this.discountPolicy = discountPolicy;
	}
	
	// 수정자(setter) 주입
//	@Autowired
//	public void setMemberRepository(MemberRepository memberRepository) {
//		System.out.println("memberRepository = " + memberRepository);
//		this.memberRepository = memberRepository;
//	}
//
//	@Autowired
//	public void setDiscountPolicy(DiscountPolicy discountPolicy) {
//		System.out.println("discountPolicy = " + discountPolicy);
//		this.discountPolicy = discountPolicy;
//	}

	// 일반 메서드 주입
//	@Autowired
//	public void init(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
//		this.memberRepository = memberRepository;
//		this.discountPolicy = discountPolicy;
//	}
	
	@Override
	public Order createOrder(Long memberId, String itemName, int itemPrice) {
		Member member = memberRepository.findById(memberId);
		int discountPrice = discountPolicy.discount(member, itemPrice);
		
		return new Order(memberId, itemName, itemPrice, discountPrice);
	}

	// 테스트 용도
	public MemberRepository getMemberRepository() {
		return memberRepository;
	}


}
