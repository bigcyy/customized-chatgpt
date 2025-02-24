interface Provider {
  providerName: string
  providerIconPath: string
}

interface ModelType {
  key: string
  name: string
  desc: string
}

interface ModelConfigForm {
  id?: number
  displayName: string
  modelType: string
  modelName: string
  provider: string
  modelConfig: {
    temperature: number
    maxTokens: number
  }
  apiUrl: string
  apiKey: string
}

interface ModelVO {
  id: number
  displayName: string
  modelType: ModelType
  modelName: string
  provider: Provider
}

export type { Provider, ModelType, ModelConfigForm, ModelVO }
