package be.sigmadelta.substratum.threading

import java.util.concurrent.BlockingQueue
import java.util.concurrent.TimeUnit

data class ThreadPoolProperties(val corePoolSize: Int,
                                val maxPoolSize: Int,
                                val keepAliveTime: Long,
                                val timeUnit : TimeUnit,
                                val workQueue: BlockingQueue<Runnable>)
