package com.hegunhee.newsimplememoapp.feature.memo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hegunhee.newsimplememoapp.domain.usecase.GetMemoTypeListSortedByYearAndMonthUseCase
import com.hegunhee.newsimplememoapp.domain.model.MemoType
import com.hegunhee.newsimplememoapp.feature.util.DateUtil
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MemoViewModel @Inject constructor(
    private val getAllMemoTypeBySortUseCase : GetMemoTypeListSortedByYearAndMonthUseCase
) : ViewModel(), MemoActionHandler {

    private val _memoNavigation : MutableSharedFlow<MemoNavigation> = MutableSharedFlow<MemoNavigation>()
    val memoNavigation : SharedFlow<MemoNavigation> = _memoNavigation.asSharedFlow()

    val yearDate = MutableStateFlow<Int>(DateUtil.getYear())
    val monthDate = MutableStateFlow<Int>(DateUtil.getMonth())

    val memoList : StateFlow<List<MemoType>> = yearDate.combine(monthDate) { year, month ->
        val memoList = getAllMemoTypeBySortUseCase(year,month)
        setPrices(memoList.filterIsInstance<MemoType.Memo>())
        memoList
    }.stateIn(
        scope =  viewModelScope,
        started = SharingStarted.WhileSubscribed(5000L),
        initialValue = emptyList()
    )

    val incomeValue = MutableStateFlow<Int>(0)
    val expenseValue = MutableStateFlow<Int>(0)
    val totalValue = MutableStateFlow<Int>(0)

    fun onPreviousMonthClick() {
        if (monthDate.value <= 1) {
            yearDate.value = yearDate.value - 1
            monthDate.value = 12
        } else {
            monthDate.value = monthDate.value - 1
        }
    }

    fun onNextMonthClick() {
        if (monthDate.value >= 12) {
            yearDate.value = yearDate.value + 1
            monthDate.value = 1
        } else {
            monthDate.value = monthDate.value + 1
        }
    }

    private fun setPrices(memoList : List<MemoType.Memo>) {
        incomeValue.value = memoList.filter { it.category == "수입" }.sumOf { it.price }
        expenseValue.value = memoList.filter { it.category != "수입" }.sumOf { it.price }
        totalValue.value = incomeValue.value - expenseValue.value
    }
    override fun addMemo() {
        viewModelScope.launch {
            _memoNavigation.emit(MemoNavigation.AddMemo)
        }
    }

    override fun detailMemo(memoId: Int) {
        viewModelScope.launch {
            _memoNavigation.emit(MemoNavigation.DetailMemo(memoId))
        }
    }
}