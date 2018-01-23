package be.sigmadelta.substratumdemo.domain.util.status

import be.sigmadelta.substratumdemo.domain.util.error.Error

/**
 * Creator: Bojan Belic
 * Date:    23/01/18
 * Company: Sigma Delta Software Solutions
 */
data class Status (val hasSucceeded: Boolean,
                   val successMsg: String,
                   val error: Error)