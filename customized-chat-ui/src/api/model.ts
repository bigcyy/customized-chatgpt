import type { Ref } from 'vue'
import Result from '@/request/Result'
import { get, post, put, del } from '@/request'
import type { ModelConfigForm } from '@/api/type/model'

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

/**
 * 创建模型
 */
const createModel: (data: ModelConfigForm, loading?: Ref<boolean>) => Promise<Result<any>> = (
  data,
  loading
) => {
  return post('/setting/models', data, {}, loading)
}

/**
 * 获得已经保存的模型列表
 */
const getSavedModels: (loading?: Ref<boolean>) => Promise<Result<any>> = (loading) => {
  return get('/setting/models', {}, loading)
}

/**
 * 更新模型
 */
const updateModel: (data: ModelConfigForm, loading?: Ref<boolean>) => Promise<Result<any>> = (
  data,
  loading
) => {
  return put(`/setting/models`, data, {}, loading)
}

/**
 * 根据id获得模型
 */
const getModelById: (id: number, loading?: Ref<boolean>) => Promise<Result<any>> = (
  id,
  loading
) => {
  return get(`/setting/models/${id}`, {}, loading)
}

/**
 * 删除模型
 */
const deleteModel: (id: number, loading?: Ref<boolean>) => Promise<Result<any>> = (id, loading) => {
  return del(`/setting/models/${id}`, {}, loading)
}

export default {
  getProvider,
  getProviderByModelType,
  getAllModelTypes,
  getModelTypes,
  getModelByType,
  createModel,
  getSavedModels,
  updateModel,
  getModelById,
  deleteModel
}
