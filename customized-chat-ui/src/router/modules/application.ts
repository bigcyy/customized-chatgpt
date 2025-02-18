import AppLayout from '@/layout/AppLayout.vue'

const applicationRouter = {
  path: '/application',
  name: 'application',
  component: AppLayout,
  meta: {
    title: '应用',
    icon: 'application'
  },
  redirect: '/application',
  children: [
    {
      path: '/application',
      name: 'application-index',
      component: () => import('@/views/application/index.vue')
    }
  ]
}

export default applicationRouter
