package com.hegunhee.newsimplememoapp.di

import com.hegunhee.newsimplememoapp.domain.koinTest.AddPeopleUseCase
import com.hegunhee.newsimplememoapp.domain.koinTest.GetAllPeopleUseCase
import com.hegunhee.newsimplememoapp.domain.koinTest.SayHelloUseCase
import com.hegunhee.newsimplememoapp.model.DefaultKoinTestTestRepository
import com.hegunhee.newsimplememoapp.model.KoinTestRepository
import org.koin.dsl.module

val koinTestModule = module {

    factory { SayHelloUseCase(get()) }
    factory { AddPeopleUseCase(get()) }
    factory { GetAllPeopleUseCase(get()) }

    single<KoinTestRepository> { DefaultKoinTestTestRepository() }
}