<template>
  <div
    class="menu-item-container flex-center h-full cursor p-8"
    :class="isActive ? 'active' : ''"
    @click="router.push({ name: menu.name })"
  >
    <div class="title">
      {{ menu.meta?.title as string }}
    </div>
  </div>
</template>
<script setup lang="ts">
import { useRouter, useRoute, type RouteRecordRaw } from 'vue-router'
import { computed } from 'vue'
const router = useRouter()
const route = useRoute()

const props = defineProps<{
  menu: RouteRecordRaw
}>()

const isActive = computed(() => {
  const { name, path, meta } = route
  return (name == props.menu.name && path == props.menu.path) || meta?.activeMenu == props.menu.path
})
</script>
<style lang="scss" scoped>
.menu-item-container {
  &:hover {
    color: var(--el-color-primary);
  }
}

.active {
  color: var(--el-color-primary);
}
</style>
