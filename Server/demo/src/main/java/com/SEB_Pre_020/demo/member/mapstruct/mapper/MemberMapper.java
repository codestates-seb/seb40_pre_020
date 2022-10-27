package com.SEB_Pre_020.demo.member.mapstruct.mapper;

import com.SEB_Pre_020.demo.member.dto.MemberPostDto;
import com.SEB_Pre_020.demo.member.dto.MemberResponseDto;
import com.SEB_Pre_020.demo.member.entity.Member;
import org.mapstruct.Mapper; // MapSturuct 매퍼 인터페이스로 정의하는 애너테이션
//매퍼 인터페이스를 정의하면 MapStruct가 자동으로 매퍼 구현 클래스를 생성해줌

@Mapper(componentModel = "Spring") // Spring Bean으로 등록하는 애트리뷰트
public interface MemberMapper {
    default Member memberPostDtoToMember(MemberPostDto memberPostDto){
      Member member = new Member();

      member.setEmail(memberPostDto.getEmail());
      member.setName(memberPostDto.getName());
      member.setPassword(memberPostDto.getPassword());

      return member;
    }
    default MemberResponseDto memberToMemberResponseDto(Member member){
      MemberResponseDto memberResponseDto = new MemberResponseDto();

      memberResponseDto.setEmail(member.getEmail());
      memberResponseDto.setName(member.getName());
      memberResponseDto.setPassword(member.getPassword());

      return memberResponseDto;
    }
}
