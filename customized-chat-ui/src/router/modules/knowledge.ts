import AppLayout from '@/layout/AppLayout.vue'

const knowledgeRouter = {
  path: '/knowledge',
  name: 'knowledge',
  component: AppLayout,
  meta: {
    title: '知识库',
    icon: 'knowledge'
  },
  redirect: '/knowledge',
  children: [
    {
      path: '/knowledge',
      name: 'knowledge-index',
      component: () => import('@/views/knowledge/index.vue')
    }
  ]
}

export default knowledgeRouter
