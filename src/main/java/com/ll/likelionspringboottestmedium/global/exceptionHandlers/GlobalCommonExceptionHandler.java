import com.ll.likelionspringboottestmedium.global.exceptions.GlobalException;
import com.ll.likelionspringboottestmedium.global.rq.Rq;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice(annotations = Controller.class)
@RequiredArgsConstructor
public class GlobalCommonExceptionHandler {
    private final Rq rq;

    @ExceptionHandler(GlobalException.class)
    public String handle(GlobalException ex) {
        return rq.historyBack(ex);
    }
}