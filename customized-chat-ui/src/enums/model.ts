export enum ModelType {
  LLM = '大语言模型',
  EMBEDDING = '向量模型'
}

export const modelTypeList = [
  {
    text: ModelType.LLM,
    value: 'LLM'
  },
  {
    text: ModelType.EMBEDDING,
    value: 'EMBEDDING'
  }
]
