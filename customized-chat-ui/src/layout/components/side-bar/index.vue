<template>
  <div>
    <el-scrollbar>
      <el-menu router>
        <SideBarItem
          v-for="(menu, index) in menuList"
          :key="index"
          :menu="menu"
          :activeMenu="route.path"
        />
      </el-menu>
    </el-scrollbar>
  </div>
</template>

<script setup lang="ts">
import { computed } from 'vue'
import { useRoute } from 'vue-router'
import { getChildRouteListByPathAndName } from '@/router/index'
import SideBarItem from './SideBarItem.vue'

const route = useRoute()

const menuList = computed(() => {
  const { meta } = route
  return getChildRouteListByPathAndName(meta.parentPath, meta.parentName)
})
</script>

<style lang="scss" scoped></style>
