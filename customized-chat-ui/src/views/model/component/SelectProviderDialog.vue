<template>
  <el-dialog
    v-model="dialogVisible"
    width="600px"
    :close-on-click-modal="false"
    :close-on-press-escape="false"
    :destroy-on-close="true"
    append-to-body
  >
    <template #header>
      <div class="flex flex-between">
        <h4>选择提供商</h4>
        <el-dropdown>
          <span class="cursor">
            {{ currentModelText }}
            <el-icon class="el-icon--right">
              <arrow-down />
            </el-icon>
          </span>
          <template #dropdown>
            <el-dropdown-menu>
              <el-dropdown-item
                v-for="item in modelTypeOptions"
                :key="item.value"
                @click="checkModelType(item.value)"
              >
                <span>{{ item.text }}</span>
                <el-icon v-if="currentModelText === item.text"><Check /></el-icon>
              </el-dropdown-item>
            </el-dropdown-menu>
          </template>
        </el-dropdown>
      </div>
    </template>
    <el-row v-loading="loading">
      <el-col :span="12" class="mb-16" v-for="(provider, index) in providerList" :key="index">
        <el-card>
          <template #header>
            <div class="flex flex-between">
              <span>{{ provider.providerName }}</span>
            </div>
          </template>
        </el-card>
      </el-col>
    </el-row>
  </el-dialog>
</template>

<script lang="ts" setup>
import { ref } from 'vue'
import { modelTypeList } from '@/enums/model'
import { type Provider } from '@/api/type/model'
import ModelApi from '@/api/model'

const dialogVisible = ref(false)
const providerList = ref<Array<Provider>>([])
const currentModelText = ref('所有模型')
const modelTypeOptions = [{ text: '所有模型', value: '' }, ...modelTypeList]
const loading = ref(false)
const open = () => {
  dialogVisible.value = true
  checkModelType('')
}

// 选择模型类型
const checkModelType = (modelType: string) => {
  currentModelText.value = modelTypeOptions.filter((item) => item.value === modelType)[0].text
  // 获取提供商
  ModelApi.getProviderByModelType(modelType, loading).then((res) => {
    providerList.value = res.data.providers
  })
}

defineExpose({
  open
})
</script>

<style scoped lang="scss"></style>
