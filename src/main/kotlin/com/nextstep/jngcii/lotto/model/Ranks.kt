package com.nextstep.jngcii.lotto.model

class Ranks(private val ranks: List<Rank>) {
    constructor(
        lottos: List<Lotto>,
        lastWeekLotto: Lotto,
        bonusNumber: BonusNumber
    ) : this(
        ranks = lottos.mapNotNull {
            val sameCount = it.getSameCount(lastWeekLotto)
            val bonusMatch = it.contains(bonusNumber)
            Rank.of(sameCount, bonusMatch)
        }
    )
    val sumOfPrice = ranks.sumOf { it.price }.toDouble()
    fun countOf(rank: Rank) = ranks.count { it == rank }
}
