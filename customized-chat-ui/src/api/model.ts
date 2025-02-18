import type { Ref } from 'vue'
import Result from '@/request/Result'
import { get } from '@/request'
import { type Provider } from '@/api/type/model'

/**
 * 获得供应商列表
 */
const getProvider: (loading?: Ref<boolean>) => Promise<Result<any>> = (loading) => {
  return get('/setting/providers', {}, loading)
}

/**
 * 根据模型的类型获得供应商列表
 */
const getProviderByModelType: (
  model_type: string,
  loading?: Ref<boolean>
) => Promise<Result<any>> = (model_type, loading) => {
  return get('/setting/providers', {}, loading)
}

export default {
  getProvider,
  getProviderByModelType
}
