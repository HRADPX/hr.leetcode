package com.hr.design;

import java.util.*;
import java.util.logging.Logger;

public class PayService implements PayInfoService {

    Logger logger = Logger.getLogger("payService");

    private final Map<PayMethod, MoneyService> payServiceMap = new HashMap<>(4);
    // read from configuration center
    private static final boolean SUPPORT_COMBINE_PAY = true;

    {
        payServiceMap.put(PayMethod.REMAINING, new RemainingServiceImpl());
        payServiceMap.put(PayMethod.BANK_CARD, new BankCardServiceImpl());
        payServiceMap.put(PayMethod.HUA_BEI, new HuaBeiServiceImpl());
    }

    @Override
    public List<PayMethodInfo> deducePayInfoList(long userId, double candidateMoney) {
        List<PayMethodInfo> rs = new ArrayList<>();

        Map<PayMethod, Double> remainMap = new HashMap<>();

        List<List<MoneyService>> payList = SubSetUtils.subSet(new ArrayList<>(payServiceMap.values()));
        for (List<MoneyService> moneyServices : payList) {

            if (!SUPPORT_COMBINE_PAY && moneyServices.size() > 1) {
                continue;
            }
            double money = 0;
            for (MoneyService moneyService : moneyServices) {
                money += remainMap.getOrDefault(moneyService.payMethod(), 0.0);
                if (money > candidateMoney) {
                    rs.add(new PayMethodInfo(moneyService.payMethod(), 0));
                    break;
                }
            }
        }
        return null;
    }
}

class SubSetUtils {

    public static <T> List<List<T>> subSet(List<T> list) {

        if (list == null || list.isEmpty()) {
            return Collections.emptyList();
        }
        List<List<T>> rs = new ArrayList<>();
        backtrace(rs, list, new ArrayList<>(), 0);
        return rs;
    }

    private static <T> void backtrace(List<List<T>> rs, List<T> nums, List<T> list, int idx) {

        if (!list.isEmpty()) {
            rs.add(new ArrayList<>(list));
        }

        for (int i = idx; i < nums.size(); i++) {
            list.add(nums.get(i));
            backtrace(rs, nums, list, i + 1);
            list.remove(list.size() - 1);
        }
    }
}

class PayMethodInfo {

    PayMethodInfo(PayMethod payMethod, double payMoney) {
        this.payMethod = payMethod.name();
        this.payMoney = payMoney;
    }
    private final String payMethod;
    private final double payMoney;
}

interface PayInfoService {
    List<PayMethodInfo> deducePayInfoList(long userId, double candidateMoney);
}

interface MoneyService {
    double queryMoneyByUserId(long userId);
    boolean pay(long userId, double money);
    PayMethod payMethod();
}

class RemainingServiceImpl implements MoneyService {

    @Override
    public double queryMoneyByUserId(long userId) {
        // query from db
        return 0;
    }

    @Override
    public boolean pay(long userId, double money) {
        return false;
    }

    @Override
    public PayMethod payMethod() {
        return PayMethod.REMAINING;
    }
}

class BankCardServiceImpl implements MoneyService {

    @Override
    public double queryMoneyByUserId(long userId) {
        return 0;
    }

    @Override
    public boolean pay(long userId, double money) {
        return false;
    }

    @Override
    public PayMethod payMethod() {
        return PayMethod.BANK_CARD;
    }
}

class HuaBeiServiceImpl implements MoneyService {

    @Override
    public double queryMoneyByUserId(long userId) {
        return 0;
    }

    @Override
    public boolean pay(long userId, double money) {
        return false;
    }

    @Override
    public PayMethod payMethod() {
        return PayMethod.HUA_BEI;
    }
}


enum PayMethod {

    REMAINING, BANK_CARD, HUA_BEI
}
