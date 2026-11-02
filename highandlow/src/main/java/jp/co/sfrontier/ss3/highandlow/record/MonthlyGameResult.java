package jp.co.sfrontier.ss3.highandlow.record;

import java.time.YearMonth;

/**
 * 1か月分の対戦成績を格納するDTO
 */
public class MonthlyGameResult {

    private final YearMonth month;
    private final int totalCount;
    private final double winRate;

    public MonthlyGameResult(
            YearMonth month,
            int totalCount,
            double winRate) {

        this.month = month;
        this.totalCount = totalCount;
        this.winRate = winRate;
    }

    public YearMonth getMonth() {
        return month;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public double getWinRate() {
        return winRate;
    }
}