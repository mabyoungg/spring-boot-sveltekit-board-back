package com.ll.likelionspringboottestmedium.global.security;

import com.ll.likelionspringboottestmedium.domain.memeber.memeber.entity.Member;
import com.ll.likelionspringboottestmedium.domain.memeber.memeber.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Slf4j
public class CustomOAuth2UserService extends DefaultOAuth2UserService {
    private final MemberService memberService;

    @Override
    @Transactional
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(userRequest);

        String oauthId = oAuth2User.getName();
        Map<String, Object> attributes = oAuth2User.getAttributes();
        Map attributesProperties = (Map) attributes.get("properties");

        String nickname = (String) attributesProperties.get("nickname");
        String profileImgUrl = (String) attributesProperties.get("profile_image");
        String providerTypeCode = userRequest.getClientRegistration().getRegistrationId().toUpperCase();
        String username = providerTypeCode + "__%s".formatted(oauthId);
        Member member = memberService.whenSocialLogin(providerTypeCode, username, nickname, profileImgUrl).getData();

        return new SecurityUser(member.getId(), member.getUsername(), member.getPassword(), member.getAuthorities());
    }
}
