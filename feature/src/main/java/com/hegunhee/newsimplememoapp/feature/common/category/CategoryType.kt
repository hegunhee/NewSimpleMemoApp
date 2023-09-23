package com.hegunhee.newsimplememoapp.feature.common.category

sealed interface CategoryType {

    object Empty : CategoryType

    object Asset : CategoryType

    object AttrIncome : CategoryType

    object AttrExpenses : CategoryType
}