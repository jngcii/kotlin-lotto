package com.nextstep.jngcii.lotto.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

class RanksTest {
    @ParameterizedTest
    @MethodSource("sumOfPrice")
    fun `단순상금 총액 계산 테스트`(rankList: List<Rank>, sum: Double) {
        assertThat(Ranks(rankList).sumOfPrice).isEqualTo(sum)
    }

    @ParameterizedTest
    @MethodSource("countOf")
    fun `포함 갯수 확인 테스트`(rankList: List<Rank>, target: Rank, count: Int) {
        assertThat(Ranks(rankList).countOf(target)).isEqualTo(count)
    }

    companion object {
        @JvmStatic
        fun sumOfPrice() = listOf(
            Arguments.of(
                listOf(Rank.THIRD, Rank.THIRD),
                100_000
            ),
            Arguments.of(
                listOf(Rank.FIRST, Rank.THIRD),
                2_000_050_000
            ),
            Arguments.of(
                listOf(Rank.THIRD, Rank.SECOND, Rank.SECOND, Rank.FOURTH),
                3_055_000
            )
        )

        @JvmStatic
        fun countOf() = listOf(
            Arguments.of(
                listOf(Rank.THIRD, Rank.THIRD),
                Rank.THIRD,
                2
            ),
            Arguments.of(
                listOf(Rank.FIRST, Rank.THIRD),
                Rank.THIRD,
                1
            ),
            Arguments.of(
                listOf(Rank.THIRD, Rank.SECOND, Rank.SECOND, Rank.FOURTH),
                Rank.FIRST,
                0
            )
        )
    }
}
