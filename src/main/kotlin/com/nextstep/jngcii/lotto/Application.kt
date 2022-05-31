package com.nextstep.jngcii.lotto

import com.nextstep.jngcii.lotto.model.Lotto
import com.nextstep.jngcii.lotto.model.LottoMachine
import com.nextstep.jngcii.lotto.model.LottoNumber
import com.nextstep.jngcii.lotto.model.Lottos
import com.nextstep.jngcii.lotto.view.InputView
import com.nextstep.jngcii.lotto.view.ResultView

fun main() {
    val count = InputView.getCount { readLine() }

    val lottos = LottoMachine.get(count)
    ResultView.printList(lottos)

    val record = Lottos(lottos)

    val lastWeekNumbers = InputView.getNumbers { readLine() }
    val lastWeekLotto = Lotto(lastWeekNumbers.map { LottoNumber(it) })

    val ranks = record.getResult(lastWeekLotto)
    ResultView.printResult(count, ranks)
}