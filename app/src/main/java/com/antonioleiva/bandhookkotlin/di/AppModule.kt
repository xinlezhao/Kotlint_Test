/*
 * Copyright (C) 2015 Antonio Leiva
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.antonioleiva.bandhookkotlin.di

import android.content.Context
import com.antonioleiva.bandhookkotlin.domain.BusImpl
import com.antonioleiva.bandhookkotlin.domain.interactor.base.Bus
import com.antonioleiva.bandhookkotlin.domain.interactor.base.CustomJobManager
import com.antonioleiva.bandhookkotlin.domain.interactor.base.InteractorExecutor
import com.antonioleiva.bandhookkotlin.domain.interactor.base.InteractorExecutorImpl
import com.path.android.jobqueue.JobManager
import java.util.*

interface AppModule : AppContext, BusSingleton, InteractorExecutorSingleton, JobManagerSingleton, LanguageSingleton

class AppModuleImpl(context: Context): AppModule {
    override val appContext = context;
    override val bus by lazy { BusImpl() }
    override val jobManager by lazy { CustomJobManager(context) }
    override val interactorExecutor by lazy { InteractorExecutorImpl(jobManager, bus) }
    override val language by lazy { Locale.getDefault().language }
}

interface AppContext {
    val appContext: Context
}

interface BusSingleton {
    val bus: Bus
}

interface JobManagerSingleton {
    val jobManager: JobManager
}

interface InteractorExecutorSingleton {
    val interactorExecutor: InteractorExecutor
}

interface LanguageSingleton {
    val language: String
}