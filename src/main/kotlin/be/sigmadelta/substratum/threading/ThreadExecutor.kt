/*****************************************************************
MIT License

Copyright (c) 2017 Bojan Belic - Sigma Delta Software Solutions

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
 *********************************************************************/

package be.sigmadelta.substratum.threading

import java.util.concurrent.LinkedBlockingQueue
import java.util.concurrent.ThreadPoolExecutor
import java.util.concurrent.TimeUnit

import be.sigmadelta.substratum.usecase.AbstractUseCase


class ThreadExecutor private constructor() : Executor {

    private var _threadPoolExecutor: ThreadPoolExecutor = ThreadPoolExecutor(
                                                        CORE_POOL_SIZE,
                                                        MAX_POOL_SIZE,
                                                        KEEP_ALIVE_TIME,
                                                        TIME_UNIT,
                                                        WORK_QUEUE)

    override fun execute(useCase: AbstractUseCase) {
        _threadPoolExecutor.submit {
            useCase.run()
            useCase.onFinished()
        }
    }

    public fun setThreadPoolProperties(properties: ThreadPoolProperties) {
        _threadPoolExecutor = ThreadPoolExecutor(properties.corePoolSize,
                properties.maxPoolSize,
                properties.keepAliveTime,
                properties.timeUnit,
                properties.workQueue)
    }

    companion object {

        @Volatile private var _threadExecutor: ThreadExecutor = ThreadExecutor()

        private val CORE_POOL_SIZE = 4
        private val MAX_POOL_SIZE = 8
        private val KEEP_ALIVE_TIME = 90L
        private val TIME_UNIT = TimeUnit.SECONDS
        private val WORK_QUEUE = LinkedBlockingQueue<Runnable>()

        val INSTANCE: Executor?
            get() {
                return _threadExecutor
            }
    }
}
