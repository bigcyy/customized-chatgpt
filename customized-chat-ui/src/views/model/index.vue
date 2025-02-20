<template>
  <div class="content-container">
    <div class="content-header">
      <h4>模型</h4>
    </div>
    <div class="content-body">
      <div class="content-body-header flex flex-between">
        <el-button type="primary" @click="handleAddModel">新增模型</el-button>
        <el-input v-model="search" placeholder="请输入内容" style="width: 240px" />
      </div>
      <el-table :data="filterTableData" style="width: 100%">
        <el-table-column label="Date" prop="date" />
        <el-table-column label="Name" prop="name" />
        <el-table-column align="right">
          <template #default="scope">
            <el-button size="small" @click="handleEdit(scope.$index, scope.row)"> Edit </el-button>
            <el-button size="small" type="danger" @click="handleDelete(scope.$index, scope.row)">
              Delete
            </el-button>
          </template>
        </el-table-column>
      </el-table>
    </div>
    <CreateModelDialog ref="createModelRef" @change="openSelectProviderDialog()" />
    <SelectProviderDialog ref="selectProviderRef" @change="openCreateModelDialog($event)" />
  </div>
</template>

<script lang="ts" setup>
import { computed, ref } from 'vue'
import SelectProviderDialog from './component/SelectProviderDialog.vue'
import CreateModelDialog from './component/CreateModelDialog.vue'
import { type Provider } from '@/api/type/model'

interface User {
  date: string
  name: string
  address: string
}

const selectProviderRef = ref<InstanceType<typeof SelectProviderDialog>>()
const createModelRef = ref<InstanceType<typeof CreateModelDialog>>()

const search = ref('')
const filterTableData = computed(() =>
  tableData.filter(
    (data) => !search.value || data.name.toLowerCase().includes(search.value.toLowerCase())
  )
)
const handleEdit = (index: number, row: User) => {
  console.log(index, row)
}
const handleDelete = (index: number, row: User) => {
  console.log(index, row)
}
const handleAddModel = () => {
  selectProviderRef.value?.open()
}
const openCreateModelDialog = (provider: Provider) => {
  createModelRef.value?.open(provider)
}
const openSelectProviderDialog = () => {
  selectProviderRef.value?.open()
}
const tableData: User[] = [
  {
    date: '2016-05-03',
    name: 'Tom',
    address: 'No. 189, Grove St, Los Angeles'
  },
  {
    date: '2016-05-02',
    name: 'John',
    address: 'No. 189, Grove St, Los Angeles'
  },
  {
    date: '2016-05-04',
    name: 'Morgan',
    address: 'No. 189, Grove St, Los Angeles'
  },
  {
    date: '2016-05-01',
    name: 'Jessy',
    address: 'No. 189, Grove St, Los Angeles'
  }
]
</script>

<style scoped lang="scss">
.content-body {
  background-color: var(--app-view-bg-color);
  box-sizing: border-box;
  min-width: 847px;
}
</style>
