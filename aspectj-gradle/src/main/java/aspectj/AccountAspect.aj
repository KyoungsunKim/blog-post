package aspectj;

import com.ksnote.Account;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public aspect AccountAspect {
    private final int MIN_BALANCE = 10;

    pointcut callWithDraw(int amount, Account acc) :
            call(boolean com.ksnote.Account.withdraw(int)) && args(amount) && target(acc);

    before(int amount, Account acc) : callWithDraw(amount, acc) {
        System.out.printf("before");
        log.info("before");
    }

    boolean around(int amount, Account acc) :
            callWithDraw(amount, acc) {
        if (acc.balance < amount) {
            return false;
        }
        return proceed(amount, acc);
    }

    after(int amount, Account balance) : callWithDraw(amount, balance) {
        System.out.printf("after");
        log.info("after");
    }
}
