package com.ll.likelionspringboottestmedium.global.rq;

import com.ll.likelionspringboottestmedium.domain.memeber.memeber.entity.Member;
import com.ll.likelionspringboottestmedium.global.rsData.RsData;
import com.ll.likelionspringboottestmedium.global.security.SecurityUser;
import com.ll.likelionspringboottestmedium.standard.util.Ut.Ut;
import jakarta.persistence.EntityManager;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Optional;

@Component
@RequestScope
@RequiredArgsConstructor
public class Rq {
    private final HttpServletRequest request;
    private final HttpServletResponse response;
    private final EntityManager entityManager;
    private Member member;

    public String redirect(String url, String msg) {
        msg = URLEncoder.encode(msg, StandardCharsets.UTF_8);

        StringBuilder sb = new StringBuilder();

        sb.append("redirect:");
        sb.append(url);

        if (msg != null) {
            sb.append("?msg=");
            sb.append(msg);
        }

        return sb.toString();
    }

    public String historyBack(String msg) {
        request.setAttribute("failMsg", msg);

        return "global/js";
    }

    public String redirectOrBack(RsData<?> rs, String path) {
        if (rs.isFail()) return historyBack(rs.getMsg());

        return redirect(path, rs.getMsg());
    }

    public Member getMember() {
        if (isLogout()) return null;

        if (member == null) {
            member = entityManager.getReference(Member.class, getUser().getId());
        }

        return member;
    }

    private boolean isLogout() {
        return !isLogin();
    }

    private boolean isLogin() {
        return getUser() != null;
    }

    private SecurityUser getUser() {
        return Optional.ofNullable(SecurityContextHolder.getContext())
                .map(context -> context.getAuthentication())
                .filter(authentication -> authentication.getPrincipal() instanceof SecurityUser)
                .map(authentication -> (SecurityUser) authentication.getPrincipal())
                .orElse(null);
    }

    public boolean isAdmin() {
        if (isLogout()) return false;

        return getUser()
                .getAuthorities()
                .stream()
                .anyMatch(it -> it.getAuthority().equals("ROLE_ADMIN"));
    }

    public void setAttribute(String key, Object value) {
        request.setAttribute(key, value);
    }

    public String getCurrentQueryStringWithoutParam(String paramName) {
        String queryString = request.getQueryString();

        if (queryString == null) {
            return "";
        }

        queryString = Ut.url.deleteQueryParam(queryString, paramName);

        return queryString;
    }
}
