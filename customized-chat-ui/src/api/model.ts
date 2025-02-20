import type { Ref } from 'vue'
import Result from '@/request/Result'
import { get } from '@/request'

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

/**
 * 获得所有模型类型
 */
const getAllModelTypes: (loading?: Ref<boolean>) => Promise<Result<any>> = (loading) => {
  return get('/setting/model/types', {}, loading)
}

/**
 * 获得提供商可以提供的模型类型
 */
const getModelTypes: (provider: string, loading?: Ref<boolean>) => Promise<Result<any>> = (
  provider,
  loading
) => {
  return get(`/setting/${provider}/types`, {}, loading)
}

/**
 * 根据模型类型获得模型列表
 */
const getModelByType: (
  provider: string,
  type: string,
  loading?: Ref<boolean>
) => Promise<Result<any>> = (provider, type, loading) => {
  return get(`/setting/${provider}/${type}/models`, {}, loading)
}

export default {
  getProvider,
  getProviderByModelType,
  getAllModelTypes,
  getModelTypes,
  getModelByType
}
