package hello.login.web.session;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.naming.Name;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;

@Slf4j
@RestController
public class SessionInfoController {
    @GetMapping("/session-info")
    public String sessionInfo(HttpServletRequest request) {
        HttpSession session = request.getSession();
        if (session == null) {
            return "세션이 없습니다";
        }

        session.getAttributeNames().asIterator()
                .forEachRemaining(name -> log.info("session name={}, value={}",name, session.getAttribute(name)));

        log.info("sessionId={}", session.getId());  // JSESSIONID의 값
        log.info("sessionId={}", session.getMaxInactiveInterval()); // 세션의 유효기간
        log.info("creationTime={}", new Date(session.getCreationTime())); // 세션 생성일시
        log.info("lastAccessedTime={}", new Date(session.getLastAccessedTime())); // 세션과 연결된 사용자의 마지막 서버 접근시간
        log.info("isNew={}", session.isNew());  // 새로 생성된 세션인지, 아니면 이미 만들어졌고 클라이언트 -> 서버로 sessionId(JSESSIONID)를 요청해서 조회된 세션인지 여부

        return "세션 출력";

    }
}
