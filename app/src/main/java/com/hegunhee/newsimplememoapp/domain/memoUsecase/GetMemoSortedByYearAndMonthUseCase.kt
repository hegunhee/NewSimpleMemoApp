package com.hegunhee.newsimplememoapp.domain.memoUsecase

import com.hegunhee.newsimplememoapp.data.entity.Memo
import com.hegunhee.newsimplememoapp.domain.UseCase
import com.hegunhee.newsimplememoapp.model.MemoRepository

class GetMemoSortedByYearAndMonthUseCase(
    private val repository: MemoRepository
) : UseCase {
    suspend operator fun invoke(year: Int, month: Int): List<Memo> {
        return repository.getMemoListSortedByYearAndMonth(year, month)
            .sortedByDescending { it.minute }.sortedByDescending {
                it.hour + if (it.amPm == "오후") 12 else 0
            }.sortedByDescending { it.day }

    }
}