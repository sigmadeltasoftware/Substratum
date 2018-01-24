package be.sigmadelta.substratumdemo.domain.util.status

/**
 * Creator: Bojan Belic
 * Date:    24/01/18
 * Company: Sigma Delta Software Solutions
 */

data class StatusPair<out T> (val status: Status,
                              val payload: T?)