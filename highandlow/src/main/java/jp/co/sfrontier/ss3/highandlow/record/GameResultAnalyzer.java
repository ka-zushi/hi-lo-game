package jp.co.sfrontier.ss3.highandlow.record;

import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 対戦結果の分析を行うクラス。
 * <p>
 * 対戦結果をもとに、直近の対戦結果、月別成績、最多連勝数などを取得する。
 * 
 */
public class GameResultAnalyzer {

	/** 分析対象の対戦結果 */
	private final List<GameResult> results;

	/**
	 * GameResultAnalyzerを生成する。
	 *
	 * @param results 分析対象の対戦結果
	 */
	public GameResultAnalyzer(List<GameResult> results) {
		this.results = results;
	}

	/**
	 * 対戦結果を新しい日時順に、指定された件数まで取得する。
	 *
	 * @param count 取得する対戦結果の最大件数
	 * @return 新しい日時順に並べた対戦結果
	 */
	public List<GameResult> getRecentResults(int count) {

		//GameResultを対戦日時の新しい順に並べるための比較ルール
		Comparator<GameResult> comparator = Comparator.comparing(GameResult::getPlayedAt)
				.reversed();

		return results.stream() //List<GameResult> ⇒ Stream<GameResult>
				.sorted(comparator) //日時の新しい順
				.limit(count) //先頭からcount件
				.toList(); //Stream<GameResult> ⇒ List<GameResult>

	}

	/**
	 * 対戦結果を年月ごとに分類する。
	 *
	 * @return 年月ごとに分類した対戦結果
	 */
	public Map<YearMonth, List<GameResult>> groupByMonth() {

		return results.stream()
				.collect(
						Collectors.groupingBy(
								result -> YearMonth.from(result.getPlayedAt())));
	}

	/**
	 * 月ごとの対戦数と勝率を取得する。
	 *
	 * @return 月別の対戦成績
	 */
	public List<MonthlyGameResult> getMonthlyResults() {

		Map<YearMonth, List<GameResult>> groupedResults = groupByMonth();

		List<MonthlyGameResult> monthlyAllResults = new ArrayList<>();

		for (Map.Entry<YearMonth, List<GameResult>> entry : groupedResults.entrySet()) {

			YearMonth month = entry.getKey();
			List<GameResult> monthlyResults = entry.getValue();

			// 対戦数・勝率を計算
			int totalCount = monthlyResults.size();

			long winCount = monthlyResults.stream()
					.filter(GameResult::isWin)
					.count();

			double winRate = (double) winCount / totalCount * 100;

			MonthlyGameResult monthlyGameResult = new MonthlyGameResult(
					month,
					totalCount,
					winRate);

			monthlyAllResults.add(monthlyGameResult);
		}

		return monthlyAllResults;
	}

	/**
	 * 最多連勝数を取得する。
	 *
	 * @return 最多連勝数
	 */
	public int getMaxWinningStreak() {

		int currentStreak = 0;
		int maxStreak = 0;

		List<GameResult> sortedResults = results.stream()
				.sorted(Comparator.comparing(GameResult::getPlayedAt))
				.toList();

		for (GameResult result : sortedResults) {

			if (result.isWin()) {
				currentStreak++;
				maxStreak = Math.max(maxStreak, currentStreak);

			} else {
				currentStreak = 0;
			}
		}
		return maxStreak;
	}
}