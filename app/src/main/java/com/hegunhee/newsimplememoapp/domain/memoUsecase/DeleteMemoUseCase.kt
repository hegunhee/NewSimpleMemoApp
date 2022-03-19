package com.hegunhee.newsimplememoapp.domain.memoUsecase

import com.hegunhee.newsimplememoapp.data.entity.Memo
import com.hegunhee.newsimplememoapp.domain.UseCase
import com.hegunhee.newsimplememoapp.model.MemoRepository

class DeleteMemoUseCase(private val repository: MemoRepository) : UseCase {
    suspend operator fun invoke(memo : Memo){
        repository.deleteMemo(memo)
    }
}