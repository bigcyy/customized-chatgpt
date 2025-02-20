<template>
  <el-dialog
    v-model="dialogVisible"
    width="600px"
    :close-on-click-modal="false"
    :close-on-press-escape="false"
    :destroy-on-close="true"
    @closed="close"
    append-to-body
  >
    <template #header>
      <div class="flex flex-between">
        <h4>选择提供商</h4>
        <el-dropdown v-loading="typesLoading">
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
                :key="item.key"
                @click="checkModelType(item.key)"
              >
                <span>{{ item.name }}</span>
                <el-icon v-if="currentModelText === item.name"><Check /></el-icon>
              </el-dropdown-item>
            </el-dropdown-menu>
          </template>
        </el-dropdown>
      </div>
    </template>
    <el-row :gutter="12" v-loading="loading">
      <el-col :span="12" class="mb-16" v-for="(provider, index) in providerList" :key="index">
        <el-card shadow="hover" @click="openCreateModelDialog(provider)">
          <div class="flex align-center cursor">
            <span
              :innerHTML="provider.providerIconPath"
              alt=""
              style="height: 24px; width: 24px"
              class="mr-8"
            />
            <span>{{ provider.providerName }}</span>
          </div>
        </el-card>
      </el-col>
    </el-row>
  </el-dialog>
</template>

<script lang="ts" setup>
import { ref } from 'vue'
import { type Provider, type ModelType } from '@/api/type/model'
import ModelApi from '@/api/model'

const dialogVisible = ref(false)
const providerList = ref<Array<Provider>>([])
const modelTypeOptions = ref<Array<ModelType>>([])
const currentModelText = ref('所有模型')
const loading = ref(false)
const typesLoading = ref(false)

const emit = defineEmits(['change'])

const open = async () => {
  await ModelApi.getAllModelTypes(typesLoading).then((res) => {
    modelTypeOptions.value = [{ key: '', name: '所有模型', desc: '' }, ...res.data.types]
  })
  dialogVisible.value = true
  checkModelType('')
}

const close = () => {
  dialogVisible.value = false
}

// 选择模型类型
const checkModelType = (modelType: string) => {
  currentModelText.value = modelTypeOptions.value.filter((item) => item.key === modelType)[0].name
  // 获取提供商
  ModelApi.getProviderByModelType(modelType, loading).then((res) => {
    providerList.value = res.data.providers
  })
}

const openCreateModelDialog = (provider: Provider) => {
  close()
  emit('change', provider)
}

defineExpose({
  open,
  close
})
</script>

<style scoped lang="scss"></style>
