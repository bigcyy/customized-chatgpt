<template>
  <div class="top-menu-container flex-center h-full">
    <MenuItem v-for="(menu, index) in topMenuList" :menu="menu" :key="index"> </MenuItem>
  </div>
</template>
<script setup lang="ts">
import { computed } from 'vue'
import MenuItem from './MenuItem.vue'
import { getChildRouteListByPathAndName } from '@/router/index'

const topMenuList = computed(() => {
  return getChildRouteListByPathAndName('/', 'home')
})
</script>
<style lang="scss" scope></style>
