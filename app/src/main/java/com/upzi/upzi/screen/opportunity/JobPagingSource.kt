package com.upzi.upzi.screen.opportunity

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.upzi.domain.model.JobDetail
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import java.io.IOException

class JobPagingSource(
    private val jobList: List<JobDetail>,
    val pageSize: Int
) : PagingSource<Int, JobDetail>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, JobDetail> {
        val pageNumber = params.key ?: 1 // Start from page 1
        return try {
            val jobs = withContext(Dispatchers.IO) { // Perform network/database IO on background thread
                delay(1000) // Mô phỏng độ trễ (1 giây)

                val startIndex = (pageNumber - 1) * pageSize
                val endIndex = (pageNumber * pageSize + pageSize).coerceAtMost(jobList.size) //Dynamic Array
                jobList.subList(startIndex, endIndex)
            }
            LoadResult.Page(
                data = jobs,
                prevKey = if (pageNumber == 1) null else pageNumber - 1,
                nextKey = if (jobs.size < pageSize) null else pageNumber + 1
            )
        } catch (e: IOException) {
            return LoadResult.Error(e)
        } catch (e: Exception) {
            return LoadResult.Error(e)
        }
    }
    override fun getRefreshKey(state: PagingState<Int, JobDetail>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }
}