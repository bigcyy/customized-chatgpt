import axios, { type AxiosRequestConfig } from 'axios'
import { MsgError } from '@/utils/message'
import type { NProgress } from 'nprogress'
import type { Ref } from 'vue'
import { ref, type WritableComputedRef } from 'vue'
import Result from './Result'

const axiosConfig = {
  baseURL: '/api/api/v1',
  withCredentials: false,
  timeout: 600000,
  headers: {}
}

const instance = axios.create(axiosConfig)
/* 设置请求拦截器 */
instance.interceptors.request.use(
  (config: AxiosRequestConfig) => {
    return config
  },
  (err: any) => {
    return Promise.reject(err)
  }
)

//设置响应拦截器
instance.interceptors.response.use(
  (response: any) => {
    const result = response.data
    if (!result.success) {
      MsgError(result.message || '请求失败')
      return Promise.reject(result)
    }
    return result
  },
  (err: any) => {
    if (err.code === 'ECONNABORTED') {
      MsgError(err.message)
      console.error(err)
    }

    return Promise.reject(err)
  }
)

export const request = instance

/* 简化请求方法，统一处理返回结果，并增加loading处理，这里以{success,data,message}格式的返回值为例，具体项目根据实际需求修改 */
const promise: (
  request: Promise<any>,
  loading?: NProgress | Ref<boolean> | WritableComputedRef<boolean>
) => Promise<Result<any>> = (request, loading = ref(false)) => {
  return new Promise((resolve, reject) => {
    if ((loading as NProgress).start) {
      ;(loading as NProgress).start()
    } else {
      ;(loading as Ref).value = true
    }
    request
      .then((response) => {
        resolve(response)
      })
      .catch((error) => {
        reject(error)
      })
      .finally(() => {
        if ((loading as NProgress).start) {
          ;(loading as NProgress).done()
        } else {
          ;(loading as Ref).value = false
        }
      })
  })
}

/**
 * 发送get请求   一般用来请求资源
 * @param url    资源url
 * @param params 参数
 * @param loading loading
 * @returns 异步promise对象
 */
export const get: (
  url: string,
  params?: unknown,
  loading?: NProgress | Ref<boolean>,
  timeout?: number
) => Promise<Result<any>> = (
  url: string,
  params: unknown,
  loading?: NProgress | Ref<boolean>,
  timeout?: number
) => {
  return promise(request({ url: url, method: 'get', params, timeout: timeout }), loading)
}
