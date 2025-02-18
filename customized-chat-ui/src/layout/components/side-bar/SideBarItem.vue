<template>
  <div>
    <el-sub-menu v-if="menu.children && menu.children.length > 0">
      <template #title>
        <el-icon>
          <AppIcon v-if="menu.meta && menu.meta.icon" :iconName="menuIcon" class="sidebar-icon" />
        </el-icon>
        <span>{{ menu.meta?.title as string }}</span>
      </template>
      <SideBarItem
        v-for="(child, index) in menu.children"
        :key="index"
        :menu="child"
        :activeMenu="activeMenu"
      ></SideBarItem>
    </el-sub-menu>
    <el-menu-item v-else>
      <template #title>
        <el-icon>
          <AppIcon v-if="menu.meta && menu.meta?.icon" :iconName="menuIcon" class="sidebar-icon" />
        </el-icon>
        <span>{{ menu.meta?.title as string }}</span>
      </template>
    </el-menu-item>
  </div>
</template>

<script setup lang="ts">
import { type RouteRecordRaw } from 'vue-router'
import { computed } from 'vue'

const props = defineProps<{
  menu: RouteRecordRaw
  activeMenu: any
}>()

const menuIcon = computed(() => {
  if (props.menu.path == props.activeMenu) {
    return props.menu.meta?.iconActive || props.menu.meta?.icon
  }
  return props.menu.meta?.icon
})
</script>

<style lang="scss" scoped></style>
