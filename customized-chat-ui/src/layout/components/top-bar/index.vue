<template>
  <div class="top-bar-container flex-between">
    <div class="top-bar-left app-title-container flex-center h-full">
      <div class="app-title flex-center cursor h-full" @click="router.push('/')">
        <img src="../../../assets/logo.png" alt="logo" />
        <span>CChat</span>
      </div>
    </div>
    <div class="top-bar-center flex-center h-full">
      <TopMenu />
    </div>
    <div class="top-bar-right flex-center h-full">
      <el-link :href="githubUrl" target="_blank" class="github-link flex-center">
        <el-icon class="github-icon"><Platform /></el-icon>
        <span class="star-count">{{ starCount }}</span>
      </el-link>
    </div>
  </div>
</template>

<script setup lang="ts">
import { Platform } from '@element-plus/icons-vue'
import { ref, onMounted } from 'vue'
import { useRouter } from 'vue-router'
import TopMenu from './top-menu/index.vue'

const router = useRouter()
const starCount = ref(0)
const githubUrl = 'https://github.com/bigcyy/customized-chat'

const fetchGitHubStars = async () => {
  try {
    const response = await fetch('https://api.github.com/repos/bigcyy/customized-chat')
    const data = await response.json()
    starCount.value = data.stargazers_count
  } catch (error) {
    console.error('获取 star 数失败:', error)
  }
}

onMounted(() => {
  fetchGitHubStars()
})
</script>

<style lang="scss" scoped>
.top-bar-container {
  height: var(--app-header-height);
  box-sizing: border-box;
  padding: var(--app-header-padding);

  .app-title {
    img {
      height: 100%;
      width: auto;
      object-fit: contain;
    }
  }
}
</style>
