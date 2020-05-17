import {IResponse} from 'types/IResponse'
import axios from 'axios'
import {IJob} from 'types/IJob'

export const createJob = async (requestId: string, job: IJob): Promise<IResponse> => {
    return await axios.post(`http://localhost:8080/pa165/rest/jobs/create/request/${requestId}`, job)
        .then((response) => {
            return {
                success: true,
            }
        })
        .catch((error) => {
            return {
                success: false,
                error: error.message,
            }
        })
}

export const deleteJob = async (jobId: string) => {
    return await axios.delete(`http://localhost:8080/pa165/rest/jobs/${jobId}`)
        .then((response) => {
            return {
                success: true,
            }
        })
        .catch((error) => {
            return {
                success: false,
                error: error.message,
            }
        })
}
